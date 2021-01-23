package io.github.ihelin.seven.product.service.impl;

import io.github.ihelin.seven.common.utils.JsonUtils;
import io.github.ihelin.seven.product.entity.SkuInfoEntity;
import io.github.ihelin.seven.product.interceptor.CartInterceptor;
import io.github.ihelin.seven.product.service.CartService;
import io.github.ihelin.seven.product.service.SkuInfoService;
import io.github.ihelin.seven.product.service.SkuSaleAttrValueService;
import io.github.ihelin.seven.product.vo.Cart;
import io.github.ihelin.seven.product.vo.CartItem;
import io.github.ihelin.seven.product.vo.UserInfoTo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @author iHelin
 */
@Service
public class CartServiceImpl implements CartService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SkuInfoService skuInfoService;

    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Autowired
    private ThreadPoolExecutor executor;

    private final String CART_PREFIX = "seven:cart:";

    @Override
    public CartItem addToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException {
        BoundHashOperations<String, Long, String> cartOperation = getCartOps();
        String res = cartOperation.get(skuId);
        CartItem cartItem;
        if (StringUtils.isEmpty(res)) {
            //如果购物车没有当前商品，就添加
            cartItem = new CartItem();
            // 异步编排
            CompletableFuture<Void> getSkuInfo = CompletableFuture.runAsync(() -> {
                // 1. 查询当前要添加的商品的信息
                SkuInfoEntity skuInfoEntity = skuInfoService.getById(skuId);
                // 2. 添加新商品到购物车
                cartItem.setCount(num);
                cartItem.setCheck(true);
                cartItem.setImage(skuInfoEntity.getSkuDefaultImg());
                cartItem.setPrice(skuInfoEntity.getPrice());
                cartItem.setTitle(skuInfoEntity.getSkuTitle());
                cartItem.setSkuId(skuId);
            }, executor);

            CompletableFuture<Void> getSkuSaleAttrValues = CompletableFuture.runAsync(() -> {
                // 3. 查询sku组合信息
                List<String> values = skuSaleAttrValueService.getSkuSaleAttrValuesAsStringList(skuId);
                cartItem.setSkuAttr(values);
            }, executor);

            CompletableFuture.allOf(getSkuInfo, getSkuSaleAttrValues).get();
        } else {
            //如果购物车有当前商品，就修改数量
            cartItem = JsonUtils.parseObject(res, CartItem.class);
            if (cartItem != null) {
                cartItem.setCount(cartItem.getCount() + num);
                cartItem.setCheck(true);
            }
        }
        cartOperation.put(skuId, JsonUtils.toJSONString(cartItem));
        return cartItem;
    }

    @Override
    public CartItem getCartItem(Long skuId) {
        BoundHashOperations<String, Long, String> cartOps = getCartOps();
        String o = cartOps.get(skuId);
        return JsonUtils.parseObject(o, CartItem.class);
    }

    @Override
    public Cart getCart() throws ExecutionException, InterruptedException {
        UserInfoTo userInfoTo = CartInterceptor.THREAD_LOCAL.get();
        Cart cart = new Cart();
        // 临时购物车的key
        String tempCartKey = CART_PREFIX + userInfoTo.getUserKey();
        if (userInfoTo.getUserId() != null) {
            // 1. 已登录 对用户的购物车进行操作
            String cartKey = CART_PREFIX + userInfoTo.getUserId();
            // 1.1 如果临时购物车的数据没有进行合并
            List<CartItem> tempItem = getCartItems(tempCartKey);
            if (tempItem != null) {
                // 1.2 临时购物车有数据 则进行合并
                logger.info("\n[" + userInfoTo.getUsername() + "] 的购物车已合并");
                for (CartItem cartItem : tempItem) {
                    addToCart(cartItem.getSkuId(), cartItem.getCount());
                }
                // 1.3 清空临时购物车
                clearCart(tempCartKey);
            }
            // 1.4 获取登录后的购物车数据 [包含合并过来的临时购物车数据]
            List<CartItem> cartItems = getCartItems(cartKey);
            cart.setItems(cartItems);
        } else {
            // 2. 没登录 获取临时购物车的所有购物项
            cart.setItems(getCartItems(tempCartKey));
        }
        return cart;
    }

    @Override
    public void clearCart(String cartKey) {
        stringRedisTemplate.delete(cartKey);
    }

    @Override
    public void checkItem(Long skuId, Integer check) {
        // 获取要选中的购物项
        CartItem cartItem = getCartItem(skuId);
        cartItem.setCheck(check == 1);
        BoundHashOperations<String, Long, String> cartOps = getCartOps();
        cartOps.put(skuId, JsonUtils.toJSONString(cartItem));
    }

    @Override
    public void changeItemCount(Long skuId, Integer num) {
        CartItem cartItem = getCartItem(skuId);
        cartItem.setCount(num);
        BoundHashOperations<String, Long, String> cartOps = getCartOps();
        cartOps.put(skuId, JsonUtils.toJSONString(cartItem));
    }

    @Override
    public void deleteItem(Long skuId) {
        BoundHashOperations<String, Long, String> cartOps = getCartOps();
        cartOps.delete(skuId);
    }

    @Override
    public BigDecimal toTrade() throws ExecutionException, InterruptedException {
        BigDecimal amount = getCart().getTotalAmount();
        UserInfoTo userInfoTo = CartInterceptor.THREAD_LOCAL.get();
        stringRedisTemplate.delete(CART_PREFIX + (userInfoTo.getUserId() != null ? userInfoTo.getUserId().toString() : userInfoTo.getUserKey()));
        return amount;
    }

    @Override
    public List<CartItem> getUserCartItems() {
        UserInfoTo userInfoTo = CartInterceptor.THREAD_LOCAL.get();
        List<CartItem> result = new ArrayList<>();
        if (userInfoTo.getUserId() != null) {
            String cartKey = CART_PREFIX + userInfoTo.getUserId();
            List<CartItem> cartItems = getCartItems(cartKey);
            // 获取所有被选中的购物项
            result = cartItems.stream().filter(CartItem::getCheck).peek(item -> {
                SkuInfoEntity skuInfoEntity = skuInfoService.getById(item.getSkuId());
                item.setPrice(skuInfoEntity.getPrice());
            }).collect(Collectors.toList());
        }
        return result;
    }

    /**
     * 获取购物车所有项
     */
    private List<CartItem> getCartItems(String cartKey) {
        BoundHashOperations<String, Long, String> hashOps = stringRedisTemplate.boundHashOps(cartKey);
        List<String> values = hashOps.values();
        List<CartItem> results = new ArrayList<>();
        if (values != null && values.size() > 0) {
            results = values.stream().map(obj -> JsonUtils.parseObject(obj, CartItem.class)).collect(Collectors.toList());
        }
        return results;
    }

    /**
     * 获取到我们要操作的购物车 [已经包含用户前缀 只需要带上用户id 或者临时id 就能对购物车进行操作]
     */
    private BoundHashOperations<String, Long, String> getCartOps() {
        UserInfoTo userInfoTo = CartInterceptor.THREAD_LOCAL.get();
        // 1. 这里我们需要知道操作的是离线购物车还是在线购物车
        String cartKey = CART_PREFIX;
        if (userInfoTo.getUserId() != null) {
            logger.debug("\n用户 [" + userInfoTo.getUsername() + "] 正在操作购物车");
            // 已登录的用户购物车的标识
            cartKey += userInfoTo.getUserId();
        } else {
            logger.debug("\n临时用户 [" + userInfoTo.getUserKey() + "] 正在操作购物车");
            // 未登录的用户购物车的标识
            cartKey += userInfoTo.getUserKey();
        }
        // 绑定这个 key 以后所有对redis 的操作都是针对这个key
        return stringRedisTemplate.boundHashOps(cartKey);
    }
}

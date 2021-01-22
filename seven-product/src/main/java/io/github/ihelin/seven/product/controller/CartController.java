package io.github.ihelin.seven.product.controller;

import io.github.ihelin.seven.product.service.CartService;
import io.github.ihelin.seven.product.vo.Cart;
import io.github.ihelin.seven.product.vo.CartItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author iHelin
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String PATH = "redirect:http://cart.seven.com/cart/cart.html";

    @Autowired
    private CartService cartService;

    @ResponseBody
    @GetMapping("/currentUserCartItems")
    public List<CartItem> getCurrentUserCartItems() {
        return cartService.getUserCartItems();
    }

    @ResponseBody
    @GetMapping("toTrade")
    public String toTrade() throws ExecutionException, InterruptedException {
        BigDecimal price = cartService.toTrade();
        return "结算成功,共：￥" + price.toString();
    }

    /**
     * 购物车删除商品
     *
     * @param skuId
     * @return
     */
    @GetMapping("/deleteItem")
    public String deleteItem(@RequestParam("skuId") Long skuId) {
        cartService.deleteItem(skuId);
        return PATH;
    }

    /**
     * 改变购物车中物品的数量
     *
     * @param skuId
     * @param num
     * @return
     */
    @GetMapping("/countItem")
    public String countItem(@RequestParam("skuId") Long skuId, @RequestParam("num") Integer num) {
        cartService.changeItemCount(skuId, num);
        return PATH;
    }

    /**
     * 选择/反选购物车商品
     *
     * @param skuId
     * @param check
     * @return
     */
    @GetMapping("checkItem.html")
    public String checkItem(@RequestParam("skuId") Long skuId, @RequestParam("check") Integer check) {
        cartService.checkItem(skuId, check);
        return PATH;
    }

    /**
     * 添加商品到购物车
     * org.springframework.web.servlet.mvc.support.RedirectAttributes#addAttribute(java.lang.String, java.lang.Object): 会自动将数据添加到url后面
     */
    @GetMapping("/addToCart")
    public String addToCart(@RequestParam("skuId") Long skuId,
                            @RequestParam("num") Integer num,
                            RedirectAttributes redirectAttributes) throws ExecutionException, InterruptedException {

        cartService.addToCart(skuId, num);
        redirectAttributes.addAttribute("skuId", skuId);
        // 重定向到成功页面
        return "redirect:http://cart.seven.com/cart/addToCartSuccess.html";
    }

    /**
     * 添加成功页面
     *
     * @param skuId
     * @param model
     * @return
     */
    @GetMapping("/addToCartSuccess.html")
    public String addToCartSuccessPage(@RequestParam(value = "skuId", required = false) Object skuId, Model model) {
        CartItem cartItem = null;
        // 然后在查一遍 购物车
        if (skuId == null) {
            model.addAttribute("item", null);
        } else {
            try {
                cartItem = cartService.getCartItem(Long.parseLong((String) skuId));
            } catch (NumberFormatException e) {
                logger.warn("恶意操作! 页面传来非法字符.");
            }
            model.addAttribute("item", cartItem);
        }
        return "success";
    }

    /**
     * 浏览器有一个cookie：user-key 标识用户身份 一个月后过期
     * 每次访问都会带上这个 user-key
     * 如果没有临时用户 还要帮忙创建一个
     */
    @GetMapping({"/cart.html"})
    public String cartListPage(Model model) throws ExecutionException, InterruptedException {
        Cart cart = cartService.getCart();
        model.addAttribute("cart", cart);
        return "cartList";
    }
}

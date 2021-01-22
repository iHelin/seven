package io.github.ihelin.seven.product.controller;

import io.github.ihelin.seven.product.entity.CategoryEntity;
import io.github.ihelin.seven.product.service.CategoryService;
import io.github.ihelin.seven.product.vo.Catalog2Vo;
import org.redisson.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author iHelin ihelin@outlook.com
 * @since 2021/1/16 下午1:19
 */
@Controller
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * RLock锁有看门狗机制 会自动帮我们续期，默认三秒自动过期
     * lock.lock(10,TimeUnit.SECONDS); 十二猴子的时间一定要大于业务的时间 否则会出现死锁的情况
     * 如果我们传递了锁的超时时间就给redis发送超时脚本 默认超时时间就是我们指定的
     * 如果我们未指定，就使用 30 * 1000 [LockWatchdogTimeout]
     * 只要占锁成功 就会启动一个定时任务 任务就是重新给锁设置过期时间 这个时间还是 [LockWatchdogTimeout] 的时间 1/3 看门狗的时间续期一次 续成满时间
     */
    @ResponseBody
    @RequestMapping("/index/hello")
    public String hello() {
        RLock lock = redissonClient.getLock("my-lock");
        // 阻塞式等待
        lock.lock();
        try {
            System.out.println("加锁成功，执行业务。" + Thread.currentThread().getId());
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            logger.error("hello error", e);
        } finally {
            System.out.println("释放锁。" + Thread.currentThread().getId());
            lock.unlock();
        }
        return "hello";
    }

    @GetMapping("/index/read")
    @ResponseBody
    public String readValue() {
        RReadWriteLock lock = redissonClient.getReadWriteLock("rw-lock");
        RLock rLock = lock.readLock();
        String s = "";
        rLock.lock();
        try {
            s = stringRedisTemplate.opsForValue().get("writeValue");
        } catch (Exception e) {
            logger.error("readValue error", e);
        } finally {
            rLock.unlock();
        }
        return s;
    }

    /**
     * 读写锁
     */
    @GetMapping("/index/write")
    @ResponseBody
    public String writeValue() {
        RReadWriteLock lock = redissonClient.getReadWriteLock("rw-lock");
        RLock rLock = lock.writeLock();
        rLock.lock();
        String s = "";
        try {
            s = UUID.randomUUID().toString();
            Thread.sleep(5000);
            stringRedisTemplate.opsForValue().set("writeValue", s);
        } catch (Exception e) {
            logger.error("writeValue error", e);
        } finally {
            rLock.unlock();
        }
        return s;
    }

    /**
     * 闭锁 只有设定的人全通过才关门
     */
    @ResponseBody
    @GetMapping("/index/lockDoor")
    public String lockDoor() throws InterruptedException {
        RCountDownLatch door = redissonClient.getCountDownLatch("door");
        // 设置这里有5个人
        door.trySetCount(5);
        door.await();
        return "5个人全部通过了...";
    }

    @ResponseBody
    @GetMapping("/index/go/{id}")
    public String go(@PathVariable("id") Long id) throws InterruptedException {
        RCountDownLatch door = redissonClient.getCountDownLatch("door");
        // 每访问一次相当于出去一个人
        door.countDown();
        return id + "走了";
    }

    /**
     * 尝试获取车位 [信号量]
     * 信号量:也可以用作限流
     */
    @ResponseBody
    @GetMapping("/index/park")
    public String park() {
        RSemaphore park = redissonClient.getSemaphore("park");
        boolean acquire = park.tryAcquire();
        return "获取车位 =>" + acquire;
    }

    /**
     * 离开车位
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/index/leave")
    public String leave() {
        RSemaphore park = redissonClient.getSemaphore("park");
        park.release();
        return "ok => 车位+1";
    }


    @GetMapping({"/", "index", "/index.html"})
    public String indexPage(Model model, HttpSession httpSession) {
        // 获取一级分类所有缓存
        List<CategoryEntity> categorys = categoryService.getLevel1Categories();
        model.addAttribute("categorys", categorys);
        return "index";
    }

    @ResponseBody
    @RequestMapping("index/catalog.json")
    public Map<String, List<Catalog2Vo>> getCatalogJson() {
        Map<String, List<Catalog2Vo>> map = categoryService.getCatalogJson();
        return map;
    }
}

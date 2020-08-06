package com.ns.controller;

import com.example.apijson.entity.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private  RedisTemplate redisTemplate;

    //商品加入购物车
    @PostMapping("/addMenu")
    public ResultData addCartMenu(@RequestBody Map map){
        log.info("商品加入购物车");
        try {
            //判断当前商品 有没有添加 有加数量 没有就添加
            Map<String, Object> o =  redisTemplate.opsForHash().entries(map.get("userId"));
            if(o.isEmpty()){
                redisTemplate.opsForHash().put(map.get("userId"),map.get("menuId"),(Integer)map.get("num"));
            }else{
                boolean hasKey = redisTemplate.opsForHash().hasKey(map.get("userId"),map.get("menuId"));
                if(hasKey){
                    redisTemplate.opsForHash().increment(map.get("userId"), map.get("menuId"), 1);
                }else{
                    redisTemplate.opsForHash().put(map.get("userId"),map.get("menuId"),(Integer)map.get("num"));
                }
            }
            return new ResultData(200,"添加成功",redisTemplate.opsForHash().entries(map.get("userId")));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(444,"添加购物车失败",false);
        }
    }
    //查询商品的数量 userHashRedisTemplate.opsForHash().get(Key， hashKey);
    @PostMapping("/selectMenuNum")
    public ResultData selectCartMenuNum(@RequestBody Map map){
        log.info("查询商品数量");
        try {
            Map<String, Object> o =  redisTemplate.opsForHash().entries(map.get("userId"));
            int num=0;
            for (Map.Entry<String, Object> entry : o.entrySet()) {
                String mapKey = entry.getKey();
                Object mapValue = entry.getValue();
                if(mapKey.equals(map.get("menuId"))){
                   num= (int) mapValue;
                   break;
                }
            }
            return new ResultData(200,"查询成功",num);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(444,"查询失败",false);
        }
    }

    //商品数量增加
    @PostMapping("/addMenuNum")
    public ResultData addCartMenuNum(@RequestBody Map map){
        log.info("商品数量增加");
        try {
            redisTemplate.opsForHash().increment(map.get("userId"), map.get("menuId"), 1);
            return new ResultData(200,"添加成功",redisTemplate.opsForHash().entries(map.get("userId")));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(444,"添加购物车失败",false);
        }
    }
    //商品数量减少
    @PostMapping("/decMenuNum")
    public ResultData decCartMenuNum(@RequestBody Map map){
        log.info("商品数量减少");
        try {
            Integer integer= (Integer) redisTemplate.opsForHash().get(map.get("userId"),map.get("menuId"));
            if(integer<=1){
                redisTemplate.opsForHash().delete(map.get("userId"), map.get("menuId"));
            }else{
                redisTemplate.opsForHash().increment(map.get("userId"), map.get("menuId"), -1);
            }
            return new ResultData(200,"减少成功",redisTemplate.opsForHash().entries(map.get("userId")));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(444,"减少失败",false);
        }
    }

    //删除商品 userHashRedisTemplate.opsForHash().delete(key, sourceOrgId);
    @PutMapping("/deleteMenu")
    public ResultData deleteCartMenu(@RequestBody Map map){
        log.info("删除商品");
        try {
             redisTemplate.opsForHash().delete(map.get("userId"),map.get("menuId"));
            return new ResultData(200,"删除商品成功",redisTemplate.opsForHash().entries(map.get("userId")));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(444,"删除失败",false);
        }
    }

    //查询所有购物车商品
     @PostMapping("/findCart")
    public ResultData findCart(@RequestBody Map map){
        log.info("查询所有购物车商品");
        try {
            Map<String, Object> o =  redisTemplate.opsForHash().entries(map.get("userId"));
            return new ResultData(200,"查询所有购物车商品成功",o);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(444,"查询失败",false);
        }
    }
    //清空购物车
    @PostMapping("/deleteCart")
    public ResultData deleteCart(@RequestBody Map map){
        log.info("清空购物车");
        try {
            redisTemplate.delete((map.get("userId")));
            return new ResultData(200,"清空购物车成功",true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultData(444,"清空购物车",false);
        }
    }

}

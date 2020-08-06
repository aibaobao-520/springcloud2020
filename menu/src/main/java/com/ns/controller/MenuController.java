package com.ns.controller;

import com.example.apijson.entity.Page;
import com.example.apijson.entity.ResultData;
import com.ns.config.OSSConfig;
import com.ns.entity.Menu;
import com.ns.entity.Type;
import com.ns.service.MenuService;
import com.ns.service.TypeService;
import com.ns.vo.MenuTypeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    OSSConfig ossConfig;

    @Autowired
     private MenuService menuService;
    @Autowired
    private TypeService typeService;

    /**
     * 支持跨域的请求 到vue
     * @param index
     * @param limit
     * @return
     */
    //@CrossOrigin
    @GetMapping("/findAll/{index}/{limit}")
    public ResultData findAll(@PathVariable("index") int
                                      index, @PathVariable("limit") int limit) {
        Page page =menuService.findPage(index, limit);
        if(page.getTotal()!=null){
            return new ResultData(200, "查询成功", page);
        } else {
            return new ResultData(444, "无数据", false);
        }
    }
  /* @GetMapping("/findAll")
    public List<Type> findAll(){
        return typeService.findAll();
    }*/

    @PostMapping("/save")
    public ResultData save(@RequestBody Menu menu) {
        log.info("添加餐品");
        try {
            menuService.save(menu);
            return new ResultData(200, "添加菜品成功", true);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResultData(444, "添加菜品失败", false);
        }
    }

    @GetMapping("/findById/{id}")
    public ResultData findById(@PathVariable("id") long id) {
        log.info("查询餐品");
        try {
            Menu m = menuService.findById(id);
            return new ResultData(200, "查询餐品成功", m);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResultData(444, "查询餐品失败", false);
        }
    }

    @GetMapping("/findAllType")
    public ResultData findAllType() {
        log.info("查询菜系");
        try {
            List<Type> types = typeService.findAll();
            return new ResultData(200, "查询餐品成功", types);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResultData(444, "查询餐品失败", false);
        }
    }

    @PutMapping("/update")
    public ResultData update(@RequestBody Menu menu) {
        log.info("修改餐品");
        try {
            menuService.update(menu);
            return new ResultData(200, "修改餐品成功", true);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResultData(444, "修改餐品失败", false);
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResultData deleteById(@PathVariable("id") long id) {
        log.info("删除餐品");
        try {
            menuService.deleteById(id);
            return new ResultData(200, "删除餐品成功", true);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResultData(444, "删除餐品失败", false);
        }
    }
    @GetMapping("/findByTypeMenu/index")
    public ResultData findByTypeMenu() {
        log.info("查询全部菜系的菜");
        try {
           List<MenuTypeVo> menuTypeVos= menuService.findByMenuTypeVo();
            //return new ResultData(200, "查询成功", menus);
             return new ResultData(200, "查询成功", menuTypeVos);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResultData(444, "查询失败", false);
        }
    }

    @GetMapping("/findByMenu/{id}")
    public ResultData findByMenu(@PathVariable("id") long id) {
        log.info("查询菜系下的菜");
        try {
          List<Menu> menus= menuService.findByMenu(id);
          if(menus.isEmpty()){
              return new ResultData(200, "该菜系下无菜品", true);
              }else{
            return new ResultData(200, "查询成功", menus);
          }
            //   return new ResultData(200, "该菜系下无菜品", true);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return new ResultData(444, "查询失败", false);
        }
    }

    @PostMapping("/image")
    public ResultData deleteById(MultipartFile file) throws Exception {
        log.info("上传服务器的图片");
        try {
            String s =menuService.uploadFile(file,ossConfig);
            return new ResultData(200,"上传图片成功",s);
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultData(400,"上传图片失败",false);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultData(400,"上传图片失败",false);
        }

    }
}

package com.ns.service.impl;

import com.example.apijson.entity.Page;
import com.ns.config.OSSConfig;
import com.ns.entity.Menu;
import com.ns.entity.Type;
import com.ns.repository.MenuRepository;
import com.ns.service.MenuService;
import com.ns.service.TypeService;
import com.ns.util.OSSSingleUtil;
import com.ns.util.ftp;
import com.ns.vo.MenuTypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
   @Resource
    private MenuRepository menuRepository;
    @Resource
   private TypeService typeService;



    @Override
    public int count() {
        return menuRepository.count();
    }

    @Override
    public Menu findById(long id) {
        //return menuRepository.selectByPrimaryKey(id);
        return menuRepository.findById(id);
    }
    @Transactional
    @Override
    public void save(Menu menu) {
       menuRepository.save(menu);
    }
    @Transactional
    @Override
    public void deleteById(long id) {
        menuRepository.deleteById(id);
    }

    @Override
    public List<Menu> findAll(int index, int limit) {
        return menuRepository.findAll((index-1)*limit,limit);
    }

    @Override
    @Transactional
    public void update(Menu menu) {
        menuRepository.update(menu);
    }

    @Override
    @Transactional
    public String uploadFile(MultipartFile file, OSSConfig ossConfig) throws Exception {
        //获取到上传的文件名
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        int lastIndexOf = fileName.lastIndexOf(".");
        //获取文件的后缀名 .jpg
        String suffix = fileName.substring(lastIndexOf);
       //能够从指定的地址加载文件资源
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!path.exists()) path = new File("");
        String pathStr = path.getAbsolutePath();
        pathStr = pathStr.replace("\\target\\classes", "");
        String pathname=pathStr.substring(0,pathStr.lastIndexOf("\\"));
        File file2 = new File(pathname+"\\"+fileName);

        //上传文件到oss
        // 上传文件流。
        InputStream in=new FileInputStream(ftp.multipartFileToFile(file));
        String s= OSSSingleUtil.upload(ossConfig.getEndpoint(),ossConfig.getAccessKeyId(),
                ossConfig.getAccessKeySecret(),ossConfig.getBucketName(),
                ossConfig.getUrl(),in,"menu/image",suffix);
        in.close();
        if (file2.isFile() && file2.exists()) {
            file2.delete();
        }
        return  s;
    }

    @Override
    public Page findPage(int index, int limit) {
        int index2=(index-1)*limit;
        List<Menu> menus = menuRepository.findAll(index2, limit);
        Page page = new Page();
        if (menus.size() > 0) {
            int c = menuRepository.count();
            page.setTotalPage(c % limit == 0 ? c / limit : c / limit + 1);
            page.setTotal(c);
            page.setPageNum(index);
            page.setT(menus);
      }
        return  page;
    }

    @Override
    public List<Menu> findByMenu(long id) {
        return menuRepository.findByMenu(id);
    }

    @Override
    public List<MenuTypeVo> findByMenuTypeVo() {
     List<Type> types=typeService.findAll();
     List<MenuTypeVo> menuTypeVos=types.stream().map(t -> new MenuTypeVo(
             t.getId(),t.getName(),menuRepository.findByMenu(t.getId())
     )).collect(Collectors.toList());
        return menuTypeVos;
    }
}

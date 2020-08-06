package com.ns.service.Impl;

import com.example.apijson.entity.Page;
import com.ns.entity.User;
import com.ns.repository.UserRepository;
import com.ns.service.UserService;
/*import org.springframework.amqp.core.AmqpTemplate;*/
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService/*, ApplicationRunner*/ {

    @Resource
    private UserRepository userRepository;

    @Override
    public List<User> findAll(Integer page,Integer limit) {
        page=(page-1)*limit;
        return userRepository.findAll(page,limit);
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setRegisterdate(new Date());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(User user) {
           user.setRegisterdate(new Date());
           userRepository.update(user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        userRepository.delete(id);
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public Integer findAllCount() {
        return userRepository.findAllCount();
    }

    @Override
    public Page findPage(Integer page, Integer limit) {
        List<User> users=findAll(page,limit);
        Page page1=new Page();
        if(users.size()>0){
        page1.setPageNum(page);
        int c=findAllCount();
        page1.setTotalPage(c%limit==0?c/limit:c/limit+1);
        page1.setTotal(c);
        page1.setT(users);
        }
        return page1;
    }

    /**
     * 定义一个消息生产者的方法 启动后执行的方法
     */
  /*  @Autowired
    private AmqpTemplate rabbitTemplate;
    @Override
    public void run(ApplicationArguments args) throws Exception {

            String context="你办事吗?上班摸鱼";
            System.out.println("hekun : " + context);
            // 第一个参数为刚刚定义的队列名称
            this.rabbitTemplate.convertAndSend("rabbit", context);

    }*/
}

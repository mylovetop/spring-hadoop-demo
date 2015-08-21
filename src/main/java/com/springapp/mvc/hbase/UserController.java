package com.springapp.mvc.hbase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zdsoft on 15-8-21.
 */
@Controller
public class UserController {

    private static final Log log = LogFactory.getLog(UserApp.class);



    public static void main(String[] args) throws Exception {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext(
                "/application-context.xml", UserApp.class);
        log.info("HBase Application Running");


        UserUtils userUtils = context.getBean(UserUtils.class);
        userUtils.initialize();
        userUtils.addUsers();

        UserRepository userRepository = context.getBean(UserRepository.class);
        List<User> users = userRepository.findAll();
        System.out.println("Number of users = " + users.size());
        System.out.println(users);
        context.registerShutdownHook();

    }

    @Autowired
    private UserUtils userUtils;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/userInfo.html")
    public String userInfo(ModelMap modelMap){
        try {
//            userUtils.initialize();
//            userUtils.addUsers();
            List<User> users = userRepository.findAll();
            modelMap.put("message", users.size());
        } catch (Exception e){
            log.debug(e);
        }

        return "user-info";
    }
}

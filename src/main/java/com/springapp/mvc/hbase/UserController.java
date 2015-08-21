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

    private static final Log log = LogFactory.getLog(UserController.class);


    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/userInfo.html")
    public String userInfo(ModelMap modelMap){
        try {
            List<User> users = userRepository.findAll();
            modelMap.put("message", users.size());
        } catch (Exception e){
            log.debug(e);
        }

        return "user-info";
    }
}

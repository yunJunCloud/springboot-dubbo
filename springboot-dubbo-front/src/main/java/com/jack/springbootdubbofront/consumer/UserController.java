package com.jack.springbootdubbofront.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jack.bean.User;
import com.jack.interfaces.IUserService;
import com.jack.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Reference
    private IUserService iUserService;

    @RequestMapping("/getUser")
    public String getUser(Model model){
        List<User> userList = iUserService.getUserList();
        StringBuffer sb = new StringBuffer();
        String userName ;
        for (User user: userList) {
            userName = user.getUserName();
            sb.append(userName);
            sb.append("---");
        }
        model.addAttribute("name",sb.toString());
        return "test";
    }

    @RequestMapping( value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody Map<String, Object> params){
        Result result = new Result();
        /*System.out.println(params);*/
        /*String username = (String) params.get("user");
        String pass = (String) params.get("password");*/
       User user1 = iUserService.getUserByKey((Object) params.get("user"));
        if(null!=user1&&user1.getPassWord().equalsIgnoreCase((String) params.get("password"))){
            result.setCode("200");
            result.setMessage("成功");
        }
        return result;
    }
}

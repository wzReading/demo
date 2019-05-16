package com.qf.controller;

import com.qf.entity.User;
import com.qf.service.IUserService;
import com.qf.utils.SendEmail;
import com.qf.utils.VerificationCodeUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @version 1.0
 * @user reading
 * @date 2019/5/16 20:12
 */
@Controller
@RequestMapping("/w")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value="/login")
    public String login(User user, ModelMap map, HttpServletRequest request){
        User user1 = iUserService.login(user.getUsername(), user.getPassword());

        if(user1 != null){
            request.getSession().setAttribute("user", user1);
            map.put("info", "登录成功，欢迎回来！");

            return "info";
        }else{
            return "index";
        }
    }

    @RequestMapping("/forgetPasswd")
    public String forgetPasswd(){
        return  "forgetPasswd";
    }

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    @RequestMapping(value = "/getVerificationCode",produces = "text/html;charset=utf8")
    @ResponseBody
    public String getVerificationCode(String toEmail,Integer typeCode,HttpServletRequest request){
        String code = "";
        if(typeCode == 1){
           code = VerificationCodeUtils.createVerificationCode();
            request.getSession().setAttribute("vcode", code);
        }

        try {
            SendEmail.send(toEmail, code, typeCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",code);

        return  jsonObject.toString();
    }

    @RequestMapping("/toUpdatePassword")
    public String toUpdatePassword(String email, Model model){
        model.addAttribute("email",email);
        return "toUpdatePassword";
    }

    @RequestMapping("updatePassword")
    public String updatePassword(String email,String password){
        iUserService.updatePasswordByName(email, password);
        return "index";
    }

    @RequestMapping("/register")
    public String register(User user,Model model){
        model.addAttribute("info","注册成功");
        iUserService.register(user);

        return "index";
    }


}

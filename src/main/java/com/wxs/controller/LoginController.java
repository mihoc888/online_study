package com.wxs.controller;

import com.wxs.controller.common.GlobalConstant;
import com.wxs.controller.common.SHA1Utils;
import com.wxs.po.Student;
import com.wxs.po.Userlogin;
import com.wxs.service.StudentService;
import com.wxs.service.UserloginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * Author: jitwxs
 * Date: 2017-10-10
 * 登陆Controller层
 */
@Controller
public class LoginController {
    @Resource(name = "userloginServiceImpl")
    private UserloginService userloginService;

    @Resource(name = "studentServiceImpl")
    private StudentService studentService;
    /**
     * 登录跳转
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String loginUI() throws Exception {
        return "../../login";
    }

    /**
     * 登录表单处理
     *
     * @param userlogin Userlogin对象
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public String login(Userlogin userlogin) throws Exception {

        //Shiro实现登录
        UsernamePasswordToken token = new UsernamePasswordToken(userlogin.getName(),
                userlogin.getPassword());
        Subject subject = SecurityUtils.getSubject();

        //如果获取不到用户名就是登录失败，但登录失败的话，会直接抛出异常
        subject.login(token);

        //所有用户均重定向对应的展示课程页面
        if (subject.hasRole(GlobalConstant.ROle_Type.ADMIN.getName())) {
            return "redirect:/admin/showCourse";
        } else if (subject.hasRole(GlobalConstant.ROle_Type.TEACHER.getName())) {
            return "redirect:/teacher/showCourse";
        } else if (subject.hasRole(GlobalConstant.ROle_Type.STUDENT.getName())) {
            return "redirect:/student/showCourse";
        }

        return "../../login";
    }

    @RequestMapping("/registerPage")
    public String registerPage() {
        return "register";
    }

    @RequestMapping("/register")
    public String register(@RequestParam("name")String name ,Model model,
                           @RequestParam("password")String password){
        try {
            // 密码加密
            String entryptPassword = SHA1Utils.entryptPassword(password);
            // 添加登录用户
            Userlogin userlogin = new Userlogin();
            userlogin.setName(name);
            userlogin.setPassword(entryptPassword);
            userlogin.setRole(3);
            userloginService.save(userlogin);
            // 添加Student表 --> 一开始是 学生
            Student student = new Student();
            student.setName(name);
            student.setId(userlogin.getId());
            student.setBalance(100000);
            studentService.save(student);
            // 没有开启事务
        } catch (Exception e) {
            return "error";
        }
        model.addAttribute("message", "注册成功");
        // 注册成功 , 重定向到登录页面
        return "redirect:/login";
    }
}

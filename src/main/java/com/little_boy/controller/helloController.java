package com.little_boy.controller;

import com.little_boy.domian.user;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/hello")
public class helloController {
    @RequestMapping("/hello_ok")
    public String hello(){
        System.out.println("hello");
        return "ok_hello";
    }

    /*返回字符串*/
    @RequestMapping("/testString")
    public String testString(Model model){
        System.out.println("testString");
        /*模拟从数据库中查询除user对象*/

        user user=new user();
        user.setUsername("丫丫");
        user.setAge(21);
        user.setPassword("123");

        model.addAttribute("user",user);

        return "ok_hello";
    }

    /*
    * 是void
    * 请求转发一次请求，不用编写项目名称
    * */
    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("testVoid");
        /*模拟从数据库中查询除user对象*/
        //编写请求转发
//        request.getRequestDispatcher("/WEB-INF/pages/ok_hello.jsp").forward(request,response);

        //重定向
        response.sendRedirect(request.getContextPath()+"/index.jsp");

//        //设置中文乱码
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;charset=UTF-8");
//
//        //直接会进行响应
//        response.getWriter().print("你好");


        return;
    }


    /*返回ModelAndView*/
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        //创建tModelAndView
        ModelAndView mv=new ModelAndView();
        System.out.println("testString");
        /*模拟从数据库中查询除user对象*/

        user user=new user();
        user.setUsername("丫丫");
        user.setAge(21);
        user.setPassword("123");

        //把user对象储存到mv对象中，也会把user对象存入request对象
        mv.addObject("user",user);

        //跳转到哪个页面
        mv.setViewName("ok_hello");
        return mv;
    }

    /*
    * 使用关键字的方式进行转发或者重定向
    * */
    @RequestMapping("/testForwardOrRedirect")
    public String testForwardOrRedirect(){
        System.out.println("testForwardOrRedirect");

//        return "forward:/WEB-INF/pages/ok_hello.jsp";  //转发
        return "redirect:/index.jsp";   //重定向
    }

    /*
    * 模拟异步请求响应
    * */
    @RequestMapping("/textAjax")
    public @ResponseBody user textAjax(@RequestBody user user){
        System.out.println("textAjax");
        System.out.println(user);

        //作响应，模拟查询数据库
        user.setUsername("哈哈");
        user.setAge(40);
        //作响应

        return user;
    }


}

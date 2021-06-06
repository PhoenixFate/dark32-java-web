package com.phoenix.shop.web.servlet;

import com.phoenix.shop.domain.User;
import com.phoenix.shop.service.UserService;
import com.phoenix.shop.utils.CommonsUtils;
import com.phoenix.shop.utils.MailUtils;
import com.phoenix.shop.utils.MyBeanUtils;
import com.phoenix.shop.web.base.BaseServlet;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Map;

public class UserServlet extends BaseServlet {

    private UserService userService=new UserService();

    public void findAll(HttpServletRequest request, HttpServletResponse response){
        System.out.println("findAll");
    }

    public String registerUI(HttpServletRequest request, HttpServletResponse response){
        return "/jsp/register.jsp";
    }

    public String loginUI(HttpServletRequest request, HttpServletResponse response){
        return "/jsp/login.jsp";
    }

    public void checkUsername(HttpServletRequest request, HttpServletResponse response){
        String username=request.getParameter("username");
        try {
            Integer integer = userService.checkUsername(username);
            System.out.println("checkUserName: -------------------: "+integer);
            if(integer.equals(0)){
                response.getWriter().println("0");
            }else {
                response.getWriter().println("1");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * json格式
     * @param request
     * @param response
     */
    public void checkUsername2(HttpServletRequest request, HttpServletResponse response){
        String username=request.getParameter("username");
        try {
            Integer integer = userService.checkUsername(username);

            System.out.println("checkUserName: -------------------: "+integer);
            String json=null;
            if(integer.equals(0)){
                json="{\"isExist\":true}";
            }else {
                json="{\"isExist\":false}";
            }
            response.getWriter().write(json);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断验证玛是否正确
        HttpSession session = request.getSession();
        String sessionCheckCode =(String) session.getAttribute("sessionCheckCode");
        String checkCode = request.getParameter("checkCode");
        System.out.println("sessionCheckCode: "+sessionCheckCode);
        System.out.println("checkCode: "+checkCode);
        if(sessionCheckCode!=null && checkCode!=null){
            if(!sessionCheckCode.equals(checkCode)){
                // 验证码错误
                request.setAttribute("registerInfo","验证码错误");
                request.getRequestDispatcher("/jsp/register.jsp").forward(request,response);
                return;
            }
        }else {
            System.out.println("????");
            response.sendRedirect(request.getContextPath()+"/jsp/register.jsp");
            return;
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user=new User();
        MyBeanUtils.populate(user,parameterMap);
        System.out.println(user);
        user.setUid(CommonsUtils.getUUID());
        user.setState(0);
        user.setCode(CommonsUtils.getUUID());
        UserService userService=new UserService();
        try {
            int count=userService.register(user);
            if(count >=1 ){
                // 注册成功
                //
                String content="请点击下方连接进行激活<a href=\"http://localhost:8080"+request.getContextPath()+"/userServlet?method=active&code="+user.getCode()+"\">点击激活</a>";
                MailUtils.sendMail(user.getEmail(),content);
                response.sendRedirect(request.getContextPath()+"/jsp/registerSuccess.jsp");
            }else {
//                req.setAttribute("registerInfo","注册失败");
//                req.getRequestDispatcher(req.getContextPath()+"/register.jsp").forward(req,resp);
                response.sendRedirect(request.getContextPath()+"/jsp/registerFailure.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void active(HttpServletRequest request, HttpServletResponse response) {
        String code=request.getParameter("code");
        UserService userService=new UserService();
        try {
            Integer count = userService.active(code);
            if(count>=1){
                // 激活成功
                response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
            }else {
                // 激活失败
                response.sendRedirect(request.getContextPath()+"/jsp/activeFailure.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session=request.getSession();
        String checkCode=request.getParameter("checkCode");
        System.out.println("checkCode: ----------------------------------------------------"+checkCode);
        String sessionCheckCode = (String)session.getAttribute("sessionCheckCode");
        System.out.println("sessionCheckCode: ------------------------------------------------"+sessionCheckCode);
        if(session.getAttribute("sessionCheckCode")!=null && checkCode!=null){
            // 判断验证码是否正确
            if(!checkCode.equals(sessionCheckCode)){
                // 验证码错误
                request.setAttribute("loginInfo","验证码错误");
                try {
                    request.getRequestDispatcher("/jsp/login.jsp").forward(request,response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
        }
        String username = request.getParameter("username");
        String password=request.getParameter("password");
        UserService userService=new UserService();
        User user=null;
        try {
            user = userService.login(username, password);
            System.out.println(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(user==null){
            //用户名或者密码错误;
            request.setAttribute("loginInfo","用户名或者密码错误");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request,response);
        }else {
            // 存在该账号
            // 判断该账号是否激活
            if(user.getState()==0){
                request.setAttribute("loginInfo","该账号还未激活，请进入邮箱激活");
                request.getRequestDispatcher("/jsp/login.jsp").forward(request,response);
            }else {
                // 登录成功，跳转首页
                // 设置session中的user和cookie
                session.setAttribute("user",user);
                System.out.println("autoLogin:--------------------------: "+request.getParameter("autoLogin"));
                System.out.println("rememberName:-----------------------: "+request.getParameter("rememberName"));
                // 判断是否勾选自动登录
                if(request.getParameter("autoLogin")!=null){
                    // 如果勾选自动登录，在cookie中设置用户名和密码
                    Cookie usernameCookie=new Cookie("cookieUsername", URLEncoder.encode(username,"utf-8") );
                    Cookie passwordCookie=new Cookie("cookiePassword", URLEncoder.encode(password,"utf-8"));
                    // cookie 持久化
                    usernameCookie.setMaxAge(60*60*8);
                    passwordCookie.setMaxAge(60*60*8);
                    // cookie设置路径
                    usernameCookie.setPath(request.getContextPath());
                    passwordCookie.setPath(request.getContextPath());
                    response.addCookie(usernameCookie);
                    response.addCookie(passwordCookie);
                }

                // 判断是否勾选记住用户名
                if(request.getParameter("rememberName")!=null){
                    Cookie rememberNameCookie =new Cookie("rememberName",user.getUsername());
                    rememberNameCookie.setPath(request.getContextPath());
                    rememberNameCookie.setMaxAge(60*60*24*30);
                    response.addCookie(rememberNameCookie);
                }

                // 跳转首页
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            }
        }
    }



    public void logout(HttpServletRequest request, HttpServletResponse response){
        // 移除session和cookie中的user
        request.getSession().removeAttribute("user");

        //cookie 中移除用户名的密码，否则勾选了自动登录会自动登录
        Cookie usernameCookie=new Cookie("cookieUsername","");
        usernameCookie.setMaxAge(0);
        usernameCookie.setPath(request.getContextPath());
        Cookie passwordCookie=new Cookie("cookiePassword","");
        passwordCookie.setMaxAge(0);
        passwordCookie.setPath(request.getContextPath());

        response.addCookie(usernameCookie);
        response.addCookie(passwordCookie);

        try {
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

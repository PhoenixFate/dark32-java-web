package com.phoenix.login;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		System.out.println("sessionId: "+request.getSession().getId());

		//验证码校验
		//获得页面输入的验证
		String checkCode_client = request.getParameter("checkCode");
		//获得生成图片的文字的验证码
		String checkCode_session = (String) request.getSession().getAttribute("sessionCheckCode");
		//比对页面的和生成图片的文字的验证码是否一致
		if(checkCode_session!=null){
			if(!checkCode_session.equals(checkCode_client)){
				request.setAttribute("loginInfo", "您的验证码不正确");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
//				response.sendRedirect(request.getContextPath()+"/login.jsp");
			}else{
				//获得页面的用户名和密码进行数据库的校验
				//......

				request.getRequestDispatcher("index.jsp").forward(request,response);
			}
		}else {
			// session中没有得到验证码
			// 刷新页面
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
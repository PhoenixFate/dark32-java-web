package com.phoenix.api;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 在action中访问 servlet中的原生api
 *
 * 方式二：通过implements ServletRequestAware , ServletResponseAware, ServletContextAware 来获得对象
 */
public class ApiDemo3 extends ActionSupport implements ServletRequestAware , ServletResponseAware, ServletContextAware {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletContext servletContext;

    @Override
    public String execute() throws Exception {
        //原生request
        System.out.println("原生request: "+request);

        //原生response
        System.out.println("原生response: "+response);

        //原生session
        System.out.println("原生session: "+request.getSession());

        //原生servletContext
        System.out.println("原生servletContext: "+servletContext);

        return super.execute();
    }


    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request=httpServletRequest;
    }

    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response=httpServletResponse;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext=servletContext;
    }
}

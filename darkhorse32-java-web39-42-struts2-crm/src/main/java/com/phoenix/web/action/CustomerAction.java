package com.phoenix.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.phoenix.domain.Customer;
import com.phoenix.service.CustomerService;
import com.phoenix.service.impl.CustomerServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

    private CustomerService customerService=new CustomerServiceImpl();

    private Customer customer=new Customer();

    /**
     * old 刚学struts的写法
     * @return
     * @throws Exception
     */
    // public String list() throws Exception {
    //     //1.接受参数
    //     String customerName = ServletActionContext.getRequest().getParameter("customerName");
    //     //2.创建离线查询对象
    //     DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Customer.class);
    //     //3.判断参数拼装条件
    //     if(StringUtils.isNotBlank(customerName)){
    //         detachedCriteria.add(Restrictions.like("customerName","%"+customerName+"%"));
    //     }
    //     //4.调用service将离线对象传递
    //     List<Customer> customerList = customerService.findAllCriteria(detachedCriteria);
    //     //5.将返回对list封装到request，并转发到list.jsp显示
    //     ServletActionContext.getRequest().setAttribute("customerList",customerList);
    //     return "list";
    // }

    /**
     *
     * @return
     * @throws Exception
     */
    public String list() throws Exception {
        //1.接受参数
        String customerName = ServletActionContext.getRequest().getParameter("customerName");
        //2.创建离线查询对象
        DetachedCriteria detachedCriteria=DetachedCriteria.forClass(Customer.class);
        //3.判断参数拼装条件
        if(StringUtils.isNotBlank(customerName)){
            detachedCriteria.add(Restrictions.like("customerName","%"+customerName+"%"));
        }
        //4.调用service将离线对象传递
        List<Customer> customerList = customerService.findAllCriteria(detachedCriteria);
        //5.将返回对list封装到request，并转发到list.jsp显示
        //ServletActionContext.getRequest().setAttribute("customerList",customerList);

        //放到ActionContext中
        ActionContext.getContext().put("customerList",customerList);

        return "list";
    }

    public String add() throws Exception {
        //1.调用service
        customerService.save(customer);
        //2.重定向到列表action方法
        return "toList";
    }


    public Customer getModel() {
        return customer;
    }
}

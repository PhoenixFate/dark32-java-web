package com.phoenix.ognl;

import com.phoenix.bean.User;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class OgnlDemo {

    /**
     * 准备
     */
    @Test
    public void test01(){
        //准备ognlContext
        OgnlContext ognlContext=new OgnlContext();

        //准备Root
        User user=new User();
        user.setName("tome");
        user.setAge(18);
        ognlContext.setRoot(user);


        //准备context
        Map<String,User> context=new HashMap<String, User>();
        context.put("user1",new User("jack",18));
        context.put("user2",new User("rose",22));
        ognlContext.setValues(context);


        //书写ognl
        try {
            Ognl.getValue("",ognlContext,ognlContext.getRoot());
        } catch (OgnlException e) {
            e.printStackTrace();
        }
    }

    /**
     * 基本语法演示
     */
    @Test
    public void test02(){
        //准备ognlContext
        OgnlContext ognlContext=new OgnlContext();

        //准备Root
        User user=new User();
        user.setName("tom");
        user.setAge(18);
        ognlContext.setRoot(user);

        //准备context
        Map<String,User> context=new HashMap<String, User>();
        context.put("user1",new User("jack",18));
        context.put("user2",new User("rose",22));
        ognlContext.setValues(context);


        //书写ognl
        try {
            //取出root中user对象的name书写
            System.out.println(Ognl.getValue("name",ognlContext,ognlContext.getRoot()));
            System.out.println(Ognl.getValue("age",ognlContext,ognlContext.getRoot()));


        } catch (OgnlException e) {
            e.printStackTrace();
        }
    }


    /**
     * 基本语法演示
     */
    @Test
    public void test03(){
        //准备ognlContext
        OgnlContext ognlContext=new OgnlContext();

        //准备Root
        User user=new User();
        user.setName("tom");
        user.setAge(18);
        ognlContext.setRoot(user);

        //准备context
        Map<String,User> context=new HashMap<String, User>();
        context.put("user1",new User("jack",18));
        context.put("user2",new User("rose",22));
        ognlContext.setValues(context);


        //书写ognl
        try {
            //从context中取值
            System.out.println(Ognl.getValue("#user1.name",ognlContext,ognlContext.getRoot()));
            System.out.println(Ognl.getValue("#user1.age",ognlContext,ognlContext.getRoot()));
            System.out.println(Ognl.getValue("#user2.name",ognlContext,ognlContext.getRoot()));
            System.out.println(Ognl.getValue("#user2.age",ognlContext,ognlContext.getRoot()));

        } catch (OgnlException e) {
            e.printStackTrace();
        }
    }



    /**
     * 基本语法演示
     * 为属性赋值
     */
    @Test
    public void test04(){
        //准备ognlContext
        OgnlContext ognlContext=new OgnlContext();

        //准备Root
        User user=new User();
        user.setName("tom");
        user.setAge(18);
        ognlContext.setRoot(user);

        //准备context
        Map<String,User> context=new HashMap<String, User>();
        context.put("user1",new User("jack",18));
        context.put("user2",new User("rose",22));
        ognlContext.setValues(context);

        //书写ognl
        try {
            //为root中的user赋值
            Ognl.getValue("name='jerry'",ognlContext,ognlContext.getRoot());
            System.out.println(Ognl.getValue("name",ognlContext,ognlContext.getRoot()));
            Ognl.getValue("#user1.name='user1'",ognlContext,ognlContext.getRoot());
            System.out.println(Ognl.getValue("#user1.name",ognlContext,ognlContext.getRoot()));
            //先赋值，后取值，类似于逗号表达式
            System.out.println(Ognl.getValue("#user2.name='user2',#user2.name",ognlContext,ognlContext.getRoot()));

        } catch (OgnlException e) {
            e.printStackTrace();
        }
    }



    /**
     * 基本语法演示
     * 调用对象中的方法
     */
    @Test
    public void test05(){
        //准备ognlContext
        OgnlContext ognlContext=new OgnlContext();

        //准备Root
        User user=new User();
        user.setName("tom");
        user.setAge(18);
        ognlContext.setRoot(user);

        //准备context
        Map<String,User> context=new HashMap<String, User>();
        context.put("user1",new User("jack",18));
        context.put("user2",new User("rose",22));
        ognlContext.setValues(context);

        //书写ognl
        try {
            //调用root中user中的方法
            String name=(String)Ognl.getValue("getName()",ognlContext,ognlContext.getRoot());
            System.out.println(name);
            System.out.println(Ognl.getValue("#user1.name='user1',#user1.getName()",ognlContext,ognlContext.getRoot()));
            System.out.println(Ognl.getValue("#user2.getName()",ognlContext,ognlContext.getRoot()));

        } catch (OgnlException e) {
            e.printStackTrace();
        }
    }

    /**
     * 基本语法演示
     * 调用静态方法
     */
    @Test
    public void test06(){
        //准备ognlContext
        OgnlContext ognlContext=new OgnlContext();

        //准备Root
        User user=new User();
        user.setName("tom");
        user.setAge(18);
        ognlContext.setRoot(user);

        //准备context
        Map<String,User> context=new HashMap<String, User>();
        context.put("user1",new User("jack",18));
        context.put("user2",new User("rose",22));
        ognlContext.setValues(context);

        //书写ognl
        try {
            //调用root中user中的方法
            String name=(String)Ognl.getValue("@com.phoenix.utils.MyUtils@echo('hello !')",ognlContext,ognlContext.getRoot());
            System.out.println(name);
            Double pi=(Double)Ognl.getValue("@java.lang.Math@PI",ognlContext,ognlContext.getRoot());
            System.out.println(pi);
        } catch (OgnlException e) {
            e.printStackTrace();
        }
    }


    /**
     * 基本语法演示
     * 创建对象
     */
    @Test
    public void test07(){
        //准备ognlContext
        OgnlContext ognlContext=new OgnlContext();

        //准备Root
        User user=new User();
        user.setName("tom");
        user.setAge(18);
        ognlContext.setRoot(user);

        //准备context
        Map<String,User> context=new HashMap<String, User>();
        context.put("user1",new User("jack",18));
        context.put("user2",new User("rose",22));
        ognlContext.setValues(context);

        //书写ognl
        try {
            //创建list对象
            Integer size=(Integer)Ognl.getValue("{'tom','jerry','jack','rose'}.size",ognlContext,ognlContext.getRoot());
            System.out.println(size);
            String name=(String)Ognl.getValue("{'tom','jerry','jack','rose'}[0]",ognlContext,ognlContext.getRoot());
            System.out.println(name);
            String name2=(String)Ognl.getValue("{'tom','jerry','jack','rose'}.get(1)",ognlContext,ognlContext.getRoot());
            System.out.println(name2);

            //创建map对象
            size=(Integer)Ognl.getValue("#{'name':'jerry','age':18}.size()",ognlContext,ognlContext.getRoot());
            System.out.println(size);
            String name3=(String)Ognl.getValue("#{'name':'jerry','age':18}['name']",ognlContext,ognlContext.getRoot());
            System.out.println(name3);

        } catch(OgnlException e) {
            e.printStackTrace();
        }
    }
}

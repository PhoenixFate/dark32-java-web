package com.phoenix.web;

import com.phoenix.exception.CustomerException;
import com.phoenix.pojo.Items;
import com.phoenix.service.ItemService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 不写 method = Req  uestMethod.POST 表示任意方法都行
     * 请求返回值：
     * 1. ModelAndView 无敌，任何情况下有效，带着数据返回路径
     * 2. String 返回视图路径，(官方推荐，符合解耦思想 数据和视图分离)
     * 3. void  通过次跳转页面request.getRequestDispatcher("itemList").forward(request, response);
     * 4. 其他类别  ajax异步请求时使用次方式获取数据
     */
    @RequestMapping("/list.action")
    public ModelAndView list() {
        List<Items> list = itemService.getAllList();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("itemList");
        modelAndView.addObject("itemList", list);
        return modelAndView;
    }

    @RequestMapping("/list2.action")
    public String list2(Model model) {
        List<Items> list = itemService.getAllList();
        model.addAttribute("itemList", list);//等价于request.setAttribute()
        return "itemList";
    }

    @RequestMapping("/list3.action")
    public void list3(HttpServletRequest request, HttpServletResponse response, Model model) throws ServletException, IOException {
        List<Items> list = itemService.getAllList();
        model.addAttribute("itemList", list);//等价于request.setAttribute()
        //1. request.getRequestDispatcher("itemList").forward 重定向
        request.getRequestDispatcher("itemList").forward(request, response);
        //2. 或者 response.sendRedirect("itemList");

        //或者响应json字符串
        // 3、可以通过response指定响应结果，例如响应json数据如下：
        // response.getWriter().print("{\"abc\":123}");
    }

    @RequestMapping("/toEdit.action")
    public ModelAndView toEdit(@RequestParam(required = true, value = "id") Integer id, HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) {
        Items item = itemService.getInfo(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editItem");
        modelAndView.addObject("item", item);
        return modelAndView;
    }

    @RequestMapping(value = "/postEdit.action", method = RequestMethod.POST)
    public ModelAndView postEdit(Items items, MultipartFile pictureFile) throws IOException {
        //保存图片
        String ext = FilenameUtils.getExtension(pictureFile.getOriginalFilename());
        String pictureName = UUID.randomUUID().toString() + "." + ext;
        pictureFile.transferTo(new File("/Users/phoenix/Desktop/picture/" + pictureName));
        items.setPic(pictureName);
        itemService.updateItem(items);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /**
     * 提交修改后返回列表页面
     * 通过redirect: 来重定向
     *
     * @param items
     * @return
     */
    @RequestMapping(value = "/postEdit2.action", method = RequestMethod.POST)
    public String postEdit2(Items items) {
        itemService.updateItem(items);
        return "redirect:/item/list.action";
    }

    /**
     * 内部转发forward
     * 通过: forward:来内部转发
     *
     * @param items
     * @return
     */
    @RequestMapping(value = "/postEdit3.action", method = RequestMethod.POST)
    public String postEdit3(Items items) {
        itemService.updateItem(items);
        return "forward:/item/list.action";
    }

    //删除多个
    @RequestMapping(value = "/delete.action", method = {RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE})
    public ModelAndView delete(Integer[] ids) {
        System.out.println(Arrays.toString(ids));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("success");
        return modelAndView;
    }

    /**
     * 异常
     *
     * @return
     */
    @RequestMapping("/toError.action")
    public void toError() {
        Integer count = 1 / 0;
    }

    /**
     * 自定义异常
     *
     * @return
     */
    @RequestMapping("/toError2.action")
    public void toError2() throws CustomerException {
        throw new CustomerException("列表为kong", "200322");
    }

    /**
     * json交互
     *
     * @RequestBody 表示发送的请求的contextType是 application/json
     * @ResponseBody 把返回的数据转成json字符串
     */
    @RequestMapping(value = "/json.action", method = RequestMethod.POST)
    public @ResponseBody
    Items getJson(@RequestBody Items items) {

        return items;
    }

    //RestFul风格的开发
    @RequestMapping(value = "/itemEdit/{id}.action")
    public ModelAndView toEdit1(@PathVariable Integer id,
                                HttpServletRequest request, HttpServletResponse response
            , HttpSession session, Model model) {

        //Servlet时代开发
//		String id = request.getParameter("id");

        //查询一个商品
//		Items items = itemService.selectItemsById(Integer.parseInt(id));
        Items items = itemService.getInfo(id);
        ModelAndView mav = new ModelAndView();
        //数据
        mav.addObject("item", items);
        mav.setViewName("editItem");
        return mav;

    }

    //去登陆的页面
    @RequestMapping(value = "/toLogin.action", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login.action", method = RequestMethod.POST)
    public String login(String username
            , HttpSession httpSession) {
        httpSession.setAttribute("USER_SESSION", username);
        return "redirect:/item/list.action";
    }

}

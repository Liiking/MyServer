package com.qwy.controller;

import com.qwy.model.BlogEntity;
import com.qwy.model.UserEntity;
import com.qwy.repository.BlogRepository;
import com.qwy.repository.UserRepository;
import com.qwy.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by qwy on 17/6/12.
 * 博客相关请求控制类
 */
@Controller
public class BlogController {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    // 查看所有博文
    @RequestMapping(value = "/admin/blogs", method = RequestMethod.GET)
    public String showBlogs(ModelMap modelMap) {
        List<BlogEntity> blogs = blogRepository.findAll();
        modelMap.addAttribute("blogList", blogs);
        return "admin/blogs";
    }

    @RequestMapping(value = "/admin/blogs/add", method = RequestMethod.GET)
    public String addBlog(ModelMap modelMap){
        List<UserEntity> userList = userRepository.findAll();
        modelMap.addAttribute("userList", userList);
        return "admin/addBlog";
    }

    // 添加博文，post请求，重定向为查看博客页面
    @RequestMapping(value = "/admin/blogs/addP", method = RequestMethod.POST)
    public String addBlogPost(@ModelAttribute("blogA") BlogEntity blogEntity) {
        Utility.log("title: " + blogEntity.getTitle() + ", content: " + blogEntity.getContent());
        int len = blogRepository.findAll() == null ? 0 : blogRepository.findAll().size();
        blogEntity.setId(10000001 + len);
        blogRepository.saveAndFlush(blogEntity);
        return "redirect:/admin/blogs";
    }

    // 查看博客详情
    @RequestMapping(value = "/admin/blogs/show/{id}", method = RequestMethod.GET)
    public String showBlogDetail(@PathVariable("id") int id, ModelMap modelMap) {
        BlogEntity blogEntity = blogRepository.findOne(id);
        modelMap.addAttribute("blog", blogEntity);
        return "admin/blogDetail";
    }

    // 修改博客内容
    @RequestMapping(value = "/admin/blogs/update/{id}")
    public String modifyBlogById(@PathVariable("id") int id, ModelMap modelMap) {
        BlogEntity blog = blogRepository.findOne(id);
        List<UserEntity> userList = userRepository.findAll();
        modelMap.addAttribute("blog", blog);
        modelMap.addAttribute("userList", userList);
        return "admin/updateBlog";
    }

    // 修改博客内容
    @RequestMapping(value = "/admin/blogs/updateB", method = RequestMethod.POST)
    public String modifyBlogById(@ModelAttribute("blogU") BlogEntity blogEntity) {
        blogRepository.updateBlog(blogEntity.getTitle(), blogEntity.getContent(), //blogEntity.getUserByUserId().getId(),
                blogEntity.getPubDate(), blogEntity.getId());
        blogRepository.flush();
        return "redirect:/admin/blogs";
    }

    // 删除指定博客
    @RequestMapping(value = "/admin/blogs/delete/{id}")
    public String deleteBlogById(@PathVariable("id") int id){
        blogRepository.delete(id);
        blogRepository.flush();
        return "redirect:/admin/blogs";
    }

}

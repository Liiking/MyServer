package com.qwy.controller;

import com.qwy.model.UserEntity;
import com.qwy.repository.UserRepository;
import com.qwy.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.misc.BASE64Encoder;
import sun.security.provider.MD5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by qwy on 17/6/5.
 * 处理请求的Controller类
 */
@Controller
public class MainController {

    // 自动装配数据库接口，不需要再写原始的Connection来操作数据库
    @Autowired
    UserRepository userRepository;

    /**
     * 拦截请求进入首页
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    /**
     * 查询数据库中所有用户
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String getUsers(ModelMap modelMap){// modelMap：用于将controller方法里面的参数传递给所需的jsp页面，以进行相关显示
        // 查询user表中所有记录
        List<UserEntity> userList = userRepository.findAll();

        // 将所有记录传递给要返回的jsp页面，放在userList当中
        modelMap.addAttribute("userList", userList);

        // 返回pages目录下的admin/users.jsp页面
        return "admin/users";
    }

    /**
     * 转到添加用户页（注册页）
     * @return
     */
    @RequestMapping(value = "/admin/users/add", method = RequestMethod.GET)
    public String addUser() {
        // 转到admin/addUser.jsp页面
        return "admin/addUser";
    }

    /**
     * 转到登录页
     * @return
     */
    @RequestMapping(value = "/admin/users/login", method = RequestMethod.GET)
    public String login() {
        return "admin/login";
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = "/admin/users/logout", method = RequestMethod.GET)
    public String logout(ModelMap modelMap) {
        modelMap.addAttribute("user", null);
        return "index";
    }

    /**
     * 登录逻辑，查询数据库中是否存在指定用户
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/admin/users/findUser", method = RequestMethod.POST)
    public String findUserPost(@ModelAttribute("userF") UserEntity userEntity, ModelMap modelMap) {

        UserEntity user = null;

        List<UserEntity> userList = userRepository.findAll();
        for(UserEntity u : userList){
            String pwd = Utility.getMD5String(userEntity.getPassword());
            if(u.getUsername().equals(userEntity.getUsername()) && u.getPassword().equals(pwd)){
                // 用户名密码都正确
                user = u;
                break;
            }
        }

        if(user == null){
            return "error/user404";
        }
        modelMap.addAttribute("user", user);
        return "index";
    }

    /**
     * 向数据库中添加一个用户
     * @param userEntity
     * @return
     */
    @RequestMapping(value = "/admin/users/addUser", method = RequestMethod.POST)
    public String addUserPost(@ModelAttribute("userInfo") UserEntity userEntity) {
        // post请求传递过来的是一个UserEntity对象，里面包含了该用户的信息
        // 通过@ModelAttribute()注解可以获取传递过来的'user'，并创建这个对象

        Utility.log("addUserPost() userEntity " + userEntity);
        List<UserEntity> userList = userRepository.findAll();
        for(UserEntity u : userList){
            if(u.getUsername().equals(userEntity.getUsername())){
                // 用户名已存在
                Utility.log("用户名 " + u.getUsername() + " 已存在");
                return "/admin/addUser";
            }
        }

        userEntity.setId(userList.size() + 100001);// 手动设置用户ID
        userEntity.setPassword(Utility.getMD5String(userEntity.getPassword()));
        // 数据库中添加一个用户，该步暂时不会刷新缓存
//        userRepository.save(userEntity);

        // 数据库中添加一个用户，并立即刷新缓存
        userRepository.saveAndFlush(userEntity);

        // 重定向到用户管理页面
        return "redirect:/admin/users";
    }

    // 查看用户详情
    // @PathVariable可以收集url中的变量，需匹配的变量用{}括起来
    // 例如：访问 localhost:8080/admin/users/show/1 ，将匹配 id = 1
    @RequestMapping(value = "/admin/users/show/{id}", method = RequestMethod.GET)
    public String showUser(@PathVariable("id") Integer userId, ModelMap modelMap) {

        // 找到userId所表示的用户
        UserEntity userEntity = userRepository.findOne(userId);

        // 传递给请求页面
        modelMap.addAttribute("user", userEntity);
        return "admin/userDetail";
    }

    // 更新用户信息 页面
    @RequestMapping(value = "/admin/users/update/{id}", method = RequestMethod.GET)
    public String updateUser(@PathVariable("id") Integer userId, ModelMap modelMap) {

        // 找到userId所表示的用户
        UserEntity userEntity = userRepository.findOne(userId);

        // 传递给请求页面
        modelMap.addAttribute("user", userEntity);
        return "admin/updateUser";
    }

    // 更新用户信息 操作
    @RequestMapping(value = "/admin/users/updateU", method = RequestMethod.POST)
    public String updateUserPost(@ModelAttribute("userP") UserEntity user) {

        // 更新用户信息
        userRepository.updateUser(user.getUsername(), user.getPassword(), user.getId());
        userRepository.flush(); // 刷新缓冲区
        return "redirect:/admin/users";
    }

    // 删除用户
    @RequestMapping(value = "/admin/users/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Integer userId) {

        // 删除id为userId的用户
        userRepository.delete(userId);
        // 立即刷新
        userRepository.flush();
        return "redirect:/admin/users";
    }
}

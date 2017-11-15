package org.sang.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.sang.model.User;
import org.sang.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	
	@Resource
    private UserService userService;
	
	@RequestMapping("/sang/login")
    public String slogin() {
        return "admin/login";
    }
	
	@RequestMapping(method = RequestMethod.GET, value = "/sang/dologin")
    public String sdoLogin(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("user") == null) {
            return "admin/login";
        }
        return "redirect:/sang";
    }

    @RequestMapping(value = "/sang/dologin", method = RequestMethod.POST)
    public String sdoLogin(HttpServletRequest request, User user, Model model) {
        System.out.println("user.getUsername():" + user.getUsername() + ";user.getPassword():" + user.getPassword());
        User user1 = userService.loginUser(user.getUsername(),DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        if (user1 != null) {
        		request.getSession().setAttribute("user", user1);
                model.addAttribute("user", user1);
                return "redirect:/sang";
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "admin/login";
        }
    }
    @RequestMapping("/sang/logout")
    public String slogout(HttpServletRequest request) {
    	request.getSession().invalidate();
        return "admin/login";
    }
    
	
	 @RequestMapping(value="/views/register",method = RequestMethod.GET)
	 public String vregister() {
	     return "views/register";
	 }
	 
	 @RequestMapping(value = "/views/doregister", method = RequestMethod.POST)
	 public String vdoregister(HttpServletRequest request, User user, Model model) {
		 if (userService.checkUsername(user.getUsername())) {
			 user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
			 userService.saveUser(user);
		     return "views/login";
	        } else {
	            model.addAttribute("error", "邮箱已注册，请用不一样的邮箱进行注册");
	            return "views/register";
	        }
	 }
	 
	 @RequestMapping(value="/views/login",method = RequestMethod.GET)
	 public String vlogin() {
	     return "views/login";
	 }
	 
	 @RequestMapping(method = RequestMethod.GET, value = "/views/dologin")
	    public String vdoLogin(HttpServletRequest request, Model model) {
	        if (request.getSession().getAttribute("user") == null) {
	            return "views/login";
	        }
	        return "views/index";
	    }
	 
	 @RequestMapping(value = "/views/dologin", method = RequestMethod.POST)
	 public String vdologin(HttpServletRequest request, User user, Model model) {
		 User user1 = userService.loginUser(user.getUsername(),DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
	        if (user1 != null) {
	        		request.getSession().setAttribute("user", user1);
	                model.addAttribute("user", user1);
	                return "views/index";
	        } else {
	            model.addAttribute("error", "用户名或密码错误");
	            return "views/login";
	        }
	 }
	 
	 @RequestMapping("/views/logout")
	 public String vlogout(HttpServletRequest request) {
		 request.getSession().invalidate();
	     return "views/login";
	 }   
	 
	 @RequestMapping( value="/sang/updatePwd",method = RequestMethod.GET)
	 public String updatePwd(HttpServletRequest request) {
		 return "views/updatePwd";
	 }
	 
	 @RequestMapping( value="/sang/doupdatePwd",method = RequestMethod.GET)
	 public String doupdatePwd(HttpServletRequest request) {
		 return "views/updatePwd";
	 }
	 
	 @RequestMapping(value = "/sang/doupdatePwd", method = RequestMethod.POST)
	 public String doupdatePwd(HttpServletRequest request, User user, Model model) {
		 String oldPassword = request.getParameter("oldPassword");
	     String newpassword = request.getParameter("newpassword");
		 String exitPassword = request.getParameter("exitPassword");
		 if(DigestUtils.md5DigestAsHex(oldPassword.getBytes()).equals(user.getPassword()) ){
			 if(newpassword.equals(exitPassword) ){
				 user.setPassword(DigestUtils.md5DigestAsHex(newpassword.getBytes()));
				 userService.updateUser(user);
				 request.getSession().removeAttribute("user");
				 request.getSession().setAttribute("user", user);
				 return "redirect:/sang"; 
			 }else{
				 model.addAttribute("error", "新密码与确认密码不一致！");
				 return "views/updatePwd";
			 }
		 }else{
			 model.addAttribute("error", "原密码与登录密码不一致！");
			 return "views/updatePwd";
		 }
	 }
	 
}

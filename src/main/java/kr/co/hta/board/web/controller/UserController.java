package kr.co.hta.board.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.hta.board.service.UserService;
import kr.co.hta.board.vo.User;
import kr.co.hta.board.web.form.UserRegisterForm;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userservice;
	
	@RequestMapping("/form.do")
	public String home() {
		return "user/form.jsp";
	}
	@RequestMapping("/register.do")
	public String register(UserRegisterForm userform) {
		
		User user = new User();
		BeanUtils.copyProperties(userform, user);
		
		userservice.addNewUser(user);

		return "user/success.jsp"; 
	}
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String login() {
		return "user/loginform.jsp";
	}
	
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(String id,String pwd,HttpSession session) {
			
		User user = userservice.getUserDetail(id);
		if(user == null) {
		   return "redirect:/user/login.do?err=fail";	
		}
		if (!user.getPwd().equals(pwd)) {
			return "redirect:/user/login.do?err=fail";	
		}
		
		session.setAttribute("LOGIN_USER", user);
		
		return "redirect:/home.do";
	}
	
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
	
		return "redirect:/home.do";
	}
}

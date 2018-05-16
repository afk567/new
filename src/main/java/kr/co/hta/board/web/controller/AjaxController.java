package kr.co.hta.board.web.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.hta.board.service.BookService;
import kr.co.hta.board.vo.Book;
import kr.co.hta.board.vo.Criteria;
import kr.co.hta.board.vo.User;

@Controller
@RequestMapping("/ajax")
public class AjaxController {
	
	@Autowired
	private BookService bookService;
	
/*
	@RequestMapping("/user.do")
	public @ResponseBody User getUser() {
		User user = null;
		
		return user;
	}
	
	@RequestMapping("/user3.do")
	public @ResponseBody Map<String,Object> getUser3() {
		Map<String,Object> map = new HashMap<>();
		
		User user = new User();

		user.setId("hong");
		user.setPwd("zxcv1234");
		user.setName("홍");
		
		if(user != null) {
		   map.put("items", Arrays.asList(user));
		   map.put("success", true);
		} else {
			map.put("success",false);
		}
		
		return map;
	} 
	
	@RequestMapping("/user2.do")
	public ResponseEntity<User> getUser2() {
			/* 응답데이터가 있는 경우 
			User user = new User();
			user.setId("hong");
			user.setPwd("zxcv1234");
			user.setName("홍");
			return ResponseEntity<User>(user,HttpStatus.OK); 
			
			
		//응답데이터가 있는 경우 
			return new ResponseEntity<User>(user,HttpStatus.NO_CONTENT);
		
	}
*/
	@RequestMapping("/search.do")
	public @ResponseBody Map<String, Object> search(Criteria c) {
		Map<String, Object> map = new HashMap<>();
		
		List<Book> books = bookService.searchBooks(c);
		if (books.isEmpty()) {
			map.put("success", false);
		} else {
			map.put("success", true);
			map.put("items", books);
		}
		
		return map;
	}
	
	
}

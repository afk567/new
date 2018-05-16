package kr.co.hta.board.web.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.hta.board.annotation.LoginUser;
import kr.co.hta.board.service.BoardService;
import kr.co.hta.board.vo.Board;
import kr.co.hta.board.vo.User;
import kr.co.hta.board.web.form.BoardForm;
import kr.co.hta.board.web.view.DownloadView;

@Controller
@RequestMapping("/board")
public class BoardController {

	private String directory = "c:/upload/formfile";
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private DownloadView downloadView;
	
	@RequestMapping("/list.do")
	public String list(Model model) {
		List<Board> boards = boardService.getAllBoards();
		model.addAttribute("boards",boards);
		
		return "board/list.jsp";
	}

	@RequestMapping("/detail.do")
	public String detail(@RequestParam("no") int boardNo,Model model) {
		model.addAttribute("board",boardService.getBoardByNo(boardNo));
		
		return "board/detail.jsp";
	}
	
	
	@RequestMapping("/del.do")
	public String delete(@RequestParam("no") int boardNo,User user) {
		boardService.deleteBoard(boardNo,user.getId());
		
		return "redirect:/board/list.do";
	}
	
	@RequestMapping("/form.do")
	public String form() {
		return "board/form.jsp";
	}
	
	@RequestMapping("/add.do")
	public String add(BoardForm boardForm,@LoginUser User user) throws Exception{
		
		
		Board board = new Board();
		board.setTitle(boardForm.getTitle());
		board.setContentes(boardForm.getContents());
		board.setNick(user.getId());
		MultipartFile upfile = boardForm.getUpfile();
		if(!upfile.isEmpty()) {
			String filename = upfile.getOriginalFilename();
			board.setFilename(filename);
			
			FileCopyUtils.copy(upfile.getBytes(), new File(directory,filename));
		}
		boardService.addBoard(board);
		
		return "redirect:/board/list.do";
	}
	
	@RequestMapping("/down.do")
	public ModelAndView download(@RequestParam("no") int boardNo) {
		ModelAndView mav = new ModelAndView();
		
		Board board = boardService.getBoardByNo(boardNo);
		mav.addObject("directory",directory);
		mav.addObject("filename",board.getFilename());
		mav.setView(downloadView);
		
		return mav;
	}
}

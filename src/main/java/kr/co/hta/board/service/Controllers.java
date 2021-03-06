package kr.co.hta.board.service;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import kr.co.hta.board.web.controller.BoardController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/META-INF/spring/test-app-context.xml")
@Transactional
public class Controllers {

	@Autowired
	BoardController boardController;
	@Autowired
	BoardService boardService;
	
	MockMvc mockMvc = null;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
	}
	@Before
	public void setUp1() {
		mockMvc = MockMvcBuilders.standaloneSetup(boardService).build();
	}
	@Test
	public void testBoardController() {
		assertThat(boardController,notNullValue());
	}
	@Test
	public void testBoardControllers() {
		assertThat(boardService,notNullValue());
	}
	@Test
	public void testList() throws Exception {
		mockMvc.perform(get("/board/list.do"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(view().name("board/list.jsp"))
				.andExpect(model().attributeExists("boards"));
				
	}
	
	public void testDetail() throws Exception {
		mockMvc.perform(get("/board/detail.co").param("no","81"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(view().name("board/detail.jsp"))
				.andExpect(model().attributeExists("board"))
				.andExpect(model().attribute("board", notNullValue()));
	}
}

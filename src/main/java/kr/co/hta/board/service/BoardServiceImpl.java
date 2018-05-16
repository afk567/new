package kr.co.hta.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.hta.board.dao.BoardDao;
import kr.co.hta.board.dao.UserDao;
import kr.co.hta.board.exception.SimpleBoardException;
import kr.co.hta.board.vo.Board;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDao boardDao;
	@Autowired
	private UserDao userDao;
	
	
	@Override
	public List<Board> getAllBoards() {
		return boardDao.getAllBoards();
	}

	@Override
	public void addBoard(Board board) {
		boardDao.addBoard(board);
		// 새 게시글 등록시 포인트 5점 부여
		// User user = userDao.getUserById*board.getNick());
		// user.setPoint(user.getpoint() + 5);
		// userDao.updateUser(user);
	}

	@Override
	public Board getBoardByNo(int boardNo) {
		return boardDao.getBoardByNo(boardNo);
	}

	@Override
	public void deleteBoard(int boardNo, String userId) {
		Board board = boardDao.getBoardByNo(boardNo);
		if (!board.getNick().equals(userId)) {
			throw new SimpleBoardException("본인이 작성한 글만 삭제할 수 있습니다");
		}
		boardDao.deleteBoardByNo(boardNo);
	}

}

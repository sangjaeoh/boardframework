package com.kitri.admin.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitri.admin.board.dao.BoardAdminDao;
import com.kitri.admin.board.model.BoardListDto;
import com.kitri.admin.board.model.BoardTypeDto;
import com.kitri.admin.board.model.CategoryDto;

@Service
public class BoardAdminServiceImpl implements BoardAdminService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<BoardListDto> getBoardMenu() {			
		return sqlSession.getMapper(BoardAdminDao.class).getBoardMenu();
	}

	@Override
	public List<CategoryDto> getCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void makeCategory(String cname) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BoardTypeDto> getBoardType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void makeBoard(BoardListDto boardListDto) {
		// TODO Auto-generated method stub

	}

}

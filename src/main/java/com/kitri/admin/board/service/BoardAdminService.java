package com.kitri.admin.board.service;

import java.util.List;

import com.kitri.admin.board.model.*;

public interface BoardAdminService {

	List<BoardListDto> getBoardMenu();
	List<CategoryDto> getCategory();
	void makeCategory(String cname);
	List<BoardTypeDto> getBoardType();
	void makeBoard(BoardListDto boardListDto);
	
}

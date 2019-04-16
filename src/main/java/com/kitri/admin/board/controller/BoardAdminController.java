package com.kitri.admin.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kitri.admin.board.model.BoardListDto;
import com.kitri.admin.board.service.BoardAdminService;

@Controller
@RequestMapping("/boardadmin")
public class BoardAdminController {
	
	@Autowired
	private BoardAdminService boardAdminService;
	
	@RequestMapping("/boardmenu.kitri")
	public String boardMenu(Map<String, Object> map) {
		List<BoardListDto> list = boardAdminService.getBoardMenu();
		map.put("menulist", list);
		return "admin/board/boardmenu";
	}	
}
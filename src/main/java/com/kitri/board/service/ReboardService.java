package com.kitri.board.service;

import java.util.List;
import java.util.Map;

import com.kitri.board.model.MemoDto;
import com.kitri.board.model.ReboardDto;

public interface ReboardService {
	
	int writeArticle(ReboardDto reboardDto);
	int replyArticle(ReboardDto reboardDto);
	List<ReboardDto> listArticle(Map<String, String> map);
	ReboardDto viewArticle(int seq);
	ReboardDto getArticle(int seq);
	void modifyArticle(ReboardDto reboardDto);
	void deleteArticle(ReboardDto reboardDto);
	
}

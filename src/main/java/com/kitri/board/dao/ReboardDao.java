package com.kitri.board.dao;

import java.util.List;
import java.util.Map;

import com.kitri.board.model.MemoDto;
import com.kitri.board.model.ReboardDto;

public interface ReboardDao {

	int writeArticle(ReboardDto reboardDto);
	void updateStep(ReboardDto reboardDto);
	void replyArticle(ReboardDto reboardDto);
	void updateReply(int pseq);
	List<ReboardDto> listArticle(Map<String, String> map);
	ReboardDto viewArticle(int seq);

	int modifyArticle(ReboardDto reboardDto);
	
	void deleteMemo(MemoDto memoDto);
	void deleteArticle(ReboardDto reboardDto);
}

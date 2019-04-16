package com.kitri.board.service;

import java.util.List;

import com.kitri.board.model.MemoDto;

public interface MemoService {
	int writeMemo(MemoDto memoDto);
	String listMemo(int seq);
	int modifyMemo(MemoDto memoDto);
	int deleteMemo(int mseq);
}

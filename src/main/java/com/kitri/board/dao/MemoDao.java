package com.kitri.board.dao;

import java.util.List;
import java.util.Map;
import com.kitri.board.model.MemoDto;

public interface MemoDao {
	int writeMemo(MemoDto memoDto);
	List<MemoDto> listMemo(int seq);
	int modifyMemo(MemoDto memoDto);
	int deleteMemo(int mseq);
}
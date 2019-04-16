package com.kitri.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitri.board.dao.MemoDao;
import com.kitri.board.model.MemoDto;

@Service
public class MemoServiceImpl implements MemoService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int writeMemo(MemoDto memoDto) {
		return sqlSession.getMapper(MemoDao.class).writeMemo(memoDto);
	}

	@Override
	public String listMemo(int seq) {
		List<MemoDto> list = sqlSession.getMapper(MemoDao.class).listMemo(seq);
		JSONObject json = new JSONObject();
		JSONArray jarray = new JSONArray();
		for(MemoDto memoDto : list) {
			JSONObject memo = new JSONObject();
			memo.put("mseq", memoDto.getMseq());
			memo.put("seq", memoDto.getSeq());
			memo.put("id", memoDto.getId());
			memo.put("name", memoDto.getName());
			memo.put("mcontent", memoDto.getMcontent());
			memo.put("mtime", memoDto.getMtime());
			
			jarray.put(memo);
		}
		json.put("memolist", jarray);		
		return json.toString();
	}

	@Override
	public int modifyMemo(MemoDto memoDto) {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(MemoDao.class).modifyMemo(memoDto);
	}

	@Override
	public int deleteMemo(int mseq) {
		return sqlSession.getMapper(MemoDao.class).deleteMemo(mseq);
	}

}

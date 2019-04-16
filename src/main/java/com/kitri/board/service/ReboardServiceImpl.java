package com.kitri.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kitri.board.dao.ReboardDao;
import com.kitri.board.model.MemoDto;
import com.kitri.board.model.ReboardDto;
import com.kitri.common.dao.CommonDao;
import com.kitri.util.KitriConstance;

@Service
public class ReboardServiceImpl implements ReboardService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int writeArticle(ReboardDto reboardDto) {
		int seq = sqlSession.getMapper(CommonDao.class).getNextSeq();
		reboardDto.setSeq(seq);
		reboardDto.setRef(seq);
		return sqlSession.getMapper(ReboardDao.class).writeArticle(reboardDto) == 0 ? 0 : seq;
	}

	@Override
	@Transactional
	public int replyArticle(ReboardDto reboardDto) {
		int seq = sqlSession.getMapper(CommonDao.class).getNextSeq();
		reboardDto.setSeq(seq);
		ReboardDao reboardDao = sqlSession.getMapper(ReboardDao.class);
		reboardDao.updateStep(reboardDto);
		reboardDao.replyArticle(reboardDto);
		reboardDao.updateReply(reboardDto.getPseq());
		return seq;
	}

	@Override
	public List<ReboardDto> listArticle(Map<String, String> map) {
		int pg = Integer.parseInt(map.get("pg"));
		int end = pg * KitriConstance.BOARD_LIST_SIZE;
		int start = end - KitriConstance.BOARD_LIST_SIZE;
		map.put("start", start+"");
		map.put("end", end+"");
		return sqlSession.getMapper(ReboardDao.class).listArticle(map);
	}

	@Override
	public ReboardDto viewArticle(int seq) {
		sqlSession.getMapper(CommonDao.class).updateHit(seq);
		ReboardDto reboardDto = sqlSession.getMapper(ReboardDao.class).viewArticle(seq);
		if(reboardDto != null) {
			reboardDto.setContent(reboardDto.getContent().replaceAll("\n", "<br>"));
		}
		return reboardDto;
	}

	@Override
	public ReboardDto getArticle(int seq) {
		return sqlSession.getMapper(ReboardDao.class).viewArticle(seq);
	}

	@Override
	public void modifyArticle(ReboardDto reboardDto) {
		ReboardDao reboardDao = sqlSession.getMapper(ReboardDao.class);
		reboardDto.setContent(reboardDto.getContent().replaceAll("\n", "<br>"));
		reboardDao.modifyArticle(reboardDto);
	}

	@Override
	public void deleteArticle(ReboardDto reboardDto) {
		// TODO Auto-generated method stub
		ReboardDao reboardDao = sqlSession.getMapper(ReboardDao.class);
		reboardDao.deleteArticle(reboardDto);
	}
}

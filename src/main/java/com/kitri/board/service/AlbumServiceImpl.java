package com.kitri.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitri.board.dao.AlbumDao;
import com.kitri.board.dao.ReboardDao;
import com.kitri.board.model.AlbumDto;
import com.kitri.common.dao.CommonDao;

@Service
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int writeArticle(AlbumDto albumDto) {
		int seq = sqlSession.getMapper(CommonDao.class).getNextSeq();
		albumDto.setSeq(seq);
		return sqlSession.getMapper(AlbumDao.class).writeArticle(albumDto) == 0 ? 0 : seq;
	}

	@Override
	public List<AlbumDto> listArticle(Map<String, String> map) {
		return sqlSession.getMapper(AlbumDao.class).listArticle(map);
	}

	@Override
	public AlbumDto viewArticle(int seq) {
		sqlSession.getMapper(CommonDao.class).updateHit(seq);
		AlbumDto albumDto = sqlSession.getMapper(AlbumDao.class).viewArticle(seq);
		if(albumDto != null) {
			albumDto.setContent(albumDto.getContent().replaceAll("\n", "<br>"));
		}
		return albumDto;
	}

	@Override
	public AlbumDto getArticle(int seq) {
		return sqlSession.getMapper(AlbumDao.class).viewArticle(seq);
	}

	@Override
	public void modifyArticle(AlbumDto albumDto) {

	}

	@Override
	public void deleteArticle(int seq) {

	}

}

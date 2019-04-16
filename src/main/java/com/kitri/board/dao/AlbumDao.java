package com.kitri.board.dao;

import java.util.List;
import java.util.Map;

import com.kitri.board.model.AlbumDto;

public interface AlbumDao {
	int writeArticle(AlbumDto albumDto);
	List<AlbumDto> listArticle(Map<String, String> map);
	AlbumDto viewArticle(int seq);
	AlbumDto getArticle(int seq);
	void modifyArticle(AlbumDto albumDto);
	void deleteArticle(int seq);
}
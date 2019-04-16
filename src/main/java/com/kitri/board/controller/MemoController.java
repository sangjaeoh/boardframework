package com.kitri.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kitri.board.model.MemoDto;
import com.kitri.board.service.MemoService;
import com.kitri.member.model.MemberDto;

@Controller
public class MemoController {
	
	@Autowired
	private MemoService memoService;
	
	@RequestMapping(value="/memo", method=RequestMethod.POST, headers= {"Content-type=application/json"})
	public @ResponseBody String write(@RequestBody MemoDto memoDto, HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if(memberDto != null) {
			memoDto.setId(memberDto.getId());
			memoDto.setName(memberDto.getName());
			int cnt = memoService.writeMemo(memoDto);
		}
		String memolist = memoService.listMemo(memoDto.getSeq());
		return memolist;
	}
	@RequestMapping(value="/memo/{seq}", method=RequestMethod.GET)
	public @ResponseBody String list(@PathVariable(value= "seq") int seq) {
		String memolist = memoService.listMemo(seq);
		return memolist;
	}
	
	@RequestMapping(value="/memo/{mseq}", method=RequestMethod.DELETE)
	public @ResponseBody String delete(@PathVariable(value= "mseq") int mseq) {
		int cnt = memoService.deleteMemo(mseq);
		return "";
	}
	
	@RequestMapping(value="/memo", method=RequestMethod.PUT, headers= {"Content-type=application/json"})
	public @ResponseBody String modify(@RequestBody MemoDto memoDto, HttpSession session) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if(memberDto != null) {
			int cnt = memoService.modifyMemo(memoDto);
		}
		String memolist = memoService.listMemo(memoDto.getSeq());
		return memolist;
	}
}
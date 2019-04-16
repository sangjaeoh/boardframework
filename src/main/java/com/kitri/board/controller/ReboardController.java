package com.kitri.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kitri.board.model.MemoDto;
import com.kitri.board.model.ReboardDto;
import com.kitri.board.service.ReboardService;
import com.kitri.common.service.CommonService;
import com.kitri.member.model.MemberDto;
import com.kitri.util.PageNavigation;

@Controller
@RequestMapping("/reboard")
public class ReboardController {
	
	@Autowired
	private ReboardService reboardService;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping("/list.kitri")
	public ModelAndView list(@RequestParam Map<String, String> map) {
		ModelAndView mav = new ModelAndView();
		
		List<ReboardDto> list = reboardService.listArticle(map);
		PageNavigation navigation = commonService.getPageNavigation(map);
		navigation.setRoot("/board");
		navigation.makeNavigator();
		mav.addObject("list", list);
		mav.addObject("navigator", navigation);
		mav.setViewName("reboard/list");
		return mav;
	}
	
	@RequestMapping(value="/write.kitri", method=RequestMethod.GET)
	public String write() {
		return "reboard/write";
	}
	
	@RequestMapping(value="/write.kitri", method=RequestMethod.POST)
	public String write(ReboardDto reboardDto, HttpSession session, Model model) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if(memberDto != null) {
			reboardDto.setId(memberDto.getId());
			reboardDto.setName(memberDto.getName());
			reboardDto.setEmail(memberDto.getEmail1()+"@"+memberDto.getEmail2());
			int seq = reboardService.writeArticle(reboardDto);
			if(seq != 0) {
				model.addAttribute("wseq", seq);
			} else {
				model.addAttribute("errorMsg", "글작성 실패");
			}
		} else {
			model.addAttribute("errorMsg", "로그인이 필요한 서비스입니다.");
		}
		return "reboard/writeok";
	}
	@RequestMapping(value="/view.kitri", method=RequestMethod.GET)
	public String view(@RequestParam int seq, HttpSession session, ModelMap model) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if(memberDto != null) {
			ReboardDto reboardDto = (ReboardDto) reboardService.viewArticle(seq);
			model.addAttribute("article", reboardDto);
		}
		return "reboard/view";
	}
	
	@RequestMapping(value="/reply.kitri", method=RequestMethod.GET)
	public String reply(@RequestParam int seq, HttpSession session, ModelMap model) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if(memberDto != null) {
			ReboardDto reboardDto = (ReboardDto) reboardService.getArticle(seq);
			model.addAttribute("article", reboardDto);
		}
		return "reboard/reply";
	}
	
	@RequestMapping(value="reply.kitri", method=RequestMethod.POST)
	public String reply(ReboardDto reboardDto, HttpSession session, Model model) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if(memberDto != null) {
			reboardDto.setId(memberDto.getId());
			reboardDto.setName(memberDto.getName());
			reboardDto.setEmail(memberDto.getEmail1() + "@" + memberDto.getEmail2());
			int seq = reboardService.replyArticle(reboardDto);
			if(seq != 0) {
	 			model.addAttribute("wseq", seq);
			} else {
				model.addAttribute("errorMsg", "글작성 실패!!!!");
			} 
		} else {
			model.addAttribute("errorMsg", "로그인이 필요한 페이지입니다.!!!!");
		}
		return "reboard/writeok";
	} 
	
	@RequestMapping(value="/modify.kitri", method=RequestMethod.GET)
	public String modify(@RequestParam("seq") int seq, HttpSession session, ModelMap model) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if(memberDto != null) {
			ReboardDto reboardDto = (ReboardDto) reboardService.getArticle(seq);
			model.addAttribute("article", reboardDto);
		}
		return "reboard/modify";
	}
	
	@RequestMapping(value="/modify.kitri", method=RequestMethod.POST)
	public String modify(ReboardDto reboardDto, HttpSession session, Model model) {
		reboardService.modifyArticle(reboardDto);
		reboardDto = (ReboardDto) reboardService.getArticle(reboardDto.getSeq());
		model.addAttribute("article", reboardDto);
		return "reboard/view";
	}
	
	@RequestMapping(value="/delete.kitri", method=RequestMethod.GET)
	public String delete(ReboardDto reboardDto, HttpSession session, Model model) {
		reboardService.deleteArticle(reboardDto);
		return "reboard/list";
	}
}
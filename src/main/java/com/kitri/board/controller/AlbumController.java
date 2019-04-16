package com.kitri.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kitri.board.model.AlbumDto;
import com.kitri.board.service.AlbumService;
import com.kitri.common.service.CommonService;
import com.kitri.member.model.MemberDto;
import com.kitri.util.*;

@Controller
@RequestMapping("/album")
public class AlbumController {
	@Autowired
	ServletContext servletContext;
	@Autowired
	private AlbumService albumService;

	@RequestMapping("/list.kitri")
	public ModelAndView list(@RequestParam Map<String, String> map) {
		ModelAndView mav = new ModelAndView();
		List<AlbumDto> list = albumService.listArticle(map);
		mav.addObject("list", list);
		mav.setViewName("album/list");
		return mav;
	}
	
	@RequestMapping(value = "/write.kitri", method=RequestMethod.GET)
	public String write() {
		return "album/write";
	}
	
	@RequestMapping(value="/write.kitri", method=RequestMethod.POST)
	public String write(AlbumDto albumDto, @RequestParam("picture")MultipartFile multipartFile, HttpSession session, Model model) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if(memberDto != null) {
			albumDto.setId(memberDto.getId());
			albumDto.setName(memberDto.getName());
			albumDto.setEmail(memberDto.getEmail1()+"@"+memberDto.getEmail2());
			// 파일 업로드
			if(multipartFile != null && !multipartFile.isEmpty()) {
				String originalPicture = multipartFile.getOriginalFilename();
				//TODO: 나중에 파일 퍼미션 체크하세요. (png, gif, jpeg, jpg)
				
				String realPath = servletContext.getRealPath("/upload/album");
					
				DateFormat df = new SimpleDateFormat("yyMMdd");
				String saveFolder = df.format(new Date()); // 180725
				String realSaveFolder = realPath + File.separator + saveFolder;
				System.out.println(realSaveFolder);
				File dir = new File(realSaveFolder);
				if(!dir.exists()) {
					dir.mkdirs();
				}
				String savePicture = UUID.randomUUID().toString() + originalPicture.substring(originalPicture.lastIndexOf('.'));
				
				File file = new File(realSaveFolder, savePicture);
				try {
					multipartFile.transferTo(file);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				albumDto.setOriginalPicture(originalPicture);
				albumDto.setSavePicture(savePicture);
				albumDto.setSaveFolder(saveFolder);
				
			}
			int seq = albumService.writeArticle(albumDto);
			if(seq != 0) {
				model.addAttribute("wseq", seq);
			} else {
				model.addAttribute("errorMsg", "글작성 실패");
			}
		} else {
			model.addAttribute("errorMsg", "로그인이 필요한 서비스입니다.");
		}
		return "album/writeok";
	}
	
	@RequestMapping(value="/view.kitri", method=RequestMethod.GET)
	public String view(@RequestParam int seq, HttpSession session, ModelMap model) {
		MemberDto memberDto = (MemberDto) session.getAttribute("userInfo");
		if(memberDto != null) {
			AlbumDto albumDto = (AlbumDto) albumService.viewArticle(seq);
			model.addAttribute("article", albumDto);
		} else {
			alert("로그인이 필요한 서비스 입니다.");
		}
		return "album/albumview";
	}

	private void alert(String string) {
		// TODO Auto-generated method stub
		
	}
	
	
}
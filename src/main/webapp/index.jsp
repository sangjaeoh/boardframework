<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kitri.member.model.MemberDto"%>
<%
String root = request.getContextPath();

MemberDto memberDto = new MemberDto();
memberDto.setId("scss");
memberDto.setName("이상규");
memberDto.setEmail1("dltkd5066");
memberDto.setEmail2("naver.com");

session.setAttribute("userInfo", memberDto);

response.sendRedirect(root + "/boardadmin/boardmenu.kitri"); 
%>
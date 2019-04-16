<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/commons/template/top.jsp"%>
<%@ include file="/WEB-INF/views/commons/board_common.jsp"%>
<style>
  #columns{
    column-width:150px;
    column-gap: 15px;
  }
  #columns figure{
    display: inline-block;
    border:1px solid rgba(0,0,0,0.2);
    margin:0;
    margin-bottom: 15px;
    padding:10px;
    box-shadow: 2px 2px 5px rgba(0,0,0,0.5);;
  }
  #columns figure img{
    width:100%;
  }
  #columns figure figcaption{
    border-top:1px solid rgba(0,0,0,0.2);
    padding:10px;
    margin-top:11px;
  }
</style>
<script>
control = "${root}/album";
initPath();


$(document).ready(function() {
	
	$("#firstpage").click(function() {		
		moveBoard('${bcode}', '1', '', '', listpath);		
	});
	
	$(".mvpage").click(function() {
		moveBoard("${bcode}", $(this).attr("move-page-no"), "${key}", "${word}", listpath)
	});
	
	$("#searchBtn").click(function() {
		moveBoard('${bcode}', '1', $("#skey").val(), $("#sword").val(), listpath);		
	});
	
	$("#myBtn").click(function() {
		moveBoard('${bcode}', '1', 'id', '${userInfo.id}', listpath);		
	});
	
	$(".writeBtn").click(function() {
		moveBoard('${bcode}', '1', '', '', writepath);		
	});
	
	$(".posting").click(function() {
		$("#bcode").val("${bcode}");
		$("#pg").val("${pg}");
		$("#key").val("${key}");
		$("#word").val("${word}");
		$("#seq").val($(this).attr("article-seq"));
		document.commonform.action = "${root}/album/view.kitri";
		document.commonform.submit();
	});
	
});

</script>
<!-- title start -->
<table width="100%" cellpadding="0" cellspacing="0" border="0">
	<tr>
		<td><img src="${root}/img/board/m_icon_board.gif" width="9"
			height="9" border="0" align="absmiddle" style="margin-top: -2px">
		<b>앨범게시판</b> &nbsp;<font style="font-size: 8pt">|</font>&nbsp; 자유로운 사진을
		올리는 공간입니다<br>
		</td>
		<td align="right"></td>
	</tr>
	<tr>
		<td colspan="2" height="19"></td>
	</tr>
</table>
<!-- title end -->

<!-- bbs start -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tr valign="bottom">
		<td nowrap><img
			src="${root}/img/board/btn_write_01.gif" width="64" height="22"
			border="0" align="absmiddle" alt="글쓰기" class="writeBtn"></td>
		</td>
	</tr>
	<tr>
		<td colspan="2" height="5" style="padding: 0px"></td>
	</tr>
	
</table>

<hr>
<div id="columns">
<c:forEach var="article" items="${list}">   
     <figure class="posting" article-seq="${article.seq}">
       <img src="${root}/upload/album/${article.saveFolder}/${article.savePicture}">
       <figcaption>글번호 : ${article.seq} <br> 제목 : ${article.subject}<br>조회수: ${article.hit}<br>등록일 : ${article.logtime}</figcaption>
     </figure>
</c:forEach>   
</div>
	
<!-- bbs end -->
<%@ include file="/WEB-INF/views/commons/template/bottom.jsp"%>

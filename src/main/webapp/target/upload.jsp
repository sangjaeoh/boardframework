<%@ page contentType="text/html;charset=EUC_KR" %>

<script type="text/javascript">
var message = '<%=request.getAttribute("message")%>';
var filename = '<%=request.getAttribute("filename")%>';
var filesize = '<%=request.getAttribute("filesize")%>'
var target = '<%=request.getAttribute("target")%>';
var context = '<%=request.getContextPath()%>';

function complete() {	
	if (message != 'null' && message.length != 0) {
		if (message == 'max size exceed!') {
			alert('���ε� ���� ����� ���ѵ� ������� �ʰ��Ͽ����ϴ�!');
		}
		else if (message == 'file invalidateion error') {
			alert('������ �������� �Դϴ�');
		}
		else if (message == 'file upload error') {
			alert('���Ͼ��ε��� ������ �߻��Ͽ����ϴ�');
		}
		
		if (filename.indexOf("_swf") > -1) {
			parent.Alice.flash.loading(false);
		}
		else {
			parent.Alice.image.loading(false);
		}
	}
	else {
		if (filename.indexOf("_swf") > -1) {
			parent.Alice.flash.preview({filename:filename, filesize:filesize, upload:(context.length > 0? context+target:target)});
		}
		else {
			parent.Alice.image.preview({filename:filename, filesize:filesize, upload:(context.length > 0? context+target:target)});
		}
	}
}

window.onload=complete;
</script>
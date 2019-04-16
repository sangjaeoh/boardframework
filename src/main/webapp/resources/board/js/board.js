var control="";

var listpath;
var viewpath ;
var writepath;
var replypath;
var modifypath;
var deletepath;

function initPath(){
	listpath = control+"/list.kitri";
	viewpath = control+"/view.kitri";
	writepath = control+"/write.kitri";
	replypath = control+"/reply.kitri";
	modifypath = control+"/modify.kitri";
	deletepath = control+"/delete.kitri";
}

function moveBoard(bcode, pg, key, word, path) {
	$("#bcode").val(bcode);
	$("#pg").val(pg);
	$("#key").val(key);
	$("#word").val(word);
	$("#commonform").attr("method", "get").attr("action", path).submit();
}
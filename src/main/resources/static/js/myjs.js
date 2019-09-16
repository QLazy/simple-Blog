//$(function() {
//	$("#commentSubmit").click(function() {
//		var id = $("#question_id").val();
//		var content = $("#comment_content").val();
//		var jsonData = {"parentId":id,"parentType":1,"content":content};
//		alert(id+"+++++"+content);
//		$.ajax({
//			type : "POST",
//			contentType : "application/json;charset=utf-8",
//			url : "/comment",
//			data : JSON.stringify(jsonData),
//			dataType: "json",
//			success : function(aaa) {
//				alert("success" + aaa);
//			},
//			error : function(aaa) {
//				alert("error" + aaa);
//			}
//		});
//	});
//});

function post(){
	var id = $("#question_id").val();
	var content = $("#comment_content").val();
	var jsonData = {"parentId":id,"parentType":1,"content":content};
	alert(id+"+++++"+content);
	$.ajax({
		type : "POST",
		contentType : "application/json;charset=utf-8",
		url : "/comment",
		data : JSON.stringify(jsonData),
		dataType: "json",
		success : function(aaa) {
			alert("success" + aaa);
		},
		error : function(aaa) {
			alert("error" + aaa);
		}
	});
}



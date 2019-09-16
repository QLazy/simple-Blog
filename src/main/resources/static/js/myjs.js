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
	var content = $("#commentContent").val();
	var jsonData = {"parentId":id,"parentType":1,"content":content};
	$.ajax({
		type : "POST",
		contentType : "application/json;charset=utf-8",
		url : "/comment",
		data : JSON.stringify(jsonData),
		dataType: "json",
		success : function(parm) {
			if(parm.code == 200){
				// 将输入框隐藏或缩小
				$("#commentUser").hide();
				$("#commentSubmit").hide();
				$("#commentContent").attr("rows",1);
				
			}else if(parm.code == 2003){
				var isAccepted = confirm(parm.message);
				if(isAccepted){
					//window.localtion.replace("登录页面");
					//window.localStorage.setItem("closeFlag",true);
					alert(parm.message);
				}else{
					alert(parm.message);
				}
			}
		},
	});
}



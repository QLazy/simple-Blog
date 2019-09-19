var isNull = /^[\s\S]*.*[^\s][\s\S]*$/; // 正则判断是否为空
var timer;
// 增加一级回复
$(function() {
	$("#commentSubmit").click(function() {
		var id = $("#question_id").val();
		var content = $("#commentContent").val();
		commentBody(id, 1, content);
		// clearTimeout(timer);
	});
});
// 增加二级回复
function commentSecond(data) {
	var id = data.getAttribute("data-id");
	var content = $("#input-" + id).val();
	commentBody(id, 2, content);
}

// 向后端传递回复参数
function commentBody(id, type, content) {
	var jsonData = {
		"parentId" : id,
		"parentType" : type,
		"content" : content
	};
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/comment",
		data : JSON.stringify(jsonData),
		dataType : "json",
		processData : false,

		success : function(parm) {
			var comment = $("#commentContent");
			if (parm.code == 200) {
				// 将输入框隐藏或缩小
				comment.val("");
			} else if (parm.code == 2003) {
				var isAccepted = confirm(parm.message);
				if (isAccepted) {
					// window.localtion.replace("登录页面");
					// window.localStorage.setItem("closeFlag",true);
				} else {
					// alert(parm.message);
				}
			} else {
				alert(parm.message);
			}
		},
	});
}

$(function() {
	$("#commentContent").click(function() {
		var comment = $("#commentContent");
		comment.attr("rows", "5");
		comment.removeAttr("style");
		$("#commentSubmit").removeAttr("style");
	});
});
// 增加blur事件延时，使得提交按钮得以运行
$(function() {
	$("#commentContent").blur(function() {
		var comment = $("#commentContent");
		timer = setTimeout(function() {
			if (comment.val() == "" || !isNull.test(comment.val())) {
				comment.attr("rows", "1");
				comment.attr("style", "height:34px;");
				comment.val("");
				$("#commentSubmit").attr("style", "display:none;");
			}
		}, 200)
	});
});

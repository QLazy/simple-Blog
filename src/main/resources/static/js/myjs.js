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
// 增加二级评论显示
function collapseComment() {
	var id = $("#comment-reply").attr("data-id");
//	$('#comment-reply').attr('id', 'comment-reply-阿萨德');
//	document.getElementById('comment-reply').setAttribute('id','comment-reply-asd');
	$.getJSON("/comment/" + id, function(data) {
		var comments = $("#comment-" + id);
		$.each(data.data.reverse(), function(index, comment) {
			// ------------头像div--------------------
			// 放置个人头像
			var avatorElement = $("<img/>", {
				"class" : "media-object img-rounded avatorUrl-second-comment",
				"src" : comment.user.avatarUrl
			});
			// 头像跳转到个人主页
			var userPageElement = $("<a/>", {
				"href" : "#",
			}).append(avatorElement);
			var mediaLeftElement = $("<div/>", {
				"class" : "media-left"
			}).append(userPageElement);
			// -------------评论内容div---------------------------
			// 分割线
			var hrElement = $("<hr/>", {
				"class" : "comment-hr"
			})
			// 显示操作选项
			var likeElement = $("<span/>", {
				"class" : "glyphicon glyphicon-thumbs-up icon" // 点赞按钮
			})
			var replyElement = $("<span/>", {
				"class" : "icon comment-handle", // 回复按钮
				"text" : "回复"
			})
			var deleteElement = $("<span/>", {
				"class" : "icon comment-handle", // 删除按钮
				"text" : "删除"
			})
			// 显示评论时间
			var commentTimeElement = $("<span/>", {
				"html" : moment(comment.gmtCreate).format('YYYY-MM-DD HH:mm')
			})
			// 显示评论菜单
			var commentMenuElement = $("<div/>", {
				"class" : "comment-menu"
			}).append(commentTimeElement).append(likeElement).append(
					replyElement).append(deleteElement);
			// 显示评论内容
			var contentElement = $("<span/>", {
				"class" : "comment-content",
				"html" : comment.content
			})
			// 显示名字
			var userNameElement = $("<span/>", {
				"class" : "media-heading comment-userName",
				"html" : comment.user.name + '&nbsp; ： &nbsp;'
			})
			// 将名字与评论包起来
			var nameAndContentElement = $("<div/>", {

			}).append(userNameElement).append(contentElement);
			// 显示评论体
			var mediaBodyElement = $("<div/>", {
				"class" : "media-body"
			}).append(nameAndContentElement).append(commentMenuElement).append(
					hrElement);
			// 主体
			var mediaElement = $("<div/>", {
				"class" : "media"
			}).append(mediaLeftElement).append(mediaBodyElement);

			comments.prepend(mediaElement);
		});
	});

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
					 window.open("http://localhost:8080/checkUser");
					 window.localStorage.setItem("closeFlag","true");
				} else {
					 alert(parm.message);
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

// 添加标签
function selectTag(e) {
	var value = e.getAttribute("data-tag");
	var tag = $("#tag");
	if (tag.val().indexOf(value) != -1) {

	} else {
		if (tag.val()) {
			tag.val(tag.val() + '，' + value);
		} else {
			tag.val(value);
		}
	}
}

function showSelectTag(data) {
	$("#select-tag").show();
}


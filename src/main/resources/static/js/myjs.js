var isNull = /^[\s\S]*.*[^\s][\s\S]*$/; // 正则判断是否为空
var timer;
// 增加一级回复
function comment() {
	var id = $("#question_id").val();
	var content = $("#commentContent").val();
	commentBody(id, 1, content);
}

// 增加二级回复
function commentSecond(data) {
	var id = data.getAttribute("data-id");
	var content = $("#input-" + id).val();
	commentBody(id, 2, content);
	$("#input-" + id).val("");
}
// 增加二级评论显示
function collapseComment() {
	var id = $("#comment-reply").attr("data-id");
	var commentator = $("#comment-reply").attr("data-creator");
	var userId = $("#comment-reply").attr("data-aa");
	document.getElementById('comment-reply').setAttribute('id',
			'comment-reply-' + id);
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
				"class" : "glyphicon glyphicon-thumbs-up icon", // 点赞按钮
				"onclick" : "like(this)",
				"data-id" : comment.id,
				"text" : comment.likeCount
			})
			var replyElement = $("<span/>", {
				"class" : "icon comment-handle", // 回复按钮
				"text" : "回复"
			})
			var deleteElement = $("<span/>", {
				"class" : "icon comment-handle", // 删除按钮
				"text" : "删除",
				"data-type" : comment.parentType,
				"data-id" : comment.id,
				"data-parentId" : comment.parentId,
				"onclick" : "delect(this)"
			})
			// 显示评论时间
			var commentTimeElement = $("<span/>", {
				"html" : moment(comment.gmtCreate).format('YYYY-MM-DD HH:mm')
			})
			// 显示评论菜单
			var commentMenuElement = $("<div/>", {
				"class" : "comment-menu"
			}).append(commentTimeElement).append(likeElement).append(
					replyElement);
			// 判断当前用户是否具有删除权限
			debugger;
			if (userId == comment.user.id || userId == commentator) {
				commentMenuElement.append(deleteElement)
			}
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

// 实现点赞功能
function like(data) {
	var id = data.getAttribute("data-id");
	var jsonData = {
		"id" : id
	};
	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/comment/like",
		data : JSON.stringify(jsonData),
		dataType : "json",
		processData : false,
		success : function(parm) {
			$(data).text(parm.data.likeCount);
		},
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
				window.location.reload();
				comment.val("");
			} else if (parm.code == 2003) {
				var isAccepted = confirm(parm.message);
				if (isAccepted) {
					window.open("http://"+GetUrlRelativePath()+"/checkUser");
					window.localStorage.setItem("closeFlag", "true");
				} else {
					alert(parm.message);
				}
			} else {
				alert(parm.message);
			}
		},
	});
}

function GetUrlRelativePath(){
　　var url = document.location.toString();
　　var arrUrl = url.split("//");

　　var start = arrUrl[1].split("/");
　　return start[0];
}

function showText() {
	var comment = $("#commentContent");
	comment.attr("rows", "5");
	comment.removeAttr("style");
	$("#commentSubmit").removeAttr("style");
}
// 增加blur事件延时，使得提交按钮得以运行
function closeText() {
	var comment = $("#commentContent");
	timer = setTimeout(function() {
		if (comment.val() == "" || !isNull.test(comment.val())) {
			comment.attr("rows", "1");
			comment.attr("style", "height:34px;");
			$("#commentSubmit").attr("style", "display:none;");
			comment.val("");
		}
	}, 200)
}

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

// 删除相应的评论
function delect(target) {
	var id = target.getAttribute("data-id");
	var type = target.getAttribute("data-type");
	var parentId = target.getAttribute("data-parentId");
	var jsonData = {
		"id" : id,
		"parentId" : parentId,
		"parentType" : type,
		"content" : ""
	};
	var isAccepted = confirm("真的要删除吗 (つД｀)･ﾟ･");
	if (isAccepted) {
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/comment/delect",
			data : JSON.stringify(jsonData),
			dataType : "json",
			processData : false,
			success : function(parm) {
				window.location.reload();
			},
		});
	}
}

package com.example.myBlog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.myBlog.dto.NotificationDTO;
import com.example.myBlog.entity.MyUser;
import com.example.myBlog.enums.NotificationEnum;
import com.example.myBlog.service.NotificationService;

@Controller
public class NotificationContrller {

	@Autowired
	private NotificationService notificationService;

	@GetMapping("/notification/{id}")
	public String editPublish(@PathVariable(name = "id") int id, HttpServletRequest request) {
		MyUser myUser = (MyUser) request.getSession().getAttribute("user");
		if (myUser == null) {
			return "redirect:/";
		}

		NotificationDTO notificationDTO = notificationService.read(id, myUser);

		if (NotificationEnum.REPLY_COMMENT.getType() == notificationDTO.getType()
				|| NotificationEnum.REPLY_QUESTION.getType() == notificationDTO.getType()) {
			return "redirect:/question/" + notificationDTO.getOuterId();
		}

		return "publish";
	}
}

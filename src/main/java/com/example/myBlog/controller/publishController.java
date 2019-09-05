package com.example.myBlog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.myBlog.entity.myQuestion;
import com.example.myBlog.entity.myUser;
import com.example.myBlog.service.myQuestionService;
import com.example.myBlog.service.myUserService;

@Controller
public class publishController {
	
	@Autowired
	myUserService myservice;
	
	@Autowired
	myQuestionService mqs;
	
	//get提交就渲染页面
	@GetMapping("/publish")
	public String getPublish() {
		return "publish";
	}
	
	//验证表单
	@PostMapping("/publish")
	public String postPublish(
			@RequestParam("title") String title,
			@RequestParam("description") String description,
			@RequestParam("tag") String tag,
			HttpServletRequest request,
			Model model) {
		
		
		myUser user = myservice.queryUserByToken(request);
		
		myQuestion mq = new myQuestion();
		
		if(title==null || title.equals("")) {
			model.addAttribute("error", "标题不能为空");
			return "publish";
		}
		if(tag==null || tag.equals("")) {
			model.addAttribute("error", "标签不能为空");
			return "publish";
		}
		if(description==null || description.equals("")) {
			model.addAttribute("error", "问题补充不能为空");
			return "publish";
		}
		
		mq.setTitle(title);
		mq.setTag(tag);
		mq.setCreator(user.getId());
		mq.setDescription(description);
		mq.setGmtCreate(System.currentTimeMillis());
		mq.setGmtModified(mq.getGmtCreate());
		
		mqs.add(mq);
		
		
		return "publish";
	}
}













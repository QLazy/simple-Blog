package com.example.myBlog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.myBlog.dto.questionDTO;
import com.example.myBlog.entity.myQuestion;
import com.example.myBlog.entity.myUser;
import com.example.myBlog.service.myQuestionService;

@Controller
public class publishController {

	@Autowired
	private myQuestionService questionService;

	// get提交就渲染页面
	@GetMapping("/publish/{id}")
	public String editPublish(Model model, @PathVariable(name = "id") int id) {

		questionDTO questionDTO = questionService.queryQuestionById(id);
		model.addAttribute("title", questionDTO.getTitle());
		model.addAttribute("tag", questionDTO.getTag());
		model.addAttribute("description", questionDTO.getDescription());
		model.addAttribute("id", questionDTO.getId());
		
		return "publish";
	}
	
	@GetMapping("/publish")
	public String publish() {
		return "publish";
	}
	
	// 验证表单
	@PostMapping("/publish")
	public String postPublish(
			@RequestParam("title") String title,
			@RequestParam("description") String description,
			@RequestParam("tag") String tag, 
			@RequestParam(value = "id", defaultValue = "0") int id,
			HttpServletRequest request, 
			Model model) {

		myUser user = (myUser) request.getSession().getAttribute("user");
		myQuestion question = new myQuestion();

//		if(user==null) {
//			return "redirect:/";
//		}
		
		model.addAttribute("title", title);
		model.addAttribute("description", description);
		model.addAttribute("tag", tag);

		if (title == null || title.equals("")) {
			model.addAttribute("error", "* 标题不能为空");
			return "publish";
		}
		if (description == null || description.equals("")) {
			model.addAttribute("error", "* 问题补充不能为空");
			return "publish";
		}
		if (tag == null || tag.equals("")) {
			model.addAttribute("error", "* 标签不能为空");
			return "publish";
		}
		
		question.setTitle(title);
		question.setTag(tag);
		question.setCreator(user.getId());
		question.setDescription(description);
		question.setId(id);

		questionService.addOrUpdate(question);

		return "redirect:/";
	}
}

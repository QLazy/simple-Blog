package com.example.myBlog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.myBlog.dto.CommentCreatorDTO;
import com.example.myBlog.dto.PaginationDTO;
import com.example.myBlog.dto.QuestionQueryDTO;
import com.example.myBlog.dto.ResultDTO;
import com.example.myBlog.entity.MyComment;
import com.example.myBlog.entity.MyUser;
import com.example.myBlog.excuption.CustomizeErrorCode;
import com.example.myBlog.service.QuestionService;

@Controller
public class IndexController {

	@Autowired
	private QuestionService questionService;

	@RequestMapping("/")
	public String index(HttpServletRequest request, Model model,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "size", defaultValue = "8") int size,
			@RequestParam(name = "search", required = false) String search) {
		QuestionQueryDTO queryDTO = new QuestionQueryDTO();
		queryDTO.setPage(page);
		queryDTO.setSize(size);
		queryDTO.setSearch(search);
		request.getSession().setAttribute("search", search);
		@SuppressWarnings("rawtypes")
		PaginationDTO pagination = questionService.queryAllQuestion(null,queryDTO);
		model.addAttribute("pagination", pagination);

		return "index";
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(path = "/time", method = RequestMethod.POST)
	public ResultDTO like() {
		return ResultDTO.succesOf("1569733036935");
	}
}

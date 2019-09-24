package com.example.myBlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.myBlog.dto.FileDTO;

@Controller
public class FileController {
	@ResponseBody
	@RequestMapping("/file/upload")
	public FileDTO upload() {
		FileDTO fileDTO = new FileDTO();
		fileDTO.setSuccess(1);
		fileDTO.setUrl("/images/1.jpg");
		return fileDTO;
	}
}

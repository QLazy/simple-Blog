package com.example.myBlog.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.myBlog.dto.FileDTO;
import com.example.myBlog.provider.UCloudProvider;

@Controller
public class FileController {

	@Autowired
	private UCloudProvider uCloudProvider;

	@ResponseBody
	@RequestMapping("/file/upload")
	public FileDTO upload(HttpServletRequest request) {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("editormd-image-file");

		try {
			String fileName = uCloudProvider.upload(file.getInputStream(), file.getContentType(), file.getOriginalFilename());
			FileDTO fileDTO = new FileDTO();
			fileDTO.setSuccess(1);
			fileDTO.setUrl(fileName);
			return fileDTO;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		FileDTO fileDTO = new FileDTO();
		fileDTO.setSuccess(1);
		fileDTO.setUrl("/images/1.jpg");
		return fileDTO;
	}
}

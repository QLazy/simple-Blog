package com.example.myBlog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.myBlog.excuption.CustomizeErrorCode;

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomizeErrorContruller implements ErrorController {

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "error";
	}

	@RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView errorHtml(HttpServletRequest request, Model model) {
		HttpStatus status = getStatus(request);

		if (status.is4xxClientError()) {
			model.addAttribute("message", CustomizeErrorCode.CLIENT_ERROR);
		}
		if (status.is5xxServerError()) {
			model.addAttribute("message", CustomizeErrorCode.SERVICE_ERROR);
		}

		return new ModelAndView("error");
	}

	protected HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		try {
			return HttpStatus.valueOf(statusCode);
		} catch (Exception ex) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}

}

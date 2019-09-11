package com.example.myBlog.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.example.myBlog.excuption.CustomizeErrorCode;
import com.example.myBlog.excuption.CustomizeExcuption;

@ControllerAdvice
public class CustomizeExcuptionHandle {

	@ExceptionHandler(Exception.class)
	ModelAndView handle(HttpServletRequest request, Throwable e, Model model) {
//		HttpStatus status = getStatus(request);

		if (e instanceof CustomizeExcuption) {
			model.addAttribute("message", e.getMessage());
		} else {
			model.addAttribute("message", CustomizeErrorCode.SERVICE_ERROR);
		}

		return new ModelAndView("error");
	}

}
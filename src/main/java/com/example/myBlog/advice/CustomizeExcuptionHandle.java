package com.example.myBlog.advice;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.example.myBlog.dto.ResultDTO;
import com.example.myBlog.excuption.CustomizeErrorCode;
import com.example.myBlog.excuption.CustomizeExcuption;


@ControllerAdvice
public class CustomizeExcuptionHandle {

	@ExceptionHandler(Exception.class)
	ModelAndView handle(HttpServletRequest request, HttpServletResponse response,
			Throwable e, Model model) {
		
		String contentType = request.getContentType();
		
		if("application/json".equals(contentType)) {
			
			ResultDTO resultDTO = null;
			if (e instanceof CustomizeExcuption) {
				resultDTO = ResultDTO.errorOf((CustomizeExcuption) e);
			} else {
				resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SERVICE_ERROR);
			}
			
			try {
				response.setContentType("application/json");
				response.setStatus(200);
				response.setCharacterEncoding("utf-8");
				PrintWriter writer = response.getWriter();
				writer.write(JSON.toJSONString(resultDTO));
				writer.close();
			} catch (IOException e1) {
			}
			
			return null;
		}else {
			if (e instanceof CustomizeExcuption) {
				model.addAttribute("message", e.getMessage());
			} else {
				model.addAttribute("message", CustomizeErrorCode.SERVICE_ERROR.getMessage());
			}
			
			return new ModelAndView("error");
		}
		
		

	}

}

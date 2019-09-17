package com.example.myBlog.dto;

import com.example.myBlog.excuption.CustomizeErrorCode;
import com.example.myBlog.excuption.CustomizeExcuption;

import lombok.Data;

@Data
public class ResultDTO {
	private int code;
	private String message;
	
	public static ResultDTO errorOf(Integer code,String massage) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setCode(code);
		resultDTO.setMessage(massage);
		return resultDTO;
	}

	public static ResultDTO errorOf(CustomizeErrorCode noLogin) {
		return errorOf(noLogin.getCode(),noLogin.getMessage());
	}
	
	public static ResultDTO succesOf() {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setCode(200);
		resultDTO.setMessage("请求成功");
		return resultDTO;
	}

	public static ResultDTO errorOf(CustomizeExcuption e) {
		return errorOf(e.getCode(),e.getMessage());
	}
	
}

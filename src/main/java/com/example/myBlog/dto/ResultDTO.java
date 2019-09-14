package com.example.myBlog.dto;

import com.example.myBlog.excuption.CustomizeErrorCode;
import com.example.myBlog.excuption.CustomizeExcuption;

import lombok.Data;

@Data
public class ResultDTO {
	private int code;
	private String massage;
	
	public static ResultDTO errorOf(Integer code,String massage) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setCode(code);
		resultDTO.setMassage(massage);
		return resultDTO;
	}

	public static ResultDTO errorOf(CustomizeErrorCode noLogin) {
		return errorOf(noLogin.getCode(),noLogin.getMassage());
	}
	
	public static ResultDTO succesOf() {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setCode(200);
		resultDTO.setMassage("请求成功");
		return resultDTO;
	}

	public static ResultDTO errorOf(CustomizeExcuption e) {
		return errorOf(e.getCode(),e.getMessage());
	}
	
}

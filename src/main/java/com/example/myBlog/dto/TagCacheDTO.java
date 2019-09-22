package com.example.myBlog.dto;

import java.util.List;

import lombok.Data;

@Data
public class TagCacheDTO {

	private String tagCacheName;
	private List<String> tagCacheValue;
	
}

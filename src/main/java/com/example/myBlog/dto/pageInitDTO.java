package com.example.myBlog.dto;

import java.util.List;

import lombok.Data;

@Data
public class pageInitDTO {
	
	private List<questionDTO> questions;
	private boolean showPrvious;
	private boolean showFirstPage;
	private boolean showNext;
	private boolean showEndPage;
	private int pages;
	private int page;
}

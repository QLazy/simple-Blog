package com.example.myBlog.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PaginationDTO<T> {

	private List<T> data;
	private boolean showPrvious;
	private boolean showFirstPage;
	private boolean showNext;
	private boolean showEndPage;
	private int totalPages;
	private int page;
	private List<Integer> pages = new ArrayList<>();

	public void pagination(int totalPages, int page) {
		this.page = page;
		this.totalPages = totalPages;
		
		pages.add(page);
		for (int i = 1; i < 4; i++) {
			if (page - i > 0) {
				// 从头插入数据，防止乱序
				pages.add(0, page - i);
			}
			if (page + i <= totalPages) {
				pages.add(page + i);
			}
		}

		showNext = page != totalPages;
		showPrvious = page != 1;
		showFirstPage = !pages.contains(1);
		showEndPage = !pages.contains(totalPages);

	}
}

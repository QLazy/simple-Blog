package com.example.myBlog.dto;

import lombok.Data;

@Data
public class NotificationDTO {
	private Integer id;
	private Integer status;
	private Long gmtCreate;
	private Integer notifier;
	private String notifierName;
	private String outerTitle;
	private String typeName;
	private Integer type;
	private Integer outerId;
	private Integer receiver;
}

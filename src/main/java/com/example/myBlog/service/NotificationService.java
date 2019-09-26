package com.example.myBlog.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myBlog.dto.NotificationDTO;
import com.example.myBlog.dto.PaginationDTO;
import com.example.myBlog.entity.MyUser;
import com.example.myBlog.entity.Notification;
import com.example.myBlog.entity.NotificationExample;
import com.example.myBlog.enums.NotificationEnum;
import com.example.myBlog.enums.NotificationStatusEnum;
import com.example.myBlog.excuption.CustomizeErrorCode;
import com.example.myBlog.excuption.CustomizeExcuption;
import com.example.myBlog.mapper.NotificationMapper;

@Service
public class NotificationService {

	@Autowired
	private NotificationMapper notificationMapper;

	@SuppressWarnings("rawtypes")
	public PaginationDTO queryAllNotification(MyUser myuser, int page, int size) {

		PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();

		NotificationExample notificationExample = new NotificationExample();
		int totalCount = 0;

		if (myuser == null) {
			totalCount = (int) notificationMapper.countByExample(new NotificationExample());
		} else {
			notificationExample.createCriteria().andReceiverEqualTo(myuser.getId());
			totalCount = (int) notificationMapper.countByExample(notificationExample);
		}
		int totalPages = (int) Math.ceil(totalCount * 1.0 / size);

		if (page > totalPages) {
			page = totalPages;
		} else if (page < 1) {
			page = 1;
		}

		page = page == 0 ? 1 : page;
		totalPages = totalPages == 0 ? 1 : totalPages;
		
		int pageStartData = size * (page - 1);

		paginationDTO.pagination(totalPages, page);

		notificationExample.createCriteria().andReceiverEqualTo(myuser.getId());
		notificationExample.setOrderByClause("gmt_Create desc");
		List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(notificationExample,
				new RowBounds(pageStartData, size));

		if (notifications.size() == 0) {
			return paginationDTO;
		}

		List<NotificationDTO> notificationDTOs = new ArrayList<>();
		for (Notification notification : notifications) {
			NotificationDTO notificationDTO = new NotificationDTO();
			BeanUtils.copyProperties(notification, notificationDTO);
			notificationDTO.setTypeName(NotificationEnum.nameOfType(notification.getType()));
			notificationDTOs.add(notificationDTO);
		}

		paginationDTO.setData(notificationDTOs);

		return paginationDTO;
	}

	//统计未读通知数量
	public int unreadCount(Integer id) {
		NotificationExample notificationExample = new NotificationExample();
		notificationExample.createCriteria().andReceiverEqualTo(id)
				.andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
		return (int) notificationMapper.countByExample(notificationExample);
	}

	public NotificationDTO read(int id, MyUser myUser) {

		Notification notification = notificationMapper.selectByPrimaryKey(id);

		if (notification == null) {
			throw new CustomizeExcuption(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
		}

		if (myUser.getId() != notification.getReceiver()) {
			throw new CustomizeExcuption(CustomizeErrorCode.READ_NOTIFICATION_USER_ERROR);
		}

		notification.setStatus(NotificationStatusEnum.READ.getStatus());
		notificationMapper.updateByPrimaryKey(notification);

		NotificationDTO notificationDTO = new NotificationDTO();
		BeanUtils.copyProperties(notification, notificationDTO);
		notificationDTO.setTypeName(NotificationEnum.nameOfType(notification.getType()));

		return notificationDTO;
	}

}

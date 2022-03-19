package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Message;
import com.example.demo.repository.BulletinBoardMapper;
import com.example.demo.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private BulletinBoardMapper mapper;
	
	@Override
	public List<Message> getMessageas(String threadNumber) {
		return mapper.selectMessageList(threadNumber);
	}

	
}

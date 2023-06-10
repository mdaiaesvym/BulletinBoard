package com.example.demo.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Message;
import com.example.demo.repository.BulletinBoardMapper;
import com.example.demo.service.MessageService;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

  @Autowired
  private BulletinBoardMapper mapper;

  @Override
  public Integer getThreadCount() {
    return mapper.selectThreadCount();
  }

  @Override
  public boolean addMessage(Message message) {
    return mapper.insertMessage(message);
  }

  @Override
  public List<Message> getMessageas(String threadNumber) {
    return mapper.selectMessageList(threadNumber);
  }

  @Override
  public String getThreadName(String threadNumber) {
    return mapper.selectThreadName(threadNumber);
  }

}

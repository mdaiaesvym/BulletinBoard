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
  public boolean addMessage(Message message) {
    mapper.addMessage(message);

    return mapper.updateThread(message);
  }

  @Override
  public List<Message> getMessageList(Integer threadNumber) {
    return mapper.getMessageList(threadNumber);
  }

  @Override
  public String getThreadName(Integer threadNumber) {
    return mapper.getThreadName(threadNumber);
  }

}

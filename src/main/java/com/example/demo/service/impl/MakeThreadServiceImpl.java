package com.example.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.form.MakeThreadForm;
import com.example.demo.model.Message;
import com.example.demo.model.Thread;
import com.example.demo.repository.BulletinBoardMapper;
import com.example.demo.service.MakeThreadService;

@Service
public class MakeThreadServiceImpl implements MakeThreadService {

  @Autowired
  private BulletinBoardMapper mapper;

  @Override
  public boolean makeThread(Thread thread) {
    return mapper.insertThread(thread);
  }

  @Override
  public boolean addMessage(Message message) {
    return mapper.insertMessage(message);
  }

  @Override
  public int getThreadMaxNumber() {
    return mapper.threadMaxNumber();
  }

  @Override
  public void setContributorName(MakeThreadForm makethreadForm) {
    if (!makethreadForm.isHasContributorName()) {
      makethreadForm.setContributorName("匿名");
    }
  }

  @Override
  public List<Thread> getThreadNameCount() {
    return mapper.selectThreadList();
  }

}

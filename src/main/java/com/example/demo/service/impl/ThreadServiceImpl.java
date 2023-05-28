package com.example.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Thread;
import com.example.demo.repository.BulletinBoardMapper;
import com.example.demo.service.ThreadService;

@Service
public class ThreadServiceImpl implements ThreadService {

  @Autowired
  private BulletinBoardMapper mapper;

  @Override
  public boolean makeThread(Thread thread) {
    return mapper.insertThread(thread);
  }

  @Override
  public int getThreadMaxNumber() {
    return mapper.threadMaxNumber();
  }

  @Override
  public List<Thread> getThreadNameCount() {
    return mapper.selectThreadList();
  }

}

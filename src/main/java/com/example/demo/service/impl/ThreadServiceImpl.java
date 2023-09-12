package com.example.demo.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Thread;
import com.example.demo.repository.BulletinBoardMapper;
import com.example.demo.service.ThreadService;

@Service
@Transactional
public class ThreadServiceImpl implements ThreadService {

  @Autowired
  private BulletinBoardMapper mapper;

  @Override
  public Integer makeThread(Thread thread) {
    mapper.makeThread(thread);

    // 追加されたオートインクリメントの値（スレッド番号）を返却
    return thread.getThreadNumber();
  }

  @Override
  public List<Thread> getThreadList() {
    return mapper.getThreadList();
  }

}

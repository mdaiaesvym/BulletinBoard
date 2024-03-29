package com.example.demo.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.model.Thread;
import com.example.demo.service.ThreadService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ThreadsController {
  private final ThreadService threadService;

  private final String THREADS = "threads";

  /**
   * 画面表示
   * 
   * @param model
   * @return
   */
  @GetMapping(THREADS)
  public String show(Model model) {

    // スレッド名・メッセージ数・メッセージ最終更新日時取得
    List<Thread> threadList = threadService.getThreadList();
    model.addAttribute("threadList", threadList);

    return THREADS;
  }
}

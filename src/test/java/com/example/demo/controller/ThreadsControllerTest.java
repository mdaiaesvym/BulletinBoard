package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.example.demo.service.MakeThreadService;

@SpringBootTest
@AutoConfigureMockMvc
public class ThreadsControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  MakeThreadService makeThreadService;

  @Test
  public void アクセス成功() throws Exception {
    // アクセス
    mockMvc.perform(get("/threads"))
        // リクエスト成功をテスト
        .andExpect(status().isOk())
        // ビュー名をテスト
        .andExpect(view().name("threads"))
        // modelに存在することをテスト
        .andExpect(model().attribute("threadList", makeThreadService.getThreadNameCount()));
  }
}

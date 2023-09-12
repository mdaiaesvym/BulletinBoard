package com.example.demo.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.example.demo.service.MessageService;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MessagesControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  MessageService messageService;

  @BeforeEach
  public void setUp() {
    when(messageService.addMessage(any())).thenReturn(true);
  }

  @Test
  public void 存在するページにアクセス() throws Exception {
    List<Integer> threadNumberList = Arrays.asList(1, 2, 3);
    when(messageService.getThreadNumberList()).thenReturn(threadNumberList);

    mockMvc.perform(get("/messages?threadNumber=1"))
        // リクエスト成功をテスト
        .andExpect(status().isOk())
        // ビュー名をテスト
        .andExpect(view().name("messages"))
        // modelに存在することのテスト
        .andExpect(model().attribute("messageList", messageService.getMessageList(1)))
        .andExpect(model().attribute("threadName", messageService.getThreadName(1)))
        .andExpect(model().attribute("threadNumber", 1));
  }

  @Test
  public void 存在しないページにアクセス() throws Exception {
    List<Integer> threadNumberList = Arrays.asList(1, 2, 3);
    when(messageService.getThreadNumberList()).thenReturn(threadNumberList);

    mockMvc.perform(get("/messages?threadNumber=11"))
        // リダイレクトに成功することのテスト
        .andExpect(status().isFound())
        // リダイレクト先URLのテスト
        .andExpect(redirectedUrl("threads"));

    mockMvc.perform(get("/messages?threadNumber=0"))
        // リダイレクトに成功することのテスト
        .andExpect(status().isFound())
        // リダイレクト先URLのテスト
        .andExpect(redirectedUrl("threads"));
  }

  @Test
  public void メッセージ投稿成功_投稿者名フラグオフ() throws Exception {
    mockMvc.perform(post("/messages?threadNumber=1")
        // params = "postMessage"の呼び出し
        .param("postMessage", "")
        // formに値を設定
        .param("message", "メッセージテスト").param("hasContributorName", "false")
        .param("contributorName", ""))
        // エラーがないことのテスト
        .andExpect(model().hasNoErrors())
        // リダイレクトに成功することのテスト
        .andExpect(status().isFound())
        // 成功メッセージがあること
        .andExpect(flash().attributeExists("infoMessage"))
        // リダイレクト先URLのテスト
        .andExpect(redirectedUrl("messages?threadNumber=1"));
  }

  @Test
  public void メッセージ投稿成功_投稿者名フラグオン() throws Exception {
    mockMvc.perform(post("/messages?threadNumber=1")
        // params = "postMessage"の呼び出し
        .param("postMessage", "")
        // formに値を設定
        .param("message", "メッセージテスト").param("hasContributorName", "true")
        .param("contributorName", "投稿者テスト"))
        // エラーがないことのテスト
        .andExpect(model().hasNoErrors())
        // リダイレクトに成功することのテスト
        .andExpect(status().isFound())
        // 成功メッセージがあること
        .andExpect(flash().attributeExists("infoMessage"))
        // リダイレクト先URLのテスト
        .andExpect(redirectedUrl("messages?threadNumber=1"));
  }

  @Test
  public void メッセージ投稿失敗_メッセージが空() throws Exception {
    List<Integer> threadNumberList = Arrays.asList(1, 2, 3);
    when(messageService.getThreadNumberList()).thenReturn(threadNumberList);

    mockMvc.perform(post("/messages")
        // params = "postMessage"の呼び出し
        .param("postMessage", "")
        // formに値を設定
        .param("threadNumber", "1").param("message", "").param("hasContributorName", "true")
        .param("contributorName", "投稿者テスト"))
        // エラーがあることのテスト
        .andExpect(model().hasErrors())
        // リクエスト成功をテスト
        .andExpect(status().isOk())
        // エラーメッセージがあること
        .andExpect(model().attributeExists("errorMessage"))
        // ビュー名をテスト
        .andExpect(view().name("messages"));
  }

  @Test
  public void メッセージ投稿失敗_投稿者が空() throws Exception {
    List<Integer> threadNumberList = Arrays.asList(1, 2, 3);
    when(messageService.getThreadNumberList()).thenReturn(threadNumberList);

    mockMvc.perform(post("/messages?threadNumber=1")
        // params = "postMessage"の呼び出し
        .param("postMessage", "")
        // formに値を設定
        .param("message", "メッセージテスト").param("hasContributorName", "true")
        .param("contributorName", ""))
        // エラーがあることのテスト
        .andExpect(model().hasErrors())
        // リクエスト成功をテスト
        .andExpect(status().isOk())
        // エラーメッセージがあること
        .andExpect(model().attributeExists("errorMessage"))
        // ビュー名をテスト
        .andExpect(view().name("messages"));
  }

  @Test
  public void メッセージ投稿失敗_投稿者フラグがオンで投稿者名が空() throws Exception {
    List<Integer> threadNumberList = Arrays.asList(1, 2, 3);
    when(messageService.getThreadNumberList()).thenReturn(threadNumberList);

    mockMvc.perform(post("/messages?threadNumber=1")
        // params = "postMessage"の呼び出し
        .param("postMessage", "")
        // formに値を設定
        .param("message", "メッセージテスト").param("hasContributorName", "true")
        .param("contributorName", ""))
        // エラーがあることのテスト
        .andExpect(model().hasErrors())
        // リクエスト成功をテスト
        .andExpect(status().isOk())
        // エラーメッセージがあること
        .andExpect(model().attributeExists("errorMessage"))
        // ビュー名をテスト
        .andExpect(view().name("messages"));
  }

}

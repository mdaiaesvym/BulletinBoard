package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.service.MessageService;

@SpringBootTest
@AutoConfigureMockMvc
public class MessagesControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	MessageService messageService;

	@Test
	public void アクセス成功() throws Exception {
		mockMvc.perform(get("/messages/1"))
				//リクエスト成功をテスト
				.andExpect(status().isOk())
				//ビュー名をテスト
				.andExpect(view().name("/messages"))
				//modelに存在することのテスト
				.andExpect(model().attribute("messageList", messageService.getMessageas("1")))
				.andExpect(model().attribute("threadName", messageService.getThreadName("1")))
				.andExpect(model().attribute("threadNumber", "1"));
	}

	@Test
	public void メッセージ投稿成功() throws Exception {
		mockMvc.perform(post("/messages/1")
				//params = "postMessage"の呼び出し
				.param("postMessage", "")
				//formに値を設定
				.param("message", "メッセージテスト")
				.param("checkContributorName", "1")
				.param("contributorName", "投稿者テスト"))
				//エラーがないことのテスト
				.andExpect(model().hasNoErrors())
				//リダイレクトに成功することのテスト
				.andExpect(status().isFound())
				//リダイレクト先URLのテスト
				.andExpect(redirectedUrl("/messages/1"));
	}

	@Test
	public void メッセージ投稿失敗_メッセージが空() throws Exception {
		mockMvc.perform(post("/messages/1")
				//params = "postMessage"の呼び出し
				.param("postMessage", "")
				//formに値を設定
				.param("message", "")
				.param("checkContributorName", "1")
				.param("contributorName", "投稿者テスト"))
				//エラーがあることのテスト	
				.andExpect(model().hasErrors())
				//リクエスト成功をテスト
				.andExpect(status().isOk())
				//ビュー名をテスト
				.andExpect(view().name("/messages"));
	}

	@Test
	public void メッセージ投稿失敗_投稿者が空() throws Exception {
		mockMvc.perform(post("/messages/1")
				//params = "postMessage"の呼び出し
				.param("postMessage", "")
				//formに値を設定
				.param("message", "メッセージテスト")
				.param("checkContributorName", "1")
				.param("contributorName", ""))
				//エラーがあることのテスト	
				.andExpect(model().hasErrors())
				//リクエスト成功をテスト
				.andExpect(status().isOk())
				//ビュー名をテスト
				.andExpect(view().name("/messages"));
	}

}

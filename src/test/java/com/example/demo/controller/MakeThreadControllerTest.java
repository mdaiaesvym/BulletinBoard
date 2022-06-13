package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MakeThreadControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void アクセス成功() throws Exception {
		//アクセス
		mockMvc.perform(get("/makeThread"))
				//リクエスト成功をテスト
				.andExpect(status().isOk())
				//ビュー名をテスト
				.andExpect(view().name("/makeThread"));
	}

	@Test
	public void 新規作成成功() throws Exception {
		//ポスト
		mockMvc.perform(post("/makeThread")
				//params = "makeThread"の呼び出し
				.param("makeThread", "")
				//formに値を設定
				.param("threadName", "スレッド名テスト")
				.param("message", "メッセージテスト")
				.param("checkContributorName", "1")
				.param("contributorName", "投稿者テスト"))
				//エラーがないことのテスト
				.andExpect(model().hasNoErrors())
				//リダイレクトに成功することのテスト
				.andExpect(status().isFound())
				//リダイレクト先URLのテスト
				.andExpect(redirectedUrl("/threads"));
	}

	@Test
	public void 新規作成失敗_スレッド名が空() throws Exception {
		//ポスト
		mockMvc.perform(post("/makeThread")
				//params = "makeThread"の呼び出し
				.param("makeThread", "")
				//formに値を設定
				.param("threadName", "")
				.param("message", "メッセージテスト")
				.param("checkContributorName", "1")
				.param("contributorName", "投稿者テスト"))
				//エラーがあることのテスト
				.andExpect(model().hasErrors())
				//リクエスト成功をテスト
				.andExpect(status().isOk())
				//ビュー名をテスト
				.andExpect(view().name("/makeThread"));
	}

	@Test
	public void 新規作成失敗_メッセージが空() throws Exception {
		//ポスト
		mockMvc.perform(post("/makeThread")
				//params = "makeThread"の呼び出し
				.param("makeThread", "")
				//formに値を設定
				.param("threadName", "スレッド名テスト")
				.param("message", "")
				.param("checkContributorName", "1")
				.param("contributorName", "投稿者テスト"))
				//エラーがあることのテスト
				.andExpect(model().hasErrors())
				//リクエスト成功をテスト
				.andExpect(status().isOk())
				//ビュー名をテスト
				.andExpect(view().name("/makeThread"));
	}

	@Test
	public void 新規作成失敗_投稿者が空() throws Exception {
		//ポスト
		mockMvc.perform(post("/makeThread")
				//params = "makeThread"の呼び出し
				.param("makeThread", "")
				//formに値を設定
				.param("threadName", "スレッド名テスト")
				.param("message", "メッセージテスト")
				.param("checkContributorName", "1")
				.param("contributorName", ""))
				//エラーがあることのテスト
				.andExpect(model().hasErrors())
				//リクエスト成功をテスト
				.andExpect(status().isOk())
				//ビュー名をテスト
				.andExpect(view().name("/makeThread"));
	}
}
package controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.example.demo.BulletinBoardApplication;
import com.example.demo.service.ThreadService;

@SpringBootTest(classes = BulletinBoardApplication.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ThreadListControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  ThreadService threadService;

  @Test
  public void アクセス成功() throws Exception {
    // アクセス
    mockMvc.perform(get("/threadList"))
        // リクエスト成功をテスト
        .andExpect(status().isOk())
        // ビュー名をテスト
        .andExpect(view().name("threadList"))
        // modelに存在することをテスト
        .andExpect(model().attribute("threadList", threadService.getThreadList()));
  }
}

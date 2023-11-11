//初期実行
window.addEventListener("load", function () {
  document.querySelectorAll(".contributorName").forEach((nameArea) => {
    nameArea.addEventListener("change", () => switchingContributorNameArea());
  });
});

//投稿ボタンをクリック
document.querySelector("#makeMessageButton").addEventListener("click", () => {
  //メッセージ取得
  const message = document.querySelector("#postMessage").value;

  if (window.confirm(message)) {
    //フォームをサブミット
    document.querySelector("#makeMessageForm").submit();
  }
});

//投稿者名の表示・非表示
function switchingContributorNameArea() {
  const anonymous = document.querySelector("#anonymous");
  const contributorNameForm = document.querySelector("#contributorNameForm");
  const contributorNameTextArea = document.querySelector("#contributorName");

  if (anonymous.checked) {
    contributorNameForm.setAttribute("hidden", "");
    contributorNameTextArea.value = "";
  } else {
    contributorNameForm.removeAttribute("hidden");
  }
}

//メッセージ入力フォームに返信先の番号を入れ、メッセージ入力フォームにフォーカスする
//htmlから呼び出している
function replay(count) {
  messageArea = document.querySelector("#message");

  //対象のメッセージ番号をメッセージのテキストエリアに入れる
  messageArea.value = ">>" + count + "\n";

  //マウスカーソルをメッセージに合わせる
  messageArea.focus();
}

//メッセージ入力フォームに返信先の番号を入れ、メッセージ入力フォームにフォーカスする
function replay(count) {
  messageArea = document.querySelector("#message");

  //対象のメッセージ番号をメッセージのテキストエリアに入れる
  messageArea.value = ">>" + count + "\n";

  //マウスカーソルをメッセージに合わせる
  messageArea.focus();
}

document.querySelector("#makeMessageButton").addEventListener("click", () => {
  //メッセージ取得
  const message = document.querySelector("#postMessage").value;

  if (window.confirm(message)) {
    //フォームをサブミット
    document.querySelector("#makeMessageForm").submit();
  }
});

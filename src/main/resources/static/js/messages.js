//メッセージ入力フォームに返信先の番号を入れ、メッセージ入力フォームにフォーカスする
function replay(count) {
  messageArea = document.getElementById("message");

  //対象のメッセージ番号をメッセージのテキストエリアに入れる
  messageArea.value = ">>" + count + "\n";

  //マウスカーソルをメッセージに合わせる
  messageArea.focus();
}

function replay(count){
  messageArea = document.getElementById("message")

  //対象のメッセージ番号をメッセージのテキストエリアに入れる
  messageArea.value=">>" + count + "\n"

  //マウスカーソルをメッセージに合わせる
  messageArea.focus()
}
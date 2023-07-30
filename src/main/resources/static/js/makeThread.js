document.querySelector("#makeThreadButton").addEventListener("click", () => {
  //メッセージ取得
  const message = document.querySelector("#postMessage").value;

  if (window.confirm(message)) {
    //フォームをサブミット
    document.querySelector("#makeThreadForm").submit();
  }
});

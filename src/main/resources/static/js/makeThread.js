//初期実行
window.addEventListener("load", function () {
  document.querySelectorAll(".contributorName").forEach((nameArea) => {
    nameArea.addEventListener("change", () => switchingContributorNameArea());
  });
});

//作成ボタンをクリック
document.querySelector("#makeThreadButton").addEventListener("click", () => {
  //メッセージ取得
  const message = document.querySelector("#postMessage").value;

  if (window.confirm(message)) {
    //フォームをサブミット
    document.querySelector("#makeThreadForm").submit();
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

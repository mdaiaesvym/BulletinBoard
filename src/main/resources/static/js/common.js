//初期表示
window.addEventListener("DOMContentLoaded", function () {
  contributorNameOpenHidden();
});

//投稿者名の表示・非表示
function contributorNameOpenHidden() {
  var anonymous = document.getElementById("anonymous");
  var contributorNameForm = document.getElementById("contributorNameForm");
  var contributorNameTextArea = document.getElementById("contributorName");

  if (anonymous.checked) {
    contributorNameForm.setAttribute("hidden", "");
    contributorNameTextArea.value = "";
  } else {
    contributorNameForm.removeAttribute("hidden");
    contributorNameForm.style.display = "";
  }
}

//全角スペースを半角スペースに置換
function changeSpace() {
  changeTexts = document.querySelectorAll(".change-space");
  for (const changeText of changeTexts) {
    changeText.value = changeText.value.replace(/　/g, " ");
  }
}

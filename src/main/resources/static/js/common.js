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
    contributorNameForm.style.display = "none";
    contributorNameTextArea.value = "";
  } else {
    contributorNameForm.style.display = "";
  }
}

//匿名で投稿の場合、投稿者名に「匿名」を入れる
function setAnonymous() {
  var checkContributorName = document.querySelector("input[name=checkContributorName]:checked");

  if (checkContributorName.value === "0") {
    document.getElementById("contributorName").value = "匿名";
  }
}

//全角スペースを半角スペースに置換
function changeSpace() {
  changeTexts = document.querySelectorAll(".change-space");
  console.log(changeTexts);
  for (const changeText of changeTexts) {
    changeText.value = changeText.value.replace(/　/g, " ");
  }
}

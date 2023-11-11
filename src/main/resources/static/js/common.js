//初期実行
window.addEventListener("DOMContentLoaded", function () {
  const anonymous = document.querySelector("#anonymous");
  anonymous.addEventListener("change", () => contributorNameOpenHidden());
  const onymous = document.querySelector("#onymous");
  onymous.addEventListener("change", () => contributorNameOpenHidden());
});

//投稿者名の表示・非表示
function contributorNameOpenHidden() {
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

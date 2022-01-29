//初期表示
window.onload = function(){
  contributorNameOpenHidden();
}

//投稿者名の表示・非表示
function contributorNameOpenHidden(){

  var anonymous = document.getElementById("anonymous");
  var contributorNameForm = document.getElementById("contributorNameForm");
  var contributorNameTextArea = document.getElementById("contributorName");

  if(anonymous.checked){
    contributorNameForm.style.display = "none";
    contributorNameTextArea.value = "";
  }else{
    contributorNameForm.style.display = "";
  }
}

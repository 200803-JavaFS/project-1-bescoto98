document.getElementById('loginBtn').addEventListener("click",loginfunc);

async function loginfunc(){

  let u = document.getElementById("username").value;
  let p = document.getElementById("password").value;

  //send message
  //if successful which we Are assuming
  window.location.replace("userPage.html");
}

const url = "http://localhost:8080/project1/"

// let userdto = null;

async function loginfunc(){

  let u = document.getElementById("username").value;
  let p = document.getElementById("password").value;

  //send message
  let user = {
    username : u,
    password : p
  }

  let resp = await fetch(url + "login",
    {
      method:'POST',
      body: JSON.stringify(user),
      credentials: "include"
    });

  if(resp.status === 200){

    let userdto = await getUserDTO();

    console.log(userdto);
    if(userdto.role == 0){
      window.location.replace("adminPage.html");
    }
    else if(userdto.role == 1){
      window.location.replace("userPage.html");
    }

  }
}

async function getUserDTO(){
  let getCred = await fetch(url + "login",
  {
    credentials: 'include'
  });

  if(getCred.status === 200){
    let data = await getCred.json();
    console.log(data);
    return data;

  }
}

async function getAll(){

  let resp = await fetch(url + "reimbursement",{
    credentials:'include'
  });

  if(resp.status === 200){
    let data = await resp.json();
    console.log(data);
  }
}

async function getUsersName(){
  console.log("getting name");
  let userdto = await getUserDTO();
  document.getElementById("greeting").innerText = "Hello " + userdto.name;
}

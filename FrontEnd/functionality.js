const url = "http://localhost:8080/project1/"

function translateStatus(s){
  if(s == 1){
    return "PENDING";
  }
  if(s == 2){
    return "APPROVED";
  }
  if(s == 3){
    return "DENIED";
  }
}

function showPending(){
  let status = document.getElementById("pendingbool");
  let tbl = document.getElementById("allReimb");
  let rows = tbl.rows;

  if(status.checked){
      for(let i = 1; i < rows.length; i++){
        if(rows[i].getElementsByTagName("td")[5].innerText.includes("APPROVED")
        || rows[i].getElementsByTagName("td")[5].innerText.includes("DENIED")){
        rows[i].style.display = "none";
      }
    }
  }
  else {
    for(let i = 1; i < rows.length; i++){
      rows[i].style.display = "table-row";
    }

  }

}

function showApproved(){

}

function showDenied(){

}


// function to kick off login services
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

// will return an object with most important user information
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

//return all reimbursement tickets
async function getAll(){

  let resp = await fetch(url + "reimbursement",{
    credentials:'include'
  });

  if(resp.status === 200){
    let data = await resp.json();
    console.log(data);

    let btn = document.getElementById("showAllBtn");
    btn.style.display = "none";

    let index = 1;

    let tbl = document.getElementById("allReimb");

    for(let reimbursement of data){

      let row = tbl.insertRow(index);
      let c0 = row.insertCell(0);
      let c1 = row.insertCell(1);
      let c2 = row.insertCell(2);
      let c3 = row.insertCell(3);
      let c4 = row.insertCell(4);
      let c5 = row.insertCell(5);

      c0.innerHTML = reimbursement.r_id;
      c1.innerHTML = reimbursement.r_amnt;
      c2.innerHTML = reimbursement.r_submitted;
      if(reimbursement.r_resolved != null){
          c3.innerHTML = reimbursement.r_resolved;
      }
      c4.innerHTML = reimbursement.r_author;
      c5.innerHTML = translateStatus(reimbursement.r_status);

      index++;
    }
  }
}


async function getUsersName(){
  console.log("getting name");
  let userdto = await getUserDTO();
  document.getElementById("greeting").innerText = "Hello " + userdto.name;
}

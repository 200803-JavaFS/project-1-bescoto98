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

    let status = document.getElementById("approvedbool");
    let tbl = document.getElementById("allReimb");
    let rows = tbl.rows;

    if(status.checked){
        for(let i = 1; i < rows.length; i++){
          if(rows[i].getElementsByTagName("td")[5].innerText.includes("PENDING")
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

function showDenied(){
  let status = document.getElementById("deniedbool");
  let tbl = document.getElementById("allReimb");
  let rows = tbl.rows;

  if(status.checked){
      for(let i = 1; i < rows.length; i++){
        if(rows[i].getElementsByTagName("td")[5].innerText.includes("APPROVED")
        || rows[i].getElementsByTagName("td")[5].innerText.includes("PENDING")){
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

async function logout(){
  let resp = await fetch(url+"logout",{
    credentials:'include'
  });
  window.location.replace("login.html");
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

function getToday(){
  let d = new Date();
  let fullDay = d.getMonth() + "-" + d.getDate() + "-" + d.getFullYear();
  // console.log(fullDay);
  return fullDay;
}

async function updateTicket(){
  let r_id = document.getElementById("r_id");

  let ticket = await getTicket(r_id.value);

  ticket.r_resolved = getToday();

  let resolver = await getUserDTO();
  ticket.r_resolver = resolver.id;

  let status = document.getElementById("approveOrDeny");
  if(status.checked){
    ticket.r_status = 3;
  }
  else{
    ticket.r_status = 2;
  }

  console.log(ticket);

  let resp = await fetch(url + "reimbursement",
    {
      method:'PUT',
      body:JSON.stringify(ticket),
      credentials: "include"});

  if(resp.status === 202){
    console.log("added ticket");
    //get rid of submit button
  }


}

async function getTicket(id){
  let resp = await fetch(url+"reimbursement/"+id,{
    credentials: 'include'
  });

  if(resp.status === 200){
    let data = await resp.json();

    return data;
    // console.log(data);
  }
}

async function getUsersName(){
  console.log("getting name");
  let userdto = await getUserDTO();
  document.getElementById("greeting").innerText = "Hello " + userdto.name;
}
// employee functions
async function showMyTickets(){


}

async function addTicket(){

  let amnt = document.getElementById("r_amount").value;
  let description = document.getElementById("r_description").value;
  let type = document.getElementById("r_type").value;

  let submitted = getToday();

  let status = 1;

  let employee = await getUserDTO();
  let author = employee.id;

  let reimbursement = {
    r_amnt : amnt,
    r_description : description,
    r_type : type,
    r_submitted : submitted,
    r_status : status,
    r_author : author
  }

  console.log(reimbursement);

  let resp = await fetch(url + "reimbursement",
  {
    method: 'POST',
      body: JSON.stringify(reimbursement),
      credentials: 'include'});

  if(resp.status === 201){
    console.log("reimbursement added");
  }

}

const url = "http://localhost:8080/project1/"

/** HELPER FUNCTIONS **/
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

function translateType(t){
  if(t == 1){
    return "LODGING";
  }
  if(t == 2){
    return "TRAVEL";
  }
  if(t == 3){
    return "FOOD";
  }
  if(t == 4){
    return "OTHER";
  }

}

function getToday(){

  let d = new Date();
  let fullDay = d.getMonth() + "-" + d.getDate() + "-" + d.getFullYear();

  return fullDay;
}
/** END HELPER FUNCTIONS **/

/** FILTER FUNCTIONS **/
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
/** END FILTER FUNCTIONS**/


/** SHARED FUNCTIONS **/
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

    if(userdto.role == 0){
      window.location.replace("adminPage.html");
    }
    else if(userdto.role == 1){
      window.location.replace("userPage.html");
    }

  }
  else if (resp.status === 401){
    let lMsg = document.getElementById("loginMessage");
    lMsg.style = "background-color:#ff9999;";
    lMsg.innerHTML = "Invalid login";

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

    return data;

  }
}

async function logout(){
  let resp = await fetch(url+"logout",{
    credentials:'include'
  });
  window.location.replace("login.html");
}

async function getUsersName(){
  let userdto = await getUserDTO();
  document.getElementById("greeting").innerText = "Hello, " + userdto.name;
}

async function getTicket(id){
  let resp = await fetch(url+"reimbursement/"+id,{
    credentials: 'include'
  });

  if(resp.status === 200){
    let data = await resp.json();

    return data;
  }
}

async function showMyTickets(){

  if(document.contains(document.getElementById("errorMsg"))){
    document.getElementById("errorMsg").remove();
  }
  
  let tickId = document.getElementById("ticketIdNum").value;
  let ticket = await getTicket(tickId);

  let user = await getUserDTO();

  if(ticket == null){
    let errorMsg = document.createElement("div");
    errorMsg.innerHTML = '<h3 class="centered" style="color:red;" id="errorMsg">Unable to access that ticket</h3>';
    document.body.appendChild(errorMsg);

    return;
  }

  if(ticket.r_author == user.id || user.role == 0){

    // window.location.replace("ticketPreview.html");
    let t = document.getElementById("ticketPreview");
    t.hidden=false;

    document.getElementById("idNum").innerHTML = "Ticket ID#" + ticket.r_id;
    document.getElementById("amount").innerHTML = "$" + ticket.r_amnt;
    document.getElementById("submitted").innerHTML = "Submission Date: " + ticket.r_submitted;
    if(ticket.r_resolved !=  null){
      document.getElementById("resolved").innerHTML = "Resolution Date: " + ticket.r_resolved;
      document.getElementById("resolver").innerHTML = "Resolved by Admin #"+ticket.r_resolver;
    }
    else{
      document.getElementById("resolved").innerHTML = "Resolution Date: Ticket not resolved yet";
      document.getElementById("resolver").innerHTML = "Resolved by: Ticket not resolved yet";
    }

    document.getElementById("status").innerHTML = "Ticket status: " + translateStatus(ticket.r_status);
    document.getElementById("type").innerHTML = "Reimbursement type: " + translateType(ticket.r_type);
    document.getElementById("description").innerHTML = "Description: " + ticket.r_description;

  }


}

/** END SHARED FUNCTIONS **/

/** ADMIN FUNCTIONS **/

//return all reimbursement tickets
async function getAll(){

  let resp = await fetch(url + "reimbursement",{
    credentials:'include'
  });

  if(resp.status === 200){
    let data = await resp.json();

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
      c1.innerHTML = "$" + reimbursement.r_amnt;
      c2.innerHTML = reimbursement.r_submitted;
      if(reimbursement.r_resolved != null){
          c3.innerHTML = reimbursement.r_resolved;
      }
      else{
        c3.innerHTML = "-"
      }
      c4.innerHTML = reimbursement.r_author;
      c5.innerHTML = translateStatus(reimbursement.r_status);

      index++;
    }
  }
}

async function updateTicket(){
  document.getElementById("submitBtn").hidden = true;
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

  let resp = await fetch(url + "reimbursement",
    {
      method:'PUT',
      body:JSON.stringify(ticket),
      credentials: "include"});

  if(resp.status === 202){

    let msg = document.getElementById("message");
    msg.style = "color:green;";
    msg.innerHTML = "Ticket successfully updated!";

  }


}

/** END ADMIN FUNCTIONS **/

/** EMPLOYEE FUNCTIONS **/
async function addTicket(){

  document.getElementById("addBtn").hidden = true;

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

  let resp = await fetch(url + "reimbursement",
  {
    method: 'POST',
      body: JSON.stringify(reimbursement),
      credentials: 'include'});

  if(resp.status === 201){
    let msg = document.getElementById("message");
    msg.style = "color:green;";
    msg.innerHTML = "Ticket successfully added!";
  }

}

/** END EMPLOYEE FUNCTIONS **/

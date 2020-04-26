window.onload = init;
var form;

function init() {
  alignImage();
  form = document.forms["user-form"];
}

var adminData = null;

function changePassword(admin) {
  adminData = JSON.parse(admin);
}

function startEdit(admin) {
  adminData = JSON.parse(admin);
  for (var i = 0; i < form.length; i++) {
    if (form[i].name != "id") form[i].disabled = false;
  }
  $("#edit-button")[0].disabled = true;
  $("#password-button")[0].disabled = true;
  $("#submit-button")[0].hidden = false;
  $("#cancel-button")[0].hidden = false;
}
function alignImage() {
  let imgProfile = document
    .getElementById("profile-picture")
    .getElementsByTagName("img")[0];
  height = imgProfile.height;
  width = imgProfile.width;
  if (height < width) {
    imgProfile.style.width = "100%";
    imgProfile.style.height = "auto";
    height = imgProfile.height / 2;
    imgProfile.style.top = "50%";
    imgProfile.style.marginTop = `-${height}px`;
  }
  if (height > width) {
    imgProfile.style.height = "100%";
    imgProfile.style.width = "auto";
    width = imgProfile.width / 2;
    imgProfile.style.left = "50%";
    imgProfile.style.marginLeft = `-${width}px`;
  }
}
function resetForm() {
  form.classList.remove("was-validated");
  for (var i = 0; i < form.length; i++) {
    if (form[i].name != "id") form[i].disabled = true;
  }
  $("#edit-button")[0].disabled = false;
  $("#password-button")[0].disabled = false;
  $("#submit-button")[0].hidden = true;
  $("#cancel-button")[0].hidden = true;
}

function resetValues() {
  form["firstName"].value = adminData.firstName;
  form["lastName"].value = adminData.lastName;
  form["email"].value = adminData.email;
  resetForm();
}

function submitAdmin() {
  var http = new XMLHttpRequest();
  var url = apiUrl + "/api/user";
  http.open("PUT", url, true);

  http.onreadystatechange = function() {
    if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
      location.reload();
    }
  };
  var formData = new FormData();
  formData.append("id", adminData.id);
  formData.append("username", adminData.username);
  formData.append("firstName", form["firstName"].value);
  formData.append("lastName", form["lastName"].value);
  formData.append("email", form["email"].value);
  http.send(formData);
}

function validateForm(event) {
  event.preventDefault();
  let validForm = true;
  if (!form["firstName"].validity.valid) {
    validForm = false;
    form["firstName"].parentNode.className = "invalid";
  }
  if (!form["lastName"].validity.valid) {
    validForm = false;
    form["lastName"].parentNode.className = "invalid";
  }
  if (!form["email"].validity.valid) {
    validForm = false;
    form["email"].parentNode.className = "invalid";
  }
  if (validForm) {
    submitAdmin();

  }
}

function validateInput(event){
  if(event.target.validity.valid){
    event.target.parentNode.classList.remove("invalid")
  }
}
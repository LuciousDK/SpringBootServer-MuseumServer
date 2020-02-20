window.onload = alignImage;

var adminData = null;

function changePassword(admin) {
  adminData = JSON.parse(admin);
}

function startEdit(admin) {
  adminData = JSON.parse(admin);
  $("#edit-button")[0].disabled = true;
  $("#password-button")[0].disabled = true;
  $("#submit-button")[0].hidden = false;
  $("#cancel-button")[0].hidden = false;

  var form = document.forms["administrator-form"];

  for (var i = 0; i < form.length; i++) {
    if (form[i].name != "id") form[i].disabled = false;
  }
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
  var form = document.forms["administrator-form"];
  form.classList.remove("was-validated");
  form["firstName"].value = adminData.firstName;
  form["lastName"].value = adminData.lastName;
  form["email"].value = adminData.email;
  for (var i = 0; i < form.length; i++) {
    if (form[i].name != "id") form[i].disabled = true;
  }
  $("#edit-button")[0].disabled = false;
  $("#password-button")[0].disabled = false;
  $("#submit-button")[0].hidden = true;
  $("#cancel-button")[0].hidden = true;
}

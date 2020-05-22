$(document).ready(function () {
  $("#search-bar").on("keyup", function () {
    var value = $(this).val().toLowerCase();
    $("#exhibitions-table tr").filter(function () {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
    });
  });
});

function startCreation() {
  resetForm();
  var form = document.forms["exhibition-form"];
  form["id"].value = null;
  form["name"].value = "";
  form["openingDate"].value = "";
  form["closingDate"].value = "";
  form["location"].value = "";
}

function editExhibition(exhibition) {
  resetForm();
  var exhibitionJSON = JSON.parse(exhibition);
  var form = document.forms["exhibition-form"];
  form["id"].value = exhibitionJSON.id;
  form["name"].value = exhibitionJSON.name;

  if (exhibitionJSON.openingDate == "null") form["openingDate"].value = "";
  else form["openingDate"].value = exhibitionJSON.openingDate;

  if (exhibitionJSON.closingDate == "null") form["closingDate"].value = "";
  else form["closingDate"].value = exhibitionJSON.closingDate;

  if (exhibitionJSON.location == "null") form["location"].value = "";
  else form["location"].value = exhibitionJSON.location;
}

/**
 * Sends a request to server to create a new exhibition if id == null
 * Else updates existing exhibition with that id
 */
function submitExhibition() {
  event.preventDefault();
  var form = $('form[name="exhibition-form"]');
  var data = getFormDataJSON(form);
  var http = new XMLHttpRequest();
  var url = apiUrl + "/api/exhibition";
  var formData = new FormData();
  $.each(data, function (key, value) {
    if (value != "") formData.append(key, value);
  });
  http.open("POST", url, true);
  http.send(formData);

  //If request succesful reload page to display changes
  http.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      location.reload();
    }
  };
}
function resetForm() {
  document.forms["exhibition-form"].classList.remove("was-validated");
}

function changeTab(evt, tabName) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(tabName).style.display = "block";
  evt.currentTarget.className += " active";
}

function changeSideTab(evt, sideTabName) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("side-tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("side-tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(sideTabName).style.display = "block";
  evt.currentTarget.className += " active";
}

function allowDrop(ev) {
  if (ev.target.id == "assigned-media" || ev.target.id == "unassigned-media") {
    ev.preventDefault();
  }
}

function drag(ev) {
  ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
  ev.preventDefault();
  var data = ev.dataTransfer.getData("text");
  var card = document.getElementById(data);
  ev.target.appendChild(card);
  if (ev.target.id == "assigned-media") {
    var exhibitionId = card.id.split("-")[1];
    var mediaId = card.id.split("-")[2];
    addMedia(exhibitionId, mediaId);
  }
  if (ev.target.id == "unassigned-media") {
    var exhibitionId = card.id.split("-")[1];
    var mediaId = card.id.split("-")[2];
    removeMedia(exhibitionId, mediaId);
  }
}

function removeMedia(exhibitionId, mediaId) {
  var http = new XMLHttpRequest();
  var url = apiUrl + "/api/exhibition/removeMedia";
  var formData = new FormData();

  formData.append("exhibitionId", exhibitionId);
  formData.append("mediaId", mediaId);
  http.open("POST", url, true);
  http.send(formData);
}

function addMedia(exhibitionId, mediaId) {
  var http = new XMLHttpRequest();
  var url = apiUrl + "/api/exhibition/addMedia";
  var formData = new FormData();

  formData.append("exhibitionId", exhibitionId);
  formData.append("mediaId", mediaId);
  http.open("POST", url, true);
  http.send(formData);
}

function toggleExhibition(id){
  var http = new XMLHttpRequest();
  var url = apiUrl + "/api/exhibition/toggle";
  var formData = new FormData();
  formData.append("id", id);
  http.open("POST", url, true);
  http.send(formData);

  //If request succesful reload page to display changes
  http.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      location.reload();
    }
  };
}
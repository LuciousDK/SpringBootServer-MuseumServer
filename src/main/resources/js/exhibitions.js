$(document).ready(function() {
  $("#search-bar").on("keyup", function() {
    var value = $(this)
      .val()
      .toLowerCase();
    $("#exhibitions-table tr").filter(function() {
      $(this).toggle(
        $(this)
          .text()
          .toLowerCase()
          .indexOf(value) > -1
      );
    });
  });
});

function newExhibition() {
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

function submitForm(event) {
  event.preventDefault();

  if (document.forms["exhibition-form"]["id"].value == "") {
    submitExhibition();
  } else {
    updateExhibition();
  }
}

function updateExhibition() {
  var form = document.forms["exhibition-form"];
  var http = new XMLHttpRequest();
  var url = apiUrl + "/api/exhibition";
  http.open("PUT", url, true);

  http.onreadystatechange = function() {
    if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
      location.reload();
    }
  };
  var formData = new FormData();
  formData.append("id", form["id"].value);
  formData.append("name", form["name"].value);
  if (form["openingDate"].value != "")
    formData.append("openingDate", form["openingDate"].value);
  if (form["closingDate"].value != "")
    formData.append("closingDate", form["closingDate"].value);
  formData.append("location", form["location"].value);
  http.send(formData);
}

function submitExhibition() {
  var form = document.forms["exhibition-form"];
  var http = new XMLHttpRequest();
  var url = apiUrl + "/api/exhibition";
  http.open("POST", url, true);

  http.onreadystatechange = function() {
    if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
      location.reload();
    }
  };
  var formData = new FormData();
  formData.append("name", form["name"].value);
  formData.append("openingDate", form["openingDate"].value);
  formData.append("closingDate", form["closingDate"].value);
  formData.append("location", form["location"].value);
  http.send(formData);
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

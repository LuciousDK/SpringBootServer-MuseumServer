$(document).ready(function () {
  $("#search-bar").on("keyup", function () {
    var value = $(this).val().toLowerCase();
    $("#artworks-table tr").filter(function () {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
    });
  });
});

function editArtwork(artwork) {
  resetForm();
  var artworkJSON = JSON.parse(artwork);
  var form = document.forms["artwork-form"];
  form["id"].value = artworkJSON.id;
  form["name"].value = artworkJSON.name;

  if (artworkJSON.author == "null") form["author"].value = "";
  else form["author"].value = artworkJSON.author;

  if (artworkJSON.country == "null") form["country"].value = "";
  else form["country"].value = artworkJSON.country;

  if (artworkJSON.description == "null") form["description"].value = "";
  else form["description"].value = artworkJSON.description;

  form["exhibitionId"].value = artworkJSON.exhibitionId;
}

function resetForm() {
  var form = document.forms["artwork-form"];
  form["id"].value = null;
  form["name"].value = "";
  form["author"].value = "";
  form["country"].value = "";
  form["description"].value = "";
  form["exhibitionId"].value = "";
  document.forms["artwork-form"].classList.remove("was-validated");
}

async function submitArtwork() {
  event.preventDefault();
  var form = $('form[name="artwork-form"]');
  var data = getFormDataJSON(form);
  var http = new XMLHttpRequest();
  var url = apiUrl + "/api/artwork";
  var formData = new FormData();
  $.each(data, function (key, value) {
    formData.append(key, value);
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

async function toggleArtwork(id) {
  var http = new XMLHttpRequest();
  var url = apiUrl + "/api/artwork/toggle";
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

async function removeArtwork() {
  event.preventDefault();
  if (window.confirm("¿Estás seguro?")) {
    var form = $('form[name="artwork-form"]');
    var id = getFormDataJSON(form).id;
    var http = new XMLHttpRequest();
    var url = apiUrl + "/api/artwork/delete";
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
}

function removeMedia(artworkId, mediaId) {
  var http = new XMLHttpRequest();
  var url = apiUrl + "/api/artwork/removeMedia";
  var formData = new FormData();

  formData.append("artworkId", artworkId);
  formData.append("mediaId", mediaId);
  http.open("POST", url, true);
  http.send(formData);
}

function addMedia(artworkId, mediaId) {
  var http = new XMLHttpRequest();
  var url = apiUrl + "/api/artwork/addMedia";
  var formData = new FormData();

  formData.append("artworkId", artworkId);
  formData.append("mediaId", mediaId);
  http.open("POST", url, true);
  http.send(formData);
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
    var artworkId = card.id.split("-")[1];
    var mediaId = card.id.split("-")[2];
    addMedia(artworkId, mediaId);
  }
  if (ev.target.id == "unassigned-media") {
    var artworkId = card.id.split("-")[1];
    var mediaId = card.id.split("-")[2];
    removeMedia(artworkId, mediaId);
  }
}

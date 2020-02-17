$(document).ready(function() {
  $("#search-bar").on("keyup", function() {
    var value = $(this)
      .val()
      .toLowerCase();
    $("#medias-table tr").filter(function() {
      $(this).toggle(
        $(this)
          .text()
          .toLowerCase()
          .indexOf(value) > -1
      );
    });
  });
});
function submitForm(ev) {
  ev.preventDefault();
  var form = document.forms["media-form"];

  var fileName = form["file"].files[0].name.split(".")[0];
  var extension = form["file"].files[0].name.split(".")[1];
  var displayName = form["name"].value;
  var fileType = "";
  if (form["file"].files[0].type.includes("image")) {
    fileType = "image";
  }
  if (form["file"].files[0].type.includes("video")) {
    fileType = "video";
  }
  if (form["file"].files[0].type.includes("audio")) {
    fileType = "audio";
  }

  var formData = new FormData();
  formData.append("fileName", fileName);
  formData.append("fileType", fileType);
  formData.append("extension", extension);
  formData.append("displayName", displayName);

  formData.append("file", form["file"].files[0], form["file"].files[0].name);

  uploadMedia(formData);
}

function uploadMedia(formData) {
  var http = new XMLHttpRequest();
  var url = apiUrl + "/media";
  http.open("POST", url, true);

  http.onreadystatechange = function() {
    if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
      location.reload();
    }
  };

  http.send(formData);
}

function deleteMedia(id) {
  var http = new XMLHttpRequest();
  var url = apiUrl + "/media";
  http.open("DELETE", url, true);

  http.onreadystatechange = function() {
    if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
      location.reload();
    }
  };

  var formData = new FormData();
  formData.append("id", id);
  http.send(formData);
}

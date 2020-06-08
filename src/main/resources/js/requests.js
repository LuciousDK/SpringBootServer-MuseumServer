async function requestArtworks(callback) {
  $.get("api/artworks", (result) => {
    let artworkList = {};
    result.forEach((element) => {
      artworkList[element.id] = element;
    });
    callback(artworkList);
  });
}
async function submitArtwork(artwork) {
  let data = {};
  if (artwork.id) {
    data.id = artwork.id;
  }
  data.name = artwork.name;
  data.author = artwork.author;
  data.description = artwork.description;
  data.country = artwork.country;
  if (artwork.exhibition != null) {
    data.exhibitionId = artwork.exhibition.id;
  }
  $.post("api/artwork", data).done(() => {
    console.log("Obra actualizada");
  });
}
async function changeArtworkState(id, callback) {
  $.post("api/artwork/toggle", { id: id }).done(() => {
    callback();
  });
}

/**
 * API Requests For Files Management
 */
async function uploadFile(data, callback) {
  let formData = new FormData();
  for (var key in data) {
    formData.append(key, data[key]);
  }
  fetch("api/media", { method: "POST", body: formData }).then(callback());
}

async function requestFiles(callback) {
  $.get("api/medias", (result) => {
    let mediaList = {};
    result.forEach((element) => {
      mediaList[element.id] = element;
    });
    callback(mediaList);
  });
}

async function nameAvailable(title, type) {
  let availability = null;
  await $.get(`api/media/title/${title}`, async (result) => {
    let mediaList = result;
    let valid = true;
    if (mediaList.length >= 0) {
      mediaList.forEach((element) => {
        if (type.localeCompare(element.fileType) == 0) {
          valid = false;
        }
      });
    }
    availability = valid;
  });
  return availability;
}

async function deleteFile(id, callback) {
  let request = new XMLHttpRequest();

  var formData = new FormData();
  formData.append("id", id);

  request.open("DELETE", "api/media");
  request.onreadystatechange = function () {
    if (request.readyState == 4 && request.status == 200) {
      callback();
    }
  };
  request.send(formData);
}

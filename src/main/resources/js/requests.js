async function requestArtworks(callback) {
  let request = new XMLHttpRequest();
  request.open("GET", "api/artworks", true);
  request.onreadystatechange = function () {
    if (request.readyState == 4 && request.status == 200) {
      let artworkList = {};
      JSON.parse(request.responseText).forEach((element) => {
        artworkList[element.id] = element;
      });
      callback(artworkList);
    }
  };
  request.send();
}
async function submitArtwork(artwork) {
  
  var formData = new FormData();
  formData.append("id", artwork.id);
  formData.append("name",artwork.name);
  formData.append("author", artwork.author);
  formData.append("description",artwork.description);
  formData.append("country", artwork.country);
  if (artwork.exhibition != null) {
    
  formData.append("exhibitionId",artwork.exhibition.id);
  };
  let request = new XMLHttpRequest();
  request.open("POST", "api/artwork");
  request.onreadystatechange = function () {
    if (request.readyState == 4 && request.status == 200) {
      console.log("obra actualizada");
    }
  };
  request.send(formData);   
}
async function changeArtworkState(id,callback) {
  
  var formData = new FormData();
  formData.append("id", id);
  let request = new XMLHttpRequest();
  request.open("POST", "api/artwork/toggle");
  request.onreadystatechange = function () {
    if (request.readyState == 4 && request.status == 200) {
      callback()
    }
  };
  request.send(formData);   
}
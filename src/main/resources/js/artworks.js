$(document).ready(function(){
    $("#search-bar").on("keyup", function() {
      var value = $(this).val().toLowerCase();
      $("#artworks-table tr").filter(function() {
        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
      });
    });
  });

function editArtwork(artwork){
 var artworkJSON = JSON.parse(artwork);
 var form = document.forms["artwork-form"];
 form["id"].value = artworkJSON.id;
 form["name"].value = artworkJSON.name;

 if(artworkJSON.author=="null")
  form["author"].value = "";
 else
  form["author"].value = artworkJSON.author;

 if(artworkJSON.country=="null")
  form["country"].value = "";
 else
 form["country"].value = artworkJSON.country;

 if( artworkJSON.description=="null")
  form["description"].value ="";
 else
  form["description"].value= artworkJSON.description;

 form["exhibitionId"].value = artworkJSON.exhibitionId;
 
}
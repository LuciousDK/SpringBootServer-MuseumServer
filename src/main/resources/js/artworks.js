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
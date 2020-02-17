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

function submitForm(){
    var form = document.forms['media-form'];
    console.log(form['file'])
}
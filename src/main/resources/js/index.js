let artworks = [];
let files = [];
let search = null
$("*").click(function () {
  event.stopPropagation();
});
function selectTab(tabName) {
  if ($(event.target).is(".tab-item:not(.selected)")) {
    $(".tab-item").removeClass("selected");
    $(event.target).addClass("selected");
    let search = null;
    switch (tabName) {
      case "artworks":
        $("#content-body").load("artworks", getArtworks);
        break;
      case "files":
        $("#content-body").load("files", getFiles);

        break;
      default:
        break;
    }
  }
}

function toggleCard() {
  let card = $(event.target).parents(".card");

  if (card.hasClass("toggled")) {
    card.removeClass("toggled");
  } else {
    card.addClass("toggled");
  }
}

(function ($) {
  $.fn.setCursorToTextEnd = function () {
    $initialVal = this.val();
    this.val($initialVal + " ");
    this.val($initialVal);
  };
})(jQuery);

function openModal() {
  $("#main-modal").addClass("active");
  $("#main-modal").removeClass("inactive");
}
function closeModal() {
  if (event) event.stopPropagation();
  $("#main-modal").removeClass("active");
  $("#main-modal").addClass("closing");
  setTimeout(() => {
    $("#main-modal").removeClass("closing");
    $("#main-modal").addClass("inactive");
    $("#main-modal .modal-body").html("");
    $("#main-modal .modal-header .title").text("");
    $("#main-modal .modal-footer").html("");
  }, 150);
}
function openImageInModal(event, title) {
  let image = $(event.target);
  let modalBody = $("#main-modal .modal-body");
  let modalHeader = $("#main-modal .modal-header");

  modalBody.append(`<img src="${image.attr("src")}">`);
  if (title) {
    modalHeader.find(".title").text(title);
  } else {
    modalHeader.find(".title").text(image.attr("src"));
  }
  openModal();
}

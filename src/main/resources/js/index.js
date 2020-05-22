let data = {};

$("*").click(function() {
  event.stopPropagation();
});
function selectTab(tabName) {
  if ($(event.target).is(".tab-item:not(.selected)")) {
    $(".tab-item").removeClass("selected");
    $(event.target).addClass("selected");
    switch (tabName) {
      case "artworks":
        $("#content-body").load("artworks");
        getArtworks();
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

    setTimeout(function () {
      card.get(0).scrollIntoView();
    }, 175);
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
  event.stopPropagation();
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
function openImageInModal(event,title) {
  let image = $(event.target);
  let modalBody = $("#main-modal .modal-body");
  let modalHeader = $("#main-modal .modal-header");
  

  modalBody.append(`<img src="${image.attr("src")}">`);
  modalBody
    .find("img")
    .css({
      display: "block",
      "max-height": "calc(85vh - 40px)",
      "max-width": "calc(85vw - 40px)",
    });
  if (title) {
    modalHeader.find(".title").text(title)
  }
  else{
    modalHeader.find(".title").text(image.attr("src"))
  }
  openModal();
}

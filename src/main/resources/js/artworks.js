function createCards(dataset) {
  $("#cards-container").html("");
  artworks = dataset;
  for (var id in artworks) {
    $("#cards-container").append(artworkCard(artworks[id]));
  }
}
function artworkCard(data) {
  let card = $(`<div class="card artwork" id="artwork-${data.id}"></div>`);
  let cardHeader = $(
    `<div class="card-header row" onclick="toggleCard()">
        <div class="card-header-id">${data.id}</div>
        <div class="card-header-name">${data.name}</div>
        <div class="card-header-author">-&nbsp;&nbsp;&nbsp;&nbsp;${data.author}</div>
    </div>`
  );
  let statusIcon;
  switch (data.state.name) {
    case "ACTIVE":
      statusIcon = $(`<img src="assets/green-light.png">`);
      break;
    case "INACTIVE":
      statusIcon = $(`<img src="assets/red-light.png">`);
      break;
    default:
      break;
  }
  if (statusIcon != null) {
    statusIcon.addClass("state-icon");
    cardHeader.append(statusIcon);
  }
  if (data.exhibition == null) {
    data.exhibition = {};
    data.exhibition.name = "No Asignado";
  }
  let cardBody = $(
    `<div class="card-body row">
        <div class="card-image">
            <img src="assets/empty-image.png" alt="Image Not Found">
        </div>

        <div class="card-description column">
            <h1>Descripción</h1>
            <div class="row description">
                <textarea name="description" spellcheck="false"
                    disabled>${data.description}</textarea>
                <div class="text-buttons column">
                    <div class="edit-button"><img onclick="editArtworkTextarea('${data.id}')" src="assets/edit-icon.png"></div>
                </div>
            </div>
        </div>
        <div class="card-data row">
            <div class="labels">
                <div>ID:</div>
                <div>Nombre:</div>
                <div>Autor:</div>
                <div>País:</div>
                <div>Exhibición:</div>
            </div>
            <div class="dataset">
                <div class="data row">
                    <div class="data-value" name="id">${data.id}</div>
                </div>
                <div class="data row" name="name">
                    <div class="data-value">${data.name}</div>
                    <div class="edit-button edit-data"><img onclick="editArtworkData(${data.id})"
                            src="assets/edit-icon-solid.png" alt="Edit"></div>
                </div>
                <div class="data row" name="author">
                    <div class="data-value">${data.author}</div>
                    <div class="edit-button edit-data"><img onclick="editArtworkData(${data.id})"
                            src="assets/edit-icon-solid.png" alt="Edit"></div>
                </div>
                <div class="data row" name="country">
                    <div class="data-value">${data.country}</div>
                    <div class="edit-button edit-data"><img onclick="editArtworkData(${data.id})"
                            src="assets/edit-icon-solid.png" alt="Edit"></div>
                </div>
                <div class="data row" name="exhibition">
                    <div class="data-value">${data.exhibition.name}</div>
                    <div class="edit-button edit-data"><img onclick="editArtworkData(${data.id})"
                            src="assets/edit-icon-solid.png" alt="Edit"></div>
                </div>
            </div>
        </div>
    </div>`
  );
  card.append(cardHeader);
  card.append(cardBody);
  if (data.media.length > 0) {
    data.media.forEach((element) => {
      if (
        element.fileType.localeCompare("image") == 0 &&
        element.displayName.localeCompare(data.name) == 0
      ) {
        card
          .find(".card-image img")
          .attr("src", `img/${element.fileName}.${element.extension}`)
          .click(() => {
            openImageInModal(event, element.displayName);
          });
      }
    });
  }
  cardHeader.find(".state-icon").click(() => {
    event.stopPropagation();
    let icon = event.target;
    changeArtworkState(data.id, () => {
      if (artworks[data.id].state.name.localeCompare("ACTIVE") == 0) {
        artworks[data.id].state.name = "INACTIVE";
        $(icon).attr("src", "assets/red-light.png");
      } else {
        artworks[data.id].state.name = "ACTIVE";
        $(icon).attr("src", "assets/green-light.png");
      }
    });
  });
  return card;
}

async function getArtworks() {
  await requestArtworks(createCards);
}

function editArtworkTextarea(id) {
  let card = $(event.target).closest(".card");
  card.addClass("editing");
  let textarea = card.find(".card-description textarea[name='description']");
  textarea.prop("disabled", false);
  textarea.focus();
  textarea.setCursorToTextEnd();
  let buttonsContainer = card.find(".card-description .text-buttons");
  buttonsContainer.html(
    `<div><img onclick = "confirmArtworkTextarea('${id}')" src="assets/confirm-icon.png"></div>` +
      `<div><img onclick = "cancelArtworkTextarea('${id}')" src="assets/cancel-icon.png"></div>` +
      `<div><img onclick = "clearArtworkTextarea()" src="assets/clear-icon.png"></div>` +
      `<div class="restore-button" style="display:none"><img onclick = "restoreArtworkTextarea(event.target,'${id}')" src="assets/undo-icon.png"></div>`
  );
  textarea.bind("input paste", () => {
    if (artworks[id].description.localeCompare(textarea.val()) != 0) {
      buttonsContainer.children(".restore-button").css("display", "block");
    } else {
      buttonsContainer.children(".restore-button").css("display", "none");
    }
  });
}

function confirmArtworkTextarea(id) {
  artworks[id].description = $(event.target)
    .closest(".card")
    .find(".card-description textarea[name='description']")
    .val();
  submitArtwork(artworks[id]);
  resetArtworkTextarea($(event.target).closest(".card"), id);
}

function cancelArtworkTextarea(id) {
  resetArtworkTextarea($(event.target).closest(".card"), id);
}

function clearArtworkTextarea() {
  $(event.target).closest(".card").find("textarea[name='description']").val("");
  $(event.target).parent().siblings(".restore-button").css("display", "block");
}

function restoreArtworkTextarea(element, id) {
  let card = $(element).closest(".card");
  card.find(".text-buttons .restore-button").css("display", "none");
  card
    .find(".card-description textarea[name='description']")
    .val(artworks[id].description);
}

function resetArtworkTextarea(card, id) {
  card
    .find(".card-description textarea[name='description']")
    .prop("disabled", true);
  restoreArtworkTextarea(card, id);
  card
    .find(".card-description .text-buttons")
    .html(
      `<div class="edit-button"><img onclick = "editArtworkTextarea('${id}')" src="assets/edit-icon.png"></div>`
    );

  card.removeClass("editing");
}

function editArtworkData(id) {
  $(event.target).closest(".card").addClass("editing");
  let column = $(event.target).closest(".data").attr("name");
  artworks[id][column] = $(event.target)
    .parent()
    .siblings(".data-value")
    .text();
  let parentElement = $(event.target).parent().parent();

  parentElement.html(
    `<input name=${column} type="text">` +
      `<div class="confirm-button"><img onclick="confirmArtworkData(${id})" src="assets/confirm-icon.png"></div>` +
      `<div class="cancel-button"><img onclick="cancelArtworkData(${id})" src="assets/cancel-icon.png"></div>`
  );
  parentElement.children("input").val(artworks[id][column]);
  parentElement.closest(".card-body").scrollLeft(20000);
}

function cancelArtworkData(id) {
  resetArtworkData($(event.target).closest(".data"), id);
}
function confirmArtworkData(id) {
  let dataElement = $(event.target).closest(".data");
  let column = dataElement.attr("name");
  artworks[id][column] = dataElement.find("input").val();
  resetArtworkData(dataElement, id);
  submitArtwork(artworks[id]);
}

function resetArtworkData(element, id) {
  let column = element.attr("name");
  element.html(
    `<div class="data-value" name=${column}>${artworks[id][column]}</div>
      <div class="edit-button edit-data"><img onclick="editArtworkData(${id})"
              src="assets/edit-icon-solid.png" alt="Edit"></div>`
  );
  element.closest(".card").removeClass("editing");
}

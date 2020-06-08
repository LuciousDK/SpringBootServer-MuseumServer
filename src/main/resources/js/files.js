let sort = ["", ""];
async function getFiles() {
  await requestFiles(createTableRows);
}
function createTableRows(dataSet) {
  files = dataSet;
  for (var id in files) {
    $("#data-table tbody").append(tableRow(files[id]));
  }
}

function tableRow(data) {
  let row = $(
    `<tr>
  <td name="id">${data.id}</td>
  <td name="title">${data.displayName}</td>
  <td name="nombre">${data.fileName}.${data.extension}</td>
  <td name="type">${data.fileType}</td>
</tr>`
  );
  row.find("td").click(() => {
    openFileInModal(data);
  });
  row.append(actionColumn());
  return row;
}

function actionColumn() {
  return $(
    `<td name="actions">
  <div class="action-buttons">
    <button style="background-color:grey">
      <img src="assets/edit-icon-beige.png">
    </button><button class="danger" onclick="deleteFileWarning()">
      <img src="assets/trashcan.png">
    </button>
  </div>
</td>`
  );
}

//Funcion de evento onkeyup para filtrar la tabla #data-table
function tableFilter() {
  var value = $(event.target).val().toLowerCase();
  $("#data-table tbody tr").filter(function () {
    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
  });
}

function sortTable() {
  let th = $(event.target).closest("th");
  let data = $(`#data-table tbody tr td[name="${th.attr("name")}"]`);
  if (sort[0].localeCompare(th.attr("name")) != 0) {
    sort[0] = th.attr("name");
    sort[1] = "asc";
  } else {
    if (sort[1].localeCompare("asc") == 0) {
      sort[1] = "desc";
    } else {
      sort[1] = "asc";
    }
  }
  data.sort((a, b) => {
    if (sort[1].localeCompare("asc") == 0) {
      return $(a).text().localeCompare($(b).text());
    } else {
      return $(b).text().localeCompare($(a).text());
    }
  });
  data.each((index, element) => {
    let row = element.closest("tr");
    let clone = row;
    row.remove();
    $("#data-table tbody").append(clone);
  });
  $("#data-table th img").attr("src", "assets/unsorted.png");
  if (sort[1].localeCompare("asc") == 0) {
    th.find("img").attr("src", "assets/sorted-asc.png");
  } else {
    th.find("img").attr("src", "assets/sorted-desc.png");
  }
}

function openUploadModal() {
  let modalBody = $("#main-modal .modal-body");
  let modalHeader = $("#main-modal .modal-header");
  let modalFooter = $("#main-modal .modal-footer");
  let titleLabel = $("<div>Título:</div>");
  titleLabel.css("font-weight", "bold");
  let titleInput = $(`<input type="text" name="title">`);
  titleInput.css({
    "margin-bottom": "20px",
    width: "100%",
    "line-height": "30px",
    "font-size": "large",
    padding: "0 5px",
  });
  let fileInput = $(
    `<input class="row" type="file" name="file" accept="audio/*|video/*|image/*">`
  );

  fileInput.change(() => {
    displayPreview(fileInput);
  });

  let submitButton = $("<button>Aceptar</button>");
  submitButton.css({
    "background-color": "rgb(16, 148, 255)",
  });

  submitButton.click(async () => {
    if (await validateFile()) {
      uploadFile(getFileInfo(), closeModal());
    }
  });

  modalBody.append(titleLabel);
  modalBody.append(titleInput);
  modalBody.append(fileInput);
  modalBody.append($("<div id='preview'></div>"));
  modalBody.append($("<div class='error'></div>"));
  modalFooter.append(submitButton);
  modalHeader.find(".title").text("Nuevo Archivo");
  openModal();
}

function displayPreview(fileInput) {
  var reader = new FileReader();
  let previewContainer = $("#preview");
  reader.onload = function (e) {
    let type = e.target.result.split(";")[0];
    if (type.includes("image")) {
      let image = $("<img>");
      image
        .attr("src", e.target.result)
        .css({ "max-height": "50vh", "max-width": "50vw" });
      previewContainer.html(image);
    }
    if (type.includes("video")) {
      let video = $(`<video controls></video>`);
      video
        .attr("src", e.target.result)
        .css({ "max-height": "50vh", "max-width": "50vw" });
      previewContainer.html(video);
    }
    if (type.includes("audio")) {
      let audio = $(`<audio controls></audio>`);
      audio
        .attr("src", e.target.result)
        .css({ "max-height": "50vh", "max-width": "50vw" });
      previewContainer.html(audio);
    }
  };
  if (fileInput.get(0).files[0]) {
    reader.readAsDataURL(fileInput.get(0).files[0]);
  } else {
    previewContainer.html("");
  }
}

function deleteFileWarning() {
  let tr = $(event.target).closest("tr");
  let id = tr.find(`td[name="id"]`).text().trim();
  let modalBody = $("#main-modal .modal-body");
  let modalHeader = $("#main-modal .modal-header");
  let modalFooter = $("#main-modal .modal-footer");
  let message = $(
    "<div>¿Estás seguro de que quieres borrar este archivo?</div>"
  );
  message.css("font-weight", "bold");

  let deleteButton = $("<button>Aceptar</button>");
  deleteButton.css({
    "background-color": "rgb(16, 148, 255)",
  });
  deleteButton.click(() => {
    deleteFile(id, () => {
      delete files[id];
      tr.remove();
      closeModal();
    });
  });

  let cancelButton = $("<button>Cancelar</button>");
  cancelButton.css({
    "background-color": "red",
  });
  cancelButton.click(closeModal);

  modalBody.append(message);
  modalFooter.append(deleteButton);
  modalFooter.append(" ");
  modalFooter.append(cancelButton);
  modalHeader.find(".title").text("Aviso");
  openModal();
}

async function validateFile() {
  let titleInput = $("#main-modal input[name='title']");
  let fileInput = $("#main-modal input[name='file']");
  let modalBody = $("#main-modal .modal-body");
  let errorMessage = modalBody.find(".error");
  if (titleInput.val().trim().localeCompare("") == 0) {
    titleInput.css({ border: "red 1px solid", "border-radius": "3px" });
    errorMessage.text("Indique el título del archivo.");
    return false;
  } else {
    titleInput.css({ border: "grey 1px solid", "border-radius": "3px" });
  }

  if (fileInput.get(0).files[0] == undefined) {
    fileInput.css("color", "red");
    errorMessage.text("Debe seleccionar un archivo.");
    return false;
  } else {
    fileInput.css("color", "black");
  }
  if (
    !(await nameAvailable(
      titleInput.val().trim(),
      fileInput.get(0).files[0].type.split("/")[0]
    ))
  ) {
    titleInput.css({ border: "red 1px solid", "border-radius": "3px" });
    errorMessage.text("Ya existe un archivo del mismo tipo con ese título.");
    return false;
  } else {
    titleInput.css({ border: "grey 1px solid", "border-radius": "3px" });
  }
  errorMessage.text("");
  return true;
}

function getFileInfo() {
  let modal = $("#main-modal");
  let data = {};
  data.file = modal.find("input[name='file']").get(0).files[0];
  data.fileName = data.file.name.split(".")[0];
  data.extension = data.file.name.split(".")[1];
  data.displayName = modal.find("input[name='title']").val();

  if (data.file.type.includes("image")) {
    data.fileType = "image";
  }
  if (data.file.type.includes("video")) {
    data.fileType = "video";
  }
  if (data.file.type.includes("audio")) {
    data.fileType = "audio";
  }
  return data;
}

function openFileInModal(data) {
  event.stopPropagation()
  let modal = $("#main-modal");
  modal.find(".modal-header .title").text(data.displayName);
  let body = modal.find(".modal-body");
  console.log(data)
  switch (data.fileType) {
    case "image":
      body.html(`<img src="img/${data.fileName}.${data.extension}">`)
      break;
    case "video":
      body.html(`<video src="video/${data.fileName}.${data.extension}" controls>`)
      break;
    case "audio":
      body.html(`<audio src="audio/${data.fileName}.${data.extension}" controls>`)
      break;
  }
  openModal()
}

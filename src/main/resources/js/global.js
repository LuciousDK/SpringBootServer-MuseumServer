const apiUrl = "http://localhost:8080";
(function() {
  "use strict";
  window.addEventListener(
    "load",
    function() {
      $(".dropdown-menu").mouseleave(function() {
        $(".dropdown").dropdown("toggle");
      });

      var forms = document.getElementsByClassName("needs-validation");

      var validation = Array.prototype.filter.call(forms, function(form) {
        form.addEventListener(
          "submit",
          function(event) {
            if (form.checkValidity() === false) {
              event.preventDefault();
              event.stopPropagation();
            }
            form.classList.add("was-validated");
          },
          false
        );
      });
    },
    false
  );
})();

function appendModal() {
  document.body.innerHTML += adminForm;
  $("#admin-modal").modal();
  $(document).on("hidden.bs.modal", ".modal", function() {
    $("#admin-modal").remove();
  });

  var forms = document.getElementsByClassName("admin-form");
  var validation = Array.prototype.filter.call(forms, function(form) {
    form.addEventListener(
      "submit",
      function(event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        form.classList.add("was-validated");
      },
      false
    );
  });
}
function submitAdminForm(event) {
  event.preventDefault();
  var form = document.forms["admin-form"];
  var http = new XMLHttpRequest();
  var url = apiUrl + "/api/user";
  http.open("POST", url, true);

  http.onreadystatechange = function() {
    if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
      $("#admin-modal").modal("hide");
    }
  };
  var formData = new FormData();
  formData.append("firstName", form["firstName"].value);
  formData.append("lastName", form["lastName"].value);
  formData.append("username", form["username"].value);
  formData.append("email", form["email"].value);
  formData.append("password", form["password"].value);
  if (validateNewAdminForm()) http.send(formData);
}

function validateNewAdminForm() {
  var form = document.forms["admin-form"];
  if (!form["firstName"].validity.valid) return false;
  if (!form["lastName"].validity.valid) return false;
  if (!form["username"].validity.valid) return false;
  if (!form["email"].validity.valid) return false;
  if (!form["password"].validity.valid) return false;
  return true;
}

function openReport(event, url) {
  event.preventDefault();
  var win = window.open(url, "_blank");
  win.focus();
}
function getFormDataJSON(form){
  var data = form.serializeArray();
  var result = {};

  $.map(data, function(input, index){
    result[input['name']] = input['value'];
  });
  return result;
}

const adminForm =
  '<div class="modal fade" id="admin-modal">' +
  '            <div class="modal-dialog modal-lg">' +
  '                <div class="modal-content">' +
  '                    <form action="#" class="admin-form" name="admin-form"' +
  '                      onsubmit="submitAdminForm(event)" novalidate>' +
  '                        <div class="modal-header">' +
  '                            <h4 class="modal-title">Editar Registro</h4>' +
  '                            <button type="button" class="close ml-auto" data-dismiss="modal">×</button>' +
  "                        </div>" +
  '                        <div class="modal-body">' +
  '                            <div class="form-group">' +
  '                                <label for="username">Nombre de Usuario</label>' +
  '                                <input class="form-control" type="text" name="username"' +
  '                                    placeholder="Usuario" required />' +
  '                                <div class="invalid-feedback">' +
  "                                    El campo no puede estar vacio." +
  "                                </div>" +
  "                            </div>" +
  '                            <div class="form-group">' +
  '                                <label for="firstName">Nombre</label>' +
  '                                <input class="form-control" type="text"  name="firstName"' +
  '                                    placeholder="Nombre" required />' +
  '                                <div class="invalid-feedback">' +
  "                                    El campo no puede estar vacio." +
  "                                </div>" +
  "                            </div>" +
  '                            <div class="form-group">' +
  '                                <label for="lastName">Apellido/s</label>' +
  '                                <input class="form-control" type="text"  name="lastName"' +
  '                                    placeholder="Apellido" required />' +
  '                                <div class="invalid-feedback">' +
  "                                    El campo no puede estar vacio." +
  "                                </div>" +
  "                            </div>" +
  '                            <div class="form-group">' +
  '                                <label for="email">Correo electrónico</label>' +
  '                                <input class="form-control" type="email"  name="email"' +
  '                                    placeholder="Correo electrónico" required />' +
  '                                <div class="invalid-feedback">' +
  "                                    El correo introducido no es válido." +
  "                                </div>" +
  "                            </div>" +
  '                            <div class="form-group">' +
  '                                <label for="password">Contraseña</label>' +
  '                                <input class="form-control" type="password" name="password"' +
  '                                    placeholder="Contraseña" minlength="4" required />' +
  '                                <div class="invalid-feedback">' +
  "                                    Debe contener un mínimo de 4 caracteres." +
  "                                </div>" +
  "                            </div>" +
  "                        </div>" +
  '                        <div class="modal-footer">' +
  '                            <button name="submit" class="btn btn-info" type="submit" value="0">Guardar</button>' +
  '                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>' +
  "                        </div>" +
  "                    </form>" +
  "                </div>" +
  "            </div>";

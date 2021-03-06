/*function deleteBy(id)
{
          //con fetch invocamos a la API de odontologos con el método DELETE
          //pasandole el id en la URL
          const url = '/pacientes/'+ id;
          const settings = {
              method: 'DELETE'
          }
          fetch(url,settings)
          .then(response => response.json())

          //borrar la fila del odontologo eliminado
          let row_id = "#tr_" + id;
          document.querySelector(row_id).remove();

}*/

$(document).ready(function () {
    let pacienteId = 0;

    $(document).on("click", "#div_paciente_table table button.btn_delete", function () {
        let btn_id = (event.srcElement.id);
        pacienteId = btn_id.split("_")[2];

        $("div.modal-body")
            .text("Querés eliminar le paciente con ID = " + pacienteId + "?");
        $("#model-delete-btn").css({ "display": "inline" });
    });

    $(document).on("click", "#model-delete-btn", function () {
        $.ajax({
            url: '/pacientes/' + pacienteId,
            type: 'DELETE',
            success: function (response) {
                $("div.modal-body")
                    .text("eliminade correctamente!");

                $("#model-delete-btn").css({ "display": "none" });
                $("button.btn.btn-secondary").text("Close");

                // Delete the odontólogo row on HTML page
                let row_id = "tr_" + pacienteId;
                $("#" + row_id).remove();
                $("#div_paciente_updating").css({ "display": "none" });
            },
            error: function (error) {
                console.log(error);
                $("#div_paciente_updating").css({ "display": "none" });
                alert("Error -> " + error);
            }
        });
    });
});
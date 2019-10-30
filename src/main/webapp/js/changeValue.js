    $(document).ready(function()
    {

        $("#estadoTipo").change(function () {
            var tipoEstadoCambiante= $("#estadoTipo").val();
            var linkModif = $("#linkModificacion").val();
            $("#linkModificacion").text(linkModif+"/"+tipoEstadoCambiante);
        })
       
    });

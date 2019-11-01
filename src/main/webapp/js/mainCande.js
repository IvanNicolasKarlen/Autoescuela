    $(document).ready(function()
    {

        $("#estadoTipo").change(function () {
            var tipoEstadoCambiante= $("#estadoTipo").val();
            var linkModif = $("#linkModificacion").val();
            $("#linkModificacion").text(linkModif+"/"+tipoEstadoCambiante);
        })
        
        $("#buscarPor").change(function(){
        	var buscarPor = $("#buscarPor").val();
        	var busqueda = document.getElementById("inputBusqueda");
        	busqueda.setAttribute("name",buscarPor);
        })
       
    });

$(document).ready(function(event) {

    $("#dodajPreduzece").submit(function(event) {
		event.preventDefault();
		dodajPreduzece();
	});

    var nazivInput = $("#naziv");
    var adresaInput = $("#adresa");
    var brojTelefonaInput = $("#brojTelefona");
    var pibInput = $("#pib");
    var mibInput = $("#mib");

    function dodajPreduzece(){

        var formData = {
            "naziv": nazivInput.val(),
            "adresa": adresaInput.val(),
            "telefon": brojTelefonaInput.val(),
            "pIB": pibInput.val(),
            "mIB": mibInput.val()
        }

        $.ajax({
            url : 'http://localhost:8080/api/preduzece',
            type : "POST",
            contentType: 'application/json; charset=utf-8',
            data : JSON.stringify(formData),
            success: function(result){
                alert('Preduzece uspjesno dodato');
                window.location.replace("prikazPreduzeca.html");
            },
            error : function(e){
                alert('Doslo je do neke greške!')
                console.log("ERROR: ", e);
            }
        });
    }
});
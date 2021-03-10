var prikaziPreduzeca = false;
var dodajPreduzece = false;

function prikazSvihPreduzeca() {

    var tabelaPreduzeca = $("#preduzecaTable");
    var tbodyPred = $("#tbodyPreduzece");
    odrediPrikaz('svaPreduzeca');
    function prikaziPreduzeca(){
        $.ajax({
            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/preduzece",
            success : function(result){
                tabelaPreduzeca.show();
                tbodyPred.empty();
                for(preduzece in result){
                    tbodyPred.append(
                        '<tr>'
							+'<td align="center">'+'<a href="" id="prikaziJedno" result-prID="'+result[preduzece].id+'">'+result[preduzece].naziv+'</a>'+'</td>'
							+'<td align="center">'+result[preduzece].adresa+'</td>'
							+'<td align="center">'+result[preduzece].telefon+'</td>'
							+'<td align="center">'+result[preduzece].pIB+'</td>'
							+'<td align="center">'+result[preduzece].mIB+'</td>'
						+'</tr>'
                    )};
                    selectedId = $(this).attr('result-prID');
                    
            },
            error :function(e){
                alert('ne valja nesto');
            }
        });
    }
    prikaziPreduzeca();

    console.log('aaaaa');
}

function submitPreduzece(){

    var nazivInput = $("#naziv");
    var adresaInput = $("#adresa");
    var brojTelefonaInput = $("#brojTelefona");
    var pibInput = $("#pib");
    var mibInput = $("#mib");

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
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}
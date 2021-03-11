var prikaziPreduzeca = false;
var dodajPreduzece = false;

function prikazSvihPreduzeca() {

    var tabelaPreduzeca = $("#preduzecaTable");
    var tbodyPred = $("#tbodyPreduzece");
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
                            +'<td class="naziv" align="center">'+result[preduzece].naziv+'</td>'
                            +'<td class="adresa" align="center">'+result[preduzece].adresa+'</td>'
                            +'<td classs="brojTelefona" align="center">'+result[preduzece].telefon+'</td>'
                            +'<td class="pib" align="center">'+result[preduzece].pIB+'</td>'
                            +'<td class="mib" align="center">'+result[preduzece].mIB+'</td>'
                            +'<td>'
                                +'<button type="submit" class="btn btn-warning" style="margin-right: 5%;" onclick="editPreduzece('+result[preduzece].id+')">IZMENI</button>'
                                +'<button type="submit" class="btn btn-danger" onclick="deletePreduzece('+result[preduzece].id+')">OBRIŠI</button>'
                            +'</td>'
                        +'</tr>'
                    )};
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

function editPreduzece(id){
    $('#btnDodajPreduzece').hide();
    $("#preduzecaTable").hide();
    $("#dodajPreduzece").show();
    $('#updatePreduzece').show();
    function prikaziPreduzece(){



        $.ajax({
            url:'http://localhost:8080/api/preduzece/' + id,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            // data : JSON.stringify(formData),
            success: function(result){


                var nazivP = $('#naziv');
                var adresaP = $('#adresa');
                var brojTelefonaP = $('#brojTelefona');
                var pibP = $('#pib');
                var mibP = $('#mib');

                nazivP.val(result.naziv);
                adresaP.val(result.adresa);
                brojTelefonaP.val(result.telefon);
                pibP.val(result.pIB);
                mibP.val(result.mIB);


                $("#updatePreduzece").on('click', function(event){

                    var naziv = nazivP.val();
                    var adresa = adresaP.val();
                    var telefon = brojTelefonaP.val();
                    var pib = pibP.val();
                    var mib = mibP.val();

                    var formData = {
                        'naziv': naziv,
                        "adresa": adresa,
                        "telefon": telefon,
                        "pIB": pib,
                        "mIB": mib
                    }

                    $.ajax({
                        url:'http://localhost:8080/api/preduzece/' + id,
                        type: 'PUT',
                        contentType: 'application/json; charset=utf-8',
                        data : JSON.stringify(formData),
                        success: function(result){
                            alert('Preduzece uspjesno izmjenjeno');
                        },
                        error : function(e){
                            alert('Doslo je do neke greške!')
                            console.log("ERROR: ", e);
                        }
                    });
                });

                },
            error : function(e){
                alert('Doslo je do neke greške!')
                console.log("ERROR: ", e);
            }

        });
    }

    prikaziPreduzece();
}

function deletePreduzece(id){
    $.ajax({
        url:'http://localhost:8080/api/preduzece/' + id,
        type: 'DELETE',
        contentType: 'application/json; charset=utf-8',
        success: function(result){
            alert('Preduzece uspjesno obrisano');
            prikazSvihPreduzeca();
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}
    
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
    var greska = "";
    var nazivInput = "";
    var adresaInput = "";
    var brojTelefonaInput = "";
    var pibInput = "";
    var mibInput = "";
    nazivInput = $("#naziv").val();
    adresaInput = $("#adresa").val();
    brojTelefonaInput = $("#brojTelefona").val();
    pibInput = $("#pib").val();
    mibInput = $("#mib").val();

    var nazivGreska;
    var adresaGreska;
    var brojTelGreska;
    var pibGreska;
    var mibGreska;
    

    if(nazivInput === ""){
        nazivGreska = true;
        greska += "\nMorate uneti naziv preduzeca";
    }
    if(adresaInput === ""){
        adresaGreska = true;
        greska += "\nMorate uneti adresu preduzeca";
    }
    if(brojTelefonaInput === ""){
        brojTelGreska = true;
        greska += "\nMorate uneti broj telefona preduzeca";
    }
    if(pibInput === ""){
        pibGreska = true;
        greska += "\nMorate uneti pib preduzeca";
    }
    if(mibInput === ""){
        mibGreska = true;
        greska += "\nMorate uneti mib preduzeca";
    }
    if(nazivGreska || adresaGreska || brojTelGreska || pibGreska || mibGreska){
        alert(greska);
    }
    else{
        var formData = {
            "naziv": nazivInput,
            "adresa": adresaInput,
            "telefon": brojTelefonaInput,
            "pIB": pibInput,
            "mIB": mibInput
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
}

function editPreduzece(id){
    $('#btnDodajPreduzece').show();
    $("#preduzecaTable").hide();
    $("#dodajPreduzece").show();
    $('#updatePreduzece').show();
    $('#btnDodajPreduzece').hide();
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
                var idPreduzecaUpdate = $('#idPreduzecaUpdate');

                idPreduzecaUpdate.val(result.id);
                nazivP.val(result.naziv);
                adresaP.val(result.adresa);
                brojTelefonaP.val(result.telefon);
                pibP.val(result.pIB);
                mibP.val(result.mIB);
                console.log(JSON.stringify(result));


                },
            error : function(e){
                alert('Doslo je do neke greške!')
                console.log("ERROR: ", e);
            }

        });
    }

    prikaziPreduzece();
}

function submitUpdatePreduzece(){

    var id = $('#idPreduzecaUpdate').val();
    var naziv = $('#naziv').val();
    var adresa = $('#adresa').val();
    var telefon = $('#brojTelefona').val();
    var pib = $('#pib').val();
    var mib = $('#mib').val();

    var formData = {
        'naziv': naziv,
        "adresa": adresa,
        "telefon": telefon,
        "pIB": pib,
        "mIB": mib
    }
    console.log("Ajax zahtev------------")
    $.ajax({
        url:'http://localhost:8080/api/preduzece/' + id,
        type: 'PUT',
        contentType: 'application/json; charset=utf-8',
        data : JSON.stringify(formData),
        success: function(result){
            alert('Preduzece uspjesno izmjenjeno');
            odrediPrikaz('svaPreduzeca')
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
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
    
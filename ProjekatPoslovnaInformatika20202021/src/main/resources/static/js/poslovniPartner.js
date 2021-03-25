function prikazSvihPartnera() {

    var tabelaPartneri = $("#poslovniPartneri");
    var tbodyPartneri = $("#tbodyPoslovniPartneri");
    
    function prikaziPartnere(){
        
        $.ajax({
            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/poslovni-partner",
            success : function(result){
            	tabelaPartneri.show();
            	tbodyPartneri.empty();
                var html = '';
                for(i in result){
                    var partner = result[i];
                    html += '<tr>';
                    html +=     '<td align="center">'+partner.sifraPartnera+'</td>';
                    html +=     '<td align="center">'+partner.nazivPartnera+'</td>';
                    html +=     '<td align="center">'+partner.adresa+'</td>';
                    html +=     '<td align="center">'+partner.brojTelefona+'</td>';
                    html +=     '<td align="center">'+partner.email+'</td>';
                    html +=     '<td align="center">'+partner.pib+'</td>';
                    html +=     '<td align="center">'+partner.mib+'</td>';
                    html +=     '<td align="center">'+partner.nazivPreduzeca+'</td>';
	                html +=		'<td>';
	            	html +=			'<button type="submit" class="btn btn-warning" onclick="editPoslovniPartner('+partner.sifraPartnera+')">IZMENI</button>';
	            	html +=		'</td>';
                    html +=		'<td>';
                    html +=			'<button type="submit" class="btn btn-danger" onclick="deletePoslovniPartner('+ partner.sifraPartnera+')">OBRIŠI</button>';
	            	html +=		'</td>';
                    html += '</tr>';
                };
                tbodyPartneri.append(html)
                    
            },
            error :function(e){
                alert('ne valja nesto');
            }
        });
    }
    prikaziPartnere();
}

function submitPoslovnogPartnera(){
    console.log("submitPartner")
    var greska = "";
    var nazivInput = "";
    var adresaInput = "";
    var brojTelefonaInput = "";
    var email = "";
    var pibInput = "";
    var mibInput = "";
    nazivInput = $("#nazivInputPoslovnogPartnera").val();
    adresaInput = $("#adresaInputPoslovnogPartnera").val();
    brojTelefonaInput = $("#telefonInputPoslovnogPartnera").val();
    email = $("#emailInputPoslovnogPartnera").val();
    pibInput = $("#pibPP").val();
    mibInput = $("#mibPP").val();
    var preduzece = $("#preduzecePP").val();

    var nazivGreska;
    var adresaGreska;
    var brojTelGreska;
    var eadresaGreska;
    var pibGreska;
    var mibGreska;

    if(nazivInput === ""){
        nazivGreska = true;
        greska += "\nMorate uneti naziv poslovnog partnera";
    }
    if(adresaInput === ""){
        adresaGreska = true;
        greska += "\nMorate uneti adresu poslovnog partnera";
    }
    if(brojTelefonaInput === ""){
        brojTelGreska = true;
        greska += "\nMorate uneti broj telefona poslovnog partnera";
    }
    if(email === ""){
        eadresaGreska = true;
        greska += "\nMorate uneti email poslovnog partnera";
    }
    if(pibInput === ""){
        pibGreska = true;
        greska += "\nMorate uneti pib poslovnog partnera";
    }
    if(mibInput === ""){
        mibGreska = true;
        greska += "\nMorate uneti mib poslovnog partnera";
    }
    if(nazivGreska || adresaGreska || brojTelGreska || eadresaGreska || pibGreska || mibGreska){
        alert(greska);
    }
    else{
        var formData = {
            "nazivPartnera": nazivInput,
            "adresa": adresaInput,
            "brojTelefona": brojTelefonaInput,
            "email": email,
            "pib": pibInput,
            "mib": mibInput,
            "idPreduzeca": preduzece
            
        }
    
        // alert(JSON.stringify(formData));

        $.ajax({
            url : 'http://localhost:8080/api/poslovni-partner',
            type : "POST",
            contentType: 'application/json; charset=utf-8',
            data : JSON.stringify(formData),
            success: function(result){
                alert('Partner je uspesno dodat');
            },
            error : function(e){
                alert('Doslo je do neke greške!')
                console.log("ERROR: ", e);
            }
        });
    }
}

function editPoslovniPartner(id){
    // alert('prva f-ja');
    $('#btnDodajPoslovnogPartnera').show();
    $("#poslovniPartneri").hide();
    $("#dodavanjePoslovnogPartnera").show();
    $('#izmeniPoslovnogPartnera').show();
    $('#btnDodajPoslovnogPartnera').hide();
    
    function prikaziPoslovnogPartnera(){
        $.ajax({
            url:'http://localhost:8080/api/poslovni-partner/' + id,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            success: function(result){
                dajPreduzeca("",result.idPreduzeca);
                var nazivP = $('#nazivInputPoslovnogPartnera');
                var adresaP = $('#adresaInputPoslovnogPartnera');
                var brojTelefonaP = $('#telefonInputPoslovnogPartnera');
                var emailP = $('#emailInputPoslovnogPartnera');
                var pibP = $('#pibPP');
                var mibP = $('#mibPP');
                var idPreduzecaUpdate = $('#idupdatePoslPartner');

                idPreduzecaUpdate.val(result.sifraPartnera);
                nazivP.val(result.nazivPartnera);
                adresaP.val(result.adresa);
                emailP.val(result.email);
                brojTelefonaP.val(result.brojTelefona);
                pibP.val(result.pib);
                mibP.val(result.mib);
                console.log(JSON.stringify(result));
            },
            error : function(e){
                alert('Doslo je do neke greške!')
                console.log("ERROR: ", e);
            }

        });
    }

    prikaziPoslovnogPartnera();
}

function submitUpdatePoslPartner(){
    // alert('druga f-ja');
    var naziv = $("#nazivInputPoslovnogPartnera").val();
    var adresa = $("#adresaInputPoslovnogPartnera").val();
    var telefon = $("#telefonInputPoslovnogPartnera").val()
    var email = $("#emailInputPoslovnogPartnera").val();
    var pib = $("#pibPP").val();
    var mib = $("#mibPP").val();
    var preduzeceP = $("#preduzecePP").val();

    var id = $("#idupdatePoslPartner").val();

    console.log(id + "id");
    var formData = {
        "nazivPartnera": naziv,
        "adresa": adresa,
        "brojTelefona": telefon,
        "email": email,
        "pib": pib,
        "mib": mib,
        "idPreduzeca": preduzeceP
    }
    // alert(JSON.stringify(formData));
    $.ajax({
        url:'http://localhost:8080/api/poslovni-partner/'+id,
        type: 'PUT',
        contentType: 'application/json; charset=utf-8',
        data : JSON.stringify(formData),
        success: function(result){
            alert('Poslovni partner je uspesno izmenjen!');
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}


function deletePoslovniPartner(id){
    $.ajax({
        url:'http://localhost:8080/api/poslovni-partner/' + id,
        type: 'DELETE',
        contentType: 'application/json; charset=utf-8',
        success: function(result){
            prikazSvihPartnera();
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}
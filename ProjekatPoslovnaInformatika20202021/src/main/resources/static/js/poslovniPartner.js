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
	                html +=		'<td>'
	            	html +=			'<button type="submit" class="btn btn-warning" style="margin-right: 5%;" onclick="editPoslovniPartner('+partner.sifraPartnera+')">IZMENI</button>'
	            	html +=			'<button type="submit" class="btn btn-danger" onclick="deletePoslovniPartner('+ partner.sifraPartnera+')">OBRIŠI</button>'
	            	html +=		'</td>'
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
    var naziv = $("#nazivInputPoslovnogPartnera");
    var adresa = $("#adresaInputPoslovnogPartnera");
    var telefon = $("#telefonInputPoslovnogPartnera");
    var email = $("#emailInputPoslovnogPartnera");
    var pib = $("#pibInputPoslovnogPartnera");
    var mib = $("#mibInputPoslovnogPartnera");
    var preduzece = $("#inputPreduzecePoslovnogPartnera");

    var formData = {
        "nazivPartnera": naziv.val(),
        "adresa": adresa.val(),
        "brojTelefona": telefon.val(),
        "email": email.val(),
        "pIB": pib.val(),
        "mIB": mib.val(),
        "idPreduzeca": preduzece.val()
        
    }

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

function editPoslovniPartner(id){
    $('#btnDodajPoslovnogPartnera').show();
    $("#poslovniPartneri").hide();
    $("#dodavanjePoslovnogPartnera").show();
    $('#izmeniPoslovnogPartnera').show();
    
    function prikaziPoslovnogPartnera(){

        $.ajax({
            url:'http://localhost:8080/api/poslovni-partner/' + id,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',

            success: function(result){
            	dajPreduzeca("selectPreduzece");
                
                var nazivPP = $("#nazivInputPoslovnogPartnera");
                var adresaPP = $("#adresaInputPoslovnogPartnera");
                var telefonPP = $("#telefonInputPoslovnogPartnera");
                var emailPP = $("#emailInputPoslovnogPartnera");
                var pibPP = $("#pibInputPoslovnogPartnera");
                var mibPP = $("#mibInputPoslovnogPartnera");
                var preduzecPP = $("#inputPreduzecePoslovnogPartnera");

                nazivPP.val(result.nazivPartnera);
                adresaPP.val(result.adresa);
                telefonPP.val(result.brojTelefona);
                emailPP.val(result.email);
                pibPP.val(result.pIB);
                mibPP.val(result.mIB);
                preduzecPP.val(result.nazivPreduzeca);


                $("#izmeniPoslovnogPartnera").on('click', function(event){
                        
                    var naziv = nazivPP.val();
                    var adresa = adresaPP.val();
                    var telefon = telefonPP.val()
                    var email = emailPP.val();
                    var pib = pibPP.val();
                    var mib = mibPP.val();
                    var preduzece = preduzecePP.val();

                    var formData = {
                        "nazivPartnera": naziv,
                        "adresa": adresa,
                        "brojTelefona": telefon,
                        "email": email,
                        "pIB": pib,
                        "mIB": mib,
                        "idPreduzeca": preduzece
                    }

                    $.ajax({
                        url:'http://localhost:8080/api/poslovni-partner/' + id,
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
                });

                },
            error : function(e){
                alert('Doslo je do neke greške!')
                console.log("ERROR: ", e);
            }

        });
    }

    prikaziPoslovnogPartnera();
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
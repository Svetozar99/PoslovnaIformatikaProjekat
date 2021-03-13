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
        "pib": pib.val(),
        "mib": mib.val(),
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
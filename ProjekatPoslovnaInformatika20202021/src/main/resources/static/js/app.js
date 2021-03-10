var prikaziOtpremnicu = false;
var prikaziPrijemnicu = false;
var prikaziMedjumagacinskiPromet = false;

var prikaziPreduzeca = false;
var prikaziMagacinskeKartice = false;
var dodajPreduzece = false;

var prikaziFormuZaDodavanjeRobeUsluge = false;
var prikaziPrometeMagacinskihKartica = false;


function odrediPrikaz(id){
    dodavanjePreduzeca = false;
    prikaziPreduzeca = false;
    prikaziMagacinskeKartice = false;
    prikaziOtpremnicu = false;
    prikaziPrijemnicu = false;
    prikaziMedjumagacinskiPromet = false;
    dodavanjeRobeUsluge = false;
    prikaziPrometeMagacinskihKartica = false;
    if(id === "otpremnica"){
        prikaziOtpremnicu = true;
    }else if( id === "prijemnica"){
        prikaziPrijemnicu = true;
    }else if(id === "medjumagacinskiPromet"){
        prikaziMedjumagacinskiPromet = true; 
    }else if(id === "svaPreduzeca"){
        prikaziPreduzeca = true;  
    }else if(id === "dodajPreduzece"){
        dodavanjePreduzeca = true;
    }else if(id === "dodavanjeRobeUsluge"){
        prikaziFormuZaDodavanjeRobeUsluge = true;
    }else if(id === "sviPrometiMagKart"){
        prikaziPrometeMagacinskihKartica = true;
    }else if(id === "sveMagacinskeKartice"){
        prikaziMagacinskeKartice = true;
    }
    prikazi();
}

function prikazi(){
    redovi.forEach(element => {
        console.log("red"+element.id);
        $("#red"+element.id).remove();
    });
    redovi = [];
    var prometniDokment = $("#prometniDokment");
    var prijemnica = $("#prijemnica");
    var otpremnica = $("#otpremnica");
    var medjumagacinskiPromet = $("#medjumagacinskiPromet");
    var preduzecaTable = $("#preduzecaTable");
    var dodajPreduzece = $("#dodajPreduzece");

    var dodavanjeRobeUsluge = $("#dodavanjeRobeUsluge");

    var prometMagKart = $("#prometiMagacinskihKarticaTable");
    var magacinskeKartice = $("#magacinskeKarticeTable");
    

    otpremnica.hide();
    prijemnica.hide();
    medjumagacinskiPromet.hide();
    preduzecaTable.hide();
    dodajPreduzece.hide();
    prometniDokment.hide();
    magacinskeKartice.hide();
    prometMagKart.hide();
    dodavanjeRobeUsluge.hide();
    if(prikaziPrijemnicu || prikaziOtpremnicu || prikaziMedjumagacinskiPromet){
        dajRobuIliUsluge();
    }   
    if(prikaziPrijemnicu){
        prometniDokment.show();
        prijemnica.show();
    }else if(prikaziOtpremnicu){
        prometniDokment.show();
        otpremnica.show();
    }else if(prikaziMedjumagacinskiPromet){
        prometniDokment.show();
        medjumagacinskiPromet.show();
    }else if(dodavanjePreduzeca){
        dodajPreduzece.show();
    }else if(prikaziFormuZaDodavanjeRobeUsluge){
        dajPreduzeca("selectPreduzeca");
        dajJediniceMere();
        dodavanjeRobeUsluge.show();

    }else if(prikaziPrometeMagacinskihKartica){
        prometMagKart.show();

    }else if(prikaziMagacinskeKartice){
        magacinskeKartice.show();

    }
}

function promeniIzgledTaba(dropdown){
    var prometniDokumentDropdown = $("#prometniDokumentDropdown");
    var robeUslugeDropdown = $("#robeUslugeDropdown");

    var preduzeceDropdown = $("#preduzeceDropdown");

    var prikazMagKartDropdown = $("#prikazMagKartDropdown");
    
    var prikazMagacinskeKartice = $("#prikazMagacinskeKarticeDropdown");
    if(dropdown === "prometniDokumentDropdown"){
        console.log("prometniDokumentDropdown")
        prometniDokumentDropdown.addClass("active");
        preduzeceDropdown.removeClass("active");
        robeUslugeDropdown.removeClass("active");
        prikazMagKartDropdown.removeClass("active");
        prikazMagacinskeKartice.removeClass("active");
    }
    else if(dropdown === "robeUslugeDropdown"){
        console.log("robeUslugeDropdown")
        robeUslugeDropdown.addClass("active");
        preduzeceDropdown.removeClass("active");
        prometniDokumentDropdown.removeClass("active");
        prikazMagKartDropdown.removeClass("active");
        prikazMagacinskeKartice.removeClass("active");
    }
    else if(dropdown === "preduzeceDropdown"){
        console.log("preduzeceDropdown")
        preduzeceDropdown.addClass("active");
        robeUslugeDropdown.removeClass("active");
        prometniDokumentDropdown.removeClass("active");
        prikazMagKartDropdown.removeClass("active");
        prikazMagacinskeKartice.removeClass("active");
    }
    else if(dropdown === "prikazMagKartDropdown"){
        console.log("prikazMagKartDropdown")
        prikazMagKartDropdown.addClass("active");
        preduzeceDropdown.removeClass("active");
        robeUslugeDropdown.removeClass("active");
        prometniDokumentDropdown.removeClass("active");
        prikazMagacinskeKartice.removeClass("active");
    }
    else if(dropdown === "prikazMagacinskeKarticeDropdown"){
        console.log("prikazMagacinskeKarticeDropdown")
        prikazMagKartDropdown.removeClass("active");
        preduzeceDropdown.removeClass("active");
        robeUslugeDropdown.removeClass("active");
        prometniDokumentDropdown.removeClass("active");
        prikazMagacinskeKartice.addClass("active");
    }
}

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
							+'<td/>'
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

function prikazSvihPrometaMagKartica() {

    var tabelaPrometaMK = $("#prometiMagacinskihKarticaTable");
    var tbodyPro = $("#tbodyProm");
    
    odrediPrikaz('sviPrometiMagKart');
    function prikaziPromete(){
        $.ajax({
            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/promet-magacinske-kartice",
            success : function(result){
                tabelaPrometaMK.show();
                tbodyPro.empty();
                for(p in result){
                    tbodyPro.append(
                        '<tr>'
							+'<td align="center">'+'<a href="" id="prikaziJedno" result-prID="'+result[p].id+'">'+result[p].vrstaPrometa+'</a>'+'</td>'
							+'<td align="center">'+result[p].smer+'</td>'
							+'<td align="center">'+result[p].kolicina+'</td>'
							+'<td align="center">'+result[p].cena+'</td>'
							+'<td align="center">'+result[p].dokument+'</td>'
							+'<td align="center">'+formatDate(new Date(result[p].date))+'</td>'
							+'<td/>'
						+'</tr>'
                    )};
                    selectedId = $(this).attr('result-prID');
                    
            },
            error :function(e){
                alert('ne valja nesto');
            }
        });
    }
    prikaziPromete();

    console.log('aaaaa');
}

function formatDate(date) {
    var day = date.getDate();
    var monthIndex = date.getMonth();
    var year = date.getFullYear();
    
    var months = ["Januar", "Februar", "Mart", "April", "Maj", "Jun", "Jul", "Avgust", "Septembar", "Oktobar", "Novembar", "Decembar"];
    
    return year + "-" + months[monthIndex] + "-" + day;

}

function prikazSvihMagacinskihKartica() {

    var tabelaMK = $("#magacinskeKarticeTable");
    var tbodyMK = $("#tbodyMagacinskeKartice");
    
    odrediPrikaz('sveMagacinskeKartice');
    function prikaziMagKart(){
        $.ajax({
            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/magacinska-kartica",
            success : function(result){
                tabelaMK.show();
                tbodyMK.empty();
                for(p in result){
                    tbodyMK.append(
                        '<tr>'
							+'<td align="center">'+'<a href="" id="prikaziJedno" result-prID="'+result[p].id+'">'+result[p].pocetnoStanjeKolicinski+'</a>'+'</td>'
							+'<td align="center">'+result[p].prometUlazaKolicinski+'</td>'
							+'<td align="center">'+result[p].prometIzlazaKolicinski+'</td>'
							+'<td align="center">'+result[p].ukupnaKolicina+'</td>'
							+'<td align="center">'+result[p].pocetnoStanjeVrednosno+'</td>'
							+'<td align="center">'+result[p].prometUlazaVrednosno+'</td>'
							+'<td align="center">'+result[p].prometIzlazaVrednosno+'</td>'
							+'<td align="center">'+result[p].ukupnaVrednost+'</td>'
							+'<td align="center">'+result[p].cena+'</td>'
							+'<td/>'
						+'</tr>'
                    )};
                    selectedId = $(this).attr('result-prID');
                    
            },
            error :function(e){
                alert('ne valja nesto');
            }
        });
    }
    prikaziMagKart();

    console.log('aaaaa');
}
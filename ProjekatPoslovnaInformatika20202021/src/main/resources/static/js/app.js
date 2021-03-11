var prikaziOtpremnicu = false;
var prikaziPrijemnicu = false;
var prikaziMedjumagacinskiPromet = false;

var prikaziPreduzeca = false;
var prikaziMagacinskeKartice = false;
var dodajPreduzece = false;

var prikaziMagacine = false;
var dodajMagacin = false;

var prikaziFormuZaDodavanjeRobeUsluge = false;
var prikaziSveRobeUsluge = false;
var prikaziPrometeMagacinskihKartica = false;
var preduzeca = [];

function odrediPrikaz(id){
    dodavanjePreduzeca = false;
    prikaziPreduzeca = false;
    dodavanjeMagacina = false;
    prikaziMagacine = false;
    prikaziMagacinskeKartice = false;
    prikaziOtpremnicu = false;
    prikaziPrijemnicu = false;
    prikaziMedjumagacinskiPromet = false;
    dodavanjeRobeUsluge = false;
    prikaziPrometeMagacinskihKartica = false;
    prikaziSveRobeUsluge = false;
    prikaziFormuZaDodavanjeRobeUsluge = false;

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
    }else if(id === "sviMagacini"){
        prikaziMagacine = true;
    }else if(id === "dodajMagacin"){
    	dodavanjeMagacina = true;
    }else if(id === "sveRobeUsluge"){
        prikaziSveRobeUsluge = true;
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
    var magaciniTable = $("#magaciniTable");
    var dodajMagacin = $("#dodajMagacin");
    var dodavanjeRobeUsluge = $("#dodavanjeRobeUsluge");
    var robeUslugeTable = $('#robeUslugeTable');
    var prometMagKart = $("#prometiMagacinskihKarticaTable");
    var magacinskeKartice = $("#magacinskeKarticeTable");

    otpremnica.hide();
    prijemnica.hide();
    medjumagacinskiPromet.hide();
    preduzecaTable.hide();
    dodajPreduzece.hide();
    prometniDokment.hide();
    magaciniTable.hide();
    dodajMagacin.hide();
    magacinskeKartice.hide();
    prometMagKart.hide();
    dodavanjeRobeUsluge.hide();
    robeUslugeTable.hide();

    if(prikaziPrijemnicu || prikaziOtpremnicu || prikaziMedjumagacinskiPromet){
        dajRobuIliUsluge();
    }   
    if(prikaziPrijemnicu){
        dajPreduzeca("selectPreduzeca");
        prometniDokment.show();
        prijemnica.show();
    }else if(prikaziOtpremnicu){
        dajPreduzeca("selectPreduzeca");
        prometniDokment.show();
        otpremnica.show();
    }else if(prikaziMedjumagacinskiPromet){
        prometniDokment.show();
        medjumagacinskiPromet.show();
    }else if(prikaziPreduzeca){
        prikazSvihPreduzeca();
    }else if(dodavanjePreduzeca){
        dodajPreduzece.show();
    }else if(dodavanjeMagacina){
    	dajPreduzeca("selectPreduzeca");
    	dodajMagacin.show();
    }else if(prikaziMagacine){
        prikazSvihMagacina();
    }else if(prikaziSveRobeUsluge){
        dajRobuIliUsluge("svaRobaIliUsluge");
        robeUslugeTable.show();
    }else if(prikaziFormuZaDodavanjeRobeUsluge){
        dajPreduzeca("selectPreduzeca");
        dajJediniceMere();
        dodavanjeRobeUsluge.show();
    }else if(prikaziPrometeMagacinskihKartica){
        console.log("prikazSvihPrometaMagKartica")
        prikazSvihPrometaMagKartica();
    }else if(prikaziMagacinskeKartice){
        prikazSvihMagacinskihKartica();
    }
}

function promeniIzgledTaba(dropdown){
    var prometniDokumentDropdown = $("#prometniDokumentDropdown");
    var robeUslugeDropdown = $("#robeUslugeDropdown");
    var preduzeceDropdown = $("#preduzeceDropdown");
    var magacinDropdown = $("#magacinDropdown");
    var prikazMagKartDropdown = $("#prikazMagKartDropdown");
    var prikazMagacinskeKartice = $("#prikazMagacinskihKarticaDropdown");

    prometniDokumentDropdown.removeClass("active");
    preduzeceDropdown.removeClass("active");
    robeUslugeDropdown.removeClass("active");
    prikazMagKartDropdown.removeClass("active");
    prikazMagacinskeKartice.removeClass("active");
    magacinDropdown.removeClass("active");
    if(dropdown === "prometniDokumentDropdown"){
        console.log("prometniDokumentDropdown")
        prometniDokumentDropdown.addClass("active");
    }
    else if(dropdown === "robeUslugeDropdown"){
        console.log("robeUslugeDropdown")
        robeUslugeDropdown.addClass("active");
    }
    else if(dropdown === "preduzeceDropdown"){
        console.log("preduzeceDropdown")
        preduzeceDropdown.addClass("active");
    }
    else if(dropdown === "magacinDropdown"){
        console.log("magacinDropdown")
        magacinDropdown.addClass("active");
    }
    else if(dropdown === "prikazMagKartDropdown"){
        console.log("prikazMagKartDropdown")
        prikazMagKartDropdown.addClass("active");
    }
    else if(dropdown === "prikazMagacinskihKarticaDropdown"){
        console.log("prikazMagacinskihKarticaDropdown");
        prikazMagacinskeKartice.addClass("active");
    }
}

function dajPreduzeca(text){
    $.ajax({
        type: "GET",
        contentType : 'application/json; charset=utf-8',
        url : "http://localhost:8080/api/preduzece",
        success : function(result){
            if(text === "selectPreduzeca"){
                selectPreduzece(result);
            }else {
            	preduzeca = result;
            }
        }
    });
}

function selectPreduzece(list){
    preduzeca=list;
    var inputMagacinPreduzece = $('#inputMagacinPreduzece');
    var inputDobavljac = $('#inputDobavljac');
    var inputKupac = $('#inputKupac');
    var inputProdavac = $('#inputProdavac');
    var inputKupacOtpremnica = $('#inputKupacOtpremnica');
    var inputPreduzece = $('#inputPreduzece');
    var preduzece = $('#preduzece');
    var html = "";
    list.forEach(preduzece => {
        html += '<option value="' + preduzece.id + '">' + preduzece.naziv + '</option>';
    });
    //Pravljenje liste dobavljaca
    inputDobavljac.empty();
    inputDobavljac.append(html);

    //Pravljenje liste kupaca
    inputKupac.empty();
    inputKupac.append(html);

    //Pravljenje liste prodavaca
    inputProdavac.empty();
    inputProdavac.append(html);

    //Pravljenje liste kupaca-otpremnica
    inputKupacOtpremnica.empty();
    inputKupacOtpremnica.append(html);

    //Pravljenje liste preduzeca za odabir magacina
    inputMagacinPreduzece.empty();
    inputMagacinPreduzece.append(html);

    //Pravljenje liste preduzeca za kreiranje robeIliUsluge
    inputPreduzece.empty();
    inputPreduzece.append(html);

    //Pravljenje liste preduzeca za kreiranje magacina
    preduzece.empty();
    preduzece.append(html);

    $('#inputDobavljacMestoIAdresa').val(list[0].adresa);
    $('#inputDobavljacPIB').val(list[0].pIB);
    $('#inputDobavljacTekuciRacun').val(list[0].mIB);

    $('#inputKupacMestoIAdresa').val(list[0].adresa);
    $('#inputKupacPIB').val(list[0].pIB);
    $('#inputKupacTekuciRacun').val(list[0].mIB);

    $('#inputProdavacMestoIAdresa').val(list[0].adresa);
    $('#inputProdavacPIB').val(list[0].pIB);
    $('#inputProdavacTekuciRacun').val(list[0].mIB);

    $('#inputKupacOtpremnicaMestoIAdresa').val(list[0].adresa);
    $('#inputKupacOtpremnicaPIB').val(list[0].pIB);
    $('#inputKupacOtpremnicaTekuciRacun').val(list[0].mIB);
}
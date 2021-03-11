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
        prometniDokment.show();
        prijemnica.show();
    }else if(prikaziOtpremnicu){
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
    	dajPreduzeca("magacinPreduzece");
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


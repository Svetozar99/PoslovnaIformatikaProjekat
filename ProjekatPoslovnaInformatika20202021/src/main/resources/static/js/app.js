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


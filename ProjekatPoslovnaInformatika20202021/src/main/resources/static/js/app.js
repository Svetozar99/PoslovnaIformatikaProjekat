var prikaziOtpremnicu = false;
var prikaziPrijemnicu = false;
var prikaziMedjumagacinskiPromet = false;

function odrediPrometniDokument(nazivDokumenta){
    console.log("odrediPrometniDokument")
    if(nazivDokumenta === "otpremnica"){
        prikaziOtpremnicu = true;
        prikaziPrijemnicu = false;
        prikaziMedjumagacinskiPromet = false;
    }else if( nazivDokumenta === "prijemnica"){
        prikaziPrijemnicu = true;
        prikaziOtpremnicu = false;
        prikaziMedjumagacinskiPromet = false;
    }else if(nazivDokumenta === "medjumagacinskiPromet"){
        prikaziMedjumagacinskiPromet = true;
        prikaziOtpremnicu = false;
        prikaziPrijemnicu = false;
    }
    prikaziPrometniDokument();
}

function prikaziPrometniDokument(){
    $("#prometniDokment").show();
    var prijemnica = $("#prijemnica");
    prijemnica.hide()
    var otpremnica = $("#otpremnica");
    otpremnica.hide()
    var medjumagacinskiPromet = $("#medjumagacinskiPromet");
    medjumagacinskiPromet.hide()
    if(prikaziPrijemnicu){
        prijemnica.show();
        console.log("prijemnica")
    }else if(prikaziOtpremnicu){
        otpremnica.show();
        console.log("otpremnica")
    }else if(prikaziMedjumagacinskiPromet){
        medjumagacinskiPromet.show();
        console.log("medjumagacinskiPromet")
    }
}
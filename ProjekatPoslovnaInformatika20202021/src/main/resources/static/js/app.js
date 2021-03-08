var prikaziOtpremnicu = false;
var prikaziPrijemnicu = false;
var prikaziMedjumagacinskiPromet = false;
var counter = 0;
var redovi = [];
var prikaziPreduzeca = false;
var dodavanjePreduzeca = false;

function odrediPrikaz(nazivDokumenta){
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
    }else if(nazivDokumenta === "svaPreduzeca"){
        prikaziPreduzeca = true;
        prikaziOtpremnicu = false;
        prikaziPrijemnicu = false;
        prikaziMedjumagacinskiPromet = false;
    }else if(nazivDokumenta === "dodajPreduzece"){
        dodajPreduzece = true;
        prikaziPreduzeca = false;
        prikaziOtpremnicu = false;
        prikaziPrijemnicu = false;
        prikaziMedjumagacinskiPromet = false;
    }
    prikazi();
}

function prikazi(){
    redovi.forEach(element => {
        console.log("red"+element.id);
        $("#red"+element.id).remove();
    });
    redovi = [];
    $("#prometniDokment").show();
    var prijemnica = $("#prijemnica");
    prijemnica.hide()
    var otpremnica = $("#otpremnica");
    otpremnica.hide()
    var medjumagacinskiPromet = $("#medjumagacinskiPromet");
    medjumagacinskiPromet.hide()
    if(prikaziPrijemnicu){
        prijemnica.show();
        $("#preduzecaTable").hide();
    }else if(prikaziOtpremnicu){
        otpremnica.show();
    }else if(prikaziMedjumagacinskiPromet){
        medjumagacinskiPromet.show();
    }else if(prikaziPreduzeca){
        $("#prometniDokment").hide();
    }else if(dodajPreduzece){
        $("#preduzecaTable").hide();
        $("#prometniDokment").hide();
        $("#dodajPreduzece").show();
    }
}

function ukloniRed(redId){
    console.log("redId: "+redId);
    $("#"+redId).remove();
}

function dodajRed(){
    console.log("Dodavanje novog reda");
    counter = counter + 1;
	var nazivInput = 'nazivInput'+counter;
    var kolicinaInput = 'kolicinaInput'+counter;
    var redId = "red"+counter;
	var red = {
					'id':counter,
					'naziv':nazivInput,
					'kolicina':kolicinaInput
				}
    redovi.push(red);
	var html = '';
	html += '<tr id="'+ redId + '">';
    html += '<td style="text-align: center;"></td>';
    html += '<td style="text-align: center;"></td>';
    html += '<td style="text-align: center;">';
    html += '<select class="form-control" name="nazivRobe" id="' + nazivInput + '">';
    html += '<option value="">Roba1</option>';
    html += '<option value="">Roba2</option>';
    html += '<option value="">Roba3</option>';
    html += '</select>';
    html += '</td>';
    html += '<td style="text-align: center;"></td>';
    html += '<td style="text-align: center; width: 10%;"><input id="' + kolicinaInput + '" class="form-control" type="text"></td>';
    html += '<td style="text-align: center;"></td>';
    html += '<td style="text-align: center; width: 10%;"><input readonly  class="form-control" type="text"></td>';
    html += '<td style="text-align: center;"><button onclick="ukloniRed(\'' + redId + '\')" class="btn btn-danger">Ukloni</button></td>';
    html += '</tr>';

	$('#sadrzajTabele').append(html);
}

function proknjizi(){
    console.log("Proknjizi!!!");
}

function promeniIzgledTaba(dropdown){
    var prometniDokumentDropdown = $("#prometniDokumentDropdown");
    var robeUslugeDropdown = $("#robeUslugeDropdown");
    var preduzeceDropdown = $("preduzeceDropdown");
    if(dropdown === "prometniDokumentDropdown"){
        console.log("prometniDokumentDropdown")
        prometniDokumentDropdown.addClass("active");
        preduzeceDropdown.removeClass("active");
        robeUslugeDropdown.removeClass("active");
    }
    else if(dropdown === "robeUslugeDropdown"){
        console.log("robeUslugeDropdown")
        robeUslugeDropdown.addClass("active");
        preduzeceDropdown.removeClass("active");
        prometniDokumentDropdown.removeClass("active");
    }
    else if(dropdown === "preduzeceDropdown"){
        console.log("preduzeceDropdown")
        preduzeceDropdown.addClass("active");
        robeUslugeDropdown.removeClass("active");
        prometniDokumentDropdown.removeClass("active");
    }
}
function prikazSvihPreduzeca() {

    var tabelaPreduzeca = $("#preduzecaTable");
    odrediPrikaz('svaPreduzeca');
    function prikaziPreduzeca(){
        $.ajax({
            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/preduzece",
            success : function(result){
                tabelaPreduzeca.show();
                tabelaPreduzeca.empty();
                for(preduzece in result){
                    tabelaPreduzeca.append(
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
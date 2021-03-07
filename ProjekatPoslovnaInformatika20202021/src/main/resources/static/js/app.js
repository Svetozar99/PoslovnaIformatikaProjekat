var prikaziOtpremnicu = false;
var prikaziPrijemnicu = false;
var prikaziMedjumagacinskiPromet = false;
var counter = 0;

function odrediPrometniDokument(nazivDokumenta){
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
    }else if(prikaziOtpremnicu){
        otpremnica.show();
    }else if(prikaziMedjumagacinskiPromet){
        medjumagacinskiPromet.show();
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
	var html = '';
	html += '<tr id="'+ redId + '">';
    html += '<td style="text-align: center;"></td>';
    html += '<td style="text-align: center;"></td>';
    html += '<td style="text-align: center;">';
    html += '<label>';
    html += '<select style="width: 120%;" class="form-control" name="nazivRobe" id="' + nazivInput + '">';
    html += '<option value="">Roba1</option>';
    html += '<option value="">Roba2</option>';
    html += '<option value="">Roba3</option>';
    html += '</select>';
    html += '</label>';
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
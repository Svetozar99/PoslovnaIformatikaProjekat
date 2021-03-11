var redovi = [];
var counter = 0;
var preduzeca = [];

function ukloniRed(redId){
    console.log("redId: "+redId);
    $("#"+redId).remove();
}

function dodajRed(){
    console.log("Dodavanje novog reda");
    counter = counter + 1;
	var nazivInput = 'nazivInput'+counter;
    var kolicinaInput = 'kolicinaInput'+counter;
    var cenaInput = 'cenaInput'+counter;
    var inputSifraArtikla = 'inputSifraArtikla'+counter;
    var inputJedinicaMere = 'inputJedinicaMere'+counter;
    var inputIznos = 'inputIznos'+counter;
    var redId = "red"+counter;
	var red = {
					'id':counter,
					'naziv':nazivInput,
					'kolicina':kolicinaInput
				}
    redovi.push(red);
	var html = '';
	html += '<tr id="'+ redId + '">';
    html += '<td style="text-align: center; width: 10%"><input readonly class="form-control" type="text"></td>';
    html += '<td style="text-align: center; width: 10%">' + 
            '<input readonly style="text-align: center;" class="form-control" type="text" id="' + inputSifraArtikla + '" value="' + robeUsluge[0].sifra + '">'+
            '</td>';
    html += '<td style="text-align: center;">';
    html += '<select onchange="postaviOstaleKolone('+counter+')" class="form-control" name="nazivRobe" id="' + nazivInput + '">';
    robeUsluge.forEach(element => {
        html += '<option value="' + element.sifra + '">' + element.naziv + '</option>';
    });
    html += '</select>';
    html += '</td>';
    html += '<td style="text-align: center; width: 10%">'  + 
            '<input readonly style="text-align: center;" class="form-control" type="text" id="' + inputJedinicaMere + '" value="' + robeUsluge[0].jedinicaMere + '">'+
            '</td>';
    html += '<td style="text-align: center; width: 10%;"><input onchange="racunaj()" style="text-align: center;" id="' + kolicinaInput + '" class="form-control" type="text"></td>';
    html += '<td style="text-align: center; width: 10%;"><input onchange="racunaj()" style="text-align: center;" id="' + cenaInput + '" class="form-control" type="text"></td>';
    html += '<td style="text-align: center; width: 10%;"><input readonly id="'+inputIznos+'" class="form-control" type="text"></td>';
    html += '<td style="text-align: center;"><button onclick="ukloniRed(\'' + redId + '\')" class="btn btn-danger">Ukloni</button></td>';
    html += '</tr>';

	$('#sadrzajTabele').append(html);
}

function postaviOstaleKolone(counter){
    var sifra = $('#nazivInput'+counter).val();
    robeUsluge.forEach(element => {
        if(sifra==element.sifra){
            $('#inputSifraArtikla'+counter).val(element.sifra);
            $('#inputJedinicaMere'+counter).val(element.jedinicaMere);
        }
    });
}

function racunaj(){
    var kolicinaInput = $('#kolicinaInput'+counter).val();
    var cenaInput = $('#cenaInput'+counter).val();
    $('#inputIznos'+counter).val(kolicinaInput*cenaInput);
}

function proknjizi(){
    console.log("Proknjizi!!!");
}

function dajPreduzeca(text){
    $.ajax({
        type: "GET",
        contentType : 'application/json; charset=utf-8',
        url : "http://localhost:8080/api/preduzece",
        success : function(result){
            if(text === "selectPreduzeca"){
                selectPreduzeca(result);
            }else if(text === "prijemnica"){
                selectDobavljac_Kupac(result);
            }else if(text === "magacinPreduzece"){
            	selectPreduzecaMagacin(result);
            }
        }
    });
}

function selectDobavljac_Kupac(list){
    preduzeca=list;
    var inputDobavljac = $('#inputDobavljac');
    var inputKupac = $('#inputKupac');
    var html = "";
    //Pravljenje liste dobavljaca
    inputDobavljac.empty();
    list.forEach(preduzece => {
        html += '<option value="' + preduzece.id + '">' + preduzece.naziv + '</option>';
    });
    inputDobavljac.append(html);
    html = '';
    //Pravljenje liste kupaca
    inputKupac.empty();
    list.forEach(preduzece => {
        html += '<option value="' + preduzece.id + '">' + preduzece.naziv + '</option>';
    });
    inputKupac.append(html);
    $('#inputDobavljacMestoIAdresa').val(list[0].adresa);
    $('#inputDobavljacPIB').val(list[0].pIB);
    $('#inputDobavljacTekuciRacun').val(list[0].mIB);

    $('#inputKupacMestoIAdresa').val(list[0].adresa);
    $('#inputKupacPIB').val(list[0].pIB);
    $('#inputKupacTekuciRacun').val(list[0].mIB);
}

function postaviInformacijePreduzeca(){
    
    var idDobavljac = $('#inputDobavljac').val();
    var idKupac = $('#inputKupac').val();
    preduzeca.forEach(preduzece => {
        if(preduzece.id == idDobavljac){
            $('#inputDobavljacMestoIAdresa').val(preduzece.adresa);
            $('#inputDobavljacPIB').val(preduzece.pIB);
            $('#inputDobavljacTekuciRacun').val(preduzece.mIB);
        }
    });
    preduzeca.forEach(preduzece => {    
        if(preduzece.id == idKupac){
            console.log("Else if! "+preduzece.naziv)
            $('#inputKupacMestoIAdresa').val(preduzece.adresa);
            $('#inputKupacPIB').val(preduzece.pIB);
            $('#inputKupacTekuciRacun').val(preduzece.mIB);
        }
    });    
}
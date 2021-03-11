var redovi = [];
var counter = 0;
var magacini = [];


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

function postaviInformacijePreduzeca(){
    
    var idDobavljac = $('#inputDobavljac').val();
    var idKupac = $('#inputKupac').val();
    var inputProdavac = $('#inputProdavac').val();
    var inputKupacOtpremnica = $('#inputKupacOtpremnica').val();
    preduzeca.forEach(preduzece => {
        if(preduzece.id == idDobavljac){
            $('#inputDobavljacMestoIAdresa').val(preduzece.adresa);
            $('#inputDobavljacPIB').val(preduzece.pIB);
            $('#inputDobavljacTekuciRacun').val(preduzece.mIB);
        }

        if(preduzece.id == idKupac){
            $('#inputKupacMestoIAdresa').val(preduzece.adresa);
            $('#inputKupacPIB').val(preduzece.pIB);
            $('#inputKupacTekuciRacun').val(preduzece.mIB);
        }

        if(preduzece.id == inputProdavac){
            $('#inputProdavacMestoIAdresa').val(preduzece.adresa);
            $('#inputProdavacPIB').val(preduzece.pIB);
            $('#inputProdavacTekuciRacun').val(preduzece.mIB);
        }

        if(preduzece.id == inputKupacOtpremnica){
            $('#inputKupacOtpremnicaMestoIAdresa').val(preduzece.adresa);
            $('#inputKupacOtpremnicaPIB').val(preduzece.pIB);
            $('#inputKupacOtpremnicaTekuciRacun').val(preduzece.mIB);
        }
    });    
}

function selectMagacini(list){
    console.log("selectMagacini")
    magacini=list;
    var inputMagacin1 = $('#inputMagacin1');
    var inputMagacin2 = $('#inputMagacin2');
    var html = "";
    list.forEach(magacin => {
        html += '<option value="' + magacin.id + '">' + magacin.naziv + '</option>';
    });
    //Pravljenje liste magacina za prvu listu
    inputMagacin1.empty();
    inputMagacin1.append(html);

    //Pravljenje liste magacina za drugu listu
    inputMagacin2.empty();
    inputMagacin2.append(html);
}

function dajMagacine(text){
    $.ajax({
        type: "GET",
        contentType : 'application/json; charset=utf-8',
        url : "http://localhost:8080/api/magacin",
        success : function(result){
            if(text === "selectMagacin"){
                selectMagacini(result);
            }else {
            	magacini = result;
            }
        }
    });
}

function promenaPreduzeca(){
    var idPreduzeca = $('#inputMagacinPreduzece').val();
    $.ajax({
        type: "GET",
        contentType : 'application/json; charset=utf-8',
        url : "http://localhost:8080/api/magacin/preduzece/{id}",
        success : function(result){
            selectMagacini(result);
        }
    });
}
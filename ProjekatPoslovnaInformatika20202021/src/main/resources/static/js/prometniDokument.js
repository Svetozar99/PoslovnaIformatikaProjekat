var redovi = [];
var counter = 0;
var magacini = [];
var partneri = [];


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
    var idDobavljaca = $('#inputDobavljac').val();
    var idProdavca = $('#inputProdavac').val();
    var idMagacina1 = $('#inputMagacin1').val();
    var idMagacina2 = $('#inputMagacin2').val();
    var idKupca;
    var vrstaDokumenta;
    if(prikaziPrijemnicu){
        idKupca = $('#inputKupac').val();
        vrstaDokumenta = "PR";
    }else if(prikaziOtpremnicu){
        idKupca = $('#inputKupacOtpremnica').val();
        vrstaDokumenta = "OT";
    }else if(prikaziMedjumagacinskiPromet){
        vrstaDokumenta = "MM";
        idKupca = '';
    }
    var mestoIzdavanjaRobe = $('#inputDobavljacMestoIzdavajaRobe').val();
    var nacinOtpreme = $('#inputNacinOtpreme').val();
    var prijamnicaBr = $('#inputPrijemnicaBr').val();
    var otpremnicaBr = $('#inputOtpremnicaBr').val();
    var medjumagacinskiPrometBr = $('#inputMedjumagacinskiPrometBr').val();
    var datumIzdavanja = $('#inputDatumIzdavanja').val();
    
    var robuIzdao = $('#inputRobuIzdao').val();
    var robuPrimio = $('#inputRobuPrimio').val();

    var formData = {
        "idDobavljaca": idDobavljaca,
        "idProdavca": idProdavca,
        "idMagacina1": idMagacina1,
        "idMagacina2": idMagacina2,
        "idKupca": idKupca,
        "vrstaDokumenta": vrstaDokumenta,
        "mestoIzdavanjaRobe": mestoIzdavanjaRobe,
        "nacinOtpreme": nacinOtpreme,
        "prijamnicaBr": prijamnicaBr,
        "otpremnicaBr": otpremnicaBr,
        "medjumagacinskiPrometBr": medjumagacinskiPrometBr,
        "datumIzdavanja": datumIzdavanja,
        "robuIzdao": robuIzdao,
        "robuPrimio": robuPrimio
    }
    console.log(JSON.stringify(formData));
}

function postaviInformacijePreduzeca(){
    
    var idKupac = $('#inputKupac').val();
    var inputProdavac = $('#inputProdavac').val();
    preduzeca.forEach(preduzece => {
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
    });    
}

function postaviInformacijePartnera(){
    var idDobavljac = $('#inputDobavljac').val();
    var inputKupacOtpremnica = $('#inputKupacOtpremnica').val();
    partneri.forEach(partner => {
        if(partner.sifraPartnera == idDobavljac){
            $('#inputDobavljacMestoIAdresa').val(partner.adresa);
            $('#inputDobavljacPIB').val(partner.pib);
        }

        if(partner.sifraPartnera == inputKupacOtpremnica){
            $('#inputKupacOtpremnicaMestoIAdresa').val(partner.adresa);
            $('#inputKupacOtpremnicaPIB').val(partner.pib);
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

function dajMagacine(text,idPreduzeca){
    $.ajax({
        type: "GET",
        contentType : 'application/json; charset=utf-8',
        url : "http://localhost:8080/api/magacin/preduzece/"+idPreduzeca,
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
    dajMagacine("selectMagacin",idPreduzeca);
}

function dajPoslovnePartnere(text){
    $.ajax({
        type: "GET",
        contentType : 'application/json; charset=utf-8',
        url : "http://localhost:8080/api/poslovni-partner",
        success : function(result){
            if(text === "selectPoslovniPartner"){
                selectPoslovniPartner(result);
            }
        }
    });
}

function selectPoslovniPartner(list){
    partneri=list;

    var inputDobavljac = $('#inputDobavljac');
    var inputKupacOtpremnica = $('#inputKupacOtpremnica');
    var html = "";
    list.forEach(partner => {
        html += '<option value="' + partner.sifraPartnera + '">' + partner.nazivPartnera + '</option>';
    });
    //Pravljenje liste dobavljaca
    inputDobavljac.empty();
    inputDobavljac.append(html);

    //Pravljenje liste kupaca-otpremnica
    inputKupacOtpremnica.empty();
    inputKupacOtpremnica.append(html);

    $('#inputDobavljacMestoIAdresa').val(list[0].adresa);
    $('#inputDobavljacPIB').val(list[0].pib);

    $('#inputKupacOtpremnicaMestoIAdresa').val(list[0].adresa);
    $('#inputKupacOtpremnicaPIB').val(list[0].pib);
}
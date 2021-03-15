var redovi = [];
var counter = 0;
var magacini = [];
var partneri = [];


function ukloniRed(redId){
    //console.log(redId)
    $("#red"+redId).remove();
    redovi.forEach(element => {
        if(element.id == redId){
           // console.log("U ifu sam!")
            const index = redovi.indexOf(element)
            redovi.splice(index,1);
        }
    });
    //console.log("redovi: "+JSON.stringify(redovi));
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
    var redId = counter;
	var red = {
					'id':counter,
					'naziv':nazivInput,
					'kolicina':kolicinaInput,
                    'cena':cenaInput,
                    'vrednost':inputIznos
				}
    redovi.push(red);
	var html = '';
	html += '<tr id="'+ "red"+redId + '">';
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
    var sifraPoslovnogPartnera = 0;
    var sifraMagacinaUlaz = 0;
    var sifraMagacinaIzlaz = 0;
    var idPreduzeca = 0;
    var vrstaDokumenta;
    var brojPrometnogDokumenta;
    if(prikaziPrijemnicu){
        idPreduzeca = $('#inputKupac').val();
        vrstaDokumenta = "PR";
        sifraPoslovnogPartnera = $('#inputDobavljac').val();
        brojPrometnogDokumenta = $('#inputPrijemnicaBr').val();
        sifraMagacinaUlaz = $('#magacinPrijemnica').val();

    }else if(prikaziOtpremnicu){
        idPreduzeca = $('#inputProdavac').val();
        vrstaDokumenta = "OT";
        sifraPoslovnogPartnera = $('#inputKupacOtpremnica').val();
        brojPrometnogDokumenta = $('#inputOtpremnicaBr').val();
        sifraMagacinaIzlaz = $('#magacinOtpremnica').val();
    }else if(prikaziMedjumagacinskiPromet){
        vrstaDokumenta = "MM";
        brojPrometnogDokumenta = $('#inputMedjumagacinskiPrometBr').val();
        sifraMagacinaUlaz = $('#inputMagacin1').val();
        sifraMagacinaIzlaz = $('#inputMagacin2').val();
    }
    var datumIzdavanja = $('#inputDatumIzdavanja').val();

    var formData = {
        "sifraPoslovnogPartnera": sifraPoslovnogPartnera,
        "sifraMagacina1": sifraMagacinaUlaz,
        "sifraMagacina2": sifraMagacinaIzlaz,
        "vrstaDokumenta": vrstaDokumenta,
        "brojPrometnogDokumenta":brojPrometnogDokumenta,
        "datumIzdavanja": datumIzdavanja,
        "idPreduzeca":idPreduzeca
    }
    console.log("ajax!!! "+JSON.stringify(formData));
    $.ajax({
        url : 'http://localhost:8080/api/prometni-dokument',
        type : "POST",
        contentType: 'application/json; charset=utf-8',
        data : JSON.stringify(formData),
        success: function(result){
            alert('Prometni dokument je uspesno dodat');

            var stavke = [];
            redovi.forEach(red => {
                //console.log(JSON.stringify(red))
                var stavka = {
                    'kolicina':$('#'+red.kolicina).val(),
                    'cena':$('#'+red.cena).val(),
                    'vrednost':$('#'+red.vrednost).val(),
                    'prometniDokument':result.id,
                    'robaUsluga':$('#'+red.naziv).val()
                }
                stavke.push(stavka)
            });
            console.log(JSON.stringify(stavke));
            $.ajax({
                url : 'http://localhost:8080/api/stavka-dokumenta',
                type : "POST",
                contentType: 'application/json; charset=utf-8',
                data : JSON.stringify(stavke),
                success: function(result){
                    alert('Stavke dokumenta su uspesno dodate');
                    
                },
                error : function(e){
                    alert('Doslo je do neke greške!')
                    console.log("ERROR: ", e);
                }
            });
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}

function postaviInformacijePreduzecaPrijemnica(){
    
    var idKupac = $('#inputKupac').val();
    dajMagacine("selectMagacin",idKupac);
    preduzeca.forEach(preduzece => {
        if(preduzece.id == idKupac){
            $('#inputKupacMestoIAdresa').val(preduzece.adresa);
            $('#inputKupacPIB').val(preduzece.pIB);
            $('#inputKupacTekuciRacun').val(preduzece.mIB);
        }
    });    
}

function postaviInformacijePreduzecaOtpremnica(){
    
    var idProdavac = $('#inputProdavac').val();
    dajMagacine("selectMagacin",idProdavac);
    preduzeca.forEach(preduzece => {

        if(preduzece.id == idProdavac){
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
    var inputMagacinForMagKart = $("#inputMagacinForMagKart");
    var magacinPrijemnica = $("#magacinPrijemnica");
    var magacinOtpremnica = $("#magacinOtpremnica");
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

    //Pravljenje liste magacina za drugu listu
    inputMagacinForMagKart.empty();
    inputMagacinForMagKart.append(html);

    //Pravljenje liste magacina za prijemnicu
    magacinPrijemnica.empty();
    magacinPrijemnica.append(html);

    //Pravljenje liste magacina za otpremnicu
    magacinOtpremnica.empty();
    magacinOtpremnica.append(html);

}

function dajMagacine(text,idPreduzeca){
    var u = "";
    if(idPreduzeca == 0){
        u = "http://localhost:8080/api/magacin";
    }else{
        u = "http://localhost:8080/api/magacin/preduzece/"+idPreduzeca;
    }
    $.ajax({
        type: "GET",
        contentType : 'application/json; charset=utf-8',
        url : u,
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
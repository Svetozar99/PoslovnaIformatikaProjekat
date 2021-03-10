var prikaziOtpremnicu = false;
var prikaziPrijemnicu = false;
var prikaziMedjumagacinskiPromet = false;
var counter = 0;
var redovi = [];
var prikaziPreduzeca = false;
var dodajPreduzece = false;

var prikaziMagacine = false;
var dodajMagacin = false;

var prikaziFormuZaDodavanjeRobeUsluge = false;
var robeUsluge = [];

var prikaziPrometeMagacinskihKartica = false;


function odrediPrikaz(id){
    dodavanjePreduzeca = false;
    prikaziPreduzeca = false;
    dodavanjeMagacina = false;
    prikaziMagacine = false;
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
    }else if(id === "sviMagacini"){
        prikaziMagacine = true;
    }else if(id === "dodajMagacin"){
    	dodavanjeMagacina = true;
    }else if(id === "dodavanjeRobeUsluge"){
        prikaziFormuZaDodavanjeRobeUsluge = true;
    }else if(id === "sviPrometiMagKart"){
        prikaziPrometeMagacinskihKartica = true;
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

    var prometMagKart = $("#prometiMagacinskihKarticaTable");

    otpremnica.hide();
    prijemnica.hide();
    medjumagacinskiPromet.hide();
    preduzecaTable.hide();
    dodajPreduzece.hide();
    prometniDokment.hide();
    
    magaciniTable.hide();
    dodajMagacin.hide();

    dodavanjeRobeUsluge.hide();
    if(prikaziPrijemnicu || prikaziOtpremnicu || prikaziMedjumagacinskiPromet){
        dajRobuIliUsluge();
    }

    prometMagKart.hide();

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

    }else if(dodavanjeMagacina){
    	dodajMagacin.show();
        
    }else if(prikaziFormuZaDodavanjeRobeUsluge){
        dajPreduzeca();
        dajJediniceMere();
        dodavanjeRobeUsluge.show();

    }else if(prikaziPrometeMagacinskihKartica){
        prometMagKart.show();

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

function promeniIzgledTaba(dropdown){
    var prometniDokumentDropdown = $("#prometniDokumentDropdown");
    var robeUslugeDropdown = $("#robeUslugeDropdown");

    var preduzeceDropdown = $("#preduzeceDropdown");
    
    var magacinDropdown = $("#magacinDropdown");

    var preduzeceDropdown = $("preduzeceDropdown");
    var prikazMagKartDropdown = $("#prikazMagKartDropdown");

    if(dropdown === "prometniDokumentDropdown"){
        console.log("prometniDokumentDropdown")
        prometniDokumentDropdown.addClass("active");
        preduzeceDropdown.removeClass("active");
        robeUslugeDropdown.removeClass("active");
        prikazMagKartDropdown.removeClass("active");
    }
    else if(dropdown === "robeUslugeDropdown"){
        console.log("robeUslugeDropdown")
        robeUslugeDropdown.addClass("active");
        preduzeceDropdown.removeClass("active");
        prometniDokumentDropdown.removeClass("active");
        prikazMagKartDropdown.removeClass("active");
    }
    else if(dropdown === "preduzeceDropdown"){
        console.log("preduzeceDropdown")
        preduzeceDropdown.addClass("active");
        robeUslugeDropdown.removeClass("active");
        prometniDokumentDropdown.removeClass("active");
        prikazMagKartDropdown.removeClass("active");
    }
    else if(dropdown === "magacinDropdown"){
        console.log("magacinDropdown")
        magacinDropdown.addClass("active");
        robeUslugeDropdown.removeClass("active");
        prometniDokumentDropdown.removeClass("active");
        prikazMagKartDropdown.removeClass("active");
    }
    else if(dropdown === "prikazMagKartDropdown"){
        console.log("prikazMagKartDropdown")
        prikazMagKartDropdown.addClass("active");
        preduzeceDropdown.removeClass("active");
        robeUslugeDropdown.removeClass("active");
        prometniDokumentDropdown.removeClass("active");
    }
}
function prikazSvihPreduzeca() {

    var tabelaPreduzeca = $("#preduzecaTable");
    var tbodyPred = $("#tbodyPreduzece");
    odrediPrikaz('svaPreduzeca');
    function prikaziPreduzeca(){
        $.ajax({
            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/preduzece",
            success : function(result){
                tabelaPreduzeca.show();
                tbodyPred.empty();
                for(preduzece in result){
                    tbodyPred.append(
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


function dajRobuIliUsluge(){
    $.ajax({
        type: "GET",
        contentType : 'application/json; charset=utf-8',
        url : "http://localhost:8080/api/roba-ili-usluga",
        success : function(result){
            robeUsluge = result;
        },
        error :function(e){
            console.log("Greska!!!");
        }
    });
}

function dajPreduzeca(){
    var selectPreduzece = $("#selectPreduzece");
    var html = '<label>Izaberite preduzece:';
    html += '<select class="form-control" id="inputPreduzece">';
    $.ajax({
        type: "GET",
        contentType : 'application/json; charset=utf-8',
        url : "http://localhost:8080/api/preduzece",
        success : function(result){
            for(i in result){
                var preduzece = result[i];
                html += '<option value="' + preduzece.id + '">' + preduzece.naziv + '</option>'
                };
            html += '</select>';
            html += '</label>';
            selectPreduzece.append(html);
        },
        error :function(e){
            alert('ne valja nesto');
        }
    });
}

function submitRobaUsluga(){

    var naziv = $("#inputNazivRobaUsluga");
    var preduzece = $("#inputPreduzece");
    var jedinicaMere = $("#inputJedinicaMere");
    var formData = {
        "naziv": naziv.val(),
        "preduzece": preduzece.val(),
        "jedinicaMere": jedinicaMere.val()
    }

    $.ajax({
        url : 'http://localhost:8080/api/roba-ili-usluga',
        type : "POST",
        contentType: 'application/json; charset=utf-8',
        data : JSON.stringify(formData),
        success: function(){
            alert('Roba ili usluga uspesno dodata');
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });

}
function prikazSvihPrometaMagKartica() {

    var tabelaPrometaMK = $("#prometiMagacinskihKarticaTable");
    var tbodyPro = $("#tbodyProm");
    
    odrediPrikaz('sviPrometiMagKart');
    function prikaziPromete(){
        $.ajax({
            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/promet-magacinske-kartice",
            success : function(result){
                tabelaPrometaMK.show();
                tbodyPro.empty();
                for(p in result){
                    tbodyPro.append(
                        '<tr>'
							+'<td align="center">'+'<a href="" id="prikaziJedno" result-prID="'+result[p].id+'">'+result[p].vrstaPrometa+'</a>'+'</td>'
							+'<td align="center">'+result[p].smer+'</td>'
							+'<td align="center">'+result[p].kolicina+'</td>'
							+'<td align="center">'+result[p].cena+'</td>'
							+'<td align="center">'+result[p].dokument+'</td>'
							+'<td align="center">'+formatDate(new Date(result[p].date))+'</td>'
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
    prikaziPromete();

    console.log('aaaaa');
}

function formatDate(date) {
    var day = date.getDate();
    var monthIndex = date.getMonth();
    var year = date.getFullYear();
    
    var months = ["Januar", "Februar", "Mart", "April", "Maj", "Jun", "Jul", "Avgust", "Septembar", "Oktobar", "Novembar", "Decembar"];
    
    return year + "-" + months[monthIndex] + "-" + day;

}
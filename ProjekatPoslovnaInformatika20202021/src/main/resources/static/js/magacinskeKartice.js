var selectedId;
var kartice = [];

function prikazSvihMagacinskihKartica() {

    var sifraMagacina = $('#inputMagacinForMagKart').val();
    var sifraRobeIliUsluge = $('#inputRobaForMagKart').val();
    var brojPoslovneGodine = $('#inputGodinaForMagKart').val();
    var tabelaMK = $("#magacinskeKarticeTable");
    var tbodyMK = $("#tbodyMagacinskeKartice");
    
    function prikaziMagKart(){
        $.ajax({
            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/magacinska-kartica/"+sifraMagacina+"/"+sifraRobeIliUsluge+"/"+brojPoslovneGodine,
            success : function(result){
                kartice = result;
                tabelaMK.show();
                tbodyMK.empty();
                for(p in result){
                    
                    tbodyMK.append(
                        '<tr>'
                            +'<td align="center">'+result[p].robaIliUsluga+'</td>'
							+'<td align="center">'+'<button class="btn btn-link" onclick="prikaziOdredjenuMagacinskuKarticu('+result[p].id+')">'+result[p].nazivMagacina+'</button>'+'</td>'
							+'<td align="center">'+result[p].nazivRobeIliUsluge+'</td>'
							+'<td align="center">'+result[p].brojPoslovneGodine+'</td>'
							+'<td align="center">'+result[p].ukupnaKolicina+'</td>'
							+'<td align="center">'+result[p].cena+'</td>'
						+'</tr>'
                    )};
                   
            },
            error :function(e){
                alert('ne valja nesto');
            }
        });
    }
    prikaziMagKart();

    console.log('aaaaa');
}

function magacinskeKarticeTablesP(){

    var magacinskeKarticeTables = $("#magacinskeKarticeTables");

    magacinskeKarticeTables.show();
}

function nivelacija(){
    console.log("Nivelacija");
    var id = $("#idMagacinskeKartice").val();

    var formData = {
        "id": id
    }

    $.ajax({
        url:'http://localhost:8080/api/magacinska-kartica',
        type: 'PUT',
        contentType: 'application/json; charset=utf-8',
        data : JSON.stringify(formData),
        success: function(result){
            alert('Nivelacija je uspela!');
            prikaziOdredjenuMagacinskuKarticu(id);
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}

function prikaziOdredjenuMagacinskuKarticu(id){

   // alert('evo ga !!!!!');
    var magacinSifra = 0;
    var robaSifra = 0;
    var godinaSifra = 0;

    /*magacinSifra = $("#inputMagacinForMagKart").val();
    robaSifra = $("#inputRobaForMagKart").val();
    godinaSifra = $("#inputGodinaForMagKart").val();*/

    console.log("Funckija: "+id);
    $.ajax({
        type: "GET",
        contentType : 'application/json; charset=utf-8',
        // url : 'http://localhost:8080/api/magacinska-kartica/roba-ili-usluga/'+robaSifra+'/magacin/'+magacinSifra,
        url: 'http://localhost:8080/api/magacinska-kartica/'+id,
        success : function(result){
            var idMk = $("#idMagacinskeKartice");
            var nazivMagacinaMk = $("#nazivMagacinaMagKart");
            var nazivPreduzecaMk = $("#preduzeceMagKart");
            var poslovnaGodinaMk = $("#poslovnaGodina");
            var sifraRobeUslugeMk = $("#sifraRobeUsluge");
            var nazivRobeUslugeMk = $("#nazivRobeUsluge");
            var jedinicaMereMk = $("#jedinicaMere");
            var prosecnaCenaMk = $("#prosecnaCena");
            var pocetnoStanjeKolicnskiMk = $("#pocetnoStanjeKolicnski");
            var pocetnoStanjeVrednosnoMk = $("#pocetnoStanjeVrednosno");
            var prometUlazaKolicnskiMk = $("#prometUlazaKolicnski");
            var prometUlazaVrednosnoMk = $("#prometUlazaVrednosno");
            var prometIzlazaKolicnskiMk = $("#prometIzlazaKolicnski");
            var prometIzlazaVrednosnoMk = $("#prometIzlazaVrednosno");
            var ukupnaKorekcijaKolicineMk = $("#ukupnaKorekcijaKolicine");
            var ukupnaKorekcijaVrednosnoMk = $("#ukupnaKorekcijaVrednosno");
            var ukupnaKolicinaMk = $("#ukupnaKolicina");
            var ukupnaVrednostMk = $("#ukupnaVrednost");

            idMk.val(result.id);
            nazivMagacinaMk.val(result.nazivMagacina);
            nazivPreduzecaMk.val(result.nazivPreduzeca);
            poslovnaGodinaMk.val(result.brojPoslovneGodine);
            sifraRobeUslugeMk.val(result.robaIliUsluga);
            nazivRobeUslugeMk.val(result.nazivRobeIliUsluge);
            jedinicaMereMk.val(result.jedinicaMereDto);
            prosecnaCenaMk.val(result.cena);// nisam siguran je li ispravno
            pocetnoStanjeKolicnskiMk.val(result.pocetnoStanjeKolicinski);
            pocetnoStanjeVrednosnoMk.val(result.pocetnoStanjeVrednosno);
            prometUlazaKolicnskiMk.val(result.prometUlazaKolicinski);
            prometUlazaVrednosnoMk.val(result.prometUlazaVrednosno);
            prometIzlazaKolicnskiMk.val(result.prometIzlazaKolicinski);
            prometIzlazaVrednosnoMk.val(result.prometIzlazaVrednosno);
            ukupnaKorekcijaKolicineMk.val((result.pocetnoStanjeKolicinski + result.prometUlazaKolicinski - result.prometIzlazaKolicinski) - result.pocetnoStanjeKolicinski);
            ukupnaKorekcijaVrednosnoMk.val((result.pocetnoStanjeVrednosno + result.prometUlazaVrednosno - result.prometIzlazaVrednosno) - result.pocetnoStanjeVrednosno);
            ukupnaKolicinaMk.val(result.ukupnaKolicina);
            ukupnaVrednostMk.val(result.ukupnaVrednost);

            console.log(result.id + ' result');
            console.log(result.nazivPreduzeca + ' nazivPreduuzeca');
            odrediPrikaz("prikaziOvuKarticu");
            
                        
        },
        error :function(e){
            alert('ne valja nesto');
        }
    });
}

function prethodnaKartica(){
    var idTrenutne = $("#idMagacinskeKartice").val();
    var kartica;
    var prethodna;
    kartice.forEach(k => {
        if(k.id == idTrenutne){
            kartica = k;
        }
    });
    //console.log("Kartica: "+JSON.stringify(kartica))
    var index = kartice.indexOf(kartica);
    //console.log("Index: "+index)
    if(index == 0){
        prethodna = kartice[kartice.length-1];
    }else{
        prethodna = kartice[index-1];
    }
    prikaziOdredjenuMagacinskuKarticu(prethodna.id);
}

function sledecaKartica(){
    var idTrenutne = $("#idMagacinskeKartice").val();
    var kartica;
    var sledeca;
    kartice.forEach(k => {
        if(k.id == idTrenutne){
            kartica = k;
        }
    });
    var index = kartice.indexOf(kartica);
    //console.log("Index: "+index)
    if(index == kartice.length-1){
        sledeca = kartice[0];
    }else{
        sledeca = kartice[index+1];
    }
    prikaziOdredjenuMagacinskuKarticu(sledeca.id);
}

var selectedId;

function prikazSvihMagacinskihKartica() {

    
    var tabelaMK = $("#magacinskeKarticeTable");
    var tbodyMK = $("#tbodyMagacinskeKartice");
    
    function prikaziMagKart(){
        dajMagacine('selectMagacin', 0);
        dajRobuIliU('svaRobaU');
        dajPoslGod('svePoslGodine');
        $.ajax({
            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/magacinska-kartica",
            success : function(result){
                tabelaMK.show();
                tbodyMK.empty();
                for(p in result){
                    
                    tbodyMK.append(
                        '<tr>'
							+'<td align="center">'+'<a href="" id="prikaziJedno" onclick="prikaziOdredjenuMagacinskuKarticu('+result[p].id+')">'+result[p].nazivMagacina+'</a>'+'</td>'
							+'<td align="center">'+result[p].nazivRobeIliUsluge+'</td>'
							+'<td align="center">'+result[p].brojPoslovneGodine+'</td>'
							// +'<td align="center">'+result[p].ukupnaKolicina+'</td>'
							// +'<td align="center">'+result[p].pocetnoStanjeVrednosno+'</td>'
							// +'<td align="center">'+result[p].prometUlazaVrednosno+'</td>'
							// +'<td align="center">'+result[p].prometIzlazaVrednosno+'</td>'
							// +'<td align="center">'+result[p].ukupnaVrednost+'</td>'
							// +'<td align="center">'+result[p].cena+'</td>'
							+'<td/>'
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

function prikaziOdredjenuMagacinskuKarticu(id){

    alert('evo ga !!!!!');
    var magacinSifra = 0;
    var robaSifra = 0;
    var godinaSifra = 0;

    magacinSifra = $("#inputMagacinForMagKart").val();
    robaSifra = $("#inputRobaForMagKart").val();
    godinaSifra = $("#inputGodinaForMagKart").val();

    console.log(magacinSifra + " magacinSifra");
    magacinskeKarticeTablesP();
    $.ajax({
        type: "GET",
        contentType : 'application/json; charset=utf-8',
        // url : 'http://localhost:8080/api/magacinska-kartica/roba-ili-usluga/'+robaSifra+'/magacin/'+magacinSifra,
        url: 'http://localhost:8080/api/magacinska-kartica/'+id,
        success : function(result){
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
            prometIzlazaKolicnskiMk.val(result.prometUlazaKolicinski);
            prometIzlazaVrednosnoMk.val(result.prometIzlazaVrednosno);
            ukupnaKorekcijaKolicineMk.val((result.pocetnoStanjeKolicinski + result.prometUlazaKolicinski - result.prometUlazaKolicinski) - result.pocetnoStanjeKolicinski);
            ukupnaKorekcijaVrednosnoMk.val((result.pocetnoStanjeVrednosno + result.prometUlazaVrednosno - result.prometIzlazaVrednosno) - result.pocetnoStanjeVrednosno);
            ukupnaKolicinaMk.val(result.ukupnaKolicina);
            ukupnaVrednostMk.val(result.ukupnaVrednost);

            console.log(result.id + ' result');
            console.log(result.nazivPreduzeca + ' nazivPreduuzeca');
            
                        
        },
        error :function(e){
            alert('ne valja nesto');
        }
    });
}

function prikazSvihMagacinskihKartica() {

    var tabelaMK = $("#magacinskeKarticeTable");
    var tbodyMK = $("#tbodyMagacinskeKartice");
    
    odrediPrikaz('sveMagacinskeKartice');
    function prikaziMagKart(){
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
							+'<td align="center">'+'<a href="" id="prikaziJedno" result-prID="'+result[p].id+'">'+result[p].pocetnoStanjeKolicinski+'</a>'+'</td>'
							+'<td align="center">'+result[p].prometUlazaKolicinski+'</td>'
							+'<td align="center">'+result[p].prometIzlazaKolicinski+'</td>'
							+'<td align="center">'+result[p].ukupnaKolicina+'</td>'
							+'<td align="center">'+result[p].pocetnoStanjeVrednosno+'</td>'
							+'<td align="center">'+result[p].prometUlazaVrednosno+'</td>'
							+'<td align="center">'+result[p].prometIzlazaVrednosno+'</td>'
							+'<td align="center">'+result[p].ukupnaVrednost+'</td>'
							+'<td align="center">'+result[p].cena+'</td>'
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
    prikaziMagKart();

    console.log('aaaaa');
}
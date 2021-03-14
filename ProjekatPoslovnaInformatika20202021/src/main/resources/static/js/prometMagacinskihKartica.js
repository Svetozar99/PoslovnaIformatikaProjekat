function prikazSvihPrometaMagKartica() {

    var tabelaPrometaMK = $("#prometiMagacinskihKarticaTable");
    var tbodyPro = $("#tbodyProm");
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



function prikazMagacinskeKarticeTables(){

    var prikazMagacinskeKarticeTables = $("#prikazPromMagaKart");

    prikazMagacinskeKarticeTables.show();
}
function prikazSvihPrometaMagKartica() {
    
    var idMk = $("#idMagacinskeKartice").val();
    var sadrzajTabele = $("#tBodyPrometi");
    $("#preduzecePromMagKart").val($("#preduzeceMagKart").val());
    $("#proslovnaGodinaPromMagKart").val($("#poslovnaGodina").val());
    $("#sifraRobeIliUslugePromMagKart").val($("#sifraRobeUsluge").val());
    $("#nazivRobeIliUslugePromMagKart").val($("#nazivRobeUsluge").val());
    function prikaziPromete(){
        $.ajax({
            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/promet-magacinske-kartice/"+idMk,
            success : function(result){
                sadrzajTabele.empty();
                for(p in result){ 
                    var html="";
                    d=new Date(result[p].datum);
                    if(result[p].redniBroj.startsWith("0") || result[p].kolicina<0){
                        html = '<tr>'
                                +'<td align="center">'+result[p].redniBroj+'</td>'
                                +'<td align="center">'+result[p].vrstaPrometa+'</td>'
                                +'<td align="center">'+result[p].smer+'</td>'
                                +'<td align="center">'+result[p].kolicina+'</td>'
                                +'<td align="center">'+result[p].jedinicaMere+'</td>'
                                +'<td align="center">'+result[p].vrednost+'</td>'
                                +'<td align="center">'+result[p].cena+'</td>'
                                +'<td align="center">'+d.getDate()+'-'+d.getMonth()+'-'+d.getFullYear()+'</td>'
                                +'<td align="center"></td>'
                            +'</tr>'
                    }else{
                        html = '<tr>'
                                +'<td align="center">'+result[p].redniBroj+'</td>'
                                +'<td align="center">'+result[p].vrstaPrometa+'</td>'
                                +'<td align="center">'+result[p].smer+'</td>'
                                +'<td align="center">'+result[p].kolicina+'</td>'
                                +'<td align="center">'+result[p].jedinicaMere+'</td>'
                                +'<td align="center">'+result[p].vrednost+'</td>'
                                +'<td align="center">'+result[p].cena+'</td>'
                                +'<td align="center">'+d.getDate()+'-'+d.getMonth()+'-'+d.getFullYear()+'</td>'
                                +'<td align="center"><button onclick="storniranje(\''+result[p].redniBroj+'\')" class="btn btn-outline-warning">Storniraj</button></td>'
                            +'</tr>'
                    }

                    sadrzajTabele.append(html)};
                    
            },
            error :function(e){
                alert('ne valja nesto u prikazu svih prometa');
            }
        });
    }
    prikaziPromete();

    $('#btnIzvestajPrometa').show();

    console.log('aaaaa');
}

function formatDate(date) {
    var day = date.getDate();
    var monthIndex = date.getMonth();
    var year = date.getFullYear();
    
    var months = ["Januar", "Februar", "Mart", "April", "Maj", "Jun", "Jul", "Avgust", "Septembar", "Oktobar", "Novembar", "Decembar"];
    
    return year + "-" + months[monthIndex] + "-" + day;

}



function prikazPromMagacinskeKarticeTables(){

    console.log('evo me sad u funkciji u prometMagacinskihKartica');
    var prikazPrometaM = $("#prikazPromMagaKartice");

    prikazPrometaM.show();
}

function storniranje(redniBroj){

    $.ajax({
        type: "GET",
        contentType : 'application/json; charset=utf-8',
        url : 'http://localhost:8080/api/promet-magacinske-kartice/storniraj/'+redniBroj,
        success : function(result){
            alert('stornirano');
            prikazSvihPrometaMagKartica();
        },
        error :function(e){
            alert('ne valja nesto kod storniranja');
        }
    });
}

function prometMagKarticaIzvestaj(){
    var idMk = $("#idMagacinskeKartice").val();
    window.open('http://localhost:8080/api/magacinska-kartica/report/'+idMk);
    location.reload();
}
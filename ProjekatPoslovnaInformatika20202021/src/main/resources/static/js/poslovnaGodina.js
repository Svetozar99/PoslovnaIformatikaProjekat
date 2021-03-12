function prikazSvihPoslovnihGodina() {

    var tabelaPoslovnihGodina = $("#poslovneGodineTable");
    var tbodyPoslovnaGodina = $("#tbodyPoslovneGodine");
    
    function prikaziPoslovneGodine(){
        $.ajax({
            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/poslovna-godina",
            success : function(result){
            	tabelaPoslovnihGodina.show();
            	tbodyPoslovnaGodina.empty();
                for(pg in result){
                	tbodyPoslovnaGodina.append(
                        '<tr>'
                			
                			+'<td align="center">'+'<a href="" id="prikaziJednu" result-pgID="'+result[pg].id+'">'+result[pg].brojGodine+'</td>'
							+'<td align="center">'+result[pg].zakljucena+'</a>'+'</td>'
							+'<td align="center">'+result[pg].nazivPreduzeca+'</td>'
							
						+'</tr>'
                    )};
                    selectedId = $(this).attr('result-pgID');
                    
            },
            error :function(e){
                alert('ne valja nesto');
            }
        });
    }
    prikaziPoslovneGodine();

    console.log('poslovna godina');
}

function submitPoslovnaGodina(){

    var brojGodineInput = $("#brojGodine");
    var zakljucenaInput = $("#zakljucena");
    var i = zakljucenaInput.val() ? 0 : 1 ;
    var preduzeceInput = $("#preduzecePoslovnaGodina");

    var formData = {
        "brojGodine": brojGodineInput.val(),
        "zakljucena": i,
        "preduzece": preduzeceInput.val()
        
    }

    $.ajax({
        url : 'http://localhost:8080/api/poslovna-godina',
        type : "POST",
        contentType: 'application/json; charset=utf-8',
        data : JSON.stringify(formData),
        success: function(result){
            alert('Poslovna godina je uspesno dodata');
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}
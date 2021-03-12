function prikazSvihJedinicaMere() {

    var tabelaJedinicaMere = $("#jedinicaMereTable");
    var tbodyJedinicaMere = $("#tbodyJedinicaMere");
    
    function prikaziJediniceMere(){
        $.ajax({
            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/jedinica-mere",
            success : function(result){
            	tabelaJedinicaMere.show();
            	tbodyJedinicaMere.empty();
                for(jedinicaMere in result){
                	tbodyJedinicaMere.append(
                        '<tr>'
                			
                			+'<td align="center">'+result[jedinicaMere].id+'</td>'
							+'<td align="center">'+'<a href="" id="prikaziJedno" result-jedMerID="'+result[jedinicaMere].id+'">'+result[jedinicaMere].naziv+'</a>'+'</td>'
							+'<td align="center">'+result[jedinicaMere].skraceniNaziv+'</td>'
							
						+'</tr>'
                    )};
                    selectedId = $(this).attr('result-jedMerID');
                    
            },
            error :function(e){
                alert('ne valja nesto');
            }
        });
    }
    prikaziJediniceMere();

    console.log('jedinicaMere');
}

function submitJedinicaMere(){

    var nazivInput = $("#nazivJediniceMere");
    var skraceniNazivInput = $("#skraceniNaziv");

    var formData = {
        "naziv": nazivInput.val(),
        "skraceniNaziv": skraceniNazivInput.val()
        
    }

    $.ajax({
        url : 'http://localhost:8080/api/jedinica-mere',
        type : "POST",
        contentType: 'application/json; charset=utf-8',
        data : JSON.stringify(formData),
        success: function(result){
            alert('Jedinica mere je uspesno dodata');
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}
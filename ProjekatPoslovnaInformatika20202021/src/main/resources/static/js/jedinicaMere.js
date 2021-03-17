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
                            +'<td>'
	                            +'<button type="submit" class="btn btn-warning" style="margin-right: 5%;" onclick="editJedinicaMere('+result[jedinicaMere].id+')">IZMENI</button>'
	                            +'<button type="submit" class="btn btn-danger" onclick="deleteJedinicaMere('+result[jedinicaMere].id+')">OBRIŠI</button>'
	                        +'</td>'
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

function editJedinicaMere(id){
    $('#btnDodajJedinicuMere').show();
    $("#jedinicaMereTable").hide();
    $("#dodajJedinicuMere").show();
    $('#izmeniJedinicuMere').show();
    
    function prikaziJedinicuMere(){

        $.ajax({
            url:'http://localhost:8080/api/jedinica-mere/' + id,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',

            success: function(result){


                var nazivJM = $('#nazivJediniceMere');
                var skraceniNazivJM = $('#skraceniNaziv');

                nazivJM.val(result.naziv);
                skraceniNazivJM.val(result.skraceniNaziv);

                $("#izmeniJedinicuMere").on('click', function(event){

                    var naziv = nazivJM.val();
                    var skraceniNaziv = skraceniNazivJM.val();

                    var formData = {
                        "naziv": naziv,
                        "skraceniNaziv": skraceniNaziv

                    }

                    $.ajax({
                        url:'http://localhost:8080/api/jedinica-mere/' + id,
                        type: 'PUT',
                        contentType: 'application/json; charset=utf-8',
                        data : JSON.stringify(formData),
                        success: function(result){
                            alert('Jedinica mere je uspesno izmenjena!');
                        },
                        error : function(e){
                            alert('Doslo je do neke greške!')
                            console.log("ERROR: ", e);
                        }
                    });
                });

                },
            error : function(e){
                alert('Doslo je do neke greške!')
                console.log("ERROR: ", e);
            }

        });
    }

    prikaziJedinicuMere();
}

function deleteJedinicaMere(id){
    $.ajax({
        url:'http://localhost:8080/api/jedinica-mere/' + id,
        type: 'DELETE',
        contentType: 'application/json; charset=utf-8',
        success: function(result){
            prikazSvihJedinicaMere();
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}
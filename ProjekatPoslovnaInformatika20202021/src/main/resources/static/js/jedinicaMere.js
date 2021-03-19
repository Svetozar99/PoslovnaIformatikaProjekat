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
							+'<td align="center">'+result[jedinicaMere].naziv+'</a>'+'</td>'
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
    var greska = "";
    var nazivInput = "";
    var skraceniNazivInput = "";
    var nazivGreska;
    var skrNazivGreska;
    nazivInput = $("#nazivJediniceMere").val();
    skraceniNazivInput = $("#skraceniNaziv").val();

    if(nazivInput === ""){
        nazivGreska = true;
        greska += "\nMorate uneti naziv jedinice mere";
    }
    if(skraceniNazivInput === ""){
        skrNazivGreska = true;
        greska += "\nMorate uneti skraceni naziv jedinice mere";
    }
    if(nazivGreska || skrNazivGreska){
        alert(greska);
    }
    else{
        var formData = {
            "naziv": nazivInput,
            "skraceniNaziv": skraceniNazivInput
            
        }
    
        $.ajax({
            url : 'http://localhost:8080/api/jedinica-mere',
            type : "POST",
            contentType: 'application/json; charset=utf-8',
            data : JSON.stringify(formData),
            success: function(result){
                alert('Jedinica mere je uspesno dodata');
                odrediPrikaz('sveJediniceMere');
            },
            error : function(e){
                alert('Doslo je do neke greške!')
                console.log("ERROR: ", e);
            }
        });
    }
}

function editJedinicaMere(id){
    $('#btnDodajJedinicuMere').show();
    $("#jedinicaMereTable").hide();
    $("#dodajJedinicuMere").show();
    $('#izmeniJedinicuMere').show();
    $('#btnDodajJedinicuMere').hide();
    
    function prikaziJedinicuMere(){

        $.ajax({
            url:'http://localhost:8080/api/jedinica-mere/' + id,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',

            success: function(result){


                var nazivJM = $('#nazivJediniceMere');
                var skraceniNazivJM = $('#skraceniNaziv');
                var idJediniceMereUpdate = $('#idJediniceMereUpdate');

                idJediniceMereUpdate.val(result.id);
                nazivJM.val(result.naziv);
                skraceniNazivJM.val(result.skraceniNaziv);

                

                },
            error : function(e){
                alert('Doslo je do neke greške!')
                console.log("ERROR: ", e);
            }

        });
    }

    prikaziJedinicuMere();
}

function submitUpdateJedinicaMere(){

    var naziv = $('#nazivJediniceMere').val();
    var skraceniNaziv = $('#skraceniNaziv').val();
    var id = $('#idJediniceMereUpdate').val();
    

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
            odrediPrikaz('sveJediniceMere');
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}

function deleteJedinicaMere(id){
    $.ajax({
        url:'http://localhost:8080/api/jedinica-mere/' + id,
        type: 'DELETE',
        contentType: 'application/json; charset=utf-8',
        success: function(result){
            prikazSvihJedinicaMere();
            odrediPrikaz('sveJediniceMere');
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}
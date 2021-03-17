function prikazSvihMagacina() {

    var tabelaMagacina = $("#magaciniTable");
    var tbodyMagacin = $("#tbodyMagacin");
    
    function prikaziMagacine(){
        $.ajax({
            type: "GET",
            contentType : 'application/json; charset=utf-8',
            url : "http://localhost:8080/api/magacin",
            success : function(result){
            	tabelaMagacina.show();
            	tbodyMagacin.empty();
                for(magacin in result){
                	tbodyMagacin.append(
                        '<tr>'
                			
                			+'<td align="center">'+result[magacin].id+'</td>'
							+'<td align="center">'+'<a href="" id="prikaziJedno" result-magID="'+result[magacin].id+'">'+result[magacin].naziv+'</a>'+'</td>'
							+'<td align="center">'+result[magacin].nazivPreduzeca+'</td>'
                            +'<td>'
	                            +'<button type="submit" class="btn btn-warning" style="margin-right: 5%;" onclick="editMagacin('+result[magacin].id+')">IZMENI</button>'
	                            +'<button type="submit" class="btn btn-danger" onclick="deleteMagacin('+result[magacin].id+')">OBRIŠI</button>'
                            +'</td>'
							
						+'</tr>'
                    )};
                    selectedId = $(this).attr('result-magID');
                    
            },
            error :function(e){
                alert('ne valja nesto');
            }
        });
    }
    prikaziMagacine();

    console.log('magacin');
}

function submitMagacin(){

    var nazivInput = $("#nazivMagacina");
    var preduzeceInput = $("#preduzece");

    var formData = {
        "naziv": nazivInput.val(),
        "preduzece": preduzeceInput.val()
        
    }

    $.ajax({
        url : 'http://localhost:8080/api/magacin',
        type : "POST",
        contentType: 'application/json; charset=utf-8',
        data : JSON.stringify(formData),
        success: function(result){
            alert('Magacin je uspesno dodat');
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}

/*function selectPreduzecaMagacin(preduzeca){
    var selectPreduzeceMagacin = $("#selectPreduzeceMagacin");
    selectPreduzeceMagacin.empty();
    var html = '<label>Izaberite preduzeceeee:';
    html += '<select class="form-control" id="preduzece">';
    preduzeca.forEach(preduzece => {
        html += '<option value="' + preduzece.id + '">' + preduzece.naziv + '</option>'
    });
    html += '</select>';
    html += '</label>';
    selectPreduzeceMagacin.append(html);
}*/

function editMagacin(id){
    $('#btnDodajMagacin').show();
    $("#magacinTable").hide();
    $("#dodajMagacin").show();
    $('#izmeniMagacin').show();
    
    function prikaziMagacin(){

        $.ajax({
            url:'http://localhost:8080/api/magacin/' + id,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',

            success: function(result){
            	dajPreduzeca("selectPreduzeca");
                
                var nazivM = $("#nazivMagacina");
                var preduzeceM = $("#preduzece");

                nazivM.val(result.naziv);
                preduzeceM.val(result.nazivPreduzeca);


                $("#izmeniMagacin").on('click', function(event){
                        
                    var naziv = nazivM.val();
                    var preduzece = preduzeceM.val();

                    var formData = {
                        "naziv": naziv,
                        "preduzece": preduzece
                    }

                    $.ajax({
                        url:'http://localhost:8080/api/magacin/' + id,
                        type: 'PUT',
                        contentType: 'application/json; charset=utf-8',
                        data : JSON.stringify(formData),
                        success: function(result){
                            alert('Magacin je uspesno izmenjen!');
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

    prikaziMagacin();
}

function deleteMagacin(id){
    $.ajax({
        url:'http://localhost:8080/api/magacin/' + id,
        type: 'DELETE',
        contentType: 'application/json; charset=utf-8',
        success: function(result){
            prikazSvihMagacina();
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}
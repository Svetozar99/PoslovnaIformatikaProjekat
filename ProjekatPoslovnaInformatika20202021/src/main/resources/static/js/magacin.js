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
							+'<td align="center">'+result[magacin].naziv+'</a>'+'</td>'
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
    var greska = "";
    var nazivInput = "";
    nazivInput = $("#nazivMagacina").val();
    var preduzeceInput = $("#preduzece");

    var nazivGreska;

    if(nazivInput === ""){
        nazivGreska = true;
        greska += "\nMorate uneti naziv magacina";
    }
    if(nazivGreska){
        alert(greska);
    }else{
        var formData = {
            "naziv": nazivInput,
            "preduzece": preduzeceInput.val()
        }
        $.ajax({
            url : 'http://localhost:8080/api/magacin',
            type : "POST",
            contentType: 'application/json; charset=utf-8',
            data : JSON.stringify(formData),
            success: function(result){
                alert('Magacin je uspesno dodat!');
                odrediPrikaz('sviMagacini');
            },
            error : function(e){
                alert('Doslo je do neke greške!')
                console.log("ERROR: ", e);
            }
        });
    }
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
    $("#magaciniTable").hide();
    $("#dodajMagacin").show();
    $('#izmeniMagacin').show();
    $('#btnDodajMagacin').hide();
    
    function prikaziMagacin(){

        $.ajax({
            url:'http://localhost:8080/api/magacin/' + id,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',

            success: function(result){
                
            	dajPreduzeca("",result.preduzece);
                
                var nazivM = $("#nazivMagacina");
                var idMagacinUpdate = $("#idMagacinUpdate");

                nazivM.val(result.naziv);
                idMagacinUpdate.val(result.id);

                },
            error : function(e){
                alert('Doslo je do neke greške!')
                console.log("ERROR: ", e);
            }

        });
    }

    prikaziMagacin();
}

function submitUpdateMagacin(){
                        
    var naziv = $("#nazivMagacina").val();
    var preduzece = $("#preduzece").val();
    var id = $("#idMagacinUpdate").val();

    console.log("Id: "+id)

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
            odrediPrikaz('sviMagacini');
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}

function deleteMagacin(id){
    $.ajax({
        url:'http://localhost:8080/api/magacin/' + id,
        type: 'DELETE',
        contentType: 'application/json; charset=utf-8',
        success: function(result){
            alert("Magacin je obrisan!");
            odrediPrikaz('sviMagacini');
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });
}
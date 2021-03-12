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
							+'<td>'
                                +'<button type="submit" class="btn btn-warning" style="margin-right: 5%;" onclick="editPoslovnaGodina('+result[pg].id+')">IZMENI</button>'
                                // +'<button type="submit" class="btn btn-danger" onclick="deletePreduzece('+result[preduzece].id+')">OBRIŠI</button>'
                            +'</td>'
						+'</tr>'
                    )};
                    
                    
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
    var zakljucenaInput = $("#zakljucenaGod");
    var preduzeceInput = $("#preduzecePoslovnaGodina");

    var formData = {
        "brojGodine": brojGodineInput.val(),
        "zakljucena": zakljucenaInput.is(":checked"),
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

function editPoslovnaGodina(id){
    $('#btnDodajPoslovnuGodinu').show();
    $("#poslovneGodineTable").hide();
    $("#dodajPoslovnuGodinu").show();
    $('#updatePoslovnaGodina').show();
    function prikaziPoslovnuGodinu(){

        $.ajax({
            url:'http://localhost:8080/api/poslovna-godina/' + id,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            // data : JSON.stringify(formData),
            success: function(result){
                dajPreduzeca("selectPreduzeca");

                var brojGodineP = $('#brojGodine');
                var zakljucenaP = $('#zakljucena');
                var preduzeceP = $('#preduzecePoslovnaGodina');

                brojGodineP.val(result.brojGodine);
                // zakljucenaP.val(result.zakljucena.is(":checked"));
                preduzeceP.val(result.nazivPreduzeca);
                zakljucenaP.prop('checked', result.zakljucena);

                $("#updatePoslovnaGodina").on('click', function(event){

                    var brojGodine = brojGodineP.val();
                    var preduzece = preduzeceP.val();
                    var zakljucena = zakljucenaP.is(":checked");
                    

                    // alert('broj godine ' + brojGodineP);
                    // alert('zakljucena ' + result.zakljucena);
                    // alert('preduzece '+ preduzeceP);

                    var formData = {
                        'brojGodine': brojGodine,
                        "zakljucena": zakljucena,
                        "preduzece": preduzece,
                    }

                    $.ajax({
                        url:'http://localhost:8080/api/poslovna-godina/' + id,
                        type: 'PUT',
                        contentType: 'application/json; charset=utf-8',
                        data : JSON.stringify(formData),
                        success: function(result){
                            alert('Poslovna godina uspjesno izmjenjena');
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

    prikaziPoslovnuGodinu();
}
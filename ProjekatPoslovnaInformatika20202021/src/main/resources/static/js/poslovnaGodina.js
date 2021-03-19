var cekirana = $("#cekirana");

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
                			
                			+'<td align="center">'+result[pg].brojGodine+'</td>'
							+'<td align="center">'+result[pg].zakljucena+'</a>'+'</td>'
							+'<td align="center">'+result[pg].nazivPreduzeca+'</td>'
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
    $("#cekirana").hide();
    $("#nijeCekirana").show();

    var brojGodineInput = $("#brojGodine");
    var zakljucenaInput = $("#zakljucenaGod");
    var preduzeceInput = $("#preduzecePoslovnaGodina");

    var formData = {
        "brojGodine": brojGodineInput.val(),
        "zakljucena": zakljucenaInput.is(":checked"),
        "preduzece": preduzeceInput.val()
    }

    alert(JSON.stringify(formData));

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
                dajPreduzeca("",result.idPreduzeca);

                var brojGodineP = $('#brojGodine');
                var zakljucenaP;
                var preduzeceP = $('#preduzecePoslovnaGodina');
                brojGodineP.val(result.brojGodine);
                preduzeceP.val(result.nazivPreduzeca);
                if (result.zakljucena == true)
                {
                    zakljucenaP = $("#cekirana")
                    $("#nijeCekirana").hide();
                    $("#cekirana").show();
                }
                else if (result.zakljucena == false)
                {
                    zakljucenaP = $("#nijeCekirana");
                    $("#cekirana").hide();
                    $("#nijeCekirana").show();
                }   
                
                $("#updatePoslovnaGodina").on('click', function(event){

                    var brojGodine = brojGodineP.val();
                    var preduzece = preduzeceP.val();
                    var zakljucena = zakljucenaP;

                    var formData = {
                        'brojGodine': brojGodine.val(),
                        "zakljucena": zakljucena.is(":checked"),
                        "preduzece": preduzece.val(),
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
                            alert('Doslo je do neke greške u put-u!')
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

function deletePoslovnaGodina(id){
    $.ajax({
        url:'http://localhost:8080/api/poslovna-godina/' + id,
        type: 'DELETE',
        contentType: 'application/json; charset=utf-8',
        success: function(result){
            alert('Poslovna godina uspjesno obrisana');
            prikazSvihPoslovnihGodina();
        },
        error : function(e){
            alert('Doslo je do neke greške kod brisanja!')
            console.log("ERROR: ", e);
        }
    });
}
poslovneGodine = [];
function dajPoslGod(text){
    $.ajax({
        type: "GET",
        contentType : 'application/json; charset=utf-8',
        url : "http://localhost:8080/api/poslovna-godina",
        success : function(result){
            if(text === "svePoslGodine"){
                selectPoslGod(result);
            }else{
                poslovneGodine = result;
            }
        },
        error :function(e){
            console.log("Greska!!!");
        }
    });
}

function selectPoslGod(list){
    console.log("selectPoslGod")
    posG=list;
   
    var inputGodinaForMagKart = $("#inputGodinaForMagKart");
    var html = "";
    html += '<option value="0"></option>';
    list.forEach(posG => {
        html += '<option value="' + posG.brojGodine + '">' + posG.brojGodine + '</option>';
    });

    inputGodinaForMagKart.empty();
    inputGodinaForMagKart.append(html);

}
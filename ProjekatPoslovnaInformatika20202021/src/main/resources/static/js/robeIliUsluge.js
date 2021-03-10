var robeUsluge = [];

function dajRobuIliUsluge(text){
    $.ajax({
        type: "GET",
        contentType : 'application/json; charset=utf-8',
        url : "http://localhost:8080/api/roba-ili-usluga",
        success : function(result){
            if(text === "svaRobaIliUsluge"){
                prikaziRobeIliUsluge(result);
            }else{
                robeUsluge = result;
            }
        },
        error :function(e){
            console.log("Greska!!!");
        }
    });
}

function dajJediniceMere(){
    var selectJedinicaMere = $("#selectJedinicaMere");
    selectJedinicaMere.empty();
    var html = '<label>Izaberite jedinicu mere:';
    html += '<select class="form-control" id="inputJedinicaMere">';
    $.ajax({
        type: "GET",
        contentType : 'application/json; charset=utf-8',
        url : "http://localhost:8080/api/jedinica-mere",
        success : function(result){
            result.forEach(jedinicaMere => {
                html += '<option value="' + jedinicaMere.id + '">' + jedinicaMere.naziv + '</option>'
            });
            html += '</select>';
            html += '</label>';
            selectJedinicaMere.append(html);
        },
        error :function(e){
            console.log("Greska!!!");
        }
    });
}

function selectPreduzeca(preduzeca){
    var selectPreduzece = $("#selectPreduzece");
    selectPreduzece.empty();
    var html = '<label>Izaberite preduzece:';
    html += '<select class="form-control" id="inputPreduzece">';
    preduzeca.forEach(preduzece => {
        html += '<option value="' + preduzece.id + '">' + preduzece.naziv + '</option>'
    });
    html += '</select>';
    html += '</label>';
    selectPreduzece.append(html);
}

function submitRobaUsluga(){

    var naziv = $("#inputNazivRobaUsluga");
    var preduzece = $("#inputPreduzece");
    var jedinicaMere = $("#inputJedinicaMere");
    var formData = {
        "naziv": naziv.val(),
        "preduzece": preduzece.val(),
        "jedinicaMere": jedinicaMere.val()
    }

    $.ajax({
        url : 'http://localhost:8080/api/roba-ili-usluga',
        type : "POST",
        contentType: 'application/json; charset=utf-8',
        data : JSON.stringify(formData),
        success: function(){
            alert('Roba ili usluga uspesno dodata');
        },
        error : function(e){
            alert('Doslo je do neke greške!')
            console.log("ERROR: ", e);
        }
    });

}

function prikaziRobeIliUsluge(list){
    var tbodyRobaUsluga = $('#tbodyRobaUsluga');
    var html='';
    tbodyRobaUsluga.empty();
    list.forEach(ru => {
        html='<tr>';
        html +=     '<td align="center">'+ru.sifra+'</td>';
        html +=     '<td align="center">';
        html +=         '<a href="" id="prikaziJedno" result-prID="'+ru.sifra+'">';
        html +=             ru.naziv;
        html +=         '</a>';
        html +=     '</td>';
        html +=     '<td align="center">'+ru.jedinicaMere+'</td>';
        html +=   '</tr>';
        tbodyRobaUsluga.append(html);
    });
}
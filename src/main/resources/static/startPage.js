function loadSelects(){
    let select = document.getElementById('form');
    $.ajax({
        url: "http://localhost:8080/accounts",
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            for (let i=0;i<data.length;i++){
                let option = document.createElement("option");
                option.text = data[i].name+"@"+data[i].address;
                option.value = data[i].id;
                select.appendChild(option);
            }
        }
    });
}

function connect(){
    var password = document.getElementById("password").value;
    var result = document.getElementById("form");
    var id = result.options[result.selectedIndex].value;
    $.ajax({
        type: "POST",
        url: "/getConnection",
        contentType: "application/json",
        dataType: 'text',
        data:JSON.stringify({
            id:id,
            password:password
        }),
        error:function () {
            alert('Bad password');
        },
        success:function (){
            alert('Correct password')
        }
    });
}
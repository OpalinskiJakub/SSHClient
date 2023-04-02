function createAccount() {
    var name = document.getElementById("name").value;
    var address = document.getElementById("address").value;
    $.ajax({
        type: "POST",
        url: "/accounts",
        contentType: "application/json",
        dataType: 'json',
        data: JSON.stringify({
            name: name,
            address: address
        }),
        success: function () {
            alert('Created');
        }
    });
}

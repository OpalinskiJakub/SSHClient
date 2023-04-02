const socket = new WebSocket("ws://localhost:8080/name");

var terminal = new Terminal()
terminal.open(document.getElementById("terminal"));

socket.onmessage = function (response)  {
    terminal.write(response.data);
}

function clear(request) {
    for (var i=0;i<request.length;i++) {
        terminal.write('\b \b');
    }
}

function main() {

    request = '';

    terminal.onData(function (data) {
        if(data=='\u007F') {
            if (request.length>0) {
                request = request.slice(0, request.length - 1);
                terminal.write('\b \b');
            }
        }else if(data=='\r') {
            clear(request);
            socket.send(request + '\n');
            request = '';
        } else if(data>=String.fromCharCode(32)&&data<=String.fromCharCode(126)){
            request += data;
            terminal.write(data);
        }
    });
}


main();

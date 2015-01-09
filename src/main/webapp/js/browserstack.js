
var registerUrl = "/node/register";
var connectUrl = "/node/connect"

var webSocket;
var nodeId ;
var host = 'ws://' + location.host;

function boot() {
	register();
    connect();
}

function jsonCallback() {

}

function register() {
	$.get(registerUrl, function(data) {
		nodeId = data.nodeId;
	});
}

function connect() {
   webSocket = new WebSocket(host + connectUrl);
   webSocket.onmessage = onMessage;
   webSocket.onclose = onClose;
}

function onMessage(data) {
	var eventType = data.eventType;
	switch(eventType) {
    	case "SubmitTask":
    		onSubmitTask(data.payload);
        	break;

    }
}

function onSubmitTask(payload) {
	
}

function onClose(data) {

}




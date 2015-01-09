
var registerUrl = "/register";
var connectUrl = "/node/connect"

var webSocket;
var nodeId ;
var host = 'ws://' + location.host;

function boot() {
	log("Registering client...");
	register();
	log("Establishing web socket connection...");
    connect();
}

function jsonCallback() {

}

function register() {
	$.get(registerUrl, function(data) {
		nodeId = data.nodeId;
		log("Node registration id: " + nodeId);
	});
}

function connect() {
   webSocket = new WebSocket(host + connectUrl);
   webSocket.onmessage = onMessage;
   webSocket.onclose = onClose;
}

function onMessage(data) {
	var eventType = data.eventType;
	log("Received message for event type: " + eventType);
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

function log(message) {
	$('textarea[name="console"').append("\n");
	$('textarea[name="console"').append(message);
}

function clearConsole() {
	$('textarea[name="console"]').val('');
}




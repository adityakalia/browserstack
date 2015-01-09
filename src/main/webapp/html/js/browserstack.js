
var registerUrl = "/register";
var submitUrl = "/submit";
var gettaskUrl = "/gettask";
var taskResponseUrl = "/taskresponse";

var nodeId;
var jobId;
var sequence;

function boot() {
	log("Registering client...");
	register();
	poll();
}

function register() {
	$.get(registerUrl, function(data) {
		nodeId = data.nodeId;
		log("Node registration id: " + nodeId);
	});
}

function poll() {
    log("Polling tasks...");
    setTimeout(function(){
        $.get(gettaskUrl, function(task) {
            if(task != null) {
                onTaskReceived(task);
            }
        });
        poll();
    }, 3000);
}

function onTaskReceived(task) {
	var script = task.script;
	var data = task.data;
	jobId = task.jobId;
	sequence = task.sequence;
	log("Task received: " + task);

	var dynamicScript = '<script type="text/javascript">' + 'var data = ' + data + '";' + script + '</script>';
	eval(dynamicScript);
}

function sendRespone(response) {
    var data =  {
        'nodeId': nodeId,
        'jobId': jobId,
        'sequence': sequence,
        'response': response
    };

    $.ajax({
            type: "POST",
            url: taskResponseUrl,
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        });
}

function log(message) {
	$('textarea[name="console"').append("\n");
	$('textarea[name="console"').append(message);
}

function clearConsole() {
	$('textarea[name="console"]').val('');
}

function submitTask() {
	var data = {
        'name': $('input[name="jobName"').val(),
        'mapperScript': $('textarea[name="taskcode"').val(),
        'reducerScript': $('textarea[name="reducecode"').val(),
        'data': $('textarea[name="inputdata"').val()
    };
    $.ajax({
        type: "POST",
        url: submitUrl,
        data: JSON.stringify(data),
        dataType: 'json',
        contentType: 'application/json; charset=utf-8'
    });
}




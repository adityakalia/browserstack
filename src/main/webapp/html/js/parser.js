var resultDataMap = new Map();
var obj = {};
init();
function init() {
	console.log("Starting...")
	loadDataFromURL();
}

function loadDataFromURL() {
	var urlList = ["http://www.html5rocks.com/", "http://www.google.com",  "http://www.rediff.com"];
	
       for (var i = 0; i < urlList.length; i++) {
	  fetchDataViaHttp(urlList[i]); 
	}
}

function fetchDataViaHttp(Url) {

	var req;
	var ua = navigator.userAgent.toLowerCase();
	if (!window.ActiveXObject)
		req = new XMLHttpRequest();
	else if (ua.indexOf('msie 5') == -1)
		req = new ActiveXObject("Msxml2.XMLHTTP");
	else
		req = new ActiveXObject("Microsoft.XMLHTTP");

	req.open('GET', Url, false);
	req.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	req.send(null);
	if (req.status == 200) {
		var e = document.getElementById("result");
		if (e)
			e.innerHTML = req.responseText;
		var doc = new DOMParser()
				.parseFromString(req.responseText, 'text/html');
		// alert(doc.querySelector('body').innerHTML);
		var items = doc.getElementsByTagName("*");// get all tags
		var data;
		for (var i = 0; i < items.length; i++) {
			data = data + " " + items[i].innerText.trim();
		}
		splitStringData(data);
	} else {
		alert("Failed!");
	}
}

function splitStringData(data) {
	data = data.replace(/[0-9]/g, '');
	data = data.replace(/[^a-zA-Z]/g, " ");
	// console.log(data);
	var results = data.split(' ');

	storeInMap(results);
}

function storeInMap(data) {

	for (var i = 0; i < data.length; i++) {
		var word = data[i].trim();
		if (word.length > 3) {
			console.log(i + " " + word + " ")
			if (resultDataMap.has(word)) {
				var count = resultDataMap.get(word);
				resultDataMap.set(word, ++count);
				obj[word] = count;
			} else {
				resultDataMap.set(word, 1);
				obj[word] = 1;
			}

		}

	}
	console.log("Map Size:" + resultDataMap.size);
	sendResponse(obj);
}

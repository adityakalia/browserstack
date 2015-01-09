
 var resultDataMap = new Map();

function init(){
    console.log("Starting...")
    loadDataFromURL();
}

function loadDataFromURL(){
    var data = ['http://news.google.co.in/', 'http://www.html5rocks.com/en/search?q=cloud', 'http://en.wikipedia.org/wiki/Cloud_computing','http://www.informationweek.in/category/cloud-computing','http://www.ibm.com/cloud-computing/in/en/iaas.html'];
      for (var i = 0; i < data.length; i++) {
      console.log("========finding="+data[i]);
      fetchDataViaHttp(data[i]);
    }
   // fetchDataViaHttp(URL);
}

function fetchDataViaHttp(Url){

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
   // req.setRequestHeader('Access-Control-Allow-Origin', '*');
    req.send(null);
    if(req.status == 200){
            var e = document.getElementById("result");
            if(e)e.innerHTML = req.responseText;
            var doc = new DOMParser().parseFromString(req.responseText, 'text/html');
          //  alert(doc.querySelector('body').innerHTML);
          var items = doc.getElementsByTagName("*");//get all tags
          var data;
              for (var i = 0; i < items.length; i++) {
                data = data+" "+  items[i].innerText.trim();
              }
              splitStringData(data);
    }else{
         alert("Failed!");
    }
}


function splitStringData(data){
    data = data.replace(/[0-9]/g, '');
    data = data.replace(/[^a-zA-Z]/g, " ");
    //console.log(data);
    var results = data.split(' ');

    storeInMap(results);
}

function storeInMap(data){

     for (var i = 0; i < data.length; i++) {
         var word =  data[i].trim();
         if(word.length>3){
            console.log(i+" "+word+" ")
            if(resultDataMap.has(word)){
             var count = resultDataMap.get(word);
                 resultDataMap.set(word, ++count);
            }else{
                 resultDataMap.set(word, 1);
            }

         }

     }
      console.log("Map Size:"+resultDataMap.size);

    // return resultDataMap;
}

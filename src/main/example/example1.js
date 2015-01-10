Case Run two nodes.
-------------------

Mapper Code:

var sum = data.a + data.b;
var result = {"sum": sum};
alert(sum);
sendResponse(result);


Reducer Code:
var total = responseData[0].value + responseData[1].value;
return JSON.stringify({"total": total});

Data:
{"a":10, "b": 30}
{"a":2, "b": 10}
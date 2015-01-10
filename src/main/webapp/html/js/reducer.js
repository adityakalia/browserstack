var keyes = []; var values = []; var result = '';
			 for (i = 0; i < responseData.length; i++) { 
			  var pair = responseData[i];
			 var index = keyes.indexOf(pair.key);
			  if(index >=0) { values[index] += pair.value; }
		      else { keyes.push(pair.key); values.push(pair.value);}
			 }
			 for (i = 0; i < keyes.length; i++) { 
			 result = result + ' ' + keyes[i]  + ':' + values[i] ;
			 }
			 return result;

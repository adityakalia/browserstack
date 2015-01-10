package org.hackathon.elite7.service;

//import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.hackathon.elite7.model.Pair;
import org.springframework.stereotype.Service;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JavaScriptService {

	public Object process(String script, List<Pair> pairList) {
		// create a script engine manager
		ScriptEngineManager factory = new ScriptEngineManager();
		// create a JavaScript engine
		ScriptEngine engine = factory.getEngineByName("JavaScript");
		// evaluate JavaScript code from String
		script = "(function() {" + this.generateData(pairList) + script + "}())";
		System.out.println("EXECUTING REDUCER SCRIPT...");
		try {
			String obj = (String)engine.eval(script);
			if (obj.length() > 100) 
				obj = obj.substring(0, 100) + "....";
			System.out.println("EXECUTION RESPONSE " + obj);
			return obj;
		} catch (ScriptException e) {
			throw new RuntimeException(e);
		}
	}
	
	private String generateData(List<Pair> pairList) {
		StringBuilder builder = new StringBuilder();
		builder.append("var responseData = [");
		for (Pair p : pairList) { 
			builder.append(p.toString());
			builder.append(",");
		}
		//    remove , at  end
		String str = builder.toString();
		str = str.substring(0, str.length()-1);
		return str + "];";
	}

//	public static void main(String[] args) {
//		JavaScriptService service = new JavaScriptService();
//		List<Pair> pairs = new ArrayList<Pair>(){{
//			add(new Pair("show", "34"));
//			add(new Pair("show", "34"));
//			add(new Pair("hide", "34"));
//			add(new Pair("hide", "34"));
//		}};
//		String script = "var keyes = []; var values = [];"
//			+ "for (i = 0; i < responseData.length; i++) { "
//			+ " var pair = responseData[i];"
//			+ "var index = keyes.indexOf(pair.key);"
//			+ " if(index >=0) { values[index] += pair.value; }"
//		    + " else { keyes.push(pair.key); values.push(pair.value);}"
//			+ "}"
//			+ "return keyes;";
//
//		ScriptObjectMirror result = (ScriptObjectMirror) service.process(script, pairs);
//		System.out.println(result.getSlot(1));
//	}
}
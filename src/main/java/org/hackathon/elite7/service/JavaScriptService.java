package org.hackathon.elite7.service;

import java.util.List;

import javax.script.*;

import org.hackathon.elite7.model.Pair;
import org.springframework.stereotype.Service;

@Service
public class JavaScriptService {

	public Object process(String script, List<Pair> pairList) {
		// create a script engine manager
		ScriptEngineManager factory = new ScriptEngineManager();
		// create a JavaScript engine
		ScriptEngine engine = factory.getEngineByName("JavaScript");
		// evaluate JavaScript code from String
		script = this.generateData(pairList) + script;
		
		System.out.println("EXECUTING REDUCER SCRIPT...");
		try {
			Object obj = engine.eval(script);
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
}
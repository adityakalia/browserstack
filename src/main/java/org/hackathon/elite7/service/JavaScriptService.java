package org.hackathon.elite7.service;

import javax.script.*;

import org.springframework.stereotype.Service;

@Service
public class JavaScriptService {
	
	public Object process(String script) throws ScriptException {
		 // create a script engine manager
        ScriptEngineManager factory = new ScriptEngineManager();
        // create a JavaScript engine
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        // evaluate JavaScript code from String
        return engine.eval(script);
	}
}
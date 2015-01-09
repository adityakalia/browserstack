package org.hackathon.elite7.controller;

import java.util.HashMap;
import java.util.Map;

import org.hackathon.elite7.model.Task;
import org.hackathon.elite7.model.TaskResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hackathon.elite7.model.Job;
import org.hackathon.elite7.service.EngineService;
import org.hackathon.elite7.service.TrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class BrowserStackController {

	@Autowired
	private TrackerService tracker;
	
	@Autowired
	private EngineService engine;

	@RequestMapping(value = "/register")
	public Map<String, String> register(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String id = session.getId();
		tracker.register(id);

		Map<String, String> responseMap = new HashMap<String, String>();
		responseMap.put("nodeId", id);
		return responseMap;
	}

	@RequestMapping(value = "/submit")
	public String submitJob(@RequestBody Job job) {
		engine.pushJob(job);
		return "Job Started";
	}
	
	@RequestMapping(value = "/getTask")
	public Task getTask(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String nodeId = session.getId();
		tracker.setBusy(nodeId);
		return engine.getTask();
	}
	
	@RequestMapping(value = "/taskResponse", method=RequestMethod.POST)
	public void taskResponse(@RequestBody TaskResponse taskResponse) {
		System.out.println("RESPONSE RECEIVED FOR JOB " + taskResponse.getJobId());
		System.out.println("RESPONSE " + taskResponse.getResponse());

		engine.processTaskResponse(taskResponse);
	}
}
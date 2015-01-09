package org.hackathon.elite7.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hackathon.elite7.model.*;

@Service
public class EngineService {

	@Autowired
	private TrackerService tracker;
	@Autowired
	private JavaScriptService jsService;
	private Job currentJob;
	private Queue<Task> taskQueue = new LinkedList<Task>();
	private List<TaskResponse> taskResponseList = new ArrayList<TaskResponse>();
	private int currentTaskSize;

	public void pushJob(Job job) {
		UUID jobId = UUID.randomUUID();
		System.out.println("JOB ID " + jobId);
		job.setId(jobId.toString());
		this.currentJob = job;
		List<String> nodes = tracker.getAvailableNodes();
		if (nodes.size() > 0) {
			List<Task> tasks = this.getMapperTasks(job, nodes.size());
			this.currentTaskSize = tasks.size();
			for (Task task : tasks) {
				taskQueue.add(task);
			}
		} else {
			System.out.println("No nodes Available");
		}
	}

	public Task getTask() {
		Task t = null;
		if (!taskQueue.isEmpty())
			t = taskQueue.poll();
		return t;
	}

	public void processTaskResponse(TaskResponse taskResponse) {
		taskResponseList.add(taskResponse);
		if (taskResponseList.size() == this.currentTaskSize) {
			aggregateTaskResponses(this.taskResponseList);
		}
	}

	public void aggregateTaskResponses(List<TaskResponse> responseList) {
		
		System.out.println(" ============= AGGREGATING AND SORTING =============");
		List <Pair> pairList = new ArrayList<Pair>();
		for (TaskResponse response : responseList) 
			for (Map.Entry<String, String> entry : response.getResponse().entrySet())
				pairList.add(new Pair(entry.getKey(), entry.getValue()));
				
		Collections.sort(pairList);
		for (Pair p : pairList)
			System.out.println(p.getKey() + " = " + p.getValue());
		
		jsService.process(currentJob.getReducerScript(), pairList);
	}

	List<Task> getMapperTasks(Job job, int batchSize) {
		System.out.println("JOB DATA" + job.getData());
		String lines[] = job.getData().split("\\r?\\n");
		int size = lines.length / batchSize;
		System.out.println("BATCH SIZE " + batchSize);
		System.out.println("SIZE " + size);
		// logic has a bug where it ignores the residuals
		// less than batch size
		List<Task> tasks = new ArrayList<Task>();
		for (int i = 0; i < batchSize; i++) {
			Task task = new Task(job.getId(), i, job.getMapperScript(),
					Arrays.copyOfRange(lines, i * size, (i * size) + size));
			tasks.add(task);
		}
		return tasks;
	}
}
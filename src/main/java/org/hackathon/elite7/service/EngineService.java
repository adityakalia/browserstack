package org.hackathon.elite7.service;

import java.util.ArrayList;
import java.util.Arrays;
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
	private Queue<Task> taskQueue = new LinkedList<Task>();
	private List<TaskResponse> taskResponseList = new ArrayList<TaskResponse>();
	private int currentTaskSize; 

	public void pushJob(Job job) {
		UUID jobId = UUID.randomUUID();
		System.out.println("JOB ID " + jobId);
		job.setId(jobId.toString());
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
		Task t =  null;
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
		
		Map<String, String> map = new HashMap<String, String>();
		
	}

	List<Task> getMapperTasks(Job job, int batchSize) {
		System.out.println("JOB DATA" + job.getData());
		String lines[] = job.getData().split("\\r?\\n");
		int size = lines.length/batchSize;
		System.out.println("BATCH SIZE " + batchSize);
		System.out.println("SIZE " + size);
		//  logic has a bug where it ignores the residuals 
		// less than batch size
		List <Task> tasks = new ArrayList<Task>();
		for (int i = 0; i < batchSize; i++) {
			Task task = new Task(job.getId(), i, job.getMapperScript(), 
					Arrays.copyOfRange(lines, i * size, (i * size) + size));
			tasks.add(task);
		}
		return tasks;
	}
}
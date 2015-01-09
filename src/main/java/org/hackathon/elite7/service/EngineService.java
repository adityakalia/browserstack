package org.hackathon.elite7.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.hackathon.elite7.model.*;

@Service
public class EngineService {

	@Autowired
	private TrackerService tracker;

	public void startJob(Job job) {
		UUID jobId = UUID.randomUUID();
		System.out.println("JOB ID " + jobId);
		job.setId(jobId.toString());
		List<String> nodes = tracker.getAvailableNodes();
		if (nodes.size() > 0) { 
			List<Task> tasks = this.getMapperTasks(job, nodes.size());
			this.processTasks(tasks);
		} else {
			System.out.println("No nodes Available");
		}
	}
	
	public void processTasks(List<Task> tasks) {
		
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
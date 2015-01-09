package org.hackathon.elite7.service;

import java.util.HashMap;
import java.util.Map;

import org.hackathon.elite7.model.NodeStatus;
import org.springframework.stereotype.Service;

@Service
public class TrackerService {
	
	private Map<String, NodeStatus> nodeMap = new HashMap<String, NodeStatus>();
	
	public void register(String id) {
		nodeMap.put(id, NodeStatus.AVAILABLE);
	}
	
	
}
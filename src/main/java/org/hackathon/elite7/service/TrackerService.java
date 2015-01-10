package org.hackathon.elite7.service;

import org.hackathon.elite7.model.NodeStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TrackerService {

	private Map<String, NodeStatus> nodeMap = new HashMap<String, NodeStatus>();

	public void register(String id) {
		if(!nodeMap.containsKey(id)) {
			System.out.println("NODE REGISTTERED " + id);
		}
		nodeMap.put(id, NodeStatus.AVAILABLE);
	}

	public List<String> getAvailableNodes() {
		List<String> availableNodes = new ArrayList<String>();

		for (Map.Entry<String, NodeStatus> node : nodeMap.entrySet()) {
			if (node.getValue().equals(NodeStatus.AVAILABLE))
				System.out.println("AVAILABLE NODES " + node.getKey());
				availableNodes.add(node.getKey());
		}
		return availableNodes;
	}
	
	public void setBusy(String nodeId) {
		if (nodeMap.containsKey(nodeId))
			nodeMap.put(nodeId, NodeStatus.UNAVAILABLE);
	}

	public void setAvailable(String nodeId) {
		if (nodeMap.containsKey(nodeId))
			nodeMap.put(nodeId, NodeStatus.AVAILABLE);
	}
}
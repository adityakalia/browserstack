
package org.hackathon.elite7.model;

import java.util.Map;

public class TaskResponse {
	
	String jobId;
	int sequence;
	Map<String, String> response;
	
	public TaskResponse() {
		
	}
	
	public TaskResponse(String jobId, int sequence, Map<String, String> response) {
		super();
		this.jobId = jobId;
		this.sequence = sequence;
		this.response = response;
		
		System.out.println("=======================================");
		System.out.println("JOB ID " + jobId);
		System.out.println("SEQUENCE " + sequence);
		System.out.println("RESPONSE ");
		for (String d: response.keySet()) {
			System.out.println(d);
		}
		System.out.println("=======================================");
	}
	public Map<String, String> getResponse() {
		return response;
	}
	public void setResponse(Map<String, String> response) {
		this.response = response;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jobId == null) ? 0 : jobId.hashCode());
		result = prime * result + sequence;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskResponse other = (TaskResponse) obj;
		if (jobId == null) {
			if (other.jobId != null)
				return false;
		} else if (!jobId.equals(other.jobId))
			return false;
		if (sequence != other.sequence)
			return false;
		return true;
	}
	
	
	
	
}

package org.hackathon.elite7.model;

public class Task {
	
	String jobId;
	int sequence;
	String script;
	String[] data;
	public Task(String jobId, int sequence, String script, String data[]) {
		super();
		this.jobId = jobId;
		this.sequence = sequence;
		this.script = script;
		this.data = data;
		
		System.out.println("=======================================");
		System.out.println("JOB ID " + jobId);
		System.out.println("SEQUENCE " + sequence);
		System.out.println("SCRIPT " + script);
		System.out.println("DATA ");
		for (String d: data) {
			System.out.println(d);
		}
		System.out.println("=======================================");
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
	public String getScript() {
		return script;
	}
	public void setScript(String script) {
		this.script = script;
	}
	public String[] getData() {
		return data;
	}
	public void setData(String[] data) {
		this.data = data;
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
		Task other = (Task) obj;
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
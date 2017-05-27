package ProcessCalculator;

public class Process  implements Comparable<Process> {
	
	//Class/ Member variables
	private int processId;
	private int burstTime;
	private int wait;
	
	//constructor
	public Process(int processId, int burstTime,int wait) {
		super();
		this.processId = processId;
		this.burstTime = burstTime;
		this.wait=0;
	}
//setters and getters
	public int getWait() {
		return wait;
	}

	public void setWait(int wait) {
		this.wait = wait;
	}

	public int getProcessId() {
		return processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public int getBurstTime() {
		return burstTime;
	}

	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}

	@Override
	//compare method  from comparable.
	//allows the collections.sort method in Runner class.
	public int compareTo(Process p2) {
		int difference = ((Process)p2).getBurstTime();
		
		return this.getBurstTime() - difference;
	}
}
	

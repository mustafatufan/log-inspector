package ee.tufan.log.model;

public class LogAverage implements Comparable<LogAverage> {

	private long total = 0l;
	private int count = 0;

	private float average = 0l;

	public void addValue(Long duration) {
		count++;
		total += duration;

		average = (float) total / (float) count;
	}

	public float getAverage() {
		return average;
	}

	@Override
	public int compareTo(LogAverage o) {
		int result = 0;

		if (this.average > o.average) return 1;
		else if (this.average < o.average) return -1;
		
		return result;
	}
}

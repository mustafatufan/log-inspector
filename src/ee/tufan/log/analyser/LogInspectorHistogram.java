package ee.tufan.log.analyser;

import ee.tufan.log.model.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LogInspectorHistogram {

	private final static String HEADER = "\n\n\u23F0 Histogram Based on Hours\n\nHour | 0% ███████████████████████████████████ 100%\n_____|____________________________________________";

	private final static String DOT = "█";
	private final static int MAX_LENGTH = 42;

	private final static String FORMAT_HOUR = "  %02d | ";

	private String graph;

	public LogInspectorHistogram(List<Log> logList) {
		setGraph(logList);
	}

	public String getGraph() {
		return graph;
	}

	private void setGraph(List<Log> logList) {
		Long totalTime = 0l;
		Map<Integer, Long> histogram = new HashMap<>();

		for (Log log : logList) {
			Long duration = log.getDuration();
			totalTime += duration;
			Long hourDuration = histogram.get(log.getHour());
			hourDuration = hourDuration == null ? duration : hourDuration + duration;
			histogram.put(log.getHour(), hourDuration);
		}

		graph = HEADER.concat(createGraph(histogram, totalTime));
	}

	private String createGraph(Map<Integer, Long> histogram, Long totalTime) {
		String result = "";
		for (int i = 0; i < 24; i++) {
			Long duration = histogram.get(i) == null ? 0l : histogram.get(i);
			float rate = (float) duration / (float) totalTime;
			int n = (int) (rate * MAX_LENGTH) + 1;
			result = result.concat("\n")
					.concat(String.format(FORMAT_HOUR, Integer.parseInt(String.valueOf(i))))
					.concat(IntStream.range(0, n)
							.mapToObj(j -> DOT)
							.collect(Collectors.joining("")));
		}
		return result;
	}


}

package ee.tufan.log.analyser;

import ee.tufan.log.model.Log;
import ee.tufan.log.model.LogAverage;
import ee.tufan.log.util.LogUtil;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogInspectorTopResources {

	private final static String HEADER = "\n\u270B Top {0} Resources by Average Duration\n";

	private final static String MESSAGE_RESOURCE = "\n#{0} {1} - ({2,number,#.##} ms)";

	private final static String FORMAT_ORDER = "%{0}d";

	private String graph = "";

	public LogInspectorTopResources(List<Log> logList, int resCount) {
		setGraph(logList, resCount);
	}

	public String getGraph() {
		return graph;
	}

	private void setGraph(List<Log> logList, int resCount) {
		Map<String, LogAverage> averageMap = getAverageMap(logList);
		int count = Integer.min(resCount, averageMap.size());
		String title = MessageFormat.format(HEADER, count);
		graph = title.concat(createGraph(averageMap, resCount));

	}

	private Map<String, LogAverage> getAverageMap(List<Log> logList) {
		Map<String, LogAverage> averageMap = new HashMap<>();

		for (Log log : logList) {
			LogAverage average = averageMap.get(log.getResourceType());
			if (average == null) {
				average = new LogAverage();
			}
			average.addValue(log.getDuration());
			averageMap.put(log.getResourceType(), average);
		}

		return LogUtil.sortAverageMap(averageMap);
	}

	private String createGraph(Map<String, LogAverage> averageMap, int count) {
		String graph = "";

		int digit = String.valueOf(count).length();
		String format = MessageFormat.format(FORMAT_ORDER, digit);

		int i = 1;
		for (Map.Entry<String, LogAverage> entry : averageMap.entrySet()) {
			String line = MessageFormat.format(MESSAGE_RESOURCE,
					String.format(format, i),
					entry.getKey(),
					entry.getValue().getAverage());

			graph = graph.concat(line);
			if (i == count) break;
			i++;
		}

		return graph;
	}

}

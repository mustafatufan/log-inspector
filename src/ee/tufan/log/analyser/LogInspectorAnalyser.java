package ee.tufan.log.analyser;

import ee.tufan.log.model.Log;

import java.util.List;

public class LogInspectorAnalyser {

	public String analyse(List<Log> logList, int resCount) {
		LogInspectorTopResources topResources = new LogInspectorTopResources(logList, resCount);
		String resourcesGraph = topResources.getGraph();

		LogInspectorHistogram histogram = new LogInspectorHistogram(logList);
		String histogramGraph = histogram.getGraph();

		return resourcesGraph.concat(histogramGraph);
	}

}

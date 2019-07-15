package ee.tufan.log;

import ee.tufan.log.analyser.LogInspectorAnalyser;
import ee.tufan.log.parser.LogInspectorParser;
import ee.tufan.log.model.Log;

import java.io.File;
import java.util.List;

public class LogInspector {

	private String result;

	public LogInspector(File logFile, int resCount) throws LogInspectorException {
		LogInspectorParser parser = new LogInspectorParser();
		List<Log> logList = parser.parse(logFile);

		LogInspectorAnalyser analyser = new LogInspectorAnalyser();
		result = analyser.analyse(logList, resCount);
	}

	public String getResult() {
		return result;
	}

}

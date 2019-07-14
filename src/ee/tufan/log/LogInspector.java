package ee.tufan.log;

import java.io.File;
import java.util.List;

public class LogInspector {

	private String result;

	public LogInspector(File logFile, int resCount) throws LogInspectorException {
		LogInspectorParser parser = new LogInspectorParser();
		List<LogLine> logs = parser.parse(logFile);

		LogInspectorAnalyser analyser = new LogInspectorAnalyser();
		result = analyser.analyse(logs, resCount);
	}

	public String getResult() {
		return result;
	}

}

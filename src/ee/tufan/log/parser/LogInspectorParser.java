package ee.tufan.log.parser;

import ee.tufan.log.LogInspectorException;
import ee.tufan.log.model.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogInspectorParser {

	// 1234-12-12 12:12:12,123 () [] 0 in 0
	private final static int MIN_LENGTH = 36;

	private final static int INDEX_TIMESTAMP = 23;
	private final static String INDEX_THREAD_ID = ") ";
	private final static String INDEX_USER_CONTEXT = "] ";
	private final static String INDEX_DURATION = " in ";

	private final static String FORMAT_DATE = "yyyy-MM-dd HH:mm:ss,SSS";

	private final static String REGEX_SPACE = "\\s+";

	public List<Log> parse(File logFile) throws LogInspectorException {
		List<String> rawLines = getRawLines(logFile);
		List<Log> logLines = getLogLines(rawLines);
		return logLines;
	}

	private List<String> getRawLines(File logFile) throws LogInspectorException {
		List<String> rawLines = new ArrayList<>();

		try (BufferedReader reader = Files.newBufferedReader(logFile.toPath())) {
			String line;
			while ((line = reader.readLine()) != null) {
				rawLines.add(line);
			}
		} catch (IOException ex) {
			throw new LogInspectorException(ex.getMessage());
		}

		return rawLines;
	}

	private List<Log> getLogLines(List<String> rawLines) throws LogInspectorException {
		List<Log> logLines = new ArrayList<>();

		for (String line : rawLines) {
			if (line == null || line.length() < MIN_LENGTH) {
				continue;
			}
			logLines.add(createLogLineFromString(line));
		}

		return logLines;
	}

	/**
	 * TODO: Refactor
	 *
	 * @param raw string of log line
	 * @return <code>Log</code> object
	 * @throws LogInspectorException
	 */
	private Log createLogLineFromString(String raw) throws LogInspectorException {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
		Date timestamp;
		try {
			String dateString = raw.substring(0, INDEX_TIMESTAMP);
			timestamp = sdf.parse(dateString);
			raw = raw.substring(INDEX_TIMESTAMP + 1).trim();
		} catch (ParseException ex) {
			throw new LogInspectorException(ex.getMessage());
		}

		int threadIdIndex = raw.indexOf(INDEX_THREAD_ID);
		String threadId = raw.substring(1, threadIdIndex);
		raw = raw.substring(threadIdIndex + INDEX_THREAD_ID.length() - 1).trim();

		int userContextIndex = raw.indexOf(INDEX_USER_CONTEXT);
		String userContext = raw.substring(1, userContextIndex);
		raw = raw.substring(userContextIndex + INDEX_USER_CONTEXT.length() - 1).trim();

		int durationIndex = raw.lastIndexOf(INDEX_DURATION);
		Long duration = Long.valueOf(raw.substring(durationIndex + INDEX_DURATION.length()));
		raw = raw.substring(0, durationIndex);

		String[] arr = raw.split(REGEX_SPACE);
		String resourceName = arr[0];
		Long dataPayload = null;
		if (arr.length > 1) {
			try {
				dataPayload = Long.valueOf(arr[1]);
			} catch (NumberFormatException ex) {
				dataPayload = -1l;
			}
		}

		return new Log(timestamp, threadId, userContext, resourceName, dataPayload, duration);
	}

}

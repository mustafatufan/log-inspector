package ee.tufan.log;

import java.io.*;
import java.text.MessageFormat;

public class Application {

	private final long START_TIME = System.currentTimeMillis();

	private final static String MESSAGE_ERROR_PARAM = "\nParameters are invalid!";
	private final static String MESSAGE_ERROR_FILE_PATH = "\n[FILE_PATH] parameter is wrong or file is corrupt!";
	private final static String MESSAGE_ERROR_RES_COUNT = "\n[RES_COUNT] parameter is invalid!";

	private final static String MESSAGE_HELP = "\n\u2315 Log Inspector Help\n\nParameters:\n[FILE_PATH]: Log file's path with extension without quotes. (Exp: examples/timing.log)\n[RES_COUNT]: Number of resources to list, can't be negative. (Exp: 42)\n\nUsage:\njava -jar assignment.jar [FILE_PATH] [RES_COUNT]";
	private final static String MESSAGE_RUNTIME = "\n\u23ED Total Runtime: {0} ms";

	private final static String PARAM_HELP = "-h";

	private final static int INDEX_FILE_PATH = 0;
	private final static int INDEX_RES_COUNT = 1;

	private final static String HEADER = "\n\u2315 Log Inspector Result\n";

	public static void main(String[] args) {
		new Application(args);
	}

	public Application(String[] args) {
		checkHelp(args.length == 0 ? null : args[0]);

		try {
			checkArgs(args);

			File logFile = getLogFile(args[INDEX_FILE_PATH]);
			int resourceCount = getResourceCount(args[INDEX_RES_COUNT]);

			LogInspector inspector = new LogInspector(logFile, resourceCount);
			System.out.println(HEADER.concat(inspector.getResult()));
		} catch (LogInspectorException ex) {
			ex.printStackTrace();
			printHelp();
		}

		terminate();
	}

	private void checkHelp(String param) {
		if (PARAM_HELP.equalsIgnoreCase(param)) {
			printHelp();
		}
	}

	private void checkArgs(String[] args) throws LogInspectorException {
		if (args.length != 2) {
			throw new LogInspectorException(MESSAGE_ERROR_PARAM);
		}
	}

	private File getLogFile(String filePath) throws LogInspectorException {
		File file = new File(filePath);
		if (!file.canRead()) {
			throw new LogInspectorException(MESSAGE_ERROR_FILE_PATH);
		}
		return file;
	}

	private int getResourceCount(String param) throws LogInspectorException {
		int count;
		LogInspectorException exception = new LogInspectorException(MESSAGE_ERROR_RES_COUNT);
		try {
			count = Integer.valueOf(param).intValue();
			if (count < 0) {
				throw exception;
			}
		} catch (NumberFormatException ex) {
			throw exception;
		}
		return count;
	}

	private void printHelp() {
		System.out.println(MESSAGE_HELP);
		terminate();
	}

	private void terminate() {
		String runtime = MessageFormat.format(MESSAGE_RUNTIME, getTotalRuntime());
		System.out.println(runtime);
		System.exit(0);
	}

	private long getTotalRuntime() {
		return System.currentTimeMillis() - START_TIME;
	}

}

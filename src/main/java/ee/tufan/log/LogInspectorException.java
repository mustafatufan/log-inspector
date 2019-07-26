package ee.tufan.log;

public class LogInspectorException extends Exception {

	public LogInspectorException(String message) {
		super(message);
	}

	public LogInspectorException(String message, Throwable ex) {
		super(message, ex);
	}

}

package ee.tufan.log;

import java.util.Date;

public class LogLine {

	private Date timestamp;
	private String threadId;
	private String userContext;
	private String resourceName;
	private Long dataPayload;
	private Long duration;

	public LogLine(Date timestamp, String threadId, String userContext, String resourceName, Long dataPayload, Long duration) {
		this.timestamp = timestamp;
		this.threadId = threadId;
		this.userContext = userContext;
		this.resourceName = resourceName;
		this.dataPayload = dataPayload;
		this.duration = duration;
	}

}

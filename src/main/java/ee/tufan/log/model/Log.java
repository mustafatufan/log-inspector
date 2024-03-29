package ee.tufan.log.model;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Log {

	private final static String REGEX_ACTION_TARGET = "(action|target)([=])\\w+";
	private final static String REGEX_QUESTION_MARK = "[?]";
	private final static Pattern pattern = Pattern.compile(REGEX_ACTION_TARGET);

	// raw
	private LocalDateTime timestamp;
	private String threadId;
	private String userContext;
	private String resource;
	private Long dataPayload;
	private Long duration;

	// calculated
	private int hour;
	private String resourceType;

	public Log(LocalDateTime timestamp, String threadId, String userContext, String resource, Long dataPayload, Long duration) {
		this.timestamp = timestamp;
		this.threadId = threadId;
		this.userContext = userContext;
		this.resource = resource;
		this.dataPayload = dataPayload;
		this.duration = duration;

		setHour(timestamp);
		setResourceType(resource);
	}

	private void setHour(LocalDateTime rawDate) {
		hour = rawDate.getHour();
	}

	private void setResourceType(String rawResource) {
		resourceType = findResourceType(rawResource);
	}

	/**
	 * Searches for <code>ACTION</code> and <code>TARGET</code> parameters in <code>rawResource</code>.
	 * If found, returns parameter as <code>resourceType</code>.
	 * Else returns <code>rawResource</code>'s root as <code>resourceType</code>.
	 *
	 * @param rawResource resource from log file
	 * @return resourceType
	 */
	private String findResourceType(String rawResource) {
		Matcher matcher = pattern.matcher(rawResource);
		while (matcher.find()) {
			return matcher.group();
		}

		return rawResource.split(REGEX_QUESTION_MARK)[0];
	}

	public Long getDuration() {
		return duration;
	}

	public int getHour() {
		return hour;
	}

	public String getResourceType() {
		return resourceType;
	}

}

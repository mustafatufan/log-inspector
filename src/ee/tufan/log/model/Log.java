package ee.tufan.log.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Log {

	private final static String REGEX_ACTION_TARGET = "(action|target)([=])\\w+";
	private final static String REGEX_QUESTION_MARK = "[?]";

	// raw
	private Date timestamp;
	private String threadId;
	private String userContext;
	private String resource;
	private Long dataPayload;
	private Long duration;

	// calculated
	private int hour;
	private String resourceType;

	public Log(Date timestamp, String threadId, String userContext, String resource, Long dataPayload, Long duration) {
		this.timestamp = timestamp;
		this.threadId = threadId;
		this.userContext = userContext;
		this.resource = resource;
		this.dataPayload = dataPayload;
		this.duration = duration;

		setHour(timestamp);
		setResourceType(resource);
	}

	private void setHour(Date rawDate) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(rawDate);
		hour = calendar.get(Calendar.HOUR_OF_DAY);
	}

	private void setResourceType(String rawResource) {
		resourceType = findResourceType(rawResource);
	}

	private String findResourceType(String rawResource) {
		Pattern pattern = Pattern.compile(REGEX_ACTION_TARGET);
		Matcher matcher = pattern.matcher(rawResource);
		while (matcher.find()) {
			return matcher.group();
		}

		// If it is not an action or target then get root as resourceType.
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

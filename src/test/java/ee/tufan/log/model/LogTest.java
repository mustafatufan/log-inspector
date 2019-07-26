package ee.tufan.log.model;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class LogTest {

	private static final String EMPTY = "";
	private static final long ZERO = 0;

	private static final String TEST_ACTION = "/mainContent.do?action=NOTIFICATIONS&notificationType=invoice&contentId=notifications";
	private static final String RESULT_ACTION = "action=NOTIFICATIONS";

	private static final String TEST_TARGET = "/tools.do?target=changeBilling";
	private static final String RESULT_TARGET = "target=changeBilling";

	private static final String TEST_ROOT = "/invoiceInfo.do?invoiceKey=1588074530&from=main.do";
	private static final String RESULT_ROOT = "/invoiceInfo.do";

	@Test
	public void test_get_hour() {
		LocalDateTime timestamp = LocalDateTime.now();
		Log log = new Log(timestamp, EMPTY, EMPTY, EMPTY, ZERO, ZERO);
		assertEquals(timestamp.getHour(), log.getHour());
	}

	@Test
	public void test_get_resource_type_action() {
		LocalDateTime timestamp = LocalDateTime.now();
		Log log = new Log(timestamp, EMPTY, EMPTY, TEST_TARGET, ZERO, ZERO);
		assertEquals(RESULT_TARGET, log.getResourceType());
	}

	@Test
	public void test_get_resource_type_target() {
		LocalDateTime timestamp = LocalDateTime.now();
		Log log = new Log(timestamp, EMPTY, EMPTY, TEST_ACTION, ZERO, ZERO);
		assertEquals(RESULT_ACTION, log.getResourceType());
	}

	@Test
	public void test_get_resource_type_root() {
		LocalDateTime timestamp = LocalDateTime.now();
		Log log = new Log(timestamp, EMPTY, EMPTY, TEST_ROOT, ZERO, ZERO);
		assertEquals(RESULT_ROOT, log.getResourceType());
	}

}

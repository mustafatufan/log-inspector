package ee.tufan.log.util;

import ee.tufan.log.model.LogAverage;
import org.junit.Before;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class LogUtilTest {

	private static final long ONE = 1;
	private static final long TEN = 10;
	private static final long HUNDRED = 100;

	private static LogAverage A = new LogAverage();
	private static LogAverage B = new LogAverage();
	private static LogAverage C = new LogAverage();

	@Before
	public void fillLogAverages() {
		A.addValue(ONE);
		B.addValue(HUNDRED);
		C.addValue(TEN);
	}

	@Test
	public void test_sort_average_map() {
		String expected = concatByIterating(getExpected());

		Map<String, LogAverage> tested = getTested();
		String result = concatByIterating(LogUtil.sortAverageMap(tested));

		assertEquals(expected, result);
	}

	private Map<String, LogAverage> getTested() {
		Map<String, LogAverage> tested = new HashMap<>();
		tested.put("a", A); // 1
		tested.put("b", B); // 100
		tested.put("c", C); // 10
		return tested;
	}

	private Map<String, LogAverage> getExpected() {
		Map<String, LogAverage> expected = new LinkedHashMap<>();
		expected.put("b", B); // 100
		expected.put("c", C); // 10
		expected.put("a", A); // 1
		return expected;
	}

	private String concatByIterating(Map<String, LogAverage> map) {
		String result = "";
		for (Map.Entry<String, LogAverage> entry : map.entrySet()) {
			result = result.concat(entry.getKey()).concat(String.valueOf(entry.getValue().getAverage()));
		}
		return result;
	}

}

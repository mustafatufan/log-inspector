package ee.tufan.log.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LogAverageTest {

	private static final long NEG_ONE = -1;
	private static final long ZERO = 0;
	private static final long ONE = 1;
	private static final long TEN = 10;
	private static final long HUNDRED = 100;

	@Test
	public void test_add_value() {
		LogAverage average = new LogAverage();
		average.addValue(ONE);
		assertEquals(ONE, average.getAverage(), ZERO);
	}

	@Test
	public void test_get_average() {
		LogAverage average = new LogAverage();
		average.addValue(ONE);
		average.addValue(2l);
		assertEquals(1.5, average.getAverage(), ZERO);
	}

	@Test
	public void test_compare_to() {
		LogAverage test = new LogAverage();
		test.addValue(TEN);

		LogAverage bigger = new LogAverage();
		bigger.addValue(HUNDRED);

		LogAverage smaller = new LogAverage();
		smaller.addValue(ONE);

		LogAverage same = new LogAverage();
		same.addValue(TEN);

		assertEquals(NEG_ONE, test.compareTo(bigger));
		assertEquals(ONE, test.compareTo(smaller));
		assertEquals(ZERO, test.compareTo(same));
	}

}

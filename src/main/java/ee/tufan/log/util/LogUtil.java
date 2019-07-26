package ee.tufan.log.util;

import ee.tufan.log.model.LogAverage;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LogUtil {

	public static Map<String, LogAverage> sortAverageMap(final Map<String, LogAverage> map) {
		return map.entrySet()
				.stream()
				.sorted((Map.Entry.<String, LogAverage>comparingByValue().reversed()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}
}

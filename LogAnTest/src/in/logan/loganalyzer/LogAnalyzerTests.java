package in.logan.loganalyzer;


import org.junit.Assert;
import org.junit.Test;
public class LogAnalyzerTests {
	@Test
	public void IsValidFileName_BadExtension_ReturnsFalse() {
		LogAnalyzer analyzer = new LogAnalyzer();

		boolean result = analyzer
				.isValidLogFileName("filewithbadextension.foo");

		Assert.assertFalse(
				"The .foo extension should return false. But returned "
						+ result, result);
	}

	@Test
	public void IsValidLogFileName_GoodExtensionLowercase_ReturnsTrue() {
		LogAnalyzer analyzer = new LogAnalyzer();

		boolean result = analyzer
				.isValidLogFileName("filewithgoodextension.slf");

		Assert.assertTrue(
				"The .slf extension should return true. But returned " + result,
				result);
	}

	@Test
	public void IsValidLogFileName_GoodExtensionUppercase_ReturnsTrue() {
		LogAnalyzer analyzer = new LogAnalyzer();

		boolean result = analyzer
				.isValidLogFileName("filewithgoodextension.SLF");

		Assert.assertTrue(
				"The .slf extension should return true. But returned " + result,
				result);
	}

	
}

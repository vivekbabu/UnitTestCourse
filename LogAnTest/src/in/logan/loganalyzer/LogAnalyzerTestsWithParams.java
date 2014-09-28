package in.logan.loganalyzer;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class LogAnalyzerTestsWithParams {
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	@Parameters({ "filewithbadextension.foo, false",
			"filewithgoodextension.slf, true",
			"filewithgoodextension.SLF, true" })
	public void isValidLogFileName_VariousExtensions_ChecksThem(
			String fileName, boolean expectedResult) {
		LogAnalyzer analyzer = getLogAnalyzer();

		boolean result = analyzer.isValidLogFileName(fileName);

		Assert.assertEquals("Expected result was  " + expectedResult
				+ "But returned " + result, expectedResult, result);
	}

	private LogAnalyzer getLogAnalyzer() {
		return new LogAnalyzer();
	}
	
	@Test
	public void isValidLogFileName_EmptyFileName() {
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("fileName should not be empty");
		LogAnalyzer analyzer = getLogAnalyzer();
		analyzer.isValidLogFileName("");

	}
	
	@Test
	@Parameters({ "filewithbadextension.foo, false",
			"filewithgoodextension.slf, true",
			"filewithgoodextension.SLF, true" })
	public void isValidLogFileName_VariousExtensions_ChecksIfReturnsCorrectLasLogFileNameValidValue(
			String fileName, boolean expectedRememberedValue) {
		LogAnalyzer analyzer = getLogAnalyzer();

		analyzer.isValidLogFileName(fileName);
		
		boolean rememberedValue = analyzer.isLastLogFileNameValid();

		Assert.assertEquals("Expected result was  " + expectedRememberedValue
				+ "But returned " + rememberedValue, expectedRememberedValue, rememberedValue);
	}
}

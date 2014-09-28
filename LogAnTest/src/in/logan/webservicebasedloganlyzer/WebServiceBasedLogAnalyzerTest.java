package in.logan.webservicebasedloganlyzer;

import in.logan.webservicebasedloganlyzer.emailservice.EmailService;
import in.logan.webservicebasedloganlyzer.emailservice.emailinfo.EmailInfo;
import in.logan.webservicebasedloganlyzer.logwebservice.LogWebService;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class WebServiceBasedLogAnalyzerTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	@Parameters({ "filewithbadextension.foo, false",
			"filewithgoodextension.slf, true",
			"filewithgoodextension.SLF, true" })
	public void isValidLogFileName_VariousExtensions_ChecksThem(
			String fileName, boolean expectedResult) {
		WebServiceBasedLogAnalyzer analyzer = getLogAnalyzer();

		boolean result = analyzer.isValidLogFileName(fileName);

		Assert.assertEquals("Expected result was  " + expectedResult
				+ "But returned " + result, expectedResult, result);
	}

	private WebServiceBasedLogAnalyzer getLogAnalyzer() {
		return new WebServiceBasedLogAnalyzer();
	}

	@Test
	public void isValidLogFileName_EmptyFileName() {
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("fileName should not be empty");
		WebServiceBasedLogAnalyzer analyzer = getLogAnalyzer();
		analyzer.isValidLogFileName("");

	}

	@Test
	public void isValidLogFileName_ServicesNotInitialized() {
		expectedEx.expect(IllegalStateException.class);
		expectedEx.expectMessage("All dependencies not initialized");
		WebServiceBasedLogAnalyzer analyzer = getLogAnalyzer();
		analyzer.isValidLogFileName("abc.slf");

	}

	@Test
	public void isValidLogFileName_MailIsSentOnException() {
		WebServiceBasedLogAnalyzer analyzer = getLogAnalyzer();
		FakeEmailService fakeEmailService = new FakeEmailService();
		analyzer.setLogWebservice(new FakeLogService(true));
		analyzer.setEmailService(fakeEmailService);
		
		analyzer.isValidLogFileName("abc.slf");
		
		Assert.assertEquals("The email sent was not proper", new EmailInfo("vivek.babu@gmail.com",
						"logger@log.com", "Error occured"), fakeEmailService.getEmailInfo());
		

	}
	
	@Test
	public void isValidLogFileName_MessageLoggedToTheWebservice() {
		WebServiceBasedLogAnalyzer analyzer = getLogAnalyzer();
		FakeEmailService fakeEmailService = new FakeEmailService();
		FakeLogService fakeLogService = new FakeLogService(false);
		analyzer.setLogWebservice(fakeLogService);
		analyzer.setEmailService(fakeEmailService);
		
		String fileName = "abc.slf";
		analyzer.isValidLogFileName(fileName);
		
		Assert.assertEquals("The logger was not called properly was not proper","Filename too short " + fileName , fakeLogService.getMessage());
		

	}
	class FakeEmailService implements EmailService {

		private EmailInfo emailInfo;

		@Override
		public void sendMail(EmailInfo emailinfo) {
			this.emailInfo = emailinfo;
		}

		public EmailInfo getEmailInfo() {
			return emailInfo;
		}
	}

	class FakeLogService implements LogWebService {
		private boolean throwEx;
		private String message;
		
		public String getMessage() {
			return message;
		}

		public FakeLogService(boolean throwEx) {
			this.throwEx = throwEx;
		}

		@Override
		public void logError(String message) throws Exception {
			if (throwEx)
				throw new Exception();
			else 
				this.message = message;
		}

	}

}

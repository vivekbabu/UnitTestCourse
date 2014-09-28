package in.logan.webservicebasedloganlyzer;

import in.logan.webservicebasedloganlyzer.emailservice.EmailService;
import in.logan.webservicebasedloganlyzer.emailservice.emailinfo.EmailInfo;
import in.logan.webservicebasedloganlyzer.logwebservice.LogWebService;

public class WebServiceBasedLogAnalyzer {

	private LogWebService logWebservice;
	private EmailService emailService;
	
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
	
	public void setLogWebservice(LogWebService logWebservice) {
		this.logWebservice = logWebservice;
	}
	
	public boolean isValidLogFileName(String fileName)
			throws IllegalArgumentException {
		if (fileName.isEmpty())
			throw new IllegalArgumentException("fileName should not be empty");
		else if (!fileName.toUpperCase().endsWith(".SLF")) {
			return false;
		} else if (fileName.length() < 8) {
			if (logWebservice == null || emailService == null)
				throw new IllegalStateException(
						"All dependencies not initialized");
			try {
				logWebservice.logError("Filename too short " + fileName);
			} catch (Exception e) {
				emailService.sendMail(new EmailInfo("vivek.babu@gmail.com",
						"logger@log.com", "Error occured"));
			}
		}
		return true;
	}
}

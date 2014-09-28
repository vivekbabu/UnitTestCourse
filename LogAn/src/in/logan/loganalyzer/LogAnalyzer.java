package in.logan.loganalyzer;

public class LogAnalyzer {
	private boolean lastLogFileNameValid;

	public boolean isLastLogFileNameValid() {
		return lastLogFileNameValid;
	}

	public boolean isValidLogFileName(String fileName)
			throws IllegalArgumentException {
		lastLogFileNameValid = false;
		if (fileName.isEmpty())
			throw new IllegalArgumentException("fileName should not be empty");
		if (!fileName.toUpperCase().endsWith(".SLF")) {
			return false;
		}
		lastLogFileNameValid = true;
		return true;
	}
}
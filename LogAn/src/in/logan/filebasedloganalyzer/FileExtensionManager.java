package in.logan.filebasedloganalyzer;

import in.logan.loganalyzer.extensionmanager.ExtensionManager;

public class FileExtensionManager implements ExtensionManager {
	
	@Override
	public boolean isValidLogFileName(String fileName) {
		if (fileName.isEmpty())
			throw new IllegalArgumentException("fileName should not be empty");
		if (!fileName.toUpperCase().endsWith(".SLF")) {
			return false;
		}
		return true;
	}

}

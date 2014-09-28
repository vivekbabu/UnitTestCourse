package in.logan.filebasedloganalyzer;

import in.logan.filebasedloganalyzer.extensionmanager.ExtensionManager;

public class FileBasedLogAnalyzer {
	private ExtensionManager extensionManager;

	public void setExtensionManager(ExtensionManager extensionManager) {
		this.extensionManager = extensionManager;
	}

	public boolean isValidLogFileName(String fileName)
			throws IllegalStateException {
		if (extensionManager == null)
			throw new IllegalStateException("Extension manager not initialized");
		boolean result = false;
		try {
			result = extensionManager.isValidLogFileName(fileName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return result;
	}
}

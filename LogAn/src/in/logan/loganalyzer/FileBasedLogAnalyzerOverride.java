package in.logan.loganalyzer;

import in.logan.loganalyzer.extensionmanager.ExtensionManager;
import in.logan.loganalyzer.extensionmanagerfactory.ExtensionManagerFactory;

public class FileBasedLogAnalyzerOverride {
	

	public boolean isValidLogFileName(String fileName)
			throws IllegalStateException {
		ExtensionManager extensionManager = getExtensionManager();
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

	private ExtensionManager getExtensionManager() {
		return ExtensionManagerFactory.getFileExtensionManager();
	}
}

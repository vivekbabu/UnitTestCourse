package in.logan.filebasedloganalyzer;

import in.logan.filebasedloganalyzer.extensionmanager.ExtensionManager;
import in.logan.filebasedloganalyzer.extensionmanagerfactory.ExtensionManagerFactory;

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

	protected ExtensionManager getExtensionManager() {
		return ExtensionManagerFactory.getFileExtensionManager();
	}
}

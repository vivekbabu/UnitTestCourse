package in.logan.loganalyzer.extensionmanagerfactory;

import in.logan.loganalyzer.FileExtensionManager;
import in.logan.loganalyzer.extensionmanager.ExtensionManager;

public class ExtensionManagerFactory {

	public static ExtensionManager getFileExtensionManager() {
		return new FileExtensionManager();
	}

}

package in.logan.filebasedloganalyzer.extensionmanagerfactory;

import in.logan.filebasedloganalyzer.FileExtensionManager;
import in.logan.filebasedloganalyzer.extensionmanager.ExtensionManager;

public class ExtensionManagerFactory {

	public static ExtensionManager getFileExtensionManager() {
		return new FileExtensionManager();
	}

}

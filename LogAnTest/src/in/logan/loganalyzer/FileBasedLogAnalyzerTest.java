package in.logan.loganalyzer;

import junit.framework.Assert;
import in.logan.loganalyzer.extensionmanager.ExtensionManager;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FileBasedLogAnalyzerTest {

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void isValidFileNameWithEmptyExtensionManagerThrowsException() {
		expectedEx.expect(IllegalStateException.class);
		expectedEx.expectMessage("Extension manager not initialized");
		FileBasedLogAnalyzer logAnalyzer = getLogAnalyzer();
		logAnalyzer.isValidLogFileName("abc.foo");
	}

	@Test
	public void isValidFileNameWithExtensionManagerWorksAsExpected() {

		FileBasedLogAnalyzer logAnalyzer = getLogAnalyzer();
		FakeExtensionManager extensionManager = new FakeExtensionManager();
		logAnalyzer.setExtensionManager(extensionManager);

		boolean validLogFileName = logAnalyzer.isValidLogFileName("abc.foo");

		Assert.assertTrue("Expected true but returned false", validLogFileName);
	}

	@Test
	public void isValidFileNameWithExtensionManagerReturnsFalseIfExtensionManagerThrowsExeption() {

		FileBasedLogAnalyzer logAnalyzer = getLogAnalyzer();
		FakeExtensionManager extensionManager = new FakeExtensionManager();
		
		extensionManager.setE(new Exception("Something went wrong"));
		logAnalyzer.setExtensionManager(extensionManager);

		boolean validLogFileName = logAnalyzer.isValidLogFileName("abc.foo");

		Assert.assertFalse("Expected false but returned true", validLogFileName);
	}

	public FileBasedLogAnalyzer getLogAnalyzer() {
		return new FileBasedLogAnalyzer();
	}

	class FakeExtensionManager implements ExtensionManager {
		Exception e = null;

		public void setE(Exception e) {
			this.e = e;
		}

		@Override
		public boolean isValidLogFileName(String fileName) throws Exception {
			if (e != null)
				throw e;
			return true;
		}
	}
}

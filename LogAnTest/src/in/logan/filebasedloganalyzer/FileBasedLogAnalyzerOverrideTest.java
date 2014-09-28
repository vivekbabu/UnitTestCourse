package in.logan.filebasedloganalyzer;

import in.logan.filebasedloganalyzer.FileBasedLogAnalyzerOverride;
import in.logan.filebasedloganalyzer.extensionmanager.ExtensionManager;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FileBasedLogAnalyzerOverrideTest {
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void isValidFileNameWithEmptyExtensionManagerThrowsException() {
		expectedEx.expect(IllegalStateException.class);
		expectedEx.expectMessage("Extension manager not initialized");
		FileBasedLogAnalyzerOverride logAnalyzer = new FileBasedLogAnalyzerOverride() {
			@Override
			protected ExtensionManager getExtensionManager() {
				return null;
			}
		};
		logAnalyzer.isValidLogFileName("abc.foo");
	}

	@Test
	public void isValidFileNameWithExtensionManagerWorksAsExpected() {

		FileBasedLogAnalyzerOverride logAnalyzer = new FileBasedLogAnalyzerOverride() {
			@Override
			protected ExtensionManager getExtensionManager() {
				return getFakeExtensionManager();
			}
		};

		boolean validLogFileName = logAnalyzer.isValidLogFileName("abc.foo");

		Assert.assertTrue("Expected true but returned false", validLogFileName);
	}

	@Test
	public void isValidFileNameWithExtensionManagerReturnsFalseIfExtensionManagerThrowsExeption() {

		FileBasedLogAnalyzerOverride logAnalyzer = new FileBasedLogAnalyzerOverride() {
			@Override
			protected ExtensionManager getExtensionManager() {
				FakeExtensionManager fakeExtensionManager = getFakeExtensionManager();
				fakeExtensionManager.setE(new Exception("Something went wrong"));
				return fakeExtensionManager;
			}
		};

		boolean validLogFileName = logAnalyzer.isValidLogFileName("abc.foo");

		Assert.assertFalse("Expected false but returned true", validLogFileName);
	}


	
	private FakeExtensionManager getFakeExtensionManager() {
		return new FakeExtensionManager();
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

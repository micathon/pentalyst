package test;

import org.junit.Test;
import config.Config;

import static org.junit.Assert.*;

public class ConfigTest {
	
	String pnyHomeFileName;
	String outFileName = "/out.txt";
	String outFullFileName;
	String sepWord = ".";

	private void initConfigTest() {
		pnyHomeFileName = System.getenv("PNY_HOME");
		outFullFileName = pnyHomeFileName + outFileName; 
	}
	
	@Test
	public void testUseDefaults() {
		Config cfg = new Config();
		goDefaults(cfg);
	}
		
	private void goDefaults(Config cfg) {
		cfg.useDefaults();
		assertFalse(cfg.isDebug());
		assertFalse(cfg.isFileOut());
		assertFalse(cfg.isUnitTest());
		assertFalse(cfg.isRunTest());
		assertFalse(cfg.isCmdPrompt());
	}
	
	private Config useSepWord(String sepWord) {
		Config cfg;
		initConfigTest();
		cfg = new Config(pnyHomeFileName, sepWord, outFullFileName);
		return cfg;
	}

	@Test
	public void testConfigNoDot() {
		Config cfg;
		String sepWord = "A";
		int errCode;

		cfg = useSepWord(sepWord);
		errCode = cfg.badSepErrCode();
		assertEquals(errCode, 1);
	}

	@Test
	public void testConfigSepUR() {
		Config cfg;
		String sepWord = ".ur";
		int errCode;

		cfg = useSepWord(sepWord);
		errCode = cfg.badSepErrCode();
		assertEquals(errCode, 2);
	}

	@Test
	public void testConfigSepOversize() {
		Config cfg;
		String sepWord = ".dfuz";
		int errCode;

		cfg = useSepWord(sepWord);
		errCode = cfg.badSepErrCode();
		assertEquals(errCode, 3);
	}

	@Test
	public void testConfigSepDups() {
		Config cfg;
		String sepWord = ".dd";
		int errCode;

		cfg = useSepWord(sepWord);
		errCode = cfg.badSepErrCode();
		assertEquals(errCode, 4);
	}

	@Test
	public void testConfigSepBadCh() {
		Config cfg;
		String sepWord = ".dz";
		int errCode;

		cfg = useSepWord(sepWord);
		errCode = cfg.badSepErrCode();
		assertEquals(errCode, 4);
	}

	@Test
	public void testConfigDebug() {
		Config cfg;
		String sepWord = ".d";

		cfg = useSepWord(sepWord);
		assertTrue(cfg.isDebug());
		assertFalse(cfg.isFileOut());
		assertFalse(cfg.isUnitTest());
		assertFalse(cfg.isRunTest());
		assertFalse(cfg.isCmdPrompt());
	}

	@Test
	public void testConfigFileOut() {
		Config cfg;
		String sepWord = ".f";

		cfg = useSepWord(sepWord);
		assertFalse(cfg.isDebug());
		assertTrue(cfg.isFileOut());
		assertFalse(cfg.isUnitTest());
		assertFalse(cfg.isRunTest());
		assertFalse(cfg.isCmdPrompt());
	}

	@Test
	public void testConfigRunTest() {
		Config cfg;
		String sepWord = ".r";

		cfg = useSepWord(sepWord);
		assertTrue(cfg.isRunTest());
		assertFalse(cfg.isDebug());
		assertFalse(cfg.isFileOut());
		assertFalse(cfg.isUnitTest());
		assertFalse(cfg.isCmdPrompt());
	}

	@Test
	public void testConfigUnitTest() {
		Config cfg;
		String sepWord = ".u";

		cfg = useSepWord(sepWord);
		assertTrue(cfg.isUnitTest());
		assertFalse(cfg.isDebug());
		assertFalse(cfg.isFileOut());
		assertFalse(cfg.isRunTest());
		assertFalse(cfg.isCmdPrompt());
	}

	@Test
	public void testConfigDefaults() {
		Config cfg;
		String sepWord = ".";

		cfg = useSepWord(sepWord);
		goDefaults(cfg);
		//assertFalse(!cfg.isDebug());
	}

	@Test
	public void testConfigGetSepWord() {
		Config cfg;
		String sepWord = ".df";

		cfg = useSepWord(sepWord);
		goTestSepWord(cfg);
		sepWord = "";
		cfg = useSepWord(sepWord);
		goTestSepWord(cfg);
		//assertTrue(!cfg.isDebug());
	}

	private void goTestSepWord(Config cfg) {
		cfg.useDefaults();
		assertTrue(cfg.isDebug());
		assertTrue(cfg.isFileOut());
		assertFalse(cfg.isUnitTest());
		assertFalse(cfg.isRunTest());
		assertFalse(cfg.isCmdPrompt());
	}
	
}

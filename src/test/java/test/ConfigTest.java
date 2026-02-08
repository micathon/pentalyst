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

}

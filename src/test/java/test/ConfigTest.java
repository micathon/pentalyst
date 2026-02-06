package test;

import org.junit.Test;
import config.Config;

import static org.junit.Assert.*;

public class ConfigTest {

	@Test
	public void testUseDefaults() {
		Config cfg = new Config();
		cfg.useDefaults();
		assertEquals(cfg.isDebug(), false);
		assertEquals(cfg.isFileOut(), false);
		assertEquals(cfg.isUnitTest(), false);
		assertEquals(cfg.isRunTest(), false);
		assertEquals(cfg.isCmdPrompt(), false);
		//assertFalse(cfg.isFileOut());
		//assertEquals(ex.factorial(4), 24);
	}
}

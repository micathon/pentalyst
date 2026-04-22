package test;

import config.Config;
import init.InitMain;

import static org.junit.Assert.*;

import org.junit.Test;

public class ScanSrcTest {

	private String modstr = "c10/";
	
	private Config runTest(String fileName, int errno) {
		String filePath;
		String fileMidPath;
		InitMain initobj;
		Config cfg;
		
		cfg = new Config();
		filePath = System.getenv("PNY_HOME");
		fileMidPath = "/dat/" + modstr;
		filePath = filePath + fileMidPath;
		initobj = new InitMain(cfg);
		initobj.runInitFile(filePath, fileName);
		return cfg;
	}
	
	public void handleStmt(String fileName, int errno) {
		int srcerrno;
		Config cfg;

		fileName += errno;
		fileName += ".pny";
		cfg = runTest(fileName, errno);
		if (cfg.getErrFileNotFound()) {
			assertEquals(fileName, "");
			return;
		}
		srcerrno = cfg.getSrcErrNo();
		assertEquals(srcerrno, errno);
	}
	
	private void doCodeBuf(int errno) {
		handleStmt("doCodeBuf", errno);
	}
	
	@Test
	public void doCodeBuf10() {
		doCodeBuf(10);
	}
	
}

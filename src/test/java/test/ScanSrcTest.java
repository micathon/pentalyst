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
	
	@Test
	public void doCodeBuf20() {
		doCodeBuf(20);
	}
	
	@Test
	public void doCodeBuf30() {
		doCodeBuf(30);
	}
	
	@Test
	public void doCodeBuf40() {
		doCodeBuf(40);
	}
	
	@Test
	public void doCodeBuf50() {
		doCodeBuf(50);
	}
	
	@Test
	public void doCodeBuf60() {
		doCodeBuf(60);
	}
	
	private void doEmptyProg(int errno) {
		handleStmt("doEmptyProg", errno);
	}

	@Test
	public void doEmptyProg70() {
		doEmptyProg(70);
	}
	
	private void doTokIdent(int errno) {
		handleStmt("doTokIdent", errno);
	}

	@Test
	public void doTokIdent80() {
		doTokIdent(80);
	}
	
	@Test
	public void doTokIdent90() {
		doTokIdent(90);
	}
	
	private void doTokLower(int errno) {
		handleStmt("doTokLower", errno);
	}

	@Test
	public void doTokLower100() {
		doTokLower(100);
	}
	
}

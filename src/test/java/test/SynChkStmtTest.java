package test;

import org.junit.Test;
import config.Config;
import init.InitMain;

import static org.junit.Assert.*;

public class SynChkStmtTest {

	String pnyHomeFileName;
	int modno = 2;
	String modstr = "c02/";

	private Config runTest(String fileName, int errno) {
		String filePath;
		String fileMidPath;
		InitMain initobj;
		Config cfg = new Config();
		
		fileName += errno;
		fileName += ".pny";
		filePath = System.getenv("PNY_HOME");
		fileMidPath = "/dat/" + modstr;
		filePath = filePath + fileMidPath;
		initobj = new InitMain(cfg);
		initobj.runInitFile(filePath, fileName);
		return cfg;
	}
	
	public void doIfStmt(int errno) {
		int fullerrno;
		int moderrno;
		Config cfg;
		cfg = runTest("doIfStmt", errno);
		moderrno = cfg.getModErrNo();
		fullerrno = cfg.getFullErrNo(modno, moderrno);
		assertEquals(fullerrno, 203000 + errno);
	}
	
	@Test
	public void doIfStmt10() {
		doIfStmt(10);
	}
	
	@Test
	public void doIfStmt20() {
		doIfStmt(20);
	}
	
}

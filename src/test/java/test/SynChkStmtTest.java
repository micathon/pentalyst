package test;

import org.junit.Test;
import config.Config;
import init.InitMain;

import static org.junit.Assert.*;

public class SynChkStmtTest {

	String pnyHomeFileName;
	int modno = 2;
	String modstr = "c02/";

	private Config runTest(String fileName, int miderrno, int errno) {
		String filePath;
		String fileMidPath;
		InitMain initobj;
		Config cfg;
		double errval;
		
		cfg = new Config();
		errval = errno + (miderrno * 100);
		errval /= 100.0;
		cfg.setModNo(modno);
		cfg.setModErrNo(errval);
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
		int miderrno;
		Config cfg;
		miderrno = 30;
		cfg = runTest("doIfStmt", miderrno, errno);
		moderrno = cfg.getModErrNo();
		fullerrno = cfg.getFullErrNo(modno, moderrno);
		assertEquals(fullerrno, 200000 + (miderrno * 100) + errno);
	}
	
	@Test
	public void doIfStmt10() {
		doIfStmt(10);
	}
	
	@Test
	public void doIfStmt20() {
		doIfStmt(20);
	}
	
	@Test
	public void doIfStmt30() {
		doIfStmt(30);
	}
	
	@Test
	public void doIfStmt40() {
		doIfStmt(40);
	}
	
	@Test
	public void doIfStmt50() {
		doIfStmt(50);
	}
	
	@Test
	public void doIfStmt60() {
		doIfStmt(60);
	}
	
}

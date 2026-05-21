package test;

import config.Config;
import init.InitMain;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeneralCommonTest {

	private int modno;
	private String modstr;
	
	public GeneralCommonTest(int modno) {
		this.modno = modno;
		modstr = "c" + modno + '/';
	}

	private Config runTest(String fileName, int modno, int errno) {
		String filePath;
		String fileMidPath;
		String modstr;
		InitMain initobj;
		Config cfg;
		
		cfg = new Config();
		modstr = "c" + modno + '/';
		filePath = System.getenv("PNY_HOME");
		fileMidPath = "/dat/" + modstr;
		filePath = filePath + fileMidPath;
		initobj = new InitMain(cfg);
		initobj.runInitFile(filePath, fileName);
		return cfg;
	}
	
	public void handleStmt(String fileName, int errno) {
		int srcerrno;
		int fullerrno;
		Config cfg;

		fileName += errno;
		fileName += ".pny";
		cfg = runTest(fileName, modno, errno);
		if (cfg.getErrFileNotFound()) {
			assertEquals(fileName, "");
			return;
		}
		srcerrno = cfg.getSrcErrNo();
		fullerrno = cfg.getMillErrNo(modno, srcerrno);
		assertEquals(fullerrno, (modno * 1000) + errno);
	}
	
}

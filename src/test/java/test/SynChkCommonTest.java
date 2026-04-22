package test;

import config.Config;
import init.InitMain;

import static org.junit.Assert.*;

public class SynChkCommonTest {

	private int modno;
	private String modstr;
	
	public SynChkCommonTest(int modno) {
		this.modno = modno;
		modstr = "c0" + modno + '/';
	}

	private Config runTest(String fileName, int miderrno, int errno,
		int suffixNo) 
	{
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
		filePath = System.getenv("PNY_HOME");
		fileMidPath = "/dat/" + modstr;
		filePath = filePath + fileMidPath;
		initobj = new InitMain(cfg);
		initobj.runInitFile(filePath, fileName);
		return cfg;
	}
	
	public void handleStmt(String fileName, int miderrno, int errno) {
		handleStmtRtn(fileName, miderrno, errno, errno);
	}
	
	public void handleStmtRtn(String fileName, int miderrno, int errno,
		int suffixNo) 
	{
		int fullerrno;
		int moderrno;
		Config cfg;

		fileName += addLeadingZero(suffixNo);
		fileName += ".pny";
		cfg = runTest(fileName, miderrno, errno, suffixNo);
		if (cfg.getErrFileNotFound()) {
			assertEquals(fileName, "");
			return;
		}
		moderrno = cfg.getModErrNo();
		fullerrno = cfg.getFullErrNo(modno, moderrno);
		assertEquals(fullerrno, (modno * 100000) + (miderrno * 100) + errno);
	}
	
	private String addLeadingZero(int suffixNo) {
		String s;
		if (suffixNo == 0) {
			return "00";
		}
		s = "" + suffixNo;
		if (s.length() < 2) {
			s = "0" + s;
		}
		return s;
	}
	
}

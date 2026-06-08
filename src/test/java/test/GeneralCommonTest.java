package test;

import config.Config;
import init.InitMain;
import iconst.RunConst;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeneralCommonTest implements RunConst {

	private int modno;
	private String modstr;
	private Config cfg;
	
	public GeneralCommonTest(int modno) {
		this.modno = modno;
		modstr = "c" + modno + '/';
		cfg = new Config();
	}

	private void runTest(String fileName, int modno, int errno) {
		String filePath;
		String fileMidPath;
		String modstr;
		InitMain initobj;
		boolean success;
		
		modstr = "c" + modno + '/';
		filePath = System.getenv("PNY_HOME");
		fileMidPath = "/dat/" + modstr;
		filePath = filePath + fileMidPath;
		initobj = new InitMain(cfg);
		success = initobj.runInitSimple(filePath, fileName);
		cfg.setRunSuccess(success);
	}
	
	private boolean doRunTest(String fileName, int errno) {
		fileName = appendErrNo(fileName, errno);
		runTest(fileName, modno, errno);
		if (cfg.getErrFileNotFound()) {
			assertEquals(fileName, "");
			return false;
		}
		return true;
	}
	
	public void handleStmt(String fileName, int errno) {
		int srcerrno;
		int fullerrno;

		if(!doRunTest(fileName, errno)) {
			return;
		}
		srcerrno = cfg.getSrcErrNo();
		fullerrno = cfg.getMillErrNo(modno, srcerrno);
		assertEquals(fullerrno, (modno * 1000) + errno);
	}
	
	private String appendErrNo(String fileName, int errno) {
		fileName += errno;
		fileName += ".pny";
		return fileName;
	}
	
	public void doPushExpr(String fileName, int errno) {
		boolean runOK;

		cfg.setModSrcErr(modno, errno);
		if(!doRunTest(fileName, errno)) {
			return;
		}
		/*
		oprn("doPushExpr: success = " + cfg.getRunSuccess());
		oprn("doPushExpr: isUtErr = " + cfg.getUtErr());
		oprn("doPushExpr: kwdcount = " + cfg.getKwdCount());
		*/
		runOK = cfg.getRunSuccess() && !cfg.getUtErr();
		assertTrue(runOK);
		assertEquals(cfg.getKwdCount(), UTPUSHXCOUNT);
	}
	
	public void oprn(String msg) {  
		System.out.println(msg);
	}
	
}

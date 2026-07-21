package test;

import config.Config;
import init.InitMain;
import iconst.RunConst;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeneralCommonTest implements RunConst {

	private int modno;
	private String modstr;
	private double zdval = 999.0;
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
	
	private boolean doRunTest(String fileName, int errno, boolean setvars) {
		if (setvars) {
			cfg.setModSrcErr(modno, errno);
		}
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
		int dig;

		if(!doRunTest(fileName, errno, false)) {
			return;
		}
		dig = errno % 10;
		if (dig < 5) {  // enable up to 5 .pny files sharing an errno
			errno -= dig;
		}
		srcerrno = cfg.getSrcErrNo();
		if (srcerrno == LONGERRNO) {
			return;
		}
		fullerrno = cfg.getMillErrNo(modno, srcerrno);
		assertEquals(fullerrno, (modno * 1000) + errno);
		if (cfg.getBoolErrTrapped()) {
			assertTrue(cfg.getErrSuccess());
		}
	}

	public void handleStmtValues(String fileName, int errno,
		int ival, double dval) 
	{
		int intVal;
		double floatVal;
		boolean isFloatEq;
		
		handleStmt(fileName, errno);
		intVal = cfg.getIntVal();
		assertEquals(intVal, ival);
		if (!isFloatEqual(dval, zdval)) {
			floatVal = cfg.getFloatVal();
			isFloatEq = isFloatEqual(floatVal, dval);
			assertTrue(isFloatEq);
		}
	}
	
	public void handleStmtVal(String fileName, int errno, int ival) {
		handleStmtValues(fileName, errno, ival, zdval);
	}
	
	private boolean isFloatEqual(double x, double y) {
		boolean isEq;
		isEq = Math.abs(x - y) < 0.000001;
		return isEq;
	}
	
	private String appendErrNo(String fileName, int errno) {
		fileName += errno;
		fileName += ".pny";
		return fileName;
	}
	
	public void doPushExpr(String fileName, int errno) {
		boolean runOK;

		if(!doRunTest(fileName, errno, true)) {
			return;
		}
		runOK = cfg.getRunSuccess() && !cfg.getUtErr();
		assertTrue(runOK);
		assertEquals(cfg.getKwdCount(), UTPUSHXCOUNT);
	}

	public void doPushStmt(String fileName, int errno) {
		boolean runOK;

		//cfg.setUtErr(true);
		if(!doRunTest(fileName, errno, true)) {
			return;
		}
		runOK = cfg.getRunSuccess() && !cfg.getUtErr();
		assertTrue(runOK);
	}

	/*
	oprn("doPushExpr: success = " + cfg.getRunSuccess());
	oprn("doPushExpr: isUtErr = " + cfg.getUtErr());
	oprn("doPushExpr: kwdcount = " + cfg.getKwdCount());
	*/
	
	public double getZdval() {
		return zdval;
	}
	
	public void oprn(String msg) {  
		System.out.println(msg);
	}
	
}

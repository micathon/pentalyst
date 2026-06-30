package test;

import org.junit.Test;

public class RtOperTest {

	public void handleStmt(String fileName, int errno) {
		GeneralCommonTest common = new GeneralCommonTest(13); 
		common.handleStmt(fileName, errno);
	}

	public void handleStmtValues(String fileName, int errno,
		int ival, double dval) 
	{
		GeneralCommonTest common = new GeneralCommonTest(13); 
		common.handleStmtValues(fileName, errno, ival, dval);
	}

	private void doZeroDiv(int errno) {
		handleStmt("doZeroDiv", errno);
	}
	
	@Test
	public void doZeroDiv100() {
		doZeroDiv(100);
	}

	private void doNoVarInz(int errno) {
		handleStmt("doNoVarInz", errno);
	}
	
	@Test
	public void doNoVarInz105() {
		doNoVarInz(105);
	}
	
	private void doSetStmt(int errno, int ival, double dval) {
		handleStmtValues("doSetStmt", errno, ival, dval);
	}
	
	@Test
	public void doSetStmt110() {
		doSetStmt(110, 2026, 3.1416);
	}
	
	private void doAddOper(int errno, int ival, double dval) {
		handleStmtValues("doAddOper", errno, ival, dval);
	}
	
	@Test
	public void doAddOper120() {
		doAddOper(120, 4, 3.5);
	}
	
}

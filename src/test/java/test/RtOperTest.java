package test;

import org.junit.Test;

public class RtOperTest {
	
	private double zdval;

	public void handleStmt(String fileName, int errno) {
		GeneralCommonTest common = new GeneralCommonTest(13); 
		common.handleStmt(fileName, errno);
	}

	public void handleStmtValues(String fileName, int errno,
		int ival, double dval) 
	{
		GeneralCommonTest common = new GeneralCommonTest(13); 
		zdval = common.getZdval();
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
	
	private void doMpyOper(int errno, int ival, double dval) {
		handleStmtValues("doMpyOper", errno, ival, dval);
	}
	
	@Test
	public void doMpyOper130() {
		doMpyOper(130, 6, 3.6);
	}
	
	private void doDivOper(int errno, int ival, double dval) {
		handleStmtValues("doDivOper", errno, ival, dval);
	}
	
	@Test
	public void doDivOper140() {
		doDivOper(140, 10, 2.5);
	}
	
	private void doMinusOper(int errno, int ival, double dval) {
		handleStmtValues("doMinusOper", errno, ival, dval);
	}
	
	@Test
	public void doMinusOper150() {
		doMinusOper(150, 2, 2.4);
	}
	
	private void doModOper(int errno, int ival) {
		handleStmtValues("doModOper", errno, ival, zdval);
	}
	
	@Test
	public void doModOper145() {
		doModOper(145, 2);
	}
	
	private void doShiftOper(int errno, int ival) {
		handleStmtValues("doShiftOper", errno, ival, zdval);
	}
	
	@Test
	public void doShiftOper160() {
		doShiftOper(160, 14);
	}
	
	private void doAndBitzOper(int errno, int ival) {
		handleStmtValues("doAndBitzOper", errno, ival, zdval);
	}
	
	@Test
	public void doAndBitzOper170() {
		doAndBitzOper(170, 126);
	}
	
}

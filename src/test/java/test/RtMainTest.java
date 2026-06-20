package test;

import org.junit.Test;

public class RtMainTest {

	public void handleStmt(String fileName, int errno) {
		GeneralCommonTest common = new GeneralCommonTest(12); 
		common.handleStmt(fileName, errno);
	}
	
	private void handlePushExpr(String fileName, int errno) {
		GeneralCommonTest common = new GeneralCommonTest(12); 
		common.doPushExpr(fileName, errno);
	}
	
	private void handlePushStmt(String fileName, int errno) {
		GeneralCommonTest common = new GeneralCommonTest(12); 
		common.doPushStmt(fileName, errno);
	}
	
	private void doPushExpr(int errno) {
		handlePushExpr("doPushExpr", errno);
	}

	@Test
	public void doPushExpr200() {
		doPushExpr(200);
	}
	
	private void doPushStmt(int errno) {
		handlePushStmt("doPushStmt", errno);
	}

	@Test
	public void doPushStmt210() {
		doPushStmt(210);
	}
	
	@Test
	public void doPushStmt220() {
		doPushStmt(220);
	}
	
	@Test
	public void doPushStmt230() {
		doPushStmt(230);
	}
	
	@Test
	public void doPushStmt240() {
		doPushStmt(240);
	}
	
	@Test
	public void doPushStmt250() {
		doPushStmt(250);
	}
	
	private void doZeroDiv(int errno) {
		handleStmt("doZeroDiv", errno);
	}
	
	@Test
	public void doZeroDiv100() {
		doZeroDiv(100);
	}
/*
	private void doStmtInExpr(int errno) {
		handleStmt("doStmtInExpr", errno);
	}
	
	@Test
	public void doStmtInExpr120() {
		doStmtInExpr(120);
	}
*/
}

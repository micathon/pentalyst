package test;

import org.junit.Test;

public class RtMainTest {

	public void handleStmt(String fileName, int errno) {
		GeneralCommonTest common = new GeneralCommonTest(12); 
		common.handleStmt(fileName, errno);
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

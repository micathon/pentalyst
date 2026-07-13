package test;

import org.junit.Test;

public class RtCallTest {

	public void handleStmt(String fileName, int errno) {
		GeneralCommonTest common = new GeneralCommonTest(15); 
		common.handleStmt(fileName, errno);
	}

	public void handleStmtVal(String fileName, int errno, int ival) {
		GeneralCommonTest common = new GeneralCommonTest(15); 
		common.handleStmtVal(fileName, errno, ival);
	}

	private void doParmCall(int errno, int ival) {
		handleStmtVal("doParmCall", errno, ival);
	}
	
	@Test
	public void doParmCall200() {
		doParmCall(200, 205);
	}
	
}

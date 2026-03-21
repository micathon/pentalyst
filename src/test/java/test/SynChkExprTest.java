package test;

import org.junit.Test;

public class SynChkExprTest {

	private void handleStmt(String fileName, int miderrno, int errno) {
		SynChkCommonTest common = new SynChkCommonTest(1); 
		common.handleStmt(fileName, miderrno, errno);
	}
	
	private void handleStmtRtn(String fileName, int miderrno, int errno,
		int suffixNo) 
	{
		SynChkCommonTest common = new SynChkCommonTest(1); 
		common.handleStmtRtn(fileName, miderrno, errno, suffixNo);
	}
	
	private void doKwdConst(int errno) {
		handleStmt("doKwdConst", 40, errno);
	}
	
	@Test
	public void doKwdConst10() {
		doKwdConst(10);
	}
	
}

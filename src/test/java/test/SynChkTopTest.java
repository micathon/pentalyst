package test;

import org.junit.Test;

public class SynChkTopTest {

	private void handleStmt(String fileName, int miderrno, int errno) {
		SynChkCommonTest common = new SynChkCommonTest(3); 
		common.handleStmt(fileName, miderrno, errno);
	}

	private void doStmt(int errno) {
		handleStmt("doStmt", 10, errno);
	}
	
	@Test
	public void doStmt10() {
		doStmt(10);
	}
	
}

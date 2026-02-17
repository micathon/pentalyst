package test;

import org.junit.Test;

public class SynChkStmtTest {

	private void handleStmt(String fileName, int miderrno, int errno) {
		SynChkCommonTest common = new SynChkCommonTest(2); 
		common.handleStmt(fileName, miderrno, errno);
	}
	
	private void doIfStmt(int errno) {
		handleStmt("doIfStmt", 30, errno);
	}
	
	@Test
	public void doIfStmt10() {
		doIfStmt(10);
	}
	
	@Test
	public void doIfStmt20() {
		doIfStmt(20);
	}
	
	@Test
	public void doIfStmt30() {
		doIfStmt(30);
	}
	
	@Test
	public void doIfStmt40() {
		doIfStmt(40);
	}
	
	@Test
	public void doIfStmt50() {
		doIfStmt(50);
	}
	
	@Test
	public void doIfStmt60() {
		doIfStmt(60);
	}
	
}

package test;

import org.junit.Test;

public class RtScanTest {

	public void handleStmt(String fileName, int errno) {
		GeneralCommonTest common = new GeneralCommonTest(11); 
		common.handleStmt(fileName, errno);
	}
	
	private void doLocVar(int errno) {
		handleStmt("doLocVar", errno);
	}
	
	@Test
	public void doLocVar100() {
		doLocVar(100);
	}
/*	
	private void doDefunStmt(int errno) {
		handleStmt("doDefunStmt", errno);
	}
	
	@Test
	public void doDefunStmt150() {
		doDefunStmt(150);
	}
*/	
}

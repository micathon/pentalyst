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
	
	private void doFunRtnVal(int errno, int ival) {
		handleStmtVal("doFunRtnVal", errno, ival);
	}
	
	@Test
	public void doFunRtnVal220() {
		doFunRtnVal(220, 7);
	}
	
	private void doGlbCallLen(int errno, int ival) {
		handleStmtVal("doGlbCallLen", errno, ival);
	}
	
	@Test
	public void doGlbCallLen300() {
		doGlbCallLen(300, 3);
	}
	
}

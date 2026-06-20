package test;

import org.junit.Test;

public class RtOperTest {

	public void handleStmt(String fileName, int errno) {
		GeneralCommonTest common = new GeneralCommonTest(13); 
		common.handleStmt(fileName, errno);
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
}

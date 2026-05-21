package test;

import config.Config;
import init.InitMain;

import static org.junit.Assert.*;

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
	
}

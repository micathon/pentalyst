package test;

import org.junit.Test;
import iconst.KeywordTyp;

public class RtFlowTest {

	private double zdval;

	public void handleStmt(String fileName, int errno) {
		GeneralCommonTest common = new GeneralCommonTest(14); 
		common.handleStmt(fileName, errno);
	}

	public void handleStmtValues(String fileName, int errno,
			int ival, double dval) 
		{
			GeneralCommonTest common = new GeneralCommonTest(14); 
			zdval = common.getZdval();
			common.handleStmtValues(fileName, errno, ival, dval);
		}

	private void doIfStmt(int errno, int ival) {
		handleStmtValues("doIfStmt", errno, ival, zdval);
	}
	
	@Test
	public void doIfStmt100() {
		doIfStmt(100, KeywordTyp.IF.ordinal());
	}
	
	@Test
	public void doIfStmt110() {
		doIfStmt(110, KeywordTyp.ELIF.ordinal());
	}

	@Test
	public void doIfStmt120() {
		doIfStmt(120, KeywordTyp.ELSE.ordinal());
	}
	
	@Test
	public void doIfStmt130() {
		doIfStmt(130, KeywordTyp.FOR.ordinal());
	}
	
	@Test
	public void doIfStmt140() {
		doIfStmt(140, KeywordTyp.CASE.ordinal());
	}
	
	@Test
	public void doIfStmt150() {
		doIfStmt(150, KeywordTyp.CASE.ordinal());
	}
	
	@Test
	public void doIfStmt160() {
		doIfStmt(160, KeywordTyp.ELSE.ordinal());
	}

	@Test
	public void doIfStmt161() {
		doIfStmt(161, KeywordTyp.ELSE.ordinal());
	}
	
	private void doWhileStmt(int errno, int ival) {
		handleStmtValues("doWhileStmt", errno, ival, zdval);
	}
	
	@Test
	public void doWhileStmt200() {
		doWhileStmt(200, 0);
	}
	
	@Test
	public void doWhileStmt210() {
		doWhileStmt(210, 1);
	}

	@Test
	public void doWhileStmt220() {
		doWhileStmt(220, 2);
	}
	
}

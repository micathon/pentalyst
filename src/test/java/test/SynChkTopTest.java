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
	
	@Test
	public void doStmt30() {
		doStmt(30);
	}
/*	
	@Test
	public void doStmt40() {
		doStmt(40);
	}
	
	@Test
	public void doStmt50() {
		doStmt(50);
	}

	@Test
	public void doStmt60() {
		doStmt(60);
	}
*/
	private void doImportKwd(int errno) {
		handleStmt("doImportKwd", 40, errno);
	}
	
	@Test
	public void doImportKwd10() {
		doImportKwd(10);
	}
	
	@Test
	public void doImportKwd20() {
		doImportKwd(20);
	}
	
	@Test
	public void doImportKwd30() {
		doImportKwd(30);
	}
/*
	@Test
	public void doImportKwd50() {
		doImportKwd(50);
	}

	@Test
	public void doImportKwd60() {
		doImportKwd(60);
	}

	@Test
	public void doImportKwd70() {
		doImportKwd(70);
	}
*/
	private void doColonList(int errno) {
		handleStmt("doColonList", 50, errno);
	}
/*	
	@Test
	public void doColonList10() {
		doColonList(10);
	}
	
	@Test
	public void doColonList20() {
		doColonList(20);
	}
*/	
	private void doFromKwd(int errno) {
		handleStmt("doFromKwd", 70, errno);
	}
	
	@Test
	public void doFromKwd10() {
		doFromKwd(10);
	}
/*	
	@Test
	public void doFromKwd20() {
		doFromKwd(20);
	}
*/	
	@Test
	public void doFromKwd30() {
		doFromKwd(30);
	}

	@Test
	public void doFromKwd40() {
		doFromKwd(40);
	}
/*
	@Test
	public void doFromKwd50() {
		doFromKwd(50);
	}

	@Test
	public void doFromKwd60() {
		doFromKwd(60);
	}
*/
	private void doGlbDefStmt(int errno) {
		handleStmt("doGlbDefStmt", 80, errno);
	}
	
	@Test
	public void doGlbDefStmt10() {
		doGlbDefStmt(10);
	}

	@Test
	public void doGlbDefStmt20() {
		doGlbDefStmt(20);
	}

	@Test
	public void doGlbDefStmt30() {
		doGlbDefStmt(30);
	}

	@Test
	public void doGlbDefStmt50() {
		doGlbDefStmt(50);
	}

}

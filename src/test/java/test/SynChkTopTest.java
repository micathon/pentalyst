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

	private void doDefunStmt(int errno) {
		handleStmt("doDefunStmt", 100, errno);
	}
	
	@Test
	public void doDefunStmt10() {
		doDefunStmt(10);
	}

	@Test
	public void doDefunStmt20() {
		doDefunStmt(20);
	}

	@Test
	public void doDefunStmt30() {
		doDefunStmt(30);
	}

	@Test
	public void doDefunStmt40() {
		doDefunStmt(40);
	}
/*
	@Test
	public void doDefunStmt50() {
		doDefunStmt(50);
	}

	@Test
	public void doDefunStmt60() {
		doDefunStmt(60);
	}

	@Test
	public void doDefunStmt80() {
		doDefunStmt(80);
	}
*/
	private void doAbDefunStmt(int errno) {
		handleStmt("doAbDefunStmt", 110, errno);
	}
	
	@Test
	public void doAbDefunStmt10() {
		doAbDefunStmt(10);
	}

	@Test
	public void doAbDefunStmt20() {
		doAbDefunStmt(20);
	}

	@Test
	public void doAbDefunStmt30() {
		doAbDefunStmt(30);
	}

	@Test
	public void doAbDefunStmt40() {
		doAbDefunStmt(40);
	}
/*
	@Test
	public void doAbDefunStmt50() {
		doAbDefunStmt(50);
	}

	@Test
	public void doAbDefunStmt60() {
		doAbDefunStmt(60);
	}

	@Test
	public void doAbDefunStmt70() {
		doAbDefunStmt(70);
	}
*/
	private void doDecorList(int errno) {
		handleStmt("doDecorList", 120, errno);
	}
	
	@Test
	public void doDecorList10() {
		doDecorList(10);
	}

	@Test
	public void doDecorList20() {
		doDecorList(20);
	}

	@Test
	public void doDecorList30() {
		doDecorList(30);
	}

	@Test
	public void doDecorList40() {
		doDecorList(40);
	}

	private void doDotCall(int errno) {
		handleStmt("doDotCall", 140, errno);
	}
	
	@Test
	public void doDotCall10() {
		doDotCall(10);
	}

	@Test
	public void doDotCall20() {
		doDotCall(20);
	}

	@Test
	public void doDotCall30() {
		doDotCall(30);
	}

	@Test
	public void doDotCall40() {
		doDotCall(40);
	}

	private void doParmList(int errno) {
		handleStmt("doParmList", 150, errno);
	}
	
	@Test
	public void doParmList10() {
		doParmList(10);
	}

	@Test
	public void doParmList20() {
		doParmList(20);
	}

	@Test
	public void doParmList30() {
		doParmList(30);
	}

	@Test
	public void doParmList40() {
		doParmList(40);
	}

}

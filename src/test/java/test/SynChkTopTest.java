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

	private void doDefParm(int errno) {
		handleStmt("doDefParm", 170, errno);
	}
	
	@Test
	public void doDefParm10() {
		doDefParm(10);
	}

	@Test
	public void doDefParm20() {
		doDefParm(20);
	}

	private void doClassStmt(int errno) {
		handleStmt("doClassStmt", 240, errno);
	}
	
	@Test
	public void doClassStmt10() {
		doClassStmt(10);
	}

	@Test
	public void doClassStmt20() {
		doClassStmt(20);
	}

	@Test
	public void doClassStmt30() {
		doClassStmt(30);
	}

	@Test
	public void doClassStmt35() {
		doClassStmt(35);
	}

	@Test
	public void doClassStmt40() {
		doClassStmt(40);
	}
/*
	@Test
	public void doClassStmt50() {
		doClassStmt(50);
	}

	@Test
	public void doClassStmt55() {
		doClassStmt(55);
	}
*/
	@Test
	public void doClassStmt60() {
		doClassStmt(60);
	}
/*
	@Test
	public void doClassStmt70() {
		doClassStmt(70);
	}
*/
	private void doHedronStmt(int errno) {
		handleStmt("doHedronStmt", 260, errno);
	}
	
	@Test
	public void doHedronStmt10() {
		doHedronStmt(10);
	}
/*
	@Test
	public void doHedronStmt20() {
		doHedronStmt(20);
	}

	@Test
	public void doHedronStmt25() {
		doHedronStmt(25);
	}

	@Test
	public void doHedronStmt40() {
		doHedronStmt(40);
	}

	@Test
	public void doHedronStmt50() {
		doHedronStmt(50);
	}
*/
	private void doConstList(int errno) {
		handleStmt("doConstList", 280, errno);
	}
/*	
	@Test
	public void doConstList10() {
		doConstList(10);
	}

	@Test
	public void doConstList30() {
		doConstList(30);
	}

	@Test
	public void doConstList20() {
		doConstList(20);
	}
*/
	private void doEnumStmt(int errno) {
		handleStmt("doEnumStmt", 300, errno);
	}
	
	@Test
	public void doEnumStmt10() {
		doEnumStmt(10);
	}

	@Test
	public void doEnumStmt20() {
		doEnumStmt(20);
	}

	@Test
	public void doEnumStmt30() {
		doEnumStmt(30);
	}

	@Test
	public void doEnumStmt40() {
		doEnumStmt(40);
	}

	private void doEnumPair(int errno) {
		handleStmt("doEnumPair", 320, errno);
	}
	
	@Test
	public void doEnumPair10() {
		doEnumPair(10);
	}

	@Test
	public void doEnumPair20() {
		doEnumPair(20);
	}

	@Test
	public void doEnumPair30() {
		doEnumPair(30);
	}

	@Test
	public void doEnumPair40() {
		doEnumPair(40);
	}
/*
	@Test
	public void doEnumPair50() {
		doEnumPair(50);
	}

	@Test
	public void doEnumPair60() {
		doEnumPair(60);
	}
*/
	private void doDoesList(int errno) {
		handleStmt("doDoesList", 400, errno);
	}
/*	
	@Test
	public void doDoesList10() {
		doDoesList(10);
	}
*/
	private void doDoDefBlock(int errno) {
		handleStmt("doDoDefBlock", 410, errno);
	}
	
	@Test
	public void doDoDefBlock20() {
		doDoDefBlock(20);
	}

	@Test
	public void doDoDefBlock30() {
		doDoDefBlock(30);
	}

	@Test
	public void doDoDefBlock40() {
		doDoDefBlock(40);
	}

	private void doHedronDoBlock(int errno) {
		handleStmt("doHedronDoBlock", 420, errno);
	}
/*	
	@Test
	public void doHedronDoBlock20() {
		doHedronDoBlock(20);
	}
*/
}

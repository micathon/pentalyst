package test;

import org.junit.Test;

public class SynChkExprTest {

	private void handleStmt(String fileName, int miderrno, int errno) {
		SynChkCommonTest common = new SynChkCommonTest(1); 
		common.handleStmt(fileName, miderrno, errno);
	}
/*	
	private void handleStmtRtn(String fileName, int miderrno, int errno,
		int suffixNo) 
	{
		SynChkCommonTest common = new SynChkCommonTest(1); 
		common.handleStmtRtn(fileName, miderrno, errno, suffixNo);
	}
*/	
	private void doKwdConst(int errno) {
		handleStmt("doKwdConst", 40, errno);
	}
	
	@Test
	public void doKwdConst10() {
		doKwdConst(10);
	}
/*
	private void doExpr(int errno) {
		handleStmt("doExpr", 10, errno);
	}
	
	@Test
	public void doExpr50() {
		doExpr(50);
	}
*/	
	private void doUnaryOp(int errno) {
		handleStmt("doUnaryOp", 100, errno);
	}
	
	@Test
	public void doUnaryOp10() {
		doUnaryOp(10);
	}
	
	@Test
	public void doUnaryOp20() {
		doUnaryOp(20);
	}
	
	private void doMinusOp(int errno) {
		handleStmt("doMinusOp", 130, errno);
	}
	
	@Test
	public void doMinusOp10() {
		doMinusOp(10);
	}
	
	@Test
	public void doMinusOp20() {
		doMinusOp(20);
	}
	
	private void doQuestOp(int errno) {
		handleStmt("doQuestOp", 140, errno);
	}
	
	@Test
	public void doQuestOp10() {
		doQuestOp(10);
	}
	
	@Test
	public void doQuestOp20() {
		doQuestOp(20);
	}
	
	private void doCquestOp(int errno) {
		handleStmt("doCquestOp", 145, errno);
	}
	
	@Test
	public void doCquestOp10() {
		doCquestOp(10);
	}
	
	@Test
	public void doCquestOp20() {
		doCquestOp(20);
	}
	
	@Test
	public void doCquestOp30() {
		doCquestOp(30);
	}
	
	private void doSwixOp(int errno) {
		handleStmt("doSwixOp", 146, errno);
	}
	
	@Test
	public void doSwixOp10() {
		doSwixOp(10);
	}
/*	
	@Test
	public void doSwixOp90() {
		doSwixOp(90);
	}
*/	
	@Test
	public void doSwixOp20() {
		doSwixOp(20);
	}

	@Test
	public void doSwixOp30() {
		doSwixOp(30);
	}

	@Test
	public void doSwixOp40() {
		doSwixOp(40);
	}

	@Test
	public void doSwixOp50() {
		doSwixOp(50);
	}

	private void doMultiOp(int errno) {
		handleStmt("doMultiOp", 150, errno);
	}
	
	@Test
	public void doMultiOp10() {
		doMultiOp(10);
	}
	
	@Test
	public void doMultiOp20() {
		doMultiOp(20);
	}
	
	private void doBinaryOp(int errno) {
		handleStmt("doBinaryOp", 160, errno);
	}
	
	@Test
	public void doBinaryOp10() {
		doBinaryOp(10);
	}
	
	@Test
	public void doBinaryOp20() {
		doBinaryOp(20);
	}
	
	private void doListOp(int errno) {
		handleStmt("doListOp", 180, errno);
	}
	
	@Test
	public void doListOp10() {
		doListOp(10);
	}
/*	
	@Test
	public void doListOp20() {
		doListOp(20);
	}
*/	
	private void doDictOp(int errno) {
		handleStmt("doDictOp", 210, errno);
	}
	
	@Test
	public void doDictOp10() {
		doDictOp(10);
	}
	
	@Test
	public void doDictOp20() {
		doDictOp(20);
	}
	
	private void doSliceOp(int errno) {
		handleStmt("doSliceOp", 230, errno);
	}
	
	@Test
	public void doSliceOp10() {
		doSliceOp(10);
	}
	
	@Test
	public void doSliceOp20() {
		doSliceOp(20);
	}
	
	@Test
	public void doSliceOp30() {
		doSliceOp(30);
	}
	
	@Test
	public void doSliceOp40() {
		doSliceOp(40);
	}
/*	
	@Test
	public void doSliceOp50() {
		doSliceOp(50);
	}
	
	@Test
	public void doSliceOp60() {
		doSliceOp(60);
	}
*/	
	private void doDotOp(int errno) {
		handleStmt("doDotOp", 250, errno);
	}
/*	
	@Test
	public void doDotOp10() {
		doDotOp(10);
	}
*/	
	@Test
	public void doDotOp20() {
		doDotOp(20);
	}
	
	@Test
	public void doDotOp30() {
		doDotOp(30);
	}
/*	
	@Test
	public void doDotOp40() {
		doDotOp(40);
	}
	
	private void doCallOp(int errno) {
		handleStmt("doCallOp", 260, errno);
	}

	@Test
	public void doCallOp10() {
		doCallOp(10);
	}

	@Test
	public void doCallOp20() {
		doCallOp(20);
	}
*/
	private void doCastOp(int errno) {
		handleStmt("doCastOp", 320, errno);
	}

	@Test
	public void doCastOp10() {
		doCastOp(10);
	}

	@Test
	public void doCastOp20() {
		doCastOp(20);
	}

	@Test
	public void doCastOp30() {
		doCastOp(30);
	}

	@Test
	public void doCastOp40() {
		doCastOp(40);
	}

	@Test
	public void doCastOp50() {
		doCastOp(50);
	}

}

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
/*	
	@Test
	public void doIfStmt50() {
		doIfStmt(50);
	}
*/	
	@Test
	public void doIfStmt60() {
		doIfStmt(60);
	}
	
	private void doSetOpStmt(int errno) {
		handleStmt("doSetOpStmt", 40, errno);
	}
/*	
	@Test
	public void doSetOpStmt10() {
		doSetOpStmt(10);
	}
*/	
	@Test
	public void doSetOpStmt20() {
		doSetOpStmt(20);
	}

	private void doSetStmtTail(int errno) {
		handleStmt("doSetStmtTail", 50, errno);
	}
/*	
	@Test
	public void doSetStmtTail10() {
		doSetStmtTail(10);
	}
*/	
	@Test
	public void doSetStmtTail20() {
		doSetStmtTail(20);
	}
	
	@Test
	public void doSetStmtTail30() {
		doSetStmtTail(30);
	}
	
	private void doSetStmt(int errno) {
		handleStmt("doSetStmt", 60, errno);
	}
	
	@Test
	public void doSetStmt10() {
		doSetStmt(10);
	}
	
	private void doSetTuple(int errno) {
		handleStmt("doSetTuple", 70, errno);
	}
	
	@Test
	public void doSetTuple40() {
		doSetTuple(40);
	}
/*	
	@Test
	public void doSetTuple50() {
		doSetTuple(50);
	}
*/	
	private void doIncDecStmt(int errno) {
		handleStmt("doIncDecStmt", 80, errno);
	}
	
	@Test
	public void doIncDecStmt10() {
		doIncDecStmt(10);
	}
	
	@Test
	public void doIncDecStmt20() {
		doIncDecStmt(20);
	}
	
	@Test
	public void doIncDecStmt30() {
		doIncDecStmt(30);
	}
	
	private void doWhileStmt(int errno) {
		handleStmt("doWhileStmt", 90, errno);
	}
	
	@Test
	public void doWhileStmt10() {
		doWhileStmt(10);
	}
	
	@Test
	public void doWhileStmt20() {
		doWhileStmt(20);
	}
	
	@Test
	public void doWhileStmt30() {
		doWhileStmt(30);
	}
	
	@Test
	public void doWhileStmt40() {
		doWhileStmt(40);
	}
	
	@Test
	public void doWhileStmt45() {
		doWhileStmt(45);
	}
/*	
	@Test
	public void doWhileStmt50() {
		doWhileStmt(50);
	}
	
	@Test
	public void doWhileStmt60() {
		doWhileStmt(60);
	}
	
	@Test
	public void doWhileStmt70() {
		doWhileStmt(70);
	}
	
	@Test
	public void doWhileStmt80() {
		doWhileStmt(80);
	}
*/	
	private void doForStmt(int errno) {
		handleStmt("doForStmt", 100, errno);
	}
	
	@Test
	public void doForStmt10() {
		doForStmt(10);
	}
	
}

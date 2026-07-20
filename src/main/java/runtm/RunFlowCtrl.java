package runtm;

import iconst.IConst;
import iconst.KeywordTyp;
import iconst.PageTyp;
import iconst.RunConst;
import page.AddrNode;
import page.Node;
import page.Page;
import page.Store;

public class RunFlowCtrl implements IConst, RunConst {

	// scroll down to pushIfStmt:
	// - stack behavior of IF and WHILE statements
	
	private Store store;
	private RunTime rt;
	private RunOperators runop;
	private RunScanner rscan;
	private RunPushPop pp;
	private RunUTime ut;
	
	public RunFlowCtrl(Store store, RunTime rt, RunPushPop pp,
		RunOperators runop, RunUTime ut) 
	{
		this.store = store;
		this.rt = rt;
		this.pp = pp;
		this.runop = runop;
		this.ut = ut;
	}
	
	public void setRscan(RunScanner rscan) {
		this.rscan = rscan;
	}

	private void omsg(String msg) {
		rt.omsg(msg);
	}
	
	private void omsgz(String msg) {
		rt.omsgz(msg);
	}
	
	private void oprn(String msg) {
		System.out.println(msg);
	}

	public int handleLogicalKwd(KeywordTyp kwtop, int rightp) {
		Node node;
		AddrNode addrNode;
		PageTyp pgtyp;
		int ival, jval, kval;
		KeywordTyp kwtyp;
		boolean isShortCircuit;
		
		isShortCircuit = false;
		omsg("hlogkw: top, rightp = " + rightp);
		addrNode = store.topNode();
		pgtyp = addrNode.getHdrPgTyp();
		ival = addrNode.getAddr();
		omsg("hlogkw: pgtyp = " + pgtyp + ", addr = " + ival);
		if (pgtyp == PageTyp.KWD) {
			ival = pp.topIntVal();  // = -1
			jval = 0;
			omsg("hlogkw: KWD ival = " + ival);
		}
		else if (kwtop == KeywordTyp.QUEST) { 
			rightp = logicalQuestKwd(rightp);
			return rightp;
		}
		else if (kwtop == KeywordTyp.CASE) { 
			rightp = logicalCaseKwd(rightp);
			return rightp;
		}
		else {
			addrNode = store.popNode();
			if (addrNode == null) {
				return STKUNDERFLOW;
			}
			pgtyp = addrNode.getHdrPgTyp();
			omsg("hlogkw: pgtyp = " + pgtyp);
			if (pgtyp != PageTyp.BOOLEAN) {
				return BADOPTYP; 
			}
			jval = pp.nodeToIntVal(addrNode, rt.getLocBaseIdx());  
			ival = pp.topIntVal();  // = 0 or 1
			omsg("hlogkw: top ival = " + ival + ", jval = " + jval);
		}
		switch (kwtop) {
		case AND:
			omsg("hlogkw: AND");
			kval = 1;
			if (ival >= 0) {
				isShortCircuit = 
					((ival == 0) || (jval == 0));
				kval = isShortCircuit ? 0 : 1;
			}
			if (store.popNode() == null) {
				return STKUNDERFLOW;
			}
			else if (!pp.pushKwdVal(kval)) {
				return STKOVERFLOW;
			}
			break;
		case OR:
			omsg("hlogkw: OR, ival = " + ival);
			kval = 0;
			if (ival >= 0) {
				isShortCircuit = 
					((ival == 1) || (jval == 1));
				kval = isShortCircuit ? 1 : 0;
				omsgz("hlogkw: OR ival = " + ival); 
				omsg(", jval = " + jval + ", kval = " + kval);
			}
			if (store.popNode() == null) {
				return STKUNDERFLOW;
			}
			else if (!pp.pushKwdVal(kval)) {
				return STKOVERFLOW;
			}
			break;
		default:
			break;
		} 
		if (isShortCircuit) {
			// skip over calling getRightp until zero:
			while (rightp > 0) {
				node = store.getNode(rightp);
				rightp = node.getRightp();
			}
		}
		else if (rightp == 0) {
			return EXIT;  // got to end, no short circuit
		}
		return rightp;
	}		

	public int logicalQuestKwd(int rightp) {
		Node node;
		AddrNode addrNode;
		int ival, jval;

		addrNode = store.popNode();
		if (addrNode == null) {
			return STKUNDERFLOW;
		}
		ival = pp.topIntVal(); 
		omsg("logicalQuestKwd: top, ival = " + ival);
		if (ival < 0) {
			if (store.popNode() == null) {  // pop -1
				return STKUNDERFLOW;
			}
			if (addrNode.getHdrPgTyp() != PageTyp.BOOLEAN) {
				return BADOPTYP; 
			}
			jval = pp.nodeToIntVal(addrNode, rt.getLocBaseIdx());
			omsg("logicalQuestKwd: jval = " + jval);
			if (jval == 0) { }
			else if (!pp.pushKwdVal(0)) {
				return STKOVERFLOW;
			}
			else {
				return rightp;
			}
			node = store.getNode(rightp);
			rightp = node.getRightp();
			if (rightp <= 0) {
				return GENERR;
			}
			omsg("logicalQuestKwd: rightp = " + rightp);
			return rightp;
		}
		else if (ival == 0) {
			if (store.popNode() == null) {  
				return STKUNDERFLOW;
			}
			if (!store.pushNode(addrNode)) {
				return STKOVERFLOW;
			}
			node = store.getNode(rightp);
			rightp = node.getRightp();
			return rightp;
		}
		else {
			omsg("logicalQuestKwd: bad ival = " + ival);
			return GENERR;
		}
	}

	private int logicalCaseKwd(int rightp) {
		Node node;
		AddrNode addrNode;
		KeywordTyp kwtyp;
		KeywordTyp kwtop;
		boolean isCqCase;
		boolean isCqTrue;
		boolean isSwixCase;
		int ival, jval;
		int rtnval;
		int stkidx;
		PageTyp pgtyp;
		
		omsg("logicalCaseKwd: top");
		kwtop = popKwd();
		kwtyp = topKwd();
		omsg("logicalCaseKwd: kwtyp = " + kwtyp);
		isCqCase = (kwtyp == KeywordTyp.CQUEST);
		isSwixCase = (kwtyp == KeywordTyp.SWIX);
		pushOp(kwtop);
		if (!isCqCase && !isSwixCase) {
			return rightp;
		}
		if (isSwixCase) {
			popKwd();
			addrNode = store.popNode();
			popVal();
			store.swapNodes();
			store.pushNode(addrNode);
			rtnval = runop.runEqExpr();
			if (rtnval < 0) {
				return rtnval;
			}
		}
		stkidx = pp.popIntStk();
		if (stkidx < 0) {
			return stkidx;
		}
		addrNode = store.fetchNode(stkidx);
		pgtyp = addrNode.getHdrPgTyp();
		if (pgtyp != PageTyp.BOOLEAN) { 
			omsg("logicalCaseKwd: BADOPTYP");
			return BADOPTYP;
		}
		ival = addrNode.getAddr();
		omsg("logicalCaseKwd: ival = " + ival);
		if (ival == 1) {
			return rightp;
		}
		kwtyp = popKwd();
		if (kwtyp == KeywordTyp.SWIX) {
			pushOp(kwtyp);
			addrNode = store.popNode();
			if (pp.topIntVal() == 0) {
				// no cases were true:
				omsg("logicalCaseKwd: top = 0 (swix)");
				popKwd();
				popVal();
				popVal();
				pushAddr(0);
				return 0;
			}
			rightp = popVal();
			store.pushNode(addrNode);
			return rightp;
		}
		popVal();
		if (pp.topIntVal() == 0) {
			// no cases were true:
			omsg("logicalCaseKwd: top = 0");
			popKwd();
			popVal();
			popVal();
			pushAddr(0);
			return 0;
		}
		rightp = popVal();
		return rightp;
	}

	public int pushWhileStmt(Node node, int rightp) {
		KeywordTyp kwtyp;

		omsg("(2) pushWhileStmt: top");
		rt.setAfterStmtKwd(true);
		kwtyp = KeywordTyp.WHILE;
		if (!pushOp(kwtyp) || !pushAddr(rightp)) {
			return STKOVERFLOW;
		}
		rightp = node.getRightp();
		ut.z0211(260, rightp);
		return rightp;
	}
	
	public int pushForStmt(Node node, int rightp) {
		KeywordTyp kwtyp;

		omsg("(2) pushForStmt: top");
		rt.setAfterStmtKwd(true);
		kwtyp = KeywordTyp.FOR;
		if (!pushOp(kwtyp)) { // || !pushAddr(rightp)) {
			return STKOVERFLOW;
		}
		rightp = node.getRightp();
		node = store.getNode(rightp);
		// now we're at do #1
		if (rt.getForContinue()) {
			rightp = node.getRightp();
			// debug:
			node = store.getNode(rightp);
			kwtyp = node.getKeywordTyp();
			omsg("pushForStmt: kwtyp = " + kwtyp);
			rt.setForContinue(false);
		}
		return rightp;
	}
	
	public int runForStmt() {  
		// end of for loop header reached
		// loop control flag on stack
		int rightp;
		int stkidx;
		int ival;
		Node node;
		AddrNode addrNode;
		PageTyp pgtyp;
		
		omsg("runForStmt: top");
		stkidx = pp.popIntStk();
		if (stkidx < 0) {
			return stkidx;
		}
		addrNode = store.fetchNode(stkidx);
		pgtyp = addrNode.getHdrPgTyp();
		if (pgtyp != PageTyp.BOOLEAN) { 
			omsg("handleDoToken: BADOPTYP");
			return BADOPTYP;
		}
		ival = addrNode.getAddr();
		if (ival == 0) {
			popVal(); // 2nd zstmt in header
			rightp = popVal(); // zstmt of for
			popVal(); // ZSTMT
			node = store.getNode(rightp);
			rightp = node.getRightp();
			popKwd(); // QUEST
			popKwd(); // FOR
			return rightp;
		}
		popVal(); // 2nd zstmt in header
		rightp = popVal(); // zstmt of for  
		node = store.getNode(rightp);
		popKwd(); // QUEST
		if (!pushAddr(rightp)) { // zstmt of for
			return STKOVERFLOW; 
		}
		rightp = node.getDownp();
		node = store.getNode(rightp);
		rightp = node.getRightp();
		node = store.getNode(rightp);
		rightp = node.getRightp();
		node = store.getNode(rightp);
		rightp = node.getRightp();
		node = store.getNode(rightp);
		rightp = node.getDownp();
		return rightp; 
	}

	public int handleDoToken(Node node, int rightp) {
		KeywordTyp kwtyp;
		AddrNode addrNode;
		PageTyp pgtyp;
		int stkidx;
		int ival;
		int rtnval;
		boolean isWhile;
		boolean isCase;
		boolean isSwitch;
		KeywordTyp altkwd;
		
		kwtyp = topKwd();
		isWhile = (kwtyp == KeywordTyp.WHILE);
		isCase = (kwtyp == KeywordTyp.CASE);
		rt.setWhileUntil(false);
		switch (kwtyp) {
		case IF:
		case ELIF:
		case CASE:
		case WHILE:
			omsg("handleDoToken: afterStmtKwd = " + rt.getAfterStmtKwd());
			if (isWhile && rt.getAfterStmtKwd()) {
				rt.setWhileUntil(true);
				popKwd();
				pushOp(KeywordTyp.UNTIL);
				ival = 1;
				break;
			}
			if (isCase) {
				popKwd();
				altkwd = topKwd();
				isSwitch = (altkwd == KeywordTyp.SWITCH);
				ut.setSwitch(isSwitch);
				rtnval = runop.runEqExpr();
				if (rtnval < 0) {
					return rtnval;
				}
			}
			stkidx = pp.popIntStk();
			if (stkidx < 0) {
				return stkidx;
			}
			addrNode = store.fetchNode(stkidx);
			pgtyp = addrNode.getHdrPgTyp();
			if (pgtyp != PageTyp.BOOLEAN) { 
				omsg("handleDoToken: BADOPTYP");
				return BADOPTYP;
			}
			ival = addrNode.getAddr();
			if (isWhile) {
				omsg("(3) handleDoToken: ival = " + ival);
			}
			break;
		case ELSE:
			ival = 1;
			break;
		case FOR:
			omsg("handleDoToken: FOR");
			ival = 1;
			break;
		default:
			return BADDOSTMT;
		}
		// if ival = 1 then do block is executed
		// else (ival = 0):
		omsg("(3) handleDoToken: ival = " + ival);
		omsg("handleDoToken: bool as int = " + ival);
		if (ival == 1) { }
		//else if (isWhile && !afterStmtKwd) {
		else if (isWhile) {
			rightp = popVal();  // points to while stmt
			omsg("handleDoToken: WHILE LOOP EXIT");
			return 0;
		}
		else {
			ut.setSkippedCase(true);
			rightp = node.getRightp();
			return rightp;
		}
		rightp = node.getDownp();
		if (!pushAddr(rightp)) {
			return STKOVERFLOW;
		}
		if (!pushOp(KeywordTyp.DO)) {
			return STKOVERFLOW;
		}
		ut.z0300(100, kwtyp);
		return 0;
	}

	public boolean isJumpKwd(KeywordTyp kwtyp) {
		switch (kwtyp) {
		case ZCALL:
		case RETURN:
		case BREAK:
		case CONTINUE:
		case QUEST:
		case ZQUEST:
		case DO:
			omsg("isJumpKwd: kwtyp = " + kwtyp);
			return true;
		default:
			return false;
		}
	}
	
	public boolean isLogicalKwd(KeywordTyp kwtyp) {
		switch (kwtyp) {
		case AND:
		case OR:
		case QUEST:
		case CASE:
			return true;
		default:
			return false;
		}
	}

	public int runDoStmt() {
		int rightp;
		omsg("runDoStmt: top");
		if (rt.getWhileUntil()) {
			omsg("runDoStmt: isWhileUntil");
		}
		rightp = popVal(); 
		return rightp;
	}

	public boolean isQuestKwd(KeywordTyp kwtyp) {
		switch (kwtyp) {
		case QUEST:
		case CQUEST:
		case SWIX:
		case CASE:
			return true;
		default:
			return false;
		}
	}

	public boolean isBranchKwd(KeywordTyp kwtyp) {
		switch (kwtyp) {
		case IF:
		case ELIF:
		case ELSE:
			return true;
		default:
			return false;
		}
	}
	
	private boolean isLoopKwd(KeywordTyp kwtyp) {
		switch (kwtyp) {
		case WHILE:
		case FOR:
			return true;
		default:
			return false;
		}
	}
	
	public boolean isNoSwapKwd(KeywordTyp kwtyp) {
		switch (kwtyp) {
		case CASE:
			return true;
		default:
			return false;
		}
	}

	public int runBoolStmt() {
		omsg("runBoolStmt: top");
		return 0; 
	}
	
	public int runSwitchStmt() {
		omsg("runSwitchStmt: top");
		popVal();  // ZSTMT?
		return 0; 
	}
	
	public int pushBrkStmt(Node node) {
		int rightp;
		KeywordTyp kwtyp = KeywordTyp.BREAK;
		
		omsg("pushBrkStmt: top");
		if (!pushOp(kwtyp)) {
			return STKOVERFLOW;
		}
		rightp = node.getRightp();
		if (rightp > 0) {  // naked brk kwd expected
			return BADBRKSTMT;
		}
		if (!isBrkInLoop()) {
			return BADBRKSTMT;
		}
		rightp = 0;
		return rightp;
	}
	
	public int pushContinueStmt(Node node) {
		int rightp;
		KeywordTyp kwtyp = KeywordTyp.CONTINUE;
		
		omsg("pushContinueStmt: top");
		if (!pushOp(kwtyp)) {
			return STKOVERFLOW;
		}
		rightp = node.getRightp();
		if (rightp > 0) {  // naked continue kwd expected
			return BADBRKSTMT;
		}
		if (!isBrkInLoop()) {
			return BADBRKSTMT;
		}
		rightp = 0;
		return rightp;
	}
	
	public int runContinueStmt() {
		KeywordTyp kwtyp;
		int addr;
		int rightp;
		Node node;
		
		omsg("runContinueStmt: top");
		while (true) {
			kwtyp = popKwd();
			while (kwtyp == KeywordTyp.DO) { 
				kwtyp = popKwd();
			}
			switch (kwtyp) {
			case IF:
			case ELIF:
			case ELSE:
				popVal(); // addr
				popVal(); // ZSTMT
				break;
			case SWITCH:
			case CASE:
				popVal(); // switch control expr.
				popVal(); // addr
				popVal(); // ZSTMT
				break;
			case WHILE:
				popVal(); // 
				popVal(); // ZSTMT
				addr = popVal();
				popVal(); 
				popVal(); // ZSTMT
				rightp = addr;
				// debug:
				node = store.getNode(addr);
				kwtyp = node.getKeywordTyp();
				omsg("runContinueStmt, while kwtyp = " + kwtyp);
				return rightp;
			case FOR:
				rt.setForContinue(true);
				popVal(); // 
				popVal(); // ZSTMT
				addr = popVal();
				popVal(); // ZSTMT
				rightp = addr;
				// debug:
				node = store.getNode(rightp);
				kwtyp = node.getKeywordTyp();
				omsg("runContinueStmt, for kwtyp = " + kwtyp);
				return rightp;
			default:
				return BADBRKSTMT;
			}
		}
	}
	
	public int runBrkStmt() {
		KeywordTyp kwtyp;
		int addr;
		int rightp;
		Node node;
		
		while (true) {
			kwtyp = popKwd();
			while (kwtyp == KeywordTyp.DO) { 
				kwtyp = popKwd();
			}
			switch (kwtyp) {
			case IF:
			case ELIF:
			case ELSE:
				popVal(); // addr
				popVal(); // ZSTMT
				break;
			case SWITCH:
			case CASE:
				popVal(); // switch control expr.
				popVal(); // addr
				popVal(); // ZSTMT
				break;
			case WHILE:
			case FOR:
				popVal(); // 
				popVal(); // ZSTMT
				addr = popVal();
				if (kwtyp == KeywordTyp.WHILE) {
				  popVal(); // addr again 
				}
				popVal(); // ZSTMT
				node = store.getNode(addr);
				rightp = node.getRightp();
				return rightp;
			default:
				return BADBRKSTMT;
			}
		}
	}
	
	private boolean isBrkInLoop() {
		KeywordTyp kwtyp;
		int i = 0;
		boolean isDoKwd = false;
		boolean wasDoKwd;
		
		while (true) {
			kwtyp = pp.pickKwd(i);
			if (kwtyp == KeywordTyp.ZNULL) {
				return false;
			}
			if (isLoopKwd(kwtyp)) {
				return true;
			}
			wasDoKwd = isDoKwd;
			isDoKwd = (kwtyp == KeywordTyp.DO);
			if (isDoKwd && wasDoKwd) {
				return false; // bottom of function
			}
			i++;
		}
	}
	
	public boolean isKwdSkipped(KeywordTyp kwtyp) {
		switch (kwtyp) {
		case ELIF:
		case ELSE:
		//case CASE:
			return true;
		default:
			return false;
		}
	}
	
	public int handleSkipKwd(Node node, int rightp) {
		KeywordTyp kwtyp;
		
		if (topKwd() == KeywordTyp.SWIX) { 
			pushAddr(0);
		}
		else {
			popKwd();
			kwtyp = node.getKeywordTyp();
			if (!pushOp(kwtyp)) {
				return STKOVERFLOW;
			}
		}
		rightp = node.getRightp();
		return rightp;
	}
	
	public int handleCaseKwd(Node node, int rightp) {
		// don't need?
		KeywordTyp kwtyp;
		KeywordTyp switchkwd;
		int addr1, addr2;

		omsg("handleCaseKwd: top");
		kwtyp = node.getKeywordTyp();
		if (!pushOp(kwtyp)) {
			return STKOVERFLOW;
		}
		rightp = node.getRightp();
		return rightp;
	}
	
	public int pushIfStmt(Node node) {
		int rightp;
		KeywordTyp kwtyp;

		omsg("pushIfStmt: top");
		kwtyp = KeywordTyp.IF;
		if (!pushOp(kwtyp)) {
			return STKOVERFLOW;
		}
		rightp = node.getRightp();
		ut.z0211(250, rightp);
		return rightp;
	}
	
	/*
	=== IF statement ================================================
	
	Stack behavior of:
	set flag true;
	if flag do (println "if clause") else do (println "else clause");
	
	OP: push operator stack
	VAL: push value stack
	--OP: pop operator stack
	--VAL: pop value stack
	
	Notes:
	- currZstmt = rightp in main stmt loop
	- rp = rightp of currZstmt node

	IF CLAUSE: flag = T
	===================
	pushStmt:
	VAL: ZSTMT, currZstmt
	pushIfStmt:
	OP: IF
	pushExprOrLeaf:
	VAL: true
	handleDoToken:
	--VAL: true
	VAL: zstmt of DO block
	OP: DO
	--OP: DO
	runDoStmt:
	--VAL: zstmt of DO block
	pushStmt:
	<--- execute IF clause --->
	handleBtmZeroAddr:
	(isBranchKwd returns true)
	--OP: IF
	--VAL: currZstmt, ZSTMT
	currZstmt <- rp
	<--- end of if stmt --->
	
	ELSE CLAUSE: flag = F
	=====================
	pushStmt:
	VAL: ZSTMT, currZstmt
	pushIfStmt:
	OP: IF
	pushExprOrLeaf:
	VAL: false
	handleDoToken:
	--VAL: false
	pushExprOrLeaf: nil
	handleSkipKwd:
	--OP: IF
	OP: ELSE
	handleDoToken:
	VAL: zstmt of DO block
	OP: DO
	--OP: DO
	runDoStmt:
	--VAL: zstmt of DO block
	pushStmt:
	<--- execute ELSE clause --->
	handleBtmZeroAddr:
	(isBranchKwd returns true)
	--OP: ELSE
	--VAL: currZstmt, ZSTMT
	currZstmt <- rp
	<--- end of if stmt --->

	=== WHILE statement ================================================

	Stack behavior of:
	set i 0;
	while (< i 2) do (++ i);
	
	loop 2X:
		pushStmt:
		VAL: ZSTMT, currZstmt
		pushWhileStmt:
		OP: WHILE
		VAL: currZstmt
		pushExprOrLeaf: nil
		pushExpr:
		VAL: do
		OP: LT
		VAL: i, 2
		--OP: LT
		--VAL: 2, i
		VAL: 1
		--VAL: 1, do
		VAL: 1, do
		--VAL: do
		handleDoToken:
		--VAL: 1
		VAL: ++ stmt
		<--- execute loop body --->
		handleBtmZeroAddr:
		--OP: WHILE
		--VAL: currZstmt, currZstmt, ZSTMT
	loop:
		pushStmt:
		VAL: ZSTMT, currZstmt
		pushWhileStmt:
		OP: WHILE
		VAL: currZstmt
		pushExprOrLeaf: nil
		pushExpr:
		VAL: do
		OP: LT
		VAL: i, 2
		--OP: LT
		--VAL: 2, i
		VAL: 0
		--VAL: 0, do
		VAL: 0, do
		--VAL: do
		handleDoToken:
		--VAL: 0
		--VAL: currZstmt
		--OP: WHILE
		--VAL: currZstmt, ZSTMT
	
	Stack behavior of:
	set firstIter true;
	while true do (
	  if (not firstIter) do (break);
	  set firstIter false;
	);
	
	pushStmt:
	VAL: ZSTMT, currZstmt
	pushWhileStmt:
	OP: WHILE
	VAL: currZstmt
	pushExprOrLeaf: nil
	handleLeafToken:
	VAL: true
	handleDoToken:
	--VAL: 1
	VAL: if stmt
	OP: DO
	runDoStmt:
	--VAL: if stmt
	<--- execute loop body --->
	pushStmt:
	VAL: ZSTMT, currZstmt
	pushIfStmt:
	OP: IF
	pushExprOrLeaf:
	VAL: false
	handleDoToken:
	--VAL: 0
	--OP: IF
	--VAL: currZstmt, ZSTMT
	<--- execute set stmt --->
	handleBtmZeroAddr:
	--OP: WHILE
	--VAL: currZstmt, currZstmt, ZSTMT
	<--- 2nd iteration --->
	pushStmt:
	VAL: ZSTMT, currZstmt
	pushWhileStmt:
	OP: WHILE
	VAL: currZstmt
	pushExprOrLeaf: nil
	handleLeafToken:
	VAL: true
	handleDoToken:
	--VAL: 1
	VAL: if stmt
	OP: DO
	runDoStmt:
	--VAL: if stmt
	<--- execute loop body --->
	pushStmt:
	VAL: ZSTMT, currZstmt
	pushIfStmt:
	OP: IF
	pushExprOrLeaf:
	VAL: true
	handleDoToken:
	--VAL: 1
	VAL: brk stmt
	OP: DO
	runDoStmt:
	--VAL: brk stmt
	pushStmt:
	VAL: ZSTMT, currZstmt
	pushBrkStmt:
	OP: BREAK
	--OP: BREAK
	runBrkStmt:
	--OP: DO, IF
	--VAL: currZstmt, ZSTMT
	--OP: DO, WHILE
	--VAL: currZstmt, ZSTMT, currZstmt
	--VAL: currZstmt, ZSTMT
	*/
	
	public int doBtmUntilLoop() {
		int rightp;
		Node node;
		int stkidx;
		AddrNode addrNode;
		PageTyp pgtyp;
		int ival;
		
		rightp = popVal();
		node = store.getNode(rightp);
		rightp = node.getDownp();
		node = store.getNode(rightp);
		rightp = node.getRightp();
		node = store.getNode(rightp);
		rightp = node.getRightp();
		node = store.getNode(rightp);
		rightp = node.getRightp();
		rightp = rt.handleExprToken(rightp, true);
		stkidx = pp.popIntStk();
		if (stkidx < 0) {
			return stkidx;
		}
		addrNode = store.fetchNode(stkidx);
		pgtyp = addrNode.getHdrPgTyp();
		if (pgtyp != PageTyp.BOOLEAN) { 
			omsg("doBtmUntilLoop: BADOPTYP");
			return BADOPTYP;
		}
		ival = addrNode.getAddr();
		omsg("doBtmUntilLoop: ival = " + ival);
		popKwd();
		rightp = popVal();
		if (ival == 0) {
			popVal();
			popVal();
		}
		else {
			node = store.getNode(rightp);
			rightp = node.getRightp();
		}
		return rightp;
	}
	
	public int pushSwitchStmt(Node node) {
		int rightp;
		KeywordTyp kwtyp;

		omsg("pushSwitchStmt: top");
		kwtyp = KeywordTyp.SWITCH;
		if (!pushOp(kwtyp)) {
			return STKOVERFLOW;
		}
		rightp = node.getRightp();
		ut.z0211(270, rightp);
		return rightp;
	}
	
	private KeywordTyp popKwd() {
		return pp.popKwd();
	}
	
	private KeywordTyp topKwd() {
		return pp.topKwd();
	}
	
	private int popVal() {
		return pp.popVal();
	}

	private boolean pushAddr(int rightp) {
		return pp.pushAddr(rightp);
	}
	
	private boolean pushOp(KeywordTyp kwtyp) {
		return pp.pushOp(kwtyp);
	}
	

}
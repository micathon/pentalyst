package runtm;

import iconst.IConst;
import iconst.KeywordTyp;
import iconst.NodeCellTyp;
import iconst.RunConst;
import page.Node;
import page.Store;
import config.Config;

public class RunUTime implements IConst, RunConst {

	private Store store;
	private Config cfg;
	private boolean isSkippedCase;
	private boolean isSwitch;

	public RunUTime(Store store, Config cfg) {
		this.store = store;
		this.cfg = cfg;
		isSkippedCase = false;
		isSwitch = false;
	}
	
	public void oprn(String msg) {  
		System.out.println(msg);
	}
	
	public void z0200(int errno, KeywordTyp kwtyp, int rtnval) {
		Node node;
		
		if (!cfg.isRMainMod() || (errno != cfg.getSrcErrNo())) {
			return;
		}
		switch (kwtyp) {
		case ADD:
		case MPY:
		case XOR:
		case ANDBITZ:
		case ORBITZ:
		case XORBITZ:
		case MINUS:
		case DIV:
		case IDIV:
		case MOD:
		case SHL:
		case SHR:
		case SHRU:
		case EQ:
		case NE:
		case LT:
		case LE:
		case GE:
		case GT:
		case NOT:
		case NOTBITZ:
		case AND:
		case OR:
			break; // case count = 23 = UTPUSHXCOUNT
		default:
			return;
		}
		if (rtnval <= 0) {
			cfg.setUtErr(true);
			return;
		}
		node = store.getNode(rtnval);
		if (!chkNode(node)) {
			cfg.setUtErr(true);
			return;
		}
		cfg.incKwdCount();
	}
	
	private boolean chkNode(Node node) {
		NodeCellTyp celltyp = node.getDownCellTyp();
		if (celltyp != NodeCellTyp.LOCVAR) {
			return false;
		}
		return true;
	}
	
	public void z0210(int errno, Node node) {
		if (!cfg.isRMainMod() || (errno != cfg.getSrcErrNo())) {
			return;
		}
		if (!chkNode(node)) {
			return;
		}
		cfg.setUtErr(false);
	}

	public void z0211(int errno, int rightp) {
		if (rightp <= 0) {
			return;
		}
		Node node = store.getNode(rightp);
		z0210(errno, node);
	}
	
	public void z0300(int errno, KeywordTyp kwtyp) {
		int kwval;
		boolean isElseSwitch = false;
		
		if (cfg.getElseSwitch()) {
			isElseSwitch = true;
			kwtyp = KeywordTyp.ELSE;
		}
		kwval = kwtyp.ordinal();
		switch (kwtyp) {
		case IF:
			break;
		case ELIF:
			errno += 10;
			break;
		case ELSE:
			if (isElseSwitch) {
				errno += 60; // switch
			}
			else {
				errno += 20; 
			}
			break;
		case FOR:
			if (isSwitch) {
				errno += 70;
			}
			else {
				errno += 30;
			}
			break;
		case CASE:
			errno += 40;
			if (isSkippedCase) {
				errno += 10;
			}
			break;
		default:
			return;
		}
		cfg.trapROperIntError(errno, kwval);
	}
	
	public void setSkippedCase(boolean flag) {
		isSkippedCase = flag;
	}
	
	public boolean getSkippedCase() {
		return isSkippedCase;
	}

	public void setSwitch(boolean flag) {
		isSwitch = flag;
	}
	
	public boolean getSwitch() {
		return isSwitch;
	}

	public void z0500(int errno, int ival) {
		cfg.trapRCallIntError(errno, ival);
	}
	
}

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

	public RunUTime(Store store, Config cfg) {
		this.store = store;
		this.cfg = cfg;
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
		Node node = store.getNode(rightp);
		z0210(errno, node);
	}

}

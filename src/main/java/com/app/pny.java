package com.app;

import iconst.IConst;
import init.InitMain;
import config.Config;

public class pny implements IConst {

	public static void main(String[] args) {
		InitMain initobj;
		Config cfg = null;
		String fileName = "";
		String filePath = "";
		String fileMidPath;
		String sep = "" + SEPCH;
		String arg;
		String sepWord = "";
		int idx;
		boolean argExists = true;
		boolean isUnitTest = false;
		boolean isMain = false;
		boolean isRunTest = false;

		arg = args[0];
		if (arg.length() == 0) {
			argExists = false;
			cfg = new Config();
		}
		idx = arg.indexOf(sep);
		if (idx >= 0) {
			sepWord = arg.substring(idx);
			fileName = arg.substring(0, idx);
		}
		else if (argExists) {
			fileName = arg;
		}
		if (argExists) {
			// process config block
			filePath = System.getenv("PNY_HOME");
			cfg = new Config(filePath, fileName, sepWord);
			if (!cfg.isValid()) {
				System.out.println(
					"Argument entered in command line is invalid."
				);
				argExists = false;
			}
		}
		if (!argExists) {
			// display help info...
			cfg.displayHelpInfo();
			return;
		}
		// config block has been executed
		isMain = fileName.equals("main");
		isUnitTest = cfg.isUnitTest();
		isRunTest = cfg.isRunTest();
		if (isUnitTest) {
			fileMidPath = "/dat/test/";
			fileName = args[0] + ".test";
		}
		else if (isRunTest) {
			fileMidPath = "/dat/rt/";
			fileName = args[0] + ".test";
		}
		else {
			fileMidPath = "/dat/";
			fileName = args[0] + ".pny";
		}
		initobj = new InitMain(cfg);
		filePath = filePath + fileMidPath;
		initobj.runInit(filePath, fileName, isUnitTest, isMain, isRunTest);
	}
	
}

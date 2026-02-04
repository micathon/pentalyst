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
		String fileMidPath = "";
		String pnyHomeFileName;
		String sep = "" + SEPCH;
		String arg = "";
		String sepWord = "";
		String outFileName = "/out.txt";
		String outFullFileName;
		int idx;
		boolean argExists = true;
		boolean isUnitTest = false;
		boolean isMain = false;
		boolean isRunTest = false;
		boolean isCmdLoop;

		pnyHomeFileName = System.getenv("PNY_HOME");
		outFullFileName = pnyHomeFileName + outFileName; 
		if (args.length == 0) {
			argExists = false;
			cfg = new Config();
		}
		else {
			arg = args[0];
		}
		if (argExists) {
			idx = arg.indexOf(sep);
			omsg("main: idx = " + idx);
			if (idx >= 0) {
				sepWord = arg.substring(idx);
				fileName = arg.substring(0, idx);
			}
			else {
				fileName = arg;
			}
			omsg("main: fileName = " + fileName);
			// process config block
			filePath = pnyHomeFileName;
			cfg = new Config(filePath, fileName, sepWord, outFullFileName);
			if (!cfg.isValid()) {
				System.out.println(
					"Argument entered in command line is invalid."
				);
				System.out.println("");
				argExists = false;
			}
		}
		if (!argExists) {
			// display help info:
			cfg = new Config(outFullFileName);
			cfg.displayHelp();
			return;
		}
		// config block has been executed
		isMain = fileName.equals("main");
		isUnitTest = cfg.isUnitTest();
		isRunTest = cfg.isRunTest();
		isCmdLoop = cfg.isCmdPrompt();
		if (isUnitTest) {
			fileMidPath = "/dat/test/";
			fileName += ".test";
		}
		else if (isRunTest) {
			fileMidPath = "/dat/rt/";
			fileName += ".test";
		}
		else if (isCmdLoop) {
			fileName = "";
		}
		else {
			fileMidPath = "/dat/";
			fileName += ".pny";
		}
		initobj = new InitMain(cfg);
		filePath = filePath + fileMidPath;
		initobj.runInit(filePath, fileName, isUnitTest, isMain, isRunTest);
	}
	
	public static void omsg(String msg) {  
		if (idebug == 1) {
			System.out.println(msg);
		}
	}
	
}

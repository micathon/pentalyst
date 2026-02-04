package config;

import iconst.IConst;
import java.io.File;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class Config implements IConst {

	private boolean debug = false;
	private boolean fileout = false;
	private boolean valid = false;
	private char sepch = SEPCH;
	private boolean unchanged = false;
	private boolean isDirty = false;
	private boolean unitTestFlag = false;
	private boolean runTestFlag = false;
	private boolean doCmdPrompt = false;
	private String configFileName = "/config.txt";
	private String configFullFileName;
	private String outFileName;
	private String omsgbuf = "";
	private char dchar = 'd';
	private char fchar = 'f';
	private char uchar = 'u';
	private char rchar = 'r';
	private char xchar = 'x';
	private char pchar = 'p';

	public Config(String filePath, String fileName, 
		String sepWord, String outFileName) 
	{
		Path fPath; 

		omsg("Config: top");
		this.outFileName = outFileName;
		configFullFileName = filePath + configFileName;
		fPath = Path.of(configFullFileName);
		sepWord = configRtn(sepWord);
		if (!valid) {
			return;
		}
		if (doCmdPrompt) {
			return;
		}
		if (!isDirty) {
			return;
		}
		// save sepWord to text file:
		try {
			Files.writeString(fPath, sepWord);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String configRtn(String sepWord) {
		char ch;
		int dpos;
		int fpos;
		int upos;
		int rpos;
		int switchCount = 0;
		
		if (sepWord == "") {
			omsg("configRtn: sepWord = null string");
			sepWord = getSepWord();
		}
		omsg("configRtn: sepWord = " + sepWord);
		ch = sepWord.charAt(0);
		if (ch != sepch) {
			omsg("invalid: no dot at front of sepWord");
			return "";
		}
		sepWord = sepWord.substring(1);
		if (sepWord.length() == 0) {
			useDefaults();
			valid = true;
			return "";
		}
		ch = sepWord.charAt(0);
		if (sepWord.length() > 1) { }
		else if (ch == xchar) {
			sepWord = cancelUnitTest();
			return sepWord;
		}
		else if (ch == pchar) {
			displayCmdPrompt();
			return "";  // must detect 'p' in calling routine
		}
		dpos = sepWord.indexOf("" + dchar);
		fpos = sepWord.indexOf("" + fchar);
		upos = sepWord.indexOf("" + uchar);
		rpos = sepWord.indexOf("" + rchar);
		if ((upos >= 0) && (rpos >= 0)) {
			omsg("invalid: .ur found");
			return "";
		}
		if (sepWord.length() > 3) {
			omsg("invalid: arg too long");
			return "";
		}
		switchCount += (dpos >= 0) ? 1 : 0; 
		switchCount += (fpos >= 0) ? 1 : 0; 
		switchCount += (upos >= 0) ? 1 : 0; 
		switchCount += (rpos >= 0) ? 1 : 0;
		if (switchCount != sepWord.length()) {
			omsg("invalid: bad switches (dups, bad chars.)");
			return "";
		}
		if (dpos >= 0) {
			debug = true;
		}
		if (fpos >= 0) {
			fileout = true;
			omsg("configRtn: fpos >= 0");
			setFileOut();
		}
		if (upos >= 0) {
			unitTestFlag = true;
		}
		if (rpos >= 0) {
			runTestFlag = true;
		}
		isDirty = true;
		valid = true;
		return sepWord;
	}
	
	public Config() {
		// for 3rd constructor, scroll down to bottom
		useDefaults();
	}
	
	private void useDefaults() {
		// use defaults
		isDirty = true;
	}
	
	private String getSepWord() {
		// read sepword from text file
		// prepend sepch
		String sepWord;

		sepWord = getRawSepWord();  // get from text file
		return "" + sepch + sepWord;
	}
	
	private String getRawSepWord() {
		// read sepword from text file:
		String sepWord;
		Path fPath; 

		fPath = Paths.get(configFullFileName);
        try {
            sepWord = Files.readString(fPath, StandardCharsets.UTF_8);
        } catch (IOException e) {
        	sepWord = "";
        }
		return sepWord;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public boolean isDebug() {
		return debug;
	}
	
	public boolean isFileOut() {
		return fileout;
	}
	
	public boolean isUnitTest() {
		return unitTestFlag;
	}
	
	public boolean isRunTest() {
		return runTestFlag;
	}
	
	private String cancelUnitTest() {
		// must return d/f sepWord, no u/r
		String sepWord;
		int dpos;
		int fpos;

		sepWord = getRawSepWord();
		dpos = sepWord.indexOf("" + dchar);
		fpos = sepWord.indexOf("" + fchar);
		sepWord = "";
		if (dpos >= 0) {
			debug = true;
			sepWord += dchar;
		}
		if (fpos >= 0) {
			fileout = true;
			sepWord += fchar;
		}
		isDirty = true;
		return sepWord;  
	}
	
	private void displayCmdPrompt() {
		doCmdPrompt = true;
		valid = true;
	}

	public boolean isCmdPrompt() {
		return doCmdPrompt;
	}
	
	public void omsg(String msg) {  
		if (idebug == 1) {
			omsgbuf += msg;
			System.out.println(omsgbuf);
			omsgbuf = "";
		}
	}
	
	public void omsgz(String msg) {  
		if (idebug == 1) {
			omsgbuf += msg;
		}
	}
	
	public Config(String outFileName) {
		// display help info
		this.outFileName = outFileName;
		omsg("invalid: Config(s)");
	}

	public void setFileOut() {
        Path fPath;
		PrintStream originalOut = System.out;
		PrintStream fileOut;
		boolean success = true;

		omsg("setFileOut: outFileName = " + outFileName);
		fPath = Paths.get(outFileName);
		try {
            Files.createFile(fPath);
		}
		catch (java.nio.file.FileAlreadyExistsException e) {
			//
		}
		catch (IOException e) {
			e.printStackTrace();
			success = false;
		}
		if (!success) {
			return;
		}
		try {
			fileOut = new PrintStream(new File(outFileName));
			System.setOut(fileOut);
		} 
		catch (IOException e) {
			System.setOut(originalOut);
			e.printStackTrace();
		}
	}
	
	public void displayHelp() {
        PrintStream originalOut = System.out; // Save original System.out

        displayHelpSummary();
		System.out.println("Open out.txt for more info");
        try {
            // Create a new PrintStream to write to a file named "out.txt"
            // ?? Setting the autoFlush to true is important, or the file might be empty.
            PrintStream fileOut = new PrintStream(new File(outFileName));
            
            // Redirect standard output to the file
            System.setOut(fileOut);

            // All subsequent calls to System.out.println() will go to the file
    		displayHelpSummary();
    		displayHelpDetails();

            // You can optionally redirect System.err as well
            // System.setErr(fileOut);
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            // Best practice: ensure the output stream is flushed and closed when done
            if (System.out != originalOut) {
                System.out.flush();
                System.setOut(originalOut); // Restore original System.out
            }
        }
	}
	
	private void displayHelpSummary() {
		System.out.println("Usage:");
		System.out.println("mvn compile exec:java [arg]");
		System.out.println("");
		System.out.println("arg:");
		System.out.println("\"-Dexec.args=pny-cmd\"");
		System.out.println("");
		System.out.println("pny-cmd:");
		System.out.println("[file name w/o ext][.switches]");
		System.out.println("");
		System.out.println("switches:");
		System.out.println("d, f, u, r, x, p");
		System.out.println("");
	}

	private void displayHelpDetails() {
		System.out.println("switches, example:");
		System.out.println(".dfu");
		System.out.println("");
		System.out.println(".d - debug");
		System.out.println(".f - output to file");
		System.out.println(".u - unit test");
		System.out.println(".r - run-unit test");
		System.out.println(".x - cancel unit test");
		System.out.println(".p - display cmd prompt");
		System.out.println("");
		System.out.println(".u | .r - cannot combine u and r switches");
		System.out.println(".ur - illegal");
		System.out.println(".x - d/f settings unchanged");
		System.out.println(".p - file name is omitted");
		System.out.println("");
		System.out.println("lone switches:");
		System.out.println(".x - no other switches allowed");
		System.out.println(".p - no other switches allowed");
		System.out.println("");
		System.out.println(". all by itself:");
		System.out.println("  - use default settings");
		System.out.println("");
		System.out.println("no switches:");
		System.out.println("  - don't change settings");
		System.out.println("");
		System.out.println("no command line argument:");
		System.out.println("  - display help info");
		System.out.println("");
		System.out.println("pny-cmd file name:");
		System.out.println("  - Pentalyst source file w/o ext");
		System.out.println("");
		System.out.println("defaults:");
		System.out.println(".d - off");
		System.out.println(".f - output to console");
		System.out.println(".u - off");
		System.out.println(".r - off");
	}

}

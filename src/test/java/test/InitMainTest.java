package test;

import org.junit.Test;
import init.InitMain;
import config.Config;

import static org.junit.Assert.*;

public class InitMainTest {

  @Test
  public void testFactorial() {
	  Config cfg = new Config();
	  InitMain ex = new InitMain(cfg);
	  assertEquals(ex.factorial(4), 24);
	  assertEquals(ex.factorial(5), 120);
	  assertEquals(ex.factorial(1), 1);
	  assertEquals(ex.factorial(0), 1);
  }
}

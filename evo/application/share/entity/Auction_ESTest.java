/*
 * This file was automatically generated by EvoSuite
 * Fri Apr 03 21:16:52 GMT 2020
 */

package application.share.entity;

import org.junit.Test;
import static org.junit.Assert.*;
import application.share.entity.Auction;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class Auction_ESTest extends application.share.entity.Auction_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      Auction auction0 = new Auction("<_A4TM3GymO?H]A", (String) null, "application.share.entity.Auction");
      auction0.setPublicId((String) null);
      String string0 = auction0.getPublicId();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      Auction auction0 = new Auction("Ejs", "Ejs", "");
      String string0 = auction0.getName();
      assertEquals("Ejs", string0);
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      Auction auction0 = new Auction("<_A4TM3GymO?H]A", (String) null, "application.share.entity.Auction");
      auction0.setName("");
      String string0 = auction0.getName();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      Auction auction0 = new Auction("<_A4TM3GymO?H]A", (String) null, "application.share.entity.Auction");
      auction0.setKey((String) null);
      String string0 = auction0.getKey();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      Auction auction0 = new Auction("Ejs", "Ejs", "");
      String string0 = auction0.getKey();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      Auction auction0 = new Auction("<_A4TM3GymO?H]A", (String) null, "application.share.entity.Auction");
      String string0 = auction0.getName();
      assertNull(string0);
  }

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      Auction auction0 = new Auction("<_A4TM3GymO?H]A", (String) null, "application.share.entity.Auction");
      String string0 = auction0.getKey();
      assertNotNull(string0);
      assertEquals("application.share.entity.Auction", string0);
  }

  @Test(timeout = 4000)
  public void test7()  throws Throwable  {
      Auction auction0 = new Auction("", "}+ZK", "");
      String string0 = auction0.getPublicId();
      assertEquals("", string0);
  }

  @Test(timeout = 4000)
  public void test8()  throws Throwable  {
      Auction auction0 = new Auction("", "}+ZK", "");
      auction0.setPublicId("}+ZK");
      String string0 = auction0.getPublicId();
      assertEquals("}+ZK", string0);
  }
}

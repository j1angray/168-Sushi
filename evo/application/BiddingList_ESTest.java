/*
 * This file was automatically generated by EvoSuite
 * Fri Apr 03 21:47:22 GMT 2020
 */

package application;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import application.BiddingList;
import application.share.entity.Lot;
import javafx.beans.property.IntegerProperty;
import javafx.stage.Stage;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class BiddingList_ESTest extends BiddingList_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      BiddingList biddingList0 = new BiddingList();
      Integer integer0 = new Integer((-732));
      biddingList0.setIndex(integer0);
      double double0 = biddingList0.getIndex();
      assertEquals((-732.0), double0, 0.01);
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      BiddingList biddingList0 = new BiddingList();
      // Undeclared exception!
      try { 
        biddingList0.setIndex((Integer) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("application.BiddingList", e);
      }
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      // Undeclared exception!
      try { 
        BiddingList.isNumeric((String) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("application.BiddingList", e);
      }
  }

  @Test(timeout = 4000)
  public void test3()  throws Throwable  {
      BiddingList biddingList0 = null;
      try {
        biddingList0 = new BiddingList((Stage) null, (Lot) null);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("application.BiddingList", e);
      }
  }

  @Test(timeout = 4000)
  public void test4()  throws Throwable  {
      boolean boolean0 = BiddingList.isNumeric("5{");
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test5()  throws Throwable  {
      boolean boolean0 = BiddingList.isNumeric("");
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test6()  throws Throwable  {
      BiddingList biddingList0 = new BiddingList();
      Integer integer0 = new Integer(1);
      biddingList0.setIndex(integer0);
      double double0 = biddingList0.getIndex();
      assertEquals(1.0, double0, 0.01);
  }

  @Test(timeout = 4000)
  public void test7()  throws Throwable  {
      BiddingList biddingList0 = new BiddingList();
      double double0 = biddingList0.getIndex();
      assertEquals(0.0, double0, 0.01);
  }

  @Test(timeout = 4000)
  public void test8()  throws Throwable  {
      BiddingList biddingList0 = new BiddingList();
      IntegerProperty integerProperty0 = biddingList0.indexProperty();
      assertNotNull(integerProperty0);
  }
}

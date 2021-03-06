/*
 * This file was automatically generated by EvoSuite
 * Fri Apr 03 21:50:42 GMT 2020
 */

package application;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import application.BackBankController;
import java.io.StringReader;
import java.net.URL;
import java.util.PropertyResourceBundle;
import javafx.event.ActionEvent;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.evosuite.runtime.mock.java.net.MockURL;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true, useJEE = true) 
public class BackBankController_ESTest extends BackBankController_ESTest_scaffolding {

  @Test(timeout = 4000)
  public void test0()  throws Throwable  {
      BackBankController backBankController0 = new BackBankController();
      ActionEvent actionEvent0 = new ActionEvent();
      // Undeclared exception!
      try { 
        backBankController0.backBank(actionEvent0);
        fail("Expecting exception: NullPointerException");
      
      } catch(NullPointerException e) {
         //
         // no message in exception (getMessage() returned null)
         //
         verifyException("application.BackBankController", e);
      }
  }

  @Test(timeout = 4000)
  public void test1()  throws Throwable  {
      BackBankController backBankController0 = new BackBankController();
      URL uRL0 = MockURL.getFileExample();
      StringReader stringReader0 = new StringReader("M|~$mX}`dmZ7|@rb@m");
      PropertyResourceBundle propertyResourceBundle0 = new PropertyResourceBundle(stringReader0);
      backBankController0.initialize(uRL0, propertyResourceBundle0);
      assertEquals("some", uRL0.getAuthority());
  }

  @Test(timeout = 4000)
  public void test2()  throws Throwable  {
      BackBankController backBankController0 = new BackBankController();
      ActionEvent actionEvent0 = new ActionEvent();
      // Undeclared exception!
      try { 
        backBankController0.returnIndex(actionEvent0);
        fail("Expecting exception: NoClassDefFoundError");
      
      } catch(NoClassDefFoundError e) {
         //
         // Could not initialize class javafx.scene.layout.BorderPane
         //
         verifyException("application.share.Utils", e);
      }
  }
}

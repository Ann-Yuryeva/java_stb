package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.*;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.appmanager.SoapHelper;

import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Arrays;

public class TestBase {

  private static final String STATUS_RESOLVED = "resolved";
  private static final String STATUS_CLOSED = "closed";

  Logger logger = LoggerFactory.getLogger(TestBase.class);
  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  public boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    MantisConnectPortType mc = SoapHelper.getMantisConnect();
    IssueData issueStatusById = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));
    String issueStatus = app.soap().getIssuesStatus(issueId);
    if ((STATUS_RESOLVED.equals(issueStatusById.getStatus().getName())) || (STATUS_CLOSED.equals(issueStatusById.getStatus().getName()))) {
      return false;
    }
    return true;
  }

  public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
//    app.ftp().upload(new File("src/test/resources/config_inc.php"),"config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
//    app.ftp().restore("config_inс.php.bak", "config_inc.php");
    app.stop();
  }

  @BeforeMethod(alwaysRun = true)
  public void logTestStart(Method m, Object[] p) {
    logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m, Object[] p) {
    logger.info("Stop test " + m.getName() + " with parameters " + Arrays.asList(p));
  }
}

/*
 * Lab7App.java
 */
package lab7;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

import java.sql.*;

/**
 * The main class of the application.
 */
public class Lab7App extends SingleFrameApplication
{
  /**
   * At startup create and show the main frame of the application.
   */
  @Override
  protected void startup()
  {
    InnDB db = new InnDB();
    Connection c = db.getConnection();
    show(new Lab7View(this));
  }

  /**
   * This method is to initialize the specified window by injecting resources.
   * Windows shown in our application come fully initialized from the GUI
   * builder, so this additional configuration is not needed.
   */
  @Override
  protected void configureWindow(java.awt.Window root)
  {
  }

  /**
   * A convenient static getter for the application instance.
   * @return the instance of Lab7App
   */
  public static Lab7App getApplication()
  {
    return Application.getInstance(Lab7App.class);
  }

  /**
   * Main method launching the application.
   */
  public static void main(String[] args)
  {
    launch(Lab7App.class, args);
  }
}

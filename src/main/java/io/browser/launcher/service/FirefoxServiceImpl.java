package io.browser.launcher.service;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FirefoxServiceImpl implements FirefoxService{
  private static final Logger LOGGER = Logger.getLogger(FirefoxServiceImpl.class.getName());
  public static final String firefoxapp = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
  @Override
  public boolean start(String url) {
    try {
      ProcessBuilder p = new ProcessBuilder();
      p.command(firefoxapp, url);
      p.start();
      return true;
    }catch (Exception ex){
      LOGGER.log(Level.SEVERE,"unable execution start firefox url {0}",url);
      //TODO: send error to user
      return false;
    }
  }

  @Override
  public boolean stop() {
    try {
      ProcessBuilder p = new ProcessBuilder();
      p.command("taskkill", "/IM","firefox.exe");
      p.start();
      return true;
    }catch (Exception ex){
      LOGGER.log(Level.SEVERE,"unable kill firefox browser",ex);
      return false;
    }
  }
}

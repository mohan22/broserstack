package io.browser.launcher.conrtroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.browser.launcher.service.FirefoxService;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/browser")
public class BroswerCrontoller {
  //TODO: Maintain these properties in the properties file
  public static final String chromeApp = "<chrome app path>";

  @Autowired
  FirefoxService firefoxService;

  @RequestMapping(value = "/start/{browser}/{url}", method = RequestMethod.GET)
  public ResponseEntity<String> startBrowser(@PathVariable("browser") String browser,@PathVariable("url") String url) {
    try {
      byte[] decodedBytes = Base64.getDecoder().decode(url);
      String decodedUrl = new String(decodedBytes);
      //TODO: Factory design pattern can be used here instead of if-else
      if(browser.equalsIgnoreCase("firefox")){
        boolean success = firefoxService.start(decodedUrl);
        if(success)
          return new ResponseEntity<>("successfully launched firefox",HttpStatus.OK);
        else
          return new ResponseEntity<>("Request failed",HttpStatus.BAD_REQUEST);

      }
      else if(browser.equalsIgnoreCase("chrome")){

      }
      else{

      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ResponseEntity<>("Request failed",HttpStatus.BAD_REQUEST);
  }

  @RequestMapping(value = "/stop/{browser}", method = RequestMethod.GET)
  public ResponseEntity<String> stop(@PathVariable("browser") String browser) {
    try {
      //TODO: Factory design pattern can be used here instead of if-else
      if(browser.equalsIgnoreCase("firefox")){
        boolean success = firefoxService.stop();
        if(success)
          return new ResponseEntity<>("successfully stopped ",HttpStatus.OK);
        else
          return new ResponseEntity<>("Request failed",HttpStatus.BAD_REQUEST);

      }
      else if(browser.equalsIgnoreCase("chrome")){

      }
      else{

      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new ResponseEntity<>("Request failed",HttpStatus.BAD_REQUEST);
  }
}

package sonia.webapp.projector2.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ServiceController
{
  private final static SimpleDateFormat DATE_TIME = new SimpleDateFormat(
    "yyyy-MM-dd HH:mm:ss");

  private final static Logger LOGGER = LoggerFactory.getLogger(
    ServiceController.class.getName());

  @GetMapping(path = "/service.xml",
              produces = MediaType.TEXT_XML_VALUE)
  public String service(
    @RequestParam(name = "mac", required = false) String mac,
    @RequestParam(name = "model", required = false) String model,
    @RequestParam(name = "cmd", required = false) String cmd, Model data )
  {
    LOGGER.info("mac=" + mac + ", model=" + model + ", cmd=" + cmd );
    
    data.addAttribute( "serverDateTime", DATE_TIME.format(new Date()));
    data.addAttribute( "mac", mac );
    data.addAttribute( "model", model );
    
    return "service.xml";
  }
}

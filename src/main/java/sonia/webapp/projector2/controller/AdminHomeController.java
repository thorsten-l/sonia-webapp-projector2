package sonia.webapp.projector2.controller;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminHomeController
{
  private final static Logger LOGGER = LoggerFactory.getLogger(AdminHomeController.class.getName());

  @GetMapping(path = "/admin/", produces = MediaType.TEXT_PLAIN_VALUE)
  public String service(
    @RequestParam(name = "mac", required = false) String mac,
    @RequestParam(name = "model", required = false) String model,
    @RequestParam(name = "cmd", required = false) String cmd, 
    HttpServletRequest request )
  {
    LOGGER.info("\n\nDONE mac={}, model={}, cmd={}",  mac, model, cmd );
    return "Admin Home";
  }
}

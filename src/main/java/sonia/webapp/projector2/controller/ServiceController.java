package sonia.webapp.projector2.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
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
    @RequestParam(name = "cmd", required = false) String cmd, 
    HttpServletRequest request, Model data )
  {
    LOGGER.info("mac={}, model={}, cmd={}",  mac, model, cmd );
    
    LOGGER.debug( "local addr={}", request.getLocalAddr());
    LOGGER.debug( "local name={}", request.getLocalName());
    LOGGER.debug( "local port={}", request.getLocalPort());
    
    LOGGER.debug( "context path={}", request.getContextPath());
    
    LOGGER.debug( "request uri={}", request.getRequestURI());
    LOGGER.debug( "request url={}", request.getRequestURL().toString());
    LOGGER.debug( "query string={}", request.getQueryString());
    
    LOGGER.debug( "remote addr={}", request.getRemoteAddr());
    LOGGER.debug( "remote host={}", request.getRemoteHost());
    
    
    data.addAttribute( "serverDateTime", DATE_TIME.format(new Date()));
    data.addAttribute( "mac", mac );
    data.addAttribute( "model", model );
    
    return "service.xml";
  }
}

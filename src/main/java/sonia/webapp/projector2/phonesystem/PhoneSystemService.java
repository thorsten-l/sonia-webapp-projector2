package sonia.webapp.projector2.phonesystem;

import java.lang.reflect.Constructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sonia.webapp.projector2.configuration.Configuration;

/**
 *
 * @author Thorsten Ludewig (t.ludewig@gmail.com)
 */
public class PhoneSystemService {

  private final static Logger LOGGER = LoggerFactory.getLogger(PhoneSystemService.class.getName());

  private final static PhoneSystemService SINGLETON = new PhoneSystemService();

  private PhoneSystemService() {
    try 
    {
      Class phoneSystemTypeClass = Configuration.getActiveConfiguration()
              .getPhoneSystem().getType().getImplementation();
      Constructor<PhoneSystemInterface> constructor = 
              phoneSystemTypeClass.getConstructor();
      service = constructor.newInstance();
    } 
    catch (Exception e) {
      LOGGER.error( "ERROR: Initializing phone system. {}", e );
      System.exit(-1);
    }
  }

  public static PhoneSystemService getInstance() {
    return SINGLETON;
  }
  
  public String getPhoneLineNumber(String id) {
    return service.getPhoneLineNumber(id);
  }
  
  private PhoneSystemInterface service;
}

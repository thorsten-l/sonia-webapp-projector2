package sonia.webapp.projector2.phonesystem;

import java.lang.reflect.Constructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sonia.webapp.projector2.configuration.Configuration;
import sonia.webapp.projector2.configuration.Phone;
import sonia.webapp.projector2.configuration.Room;

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
  
  public RoomPhone getRoomPhone(String id) {
    RoomPhone roomPhone = null;
    Configuration configuration = Configuration.getActiveConfiguration();
    
    String line = service.getPhoneLineNumber(id);
    
searchPhone:
    for( Room r : configuration.getRooms() )
    {
      for( Phone p : r.getPhones())
      {
        if ( p.getLine().equals(line))
        {
          roomPhone = new RoomPhone(r, p);
          break searchPhone;
        }
      }
    }
        
    return roomPhone;
  }
  
  private PhoneSystemInterface service;
}

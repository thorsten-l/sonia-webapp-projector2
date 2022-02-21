package sonia.webapp.projector2.phonesystem;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.lang.reflect.Constructor;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sonia.webapp.projector2.configuration.Configuration;
import sonia.webapp.projector2.configuration.Phone;
import sonia.webapp.projector2.configuration.Room;

/**
 *
 * @author Thorsten Ludewig (t.ludewig@gmail.com)
 */
public class PhoneSystemService
{

  private final static Logger LOGGER = LoggerFactory.getLogger(
    PhoneSystemService.class.getName());

  private final static PhoneSystemService SINGLETON = new PhoneSystemService();

  private PhoneSystemService()
  {
    roomPhoneCache = CacheBuilder.newBuilder().expireAfterWrite(300,
      TimeUnit.SECONDS).
      build(new CacheLoader<String, RoomPhone>()
      {
        @Override
        public RoomPhone load(String id)
        {
          LOGGER.debug("Loading room & phone cache for mac/id = {}", id);

          RoomPhone roomPhone = null;
          Configuration configuration = Configuration.getActiveConfiguration();

          String line = service.getPhoneLineNumber(id);

          searchPhone:
          for (Room r : configuration.getRooms())
          {
            for (Phone p : r.getPhones())
            {
              if (p.getLine().equals(line))
              {
                roomPhone = new RoomPhone(r, p);
                break searchPhone;
              }
            }
          }

          return roomPhone;
        }
      });

    try
    {
      Class phoneSystemTypeClass = Configuration.getActiveConfiguration()
        .getPhoneSystem().getType().getImplementation();
      Constructor<PhoneSystemInterface> constructor
        = phoneSystemTypeClass.getConstructor();
      service = constructor.newInstance();

    }
    catch (Exception e)
    {
      LOGGER.error("ERROR: Initializing phone system. {}", e);
      System.exit(-1);
    }
  }

  public static PhoneSystemService getInstance()
  {
    return SINGLETON;
  }

  public RoomPhone getRoomPhone(String id)
  {
    RoomPhone roomPhone = null;
    try
    {
      roomPhone = roomPhoneCache.get(id);
    }
    catch (ExecutionException ex)
    {
      LOGGER.error("{}", ex);
    }
    return roomPhone;
  }

  private PhoneSystemInterface service;

  private final LoadingCache<String, RoomPhone> roomPhoneCache;

}

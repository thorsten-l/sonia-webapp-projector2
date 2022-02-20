package sonia.webapp.projector2.configuration;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.helpers.DefaultValidationEventHandler;
import java.io.File;
import java.util.List;
import lombok.Getter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Thorsten Ludewig (t.ludewig@gmail.com)
 */
@ToString
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Configuration
{
  private final static Logger LOGGER = LoggerFactory.getLogger(
    Configuration.class.getName());

  private final static String CONFIGURATION_FILE = "configuration.xml";

  private static Configuration activeConfiguration = new Configuration();

  public Configuration()
  {
    timestamp = System.currentTimeMillis();
    initialized = false;
  }

  private void load(File configurationFile) throws Exception
  {

    // Configuration c = JAXB.unmarshal(configurationFile, Configuration.class);
    JAXBContext context = JAXBContext.newInstance(Configuration.class);
    Unmarshaller unmarshaller = context.createUnmarshaller();
    unmarshaller.setEventHandler(new DefaultValidationEventHandler());
    Configuration c = (Configuration) unmarshaller.unmarshal(configurationFile);

    activeConfiguration = c;
    activeConfiguration.initialized = true;
  }

  private synchronized void check() throws Exception
  {
    double checkStart = System.currentTimeMillis();
    File configurationFile = new File(CONFIGURATION_FILE);

    if (activeConfiguration.initialized == false)
    {
      LOGGER.info("Loading configuration");
      load(configurationFile);
    }
    else
    {
      LOGGER.debug("Check for update configuration");

      if (activeConfiguration.timestamp < configurationFile.lastModified())
      {
        LOGGER.debug("Updating configuration");
        load(configurationFile);
      }
    }

    double checkEnd = System.currentTimeMillis();

    System.out.printf("%.3f\n", (checkEnd - checkStart) / 1000.0);

    // System.out.println(activeConfiguration.toString().replaceAll(", ", "\n"));
  }

  public static Configuration getActiveConfiguration() throws
    Exception
  {
    activeConfiguration.check();
    return activeConfiguration;
  }

  @XmlTransient
  @Getter
  private final long timestamp;

  @XmlTransient
  @Getter
  private boolean initialized;

  @XmlAttribute
  @Getter
  private String version;

  @Getter
  private String description;

  @Getter
  private PhoneSystem phoneSystem;

  @Getter
  @XmlElement(name = "room")
  private List<Room> rooms;
}

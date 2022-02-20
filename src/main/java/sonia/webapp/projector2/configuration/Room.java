package sonia.webapp.projector2.configuration;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author Thorsten Ludewig (t.ludewig@gmail.com)
 */
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class Room
{
  @XmlAttribute
  @Getter
  private String name;

  @Getter
  private String description;

  @Getter
  @XmlElement(name = "phone")
  private List<Phone> phones;

  @Getter
  @XmlElement(name = "device")
  private List<Device> devices;
  
  @Getter
  @XmlElement(name = "action")
  private List<Action> actions;
}

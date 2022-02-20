package sonia.webapp.projector2.configuration;

import sonia.webapp.projector2.configuration.types.DeviceType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author Thorsten Ludewig (t.ludewig@gmail.com)
 */
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class Device
{
  @XmlAttribute
  @Getter
  private String name;

  @XmlAttribute
  @XmlJavaTypeAdapter(sonia.webapp.projector2.configuration.adapter.XmlDeviceTypeAdapter.class)
  @Getter
  private DeviceType type;

  @XmlAttribute
  @Getter
  private String ipAddress;

  @Getter
  private String description;

  @Getter
  @XmlElement(name = "input")
  private List<InputChannel> inputChannels;

  @Getter
  @XmlElement(name = "output")
  private OutputChannel outputChannel;

  @Getter
  @XmlElement(name = "device")
  private List<Device> devices;
}

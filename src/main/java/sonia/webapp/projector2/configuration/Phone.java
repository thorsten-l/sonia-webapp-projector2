package sonia.webapp.projector2.configuration;

import sonia.webapp.projector2.configuration.types.PhoneType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author Thorsten Ludewig (t.ludewig@gmail.com)
 */
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class Phone
{
  @XmlAttribute
  @Getter
  private String line;

  @XmlAttribute
  @XmlJavaTypeAdapter(sonia.webapp.projector2.configuration.adapter.XmlPhoneTypeAdapter.class)
  @Getter
  private PhoneType type;

  @Getter
  private String description;
}

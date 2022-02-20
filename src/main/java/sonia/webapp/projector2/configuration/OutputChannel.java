package sonia.webapp.projector2.configuration;

import sonia.webapp.projector2.configuration.types.ChannelType;
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
public class OutputChannel
{
  @XmlAttribute
  @XmlJavaTypeAdapter(sonia.webapp.projector2.configuration.adapter.XmlChannelTypeAdapter.class)
  @Getter
  private ChannelType type;

  @XmlAttribute
  @Getter
  private boolean selected;

  @Getter
  private String description;
}

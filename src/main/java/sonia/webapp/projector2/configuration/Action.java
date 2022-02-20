package sonia.webapp.projector2.configuration;

import sonia.webapp.projector2.configuration.types.ActionType;
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
public class Action
{
  @XmlAttribute
  @Getter
  private String name;

  @XmlAttribute
  @XmlJavaTypeAdapter(sonia.webapp.projector2.configuration.adapter.XmlActionTypeAdapter.class)
  @Getter
  private ActionType type;

  @Getter
  private String description;
}

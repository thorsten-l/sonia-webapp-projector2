package sonia.webapp.projector2.configuration;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.ToString;

/**
 *
 * @author Thorsten Ludewig (t.ludewig@gmail.com)
 */
@ToString
@XmlAccessorType(XmlAccessType.FIELD)
public class PhoneSystem
{
  @Getter
  private String apiUrl;

  @Getter
  private String apiUser;

  @Getter
  @XmlJavaTypeAdapter(sonia.webapp.projector2.crypto.XmlPasswordAdapter.class)
  private String apiPassword;  
}

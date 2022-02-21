package sonia.webapp.projector2.configuration.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import sonia.webapp.projector2.configuration.types.PhoneSystemType;

/**
 *
 * @author Thorsten Ludewig (t.ludewig@gmail.com)
 */
public class XmlPhoneSystemTypeAdapter extends XmlAdapter<String, PhoneSystemType>
{
  @Override
  public PhoneSystemType unmarshal(String value) throws Exception
  {
    return PhoneSystemType.valueOf(value);
  }

  @Override
  public String marshal(PhoneSystemType phoneSystemType) throws Exception
  {
    return phoneSystemType.name();
  }
}

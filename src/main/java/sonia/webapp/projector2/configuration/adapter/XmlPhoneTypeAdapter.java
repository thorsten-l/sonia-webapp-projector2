package sonia.webapp.projector2.configuration.adapter;

import sonia.webapp.projector2.configuration.types.PhoneType;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Thorsten Ludewig (t.ludewig@gmail.com)
 */
public class XmlPhoneTypeAdapter extends XmlAdapter<String, PhoneType>
{
  @Override
  public PhoneType unmarshal(String value) throws Exception
  {
    return PhoneType.valueOf(value);
  }

  @Override
  public String marshal(PhoneType phoneType) throws Exception
  {
    return phoneType.name();
  }
}

package sonia.webapp.projector2.configuration.adapter;

import sonia.webapp.projector2.configuration.types.DeviceType;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Thorsten Ludewig (t.ludewig@gmail.com)
 */
public class XmlDeviceTypeAdapter extends XmlAdapter<String, DeviceType>
{
  @Override
  public DeviceType unmarshal(String value) throws Exception
  {
    return DeviceType.valueOf(value);
  }

  @Override
  public String marshal(DeviceType deviceType) throws Exception
  {
    return deviceType.name();
  }
}

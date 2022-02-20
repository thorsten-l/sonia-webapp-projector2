package sonia.webapp.projector2.configuration.adapter;

import sonia.webapp.projector2.configuration.types.ChannelType;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Thorsten Ludewig (t.ludewig@gmail.com)
 */
public class XmlChannelTypeAdapter extends XmlAdapter<String, ChannelType>
{
  @Override
  public ChannelType unmarshal(String value) throws Exception
  {
    return ChannelType.valueOf(value);
  }

  @Override
  public String marshal(ChannelType channelType) throws Exception
  {
    return channelType.name();
  }
}

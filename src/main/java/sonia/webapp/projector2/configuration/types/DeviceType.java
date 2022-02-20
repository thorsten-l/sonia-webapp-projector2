package sonia.webapp.projector2.configuration.types;

import sonia.webapp.projector2.configuration.Device;
import lombok.Getter;

/**
 *
 * @author Thorsten Ludewig (t.ludewig@gmail.com)
 */
public enum DeviceType
{
  epson(Device.class), //TODO: implemtation Class missing
  ute(Device.class);
  
  @Getter
  private final Class implementation;
  
  private DeviceType( Class implementation )
  {
    this.implementation = implementation;
  }
}

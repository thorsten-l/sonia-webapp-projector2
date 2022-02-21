package sonia.webapp.projector2.configuration.types;

import lombok.Getter;
import sonia.webapp.projector2.phonesystem.UcWare;

/**
 *
 * @author Thorsten Ludewig (t.ludewig@gmail.com)
 */
public enum PhoneSystemType {
  ucware(UcWare.class);

  @Getter
  private final Class implementation;

  private PhoneSystemType(Class implementation) {
    this.implementation = implementation;
  }
}

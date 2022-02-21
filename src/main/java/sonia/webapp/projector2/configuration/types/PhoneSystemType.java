package sonia.webapp.projector2.configuration.types;

import lombok.Getter;

/**
 *
 * @author Thorsten Ludewig (t.ludewig@gmail.com)
 */
public enum PhoneSystemType {
  ucware(PhoneSystemType.class),
  cisco(PhoneSystemType.class);

  @Getter
  private final Class implementation;

  private PhoneSystemType(Class implementation) {
    this.implementation = implementation;
  }
}

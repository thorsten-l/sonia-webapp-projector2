package sonia.webapp.projector2.phonesystem;

import sonia.webapp.projector2.configuration.Phone;

/**
 *
 * @author Thorsten Ludewig (t.ludewig@gmail.com)
 */
public interface PhoneSystemInterface {

  abstract String getPhoneLineNumber(String id);
  
}

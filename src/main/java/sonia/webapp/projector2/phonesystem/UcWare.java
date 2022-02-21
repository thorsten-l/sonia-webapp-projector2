package sonia.webapp.projector2.phonesystem;

/**
 *
 * @author Thorsten Ludewig (t.ludewig@gmail.com)
 */
public class UcWare implements PhoneSystemInterface
{
  @Override
  public String getPhoneLineNumber(String id) {
    return "19000";
  }
}

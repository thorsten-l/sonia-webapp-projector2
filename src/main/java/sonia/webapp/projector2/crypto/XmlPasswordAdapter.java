package sonia.webapp.projector2.crypto;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Thorsten Ludewig (t.ludewig@gmail.com)
 */
public class XmlPasswordAdapter extends XmlAdapter<String, String>
{
  private final AES256 CIPHER = new AES256(AppSecretKey.getSecret());

  @Override
  public String unmarshal(String encryptedText) throws Exception
  {
    return CIPHER.decrypt(encryptedText);
  }

  @Override
  public String marshal(String plainText) throws Exception
  {
    return CIPHER.encrypt(plainText);
  }
}

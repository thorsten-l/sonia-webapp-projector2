package sonia.webapp.projector2.crypto;

import ch.qos.logback.core.PropertyDefinerBase;

/**
 *
 * @author Dr. Thorsten Ludewig (t.ludewig@ostfalia.de)
 */
public class DecryptionPropertyDefiner extends PropertyDefinerBase
{
  private final AES256 CIPHER = new AES256(AppSecretKey.getSecret());

  /*
   * Return decrypted property value.
   *
   * @return decrypted property value
   */
  @Override
  public String getPropertyValue()
  {
    return CIPHER.decrypt(encryptedValue);
  }

  /**
   * Sets encrypted property value.
   *
   *
   * @param encryptedValue encrypted property
   */
  public void setEncryptedValue(String encryptedValue)
  {
    this.encryptedValue = encryptedValue;
  }

  //~--- fields ---------------------------------------------------------------
  /**
   * encrypted property
   */
  private String encryptedValue;

}

package sonia.webapp.projector2;

//~--- non-JDK imports --------------------------------------------------------

import lombok.Getter;
import lombok.Setter;

import org.kohsuke.args4j.Option;

/**
 *
 * @author Dr. Thorsten Ludewig (t.ludewig@ostfalia.de)
 */
public class Options
{

  /**
   * Field description
   */
  @Option(
    name = "--help",
    aliases = "-h",
    usage = "Displays this help"
  )
  @Getter
  @Setter
  private boolean displayHelp = false;

  /**
   * Field description
   */
  @Option(
    name = "--version",
    aliases = "-v",
    usage = "Display programm version"
  )
  @Getter
  @Setter
  private boolean displayVersion = false;

  /**
   * Field description
   */
  @Option(
    name = "--generate",
    aliases = "-g",
    usage = "Generate random password"
  )
  @Getter
  @Setter
  private int generatePasswordLength = 0;
  
  /**
   * Field description
   */
  @Option(
    name = "--encrypt",
    aliases = "-e",
    usage = "Encrypt given password"
  )
  @Getter
  @Setter
  private String password = null;

  /**
   * Field description
   */
  @Option(
    name = "--check",
    aliases = "-c",
    usage = "Check given config file"
  )
  @Getter
  @Setter
  private String checkConfigFile = null;

}

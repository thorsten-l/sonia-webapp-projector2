package sonia.webapp.projector2;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sonia.webapp.projector2.configuration.Configuration;
import sonia.webapp.projector2.crypto.AES256;
import sonia.webapp.projector2.crypto.AppSecretKey;
import sonia.webapp.projector2.crypto.PasswordGenerator;

@SpringBootApplication
public class SoniaWebappProjectorApplication
{
  private final static Logger LOGGER = LoggerFactory.getLogger(
    SoniaWebappProjectorApplication.class.getName());

  private final static Options OPTIONS = new Options();

  public static void main(String[] args) throws Exception
  {
    CmdLineParser parser = new CmdLineParser(OPTIONS);

    try
    {
      parser.parseArgument(args);
    }
    catch (CmdLineException ex)
    {
      LOGGER.error("Command line error\n");
      parser.printUsage(System.out);
      System.exit(-1);
    }

    if (OPTIONS.isDisplayHelp())
    {
      System.out.println("\nUsage: ./projector2.jar [options]\n");
      parser.printUsage(System.out);
      System.exit(0);
    }

    if (OPTIONS.getGeneratePasswordLength() > 0)
    {
      AES256 cipher = new AES256(AppSecretKey.getSecret());
      String password = PasswordGenerator.generate(OPTIONS.
        getGeneratePasswordLength());
      System.out.println("\npassword:  '" + password + "'");
      System.out.println("encrypted: '" + cipher.encrypt(password) + "'\n");
      System.exit(0);
    }

    if (OPTIONS.getPassword() != null)
    {
      AES256 cipher = new AES256(AppSecretKey.getSecret());
      System.out.println("\npassword:  '" + OPTIONS.getPassword() + "'");
      System.out.println("encrypted: '" + cipher.encrypt(OPTIONS.getPassword())
        + "'\n");
      System.exit(0);
    }

    if (OPTIONS.isDisplayVersion())
    {
      BuildProperties buildProperties = BuildProperties.getInstance();
      System.out.println("\nProject name    : " + buildProperties.
        getProjectName());
      System.out.println("Project version : " + buildProperties.
        getProjectVersion() + "\n");
      System.exit(0);
    }

    //---------------------------------------------------------------
    Configuration config = Configuration.getActiveConfiguration();
    if (config.isInitialized())
    {
      SpringApplication.run(SoniaWebappProjectorApplication.class, args);
    }
  }
}

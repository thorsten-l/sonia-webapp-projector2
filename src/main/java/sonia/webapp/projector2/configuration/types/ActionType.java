package sonia.webapp.projector2.configuration.types;

import lombok.Getter;

/**
 *
 * @author Thorsten Ludewig (t.ludewig@gmail.com)
 */
public enum ActionType
{
  all_off(ActionType.class); //TODO: implemtation Class missing
  
  @Getter
  private final Class implementation;
  
  private ActionType( Class implementation )
  {
    this.implementation = implementation;
  }
}

package sonia.webapp.projector2.configuration.adapter;

import sonia.webapp.projector2.configuration.types.ActionType;
import jakarta.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Thorsten Ludewig (t.ludewig@gmail.com)
 */
public class XmlActionTypeAdapter extends XmlAdapter<String, ActionType>
{
  @Override
  public ActionType unmarshal(String value) throws Exception
  {
    return ActionType.valueOf(value);
  }

  @Override
  public String marshal(ActionType actionType) throws Exception
  {
    return actionType.name();
  }
}

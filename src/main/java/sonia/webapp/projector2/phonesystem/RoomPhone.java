package sonia.webapp.projector2.phonesystem;

import lombok.Getter;
import lombok.ToString;
import sonia.webapp.projector2.configuration.Phone;
import sonia.webapp.projector2.configuration.Room;

/**
 *
 * @author Thorsten Ludewig (t.ludewig@gmail.com)
 */
@ToString
public class RoomPhone
{

  public RoomPhone(Room room, Phone phone)
  {
    this.room = room;
    this.phone = phone;
  }

  @Getter
  private final Room room;

  @Getter
  private final Phone phone;
}

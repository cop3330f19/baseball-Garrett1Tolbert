package baseball;
import java.util.*;
import javax.xml.bind.annotation.XmlElement;

public class MLB {
  @XmlElement(name="player")
  //stores MLB
  private List<Player> players = new ArrayList<>();

  // returns the List<Player>
  public List<Player> getPlayers() {return players;}
}

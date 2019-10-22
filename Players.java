import java.util.*;
import java.xml.bind.annotation.XmlElement;

public class Players {
  @XmlElement(name="player")
  //stores Players
  private List<Player> players = new ArrayList<>();

  // returns the List<Player>
  public List<Player> getPlayers() {return players;}
}

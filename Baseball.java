import java.util.*;


public class Baseball {

  public void requestTeam() {
    Scanner input = new Scanner(System.in);

    System.out.println("Choose a team: ");
    String team = input.next();

    this.findPlayers(team);
  }

  public void findPlayers(String team) {

  }
}

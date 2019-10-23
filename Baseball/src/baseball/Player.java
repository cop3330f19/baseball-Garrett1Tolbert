package baseball;

public class Player {
  private String name;
  private String team;
  private int atBats;
  private int hits;
  private double avg;

  public Player() {this("","",1,0);}

  public Player(String name, String team, int atBats, int hits) {
    this.name = name;
    this.team = team;
    this.atBats = atBats;
    this.hits = hits;
    this.avg = (float)hits/atBats;
  }

  public void setName(String name) {this.name = name;}
  public void setTeam(String team) {this.team = team;}
  public void setAtBats(int atBats) {this.atBats = atBats;}
  public void setHits(int hits) {this.hits = hits;}
  public String getName() {return this.name;}
  public String getTeam() {return this.team;}
  public int getAtBats() {return this.atBats;}
  public int getHits() {return this.hits;}
  public double getAvg() {return this.avg;}
}

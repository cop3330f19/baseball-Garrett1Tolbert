/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseball;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.xml.bind.JAXB;

/**
 *
 * @author garretttolbert
 */
public class BaseballController implements Initializable {
    @FXML private ListView lvTeams;
    @FXML private TableView tblResult;
    @FXML private TableColumn <String, Player> colPlayer;
    @FXML private TableColumn <String, Player> colBatAvg;

    //ArrayList of Player objects
    private List<Player> players = new ArrayList<>();
    private List<List<Player>> dictionary = new ArrayList<>();
    private List<String> teams = new ArrayList<>();
    private List<Double> teamAvg = new ArrayList<>();
    private MLB thesePlayers;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readXML();
        
        lvTeams.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //Link the column's cell value to the get function of the Player Class.
                //REMEMBER the name in the quotation marks should match the case of the get function
                colPlayer.setCellValueFactory(new PropertyValueFactory<>("Name"));
                colBatAvg.setCellValueFactory(new PropertyValueFactory<>("AtBats"));

                //Create an ObservableList object to store the States object(s)
                ObservableList<Player> result = FXCollections.observableArrayList();
                
//                teams.forEach((team) -> {
//                    System.out.println(team.getTeam());
//                });
                int idx = teams.indexOf(newValue);
                System.out.println(teamAvg.get(idx));
                dictionary.forEach((team) -> {
                    team.forEach(player->{
                        System.out.printf("Avg: %d\n",player.getAvg());
                        
                        if(player.getAvg() > teamAvg.get(idx)) {
                            System.out.println(player.getName());
                            result.add(players.get(idx));                    
                        }
                    });
                });
                
                //while loop to add players whose average is above team's average
//                players.forEach(player -> {
//                    
//                });
//                result.add(players.get(idx));
                
                
                //Bind the list  to the table
                tblResult.setItems(result);                
            }
        });
        
    
    }
    private int findPlayer(String value){
     
        for(int i = 0; i < players.size(); i++)
            if(players.get(i).getTeam().equals(value))
                return i;
        return 0;
    }
    
    private void readXML() {
 
        //ObservableList to add states to the ListView
        ObservableList<String> teamList = FXCollections.observableArrayList();
        
        try(BufferedReader input = Files.newBufferedReader(Paths.get("/Users/garretttolbert/NetBeansProjects/Baseball/src/baseball/baseball.xml"))) {
            // unmarshal the file's contents
            
            thesePlayers = JAXB.unmarshal(input, MLB.class);

            // display contents
//            System.out.printf("%-12s%-12s%-10s%-10s%n","Name","Team","At Bats","Hits");
            

            thesePlayers.getPlayers().forEach((player) -> {
                players.add(player);
                if(!teamList.contains(player.getTeam())) {
                    teamList.add(player.getTeam());
                    teams.add(player.getTeam());
//                    System.out.printf("Team: %s, Avg: %d\n", player.getTeam(),player.getAtBats());
                    dictionary.add(new ArrayList<Player>());
                }
            });
            Collections.sort(teams);

            thesePlayers.getPlayers().forEach((player) -> {
                int idx = teams.indexOf((player.getTeam()));
                dictionary.get(idx).add(player);
            });
            dictionary.forEach((team) -> {
                int count = 0;   
                double totalAvg=0.0;
                for(int j=0;j<team.size();j++) {
                    int hits = team.get(j).getHits();
                    int bats = team.get(j).getAtBats();
                    totalAvg += ((float)team.get(j).getHits() / team.get(j).getAtBats());
//                    System.out.printf("Curr Team: %s, Hits: %d, AtBats: %d\n", team.get(j).getTeam(), team.get(j).getHits(),team.get(j).getAtBats());
                    count += 1;    
                }
                teamAvg.add(totalAvg/count);
            });
//            teamAvg.forEach(avg -> {
//                System.out.printf("%.3f\n",avg);
//            });

        } catch (IOException e) {
            System.err.println("Error opening file. Check file path");
        }
        //Sorts the List of State objects by state name in ascending order
        Collections.sort(players, new PlayerComparator());
        //sorts the ObservableList
        Collections.sort(teamList);
        
        //Binds the ObservableList to the ListView
        lvTeams.setItems(teamList);        
    }    
    
}

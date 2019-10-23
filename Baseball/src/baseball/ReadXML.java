/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseball;

/**
 *
 * @author garretttolbert
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.xml.bind.JAXB;

public class ReadXML {
    public void readFile() {
        try(BufferedReader input = Files.newBufferedReader(Paths.get("baseball.xml"))) {
            // unmarshal the file's contents
            MLB players = JAXB.unmarshal(input, MLB.class);
            
            // display contents
            System.out.printf("%-12s%-12s%-10s%-10s%n","Name","Team","At Bats","Hits");
            
            for(Player player : players.getPlayers()) {
                System.out.printf("%-12s%-12s%-10s%-10s%n", 
                        player.getName(),player.getTeam(),player.getAtBats(),player.getHits());
            }
        } catch (IOException ioException) {
            System.err.println("Error opening file.");
        }
    }
}

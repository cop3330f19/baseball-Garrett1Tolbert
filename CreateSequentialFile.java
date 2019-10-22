import java.io.*;
import java.nio.*;
import java.util.*;
import javax.xml.bind.JAXB;

public class CreateSequentialFile {
  public static void main(String[] args) {

    try (BufferedWriter output = Files.newBufferedWriter(Paths.get("test.xml"))) {
      Scanner input = new Scanner(System.in);

      Players players = new Players();


    } catch (IOException ioException) {
        System.out.println("Error opening file. Terminating.");
    }
  }
}

package GetName;

import java.io.*;
import java.net.URL;
import java.util.Scanner; // Import the Scanner class

public class GetName {

  public static void main(String[] args) throws IOException {

    Scanner userInput = new Scanner(System.in); // Create a Scanner object
    System.out.println("Enter username"); // Prompts user to enter their username
    String userName = userInput.nextLine(); // Read username
    userInput.close(); // Close Scanner
    URL userURL =
        new URL("https://www.ecs.soton.ac.uk/people/" + userName + "#"); // Creates URL to use
    FileWriter webHTML = new FileWriter("WebHTML.txt"); // Opens txt file to dump HTML into
    BufferedReader in =
        new BufferedReader(new InputStreamReader(userURL.openStream())); // Gets HTML from website
    String inputLine;
    while ((inputLine = in.readLine()) != null) { // Loops through all Lines of HTML
      webHTML.write(inputLine); // Reads HTML lines to file
    }

    in.close(); // Closes connection to Website
    webHTML.close(); // Closes File
    File webHTMLOut = new File("WebHTML.txt"); // Opens File to Read
    Scanner myReader = new Scanner(webHTMLOut);
    while (myReader.hasNextLine()) { // Loops through all lines of captured HTML
      String data = myReader.nextLine(); // Read line
      int start = data.indexOf("<title>"); // Get index of "<title>" which comes before the name
      int end = data.indexOf("|"); // Get index of first "|" which comes right after the name
      String name = data.substring(start + 7, end); // Grab the substring which contains the name
      System.out.println(name); // Output the name
    }
  }
}

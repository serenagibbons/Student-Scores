import java.io.*;
import java.util.Scanner;

/**
   The ScoreReader class reads test scores as
   tokens from a file and calculates the average
   of each line of scores.
*/

public class ScoreReader
{
   private Scanner inputFile;
   private String line;
   public String source = "";

   /**
      The constructor opens a file to read
      the grades from.
      @param filename The selected file.
   */
   
   public ScoreReader(File filename) throws IOException {
      inputFile = new Scanner(filename);
   }
   
   /**
      The readNextLine method reads the next line
      from the file.
      @return true if the line was read, false
      otherwise.
   */

   public boolean readNextLine() throws IOException {
      boolean lineRead; // Flag variable
    
      // Determine whether there is more to read.
      lineRead = inputFile.hasNext();

      // If so, read the next line. 
      if (lineRead)
        line = inputFile.nextLine();
       
      return lineRead;
   }
   
   /**
      The readSource method reads the next line
      from the file into the source string.
    */
   
	public void readSource() {
		while(inputFile.hasNext()) {
			source += inputFile.nextLine() +" \n"; 
		}
	}

   /**
      The getAverage method calculates the average
      of the last set of test scores read from the file.
      @return The average.
   */
    
   public double getAverage() {
      double total = 0;	// Accumulator
      double average;   // The average test score

      // Tokenize the last line read from the file.
      String[] tokens = line.split(",");
    
      // Calculate the total of the test scores.
      for (String str : tokens)
      {
    	  total += Double.parseDouble(str);
      }
    
      // Calculate the average of the scores.
      average = total / tokens.length;
    
      // Return the average.
      return average;
   }
   
   /**
      The close method closes the file.
   */
   
   public void close() throws IOException
   {
      inputFile.close();
   }
}
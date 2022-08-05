package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileIO {
	
// The code in this FileIO is repurposed from the following website
// from https://www.w3schools.com/java/java_files_read.asp
	
	
	/** Read text file from the given filePath and return it as a string
	 * @param filePath the path of the text file
	 * @return
	 */
	public String readFile(String filePath) {
		String fileContent = "";
		try {
		      File myObj = new File(filePath);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        fileContent += myReader.nextLine();
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	return fileContent;	
	}
	
	
	/** Write the content in the first string parameter
	 * to the file path fo the second string parameter
	 * @param fileContent write in text file
	 * @param fileName text file location
	 */
	public void writeFile(String fileContent, String fileName) {
		try {
		      File myObj = new File(fileName);
		      if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		      } else ;
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		try {
		      FileWriter myWriter = new FileWriter(fileName);
		      myWriter.write(fileContent);
		      myWriter.close();
		      // System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}

}

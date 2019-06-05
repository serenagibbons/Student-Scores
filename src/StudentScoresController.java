import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class StudentScoresController {
	File selectedFile;

	@FXML
	private TextArea sourceDisplay;		// Text area on source file tab

	@FXML
	private TextArea averageDisplay;	// Text area on average tab

	@FXML
	// Opens a .csv file from computer 
	void openFile(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Spreadsheets", "*.csv"));
		selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {

			double average;         // Test average
			int studentNumber = 1;  // Control variable
			
			// Create ScoreReader object
			ScoreReader scoreReader = new ScoreReader(selectedFile);
			
			// Read in and display source file
			scoreReader.readSource();
			sourceDisplay.setText(scoreReader.source.replaceAll(",", "\t"));
				
			// Close the Scanner object inputFile
			scoreReader.close();
			
			
			// Reconnect the ScoreReader object
			scoreReader =
					new ScoreReader(selectedFile);
			
			while (scoreReader.readNextLine()) {
				
				// Get the average 
				average = scoreReader.getAverage();

				// Display the student's average
				if (studentNumber == 1) {
					averageDisplay.setText("Average for student " +
						studentNumber + " is " +
						average);
				}
				else 
					averageDisplay.setText(averageDisplay.getText() + "\nAverage for student " +
						studentNumber + " is " +
						average);

				// Increment the student number
				studentNumber++;
			}
			
			System.out.println("Student averages calculated.");
			
			// Close the scoreReader
			scoreReader.close();

		}
	}

	@FXML
	// About menu item on Help menu will open dialog box when clicked
	void displayAbout(ActionEvent event) {
		JOptionPane.showMessageDialog(null, "This app was created by Serena Gibbons to read in a file \n"
				+ "of student scores and calculate each students' average.");
	}
	
	@FXML
	// Close the Student Scores application
	void closeApp(ActionEvent event) {
		System.exit(0);
	}


}

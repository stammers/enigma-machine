import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class EnigmaFile extends EnigmaMachine{

	public String readInputString(){
		//reads an input string from a file input.txt
		String inputString = "";
		try{
			FileInputStream fstream = new FileInputStream("input.txt");
			DataInputStream inputStream = new DataInputStream(fstream);
			BufferedReader brIn = new BufferedReader(new InputStreamReader(inputStream));
			String input;
			//keeps reading the input until the next line is empty
			while((input = brIn.readLine()) != null){
				inputString = inputString + input;
			}
			inputStream.close();
		}catch ( FileNotFoundException ioe){
			System.err.println("The file input.txt wasn't found, please create it and try again");
		} catch (IOException e) {
			System.err.println("There was an error reading from the file");
		}
		return inputString;
	}
	
	public void writeOutputString(String outputString){
		//writes the encoded output to a file output.txt, with each string set on a new line
		try{
			FileWriter fstream = new FileWriter("output.txt", true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.newLine();
			out.write(outputString);
			out.close();
		}catch ( IOException ioe){
			System.err.println("There was an error writing to the file");
		}
	}
	
	//completely clears the output file
	public void resetOutputFile(){
		try{
			FileWriter fstream = new FileWriter("output.txt", false);
			BufferedWriter out = new BufferedWriter(fstream);
			out.close();
		}catch ( IOException ioe){
			System.err.println("There was an error writing to the file");
		}
	}
	
	public void encodeString(){
		//reads the input string from the file
		String stringToEncode = readInputString();
		//removes every character which isn't an upper case letter
		stringToEncode = stringToEncode.toUpperCase().replaceAll("[^A-Z]", "");
		String output = "";
		int inputLength = stringToEncode.length();
		char[] inputChar = new char[inputLength];
		//separates the input into characters
		for(int i = 0; i < inputLength; i++){
			inputChar[i] = stringToEncode.charAt(i);
		}
		//encodes each character one at a time
		for(int i = 0; i < inputChar.length; i++){
			output = output + encodeLetter(inputChar[i]);
		}
		//writes the encoded output
		writeOutputString(output);
	}
}
	

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Tester {
	
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private EnigmaFile myMachine = new EnigmaFile();
	
	public static void main(String[] args) {
		Tester myTest = new Tester();
		myTest.messageDean();
	}
	
	//Does test1 from the coursework sheet
	//tests the Basic Rotors and Reflector 1 works
	public void test1(){
		//initially sets up the Enigma Machine
		myMachine.addPlug('A', 'M');
		myMachine.addPlug('G', 'L');
		myMachine.addPlug('E', 'T');
		myMachine.addRotor("I", 0);
		myMachine.setPosition(0, 6);
		myMachine.addRotor("II", 1);
		myMachine.setPosition(1, 12);
		myMachine.addRotor("III", 2);
		myMachine.setPosition(2, 5);
		myMachine.addReflector("ReflectorI");
		System.out.println("Please enter your String to encode/decode");
		//reads the string input by the user
		String inputString = readInput();
		int inputLength = inputString.length();
		char[] inputChar = new char[inputLength];
		for(int i = 0; i < inputLength; i++){
			inputChar[i] = inputString.charAt(i);
		}
		//encodes each letter
		for(int i = 0; i < inputChar.length; i++){
			System.out.println(myMachine.encodeLetter(inputChar[i]));
		}
	}
	
	//does test 2 from the coursework sheet
	//tests the Basic Rotors and Reflector 2 work 
	public void test2(){
		//initially sets up the Enigma Machine
		myMachine.addPlug('B', 'C');
		myMachine.addPlug('R', 'I');
		myMachine.addPlug('S', 'M');
		myMachine.addPlug('A', 'F');
		myMachine.addRotor("IV", 0);
		myMachine.setPosition(0, 23);
		myMachine.addRotor("V", 1);
		myMachine.setPosition(1, 4);
		myMachine.addRotor("II", 2);
		myMachine.setPosition(2, 9);
		myMachine.addReflector("ReflectorII");
		System.out.println("Please enter your String to encode/decode");
		//reads the string input by the user
		String inputString = readInput();
		//makes all the Input Upper Case and removes every character which isn't an upper case letter
		inputString = inputString.toUpperCase().replaceAll("[^A-Z]", "");
		int inputLength = inputString.length();
		char[] inputChar = new char[inputLength];
		for(int i = 0; i < inputLength; i++){
			inputChar[i] = inputString.charAt(i);
		}
		//encodes each letter
		for(int i = 0; i < inputChar.length; i++){
			System.out.println(myMachine.encodeLetter(inputChar[i]));
		}
	}
	
	//does test 3 from the coursework sheet
	//tests the Turnover Rotors work
	public void test3(){
		//initially sets up the Enigma Machine
		myMachine.addPlug('Q', 'F');
		myMachine.addTurnoverRotor("I", 0);
		myMachine.setPosition(0, 23);
		myMachine.addTurnoverRotor("II", 1);
		myMachine.setPosition(1, 11);
		myMachine.addTurnoverRotor("III", 2);
		myMachine.setPosition(2, 7);
		myMachine.addReflector("ReflectorI");
		//sets the rotor in the next slot for slots 0 and 1
		((TurnoverRotor) myMachine.getRotor(0)).setNextRotor(myMachine.getRotor(1));
		((TurnoverRotor) myMachine.getRotor(1)).setNextRotor(myMachine.getRotor(2));
		//reads the input from the user
		System.out.println("Please enter your String to encode/decode");
		String inputString = readInput();
		int inputLength = inputString.length();
		char[] inputChar = new char[inputLength];
		for(int i = 0; i < inputLength; i++){
			inputChar[i] = inputString.charAt(i);
		}
		//encodes each character separately
		for(int i = 0; i < inputChar.length; i++){
			System.out.println(myMachine.encodeLetter(inputChar[i]));
		}
	}
	
	//decodes the message from  the Dean of the Faculty, Professor Dame Wendy Hall
	public void messageDean(){
		//initially sets up the enigma machine
		myMachine.addPlug('W', 'E');
		myMachine.addPlug('N', 'D');
		myMachine.addPlug('Y', 'H');
		myMachine.addPlug('A', 'L');
		myMachine.addRotor("I", 0);
		myMachine.setPosition(0, 25);
		myMachine.addRotor("V", 1);
		myMachine.setPosition(1, 10);
		myMachine.addRotor("III", 2);
		myMachine.setPosition(2, 11);
		myMachine.addReflector("ReflectorI");
		//clears any previous encodings from the output file
		myMachine.resetOutputFile();
		//reads the string from the input file, encodes it, and writes it to the output file
		myMachine.encodeString();
		System.out.println("Done");
	}
	
	//tests reading and writing a message to text files
	public void testEnigmaFile(){
		//initially sets up the Enigma Machine
		myMachine.addPlug('D', 'A');
		myMachine.addPlug('S', 'C');
		myMachine.addRotor("IV", 0);
		myMachine.setPosition(0, 8);
		myMachine.addRotor("III", 1);
		myMachine.setPosition(1, 4);
		myMachine.addTurnoverRotor("II", 2);
		myMachine.setPosition(2, 21);
		myMachine.addReflector("ReflectorI");
		//clears any previous work from the output file
		myMachine.resetOutputFile();
		//reads the string from the input file, encodes it, and writes it to the output file
		myMachine.encodeString();
		System.out.println("Done");
	}
	
	public void testConvert(){
		System.out.println("Enter a String");
		String input = readInput();
		System.out.println(input);
	}
	
	//reads and returns the string input by the user ready to be encoded/decoded
	public String readInput(){
		String inputString = null;
		try {  
			inputString = input.readLine();
		} catch (IOException ioe) {
			System.err.println("There was an input error");
		}
		//makes all the Input Upper Case and removes every character which isn't an upper case letter
		inputString = inputString.toUpperCase().replaceAll("[^A-Z]", "");
		return inputString;
	}
		
	
	

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class CommandLineInterface {
	
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	private EnigmaFile myMachine = new EnigmaFile();
	
	public static void main(String[] args) {
		CommandLineInterface cmd = new CommandLineInterface();
		cmd.cmdInterface();

	}
	public void cmdInterface(){
		myMachine.resetOutputFile();
		boolean continuePlugs = false;
		//gets the user to enter any plugs they would like to create
		//looping until they say no to entering more plugs
		System.out.println("Would you like to add any Plugs?\nEnter Y or N");
		continuePlugs = readYesOrNo();
		while(!continuePlugs){
			char input1 = readPlugCharacterInput("top");
			char input2 = readPlugCharacterInput("bottom");
			myMachine.addPlug(input1, input2);
			System.out.println("Would you like to create another plug?\nEnter Y or N");
			continuePlugs = readYesOrNo();
		}
		boolean continueRotorType = false;
		//gets the user to enter whether they turnover rotors or basic rotors should be used
		//loops until they enter a correct response
		String rotorType = "";
		System.out.println("Would you like a: Turnover Rotors or b: Basic Rotors\nPlease Enter a or b");
		while(!continueRotorType){
			rotorType = readInput();
			if(rotorType.equalsIgnoreCase("a") || rotorType.equalsIgnoreCase("b")){
				continueRotorType = true;
			}else {
				System.out.println("Please only enter a or b");
			}
		}
		//gets the user to input the rotor they would like at slot 0, along with its position
		System.out.println("Which type of rotor would you like at slot 1?");
		String slot1 = readRotorType();
		System.out.println("What position would you like the rotor to start at?");
		int slot1Pos = readRotorPos();
		//creates the rotor at slot 0
		if(rotorType.equalsIgnoreCase("a")){
			myMachine.addTurnoverRotor(slot1, 0);
		}else{
			myMachine.addRotor(slot1, 0);
		}
		myMachine.setPosition(0, slot1Pos);
		Boolean alreadyUsed = true;
		String slot2 = "";
		//gets the user to input the rotor they would like at slot 1, only continuing when it's not one which
		//has already been used
		while(alreadyUsed){
			System.out.println("Which type of rotor would you like at slot 2?");
			slot2 = readRotorType();
			alreadyUsed = canAddRotor(slot2);
			if(alreadyUsed){
				System.out.println("This rotor type has already been used");
			}
		}
		System.out.println("What position would you like the rotor to start at?");
		int slot2Pos = readRotorPos();
		//creates the rotor at slot 1
		if(rotorType.equalsIgnoreCase("a")){
			myMachine.addTurnoverRotor(slot2, 1);
		}else{
			myMachine.addRotor(slot2, 1);
		}
		myMachine.setPosition(1, slot2Pos);
		alreadyUsed = true;
		String slot3 = "";
		//gets the user to input the rotor they would like at slot 2, only continuing when it's not one which
		//has already been used. The position is input as well
		while(alreadyUsed){
			System.out.println("Which type of rotor would you like at slot 3?");
			slot3 = readRotorType();
			alreadyUsed = canAddRotor(slot3);
			if(alreadyUsed){
				System.out.println("This rotor type has already been used");
			}
		}
		System.out.println("What position would you like the rotor to start at?");
		int slot3Pos = readRotorPos();
		//creates the rotor at slot 2
		if(rotorType.equalsIgnoreCase("a")){
			myMachine.addTurnoverRotor(slot3, 2);
		}else{
			myMachine.addRotor(slot3, 2);
		}
		myMachine.setPosition(2, slot3Pos);
		if(rotorType.equalsIgnoreCase("a")){
			//sets the rotor in the next slot for slots 0 and 1
			((TurnoverRotor) myMachine.getRotor(0)).setNextRotor(myMachine.getRotor(1));
			((TurnoverRotor) myMachine.getRotor(1)).setNextRotor(myMachine.getRotor(2));
		}
		//gets the user to input the reflector type they'd like and then creates it
		System.out.println("Which type of reflector would you like?");
		String reflector = readReflectorType();
		myMachine.addReflector(reflector);
		//gives the user the option to have their message read in from a file
		System.out.println("Would you like to enter your message to be read from the file input.txt?");
		System.out.println("Enter Y or N");
		boolean readInput = readYesOrNo();
		String inputString = "";
		if(readInput){
			System.out.println("Please enter your String to encode/decode");
			//reads the string input by the user
			inputString = readInput();
			//makes all the Input Upper Case and removes every character which isn't an upper case letter
			inputString = inputString.toUpperCase().replaceAll("[^A-Z]", "");
		}
		int inputLength = inputString.length();
		char[] inputChar = new char[inputLength];
		//separates the input string into characters
		for(int i = 0; i < inputLength; i++){
			inputChar[i] = inputString.charAt(i);
		}
		//encodes each character seperately
		if(!readInput){
			myMachine.encodeString();
		}else{
			for(int i = 0; i < inputChar.length; i++){
				System.out.println(myMachine.encodeLetter(inputChar[i]));
			}
		}
		System.out.println("Done, your message has been encoded");
	}

	//reads and returns the string input by the user ready to be encoded/decoded
	public String readInput(){
		String inputString = null;
		try {  
			inputString = input.readLine();
		} catch (IOException ioe) {
			System.err.println("There was an input error");
		}
		return inputString;
	}
		
	//reads and returns a boolean value corresponding to the yes/no answer the end user replied
	public boolean readYesOrNo(){
		boolean carryOn = false;
		String addPlugs = null;
		while(!carryOn){
			try {
				addPlugs = input.readLine();
			} catch (IOException ioe) {
				System.err.println("There was an input error");
			}
			addPlugs = addPlugs.toUpperCase();
			if(addPlugs.equals("N") || addPlugs.equals("Y")){
					carryOn = true;
			}else{
				System.out.println("Please enter Y or N");
			}
		}
		if(addPlugs.equals("N") || addPlugs.equals("n")){
			return true;
		} else{
			return false;
		}
	}
		
	//reads and returns a single character 
	public char readPlugCharacterInput(String positionPlug){
		char inputChar = 0;
		String inputString = "";
		boolean carryOn = false;
		while(!carryOn){
			System.out.println("Please enter the character at the " + positionPlug + " end of the plug:");
			try {
				inputString = input.readLine();
			} catch (IOException ioe) {
				System.err.println("There was an input error");
			}	
			//checks that more than one character wasn't entered
			if(inputString.length() != 1){
				System.out.println("Please only Enter one character");
			}
			//converts the character to uppercase and removes all non-letter characters to check its valid as an input
			inputString = inputString + inputChar;
			inputString = inputString.toUpperCase().replaceAll("[^A-Z]", "");
			if(inputString.equals("")){
				System.out.println("Please enter a valid character");
			} else {
				carryOn = true;
			}
		}
		inputChar = inputString.charAt(0);
		return inputChar;
	}
		
	//reads and returns the type of rotor the user specifies if valid
	public String readRotorType(){
		boolean carryOn = false;
		String rotorType = null;
		while(!carryOn){
			try {
				rotorType = input.readLine();
			} catch (IOException ioe) {
				System.err.println("There was an input error");
			}
			rotorType = rotorType.toUpperCase();
			if(rotorType.equals("I") ||rotorType.equals("II") || rotorType.equals("III") || rotorType.equals("IV") ||rotorType.equals("V")){
				carryOn = true;
			} else {
				System.out.println("Please enter a valid rotor type");
				System.out.println("Either I, II, III, IV or V");
			}
		}
		return rotorType;
	}
		
	//reads and returns the rotor position entered by the user of valid
	public int readRotorPos(){
		boolean carryOn = false;
		int rotorPosition = 26;
		String rotorPos = null;
		while(!carryOn){
			try {
				rotorPos = input.readLine();
			} catch (IOException ioe) {
				System.err.println("There was an input error");
			}
			try {
				Integer.parseInt(rotorPos);
			} catch (NumberFormatException e){
				System.err.println("Error" + e.getMessage());
			}
			rotorPosition = Integer.valueOf(rotorPos);
			if(rotorPosition >= 0 && rotorPosition <=25){
				carryOn = true;
			} else {
				System.out.println("Please enter a valid rotor position from 0 to 25");
			}
		}
		return rotorPosition;
	}
		
	//reads and returns the reflector type entered by the user if valid
	public String readReflectorType(){
		boolean carryOn = false;
		String reflectorType = null;
		while(!carryOn){
			try {
				reflectorType = input.readLine();
			} catch (IOException ioe) {
				System.err.println("There was an input error");
			}
			reflectorType = reflectorType.toUpperCase();
			if(reflectorType.equals("REFLECTORI") ||reflectorType.equals("REFLECTORII")){
				carryOn = true;
			} else {
				System.out.println("Please enter a valid reflector type");
				System.out.println("Either ReflectorI or ReflectorII");
			}
		}
		return reflectorType;
	}
		
	//checks if the rotor type is already in use
	public boolean canAddRotor(String type){
		boolean alreadyUsed = false;
		for(int i = 0; i < myMachine.numOfRotors(); i++){
			if (myMachine.getRotor(i).getType().equals(type)){
				alreadyUsed = true;
			}
		}
		return alreadyUsed;
	}
}

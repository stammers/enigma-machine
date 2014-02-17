public class GUIController {
	EnigmaMachine myMachine = new EnigmaMachine();
	
	public String encodeString(String stringToEncode){
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
			output = output + myMachine.encodeLetter(inputChar[i]);
		}
		//writes the encoded output
		return output;
	}
	
	//tests if the string to encode actually has any letters
	public boolean testIfEmpty(String enteredString){
		int size = enteredString.length();
		if(size == 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	//adds the plugs to the Machine
	public void addPlugs(String char1, String char2){
			char input1 = char1.charAt(0);
			char input2 = char2.charAt(0);
			myMachine.addPlug(input1, input2);
	}
	
	//adds the rotors to the machine
	public void addRotor(String rotorType, String rotorNum, int slot, int rotorPos){
		//detects the kind of rotor
		if(rotorType.equalsIgnoreCase("Turnover")){
			myMachine.addTurnoverRotor(rotorNum, slot);
			myMachine.setPosition(slot, rotorPos);
		}
		if(rotorType.equalsIgnoreCase("Basic")){
			myMachine.addRotor(rotorNum, slot);
			myMachine.setPosition(slot, rotorPos);
		}
	}
	
	//sets the next rotor if turnover rotors are being used
	public void setNext(){
		((TurnoverRotor) myMachine.getRotor(0)).setNextRotor(myMachine.getRotor(1));
		((TurnoverRotor) myMachine.getRotor(1)).setNextRotor(myMachine.getRotor(2));
	}
	
	//adds the reflector to the machine
	public void addReflector(String reflectorType){
		myMachine.addReflector(reflectorType);
	}
	
	//reads and returns an int value corresponding to the yes/no answer the end user replied
	public int readYesOrNo(String addPlugs){
		addPlugs = addPlugs.toUpperCase();
		if(addPlugs.equals("N")){
			return 1;
		} else if(addPlugs.equals("Y")){
			return 2;
		} else{
			return 0;
		}
	}	
	
	//reads and returns an int value corresponding to the a/b answer the end user gave
	public int readAOrB(String read){
		read = read.toUpperCase();
		if(read.equals("A")){
			return 1;
		} else if(read.equals("B")){
			return 2;
		} else{
			return 0;
		}
	}	
		
	//reads and returns the type of rotor the user specifies if valid
	public int readRotorType(String rotorType){
		rotorType = rotorType.toUpperCase();
		if(rotorType.equals("I") ||rotorType.equals("II") || rotorType.equals("III") || rotorType.equals("IV") ||rotorType.equals("V")){
			return 1;
		} else {
			return 0;
		}
	}
	
	//returns codes whether the entered position is valid
	public int readRotorPos(String rotorPos){
		int rotorPosition = Integer.valueOf(rotorPos);
		if(rotorPosition >= 0 && rotorPosition <=25){
			return 1;
		} else {
			return 0;
		}
	}
	//returns a code whether the entered reflector type is valid or not
	public int readReflectorType(String reflectorType){
		reflectorType = reflectorType.toUpperCase();
		if(reflectorType.equals("REFLECTORI") ||reflectorType.equals("REFLECTORII")){
			return 1;
		} else {
			return 0;
		}
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
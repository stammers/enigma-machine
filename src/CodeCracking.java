
public class CodeCracking {
	//holds the types of rotor available
	private String[] types = {"I", "II", "III", "IV", "V"};

	public static void main(String[] args){
		CodeCracking myCodeCracker = new CodeCracking();
		myCodeCracker.challenge2();
	}
	
	public void challenge1(){
		//initially sets up the Enigma Machine
		EnigmaFile myMachine = new EnigmaFile();
		myMachine.addRotor("IV", 0);
		myMachine.setPosition(0, 8);
		myMachine.addRotor("III", 1);
		myMachine.setPosition(1, 4);
		myMachine.addRotor("II", 2);
		myMachine.setPosition(2, 21);
		myMachine.addReflector("ReflectorI");
		myMachine.resetOutputFile();
		//gets the input string from the file
		String inputString = myMachine.readInputString();
		inputString = inputString.toUpperCase();
		String output = "";
		//removes every character which isn't an upper case letter
		inputString = inputString.replaceAll("[^A-Z]", "");
		int inputLength = inputString.length();
		char[] inputChar = new char[inputLength];
		//separates each character in the string
		for(int i = 0; i < inputLength; i++){
			inputChar[i] = inputString.charAt(i);
		}
		//loops for every available character at the other end of the plug for d
		for(int character = 0; character < 26; character++){
			char plug2 = '\u0000';
			plug2 = myMachine.charOfLetter(character);
			//can't add d to a plug connected to d
			if(plug2 == 'D'){
				continue;
			}
			myMachine.addPlug('D', plug2);
			//loops for every available character to be connected to S
			for(int character2 = 0; character2 < 26; character2++){
				//can't connect an already used plug or S or D to the other end of the plug for S
				if(character2 == character || character2 == 18 || character2 == 3){
					continue;
				}
				char plugEnd = '\u0000';
				//adds the plug to the plugboard
				plugEnd = myMachine.charOfLetter(character2);
				myMachine.addPlug('S', plugEnd);
				output = "";
				//encodes each character of the input string
				for(int i = 0; i < inputChar.length; i++){
					output = output + myMachine.encodeLetter(inputChar[i]);
				}
				boolean contains = false;
				//checks if each string contains the suggested word, printing the ends of the plugs if it does
				contains = output.contains("ANSWER");
				if(contains){
					myMachine.writeOutputString(output + " Plug1: " + plug2 + " Plug2: " + plugEnd);
				}
				//removes the plug with s at one end and resets the rotors
				myMachine.removePlug(1);
				myMachine.setPosition(0, 8);
				myMachine.setPosition(1, 4);
				myMachine.setPosition(2, 21);
			}
			//resets the Enigma Machine
			myMachine.removePlug(0);
			myMachine.setPosition(0, 8);
			myMachine.setPosition(1, 4);
			myMachine.setPosition(2, 21);
		}
		System.out.println("Done");	
	}
	
	public void challenge2(){
		//initially sets up the enigma machine
		EnigmaFile myMachine = new EnigmaFile();
		myMachine.addPlug('H', 'L');
		myMachine.addPlug('G', 'P');
		myMachine.addRotor("V", 0);
		myMachine.setPosition(0, 0);
		myMachine.addRotor("III", 1);
		myMachine.setPosition(1, 0);
		myMachine.addRotor("II", 2);
		myMachine.setPosition(2, 0);
		myMachine.addReflector("ReflectorI");
		myMachine.resetOutputFile();
		//reads the inputs string from the file
		String inputString = myMachine.readInputString();
		//makes all the input string uppercase and removes any invalid characters
		inputString = inputString.toUpperCase();
		inputString = inputString.replaceAll("[^A-Z]", "");
		int inputLength = inputString.length();
		char[] inputChar = new char[inputLength];
		String output = "";
		//separates the input string into all its characters
		for(int i = 0; i < inputLength; i++){
			inputChar[i] = inputString.charAt(i);
		}
		//loops for each position for the rotor in slot 0
		for(int i = 0; i< 26; i++){
			myMachine.setPosition(0, i);
			//loops for each position for the rotor in slot 1
			for(int i1 = 0; i1 < 26; i1++){
				myMachine.setPosition(0, i);
				myMachine.setPosition(1, i1);
				//loops for each of the available positions for the rotor in slot 2
				for(int i2 = 0; i2 < 26; i2++){
					myMachine.setPosition(0, i);
					myMachine.setPosition(1, i1);
					myMachine.setPosition(2, i2);
					//encodes the input string a character at a time
					for(int i3 = 0; i3 < inputChar.length; i3++){
						output = output + myMachine.encodeLetter(inputChar[i3]);
					}
					boolean contains = false;
					//checks if each string contains the suggested word, printing the rotors and their intial positions if it does
					contains = output.contains("ELECTRIC");
					if(contains){
						myMachine.writeOutputString(output + " Rotor slot 0 position:" + i + " Rotor slot 1 position:" + i1 + " Rotor slot 2 position:" + i2);
					}
					output = "";
				}
			}
		}
		System.out.println("Done");
	}
	
	public void challenge3(){
		//initially sets up the enigma machine with the correct plugs and reflector type
		EnigmaFile myMachine = new EnigmaFile();
		myMachine.addPlug('M', 'F');
		myMachine.addPlug('O', 'I');
		myMachine.addReflector("ReflectorI");
		myMachine.resetOutputFile();
		//gets the input string from the file
		String inputString = myMachine.readInputString();
		//converts all the string to uppercase and removes any invalid characters
		inputString = inputString.toUpperCase();
		inputString = inputString.replaceAll("[^A-Z]", "");
		int inputLength = inputString.length();
		char[] inputChar = new char[inputLength];
		String output = "";
		//separates each character in the string
		for(int i = 0; i < inputLength; i++){
			inputChar[i] = inputString.charAt(i);
		}
		//loops for every type of rotor for slot 0
		for(int rotor1 = 0; rotor1 < 5; rotor1++){
			myMachine.addRotor(types[rotor1], 0);
			myMachine.setPosition(0, 22);
			//loops for every type of rotor for slot 1
			for(int rotor2 = 0; rotor2 < 5; rotor2++){
				myMachine.addRotor(types[rotor2], 1);
				myMachine.setPosition(1, 24);
				myMachine.setPosition(0, 22);
				//loops for every type of rotor for slot 2
				for(int rotor3 = 0; rotor3 < 5; rotor3 ++){
					myMachine.addRotor(types[rotor3], 2);
					myMachine.setPosition(2, 23);
					myMachine.setPosition(1, 24);
					myMachine.setPosition(0, 22);
					//encodes the input string a character at a time
					for(int i = 0; i < inputChar.length; i++){
						output = output + myMachine.encodeLetter(inputChar[i]);
					}
					boolean contains = false;
					//checks if each string contains the suggested word, printing the encoded string and each rotor type if it does
					contains = output.contains("JAVA");
					if(contains){
						myMachine.writeOutputString(output + " Slot 0 type:" + types[rotor1] + " Slot 1 type:" + types[rotor2] + " Slot 2 type:" + types[rotor3]);
					}
					output = "";
					myMachine.removeRotor(2);
				}
				myMachine.removeRotor(1);
			}
			myMachine.removeRotor(0);
		}
		System.out.println("Done");
	}
	
}
	

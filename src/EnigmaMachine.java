import java.util.ArrayList;


public class EnigmaMachine {

	private ArrayList<BasicRotor> rotors = new ArrayList<BasicRotor>();
	private ArrayList<Reflector> reflectors = new ArrayList<Reflector>();
	private Plugboard myPlugboard = new Plugboard();
	
	//adds a plug between a and b to the Plugboard
	public void addPlug(char a, char b){
		boolean added = myPlugboard.addPlug(a, b);
		if(!added){
			System.out.println("Plug is already in use");
		}
	}
	
	//clears all the plugs from the plugboard
	public void clearPlugboard(){
		myPlugboard.clear();
	}
	
	//removes a plug from the board
	public void removePlug(int plugNum){
		myPlugboard.removePlug(plugNum);
	}
	
	//adds a rotor to the machine in the correct slot
	public void addRotor(String BasicRotor, int slot){
		rotors.add(slot, new BasicRotor(BasicRotor, slot));	
	}
	
	//adds a turnover rotor to the machine in the correct slot
	public void addTurnoverRotor(String BasicRotor, int slot){
		rotors.add(slot, new TurnoverRotor(BasicRotor, slot));
	}
	
	//returns the rotor in the given slot
	public Rotor getRotor(int slot){
		return rotors.get(slot);
	}
	
	//removes the rotor at the given slot
	public void removeRotor(int slot){
		rotors.remove(slot);
	}
	
	//returns the number of rotors currently added
	public int numOfRotors(){
		return rotors.size();
	}
	
	//adds a Reflector to the machine
	public void addReflector(String type){
		Reflector myReflector = new Reflector();
		myReflector.initialise(type);
		reflectors.add(myReflector);
	}
	
	//returns the reflector
	public Reflector getReflector(){
		return reflectors.get(0);
	}
	
	//sets the position of a Rotor in a given slot
	public void setPosition(int slot, int position){
		rotors.get(slot).position = position;
	}
	
	//encodes a letter using the setup given and displays the output
	public char encodeLetter(char letter){
		//looks to see if the letter's plug is connected to another
		//if it is then the letter at the other end is used
		for(int i = 0; i < myPlugboard.getNumPlugs(); i++){
			letter = myPlugboard.getPlug(i).test(letter);
		}
		
		//gets the int value of the letter, between 0 and 25
		int letterInt = integerOfLetter(letter);	
		//puts the letter value through the first rotor
		letterInt = rotors.get(0).substitute(letterInt);
		letterInt = loopBackRound(letterInt);
		//puts the letter value through the second rotor
		letterInt = rotors.get(1).substitute(letterInt);
		letterInt = loopBackRound(letterInt);
		//puts the letter value through the third rotor
		letterInt = rotors.get(2).substitute(letterInt);
		letterInt = loopBackRound(letterInt);
		//puts the letter value through the reflector
		letterInt = reflectors.get(0).substitute(letterInt);
		letterInt = loopBackRound(letterInt);
		//sends the letter value back through the three rotors using the inverse mapping
		letterInt = rotors.get(2).substituteBack(letterInt);
		letterInt = loopBackRound(letterInt);
		letterInt = rotors.get(1).substituteBack(letterInt);
		letterInt = loopBackRound(letterInt);
		letterInt = rotors.get(0).substituteBack(letterInt);
		letterInt = loopBackRound(letterInt);
		//turns the int back into the equivalent character
		letter = charOfLetter(letterInt);
		//checks if there's a plug attached to the letter
		for(int i = 0; i < myPlugboard.getNumPlugs(); i++){
			letter = myPlugboard.getPlug(i).test(letter);
		}
		//rotates the rotor in slot 0
		rotors.get(0).rotate();
		//checks to see if the next rotor needs rotating only if previous rotor is a turnover rotor
		//if it does then it rotates it by 1
		if(rotors.get(0) instanceof TurnoverRotor){
			boolean rotateNext = ((TurnoverRotor) rotors.get(0)).nextRotor();
			for(int i = 0; i < 2; i++){
				if(rotateNext == true){
					//gets the rotor in the next slot from the turnover rotor
					BasicRotor nextSlotRotor = (BasicRotor) ((TurnoverRotor) rotors.get(i)).getNextRotor();
					nextSlotRotor.rotate();
					//checks the next rotor
					if(nextSlotRotor instanceof TurnoverRotor){
						rotateNext = (((TurnoverRotor) nextSlotRotor).nextRotor());
					}
				}
			}
		}
		return letter;
	}
	

	//loops the letter value back round if it becomes greater than 25
	private int loopBackRound(int letterInt) {
		if(letterInt <= 25){
			return letterInt;
		}else{
			int temp = 0;
			temp = letterInt - 25 - 1;
			return temp;
		}
	}

	//returns the equivalent character for a given value
	public char charOfLetter(int letterInt) {
		int letterPosition = letterInt + 65;
		char temp = (char) letterPosition;
		return temp;
	}

	//returns a value for a given character
	public int integerOfLetter(char letter) {
		return (int) letter - 65;
	}
}

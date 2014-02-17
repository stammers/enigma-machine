
public class BasicRotor extends Rotor{
	private int[] inverseMapping = new int[26];
	
	
	//creates a BasicRotor and defines it's type
	public BasicRotor(String type, int slot){
		super.type = type;
		super.slot = slot;
		initialise(type);
	}

	//returns the number mapped to by the input number
	//takes into account the position of the rotor
	public int substitute(int number) {
		int carry;
		if(position == 0){
			return mapping[number];
		}else if (position <= number){
			return mapping[number-position]+ position;
		}else {
			//loops around again once it gets to the end
			carry = position - number;
			int number2 = mapping[25-carry+1];
			number2 = number2 + position;
			return number2;
		}
	}

	//does the same as the substitute method but using an inverse map
	public int substituteBack(int number){
		
		//creates the inverse map
		for(int i = 0; i < mapping.length; i++){
			int temp = mapping[i];
			inverseMapping[temp] = i;
		}
		
		//returns the number mapped to by the input number, taking into account the position of the Rotor
		int carry;
		if(position == 0){
			return inverseMapping[number];
		}else if (position <= number){
			return inverseMapping[number-position]+ position;
		}else {
			carry = position - number;
			int number2 = inverseMapping[25-carry +1];
			number2 = number2 + position;
			return number2;
		}
	}
	
	//rotates the rotor round one space
	public void rotate(){
		position = position + 1;
		
		//loops back to the initial position once a full circle is complete
		if(position == 26){
			position = 0;
		}
	}
	
	//prints out the inverse map
	//used for testing
	public void printInverse(){
		for(int i = 0; i < inverseMapping.length; i++)
			System.out.println(inverseMapping[i]);
	}
	
	//prints out the map
	//used for testing
	public void print(){
		for(int i = 0; i < mapping.length; i++)
			System.out.println(mapping[i]);
	}
	
	
	public void initialise(String type) {
		type = type.toUpperCase();
		//initialises each type of BasicRotor with their separate mappings
		if(type.equals("I")){
			mapping[0] = 4;
			mapping[1] = 10;
			mapping[2] = 12;
			mapping[3] = 5;
			mapping[4] = 11;
			mapping[5] = 6;
			mapping[6] = 3;
			mapping[7] = 16;
			mapping[8] = 21;
			mapping[9] = 25;
			mapping[10] = 13;
			mapping[11] = 19;
			mapping[12] = 14;
			mapping[13] = 22;
			mapping[14] = 24;
			mapping[15] = 7;
			mapping[16] = 23;
			mapping[17] = 20;
			mapping[18] = 18;
			mapping[19] = 15;
			mapping[20] = 0;
			mapping[21] = 8;
			mapping[22] = 1;
			mapping[23] = 17;
			mapping[24] = 2;
			mapping[25] = 9;
		}else if(type.equals("II")){
			mapping[0] = 0;
			mapping[1] = 9;
			mapping[2] = 3;
			mapping[3] = 10;
			mapping[4] = 18;
			mapping[5] = 8;
			mapping[6] = 17;
			mapping[7] = 20;
			mapping[8] = 23;
			mapping[9] = 1;
			mapping[10] = 11;
			mapping[11] = 7;
			mapping[12] = 22;
			mapping[13] = 19;
			mapping[14] = 12;
			mapping[15] = 2;
			mapping[16] = 16;
			mapping[17] = 6;
			mapping[18] = 25;
			mapping[19] = 13;
			mapping[20] = 15;
			mapping[21] = 24;
			mapping[22] = 5;
			mapping[23] = 21;
			mapping[24] = 14;
			mapping[25] = 4;
		}else if(type.equals("III")){
			mapping[0] = 1;
			mapping[1] = 3;
			mapping[2] = 5;
			mapping[3] = 7;
			mapping[4] = 9;
			mapping[5] = 11;
			mapping[6] = 2;
			mapping[7] = 15;
			mapping[8] = 17;
			mapping[9] = 19;
			mapping[10] = 23;
			mapping[11] = 21;
			mapping[12] = 25;
			mapping[13] = 13;
			mapping[14] = 24;
			mapping[15] = 4;
			mapping[16] = 8;
			mapping[17] = 22;
			mapping[18] = 6;
			mapping[19] = 0;
			mapping[20] = 10;
			mapping[21] = 12;
			mapping[22] = 20;
			mapping[23] = 18;
			mapping[24] = 16;
			mapping[25] = 14;
		}else if(type.equals("IV")){
			mapping[0] = 4;
			mapping[1] = 18;
			mapping[2] = 14;
			mapping[3] = 21;
			mapping[4] = 15;
			mapping[5] = 25;
			mapping[6] = 9;
			mapping[7] = 0;
			mapping[8] = 24;
			mapping[9] = 16;
			mapping[10] = 20;
			mapping[11] = 8;
			mapping[12] = 17;
			mapping[13] = 7;
			mapping[14] = 23;
			mapping[15] = 11;
			mapping[16] = 13;
			mapping[17] = 5;
			mapping[18] = 19;
			mapping[19] = 6;
			mapping[20] = 10;
			mapping[21] = 3;
			mapping[22] = 2;
			mapping[23] = 12;
			mapping[24] = 22;
			mapping[25] = 1;
		}else if(type.equals("V")){
			mapping[0] = 21;
			mapping[1] = 25;
			mapping[2] = 1;
			mapping[3] = 17;
			mapping[4] = 6;
			mapping[5] = 8;
			mapping[6] = 19;
			mapping[7] = 24;
			mapping[8] = 20;
			mapping[9] = 15;
			mapping[10] = 18;
			mapping[11] = 3;
			mapping[12] = 13;
			mapping[13] = 7;
			mapping[14] = 11;
			mapping[15] = 23;
			mapping[16] = 0;
			mapping[17] = 22;
			mapping[18] = 12;
			mapping[19] = 9;
			mapping[20] = 16;
			mapping[21] = 14;
			mapping[22] = 5;
			mapping[23] = 4;
			mapping[24] = 2;
			mapping[25] = 10;
		}
		
	}
	

}

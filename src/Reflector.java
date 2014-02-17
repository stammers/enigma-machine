
public class Reflector extends Rotor {

	void initialise(String type) {
		//changes all the input reflector type to uppercase
		type = type.toUpperCase();
		//initialises all the mappings for Reflector type 1
		if(type.equals("REFLECTORI")){
			mapping[0] = 24;
			mapping[1] = 17;
			mapping[2] = 20;
			mapping[3] = 7;
			mapping[4] = 16;
			mapping[5] = 18;
			mapping[6] = 11;
			mapping[7] = 3;
			mapping[8] = 15;
			mapping[9] = 23;
			mapping[10] = 13;
			mapping[11] = 6;
			mapping[12] = 14;
			mapping[13] = 10;
			mapping[14] = 12;
			mapping[15] = 8;
			mapping[16] = 4;
			mapping[17] = 1;
			mapping[18] = 5;
			mapping[19] = 25;
			mapping[20] = 2;
			mapping[21] = 22;
			mapping[22] = 21;
			mapping[23] = 9;
			mapping[24] = 0;
			mapping[25] = 19;
			
		//initialises all the mappings for reflector type 2
		}else if(type.equals("REFLECTORII")){
			mapping[0] = 5;
			mapping[1] = 21;
			mapping[2] = 15;
			mapping[3] = 9;
			mapping[4] = 8;
			mapping[5] = 0;
			mapping[6] = 14;
			mapping[7] = 24;
			mapping[8] = 4;
			mapping[9] = 3;
			mapping[10] = 17;
			mapping[11] = 25;
			mapping[12] = 23;
			mapping[13] = 22;
			mapping[14] = 6;
			mapping[15] = 2;
			mapping[16] = 19;
			mapping[17] = 10;
			mapping[18] = 20;
			mapping[19] = 16;
			mapping[20] = 18;
			mapping[21] = 1;
			mapping[22] = 13;
			mapping[23] = 12;
			mapping[24] = 7;
			mapping[25] = 11;
		}
		
	}

	//returns the number for a given mapping element
	int substitute(int number) {
		return mapping[number];
	}


}

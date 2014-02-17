
public class TurnoverRotor extends BasicRotor{

	private int turnoverPosition;
	//holds the rotor in the next slot
	private Rotor nextRotor;
	
	public TurnoverRotor(String type, int slot){
		//creates the turnover rotor with its inital position depending on its type
		super(type, slot);
		if(type.equals("I")){
			turnoverPosition = 24;
		} else if(type.equals("II")){
			turnoverPosition = 12;
		} else if(type.equals("III")){
			turnoverPosition = 3;
		} else if(type.equals("IV")){
			turnoverPosition = 17;
		} else if(type.equals("V")){
			turnoverPosition = 7;
		}
	}
	
	//sets the rotor in the next slot in the machine
	public void setNextRotor(Rotor next){
		this.nextRotor = next;
	}
	
	//returns the rotor in the next slot in the machine
	public Rotor getNextRotor(){
		return this.nextRotor;
	}
	
	//returns whether the next rotor should be rotated
	public boolean nextRotor(){
		//rotates next only when the rotor is in its turnover position and in slot 0 or 1
		if(position == turnoverPosition && nextRotor !=null){
			return true;
		}
		return false;
	}
}

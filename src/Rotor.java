

public abstract class Rotor {

	protected String type;
	protected int position;
	protected int[] mapping = new int[26];
	protected int slot;
	
	//sets the initial position of the rotor
	public void setPosition(int position){
		this.position = position;
	}
	
	//return the rotors current position
	public int getPosition(){
		return this.position;
	}
	
	//returns the type of rotor
	public String getType(){
		return this.type;
	}
	
	abstract void initialise(String type);
	
	abstract int substitute(int number);
}

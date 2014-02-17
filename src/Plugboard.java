import java.util.ArrayList;


public class Plugboard {

	private ArrayList<Plug> plugs = new ArrayList<Plug>();
	
	//adds a plug to the plugboard
	public boolean addPlug(char end1, char end2){
		end1 = Character.toUpperCase(end1);
		end2 = Character.toUpperCase(end2);
		Plug newPlug = new Plug(end1, end2);
		boolean inUse = false;
		int i = 0;
		//loops until the plug is already in use or all the other plugs have been checked
		while (inUse == false && i < plugs.size()){
			inUse = newPlug.clashesWith(plugs.get(i));
			i++;
		}
		if(inUse == false){
			plugs.add(newPlug);
			return true;
		}else {
			return false;
		}
	}
	

	//returns the number of plugs connected
	public int getNumPlugs(){
		return plugs.size();
	}
	
	//clears all the plugs from the plugboard
	public void clear(){
		plugs.clear();
	}
	
	//removes a plug from the given place in teh plug arrayList
	//used in the code cracking challenges
	public void removePlug(int plugNum){
		plugs.remove(plugNum);
	}
	
	//gets the plug at the given position in the array of plugs
	public Plug getPlug(int position){
		return plugs.get(position);
	}
}


public class Plug {
	
	private char endSocket1;
	private char endSocket2;
	
	//creates a plug with two ends connected to different characters
	public Plug(char endSocket1, char endSocket2){
		this.endSocket1 = endSocket1;
		this.endSocket2 = endSocket2;
	}
	
	//returns the letter at the top end of the plug
	public char getEnd1(){
		return endSocket1;
	}
	
	//returns the letter at the bottom end of the plug
	public char getEnd2(){
		return endSocket2;
	}
	
	//tests if a plug is connected to a character
	//if it is it returns the character at the other end of the plug
	//if not it returns the letter plug being checked
	public char test(char letterIn){
		if(endSocket1 == letterIn){
			return endSocket2;
		}else if(endSocket2 == letterIn){
			return endSocket1;
		}else{
			return letterIn;
		}
	}
	
	//checks if the sockets are already in use
	public boolean clashesWith(Plug plugin){
		char socket1 = plugin.getEnd1();
		char socket2 = plugin.getEnd2();
		if(socket1 == endSocket1 || socket2 == endSocket2){
			return true;
		}else{
			return false;
		}
	}
}

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField output;
	private JLabel lblOutput;
	private int step = 1;
	private String plug1 = "";
	private String plug2 = "";
	private String rotorType="";
	private String rotorNum = "";
	private int rotorPos;
	private GUIController controller = new GUIController();
	private String reflectorType = "";
	private JLabel lblInstructions1;
	private JLabel lblInstructions2;
	private JButton btnGo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 366, 261);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		
		textField.setBounds(103, 54, 148, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		lblInstructions1 = new JLabel("Would you like to add any Plugs?");
		lblInstructions1.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions1.setBounds(0, 11, 350, 14);
		contentPane.add(lblInstructions1);
		
		lblInstructions2 = new JLabel("Enter Y or N");
		lblInstructions2.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructions2.setBounds(0, 29, 350, 14);
		contentPane.add(lblInstructions2);
		
		
		lblOutput = new JLabel("Output:");
		lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblOutput.setBounds(103, 157, 148, 14);
		contentPane.add(lblOutput);
		lblOutput.setVisible(false);
		
		output = new JTextField();
		output.setHorizontalAlignment(SwingConstants.CENTER);
		output.setEditable(false);
		output.setBounds(10, 182, 330, 20);
		contentPane.add(output);
		output.setColumns(10);
		output.setVisible(false);
		

		
		btnGo = new JButton("Next");
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				go();
			}
		});
		textField.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				go();
			}
			
			
		});
		btnGo.setBounds(131, 85, 89, 23);
		contentPane.add(btnGo);
		
	}
	
	public void go(){
		String entered = textField.getText();
		boolean temp = controller.testIfEmpty(entered);
		if(!temp){	
			//set up plugs
			if(step == 1){
				//returns 0 for incorrect, 1 for no, 2 for yes
				int continueStep1 = controller.readYesOrNo(entered);
				if(continueStep1 == 0){
					//retrys user entry if incorrect
					lblInstructions1.setText("Please enter Y or N");
					lblInstructions2.setText("");
					return;
				}
				if(continueStep1 == 1){
					//chose no so skips adding plugs
					step = 0;
				}
				if(continueStep1 == 2){
					//chose yes so moves onto adding plugs
					step = 2;
				}
			}
			if(step == 2){
				lblInstructions1.setText("Please enter the character ");
				lblInstructions2.setText("at the top of the plug");
				textField.setText("");
				step = 3;
				return;
			}
			if(step == 3){
				plug1 = entered;
				step = 4;
			}
			if(step == 4){
				lblInstructions1.setText("Please enter the character ");
				lblInstructions2.setText("at the bottom of the plug");
				textField.setText("");
				step = 5;
				return;
			}
			if(step == 5){
				plug2 = entered;
				controller.addPlugs(plug1, plug2);
				step = 1;
				lblInstructions1.setText("Would you like to add more Plugs?");
				lblInstructions2.setText("Enter Y or N");
				textField.setText("");
				return;
			}
			
			if(step == 0){
				lblInstructions1.setText("Would you like a: Turnover Rotors or b: Basic Rotors?");
				lblInstructions2.setText("Please Enter a or b");
				textField.setText("");
				step = 6;
				return;
			}
			//inital rotor set up
			if(step == 6){
				int continueStep6 = controller.readAOrB(entered);
				if(continueStep6 == 0){
					//retrys user entry if incorrect
					lblInstructions1.setText("Please enter a or b");
					textField.setText("");
					lblInstructions2.setText("");
					return;
				}
				if(continueStep6 == 1){
					rotorType = "Turnover";
				}
				if(continueStep6 == 2){
					rotorType = "Basic";
				}
				step = 7;
				lblInstructions1.setText("Which type of rotor would you like at slot 1?");
				textField.setText("");
				lblInstructions2.setText("");
				return;
			}
			
			
			//slot 0 rotor set up
			if(step == 7){
				int continueStep7 = controller.readRotorType(entered);
				if(continueStep7 == 0){
					lblInstructions1.setText("Please enter a valid rotor type");
					lblInstructions2.setText("Either I, II, III, IV or V");
					textField.setText("");
					return;
				}
				if(continueStep7 == 1){
					rotorNum = entered;
					lblInstructions1.setText("What position would you like the rotor to start at?");
					textField.setText("");
					lblInstructions2.setText("");
					step = 8;
					return;
				}
			}
			if(step == 8){
				int continueStep8 = controller.readRotorPos(entered);
				if(continueStep8 == 0){
					lblInstructions1.setText("Please enter a valid rotor position from 0 to 25");
					textField.setText("");
					return;
				}
				if(continueStep8 == 1){
					rotorPos = Integer.valueOf(entered);
					lblInstructions1.setText("Which type of rotor would you like at slot 2?");
					textField.setText("");
					step = 9;
				}
			}
			if(step == 9){
				controller.addRotor(rotorType, rotorNum, 0, rotorPos);
				step = 10;
				return;
			}
			
			
			//set up slot 1 rotor
			if(step == 10){
				int continueStep10 = controller.readRotorType(entered);
				if(continueStep10 == 0){
					lblInstructions1.setText("Please enter a valid rotor type");
					lblInstructions2.setText("Either I, II, III, IV or V");
					textField.setText("");
					return;
				}
				if(continueStep10 == 1){
					boolean canAdd = controller.canAddRotor(entered);
					if(!canAdd){
						rotorNum = entered;
						lblInstructions1.setText("What position would you like the rotor to start at?");
						textField.setText("");
						lblInstructions2.setText("");
						step = 11;
					}else{
						lblInstructions1.setText("This rotor type has already been used");
						textField.setText("");
					}
					return;
				}
			}
			if(step == 11){
				int continueStep11 = controller.readRotorPos(entered);
				if(continueStep11 == 0){
					lblInstructions1.setText("Please enter a valid rotor position from 0 to 25");
					textField.setText("");
					return;
				}
				if(continueStep11 == 1){
					rotorPos = Integer.valueOf(entered);
					lblInstructions1.setText("Which type of rotor would you like at slot 3?");
					textField.setText("");
					step = 12;
				}
			}
			if(step == 12){
				controller.addRotor(rotorType, rotorNum, 1, rotorPos);
				step = 13;
				return;
			}
			
			
			//slot 2 rotor set up
			if(step == 13){
				int continueStep13 = controller.readRotorType(entered);
				if(continueStep13 == 0){
					lblInstructions1.setText("Please enter a valid rotor type");
					lblInstructions2.setText("Either I, II, III, IV or V");
					textField.setText("");
					return;
				}
				if(continueStep13 == 1){
					boolean canAdd = controller.canAddRotor(entered);
					if(!canAdd){
						rotorNum = entered;
						lblInstructions1.setText("What position would you like the rotor to start at?");
						lblInstructions2.setText("");
						textField.setText("");
						step = 14;
					}else{
						lblInstructions1.setText("This rotor type has already been used");
						textField.setText("");
					}
					return;
				}
			}
			if(step == 14){
				int continueStep14 = controller.readRotorPos(entered);
				if(continueStep14 == 0){
					lblInstructions1.setText("Please enter a valid rotor position from 0 to 25");
					textField.setText("");
					return;
				}
				if(continueStep14 == 1){
					rotorPos = Integer.valueOf(entered);
					step = 15;
				}
			}
			
			
			
			//sets up the reflector
			if(step == 15){
				controller.addRotor(rotorType, rotorNum, 2, rotorPos);
				step = 16;
				textField.setText("");
				lblInstructions1.setText("Which type of reflector would you like?");
				return;
			}
			
			if(step == 16){
				if(rotorType.equalsIgnoreCase("Turnover")){
					controller.setNext();
				}
				int continueStep16 = controller.readReflectorType(entered);
				if(continueStep16 == 0){
					lblInstructions1.setText("Please enter a valid reflector type");
					lblInstructions2.setText("Either ReflectorI or ReflectorII");
					textField.setText("");
					return;
				}
				
				
				//encoding string
				if(continueStep16 == 1){
					reflectorType = entered;
					controller.addReflector(reflectorType);
					step = 17;
					textField.setText("");
					lblInstructions1.setText("Please Enter String to be encoded");
					lblInstructions2.setText("");
					btnGo.setText("Encode");
					textField.setText("");
					return;
				}
			}
			if(step == 17){
				int stringLength = entered.length();
				if(stringLength == 0){
					lblInstructions1.setText("Please Enter at least one letter");
					textField.setText("");
					return;
				}
				else{
					lblOutput.setVisible(true);
					output.setVisible(true);	
					output.setText(controller.encodeString(entered));
				}
				
			}
		}
	}
}

package major;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {
	public String cmd = "";
	public String temp = "";
	public static ArrayList<String> Args = new ArrayList<String>();
	public ArrayList<String> commands = new ArrayList<String>();
	public ArrayList<Integer> commandsArgs = new ArrayList<Integer>();
	public boolean validate = false;
	Terminal terminal;
	
	
	public Parser(){
		terminal = new Terminal();
		
		commands.add("cd");
		commands.add("ls");
		commands.add("cp");
		commands.add("cat");
		commands.add("more");
		commands.add("date");
		commands.add("|");
		commands.add("rmdir");
		commands.add("mkdir");
		commands.add("args");
		commands.add("help");
		commands.add("pwd");
		commands.add(">");
		commands.add(">>");
		commands.add("mv");
		commands.add("rm");
		commands.add("clear");
		commandsArgs.add(1);
		commandsArgs.add(0);
		commandsArgs.add(2);
		commandsArgs.add(2);
		commandsArgs.add(1);
		commandsArgs.add(0);
		commandsArgs.add(0); // pipe ->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		commandsArgs.add(1);
		commandsArgs.add(1);
		commandsArgs.add(1);
		commandsArgs.add(0);
		commandsArgs.add(0);
		commandsArgs.add(2);
		commandsArgs.add(2);
		commandsArgs.add(2);
		commandsArgs.add(1);
		commandsArgs.add(0);
		
	}
	
	
	public boolean parse(String userInput) {

		int i;
		
	    if(userInput != null){
	    	
	    	//First for loop - to get the command
	        for(i = 0; i < userInput.length(); i++){
	            if(!Character.isWhitespace(userInput.charAt(i))){
	                cmd+=userInput.charAt(i);
	                
	            }else		
	            	break;
	            
	            
	        }
	        
	        
	        //Second for loop - to get the args
	        for(int j=i+1 ; j<userInput.length();j++) {
	        	if(!Character.isWhitespace(userInput.charAt(j))){
	        		temp+=userInput.charAt(j);
	            }else { 
	            	Args.add(temp);
		        	temp = "";
	            	
	            }
	        }
	    }

	    

	    
	    //Third for loop - to check the validation of the cmd and the no. of args
	    for(int index=0; index<commands.size();index++) {
	    	
	    	int flag = 0;
	    	
	    	if(commands.get(index).equals(cmd)) {
	    		//cmd is CORRECT	, Then check no. of Args?
	    		flag = 1;
	    		
	    		if(commandsArgs.get(index).equals(Args.size())) {
	    			//The number of args is CORRECT
	    			
	    			
	    		    //Go to terminal 
	    		    
	    		    if(cmd.equals("mv")) {
	    		    	terminal.mv(Args);
	    		    }else if(cmd.equals("help")) {
	    		    	terminal.help();
	    		    }else if(cmd.equals("clear")) {
	    		    	terminal.clear();
	    		    }else if(cmd.equals("more")) {
	    		    	try {
							terminal.more(Args);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    		    }else if(cmd.equals("date")) {
	    		    	terminal.date();
	    		    }
	    		    
	    		    validate = true;
	    			
	    		}else {
	    			
	    			//The number of args is INCORRECT
	    			System.out.println(cmd+": cannot stat '"+Args.get(0)+"': No such file or directory");
	    			validate =  false;

	    		}
	    			
	    	}else if(index == commands.size() && flag == 0){
	    		//cmd is INCORRECT
	    			System.out.println("'"+cmd+"' is not recognized as an internal or external command, operable program or batch file.");
	    			validate =  false;
	    	} 	
	    }
		return validate;
	    

	   
		
	}
	
}

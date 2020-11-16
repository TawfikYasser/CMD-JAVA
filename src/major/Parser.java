package major;

import java.io.IOException;

import java.util.ArrayList;

public class Parser {
	static int ff=0;
	public static String cmd = "";
	public static String temp = "";
	public static String operatorCommand = "";
	public static String operatorFilename="";
	public static ArrayList<String> Args = new ArrayList<String>();
	public static ArrayList<String> commands = new ArrayList<String>();
	public static ArrayList<Integer> commandsArgs = new ArrayList<Integer>();
	public static ArrayList<String> pipeCommands = new ArrayList<String>();
	public boolean validate = false;
	static Terminal terminal;

	public Parser() {



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

		if (userInput != null) {

			if(userInput.contains("args")) {
				checkAndAssign(userInput);
			}else {
			
			if(userInput.contains("|")) {
				ff = -1;
				// pipe Case
				userInput+="|";
				for(int k = 0; k < userInput.length(); k++) {

					if(userInput.charAt(k) != '|') {
					  
						operatorCommand+=userInput.charAt(k);
					}
					else {
						pipeCommands.add(operatorCommand);
						operatorCommand = "";
					}
				}
					//For
					
					for(int f = 0 ;f<pipeCommands.size();f++) {
						String space  = pipeCommands.get(f);
						if(space.charAt(0) == ' ') {
							space = space.substring(1, space.length()); 
						}
						checkAndAssign(space);
						
					}

				
			}else if(userInput.contains(">")) {
				// > Case
				userInput+=">";
				for(int k = 0; k < userInput.length(); k++) {
					  
					if(userInput.charAt(k) != '>')  {
					  
						operatorCommand+=userInput.charAt(k);
					
					}
					else {
						pipeCommands.add(operatorCommand); // array with one arg
						operatorCommand = "";
					}
				}

			operatorFilename = pipeCommands.get(1); // file name 	
			
			}else if(userInput.contains(">>")) {
				// >> Case
				
				userInput+=">>";
				for(int k = 0; k < userInput.length(); k++) {
					  
					if(userInput.charAt(k) == '>' && userInput.charAt(k+1) == '>')  {
					  
						pipeCommands.add(operatorCommand);
						operatorCommand = "";
					}
					else {
						
						operatorCommand+=userInput.charAt(k);
						
						
					}
				}

			operatorFilename = pipeCommands.get(1);	
				
			}else {
				//Normal Case
				checkAndAssign(userInput);
			}
			
			}
		}
		return validate;
	}

	//Normal Case -********************************************
	public static void checkAndAssign(String userInput) {	
		int i;
		// First for loop - to get the command
		for (i = 0; i < userInput.length(); i++) {

			if (!Character.isWhitespace(userInput.charAt(i))) {
				cmd += userInput.charAt(i);

			} else
				break;
		}

		// Second for loop - to get the args
		for (int j = i + 1; j < userInput.length(); j++) {
			if (!Character.isWhitespace(userInput.charAt(j))) {
				temp += userInput.charAt(j);
			} else {
				Args.add(temp);
				temp = "";
			}
		}
		
		
		callCommand();
	}
	// Normal Case callCommand
	public static void callCommand() {


		int flag = 0;

		for (int y = 0; y < commands.size(); y++) {

			if (cmd.equals(commands.get(y))) {

				flag++;

				if (Args.size() == commandsArgs.get(y)) {

					flag++;
				}
			}
		}
	
		if (flag == 0) {
			System.out.println("Invalid Command");
		} else if (flag == 1) {
			System.out.println("The arguments number is wrong");
		} else {

			if (cmd.equals("mv")) {
				terminal.mv(Args);
			} else if (cmd.equals("help")) {
				terminal.help();
			} else if (cmd.equals("clear")) {
				terminal.clear();
			} else if (cmd.equals("more")) {
				try {
					terminal.more(Args);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (cmd.equals("date")) {
				terminal.date();
			}else if(cmd.equals("pwd")) {
				terminal.pwd();
			}else if(cmd.equals("rm")) {
				terminal.rm(Args.get(0));
			}else if(cmd.equals("rmdir")) {
				terminal.rmdir(Args.get(0));
			}else if(cmd.equals("cd")) {
				terminal.cd(Args.get(0));
			}else if(cmd.equals("mkdir")) {
				terminal.mkdir(Args.get(0));
			}else if(cmd.equals("ls")) {
				terminal.ls();
			}else if(cmd.equals("args")) {
				terminal.args(Args.get(0));
			}else if(cmd.equals("cp")) {
				terminal.cp(Args);
			}else if(cmd.equals("cat")) {
				terminal.cat(Args);
			}
		}
		cmd="";
		Args.clear();
	}
}

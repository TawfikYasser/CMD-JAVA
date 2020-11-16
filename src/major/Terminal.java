package major;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Date;

public class Terminal {
	
	public Terminal() {
		
		
		
	}
	
	public static void help() {

		
		
		System.out.println("For more information on a specific command, type args <command-name>"+"\n");
		System.out.println("cd:     Displays the name of or changes the current directory.");
		System.out.println("ls:     List information about the FILEs (the current directory by default).");
		System.out.println("cp:     Copies one or more files to another location.");
		System.out.println("cat:    Concatenate files and print on the standard output.");
		System.out.println("more:   Displays output one screen at a time.");
		System.out.println("pipe:   Use pipes “ | “ to redirect the output of the previous command as in input to another command.");
		System.out.println("mkdir:  Creates a directory with each given name.");
		System.out.println("rmdir:  Removes a directory with each given name.");
		System.out.println("mv:     Moves one or more files from one directory to another directory.");
		System.out.println("rm:     Removes each specified file. By default, it does not remove directories.");
		System.out.println(">:      Redirect the output to be written to a file using the redirect > create/replace file operator.");
		System.out.println(">>:     Redirect the output to be written to a file using the redirect >> create/append to file operator.");
		System.out.println("args:   List all parameters on the command line, numbers or strings for specific command.");
		System.out.println("date:   Diplay current date/time.");
		System.out.println("help:   Display all user commands with their definition.");
		System.out.println("pwd:    Display the current directory.");
	}
	

	
	
	
	public static void more(ArrayList<String> moreArgs) throws IOException {
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(moreArgs.get(0)));
			String line;
			while((line = in.readLine()) != null)
			{
			    System.out.println(line);
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public static void cp(ArrayList<String> cpArgs) {
		
		
		final String currentDirectory = System.getProperty("user.dir");
		//Setting the source and the destination
		String sourceFile = currentDirectory+"\\"+cpArgs.get(0);
		String targetPath = currentDirectory+"\\"+cpArgs.get(1);
		//Check if source exists or not
	    Path pathSource = Paths.get(sourceFile);
	    Path pathDestination = Paths.get(targetPath);
	    
	    if(Files.exists(pathSource)) {
	    	
	    	
	    	
	    	//Check for destination exists
	    	if(!Files.exists(pathDestination)) {
	    		//Does not exists
	    		
	    		if(cpArgs.get(1).contains("txt")) { // File

		    		File file = new File(pathDestination.toString());
		    		
		    		
		    		try {
		    			FileReader fr = new FileReader(pathSource.toString());
		    			BufferedReader br = new BufferedReader(fr);
		    			FileWriter fw = new FileWriter(cpArgs.get(1), true);
		    			String s;
		     
		    			while ((s = br.readLine()) != null) { // read a line
		    				fw.write(s); // write to output file
		    				fw.flush();
		    			}
		    			br.close();
		    			fw.close();
		                            System.out.println("file copied");
		    		} catch (IOException e) {
		    			// TODO Auto-generated catch block
		    			e.printStackTrace();
		    		}
	    		}else {
	    			mkdir(cpArgs.get(1));
	    			
			    	Path s = Paths.get(sourceFile);
					Path d = Paths.get(targetPath);
					
				    try {
				    	
						//Files.move(s, d.resolve(Paths.get(sourceFile).getFileName()));
						Files.copy(s, d.resolve(Paths.get(sourceFile).getFileName()));
						System.out.println("Source c to destination");

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}
	    		

	    	}else {
	    		//Exists
	    		
		    	Path s = Paths.get(sourceFile);
				Path d = Paths.get(targetPath);
				
			    try {
			    	
					//Files.move(s, d.resolve(Paths.get(sourceFile).getFileName()));
					Files.copy(s, d.resolve(Paths.get(sourceFile).getFileName()));
					System.out.println("Source c to destination");

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	
	    	
	    	
	    }else {
	    	System.out.println("Source file does not exists.");

	    }
	
		
		
	}
	
	public static void mv(ArrayList<String> mvArgs) {


					
		final String currentDirectory = System.getProperty("user.dir");
			
			
			
			//Setting the source and the destination
			String sourceFile = currentDirectory+"\\"+mvArgs.get(0);
			String targetPath = currentDirectory+"\\"+mvArgs.get(1);
			
			
			
			//Check if source exists or not
		    Path pathSource = Paths.get(sourceFile);
		    Path pathDestination = Paths.get(targetPath);
		    
		    
		    if(Files.exists(pathSource)) {
		    	
		    	
		    	//Check for destination exists
		    	if(!Files.exists(pathDestination)) {
		    		//Does not exists
		    		
			    	Path theDFile = Paths.get(sourceFile);
			    	try {
						Files.move(theDFile, theDFile.resolveSibling(mvArgs.get(1)), StandardCopyOption.REPLACE_EXISTING);
						System.out.println("The source renamed to "+ mvArgs.get(1));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	
			    	
		    	}else {
		    		//Exists
		    		
			    	Path s = Paths.get(sourceFile);
					Path d = Paths.get(targetPath);
					
				    try {
				    	
						Files.move(s, d.resolve(Paths.get(sourceFile).getFileName()));
						System.out.println("Source moved to destination");

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    	}
		    	

		    	
		    }else if(!Files.exists(pathSource)) {
		    	System.out.println("Source file does not exists.");
		    }
		
	
		
		
	  
	    
	}

	public static void clear() {

		try {	
			new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
		
	}
	
	public static void date() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date dateobj = new Date();
		System.out.println(df.format(dateobj));
	}
	
    public static void rm (String sourcepath)
    {
        File file=new File (sourcepath);
        if (!file.delete())
        {
            System.out.println("File can't be deleted : ");
        }
        else
            file.delete();
    }
    
    public static void rmdir (String sourcepath)
    {
        File file=new File (sourcepath);
        if (file.isDirectory())
        {

            /*
             * If directory is empty, then delete it
             */
            if (file.list().length == 0)
            {
                file.delete();
            }
            else
            {
                // list all the directory contents
                File files[] = file.listFiles();

                for (File fileDelete : files)
                {
                    /*
                     * Recursive delete
                     */
                    rmdir(fileDelete.getPath());
                }

                /*
                 * check the directory again, if empty then
                 * delete it.
                 */
                if (file.list().length == 0)
                {
                    file.delete();
                }
            }

        }
        else
        {
            /*
             * if file, then delete it
             */
            file.delete();
            System.out.println("File is deleted : " + file.getAbsolutePath());
        }
    }
	
    public static void pwd ()

    {
    	
    	 final String dir = System.getProperty("user.dir");
         System.out.println("Current directory: " + dir);

    }
    
	public static void cd(String path) {


		if(path.equals("..")) {
			final String currentDirectory = System.getProperty("user.dir");
	        //System.out.println("Current directory: " + currentDirectory);
			File f = new File(currentDirectory);
			String changedPath = f.getAbsoluteFile().getParent();
			System.setProperty("user.dir", changedPath.toString());
			//System.out.println(changedPath);
		}else {
			Path P = Paths.get(path);
			if(Files.exists(P)) {
				System.setProperty("user.dir", P.toString());
			}else {
				System.out.println("Not exists");

			}
		}

		
	}

    public static void ls() {

		final String currentDirectory = System.getProperty("user.dir");
		File directory = new File(currentDirectory);
		File[] subdirs = directory.listFiles();
		for (File dir : subdirs) {
			System.out.print(dir.getName()+"    ");
		}
		System.out.println("\n");
    }

	public static void mkdir(String name) {
		final String currentDirectory = System.getProperty("user.dir");
        System.out.println("Current directory: " + currentDirectory);
		Path p = Paths.get(currentDirectory+"\\"+name);
	      File file = new File(p.toString());
	      //Creating the directory
	       file.mkdir();
	       System.out.println("Success");
	}
    
	public static void args(String commmand) {

		if(commmand.equals("cd")) {
			System.out.println("arg: Destination Directory");
		}
		else if(commmand.equals("ls")) {
			System.out.println("Has no argument");
		}
		else if(commmand.equals("cp")) {
			System.out.println("arg1: Source Directory, arg2: Destination Directory");
		}
		else if(commmand.equals("cat")) {
			System.out.println("arg1: First File Name, arg2: Second File Name");
		}
		else if(commmand.equals("more")) {
			System.out.println("arg: File Name");
		}
		else if(commmand.equals("|")) {
			System.out.println("arg1: First Command, arg2: Second Command");
		}
		else if(commmand.equals(">")) {
			System.out.println("arg1: Command, arg2: File Name or Path");
		}
		else if(commmand.equals(">>")) {
			System.out.println("arg1: Command, arg2: File Name or Path");
		}
		else if(commmand.equals("mkdir")) {
			System.out.println("arg: Directory Name");
		}
		else if(commmand.equals("rmdir")) {
			System.out.println("arg: Directory Name");
		}
		else if(commmand.equals("mv")) {
			System.out.println("arg1: Source File,    arg2: Destination File");
		}
		else if(commmand.equals("rm")) {
			System.out.println("arg: File Name");
		}
		else if(commmand.equals("args")) {
			System.out.println("arg: Command Name");
		}
		else if(commmand.equals("date")) {
			System.out.println("Has no argument");
		}
		else if(commmand.equals("help")) {
			System.out.println("Has no argument");
		}
		else if(commmand.equals("pwd")) {
			System.out.println("Has no argument");
		}
		else if(commmand.equals("clear")) {
			System.out.println("Has no argument");
		}

		}
	
	
	
	

	
	
	
	public static void cat(ArrayList<String> catArgs) {
		
	
		
		
		final String currentDirectory = System.getProperty("user.dir");
		//Setting the source and the destination
		String sourceFile = currentDirectory+"\\"+catArgs.get(0);
		String targetPath = currentDirectory+"\\"+catArgs.get(1);
		//Check if source exists or not
	    Path pathSource = Paths.get(sourceFile);
	    Path pathDestination = Paths.get(targetPath);
	    
	    
	    
	    if(Files.exists(pathSource)) {
	    	
	    	
	    	if(Files.exists(pathDestination)) {
	    		
	    		
	    		if(sourceFile.contains("txt")&&targetPath.contains("txt")) {

		    		try {
		    			BufferedReader in = new BufferedReader(new FileReader(sourceFile.toString()));
		    			String line;
		    			while((line = in.readLine()) != null)
		    			{
		    			    System.out.println(line);
		    			}
		    			in = new BufferedReader(new FileReader(targetPath.toString()));
		    			while((line = in.readLine()) != null)
		    			{
		    			    System.out.println(line);
		    			}
		    			in.close();
		    			
		    		}catch (Exception e) {
						// TODO: handle exception
		    			e.printStackTrace();
					}
	    		}else if(sourceFile.contains("txt") && !targetPath.contains("txt")) {
		    		try {
		    			BufferedReader in = new BufferedReader(new FileReader(sourceFile.toString()));
		    			String line;
		    			while((line = in.readLine()) != null)
		    			{
		    			    System.out.println(line);
		    			}
		    			System.out.println("cat: "+targetPath+": Is a directory");
		    			in.close();
		    			
		    		}catch (Exception e) {
						// TODO: handle exception
		    			e.printStackTrace();
					}
	    		}else if(!sourceFile.contains("txt") && targetPath.contains("txt")) {
	    			System.out.println("cat: "+sourceFile+": Is a directory");

		    		try {
		    			BufferedReader in = new BufferedReader(new FileReader(targetPath.toString()));
		    			String line;
		    			while((line = in.readLine()) != null)
		    			{
		    			    System.out.println(line);
		    			}
		    			in.close();
		    			
		    		}catch (Exception e) {
						// TODO: handle exception
		    			e.printStackTrace();
					}
	    		}else {
	    			System.out.println("cat: "+sourceFile+": Is a directory");
	    			System.out.println("cat: "+targetPath+": Is a directory");

	    		}
	    			
	    	}else {
	    		//Destination not found
	    		if(sourceFile.contains("txt")) {
	    			
	        		try {
		    			BufferedReader in = new BufferedReader(new FileReader(sourceFile.toString()));
		    			String line;
		    			while((line = in.readLine()) != null)
		    			{
		    			    System.out.println(line);
		    			}
		    			System.out.println("cat: "+targetPath+": No such file or directory");
		    			in.close();
		    			
		    		}catch (Exception e) {
						// TODO: handle exception
		    			e.printStackTrace();
					}
	    			
	    		}else {
	    			System.out.println("cat: "+sourceFile+": Is a directory");

	    			System.out.println("cat: "+targetPath+": No such file or directory");

	    
	    		}
	    			
	    	}
	    	
	    	
	    }else {
	    	
	    	//source not found
	    	
	    	if(Files.exists(pathDestination)) {
	    		
	    		if(targetPath.contains("txt")) {
	    			System.out.println("cat: "+sourceFile+": No such file or directory");

	        		try {
		    			BufferedReader in = new BufferedReader(new FileReader(targetPath.toString()));
		    			String line;
		    			while((line = in.readLine()) != null)
		    			{
		    			    System.out.println(line);
		    			}
		    			in.close();
		    			
		    		}catch (Exception e) {
						// TODO: handle exception
		    			e.printStackTrace();
					}
	    		}else {
	    			
	    			System.out.println("cat: "+targetPath+": Is a directory");

	    			System.out.println("cat: "+sourceFile+": No such file or directory");

	    			
	    		}
	    		
	    	}else {
	    		
    			System.out.println("cat: "+sourceFile+": No such file or directory");
    			System.out.println("cat: "+targetPath+": No such file or directory");

	    		
	    	}
	    	
	    	
	    	
	    }
	}
	
	
	
	
	
	
	
	//For Redirect ***************************************
    

    public ArrayList pwdreturn ()
    {
        File file1=new File(".");
        File file2=new File("..");
        ArrayList <String> a1=new ArrayList<>();
        try {
            a1.add(file1.getCanonicalPath());

        } catch (Exception e) {
            System.out.println(e);
        }
        return a1;
    }
	
}

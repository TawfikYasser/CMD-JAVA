package major;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Terminal {
	
	
	public Terminal(ArrayList<String> pArgs) {
		
		
		
	}
	
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
	
	public static void mv(ArrayList<String> mvArgs) {
					
			//Getting the current directory
			Path currentRelativePath = Paths.get("");
			String currentDirectory = currentRelativePath.toAbsolutePath().toString();
			
			
			
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
	

	
    public void rm (String sourcepath)
    {
        File file=new File (sourcepath);
        if (!file.delete())
        {
            System.out.println("File can't be deleted : ");
        }
        else
            file.delete();
    }
    
    
    public void rmdir (String sourcepath)
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
	
    
    public void pwd ()
    {
        File file1=new File(".");
        File file2=new File("..");
        try {
            System.out.println("Current dir : " + file1.getCanonicalPath());
            System.out.println("Parent  dir : " + file2.getCanonicalPath());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    
    //for redired***************************************
    

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

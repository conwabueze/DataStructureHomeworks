

import java.util.Scanner;

public class BashTerminal {
	/*
	 * this main method gets command and excutes
	 * !!!!!!!!!!!!!!!!ALL INPUT VALUES STAY TRUE TO HW TABLES SO I INCLUDED THE CURLY BRACES INCLUDED IN SOME COMMANDS!!!!!!!!!!!
	 * 
	 * */
	public static void main(String[] args) throws IllegalArgumentException, FullDirectoryException, NotADirectoryException {
		boolean terminate = false;
		DirectoryTree tree= new DirectoryTree();
		
		System.out.println("Starting bash terminal.");
		String command = "";
		while(!command.equals("exit")) {
			Scanner input = new Scanner(System.in);
			System.out.print("[110352750@host]: $ ");
			command = input.nextLine();
			
			//create new directory
			if(command.contains("mkdir")) {
				int indexOfOpenBrace = command.indexOf("{");
				String name = command.substring(indexOfOpenBrace+1, command.length()-1);
				tree.makeDirectory(name);
				System.out.println("directiry created");
			}
			//create a new file
			else if(command.contains("touch")){
				int indexOfOpenBrace = command.indexOf("{");
				String name = command.substring(indexOfOpenBrace+1, command.length()-1);
				tree.makeFile(name);
				System.out.println("file created");

			}
			
			//changeDirectory
			else if(command.contains("cd")) {
				if(command.contains("/"))
					tree.resetCursor();
				else {
					int indexOfOpenBrace = command.indexOf("{");
					String name = command.substring(indexOfOpenBrace+1, command.length()-1);
					tree.changeDirectory(name);
				}
			}
			
			//present working directory
			else if(command.equals("pwd")) {
				System.out.println(tree.presentWorkingDirectory());
			}
			
			//list directory
			else if(command.equals("ls"))
				System.out.println(tree.listDirectory());
			
			else if(command.equals("ls -R"))
				tree.printDirectoryTree();
			
			else
				throw new IllegalArgumentException("Invalid value");
			
		}
	
//		tree.makeDirectory("root");
//		//System.out.println(tree);
//		tree.makeDirectory("dev");
//		tree.makeDirectory("home");
//		tree.makeDirectory("bin");
//		tree.changeDirectory("bin");
	}

}

import java.io.File;
import java.util.Scanner;



/*
 * This class is for reading the file and determining the complexity 
 */
public class PythonTracer {
	//variable for the space requirement of file
	public static final int SPACE_COUNT = 4;
	
	/**
	 * this method reads and anaylizes the file to determine the worst Complexity of it
	 * @param fileName, name of file to read
	 * @return Complexity, worst complexity of file
	 */
	public static Complexity TraceFile(String fileName) throws Exception {
		fileName += ".py";
		File file = new File("C:\\Users\\chino\\Desktop\\" + fileName);

		// block you to keep track of complexity
		BlockStack stack = new BlockStack();
		Complexity complex = new Complexity();
		Scanner input = new Scanner(file);

		int blockCount = 1;
		String blockCountStr = ""+blockCount;
		String blockName  ="BLOCK "+ blockCountStr+":";
		
		while (input.hasNextLine()) {
			String fileLine = input.nextLine();
			// if line is empty or a comment ignore
			if (fileLine.indexOf("#") != -1 || fileLine.isEmpty()) {

			}
			// where the magic happens
			else {
				// block to insert into BlockStack
				CodeBlock block = new CodeBlock();
				//indent count of line
				int indentCount = indentCounter(fileLine);
				// search if word contains keyword from block type word bank
				String keyword = "";
				for (int i = 0; i < block.BLOCK_TYPE.length; i++) {
					// if found hold it in a variable
					if (fileLine.indexOf(block.BLOCK_TYPE[i]) != -1)
						keyword = block.BLOCK_TYPE[i];
				}

				// if word is found check if its a O(1) type
				if (block.O1_LIST.contains(keyword)) {
					// create O(1) complexity and push it into the stack
					block.setBlockComplexity(new Complexity(0, 0));
					block.setHighestSubComplexity(new Complexity(0, 0));
					// if stack size is = 0 or indents = stack size push it in
					if (stack.size() == 0) {
						stack.push(block);
						System.out.println("Entering block '"+keyword+"'");
						System.out.println(block);
						System.out.println();
					} 
					else if(indentCount == stack.size()) {
						stack.push(block);
						blockCountStr+="."+blockCountStr;
						System.out.println("Entering block '"+keyword+"'");
						System.out.println(block);
						System.out.println();
						
					}
					///else make room for block in stack
					else {
						
						complexityPush(block, stack, fileLine);
						blockCount++;
						blockCountStr = "1."+blockCount;
						System.out.println("Entering block '"+keyword+"'");
						System.out.println(block);
						System.out.println();
					}
				}
				// else check is it either "for" or "while"
				else {
					// check if word is "for"
					if (keyword.equals("for")) {
						// figure out what indentation you are on
						

						// logic for get big O
						String[] cut = fileLine.split(" ");
						if (cut[cut.length - 1].equals("N:")) {
							block.setBlockComplexity(new Complexity(1, 0));
							block.setHighestSubComplexity(new Complexity(0, 0));
							if (stack.size() == 0) {
								stack.push(block);
								System.out.println("Entering block '"+keyword+"'");
								System.out.println(block);
								System.out.println();
							}
							else if(indentCount == stack.size()) {
								stack.push(block);
								blockCountStr+="."+blockCountStr;
								System.out.println("Entering block '"+keyword+"'");
								System.out.println(block);
								System.out.println();
							}
							else {
								complexityPush(block, stack, fileLine);
								blockCount++;
								blockCountStr = "1."+blockCount;
								System.out.println("Entering block '"+keyword+"'");
								System.out.println(block);
								System.out.println();
							}

						} else if (cut[cut.length - 1].equals("log_N:")) {
							block.setBlockComplexity(new Complexity(0, 1));
							block.setHighestSubComplexity(new Complexity(0, 0));
							if (stack.size() == 0) {
								stack.push(block);
								System.out.println("Entering block '"+keyword+"'");
								System.out.println(block);
								System.out.println();
							}
							else if(indentCount == stack.size()) {
								stack.push(block);
								blockCountStr+="."+blockCountStr;
								System.out.println("Entering block '"+keyword+"'");
								System.out.println(block);
								System.out.println();
							}
							else {
								complexityPush(block, stack, fileLine);
								blockCount++;
								blockCountStr = "1."+blockCount;
								System.out.println("Entering block '"+keyword+"'");
								System.out.println(block);
								System.out.println();
							}

							
						
						}
						
						

					}
					// check if word is "while"
					else if (keyword.equals("while")) {
						block.setBlockComplexity(new Complexity(0, 0));
						block.setHighestSubComplexity(new Complexity(0, 0));
						if (stack.size() == 0) {
							stack.push(block);
							System.out.println("Entering block '"+keyword+"'");
							System.out.println(block);
							System.out.println();
						}
						else if(indentCount == stack.size()) {
							stack.push(block);
							blockCountStr+="."+blockCountStr;
							System.out.println("Entering block '"+keyword+"'");
							System.out.println(block);
							System.out.println();
						}
						else {
							complexityPush(block, stack, fileLine);
							blockCount++;
							blockCountStr = "1."+blockCount;
							System.out.println("Entering block '"+keyword+"'");
							System.out.println(block);
							System.out.println();
						}
						while(input.hasNextLine()) {
							fileLine = input.nextLine();
							if (fileLine.indexOf("#") != -1 || fileLine.isEmpty()) {
								
							}
							else {
								if(indentCounter(fileLine)!=stack.size()) {
									break;
								}
									
								else {
									if(fileLine.contains("/=")) {
										stack.getHead().getBlock().setBlockComplexity(new Complexity(0, 1));
										System.out.println("Found update statement, updating '"+keyword+"'");
										System.out.println(block);
										System.out.println();
									}
									else if(fileLine.contains("-=")) {
										stack.getHead().getBlock().setBlockComplexity(new Complexity(1, 0));
										System.out.println("Found update statement, updating '"+keyword+"'");
										System.out.println(block);
										System.out.println();
									}
								}
							}
						}
						
						
					}
				}
			}	
		}
		
		stack.getComplexity();
		System.out.println("Overall complexity of test_function: "+ stack.getBlockComplexity());
		return stack.getBlockComplexity();
	}

	/**
	 * this method determines the indent count of a String
	 * @param str, line of to read
	 * @return int, number of indents
	 */
	private static int indentCounter(String str) {
		if (str.charAt(0) == ' ') {
			int spaceCount = 0;
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == ' ')
					spaceCount++;
				else if(str.charAt(i) != ' ')
					break;
			}
			return spaceCount / SPACE_COUNT;
		}
		return 0;
	}

	/**
	 * this method determines the indent count of a String
	 * @param block, CodeBlock to push
	 * @param stack, Stack to push CodeBlock to
	 * @param fileLine, line to determine indent and how to deal with it from there
	 * @return int, number of indents
	 */
	private static void complexityPush(CodeBlock block, BlockStack stack, String fileLine) throws Exception {
		int indentCount = indentCounter(fileLine);
		if (stack.size() == 0 || indentCount == stack.size()) {
			stack.push(block);
		} 
		///else make room for block in stack
		else {

			//////////// 1.insert logic for if its less then we have an error exit out
			//////////// program!!!!!!//////////
			if(indentCount > stack.size()) {
				System.exit(1);
				throw new Exception("ERROR IN FILE");				
			}
				
			
			////////////// 2.insert logic to traverse stack and insert the high complexity
			////////////// to the end of the stack
			stack.getComplexity();
			/*
			 * after this you proceed to pop everything except the last to make room for the
			 * next block
			 */
			// if size of stack is greater then the index pop the stack until there equal
			while (stack.size() > indentCount) {
				stack.pop();
				
				
			}
			
			
			// when equal push to stack
			stack.push(block);
		}
	}
	
	//MAIN METHOD
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter a file name (or 'quit' to quit): ");
		String fileName = input.nextLine().toLowerCase();
		if(fileName.equals("quit")) {
			System.out.println("GOOD BYE");
			System.exit(1);
		}else {
			TraceFile(fileName);
		}
		while(!fileName.equals("quit")) {
			System.out.println("\nPlease enter a file name (or 'quit' to quit): ");
			fileName = input.nextLine().toLowerCase();
			if(fileName.equals("quit")) {
				System.out.println("GOOD BYE");
				System.exit(1);
			}else {
				TraceFile(fileName);
			}
		}
	}

}

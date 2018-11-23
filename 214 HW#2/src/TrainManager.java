
import java.util.Scanner;

public class TrainManager {

	public static void main(String[] args) {
		TrainLinkedList list = new TrainLinkedList();
		Scanner input = new Scanner(System.in);
		String menu = "(F) Move Cursor Forward\r\n" + "(B) Move Cursor Backward\r\n" + "(I) Insert Car After Cursor\r\n"
				+ "(R) Remove Car At Cursor \r\n" + "(L) Set Load At Cursor\r\n" + "(S) Search For Product\r\n"
				+ "(T) Print Train \r\n" + "(M) Print Manifest\r\n" + "(D) Remove Dangerous Cars\r\n"
				+ "(Q) Quit";
		String selection = "";
		System.out.println(menu);
		while(selection != "Q") {
			selection = input.nextLine().toUpperCase();
			System.out.println(menu);
			try {
				switch(selection) {
					case"F":
						list.cursorForward();
						System.out.println("Cursor moved forward");
						break;
					case"B":
						list.cursorBackward();
						System.out.println("Cursor moved backwards");
						break;
					case"I":
						System.out.println("Enter car length");
						double carLength = input.nextDouble();
						System.out.println("Enter car weight");
						double carWeight = input.nextDouble();
						ProductLoad load = new ProductLoad();
						TrainCar car = new TrainCar(carLength, carWeight, load);
						list.insertAfterCursor(car);
						break;
					case"R":
						list.removeCursor();
						System.out.println("Node removed");
						break;
					case"L":
						ProductLoad newLoad;
						System.out.println("Enter load name: ");
						String loadName = input.nextLine();
						System.out.println("Enter load weight");
						double loadWeight = input.nextDouble();
						System.out.println("Enter load value");
						double loadValue = input.nextDouble();
					
						System.out.println("is this load dangerous? (1 for yes, 0 for no)");
						int yesNo = input.nextInt();
						boolean danger = false;
						if(yesNo==1) 
							danger = true;
						newLoad = new ProductLoad(loadName, loadWeight, loadValue, danger);
						
						list.getCursorData().setLoad(newLoad);
						break;
					case "S":
						System.out.println("Enter name to search for: ");
						String searchName =input.nextLine();
						list.findProduct(searchName);
						break;
					case "T":
						System.out.println(list.toString());
						break;
					case "M":
						list.printManifest();
						break;
					case "D":
						list.removeDangerousCars();
						System.out.println("Cars removed");
						break;
					case "Q":
						System.out.println("TERMINATED");
						System.exit(1);
						break;
					default:
						System.out.println(menu);
						
				}
			}catch(Exception e) {
				
			}
	}
	}		
}

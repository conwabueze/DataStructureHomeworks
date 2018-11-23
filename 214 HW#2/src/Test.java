
public class Test {
	public static void main(String[] args) {
		TrainLinkedList list = new TrainLinkedList();
		
		//1
		ProductLoad load1 = new ProductLoad("Marco", 212, 1, true);
		TrainCar car1 = new TrainCar(23,23,load1);
		//1 copy
		ProductLoad load11 = new ProductLoad("Marco", 200, 2, true);
		TrainCar car11 = new TrainCar(20,20,load11);
		//2
		ProductLoad load2 = new ProductLoad("Polo", 894843, 2, false);
		TrainCar car2 = new TrainCar(56,24,load2);
		//3
		ProductLoad load3 = new ProductLoad("Tommy", 894, 3, false);
		TrainCar car3 = new TrainCar(5633,254,load3);
		//4
		ProductLoad load4 = new ProductLoad("The North Face", 8, 7, true);
		TrainCar car4 = new TrainCar(777,24,load4);
		
		try {
		//initialize head
			
		list.insertAfterCursor(car1);
		list.insertAfterCursor(car2);
		list.cursorForward();
		list.insertAfterCursor(car11);
		list.cursorForward();
		list.insertAfterCursor(car3);
		list.cursorForward();
		list.insertAfterCursor(car4);
		list.cursorForward();
		list.printManifest();
//		list.removeCursor();
		list.removeDangerousCars();
		list.printManifest();
		
//		System.out.println("size:" + list.size() + " total weight: " + list.getWeight() + " total value: " 
//						+ list.getValue() + " total length: " + list.getLength() + " isDangerous: " + list.isDangerous());
//		list.findProduct("Marco");
		//list.printManifest();
		
		//list.removeDangerousCars();
		//list.printManifest();
//		
//		System.out.println("Removed ");
//		System.out.print(list.removeCursor());
//		System.out.println("\nAltered List");
//		list.printOut();
//		System.out.print(list.removeCursor());
//		System.out.println("\nAltered List");
//		list.printOut();
		
		}
		catch(Exception ex) {
			System.out.println(ex);
		}
		
	}
}

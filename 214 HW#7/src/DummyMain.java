import java.io.FileNotFoundException;

public class DummyMain {

	public static void main(String[] args) throws IllegalArgumentException, FileNotFoundException {
		WebGraph graph = new WebGraph();
		graph = graph.buildFromFiles("pages.txt", "links.txt");
		graph.printTable();
		graph.searchKeyword("social");
	}

}

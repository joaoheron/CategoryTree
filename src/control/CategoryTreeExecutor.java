package control;

public class CategoryTreeExecutor {
	
	private static Controller controller;
	
	public static void main (String args[]) throws Exception  {
	    
		controller = new Controller();
		controller.readFile(controller.welcome());
		controller.detectOsPath(System.getProperty("os.name"));
		controller.writeFile(controller.parse());

		System.out.println("\nThanks for using Products Converter.");
	}

}

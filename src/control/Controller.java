package control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import model.ProductNew;
import model.ProductOld;
import model.ProductNew.Tree;
import model.ProductOld.ProductCategory;
import parse.Parser;


public class Controller {
	
	private String defaultFileInputLocation;
	private String defaultFileOutputLocation; 
	private List<String> lines;
	private List<ProductOld> oldProducts;
	private List<ProductNew> newProducts;
	private Scanner scanner;
	private Parser parser;
	private Gson g;
	public static final String WIN = "WIN";
	public static final String MAC = "MAC";
	public static final String UNIX = "UNIX";

	public Controller() {
		this.defaultFileInputLocation = "res/products";
		this.defaultFileOutputLocation = System.getProperty("user.home") + "\\Desktop\\convertedProducts";
		this.lines = new ArrayList<String>();
		this.oldProducts = new ArrayList<ProductOld>();
		this.newProducts = new ArrayList<ProductNew>();
		this.g = new Gson();
	}
	
	
	public String welcome() {
		System.out.println("\n@@@@@ Welcome to Products Converter. @@@@@"
				+ "\n##### Default products list will be converted if filepath is invalid. #####"
				+ "\n>>>>> Type filepath input <<<<<");
		Scanner inputReader = new Scanner(System.in);
		String fileLocation = inputReader.next();
		
		if (inputReader != null) {
			inputReader.close();
		}
		
		return fileLocation;
	}
	
	public List<ProductOld> readFile(String fileLocation) throws Exception {
		// Reading file
		try  {
			scanner = new Scanner(new FileReader(fileLocation));
			while (scanner.hasNextLine()) {
				// each line contains a product
				lines.add(scanner.nextLine());
			}
		}
		catch (Exception e) {
			System.out.println("\nInvalid path. Converting default products list instead");
			readDefaultFile();
			
			// returns default old products list
			return oldProducts;
		}
		// Converting file 
		try {
			for (String line: lines) {
				oldProducts.add(g.fromJson(line, ProductOld.class));
			}
			// returns *fileLocation parameter* old products list
			return oldProducts;
		} 
		catch (Exception e) {
			System.out.println("\nInvalid file. Converting default products list instead");
			readDefaultFile();
			// returns default old products list 
			return oldProducts;
		}
		
	}
	
	private void readDefaultFile() throws Exception {
		try {
			scanner = new Scanner(new FileReader(defaultFileInputLocation));
			lines = new ArrayList<>();
			oldProducts = new ArrayList<>();
			
			// Reading file
			while (scanner.hasNextLine()) {
				// each line contains a product
				lines.add(scanner.nextLine());
			}
			Gson g = new Gson();
			// Converting file
			for (String line: lines) {
				oldProducts.add(g.fromJson(line, ProductOld.class));
			}
		} catch (Exception e) {
			System.out.println("\nProcessing error.");
		}
	}

	public List<ProductNew> parse() {
		parser = new Parser();
		newProducts = parser.oldToNew(oldProducts);
		
		return newProducts;
	}
	
	// Checking OS properties to define output file location
	public String detectOsPath(String os) {
		if (os==null) {
			return null;
		}
		if (os.contains("win") || os.contains("Win") || os.contains("WIN")) {
			this.defaultFileOutputLocation = System.getProperty("user.home") + "\\Desktop\\convertedProducts";
			
			return WIN;
		}
		if (os.contains("mac") || os.contains("Mac") || os.contains("MAC") || os.contains("IOS") || os.contains("iOS")) {
			this.defaultFileOutputLocation = System.getProperty("user.home") + "/Desktop/convertedProducts";
			
			return MAC;
		}
		if (os.contains("nix") || os.contains("aix") || os.contains("nux") || os.contains("unt") || os.contains("UNT") || os.contains("sunos")) {
			this.defaultFileOutputLocation = System.getProperty("user.home") + "/Desktop/convertedProducts";
			
			return UNIX;
		}
		return null;
	}
	
	public Object writeFile(List<ProductNew> newProducts) throws Exception {
		if (newProducts == null) {
			return null;
		}
		FileOutputStream fileOut;
		System.out.println("\n#### The file will be saved in ####\n" + defaultFileOutputLocation);
		try {
			File file = new File(defaultFileOutputLocation);
			if (!file.exists()) {
				file.createNewFile();
			}
			fileOut = new FileOutputStream(file); 
			
			// Writing file
			for (ProductNew newProd: newProducts) {
				fileOut.write((g.toJson(newProd).toString()+"\n").getBytes());
			}
			fileOut.close();
			System.out.println("\nFile created!");

			return fileOut;
		} catch (Exception e) {
			System.out.println("\nProcessing error.");
			return null;
		}
		finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}
}

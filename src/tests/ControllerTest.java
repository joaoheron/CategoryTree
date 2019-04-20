package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Test;

import control.Controller;
import model.ProductNew;
import model.ProductOld;
import model.ProductNew.Tree;

import static org.hamcrest.CoreMatchers.*;

public class ControllerTest {
	
	public ControllerTest() {
	}
	
	// Tests readfile() 
	@Test
	public void readFileEmptyLocationTest() throws Exception {
		Controller controller = new Controller();
		List<ProductOld> oldProducts = controller.readFile(null);
		
		assertEquals(oldProducts.size(), 100);
	}
	
	@Test
	public void readFileInvalidLocationTest() throws Exception {
		Controller controller = new Controller();
		List<ProductOld> oldProducts = controller.readFile("!@#/invalid/location\\file");
		
		assertEquals(oldProducts.size(), 100);
	}
	
	@Test
	public void readInvalidFileTest() throws Exception {
		Controller controller = new Controller();
		List<ProductOld> oldProducts = controller.readFile("res/invalidProducts");
		
		assertEquals(oldProducts.size(), 100);
	}
	
	@Test
	public void readOtherFileTest() throws Exception {
		Controller controller = new Controller();
		List<ProductOld> oldProducts = controller.readFile("res/products2");
		
		assertEquals(oldProducts.size(), 10);
	}
	
	@Test
	public void readNormalFileTest() throws Exception {
		Controller controller = new Controller();
		List<ProductOld> oldProducts = controller.readFile("res/products");
		
		assertEquals(oldProducts.size(), 100);
	}
	
	@Test
	public void readAnotherFileTest() throws Exception {
		Controller controller = new Controller();
		List<ProductOld> oldProducts = controller.readFile("res/products3");
		
		assertEquals(oldProducts.size(), 3);
	}
	
	//Tests detectOsPath()
	@Test
	public void detectWinPathTest() {
		Controller controller = new Controller();
		String win = controller.detectOsPath("windows 10");
		
		assertEquals(win, controller.WIN);
	}
	
	@Test
	public void detectUnixPathTest() {
		Controller controller = new Controller();
		String mac = controller.detectOsPath("MACBOOK PRO SLIM FINISSIMO");
		
		assertEquals(mac, controller.MAC);
	}
	
	@Test
	public void detectIosPathTest() {
		Controller controller = new Controller();
		String unix = controller.detectOsPath("Linux Ubuntu 18.02");
		
		assertEquals(unix, controller.UNIX);
	}
	
	@Test
	public void detectNullPathTest() {
		Controller controller = new Controller();
		String os = controller.detectOsPath(null);
		
		assertNull(os);
	}
	
	@Test
	public void detectEmptyPathTest() {
		Controller controller = new Controller();
		String os = controller.detectOsPath("");
		
		assertNull(os);
	}
	
	@Test
	public void detectInvalidPathTest() {
		Controller controller = new Controller();
		String os = controller.detectOsPath("9$23%(");
		
		assertNull(os);
	}
	
	// Tests writeFile()
	@Test
	public void writeFileNullProductsTest() throws Exception {
		Controller controller = new Controller();
		Object obj = controller.writeFile(null);
		
		assertNull(obj);
	}
	
	@Test
	public void writeFileEmptyProductsTest() throws Exception {
		Controller controller = new Controller();
		Object fileOut = controller.writeFile(new ArrayList<>());
		
		assertThat(fileOut, instanceOf(FileOutputStream.class));
	}
	
	@Test
	public void writeFileNormalProductsTest() throws Exception {
		Controller controller = new Controller();
		// new products
		List<Tree> treeList = new ArrayList<>();
		Tree t = new Tree("casas", new ArrayList<>());
		treeList.add(t);
		
		List<ProductNew> newProducts = new ArrayList<>();
		ProductNew productNew = new ProductNew("casinha", treeList);
		newProducts.add(productNew);
		
		Object fileOut = controller.writeFile(newProducts);
		
		assertThat(fileOut, instanceOf(FileOutputStream.class));
	}
}

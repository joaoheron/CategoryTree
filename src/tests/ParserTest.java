package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.ProductNew;
import model.ProductNew.Tree;
import model.ProductOld;
import model.ProductOld.ProductCategory;
import parse.Parser;

public class ParserTest {

	private Parser parser;
	
	public ParserTest() {
		this.parser = new Parser();
	}
	
	// Tests oldToNew() 
	@Test
	public void oldNullToNewTest() {
		List<ProductNew> newProducts = parser.oldToNew(null);
		
		assertEquals(newProducts, new ArrayList<ProductNew>());
	}
	
	@Test
	public void oldEmptyToNewTest() {
		List<ProductOld> oldProducts = new ArrayList<>();
		List<ProductNew> newProducts = parser.oldToNew(oldProducts);
		
		assertEquals(newProducts, new ArrayList<ProductNew>());
	}
	
	@Test
	public void oldNormalToNewTest() {
		// old products
		List<ProductCategory> productCategories = new ArrayList<>();
		ProductCategory pc = new ProductCategory("casas", new ArrayList<>());
		productCategories.add(pc);
		
		List<ProductOld> oldProducts = new ArrayList<>();
		ProductOld po = new ProductOld("casinha", productCategories);
		oldProducts.add(po);
		// new products
		List<Tree> treeList = new ArrayList<>();
		Tree t = new Tree("casas", new ArrayList<>());
		treeList.add(t);
		
		List<ProductNew> newProducts = new ArrayList<>();
		ProductNew productNew = new ProductNew("casinha", treeList);
		newProducts.add(productNew);
		
		List<ProductNew> newProductsParsed = parser.oldToNew(oldProducts);
		
		assertEquals(newProducts.get(0).productName, newProductsParsed.get(0).productName);
		assertEquals(newProducts.get(0).categoryTree.get(0).category, newProductsParsed.get(0).categoryTree.get(0).category);
		assertEquals(newProducts.get(0).categoryTree.get(0).children, newProductsParsed.get(0).categoryTree.get(0).children);
	}
	
	// Tests populateChildren()
	@Test
	public void populateChildrenTest() {
		Tree tree = new Tree("", new ArrayList<>());
		List <ProductCategory> pcs = new ArrayList<>();
		
		Tree treePopulate = parser.populateChildren(tree, pcs);
		
		assertEquals(treePopulate, tree);
	}
	
}

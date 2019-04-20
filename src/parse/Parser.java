package parse;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import model.ProductNew;
import model.ProductOld;
import model.ProductNew.Tree;
import model.ProductOld.ProductCategory;

public class Parser {
	
	public List<ProductNew> oldToNew(List<ProductOld> oldProducts) {
		System.out.println("\nConverting products...");
		List<ProductNew> newProducts = new ArrayList<ProductNew>();
		if (oldProducts != null && !oldProducts.isEmpty()) {
			newProducts = oldProducts.stream()
					.map(convertProductsFunction)
					.collect(Collectors.<ProductNew> toList());
			
			System.out.println("\nCongratulations! Products converted!");
		}
		else {
			System.out.println("\nList is empty or null.");
		}
		
		return newProducts;
	}

	// Função que converte a lista de estruturas de produto antiga para a nova lista de estruturas de produto
	private Function<ProductOld, ProductNew> convertProductsFunction = new Function<ProductOld, ProductNew>() {
	    public ProductNew apply(ProductOld o) {
	    	ProductNew n = new ProductNew();
	    	List<Tree> treeList = new ArrayList<>();
	    	
	    	// Iterando por cada elemento da lista de ProductCategory
	    	for(ProductCategory pcHEAD: o.productCategories) {
	    		// Verificando se a categoria é um HEAD, ou seja nao tem parents
	    		if ( pcHEAD.parents == null || pcHEAD.parents.size()==0) { 
	    			treeList.add(populateChildren(new Tree(pcHEAD.id, new ArrayList<Tree>()), o.productCategories));
	    		}
	    	}
	    	n = new ProductNew(o.productName, treeList);
	        return n;
	    }
	};
	
	public Tree populateChildren(Tree t, List <ProductCategory> pcs) {
		// Iterando por cada elemento da lista de ProductCategory
		for(ProductCategory categoryOld: pcs) {  
			
			// Verificando se o parents<> nao é null (existem 2 casos de parents null no documento)
			if (categoryOld.parents != null) {
				
				// Iterando por cada parent de cada elemento da lista de ProductCategory
				for (String parent: categoryOld.parents) {
					
					// Caso o valor do parent da categoria antiga seja igual ao valor da categoria atual, o valor do filho da categoria atual será igual ao valor da categoria antiga
					if (parent.equals(t.category)) {
						
						// Método invocado de forma recursiva para processar todos niveis da arvore
						t.children.add(populateChildren(new Tree(categoryOld.id, new ArrayList<Tree>()), pcs));
					}
				}
			}
			
		}
		return t;
	}
}

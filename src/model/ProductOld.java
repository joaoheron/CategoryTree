package model;

import java.util.List;

public class ProductOld {
	public String productName;
	public List <ProductCategory> productCategories;
	
	public ProductOld() {
	}
	
	public ProductOld(String productName, List <ProductCategory> productCategories) {
		this.productName = productName;
		this.productCategories = productCategories;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List <ProductCategory> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(List <ProductCategory> productCategories) {
		this.productCategories = productCategories;
	}
	
	public static class ProductCategory {
		public String id;
		public List<String> parents;

		public ProductCategory(String id, List<String> parents) {
			this.id = id;
			this.parents = parents;
		}
		
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public List<String> getParents() {
			return parents;
		}

		public void setParents(List<String>parents) {
			this.parents = parents;
		}

	}
} 

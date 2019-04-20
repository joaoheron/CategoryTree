package model;

import java.util.List;

public class ProductNew {
	public String productName;
	public List<Tree> categoryTree;
	
	public ProductNew() {
	}
	
	public ProductNew(String productName, List<Tree> categoryTree) {
		this.productName = productName;
		this.categoryTree = categoryTree;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public List<Tree> getCategoryTree() {
		return categoryTree;
	}

	public void setCategoryTree(List<Tree> categoryTree) {
		this.categoryTree = categoryTree;
	}

	public static class Tree {
		public String category;
		public List<Tree> children;
		
		public Tree(String category, List<Tree> children) {
			this.category = category;
			this.children = children;
		}
		
		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public List<Tree> getChildren() {
			return children;
		}

		public void setChildren(List<Tree> children) { 
			this.children = children;
		}

	}

}

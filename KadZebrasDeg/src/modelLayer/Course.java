package modelLayer;

public class Course extends Merchandise {
	private String ingredients;
	private boolean isVegetarian;
	private String typeOfCourse;


	// constructor
	public Course() {

	}
	
	public String getTypeOfCourse() {
		return typeOfCourse;
	}

	public void setTypeOfCourse(String typeOfCourse) {
		this.typeOfCourse = typeOfCourse;
	}

	public void setVegetarian(boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}

	// sets
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public void setIsVegetarian(boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}

	// gets
	public String getIngredients() {
		return ingredients;
	}

	public boolean getIsVegetarian() {
		return isVegetarian;
	}
}

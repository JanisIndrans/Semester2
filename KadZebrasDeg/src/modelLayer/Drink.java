package modelLayer;

public class Drink extends Merchandise {
	private int quantityInStock;
	private double alcoholConcentration;
	public int minQuantityInStock;

	// constructor
	public Drink() {

	}

	// sets
	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public void setAlcoholConcetration(double alcoholConcentration) {
		this.alcoholConcentration = alcoholConcentration;
	}

	public void setMinQuantityInStock(int minQuantityInStock) {
		this.minQuantityInStock = minQuantityInStock;
	}

	// gets
	public int getQuantityInStock() {
		return quantityInStock;
	}

	public double getAlcoholConcetration() {
		return alcoholConcentration;
	}

	public int getMinQuantityInStock() {
		return minQuantityInStock;
	}
}

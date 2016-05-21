package dbLayer;

import java.util.ArrayList;

import modelLayer.Drink;
import exceptionsLayer.DatabaseException;

public interface IFDBDrink {
	// create one
	public int insertDrink(Drink drink) throws DatabaseException;

	// read all
	public ArrayList<Drink> getAllDrinks();

	// read one
	public Drink findDrink(String name) throws DatabaseException;

	// update one
	public int updateDrink(String name, Drink drink) throws DatabaseException;
}

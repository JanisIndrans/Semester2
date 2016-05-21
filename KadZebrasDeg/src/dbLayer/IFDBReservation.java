package dbLayer;

import java.util.ArrayList;

import modelLayer.Reservation;
import exceptionsLayer.DatabaseException;

public interface IFDBReservation {
	// create one
	public int insertReservation(Reservation reservation)
			throws DatabaseException;

	// read all
	public ArrayList<Reservation> getAllReservations();

	// read one
	public Reservation findReservation(int id) throws DatabaseException;

	// update one
	public int updateReservation(String name, Reservation reservation)
			throws DatabaseException;

	Reservation findReservationByName(String name) throws DatabaseException;
}

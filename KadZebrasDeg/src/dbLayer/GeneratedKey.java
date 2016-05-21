package dbLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GeneratedKey {

	/**
	 * To be callable, the passed Statement object must be executed with the
	 * option Statement.RETURN_GENERATED_KEYS.<br>
	 * E.g.: Strtring s = "insert into...";<br>
	 * E.g. s.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);<br>
	 * 
	 * @param insertStatement
	 * @return
	 */
	public int getGeneratedKey(Statement insertStatement) {
		int res = -1;
		try (ResultSet rs = insertStatement.getGeneratedKeys()) {
			if (rs.next()) {
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}

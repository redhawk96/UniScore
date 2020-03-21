/*
 * Institute	: SLIIT
 * Module		: Comparative Integrated Systems
 * Project Name	: UniScore
 * Project		: Online Examination Management System
 * Group		: 19
 * Author		: Subarshan Thiyagarajah (UOB-1939088)
 */

package connectors;

import java.sql.SQLException;
import java.util.List;

public interface ConnectorInterface<T> {

	public boolean add(T type) throws ClassNotFoundException, SQLException;

	public boolean update(T type) throws ClassNotFoundException, SQLException;

	public boolean remove(T type) throws ClassNotFoundException, SQLException;

	public T get(T type) throws ClassNotFoundException, SQLException;

	public List<T> getAll() throws ClassNotFoundException, SQLException;

}

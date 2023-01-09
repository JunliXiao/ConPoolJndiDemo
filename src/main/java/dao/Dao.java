package dao;

import java.util.List;

public interface Dao<T> {

	List<T> getAll();

	T get(String id);

	boolean add(T obj);

	boolean update(T obj);

	boolean delete(String id);

}

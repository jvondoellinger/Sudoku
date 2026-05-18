package app.entity;

public interface Rule<T> {
	/**
	 * @return Return true se foi possivel aplicar a regra e false caso haja alguma violação!
	 */
	boolean apply();
}

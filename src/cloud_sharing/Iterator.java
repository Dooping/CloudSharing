package cloud_sharing;

/**
 * Iterador
 * @author David Gago 41361 / Manuel Duarte 39443
 */
public interface Iterator {
	
	/**
	* Vai para o início da colecção
	*/
	public void init();
	
	/**
	* Verifica se existe mais algum elemento a visitar
	* @return true, se houver mais elementos a visitar, false, caso contrário
	*/
	public boolean hasNext();
	
	/**
	* Devolve o próximo elemento a visitar na colecção.
	* @pre hasNext()
	* @return O próximo elemento a visitar, se existir, ou null, caso contrário.
	*/
	public Object next();
	
}

package cloud_sharing;

/**
 * Um ficheiro
 * @author David Gago 41361 / Manuel Duarte 39443
 */
public interface File {
	
	/**
	* Actualiza o ficheiro.
	* @param conta - conta que está a actualizar.
	*/
	public void update(Member conta);
	
	/**
	* Devolve o nome do ficheiro.
	* @return nome do ficheiro.
	*/
	public String getName();
	
	/**
	* Devolve o tamanho do ficheiro.
	* @return tamanho do ficheiro.
	*/
	public int getSize();
	
	/**
	* Devolve a última conta que actualizou o ficheiro.
	* @return nome da conta.
	*/
	public String getLastUpdate();

}

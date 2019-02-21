package cloud_sharing;

/**
 * Cada membro com ficheiros e os métodos associados
 * @author David Gago 41361 / Manuel Duarte 39443
 */
public interface Member {

	/**
	* Adiciona o ficheiro à conta.
	* @pre fileOwnership(fname)==0.
	* @pre checkSpace>=size.
	* @param fname - nome do ficheiro.
	* @param size - tamanho do ficheiro.
	*/
	public void addFile(String fname, int size);
	
	/**
	* Verifica o espaço livre na conta.
	* @return espaço livre.
	*/
	public int checkSpace();
	
	/**
	* Verifica o tamanho do ficheiro
	* @param fname - nome do ficheiro.
	* @return 0 - não existe, ou tamanho do ficheiro (MB)
	*/
	public int getFileSize(String fname);
	
	/**
	* Adiciona o ficheiro à lista dos partilhados.
	* @param fname - nome do ficheiro.
	*/
	public void shareFile(String fname, int size);
	
	/**
	* Actualiza um ficheiro.
	* @param changer - utilizador que está a fazer a actualização.
	* @param fname - nome do ficheiro.
	*/
	public void updateFile(Member changer, String fname);
	
	/**
	* Procura a última conta a actualizar o ficheiro.
	* @param fname - enome do ficheiro.
	* @return nome da conta.
	*/
	public String checkLastUpdate(String fname);
	
	/**
	* Verifica a existência de um ficheiro numa conta.
	* @param fname - nome do ficheiro.
	* @return 0 - não existe, 1 - pertence à conta, 2 - partilhado com a conta
	*/
	public int fileOwnership(String fname);
	
	/**
	* Lista todos os ficheiros da conta.
	* @return Iterador com os ficheiros.
	*/
	public Iterator listFiles(String type);
	
	/**
	* Verifica o nome da conta.
	* @return String com o nome.
	*/
	public String getName();
	
	/**
	* Verifica o número de ficheiros de um tipo.
	* @return número de ficheiros.
	*/
	public int getFiles(String type);

}

package cloud_sharing;

/**
 * A Cloud com as várias contas e acções
 * @author David Gago 41361 / Manuel Duarte 39443
 */
public interface Cloud {
	//Tipos de contas
	/*public enum accType {
		BASIC, PREMIUM
	}*/
	
	/**
	* Procura a conta associada ao email e devolve o objecto.
	* @param name - email do utilizador.
	* @return NULL - conta não encontrada, ou o objecto caso encontre.
	*/
	public Member getAccount(String name);
	
	/**
	* Adiciona uma nova conta.
	* @pre getAccount == NULL
	* @param name - email do utilizador.
	* @param type - tipo de conta.
	*/
	public void addAccount(String name, String type);
	
	/**
	* Verifica a existência de um ficheiro numa conta.
	* @pre getAccount != NULL
	* @param account - conta a verificar.
	* @param fname - nome do ficheiro.
	* @return 0 - não existe, 1 - pertence à conta, 2 - partilhado com a conta
	*/
	public int fileOwnership(Member account, String fname);
	
	/**
	* Adiciona um ficheiro a uma conta.
	* @pre getAccount != NULL.
	* @pre checkAccountMem >= tamanho do ficheiro.
	* @param account - dono da conta a que vai ser adicionado o ficheiro.
	* @param fname - nome do ficheiro.
	* @param size - tamanho do ficheiro (MB).
	*/
	public void uploadFile(Member account, String fname, int size);
	
	/**
	* Verifica o espaço livre da conta.
	* @pre getAccount != NULL.
	* @param account conta que vai ser verificada.
	* @return espaço livre (MB).
	*/
	public int checkAccountMem(Member account);
	
	/**
	* Verifica o tamanho do ficheiro
	* @pre getAccount != NULL.
	* @param account - conta a que o ficheiro pertence.
	* @param fname - nome do ficheiro.
	* @return 0 - não existe, ou tamanho do ficheiro (MB)
	*/
	public int getFileSize(Member account, String fname);
	
	/**
	* Verifica o tipo de conta
	* @pre getAccount != NULL.
	* @param account conta que vai ser verificada.
	* @return Premium/Basic.
	*/
	public String getType(Member account);
	
	/**
	* Partilha um ficheiro.
	* @pre getAccount != NULL tanto a owner como a target.
	* @pre fileOwnership(owner, fname)==1.
	* @pre fileOwnership(target, fname)==0.
	* @pre getType(owner)==PREMIUM.
	* @pre haja espaço livre na conta(checkAccountMem(), getFileSize(), getType()). 
	* @param owner - dono da conta.
	* @param target - utilizador com quem o ficheiro vai ser partilhado.
	* @param fname - nome do ficheiro.
	*/
	public void shareFile(Member owner, Member target, String fname);
	
	/**
	* Actualiza um ficheiro.
	* @pre getAccount != NULL tanto a owner como a changer.
	* @pre fileOwnership(owner, fname)==1.
	* @pre fileOwnership(changer, fname)==2 (ou 1 se owner==changer).
	* @param owner - dono da conta.
	* @param changer - utilizador que está a fazer a actualização.
	* @param fname - nome do ficheiro.
	*/
	public void updateFile(Member owner, Member changer, String fname);
	
	/**
	* Verifica quem actualizou pela última vez um ficheiro.
	* @pre getAccount != NULL.
	* @pre fileOwnership()==1.
	* @param owner - dono da conta.
	* @param fname - nome do ficheiro.
	* @return user que fez a última actualização.
	*/
	public String checkLastUpdate(Member owner, String fname);
	
	/**
	* Lista todos os ficheiros proprietários e partilhados de uma conta.
	* @pre getAccount != NULL.
	* @return String a imprimir.
	*/
	public Iterator listFiles(Member account, String type);
	
	/**
	* Lista todas as contas.
	* @return String a imprimir.
	*/
	public Iterator listAll();
	
	/**
	* Lista todas as contas de um tipo.
	* @return String a imprimir.
	*/
	public Iterator listByType(String type);
	
	/**
	* Verifica o número de contas na Coud,
	* @return número de contas.
	*/
	public int membCounter();

}

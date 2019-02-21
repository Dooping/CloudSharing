import java.util.Scanner;
import cloud_sharing.*;

class Main {

	/**
	 * @param args
	 */	
	public static final String ADD = "ADD";
	public static final String UPLOAD = "UPLOAD";
	public static final String SHARE = "SHARE";
	public static final String UPDATE = "UPDATE";
	public static final String MINSPACE = "MINSPACE";
	public static final String LIST_FILES = "FILES";
	public static final String LIST_ALL = "ALL";
	public static final String LIST = "LIST";
	public static final String LAST_UPDATE = "LASTUPDATE";
	public static final String EXIT = "EXIT";

	public static final String NO_ACCOUNTS = "> No accounts.";
	public static final String FILES_NOT_EXISTS = "> File does not exist.";
	public static final String ACCOUNT_ADDED = "> Account was added.";
	public static final String ACCOUNT_EXISTS = "> Account already exists.";
	public static final String FILE_UPLOADED = "> File uploaded into account.";
	public static final String ACCOUNT_NOT_EXISTS = "> Account does not exist.";
	public static final String FILE_SIZE_EXCEEDS = "> File size exceeds account capacity.";
	public static final String FILE_EXISTS = "> File already exists in the account.";
	public static final String FILE_SHARED = "> File was shared.";
	public static final String LAST_UPDATE_ACCOUNT = "> Last update: ";
	public static final String ACCOUNT_NOT_ALLOW_SHARING = "> Account does not allow file sharing.";
	public static final String FILE_ALREADY_SHARED = "> File already shared.";
	public static final String FILE_WAS_UPDATED = "> File was updated.";
	public static final String FILE_NOT_SHARED = "> File not shared.";
	public static final String FILE_LEAST_SPACE = "> Account with least free space: ";
	public static final String ACCOUNT_HAS = "> Account has ";
	public static final String ALL_ACCOUNTS = "> All accounts:";
	public static final String ACCOUNTS = " accounts:";
	public static final String EXITING = "> Exiting...";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner in = new Scanner(System.in);
		Cloud cloud = new CloudClass();
		String command;
		do {
			command = in.next();
			Interpretador(command, in, cloud);
			System.out.println();
		} while (!command.equals(EXIT));
		in.close();
	}

	private static void Interpretador(String cmd, Scanner in, Cloud cloud) {
		String accName;
		String accTargetName;
		String fileName;
		Member acc;
		Member accTarget;
		if (cmd.equals(ADD)){
			accName = in.next();
			String type = in.next();
			if (cloud.getAccount(accName) == null) {
				cloud.addAccount(accName, type);
				System.out.println(ACCOUNT_ADDED);
			} else
				System.out.println(ACCOUNT_EXISTS);
		}
		else if  (cmd.equals(UPLOAD)){
			accName = in.next();
			fileName = in.next();
			int fileSize = in.nextInt();
			acc = cloud.getAccount(accName);
			if (acc != null)
				if (cloud.fileOwnership(acc, fileName) == 0)
					if (cloud.checkAccountMem(acc) >= fileSize) {
						cloud.uploadFile(acc, fileName, fileSize);
						System.out.println(FILE_UPLOADED);
					} else
						System.out.println(FILE_SIZE_EXCEEDS);
				else
					System.out.println(FILE_EXISTS);
			else
				System.out.println(ACCOUNT_NOT_EXISTS);
		}

		else if (cmd.equals(SHARE)){
			accName = in.next();
			accTargetName = in.next();
			fileName = in.next();
			acc = cloud.getAccount(accName);
			accTarget = cloud.getAccount(accTargetName);
			if (acc != null)
				if (accTarget != null)
					if (cloud.fileOwnership(acc, fileName) == 1)
						if (cloud.getType(acc).equals("Premium"))
							//if (cloud.fileOwnership(accTarget, fileName) != 2)
								if (cloud.getType(accTarget).equals("Premium")) {
									cloud.shareFile(acc, accTarget, fileName);
									System.out.println(FILE_SHARED);
								} else {
									if (cloud.checkAccountMem(accTarget) >= (cloud
											.getFileSize(acc, fileName) / 2)) {
										cloud.shareFile(acc, accTarget,
												fileName);
										System.out.println(FILE_SHARED);
									} else
										System.out.println(FILE_SIZE_EXCEEDS);
								}
						//	else
						//		System.out.println(FILE_ALREADY_SHARED);
						else
							System.out.println(ACCOUNT_NOT_ALLOW_SHARING);
					else
						System.out.println(FILES_NOT_EXISTS);
				else
					System.out.println(ACCOUNT_NOT_EXISTS);
			else
				System.out.println(ACCOUNT_NOT_EXISTS);
		}
		else if (cmd.equals(UPDATE)){
			accName = in.next();
			accTargetName = in.next();
			fileName = in.next();
			acc = cloud.getAccount(accName);
			accTarget = cloud.getAccount(accTargetName);
			if (acc != null)
				if (accTarget != null)
					if (cloud.fileOwnership(acc, fileName) == 1)
						if (cloud.fileOwnership(accTarget, fileName) != 0) {
							cloud.updateFile(acc, accTarget, fileName);
							System.out.println(FILE_WAS_UPDATED);
						} else
							System.out.println(FILE_NOT_SHARED);
					else
						System.out.println(FILES_NOT_EXISTS);
				else
					System.out.println(ACCOUNT_NOT_EXISTS);
			else
				System.out.println(ACCOUNT_NOT_EXISTS);
		}
		else if (cmd.equals(MINSPACE)){
			Member old = null;
			int i = 0;
			if (cloud.membCounter() > 0) {
				Iterator all = cloud.listAll();
				while (all.hasNext()) {
					Member tmp = (Member) all.next();
					if (i == 0)
						old = tmp;
					i++;
					if (tmp.checkSpace() < old.checkSpace())
						old = tmp;
				}
				System.out.println(FILE_LEAST_SPACE + old.getName());
			} else
				System.out.println(NO_ACCOUNTS);
		}
		else if (cmd.equals(LIST)){
			cmd = in.next();
			if (cmd.equals(LIST_FILES)){
				accName = in.next();
				File fich;
				acc = cloud.getAccount(accName);
				if (acc != null) {
					Iterator files = acc.listFiles("owned");
					System.out.println(ACCOUNT_HAS + acc.getFiles("owned")
							+ " files:");
					while (files.hasNext()) {
						fich = (File) files.next();
						System.out.println("> "+fich.getName() + " (" + fich.getSize()
								+ " MB)");
					}
					files = acc.listFiles("shared");
					System.out.println(ACCOUNT_HAS + acc.getFiles("shared")
							+ " shared files:");
					while (files.hasNext()) {
						fich = (File) files.next();
						System.out.println("> "+fich.getName() + " (" + fich.getSize()
								+ " MB)");
					}
				}
				else System.out.println(ACCOUNT_NOT_EXISTS);
			}
			else if (cmd.equals(LIST_ALL)){
				System.out.println(ALL_ACCOUNTS);
				Iterator all = cloud.listAll();
				while(all.hasNext()){
					acc=(Member)all.next();
					System.out.println("> "+acc.getName()+" ("+cloud.getType(acc)+")");
				}
			}
			else{
				if (cmd.equals("PREMIUM")) cmd="Premium";
				else cmd="Basic";
				System.out.println("> "+cmd+ACCOUNTS);
				Iterator typeIt = cloud.listByType(cmd);
				while(typeIt.hasNext()){
					acc=(Member)typeIt.next();
					System.out.println("> "+acc.getName());
				}
			}
		}
		else if (cmd.equals(LAST_UPDATE)){
			accName = in.next();
			fileName = in.next();
			acc = cloud.getAccount(accName);
			if (acc!=null)
				if(acc.fileOwnership(fileName)==1){
					accTargetName = acc.checkLastUpdate(fileName);
					if (!accTargetName.equals(null))
						System.out.println(LAST_UPDATE_ACCOUNT+acc.checkLastUpdate(fileName));
					else
						System.out.println(FILES_NOT_EXISTS);
				}
				else System.out.println(FILES_NOT_EXISTS);
			else System.out.println(ACCOUNT_NOT_EXISTS);
		}
		else if (cmd.equals(EXIT)) 
			System.out.println(EXITING);
	}
}
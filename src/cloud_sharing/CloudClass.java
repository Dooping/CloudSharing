package cloud_sharing;

public class CloudClass implements Cloud {
	private static final int DEFAULT_SIZE = 10;
	private Member members[];
	private int counter = 0;

	public CloudClass() {
		members = new MemberClass[DEFAULT_SIZE];
	}

	@Override
	public Member getAccount(String name) {
		// TODO Auto-generated method stub
		Iterator membersIt = new IteratorClass(members, counter);
		while (membersIt.hasNext()) {
			Member obj = (MemberClass) membersIt.next();
			if (obj.getName().equals(name)) {
				return obj;
			}
		}
		return null;
	}

	@Override
	public void addAccount(String name, String type) {
		// TODO Auto-generated method stub
		if (type.equals("basic"))
			members[counter++] = new BasicClass(name);
		else
			members[counter++] = new PremiumClass(name);
	}

	@Override
	public int fileOwnership(Member account, String fname) {
		// TODO Auto-generated method stub
		return account.fileOwnership(fname);
	}

	@Override
	public void uploadFile(Member account, String fname, int size) {
		// TODO Auto-generated method stub
		account.addFile(fname, size);
	}

	@Override
	public int checkAccountMem(Member account) {
		// TODO Auto-generated method stub
		return account.checkSpace();
	}

	@Override
	public int getFileSize(Member account, String fname) {
		// TODO Auto-generated method stub
		return account.getFileSize(fname);
	}

	@Override
	public String getType(Member account) {
		// TODO Auto-generated method stub
		if (account instanceof BasicClass)
			return "Basic";
		else
			return "Premium";
	}

	@Override
	public void shareFile(Member owner, Member target, String fname) {
		// TODO Auto-generated method stub
		target.shareFile(fname, owner.getFileSize(fname));
	}

	@Override
	public void updateFile(Member owner, Member changer, String fname) {
		// TODO Auto-generated method stub
		owner.updateFile(changer, fname);
	}

	@Override
	public String checkLastUpdate(Member owner, String fname) {
		// TODO Auto-generated method stub
		return owner.checkLastUpdate(fname);
	}

	@Override
	public Iterator listFiles(Member account, String type) {
		// TODO Auto-generated method stub
		return account.listFiles(type);
	}

	@Override
	public Iterator listAll() {
		// TODO Auto-generated method stub
		Iterator membersIt = new IteratorClass(members, counter);
		return membersIt;
	}

	@Override
	public Iterator listByType(String type) {
		// TODO Auto-generated method stub
		Iterator membersIt = new IteratorClass(members, counter);
		int i = 0;
		Member[] all = new Member[DEFAULT_SIZE];
		membersIt.init();
		Member temp;
		while (membersIt.hasNext()) {
			temp = (Member) membersIt.next();
			if (getType(temp).equals(type))
				all[i++] = temp;
		}
		if (i>0){
			IteratorClass it = new IteratorClass(all, i);
			return it;
		}
		return null;
	}
	
	public int membCounter(){
		return counter;
	}
}
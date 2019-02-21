package cloud_sharing;

public abstract class MemberClass implements Member {
	private static final int DEFAULT_SIZE = 10;
	protected String name;
	protected File ownedFiles[];
	protected File sharedFiles[];
	protected int space;
	protected int counterShared;
	protected int counterOwned;

	public MemberClass() {
		ownedFiles = new FileClass[DEFAULT_SIZE];
		sharedFiles = new FileClass[DEFAULT_SIZE];
	}

	@Override
	public void addFile(String fname, int size) {
		// TODO Auto-generated method stub
		ownedFiles[counterOwned] = new FileClass(fname, size);
		ownedFiles[counterOwned++].update(this);
		space -= size;
	}

	@Override
	public int checkSpace() {
		// TODO Auto-generated method stub
		return space;
	}

	@Override
	public int getFileSize(String fname) {
		// TODO Auto-generated method stub
		Iterator ownedIt = new IteratorClass(ownedFiles, counterOwned);
		ownedIt.init();
		while (ownedIt.hasNext()) {
			File obj = (FileClass) ownedIt.next();
			if (obj.getName().equals(fname)) {
				return obj.getSize();
			}
		}
		return 0;
	}

	@Override
	public void updateFile(Member changer, String fname) {
		// TODO Auto-generated method stub
		Iterator ownedIt = new IteratorClass(ownedFiles, counterOwned);
		ownedIt.init();
		while (ownedIt.hasNext()) {
			FileClass obj = (FileClass) ownedIt.next();
			if (obj.getName().equals(fname)) {
				obj.update(changer);
			}
		}
	}

	@Override
	public String checkLastUpdate(String fname) {
		// TODO Auto-generated method stub
		Iterator ownedIt = new IteratorClass(ownedFiles, counterOwned);
		ownedIt.init();
		while (ownedIt.hasNext()) {
			FileClass obj = (FileClass) ownedIt.next();
			if (obj.getName().equals(fname)) {
				return obj.getLastUpdate();
			}
		}
		return null;
	}

	@Override
	public int fileOwnership(String fname) {
		// TODO Auto-generated method stub
		Iterator ownedIt = new IteratorClass(ownedFiles, counterOwned);
		Iterator sharedIt = new IteratorClass(sharedFiles, counterShared);
		ownedIt.init();
		while (ownedIt.hasNext()) {
			FileClass obj = (FileClass) ownedIt.next();
			if (obj.getName().equals(fname)) {
				return 1;
			}
		}
		sharedIt.init();
		while (sharedIt.hasNext()) {
			File obj2 = (FileClass) sharedIt.next();
			if (obj2.getName().equals(fname)) {
				return 2;
			}
		}
		return 0;
	}

	@Override
	public Iterator listFiles(String type) {
		// TODO Auto-generated method stub
		if (type.equals("shared"))
			return new IteratorClass(sharedFiles, counterShared);
		else
			return new IteratorClass(ownedFiles, counterOwned);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public int getFiles(String type) {
		if (type.equals("shared"))
			return counterShared;
		else
			return counterOwned;
	}
}

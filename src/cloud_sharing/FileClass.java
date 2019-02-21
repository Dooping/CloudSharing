package cloud_sharing;

public class FileClass implements File{
	private String name;
	private int size;
	private String lastAcc;

	public FileClass (String name, int size) {
		this.name=name;
		this.size=size;
	}
	@Override
	public void update(Member conta) {
		this.lastAcc=conta.getName();		
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public String getLastUpdate() {
		return lastAcc;
	}
	

}

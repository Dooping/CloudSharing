package cloud_sharing;

public class BasicClass extends MemberClass {

	public BasicClass(String name){
		super();
		this.name=name;
		space = 2048;
	}
	@Override
	public void shareFile(String fname, int size) {
		// TODO Auto-generated method stub
		sharedFiles[counterShared++]= new FileClass(fname, size);
		space -= size/2;
	}


}

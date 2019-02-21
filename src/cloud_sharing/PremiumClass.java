package cloud_sharing;

public class PremiumClass extends MemberClass{
	
	public PremiumClass(String name){
		super();
		this.name=name;
		space=5120;
	}

	@Override
	public void shareFile(String fname, int size) {
		// TODO Auto-generated method stub
		sharedFiles[counterShared++]= new FileClass(fname, size);
	}

}

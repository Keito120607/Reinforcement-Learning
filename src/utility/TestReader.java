package utility;

public class TestReader implements ResultDir {

	String dirname;
	String sn;

	@Override
	public String getFolderPath() {
		return dirname+sn;
	}

	public TestReader(String subdirname){dirname="tests/";sn=subdirname;}

	public TestReader(String dn, String subdirname){dirname=dn+"/";sn=subdirname;}

}

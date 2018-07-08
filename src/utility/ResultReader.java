package utility;

public class ResultReader implements ResultDir {

	String sn;

	@Override
	public String getFolderPath() {
		return folderName+sn;
	}

	public ResultReader(String subdirname){sn=subdirname;}

}

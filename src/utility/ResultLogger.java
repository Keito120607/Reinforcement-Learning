package utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

//出力ファイルまでを担当

public class ResultLogger implements Cloneable, ResultDir{
	private  String simulateTime="void";
	private String subname="void";

	boolean canDelete=false;



	public String getFolderPath(){
		String ret=folderName+simulateTime+"-"+subname;
		return ret;
	}

	public  ResultLogger(String sn){
		simulateTime=""+System.currentTimeMillis();
		subname=sn;

		prepareDir();
	}

	public ResultLogger(String sn, Boolean _canDelete){
		this(sn);
		canDelete=_canDelete;

	}

	private void prepareDir(){
		ArrayList<String> tmp=new ArrayList<String>();
		for(String part:getFolderPath().split("/")){
			tmp.add(part);
			String dirname=String.join("/", tmp);
			File newdir = new File(dirname);
			newdir.mkdir();
		}
	}

	@Override
	public ResultLogger clone(){
		ResultLogger ret=null;
		try {
			ret=(ResultLogger) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return ret;
	}

	public ResultLogger subDir(String _n){
		ResultLogger sub=this.clone();
		sub.subname+="/"+_n;
		sub.prepareDir();
		return sub;
	}

	synchronized public  PrintWriter  getWriter(String fn) {
		File file= new File(getFolderPath()+"/"+fn);
		PrintWriter pw=null;
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(file,true)));
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw new RuntimeException();
		}
		return pw;
	}

	public void remove(){
		if(!canDelete){System.out.println("そうはさせんぞ！");return;}
		delete(new File(getFolderPath()));


	}
	static private void delete(File f){
		if( f.exists()==false ){
			return ;
		}

		if(f.isFile()){
			f.delete();
		}

		if(f.isDirectory()){
			File[] files=f.listFiles();
			for(int i=0; i<files.length; i++){
				delete( files[i] );
			}
			f.delete();
		}
	}

}

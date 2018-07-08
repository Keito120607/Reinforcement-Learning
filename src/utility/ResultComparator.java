package utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultComparator {

	static public boolean compare(ResultDir aDir, ResultDir bdir){
		return compare(aDir.getFolderPath(), bdir.getFolderPath());
	}

	static public boolean compare(String adir, String bdir){
		boolean ret=true;

		List<String> alist =  Arrays.asList( new File(adir).list());
		List<String>  blist =Arrays.asList( new File(bdir).list());

		ArrayList<String> retains= (new ArrayList<String>(alist));
		retains.retainAll(blist);

		ArrayList<String> onlya= (new ArrayList<String>(alist));
		onlya.removeAll(blist);


		ArrayList<String> onlyb= (new ArrayList<String>(blist));
		onlyb.removeAll(alist);




		for (String string : onlya) {
			System.out.println("Aのみ : "+string);
			ret=false;
		}
		for (String string : onlyb) {
			System.out.println("Bのみ : "+string);
			ret=false;
		}
		for (String string : retains) {
			boolean tmp = fileCompare(adir+"/"+string, bdir+"/"+string);
			ret&=tmp;
			System.out.println((tmp?"一致":"不一致")+" : "+string);
		}

		return ret;
	}

	static public boolean fileCompare(String fileA, String fileB) {
	    boolean bRet = false;
	    try {
	        if( new File(fileA).length() != new File(fileA).length() ){
	            return bRet;
	        }
	        byte[] byteA = Files.readAllBytes(Paths.get(fileA));
	        byte[] byteB = Files.readAllBytes(Paths.get(fileB));
	        bRet = Arrays.equals(byteA, byteB);

	    } catch (IOException e) {
	    }
	    return bRet;
	}
}

package utility;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

//出力ファイル名と中身を担当

public class EachOutputer implements Outputer {

	String pref="Result-";

	ResultLogger rl;
	PrintWriter pw;



	public EachOutputer(ResultLogger _rl) {
		rl=_rl;
	}




	@Override
	public void output(int line, ArrayList<Double> list) {
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < list.size(); i++)  {
			buf.append(list.get(i).toString());
			if(i!=list.size()-1)buf.append(",");
		}
		pw.println(buf.toString());
	}

	@Override
	public void create(String signeture) {
		pw=rl.getWriter(pref+signeture+".txt");

	}

	@Override
	public void close() {
		pw.close();
	}




	@Override
	public void printhead(List<String> list) {
		if(list==null)return;
		pw.println("#"+String.join(",",list));
	}

}

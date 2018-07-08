package utility;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AverageOutputer implements Outputer {

	String pref="mean-";

	PrintWriter pw;

	HashMap<Integer, ArrayList<Double>> map=new HashMap<Integer, ArrayList<Double>>();
	int num=0;

	ArrayList<String> list;

	public AverageOutputer(ResultLogger _rl, String signeture) {

		pw=_rl.getWriter(pref+signeture+".txt");
	}

	@Override
	public void output(int line, ArrayList<Double> list) {
		if(!map.containsKey(line)){
			map.put(line, new ArrayList<Double>(list));
			return;
		}
		int max=map.get(line).size();
		for (int i = 0; i < max; i++) {
			double a=map.get(line).get(i);
			map.get(line).set(i, a+list.get(i));
		}
	}



	@Override
	public void create(String signeture) {num++;}
	@Override
	public void close() {}
	public void print(){
		pw.println("#"+String.join(",",list));

		int max=map.size();
		for (int i = 0; i < max; i++) {
			ArrayList<Double> a=map.get(i);
			int mm=a.size();
			for (int j = 0; j < mm; j++)  {
				pw.print(a.get(j)/(double)num);
				if(j!=mm-1)pw.print(",");
				}
			pw.println();
		}
		pw.close();
	}

	@Override
	public void printhead(List<String> _list) {
		list=new ArrayList<String>(_list);

	}

}

package utility;

import java.util.ArrayList;
import java.util.List;

public interface Outputer {
	public abstract void  output(int line, ArrayList<Double> list);
	public void create(String signeture);
	public void close();
	public void printhead(List<String> list);
}

package utility;
import java.util.SplittableRandom;

public class MyRandom {
	 private SplittableRandom r;
	 public  MyRandom(int i) {

		 r=new SplittableRandom(i);
	}

	 public double nextDouble(){
		return r.nextDouble();
	}
	 public boolean probability(double p){

		return p>r.nextDouble();
	}

	 public int nextInt(int limit){
		return r.nextInt(limit);
	}

	public int nextInt(){
		return r.nextInt();
	}

	 public boolean nextBoolean(){
		return r.nextBoolean();
	}

}

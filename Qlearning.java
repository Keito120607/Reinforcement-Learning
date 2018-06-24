import java.util.ArrayList;
import java.util.Random;

public class Qlearnig{

	public static final double EPSILON = 0.30;       // ε
	public static final double ALPHA = 0.10;         // α
	public static final double GAMMA = 0.90;         // γ
	//public static final int REWARD = 100;       // 
	public static final int COST = 1;    // 1ステップ経過のペナルティ
	public static final int LEANING_TIMES = 1000000; // 学習回数
	public static final int INIT_Q_MAX = 30;         // Qmax


	public void initQ() {
		  Random rand = new Random();

		  int randNum = rand.nextInt(INIT_Q_MAX+1);

	}  

	public int eGreedy() {
		
		int selectedA = 0;
		
        Random rand = new Random();
        int randNum = rand.nextInt(100+1);
        
        if (randNum <= EPSILON * 100.0) {
        	

			}
        } else {
        	selectedA = rand.nextInt(4);
        }
        
        return selectedA;
        
	}

}


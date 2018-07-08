package network;

import utility.BitArray;
import utility.MyRandom;

import java.util.Comparator;

public class Agent_with_V implements Cloneable{
	private BitArray Bi;//大胆さ 裏切りやすさ
	private BitArray V;//裏切り者を懲罰する確率
	private BitArray L;//協調を褒賞する確率
	public double score;
	final static int length=3;
	public int id;

	 public MyRandom random;
	 public double LDrate;
	 public double LCrate;
	 public double Brate;

	 public int limtedRemainTime;

	 public boolean fixmode=false;

	public Agent_with_V(int id, MyRandom m){
		Bi=new BitArray(length);
		V=new BitArray(length);
		L=new BitArray(length);
		random=m;
		score=0;
		Bi.SetRamdom(random);
		V.SetRamdom(random);

//		for(int i=0;i<length;i++){
//			V.Set(i, false);		//Vを0にセット
//		}

		L.SetRamdom(random);
		this.id=id;

		LDrate=1.0;
		LCrate=1.0;
		Brate=1.0;
	}
	public double getBoldness(){
		return 1-getPosting();
	}
	public double getPosting(){
		return Bi.getDouble()*Brate;
	}
	public double getVengefulness(){
		return V.getDouble()*LCrate;
	}
	public double getRewardness(){
		return L.getDouble()*LDrate;
	}

	public void addScore(double n){
		score+=n;
	}

	public String toString(){
		return "{\"name\":"+this.id+" ,\"LD\":"+this.getRewardness()+" ,\"B\":"+this.getBoldness()+" ,\"LC\":"+this.getRewardness()+"}";
	}

	 public Agent_with_V clone(){
		Agent_with_V r;
		try {
			r = (Agent_with_V)super.clone();
		} catch (CloneNotSupportedException ce)
		{ throw new RuntimeException();/* 適切なエラー処理 */}
		r.Bi=this.Bi.clone();
		r.V=this.V.clone();
		r.L=this.L.clone();

		return r;
	}

	public void mutation(){

		//大阪さん　突然変異0.005

		 for (int i = 0; i < length; i++) {
		         if(random.nextDouble()<=0.005){
		                 Bi.Set(i, !Bi.Value(i));
		         }
		         if(random.nextDouble()<=0.005){
				 V.Set(i, !V.Value(i));
			 }
		         if(random.nextDouble()<=0.005){
		                 L.Set(i, !L.Value(i));
		         }
		 }

		//平原さん　突然変異0.01
//		for (int i = 0; i < length; i++) {
//			if(random.nextDouble()<=0.01){
//				Bi.Set(i, !Bi.Value(i));
//			}
//			if(random.nextDouble()<=0.01){
//				V.Set(i, !V.Value(i));
//			}
//			if(random.nextDouble()<=0.01){
//				L.Set(i, !L.Value(i));
//			}
//		}
/*
		if(random.nextInt(100)<=2){
			int i=random.nextInt(8);
			if(i/3==0){
				Bi.Set(i%3, !Bi.Value(i%3));
			}else if(i/3==1){
				V.Set(i%3, !V.Value(i%3));
			}else{
				L.Set(i%3, !L.Value(i%3));
			}
		}
*/

	}

	public void evolution(Agent_with_V father, Agent_with_V mother){
		if(fixmode)return;
		Agent_with_V.CopyParam(this, Agent_with_V.UniformCrossover(father,mother));
	}

	//一葉交差
	static private Agent_with_V UniformCrossover(Agent_with_V father, Agent_with_V mother){
		Agent_with_V child=father.clone();
		for (int i = 0; i < length; i++) {
			if(child.random.nextBoolean()){
				child.Bi.Set(i, mother.Bi.Value(i));
			}
			if(child.random.nextBoolean()){
				child.V.Set(i, mother.V.Value(i));
			}
			if(child.random.nextBoolean()){
				child.L.Set(i, mother.L.Value(i));
			}
		}
		return child;
	}
	//パラメータコピー
	static public void CopyParam(Agent_with_V agent, Agent_with_V copyorigin){
		agent.Bi=copyorigin.Bi.clone();
		agent.V=copyorigin.V.clone();
		agent.L=copyorigin.L.clone();
	}

	//リストソート用
	static public class AgentComparator implements Comparator<Agent_with_V> {
		public int compare(Agent_with_V p1, Agent_with_V p2) {
			if(p1.score==p2.score)return 0;
			return  (p2.score >p1.score)?1:-1;
		}

	}



}

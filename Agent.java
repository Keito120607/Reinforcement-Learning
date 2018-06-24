package network;

import java.util.ArrayList;
import java.util.Comparator;

import utility.BitArray;
import utility.MyRandom;

public class Agent implements Cloneable{

	private BitArray L;//協調を褒賞する確率
	public double score;

	public double Rscore;
	public double metaRscore;

	public double kizi;	//記事投稿回数
	public double comment;//コメント回数
	public ArrayList<Double> comment_to_who; //ある相手に何回コメントを行ったか
	public double freeride_kizi; // 記事投稿に対してコメントせずフリーライドを行った回数を記録
	public double freeride_comment; //コメントに対してメタコメントせずフリーライドを行った回数を記録

	public double q_value;//Q値
	

	final static int length=3;
	public int id;

	 public MyRandom random;
	 public double LDrate;
	 public double LCrate;
	 public double Brate;

	 public int limtedRemainTime;

	 public boolean fixmode=false;

	public Agent(int id,MyRandom m){
		Bi=new BitArray(length);
		V=new BitArray(length);
		L=new BitArray(length);
		random=m;
		score=0;

		Rscore=0;
		metaRscore=0;
		parents_count=0;
		freeride_kizi=0;
		freeride_comment=0;
		mutation_count=0;

		comment_to_who = new ArrayList<Double>(10000);
		for (int i = 0; i < 10000; i++) {
			comment_to_who.add(0.0);
		}

		parents = new ArrayList<Double>(10000);
		for (int i = 0; i < 10000; i++) {
			parents.add(0.0);
		}

		Bi.SetRamdom(random);
		V.SetRamdom(random);

//		for(int i=0;i<length;i++){
//			V.Set(i, true);		//Vを1にセット
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




//	public void addScore(){
//		score += (-log(-Fscore) + log(Mscore) - log(-Cscore) + log(Rscore));
//		Fscore=0;
//		Mscore=0;
//		Cscore=0;
//		Rscore=0;
//	}

//	public void addFscore(double n){
//		Fscore+=n;
//	}
//	public void addMscore(double n){
//		Mscore+=n;
//	}
//	public void addCscore(double n){
//		Cscore+=n;
//	}
	public void addR(double n){
		Rscore+=n;
	}
	public void addmetaR(double n){
		metaRscore+=n;
	}
	

	public void addRsocretoScore(double n){
//		score += log(Rscore);
		if(Rscore!=0){
			score += n*(log(Rscore/n)+1);
			add_recieve_comment(Rscore/n);
		}
//		if(this.id==175){		System.out.println("Comment_x ,"+Rscore/n);}

		Rscore=0;


	}
	public void addmetaRscoretoScore(double n){
//		score += log(metaRscore);
		if(metaRscore!=0){
		score += n*(log(metaRscore/n)+1);
			add_recieve_comment(metaRscore/n);
		}
//		System.out.println(9*(log(metaRscore/9)+1));

//		if(this.id==175){System.out.println("Metacomment_x ,"+metaRscore/n);}
		metaRscore=0;
	}

	public double log(double n){
		if(n<=0){
			return 0;
		}else {
			return Math.log(n);
		}
	}
	
	public void addcomment(){
		comment++;
	}
	public void addcomment(int i){ comment++; comment_to_who.set(i,comment_to_who.get(i)+1.0);}
	public void addfreeride_kizi(){ freeride_kizi++; }
	public void addfreeride_comment(){ freeride_comment++; }

	public void add_read_kizi(){read_kizi++;}
	public void add_recieve_comment(double recieve_comment){this.recieve_comment+=recieve_comment;}
	



	public String toString(){
		return "{\"name\":"+this.id+" ,\"LD\":"+this.getRewardness()+" ,\"B\":"+this.getBoldness()+" ,\"LC\":"+this.getRewardness()+"}";
	}

	 public Agent clone(){
		Agent r;
		try {
			r = (Agent)super.clone();
		} catch (CloneNotSupportedException ce)
		{ throw new RuntimeException();/* 適切なエラー処理 */}
		r.Bi=this.Bi.clone();
		r.V=this.V.clone();
		r.L=this.L.clone();

		return r;
	}

	public void m(){

		


	}

	public void evolution(Agent father,Agent mother){
		if(fixmode)return;
		Agent.CopyParam(this,Agent.UniformCrossover(father,mother));
	}

	//一葉交差
	static private Agent UniformCrossover(Agent father,Agent mother){
		Agent child=father.clone();
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
	static public void CopyParam(Agent agent,Agent copyorigin){
		agent.Bi=copyorigin.Bi.clone();
		agent.V=copyorigin.V.clone();
		agent.L=copyorigin.L.clone();
	}

	//リストソート用
	static public class AgentComparator implements Comparator<Agent> {
		public int compare(Agent p1, Agent p2) {
			if(p1.score==p2.score)return 0;
			return  (p2.score >p1.score)?1:-1;
		}

	}



}

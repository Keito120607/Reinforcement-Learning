package network;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import utility.BitArray;
import utility.MyRandom;

public class Agent implements Cloneable{
	private static BitArray Bi;//大胆さ 裏切りやすさ
	private static BitArray V;//裏切り者を懲罰する確率
	private static BitArray L;//協調を褒賞する確率
	public double score;

	//logスケール用score記憶 2016/11/08
//	public double Fscore;
//	public double Mscore;
//	public double Cscore;
	public double Rscore;
	public double metaRscore;

	public static int kizi;	//記事投稿回数を記録
	public static int comment;//コメント回数を記録
	public double read_kizi;//4ラウンドでいくつの記事を読んだか
	public double recieve_comment;//4ラウンドでいくつのコメントを貰ったか
	public ArrayList<Double> comment_to_who; //ある相手に何回コメントを行ったかを記録
	public ArrayList<Double> parents; //親エージェントのid //親=1, その他=0
	public double freeride_kizi; // 記事投稿に対してコメントせずフリーライドを行った回数を記録
	public double freeride_comment; //コメントに対してメタコメントせずフリーライドを行った回数を記録



	public double parents_count;	//親に選ばれた回数を記録
	public int mutation_count; //突然変異が起こった回数
	public static int MAX = 50000;

	//強化学習におけるQ値=記事投稿数、コメント数、行動確率
	public static double[][] Q = new double[MAX][MAX];

	public int[] act = {0,0};//行動決定(0→しない、1→する)0で初期化


	int learningMax;	//最大学習回数
	double gammaRate;//割引率
	double learningRate;//学習率
	static double epsilonRate;//ε
	int learningCnt;//学習回数
	double nowR;//即時報酬



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
//		Fscore=0;
//		Mscore=0;
//		Cscore=0;
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

		setQ(0.9,0.5,0.1);
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

	public void mutation(){
		Qlearning(1);
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


	public void setQ(double gr, double lr, double er){

		this.gammaRate = gr;
		this.learningRate = lr;
		this.epsilonRate = er;
		
		initializeQ();//Q値の初期化
	}

	//Q値の初期化、とりあえず1で初期化する
	void initializeQ(){
		for (int i=0;i<MAX;i++)
			for (int j=0;j<MAX;j++)
						Q[i][j]=1;//今後Q=a*i+b*j+c*kのようにしてQ値を初期化する？//ノート記載の更新式
	}

	//Q学習
	void Qlearning(int num){

		learningMax = num;//回数指定
		int[] act=new int[2];
		int[] max_act=new int[2];
		int kizi2,comment2;

		double maxNextQ;

		//最大学習回数分ほど学習
		for(int i=0; i<learningMax; i++){

			//ε-greedy法で更新したい
			act = epsGreedy();

			kizi2 = kizi+act[0];
			comment2 = comment+act[1];

			nowR =score;//即時報酬＝メタ報酬ゲームでの報酬

			
			max_act=getMaxQact(kizi2,comment2);//Q値が最大となる行動
			maxNextQ =Q[kizi2+max_act[0]][comment2+max_act[1]];//移動後のQ値の最大値を計算

			//Q値の更新
			Q[kizi][comment]=
					(1-learningRate) * Q[kizi][comment]  + learningRate * (nowR+gammaRate*maxNextQ);

			}

	}

	//ε-greedy
	private static int[] epsGreedy(){
		int[] actD= new int[2];
		Random rand = new Random();
		double rate = rand.nextDouble();

		if(rate < epsilonRate){	//ランダム
			random();//記事投稿率、コメント投稿率ともに70%で設定//完全ランダム？
		} else {				
			getMaxQact(kizi,comment);//Q値が最大となる行動
		}
		return actD;
	}

	private static int[] getMaxQact(int kizi,int comment){//最大の値を得られる行動を探索
		int act1=0;
		int act2=0;
		int[] max_act=new int[2];

		double max = Q[kizi][comment];
		for(int i=0; i<2; i++){
			if(max < Q[kizi+i][comment]){
				max = Q[kizi+i][comment];
				act1 = i;
			}
		}

		for(int j=0; j<2; j++){
			if(max < Q[kizi+act1][comment+j]){
				max = Q[kizi+act1][comment+j];
				act2 = j;
			}
		}

		Bi.SetR(act1);
		V.SetR(act2);
		L.SetR(act2);

		max_act[0] = act1;
		max_act[1] = act2;

		return  max_act;
	}

	private static void random(){//行動確率の実現

		int[] b,v,l;
		b = new int[3];
		v = new int[3];
		l = new int[3];
		Random rand = new Random();

		for(int i=0;i<3;i++){
			b[i]=rand.nextInt(2);
			v[i]=rand.nextInt(2);
			l[i]=rand.nextInt(2);
		}

		for(int i=0;i<3;i++){
			if(b[i]==0){
				Bi.Set(i, false);
			}else if(b[i]==1){
				Bi.Set(i, true);
			}
		}

		for(int i=0;i<3;i++){
			if(v[i]==0){
				V.Set(i, false);
			}else if(v[i]==1){
				V.Set(i, true);
			}
		}

		for(int i=0;i<3;i++){
			if(l[i]==0){
				L.Set(i, false);
			}else if(l[i]==1){
				L.Set(i, true);
			}
		}		
	}

}




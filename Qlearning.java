//適用したいコード
	
public class Qlearn{
	public static final MAX = 1000000;

	//強化学習におけるQ値=記事投稿数、コメント数、もらったコメント数
	public double[][][] Q = new double[MAX][MAX][MAX];

	public int[] act = new int[2];
	act ={0,0};//行動決定(0→しない、1→する)0で初期化

	public static final double a,b,c,d;//強化学習においてQ値の更新をする際に必要な定数(仮)

	public int learningMax;	//最大学習回数
	public double gammaRate;//割引率
	public double learningRate;//学習率
	public double epsilonRate;//ε
	public int step;//ステップ数
	public int learningCnt;//学習回数
	public int nowR;//即時報酬

	public setQ(double gr, double lr, double er){

		this.gammaRate = gr;
		this.learningRate = lr;
		this.epsilonRate = er;
		
		initializeQ();//Q値の初期化
	}

	//Q値の初期化、とりあえず1で初期化する
	void initializeQ(){
		for (int i=0;i<MAX;i++)
			for (int j=0;j<MAX;j++)
				for (int k=0;k<MAX;k++)
						Q[i][j][k]=1;//今後Q=a*i+b*j+c*kのようにしてQ値を初期化する？//ノート記載の更新式
	}

	//Q学習
	int Qlearning(int num){

		learningCnt = 0;
		learningMax = num;//回数指定
		int[] act=new int[2];
		int[] max_act=new int[2];

		double maxNextQ;

		//最大学習回数分ほど学習
		for(int i=0; i<learningMax; i++){

			//ε-greedy法で更新したい
			act = epsGreedy();

			kizi += act[0];
			comment += act[1];

			nowR =score;//即時報酬＝メタ報酬ゲームでの報酬

			
			max_act=getMaxQact(kizi,comment);//Q値が最大となる行動
			maxNextQ =Q[kizi+max_act[0]][comment+max_act[1]][receive_comment];//移動後のQ値の最大値を計算

			//Q値の更新
			Q[kizi][comment][receive_comment] =
					(1-learningRate) * Q[kizi][comment][receive_comment]  + learningRate * (nowR+gammaRate*maxNextQ);

			learningCnt++;

		//学習回数を返す
		return learningCnt;
	}

	//ε-greedy
	int epsGreedy(){
		int[] actD= new int[2];
		double rate = rand.nextDouble();

		if(rate < epsilonRate){	//ランダム
			actD = ｛random(70),random(70)};//記事投稿率、コメント投稿率ともに70%で設定
		} else {				
			actD = getMaxQact(kizi, comment);//Q値が最大となる行動
		}
		return actD;
	}

	int[] getMaxQact(int x,int y){//最大の値を得られる行動を探索
		int act1=0;
		int act2=0;
		int[] max_act=new int[2];

		double max = Q[kizi][comment][receive_comment];
		for(int i=0; i<2; i++){
			if(max < Q[kizi+i][comment][receive_comment]){
				max = Q[kizi+i][comment][receive_comment];
				act1 = i;
			}
		}

		for(int j=0; j<2; j++){
			if(max < Q[kizi+act1][comment+j][receive_comment]){
				max = Q[kizi+act1][comment+j][receive_comment];
				act2 = j;
			}
		}

		max_act = {act1,act2};

		return  max_act;
	}

	int random(int n){//行動確率の実現
		int ran = rand.nextInt(100)+1;
		if(ran<=n) return 1;
		else return 0;
	}

}
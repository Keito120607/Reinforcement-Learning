//適用したいコード
	
public class Qlearn{
	public static final MAX = 1000;

	//強化学習におけるQ値=記事投稿数、コメント数、行動確率
	public double[][][] Q = new double[MAX][MAX];

	public int[] act = new int[2];
	act ={0,0};//行動決定(0→しない、1→する)0で初期化



	public int learningMax;	//最大学習回数
	public double gammaRate;//割引率
	public double learningRate;//学習率
	public double epsilonRate;//ε
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

			
			max_act=getMaxQact(kizi,comment);//Q値が最大となる行動
			maxNextQ =Q[kizi2+max_act[0]][comment2+max_act[1]];//移動後のQ値の最大値を計算

			//Q値の更新
			Q[kizi][comment]=
					(1-learningRate) * Q[kizi][comment]  + learningRate * (nowR+gammaRate*maxNextQ);

		
		return ;
	}

	//ε-greedy
	int epsGreedy(){
		int[] actD= new int[2];
		double rate = rand.nextDouble();

		if(rate < epsilonRate){	//ランダム
			random();//記事投稿率、コメント投稿率ともに70%で設定//完全ランダム？
		} else {				
			getMaxQact();//Q値が最大となる行動
		}
		return actD;
	}

	void getMaxQact(int x,int y){//最大の値を得られる行動を探索
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

		Bi.setR(act1);
		V.setR(act2);
		L.setR(act2);

		max_act = {act1,act2};

		return  max_act;
	}

	void random(){//行動確率の実現

		int[] b,v,l;
		b = new int[3];
		v = new int[3];
		l = new int[3];

		for(int i=0;i<3;i++){
			b[i]=random.nextInt(2);
			v[i]=random.nextInt(2);
			l[i]=random.nextInt(2);
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
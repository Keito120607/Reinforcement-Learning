package socialmedia.game;

import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import network.Agent;
import network.NetworkPrinter;
import socialmedia.evolutions.EvolutionStrategy;
import utility.Outputer;
import utility.ResultLogger;


final public class GameOrigin<P extends GameParam>{
	protected 	P param;
	private GameStrategy<P> strategy;
	private EvolutionStrategy evolution;
	ResultLogger rPrinter;
	NetworkPrinter nPrinter;
	private Outputer out;
	private GameResultAddtion<P> addtion;


	static public class GameBuilder<P extends GameParam> {

		private GameStrategy<P> strategy;
		private EvolutionStrategy evolution;
		ResultLogger rPrinter;
		NetworkPrinter nPrinter;
		private Outputer out;
		private GameResultAddtion<P> addtion;
		protected 	P param;
		public GameOrigin<P> build(){
			return new GameOrigin<P>(this);
		}

		public GameBuilder<P> setOutputer(Outputer _out) {
			out=_out;
			return this;
		}

		public GameBuilder<P> setAddtion(GameResultAddtion<P> _ad){
			addtion=_ad;
			return this;
		}

		public GameBuilder<P> SetParam(P _param) {
			param=_param;
			return this;

		}
		public GameBuilder<P> setStrategy(GameStrategy<P> _s){
			strategy=_s;
			return this;
		}

		public GameBuilder<P> setEvolutionStrategy(EvolutionStrategy _e){
			evolution=_e;
			return this;
		}
		public GameBuilder<P> setrPrinter(ResultLogger _rPrinter) {
			rPrinter=_rPrinter;
			return this;
		}
		public GameBuilder<P> setnPrinter(NetworkPrinter _nPrinter) {
			nPrinter=_nPrinter;
			return this;
		}

	}

	public GameOrigin(GameBuilder<P> b) {
		this.strategy=b.strategy;
		this.addtion=b.addtion;
		this.evolution=b.evolution;
		this.out=b.out;
		this.param=b.param;
		this.rPrinter=b.rPrinter;
		this.nPrinter=b.nPrinter;
	}

	public GameOrigin(){}
	public GameOrigin<P> setOutputer(Outputer _out) {
		out=_out;
		return this;
	}

	public GameOrigin<P> setAddtion(GameResultAddtion<P> _ad){
		addtion=_ad;
		return this;
	}

	public GameOrigin<P> SetParam(P _param) {
		param=_param;
		return this;

	}
	public GameOrigin<P> setStrategy(GameStrategy<P> _s){
		strategy=_s;
		return this;
	}

	public GameOrigin<P> setEvolutionStrategy(EvolutionStrategy _e){
		evolution=_e;
		return this;
	}
	public GameOrigin<P> setrPrinter(ResultLogger _rPrinter) {
		rPrinter=_rPrinter;
		return this;
	}
	public GameOrigin<P> setnPrinter(NetworkPrinter _nPrinter) {
		nPrinter=_nPrinter;
		return this;
	}


	private void beforeRun(){
		out.create(param.fileName);		//param.fileName
		out.printhead(param.headlist);
//		rPrinter=new ResultLogger(param.fileName+".csv");	//動的ネットワーク用
//		nPrinter.init(rPrinter.getWriter(param.fileName),param.getAgentList().size());//動的ネットワーク用

	}
	private void afterRun(){
//		rPrinter.close();
//		nPrinter.fin(param);	//動的ネットワーク用
		out.close();
	}

	public void run() {





		beforeRun();
		strategy.init(param);
		for (int j = 0; j < param.GenerationTime; j++) {
			strategy.initGeneration(param);
			for (int i = 0; i < 4; i++) {
				strategy.initGame(param);
				strategy.execGame(param,i);
			}
			ArrayList<Double> list_result=outputResult(j);
			if(j>1980) {
//				nPrinter.print(param, j);    //動的ネットワーク用
			}
			ArrayList<Double> list_evolution = evolution.evolution(param);
//			list_result.addAll(list_evolution);	//ゲームと進化の間の状態を出力したい場合
			out.output(j,list_result);




		}
		afterRun();
	}




	public ArrayList outputResult(int now) {
		ArrayList<Double> list = new ArrayList<Double>();

		double bave = 0;
		for (Agent agent : param.getAgentList()) {
			bave += agent.getPosting();
		}
		bave /= param.getAgentList().size();

		double vave = 0;
		for (Agent agent : param.getAgentList()) {
			vave += agent.getVengefulness();
		}
		vave /= param.getAgentList().size();

		double rave = 0;
		for (Agent agent : param.getAgentList()) {
			rave += agent.getRewardness();
		}
		rave /= param.getAgentList().size();
		//String res=(""+bave+","+vave+","+rave);
		list.add(bave);
		list.add(vave);
		list.add(rave);

		double scoreave = 0;
		double maxscore = 0;
		double minscore = java.lang.Double.NaN;
		for (Agent agent : param.getAgentList()) {
			scoreave += agent.score;

			if (maxscore < agent.score) maxscore = agent.score;
			if (java.lang.Double.isNaN(minscore)) {
				minscore = agent.score;
			} else if (minscore > agent.score) {
				minscore = agent.score;
			}

		}
		scoreave /= param.getAgentList().size();


		if (addtion != null) {
			list.addAll(addtion.getPrint(param, now));
		}

		list.add(maxscore);
		list.add(minscore);
		list.add(scoreave);


		//全てのエージェントに対して追加でアウトプットしたいこと
		for (Agent agent : param.getAgentList()) {
//			list.add(agent.score);

////		//エージェントが一世代で行った記事投稿回数(最大4)
//
//			list.add(agent.kizi);
//			agent.kizi=0;
//
//			//エージェントが一世代で行ったコメント回数
//			list.add(agent.comment);
//			agent.comment=0;


			//先頭のエージェントに対してコメントした回数
//			list.add(agent.comment_to_who.get(0));
//			agent.comment_to_who.set(0,0.0);

			//エージェントが一世代で記事投稿をフリーライドした回数
//			list.add(agent.freeride_kizi);
//			agent.freeride_kizi=0;
//
//			//エージェントが一世代で自分に対するコメントをフリーライドした回数
//			list.add(agent.freeride_comment);
//			agent.freeride_comment=0;

			//エージェントの投稿率
//			list.add(agent.getPosting());	//B
//			list.add(agent.getRewardness()); //L

//
		}

		double comment = 0;
		//エージェントのコメント回数平均
//		for(Agent agent: param.getAgentList()) {
//			list.add(agent.comment);
//			comment += agent.comment;
//			agent.comment=0;
//		}
//		list.add(comment/param.getAgentList().size());
		//エージェントのid
		for (Agent agent : param.getAgentList()) {
//			list.add((double)agent.id);
		}

		//エージェントのスコア　※[default]
		for (Agent agent : param.getAgentList()) {
//			list.add(agent.score);
		}

		//エージェントの次数 ※[default]
		for (Agent agent : param.getAgentList()) {
//			list.add((double)(param.getNeighbor(agent).size()));


		}
		for (Agent agent : param.getAgentList()) {
			//エージェントの投稿率
			list.add(agent.getRewardness()); //L ※[default]
		}
		for (Agent agent : param.getAgentList()) {
			//エージェントの投稿率
//			list.add(agent.getPosting());    //B ※[default]
		}
		for (Agent agent : param.getAgentList()) {
			//突然変異したビット数
//			list.add((double)agent.mutation_count); //※[default]
		}
		for (Agent agent : param.getAgentList()){
			//エージェントが一世代で行った記事投稿回数
//			list.add(agent.kizi);
//			agent.kizi = 0;
		}

		for (Agent agent : param.getAgentList()){
			//エージェントが一世代で行ったコメント回数
//			list.add(agent.comment);
//			agent.comment = 0;
		}
		for (Agent agent : param.getAgentList()){
			//エージェントが一世代で読んだ記事投稿数
//			list.add(agent.read_kizi);
//			agent.read_kizi = 0;
		}

		for (Agent agent : param.getAgentList()){
			//エージェントが一世代で受け取ったコメント数
//			list.add(agent.recieve_comment);
//			if(agent.id==175){ System.out.println("-------------------["+now+"] comment:"+agent.recieve_comment+"----------------");}
//			agent.recieve_comment = 0;
		}




			//あるエージェントに対して追加でアウトプットしたいこと


//		//あるエージェント
		ArrayList<Integer> A = new ArrayList<Integer>(){
			{
//				add(485);
//				add(param.getAgentList().size()/2);
//				add(param.getAgentList().size()-1);
//				add(992);
			}
		};

		for(Integer id : A) {

			//あるエージェントが行ったコメント相手の内訳
//			list.add(param.getAgentList().get(id).comment);
//			param.getAgentList().get(id).comment = 0;
//			for (int i = 0; i < param.getAgentList().size(); i++) {
//				list.add(param.getAgentList().get(id).comment_to_who.get(i));
//				param.getAgentList().get(id).comment_to_who.set(i, 0.0);
//			}

			//利得
//			list.add(param.getAgentList().get(id).score);
//
//
////			あるエージェントが親に選ばれた回数
//			list.add(param.getAgentList().get(id).parents_count);
//			param.getAgentList().get(id).parents_count=0;
//
//			//記事投稿した回数
//			list.add(param.getAgentList().get(id).kizi);
//			param.getAgentList().get(id).kizi=0;
//			//記事投稿率
//			list.add(param.getAgentList().get(id).getPosting());    //B ※[default]
//			//コメント投稿した回数
//			list.add(param.getAgentList().get(id).comment);
//			param.getAgentList().get(id).comment=0;
//			//コメント投稿率
//			list.add(param.getAgentList().get(id).getRewardness());
		}

//
//		//あるエージェント周りで出力したいこと
//		int id = 485; //あるエージェント
		int id=0;
		for(Agent agent: param.getNeighbor(param.getAgentList().get(id))) {
//
//			//id
//			list.add((double) agent.id);
		}
//			//次数
		for(Agent agent: param.getNeighbor(param.getAgentList().get(id))) {
//			list.add((double)(param.getNeighbor(agent).size()));
		}
//
//		for(Agent agent: param.getNeighbor(param.getAgentList().get(id))) {
//
//			//利得
//			list.add(agent.score);
//		}
//		for(Agent agent: param.getNeighbor(param.getAgentList().get(id))) {
//
////			あるエージェントが親に選ばれた回数
//			list.add(agent.parents_count);
//			agent.parents_count = 0;
//		}
		for(Agent agent: param.getNeighbor(param.getAgentList().get(id))) {
//			//記事投稿した回数
//			list.add(agent.kizi);
//			agent.kizi = 0;
		}
		for(Agent agent: param.getNeighbor(param.getAgentList().get(id))) {
			//記事投稿率
//			list.add(agent.getPosting());    //B ※[default]
		}
		for(Agent agent: param.getNeighbor(param.getAgentList().get(id))) {
//			//コメント投稿した回数
//
//			list.add(agent.comment);
//			agent.comment = 0;
		}
		for(Agent agent: param.getNeighbor(param.getAgentList().get(id))) {
			//コメント投稿率
//			list.add(agent.getRewardness());
		}



//		rPrinter.println(res);
//		out.output( now, list);
		return list;
	}





}

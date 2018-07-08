package socialmedia.game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import network.Agent;
import network.AgentNetwork;
import network.Network;
import network.NetworkInfo;
import network.NetworkPrinter;

import socialmedia.evolutions.EvolutionStrategy;
import socialmedia.evolutions.EvolutionToriumi;
import socialmedia.gameStrategy.ReciprocityStrategy.RecipParam;
import utility.AverageOutputer;
import utility.EachOutputer;
import utility.MyRandom;
import utility.ResultLogger;

public class GameExecutor {

	static public class ExecParamBuilder{
		int getaTrialTime=0,TrialTime=100;
		boolean useAverageOutput=false;
		boolean useS0=false;
		boolean useDynnet=false;

		int GenerationTime=10000;
		int startSeed=1005;

		MyRandom m1;

		List<Double> S0s;

		List<Integer> Cs;
		List<Double> Rs;
		List<Double> LDmaxs;

		List<Double> Bmaxs;	//2017/02/03　追加

//		NetworkInfo agents;  2016/12/08　コメントアウト
		Network agents;
		int N;	//人数
		
		ResultLogger resultLogger;

		ArrayList<String> head;

		Class<? extends GameStrategy<RecipParam>> strClass;
		GameResultAddtion<RecipParam> addtion;

        Class<? extends EvolutionStrategy> evolution;

        String Data; //追加で書くこと


		public ExecParamBuilder(){}
		public ExecParamBuilder setTrialTime(int n){TrialTime=n;return this;}
		public ExecParamBuilder setTrialTime(int n, int offset){setTrialTime(n);getaTrialTime=offset;return this;}
		public ExecParamBuilder useAverageOutouter(boolean _t){useAverageOutput=_t;return this;}
		public ExecParamBuilder setDynnet(boolean _t){useDynnet=_t;return this;}
		public ExecParamBuilder setS0Stream(List<Double> ds){S0s=ds;useS0=true;return this;}
		public ExecParamBuilder setGenerationTime(int gn){GenerationTime=gn;return this;}
		public ExecParamBuilder setStartSeed(int n){startSeed=n; m1 = new MyRandom(startSeed); return this;}
		public ExecParamBuilder setCList(List<Integer> cs){Cs=cs;return this;}
		public ExecParamBuilder setRList(List<Double> rs){Rs=rs;return this;}
		public ExecParamBuilder setLDList(List<Double> lds){LDmaxs=lds;return this;}
		public ExecParamBuilder setBList(List<Double> bs){Bmaxs=bs;return this;}		//2017/02/03 作成
//		public ExecParamBuilder setNetworkInfo(NetworkInfo ag){agents=ag;return this;}	2016/12/08　コメントアウト
		public ExecParamBuilder setNetworkInfo(Network ag){agents=ag;return this;}	//2016/12/08　作成
		public ExecParamBuilder setStrategy(Class<? extends GameStrategy<RecipParam>> str){strClass=str;return this;}
        public ExecParamBuilder setEvolution(Class<? extends EvolutionStrategy> evo){evolution=evo;return this;}
		public ExecParamBuilder setLogger(ResultLogger rl){resultLogger=rl;return this;}
		public ExecParamBuilder setAddtion(GameResultAddtion<RecipParam> gr){addtion=gr;return this;}
		public ExecParamBuilder setHeader(List<String> _list){head=new ArrayList<String>(_list);return this;}
		public ExecParamBuilder setN(int n){N=n; return this;}
		public ExecParamBuilder setAnydata(String data){Data = data; return this;}
		public ExecParam build(){return new ExecParam(this);}
	}

	static public class ExecParam{
		public ExecParam(ExecParamBuilder epb){
			getaTrialTime=epb.getaTrialTime;
			TrialTime=epb.TrialTime;
			useAverageOutput=epb.useAverageOutput;
			useS0=epb.useS0;
			useDynnet=epb.useDynnet;
			GenerationTime=epb.GenerationTime;
			startSeed=epb.startSeed;
			m1 = epb.m1;
			S0s=epb.S0s;
			Cs=epb.Cs;
			Rs=epb.Rs;
			LDmaxs=epb.LDmaxs;
			Bmaxs=epb.Bmaxs;
			agents=epb.agents;
			strategy=epb.strClass;
			N = epb.N;
			Data = epb.Data;

			resultLogger=epb.resultLogger.clone();
			addtion=epb.addtion;
			head=epb.head;

            evolution=epb.evolution;
		}

		ArrayList<String> head;
		int getaTrialTime,TrialTime;
		boolean useAverageOutput=true;
		boolean useS0=false;
		boolean useDynnet=false;
		int GenerationTime;
		int startSeed=1005;
		MyRandom m1;
		List<Double> S0s;
		List<Integer> Cs;
		List<Double> Rs;
		List<Double> LDmaxs;
		List<Double> Bmaxs;
//		NetworkInfo agents;		2016/12/08 コメントアウト
		Network agents;			//2016/12/08追加
		int N;
		String Data;
		
		
		Class<? extends GameStrategy<RecipParam>> strategy;
		private ResultLogger resultLogger;
		GameResultAddtion<RecipParam> addtion;

        Class<? extends EvolutionStrategy> evolution;

	}

	static class ChangeParams{
		boolean useS0=false;
		double S,R;
		double LD;
		double B;
		int C;
		int trial_num;
		public ChangeParams(double _S,double _R,int _C, double _LD,double _B, int _trial_num) {S=_S;C=_C;R=_R;LD=_LD;B=_B;trial_num=_trial_num;useS0=true;}
		public ChangeParams(double _R,int _C, double _LD,double _B, int _trial_num){C=_C;R=_R;LD=_LD;B=_B;trial_num=_trial_num;}
	}

	static public void start(ExecParam ep) {
		long start = System.currentTimeMillis();


		ExecutorService exec = Executors.newFixedThreadPool(8);




for(int _trial=ep.getaTrialTime;_trial<ep.TrialTime;_trial++){
		final int trial_num=_trial;
				if(ep.S0s==null){
					for (int _C : ep.Cs) {
						for (double _R : ep.Rs) {
							for (double _LD : ep.LDmaxs) {
								for (double _B : ep.Bmaxs) {
									exec.submit(() -> {
										try {
											oneExec(new ChangeParams(_R, -_C, _LD, _B, trial_num), ep);
										} catch (Exception e) {
											e.printStackTrace();
										}
									});
								}
							}
						}
					}

				}else {

					for (double _S : ep.S0s) {
						for (int _C : ep.Cs) {
							for (double _R : ep.Rs) {
								for (double _LD : ep.LDmaxs) {
									for (double _B : ep.Bmaxs) {
										exec.submit(() -> {
											try {
												oneExec(new ChangeParams(_S, _R, -_C, _LD, _B, trial_num), ep);
											} catch (Exception e) {
												e.printStackTrace();
											}

										});
									}
								}
							}
						}
					}
				}
}

		 exec.shutdown();
		 try {
			exec.awaitTermination(7, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

			//計測したい処理を記述

			long end = System.currentTimeMillis();
		 	double time = end-start;
			System.out.println("["+time + "ms|"+time/1000.0+"s|"+(time/1000.0)/60.0+"min|"+((time/1000.0)/60.0)/60.0+"h]");
	}




	private static void oneExec(ChangeParams cp,ExecParam ep){
		GameStrategy<RecipParam> strategy = null;
        EvolutionStrategy evolutionstrategy = null;
		try {
			strategy = ep.strategy.newInstance();
            evolutionstrategy = ep.evolution.newInstance();

		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		String name=strategy.getClass().getSimpleName();
		if(cp.useS0){
		name+="+S"+String.format("%.2f", cp.S);
		}
		name+="+B"+String.format("%.2f", cp.B)+"+LD"+String.format("%.2f", cp.LD)+"C"+-cp.C+"R"+String.format("%.2f", cp.R)+ep.N+"+"+ep.Data;

		AverageOutputer ao=null;
		if(ep.useAverageOutput){
			ao=new AverageOutputer(ep.resultLogger,name);
			throw new RuntimeException();
		}


//		for (int i = ep.getaTrialTime; i < ep.TrialTime; i++) {
			RecipParam param=new RecipParam();
			param.init();
			param.fileName=name+"-"+cp.trial_num;

			MyRandom m2=new MyRandom(ep.startSeed+cp.trial_num);	//ストラテジー用ランダムシード シード値++ver			2016/10/27
			MyRandom m3=new MyRandom(ep.startSeed+999*cp.trial_num);	//ネットワーク用ランダムシード
//			MyRandom m3=new MyRandom(ep.startSeed); 					//ネットワーク用固定シード


//			int seed = ep.m1.nextInt();
//			MyRandom m2 = new MyRandom(seed);		//シード値ランダムver		2016/10/27
//			System.out.println(seed);

			param.GenerationTime=ep.GenerationTime;
//			NetworkInfo ni=ep.agents
			
		
			//ここでネットワークを作りたい
			NetworkInfo ni=ep.agents.BuildNetwork(m3);
			NetworkPrinter.printNetworkForCyto2(ni, ep.resultLogger.getWriter(ep.agents.NetworkName(ep.startSeed+999*cp.trial_num)+".tsv"));


			
			
			param.agents=new AgentNetwork(ni,m2);//ep.agents;
			//param.agents.resetAgents(m2);

			for (Agent agent : param.agents.getAgentList()) {
				agent.LDrate=cp.LD;
				agent.Brate=cp.B;
			}
			param.random=m2;
			param.headlist=ep.head;

			param.setRC(cp.R, cp.C);
			if(cp.useS0){
				param.setS0(cp.S);
			}
			GameOrigin<RecipParam> game=new GameOrigin<RecipParam>();
			game.SetParam(param);

			if(ep.useAverageOutput){
				game.setOutputer(ao);
			}else{
				game.setOutputer(new EachOutputer(ep.resultLogger));
			}
			if(ep.addtion!=null){
				game.setAddtion(ep.addtion);
			}
			if(ep.useDynnet) {
				game.setnPrinter(new NetworkPrinter());
				game.setrPrinter(ep.resultLogger);
			}
			game.setStrategy(strategy);
			game.setEvolutionStrategy(evolutionstrategy);	//EvolutionToriumi, EvolutionAxelrod
			game.run();
	//	}
		if(ep.useAverageOutput){
			ao.print();
		}

	}
}

package socialmedia.gameEnv.executors;

import java.util.Arrays;

import network.Network;
import network.NetworkInfo;
import network.NetworkInfoFactory;
import network.NetworkPrinter;
import network.WSmodel;
import socialmedia.evolutions.EvolutionToriumi;

import socialmedia.game.GameExecutor;
import socialmedia.game.GameExecutor.ExecParamBuilder;
import socialmedia.gameStrategy.*;
import utility.MyRandom;
import utility.ResultLogger;


public class WSgameFocusOne {


	public void start() {

		ResultLogger a = new ResultLogger("WSgame-networks-toriumiEvo-100回平均");


//		for(int i=1000;i<=10000;i+=1000) {
		int i = 100;
		int k = 40;
		double pp = 0.0;
//		for(double pp =0.01;pp<=0.2;pp+=0.01)
		{

//			ResultLogger d = a.subDir("ws-" + i + "-" + k+ "-"+pp);
			ResultLogger d = a.subDir("ws-log");
			Network ins = new WSmodel(i, k, pp);
			GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
			epb.setNetworkInfo(ins)
					.setStrategy(LogNoReciprocityMetaRewardStrategy.class)
					.setGenerationTime(2000)
					.setLogger(d)
					.setStartSeed(1005)
					.setBList(Arrays.asList(1.0))
					.setCList(Arrays.asList(2))
					.setLDList(Arrays.asList(1.0))
					.setDynnet(true)
					.setRList(Arrays.asList(9.0))
					.setAnydata(i+"-"+k+"-"+pp)
					.setEvolution(EvolutionToriumi.class)
					.setHeader(Arrays.asList("B", "LC", "LD", "B1", "LC1", "LD1", "all", "active", "mutual", "oneside"))
					.setAddtion((p, gen) -> {
						//if(gen<100)np.printAgentForCSV(p.agents,gen);
						double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
						return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getRewardness(), p.getAgentList().get(p.getAgentList().size() / 2).getPosting(), p.getAgentList().get(p.getAgentList().size() / 2).getRewardness(), p.getAgentList().get(p.getAgentList().size() - 1).getPosting(), p.getAgentList().get(p.getAgentList().size() - 1).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
					})
					.setTrialTime(1);    //試行回数
			GameExecutor.ExecParam ep = epb.build();
			GameExecutor.start(ep);
		}


//		for(double i=0.1;i<=1.0;i+=0.1) {
////        int i=1000;
//			{
//
//				ResultLogger d = a.subDir("ws-1000-20-"+i+"-log");
//				Network ins = new WSmodel(1000,20,i);
//				GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//				epb.setNetworkInfo(ins)
//						.setStrategy(LogNoReciprocityStrategy.class)
//						.setGenerationTime(1000)
//						.setLogger(d)
//						.setStartSeed(1005)
//						.setBList(Arrays.asList(1.0))
//						.setCList(Arrays.asList(2))
//						.setLDList(Arrays.asList(1.0))
//						.setRList(Arrays.asList(9.0))
//						.setEvolution(EvolutionToriumi.class)
//						.setHeader(Arrays.asList("B", "LC", "LD", "B1", "LC1", "LD1", "all", "active", "mutual", "oneside"))
//						.setAddtion((p, gen) -> {
//							//if(gen<100)np.printAgentForCSV(p.agents,gen);
//							double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
//							return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getVengefulness(), p.getAgentList().get(0).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
//						})
//						.setTrialTime(1);    //試行回数
//				GameExecutor.ExecParam ep = epb.build();
//				GameExecutor.start(ep);
//			}
//		}

//		{
//
//			ResultLogger d=a.subDir("ws-1000-20-0.1-Log");
//			Network ins = new WSmodel(1000, 20, 0.1);
//			GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(LogNoReciprocityMetaRewardStrategy.class)
//					.setGenerationTime(1000)
//					.setLogger(d)
//					.setStartSeed(1005)
//					.setCList(Arrays.asList(2))
//					.setLDList(Arrays.asList(1.0))
//					.setRList(Arrays.asList(9.0))
//					.setEvolution(EvolutionToriumi.class)
//					.setHeader(Arrays.asList("B", "LC", "LD", "B1", "LC1", "LD1", "all", "active", "mutual", "oneside"))
//					.setAddtion((p, gen) -> {
//						//if(gen<100)np.printAgentForCSV(p.agents,gen);
//						double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
//						return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getVengefulness(), p.getAgentList().get(0).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
//					})
//					.setTrialTime(1);    //試行回数
//			GameExecutor.ExecParam ep = epb.build();
//			GameExecutor.start(ep);
//		}
//
//		{
//
//			ResultLogger d=a.subDir("ws-1000-20-0.1-Log");
//			Network ins = new WSmodel(1000, 20, 0.1);
//			GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(LogNoReciprocitySNSnormStrategy.class)
//					.setGenerationTime(1000)
//					.setLogger(d)
//					.setStartSeed(1005)
//					.setCList(Arrays.asList(2))
//					.setLDList(Arrays.asList(1.0))
//					.setRList(Arrays.asList(9.0))
//					.setEvolution(EvolutionToriumi.class)
//					.setHeader(Arrays.asList("B", "LC", "LD", "B1", "LC1", "LD1", "all", "active", "mutual", "oneside"))
//					.setAddtion((p, gen) -> {
//						//if(gen<100)np.printAgentForCSV(p.agents,gen);
//						double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
//						return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getVengefulness(), p.getAgentList().get(0).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
//					})
//					.setTrialTime(1);    //試行回数
//			GameExecutor.ExecParam ep = epb.build();
//			GameExecutor.start(ep);
//		}
//
//		{
//
//			ResultLogger d=a.subDir("ws-1000-20-0.1-Log");
//			Network ins = new WSmodel(1000, 20, 0.1);
//			GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(LogReciprocityStrategy.class)
//					.setGenerationTime(1000)
//					.setLogger(d)
//					.setStartSeed(1005)
//					.setCList(Arrays.asList(2))
//					.setLDList(Arrays.asList(1.0))
//					.setRList(Arrays.asList(9.0))
//					.setEvolution(EvolutionToriumi.class)
//					.setHeader(Arrays.asList("B", "LC", "LD", "B1", "LC1", "LD1", "all", "active", "mutual", "oneside"))
//					.setAddtion((p, gen) -> {
//						//if(gen<100)np.printAgentForCSV(p.agents,gen);
//						double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
//						return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getVengefulness(), p.getAgentList().get(0).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
//					})
//					.setTrialTime(1);    //試行回数
//			GameExecutor.ExecParam ep = epb.build();
//			GameExecutor.start(ep);
//		}
//
//		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//		{
//
//			ResultLogger d=a.subDir("ws-1000-20-0.1");
//			Network ins = new WSmodel(1000, 20, 0.1);
//			GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(ReciprocityNotExistsStrategy.class)
//					.setGenerationTime(1000)
//					.setLogger(d)
//					.setStartSeed(1005)
//					.setCList(Arrays.asList(2))
//					.setLDList(Arrays.asList(1.0))
//					.setRList(Arrays.asList(9.0))
//					.setEvolution(EvolutionToriumi.class)
//					.setHeader(Arrays.asList("B", "LC", "LD", "B1", "LC1", "LD1", "all", "active", "mutual", "oneside"))
//					.setAddtion((p, gen) -> {
//						//if(gen<100)np.printAgentForCSV(p.agents,gen);
//						double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
//						return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getVengefulness(), p.getAgentList().get(0).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
//					})
//					.setTrialTime(1);    //試行回数
//			GameExecutor.ExecParam ep = epb.build();
//			GameExecutor.start(ep);
//		}
//
//		{
//
//			ResultLogger d=a.subDir("ws-1000-20-0.1");
//			Network ins = new WSmodel(1000, 20, 0.1);
//			GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(NoReciprocityMetaRewardStrategy.class)
//					.setGenerationTime(1000)
//					.setLogger(d)
//					.setStartSeed(1005)
//					.setCList(Arrays.asList(2))
//					.setLDList(Arrays.asList(1.0))
//					.setRList(Arrays.asList(9.0))
//					.setEvolution(EvolutionToriumi.class)
//					.setHeader(Arrays.asList("B", "LC", "LD", "B1", "LC1", "LD1", "all", "active", "mutual", "oneside"))
//					.setAddtion((p, gen) -> {
//						//if(gen<100)np.printAgentForCSV(p.agents,gen);
//						double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
//						return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getVengefulness(), p.getAgentList().get(0).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
//					})
//					.setTrialTime(1);    //試行回数
//			GameExecutor.ExecParam ep = epb.build();
//			GameExecutor.start(ep);
//		}
//
//		{
//
//			ResultLogger d=a.subDir("ws-1000-20-0.1");
//			Network ins = new WSmodel(1000, 20, 0.1);
//			GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(NoReciprocitySNSnormStrategy.class)
//					.setGenerationTime(1000)
//					.setLogger(d)
//					.setStartSeed(1005)
//					.setCList(Arrays.asList(2))
//					.setLDList(Arrays.asList(1.0))
//					.setRList(Arrays.asList(9.0))
//					.setEvolution(EvolutionToriumi.class)
//					.setHeader(Arrays.asList("B", "LC", "LD", "B1", "LC1", "LD1", "all", "active", "mutual", "oneside"))
//					.setAddtion((p, gen) -> {
//						//if(gen<100)np.printAgentForCSV(p.agents,gen);
//						double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
//						return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getVengefulness(), p.getAgentList().get(0).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
//					})
//					.setTrialTime(1);    //試行回数
//			GameExecutor.ExecParam ep = epb.build();
//			GameExecutor.start(ep);
//		}
//
//		{
//
//			ResultLogger d=a.subDir("ws-1000-20-0.1");
//			Network ins = new WSmodel(1000, 20, 0.1);
//			GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(ReciprocityStrategy.class)
//					.setGenerationTime(1000)
//					.setLogger(d)
//					.setStartSeed(1005)
//					.setCList(Arrays.asList(2))
//					.setLDList(Arrays.asList(1.0))
//					.setRList(Arrays.asList(9.0))
//					.setEvolution(EvolutionToriumi.class)
//					.setHeader(Arrays.asList("B", "LC", "LD", "B1", "LC1", "LD1", "all", "active", "mutual", "oneside"))
//					.setAddtion((p, gen) -> {
//						//if(gen<100)np.printAgentForCSV(p.agents,gen);
//						double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
//						return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getVengefulness(), p.getAgentList().get(0).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
//					})
//					.setTrialTime(1);    //試行回数
//			GameExecutor.ExecParam ep = epb.build();
//			GameExecutor.start(ep);
//		}

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*
		{
			NetworkInfo ins=NetworkInfoFactory.initBA(1000, 20,10, new MyRandom(999));
			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
			epb.setNetworkInfo(ins)
			.setStrategy(ReciprocityStrategy.class)
			.setGenerationTime(2000)
			.setLogger(c)
			.setStartSeed(1005)
			.setCList(Arrays.asList(2))
			.setLDList(Arrays.asList(1.0))
			.setRList(Arrays.asList(9.0))
			.setAddtion((p,gen)->{
				return Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness());
			})
			.setTrialTime(5);
			GameExecutor.ExecParam ep= epb.build();
			GameExecutor.start(ep);
		}




		{
			ResultLogger d=a.subDir("ws-1000-20-0.01-forCyto");
			NetworkPrinter np= new NetworkPrinter();
			np.init(d.getWriter("ws-1000-20-0.01.csv"));

			NetworkInfo ins=NetworkInfoFactory.initWS(1000, 20, 0, new MyRandom(999));
			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
			epb.setNetworkInfo(ins)
			.setStrategy(ReciprocityStrategy.class)
			.setGenerationTime(2000)
			.setLogger(d)
			.setStartSeed(1005)
			.setCList(Arrays.asList(2))
			.setLDList(Arrays.asList(1.0))
			.setRList(Arrays.asList(9.0))
			.setAddtion((p,gen)->{
				if(gen<100)np.printAgentForCSV(p.agents,gen);
				return Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness());
			})
			.setTrialTime(1);
			GameExecutor.ExecParam ep= epb.build();
			GameExecutor.start(ep);

			np.printNetworkForCSV(ins);
			np.close();
		}
		
		
//2016/12/08コメントアウト　ネットワーク毎回作成前のコード
*/
//		{
//			ResultLogger d=a.subDir("ws-1000-20-0.1-friendnum-log");
//
//			for(int i=0;i<10;i++) {
//				ResultLogger s=d.subDir("seed="+(999+1300*i));
//				NetworkInfo ins = NetworkInfoFactory.initWS(1000, 20, 0.1, new MyRandom(999+130*i));
//				NetworkPrinter.printNetworkForCyto2(ins, s.getWriter("ws-1000-20-0.1-"+(999+130*i)+".tsv"));
//				GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//				epb.setNetworkInfo(ins)
//						.setStrategy(LogNoReciprocityMetaRewardStrategy.class)
//						.setGenerationTime(1000)
//						.setLogger(s)
//						.setStartSeed(1005)
//						.setCList(Arrays.asList(2))
//						.setLDList(Arrays.asList(1.0))
//						.setRList(Arrays.asList(9.0))
//						.setEvolution(EvolutionToriumi.class)
//						.setHeader(Arrays.asList("B", "LC", "LD", "B1", "LC1", "LD1", "all", "active", "mutual", "oneside"))
//						.setAddtion((p, gen) -> {
//							//if(gen<100)np.printAgentForCSV(p.agents,gen);
//							double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
//							return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getVengefulness(), p.getAgentList().get(0).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
//						})
//						.setTrialTime(1);    //試行回数
//				GameExecutor.ExecParam ep = epb.build();
//				GameExecutor.start(ep);
//			}
//		}

//		{
//			ResultLogger d=a.subDir("ws-1000-20-0.1-friendnum-log");
//
//			for(int i=0;i<10;i++) {
//				ResultLogger s=d.subDir("seed="+(999+1300*i));
//				NetworkInfo ins = NetworkInfoFactory.initWS(1000, 20, 0.1, new MyRandom(999+130*i));
//				NetworkPrinter.printNetworkForCyto2(ins, s.getWriter("ws-1000-20-0.1-"+(999+130*i)+".tsv"));
//				GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//				epb.setNetworkInfo(ins)
//						.setStrategy(LogNoReciprocityStrategy.class)
//						.setGenerationTime(1000)
//						.setLogger(s)
//						.setStartSeed(1005)
//						.setCList(Arrays.asList(2))
//						.setLDList(Arrays.asList(1.0))
//						.setRList(Arrays.asList(9.0))
//						.setEvolution(EvolutionToriumi.class)
//						.setHeader(Arrays.asList("B", "LC", "LD", "B1", "LC1", "LD1", "all", "active", "mutual", "oneside"))
//						.setAddtion((p, gen) -> {
//							//if(gen<100)np.printAgentForCSV(p.agents,gen);
//							double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
//							return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getVengefulness(), p.getAgentList().get(0).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
//						})
//						.setTrialTime(1);    //試行回数
//				GameExecutor.ExecParam ep = epb.build();
//				GameExecutor.start(ep);
//			}
//			
//		}
//		{
//			ResultLogger d=a.subDir("ws-1000-20-0.1-friendnum");
//
//			for(int i=0;i<10;i++) {
//				ResultLogger s=d.subDir("seed="+(999+1300*i));
//				NetworkInfo ins = NetworkInfoFactory.initWS(1000, 20, 0.1, new MyRandom(999+130*i));
//				NetworkPrinter.printNetworkForCyto2(ins, s.getWriter("ws-1000-20-0.1-"+(999+130*i)+".tsv"));
//				GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//				epb.setNetworkInfo(ins)
//						.setStrategy(NoReciprocityMetaRewardStrategy.class)
//						.setGenerationTime(1000)
//						.setLogger(s)
//						.setStartSeed(1005)
//						.setCList(Arrays.asList(2))
//						.setLDList(Arrays.asList(1.0))
//						.setRList(Arrays.asList(9.0))
//						.setEvolution(EvolutionToriumi.class)
//						.setHeader(Arrays.asList("B", "LC", "LD", "B1", "LC1", "LD1", "all", "active", "mutual", "oneside"))
//						.setAddtion((p, gen) -> {
//							//if(gen<100)np.printAgentForCSV(p.agents,gen);
//							double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
//							return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getVengefulness(), p.getAgentList().get(0).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
//						})
//						.setTrialTime(1);    //試行回数
//				GameExecutor.ExecParam ep = epb.build();
//				GameExecutor.start(ep);
//			}
//			
//		}

//		{
//			ResultLogger d=a.subDir("ws-1000-20-0.01-friendnum");
//
//
//			NetworkInfo ins=NetworkInfoFactory.initWS(1000, 20, 0.01, new MyRandom(999));
//			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(NoReciprocityMetaRewardStrategy.class)		//WSモデル互恵メタ報酬ゲーム
//					.setGenerationTime(10000)
//					.setLogger(d)
//					.setStartSeed(1005)
//					.setCList(Arrays.asList(2))
//					.setLDList(Arrays.asList(1.0))
//					.setRList(Arrays.asList(9.0))
//					.setEvolution(EvolutionToriumi.class)
//					.setHeader(Arrays.asList("B","LC","LD","B1","LC1","LD1","all","active","mutual","oneside"))
//					.setAddtion((p,gen)->{
//						//if(gen<100)np.printAgentForCSV(p.agents,gen);
//						double onlynoRecip=p.getSumofFriendLinkNum()-p.getSumofRecipFriendLinkNum();
//						return  Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness(),(double)ins.getPathCount()*2,  p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(),onlynoRecip);
//					})
//					.setTrialTime(100);	//試行回数
//			GameExecutor.ExecParam ep= epb.build();
//			GameExecutor.start(ep);
//
//		}
//
//
//		{
//			ResultLogger d=a.subDir("ws-1000-20-0.05-friendnum");
//
//
//			NetworkInfo ins=NetworkInfoFactory.initWS(1000, 20, 0.05, new MyRandom(999));
//			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//			.setStrategy(ReciprocityStrategy.class)
//			.setGenerationTime(2000)
//			.setLogger(d)
//			.setStartSeed(1005)
//			.setCList(Arrays.asList(2))
//			.setLDList(Arrays.asList(1.0))
//			.setRList(Arrays.asList(9.0))
//			.setHeader(Arrays.asList("B","LC","LD","B1","LC1","LD1","all","active","mutual","oneside"))
//			.setAddtion((p,gen)->{
//				//if(gen<100)np.printAgentForCSV(p.agents,gen);
//				double onlynoRecip=p.getSumofFriendLinkNum()-p.getSumofRecipFriendLinkNum();
//				return  Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness(),(double)ins.getPathCount()*2,  p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(),onlynoRecip);
//				})
//			.setTrialTime(1);
//			GameExecutor.ExecParam ep= epb.build();
//			GameExecutor.start(ep);
//
//		}
//
//
//		{
//			ResultLogger d=a.subDir("ws-1000-20-0.1-friendnum");
//
//
//			NetworkInfo ins=NetworkInfoFactory.initWS(1000, 20, 0.1, new MyRandom(999));
//			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//			.setStrategy(ReciprocityStrategy.class)
//			.setGenerationTime(2000)
//			.setLogger(d)
//			.setStartSeed(1005)
//			.setCList(Arrays.asList(2))
//			.setLDList(Arrays.asList(1.0))
//			.setRList(Arrays.asList(9.0))
//			.setHeader(Arrays.asList("B","LC","LD","B1","LC1","LD1","all","active","mutual","oneside"))
//			.setAddtion((p,gen)->{
//				//if(gen<100)np.printAgentForCSV(p.agents,gen);
//				double onlynoRecip=p.getSumofFriendLinkNum()-p.getSumofRecipFriendLinkNum();
//				return  Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness(),(double)ins.getPathCount()*2,  p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(),onlynoRecip);
//				})
//			.setTrialTime(1);
//			GameExecutor.ExecParam ep= epb.build();
//			GameExecutor.start(ep);
//
//		}
//		{
//			ResultLogger d=a.subDir("ws-1000-20-0.3-friendnum");
//
//
//			NetworkInfo ins=NetworkInfoFactory.initWS(1000, 20, 0.3, new MyRandom(999));
//			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//			.setStrategy(ReciprocityStrategy.class)
//			.setGenerationTime(2000)
//			.setLogger(d)
//			.setStartSeed(1005)
//			.setCList(Arrays.asList(2))
//			.setLDList(Arrays.asList(1.0))
//			.setRList(Arrays.asList(9.0))
//			.setHeader(Arrays.asList("B","LC","LD","B1","LC1","LD1","all","active","mutual","oneside"))
//			.setAddtion((p,gen)->{
//				//if(gen<100)np.printAgentForCSV(p.agents,gen);
//				double onlynoRecip=p.getSumofFriendLinkNum()-p.getSumofRecipFriendLinkNum();
//				return  Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness(),(double)ins.getPathCount()*2,  p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(),onlynoRecip);
//				})
//			.setTrialTime(1);
//			GameExecutor.ExecParam ep= epb.build();
//			GameExecutor.start(ep);
//
//		}
//
//		{
//			ResultLogger d=a.subDir("ws-1000-20-0.00-friendnum");
//
//
//			NetworkInfo ins=NetworkInfoFactory.initWS(1000, 20, 0, new MyRandom(999));
//			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//			.setStrategy(ReciprocityStrategy.class)
//			.setGenerationTime(2000)
//			.setLogger(d)
//			.setStartSeed(1005)
//			.setCList(Arrays.asList(2))
//			.setLDList(Arrays.asList(1.0))
//			.setRList(Arrays.asList(9.0))
//			.setHeader(Arrays.asList("B","LC","LD","B1","LC1","LD1","all","active","mutual","oneside"))
//			.setAddtion((p,gen)->{
//				//if(gen<100)np.printAgentForCSV(p.agents,gen);
//				double onlynoRecip=p.getSumofFriendLinkNum()-p.getSumofRecipFriendLinkNum();
//				return  Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness(),(double)ins.getPathCount()*2,  p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(),onlynoRecip);
//				})
//			.setTrialTime(1);
//			GameExecutor.ExecParam ep= epb.build();
//			GameExecutor.start(ep);
//
//		}



	}
}

package socialmedia.gameEnv.executors;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import network.*;
import org.example.Example03;
import socialmedia.evolutions.EvolutionToriumi;

import socialmedia.game.GameExecutor;
import socialmedia.game.GameExecutor.ExecParamBuilder;
import socialmedia.gameStrategy.*;
import utility.ResultLogger;


public class GameIn20Comp {



	public void start(){
/*
*  title = "Complete",
*         "log" or "linear"
*         Number of agents,
*         generation,
*         trial
*
* */
	    String title = "Complete+"+
                        "log+"+
                        "10+"+
                        "5+"+
                        "1";

        System.out.println(title);

		ResultLogger a=new ResultLogger(title);



//		{
//			ResultLogger d=a.subDir("Comp20game-networks");
//
//
//			NetworkInfo ins= NetworkInfoFactory.initComplete(20);
//			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//			.setStrategy(NoReciprocityMetaRewardStrategy.class)			//ゲーム戦略　ReciprocityStrategy:互恵報酬ゲーム ReciprocityMetaRewardStrategy: 互恵メタ報酬ゲーム
//			.setGenerationTime(10000)						//世代数
//			.setLogger(d)									//?
//			.setStartSeed(1005)								//シード値
//			.setCList(Arrays.asList(2))						//C（コメントコスト）の値のリスト
//                    //.setS0Stream(Arrays.asList(1.0))		//記事発見率
//			.setLDList(Arrays.asList(1.0))					//Ld(通常コメント率）の最大値のリスト
//			.setRList(Arrays.asList(9.0))					//R（コメント利得）の値のリスト
//					.setEvolution(EvolutionToriumi.class)
//			.setHeader(Arrays.asList("B","LC","LD","B1","LC1","LD1","all","active","mutual","oneside"))
//			.setAddtion((p,gen)->{
//				double onlynoRecip=p.getSumofFriendLinkNum()-p.getSumofRecipFriendLinkNum();
//				return  Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness(),(double)ins.getPathCount()*2,  p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(),onlynoRecip);
//				})
//			.setTrialTime(100);
//
//			GameExecutor.ExecParam ep= epb.build();
//			GameExecutor.start(ep);
//
//		}

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//		{
//
//			ResultLogger d=a.subDir("completegraph-20-log");
//			Network ins = new CompleteGraph(20);
//			GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(LogNoReciprocityStrategy.class)
//					.setGenerationTime(10000)
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
//					.setTrialTime(100);    //試行回数
//			GameExecutor.ExecParam ep = epb.build();
//			GameExecutor.start(ep);
//		}
//
//
//		{
//
//			ResultLogger d=a.subDir("completegraph-20-log");
//			Network ins = new CompleteGraph(20);
//			GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(LogNoReciprocityMetaRewardStrategy.class)
//					.setGenerationTime(10000)
//					.setLogger(d)
//					.setStartSeed(1005)
//					.setCList(Arrays.asList(2))
//					.setLDList(Arrays.asList(1.0))
//					.setRList(Arrays.asList(1.0,2.0,2.1,2.2,2.3,2.4,2.5,2.6,2.7,2.8,2.9,3.0,3.1,3.2,3.3,3.4,3.5,3.6,3.7,3.8,3.9,4.0,5.0,6.0,7.0,8.0,9.0))
//					.setEvolution(EvolutionToriumi.class)
//					.setHeader(Arrays.asList("B", "LC", "LD", "B1", "LC1", "LD1", "all", "active", "mutual", "oneside"))
//					.setAddtion((p, gen) -> {
//						//if(gen<100)np.printAgentForCSV(p.agents,gen);
//						double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
//						return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getVengefulness(), p.getAgentList().get(0).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
//					})
//					.setTrialTime(100);    //試行回数
//			GameExecutor.ExecParam ep = epb.build();
//			GameExecutor.start(ep);
//		}

//        for(int i=20;i<=100;i+=10) {
//            {
//
//                ResultLogger d = a.subDir("completegraph-20-log");
//                Network ins = new CompleteGraph(20);
//                GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//                epb.setNetworkInfo(ins)
//                        .setStrategy(LogNoReciprocitySNSnormStrategy.class)
//                        .setGenerationTime(10000)
//                        .setLogger(d)
//                        .setStartSeed(1005)
//                        .setCList(Arrays.asList(2))
//                        .setLDList(Arrays.asList(1.0))
//                        .setRList(Arrays.asList(9.0))
//                        .setEvolution(EvolutionToriumi.class)
//                        .setHeader(Arrays.asList("B", "LC", "LD", "B1", "LC1", "LD1", "all", "active", "mutual", "oneside"))
//                        .setAddtion((p, gen) -> {
//                            //if(gen<100)np.printAgentForCSV(p.agents,gen);
//                            double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
//                            return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getVengefulness(), p.getAgentList().get(0).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
//                        })
//                        .setTrialTime(100);    //試行回数
//                GameExecutor.ExecParam ep = epb.build();
//                GameExecutor.start(ep);
//            }
//        }
//
//		{
//
//			ResultLogger d=a.subDir("completegraph-20-log");
//			Network ins = new CompleteGraph(20);
//			GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(LogReciprocityStrategy.class)
//					.setGenerationTime(10000)
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
//					.setTrialTime(100);    //試行回数
//			GameExecutor.ExecParam ep = epb.build();
//			GameExecutor.start(ep);
//		}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//        {
//
//            ResultLogger d=a.subDir("completegraph-20");
//            Network ins = new CompleteGraph(20);
//            GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//            epb.setNetworkInfo(ins)
//                    .setStrategy(ReciprocityNotExistsStrategy.class)
//                    .setGenerationTime(10000)
//                    .setLogger(d)
//                    .setStartSeed(1005)
//                    .setCList(Arrays.asList(2))
//                    .setLDList(Arrays.asList(1.0))
//                    .setRList(Arrays.asList(9.0))
//                    .setEvolution(EvolutionToriumi.class)
//                    .setHeader(Arrays.asList("B", "LC", "LD", "B1", "LC1", "LD1", "all", "active", "mutual", "oneside"))
//                    .setAddtion((p, gen) -> {
//                        //if(gen<100)np.printAgentForCSV(p.agents,gen);
//                        double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
//                        return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getVengefulness(), p.getAgentList().get(0).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
//                    })
//                    .setTrialTime(100);    //試行回数
//            GameExecutor.ExecParam ep = epb.build();
//            GameExecutor.start(ep);
//        }
//
//
//        {
//
//            ResultLogger d=a.subDir("completegraph-20");
//            Network ins = new CompleteGraph(20);
//            GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//            epb.setNetworkInfo(ins)
//                    .setStrategy(NoReciprocityMetaRewardStrategy.class)
//                    .setGenerationTime(10000)
//                    .setLogger(d)
//                    .setStartSeed(1005)
//                    .setCList(Arrays.asList(2))
//                    .setLDList(Arrays.asList(1.0))
//                    .setRList(Arrays.asList(1.0,2.0,2.1,2.2,2.3,2.4,2.5,2.6,2.7,2.8,2.9,3.0,3.1,3.2,3.3,3.4,3.5,3.6,3.7,3.8,3.9,4.0,5.0,6.0,7.0,8.0,9.0))
//                    .setEvolution(EvolutionToriumi.class)
//                    .setHeader(Arrays.asList("B", "LC", "LD", "B1", "LC1", "LD1", "all", "active", "mutual", "oneside"))
//                    .setAddtion((p, gen) -> {
//                        //if(gen<100)np.printAgentForCSV(p.agents,gen);
//                        double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
//                        return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getVengefulness(), p.getAgentList().get(0).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
//                    })
//                    .setTrialTime(100);    //試行回数
//            GameExecutor.ExecParam ep = epb.build();
//            GameExecutor.start(ep);
//        }
//よく使うやつ↓
        ResultLogger d = a.subDir("completegraph-log");
//        for(int i=20;i<=100;i+=10)
        int i=6;
            {

//    if(i==50){continue;}


                Network ins = new CompleteGraph(i);
                GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
                epb.setNetworkInfo(ins)
                        .setStrategy(LogNoReciprocityMetaRewardStrategy.class)
                        .setGenerationTime(5)
                        .setLogger(d)
                        .setStartSeed(1005)
                        .setCList(Arrays.asList(2))
                        .setLDList(Arrays.asList(1.0))
                        .setBList(Arrays.asList(1.0))
                        .setRList(Arrays.asList(9.0))
                        .setDynnet(false)
                        .setAnydata("i" + i)
                        .setN(i)
                        .setEvolution(EvolutionToriumi.class)
                        .setHeader(Arrays.asList("B", "LC","LD", "Bhead", "LDhead", "Bmid", "LDmid", "Btail", "LDtail", "all", "active", "mutual", "oneside","maxscore","minscore","soceraverage"))
                        .setAddtion((p, gen) -> {
                            //if(gen<100)np.printAgentForCSV(p.agents,gen);
                            double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
                            return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getRewardness(),p.getAgentList().get(p.getAgentList().size()/2).getPosting(), p.getAgentList().get(p.getAgentList().size()/2).getRewardness(),p.getAgentList().get(p.getAgentList().size()-1).getPosting(), p.getAgentList().get(p.getAgentList().size()-1).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
                        })
                        .setTrialTime(1);    //試行回数
                GameExecutor.ExecParam ep = epb.build();
                GameExecutor.start(ep);


            }


//        }
//
//        {
//
//            ResultLogger d=a.subDir("completegraph-20");
//            Network ins = new CompleteGraph(20);
//            GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//            epb.setNetworkInfo(ins)
//                    .setStrategy(ReciprocityStrategy.class)
//                    .setGenerationTime(10000)
//                    .setLogger(d)
//                    .setStartSeed(1005)
//                    .setCList(Arrays.asList(2))
//                    .setLDList(Arrays.asList(1.0))
//                    .setRList(Arrays.asList(9.0))
//                    .setEvolution(EvolutionToriumi.class)
//                    .setHeader(Arrays.asList("B", "LC", "LD", "B1", "LC1", "LD1", "all", "active", "mutual", "oneside"))
//                    .setAddtion((p, gen) -> {
//                        //if(gen<100)np.printAgentForCSV(p.agents,gen);
//                        double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
//                        return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getVengefulness(), p.getAgentList().get(0).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
//                    })
//                    .setTrialTime(100);    //試行回数
//            GameExecutor.ExecParam ep = epb.build();
//            GameExecutor.start(ep);
//        }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//
//		{
//
//			ResultLogger d=a.subDir("completegraph-20-log-changeR");
//			Network ins = new CompleteGraph(20);
//			GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(NoReciprocityMetaRewardStrategy.class)
//					.setGenerationTime(1000)
//					.setLogger(d)
//					.setStartSeed(1005)
//					.setCList(Arrays.asList(2))
//					.setLDList(Arrays.asList(1.0))
//					.setRList(Arrays.asList(2.0,2.1,2.2,2.3,2.4,2.5,2.6,2.7,2.7,2.8,2.9,3.0))
//					.setEvolution(EvolutionToriumi.class)
//					.setHeader(Arrays.asList("B", "LC", "LD", "B1", "LC1", "LD1", "all", "active", "mutual", "oneside"))
//					.setAddtion((p, gen) -> {
//						//if(gen<100)np.printAgentForCSV(p.agents,gen);
//						double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
//						return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getVengefulness(), p.getAgentList().get(0).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
//					})
//					.setTrialTime(100);    //試行回数
//			GameExecutor.ExecParam ep = epb.build();
//			GameExecutor.start(ep);
//		}

		
//		{
//
//			ResultLogger d=a.subDir("completegraph-20-log");
//			Network ins = new CompleteGraph(20);
//			GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(LogReciprocityStrategy.class)
//					.setGenerationTime(1000)
////					.setS0Stream(Arrays.asList(1.0))
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
//					.setTrialTime(100);    //試行回数
//			GameExecutor.ExecParam ep = epb.build();
//			GameExecutor.start(ep);
//		}
//		
//		{
//
//			ResultLogger d=a.subDir("completegraph-20-log");
//			Network ins = new CompleteGraph(20);
//			GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(LogReciprocityStrategy.class)
//					.setGenerationTime(1000)
//					.setS0Stream(Arrays.asList(1.0))
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
//					.setTrialTime(100);    //試行回数
//			GameExecutor.ExecParam ep = epb.build();
//			GameExecutor.start(ep);
//		}
//
//		{
//
//			ResultLogger d=a.subDir("completegraph-20-log");
//			Network ins = new CompleteGraph(20);
//			GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(LogReciprocityStrategy.class)
//					.setGenerationTime(1000)
//					.setLogger(d)
//					.setS0Stream(Arrays.asList(1.0))
//					.setStartSeed(1005)
//					.setCList(Arrays.asList(2))
//					.setLDList(Arrays.asList(0.1))
//					.setRList(Arrays.asList(9.0))
//					.setEvolution(EvolutionToriumi.class)
//					.setHeader(Arrays.asList("B", "LC", "LD", "B1", "LC1", "LD1", "all", "active", "mutual", "oneside"))
//					.setAddtion((p, gen) -> {
//						//if(gen<100)np.printAgentForCSV(p.agents,gen);
//						double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
//						return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getVengefulness(), p.getAgentList().get(0).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
//					})
//					.setTrialTime(100);    //試行回数
//			GameExecutor.ExecParam ep = epb.build();
//			GameExecutor.start(ep);
//		}
//		{
//
//			ResultLogger d=a.subDir("completegraph-20-log");
//			Network ins = new CompleteGraph(20);
//			GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(LogReciprocityStrategy.class)
//					.setGenerationTime(1000)
//					.setLogger(d)
//					
//					.setStartSeed(1005)
//					.setCList(Arrays.asList(2))
//					.setLDList(Arrays.asList(0.1))
//					.setRList(Arrays.asList(9.0))
//					.setEvolution(EvolutionToriumi.class)
//					.setHeader(Arrays.asList("B", "LC", "LD", "B1", "LC1", "LD1", "all", "active", "mutual", "oneside"))
//					.setAddtion((p, gen) -> {
//						//if(gen<100)np.printAgentForCSV(p.agents,gen);
//						double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
//						return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getVengefulness(), p.getAgentList().get(0).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
//					})
//					.setTrialTime(100);    //試行回数
//			GameExecutor.ExecParam ep = epb.build();
//			GameExecutor.start(ep);
//		}

//		{
//			ResultLogger d=a.subDir("Comp20game-networks-log");
//
//
//			NetworkInfo ins= NetworkInfoFactory.initComplete(20);
//			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//			.setStrategy(LogNoReciprocityMetaRewardStrategy.class)
//			.setGenerationTime(10000)
//			.setLogger(d)
//			.setStartSeed(1005)
//			.setCList(Arrays.asList(2))
//			.setLDList(Arrays.asList(1.0))
//			.setRList(Arrays.asList(9.0))
//					.setEvolution(EvolutionToriumi.class)
//                   // .setS0Stream(Arrays.asList(1.0))
//			.setHeader(Arrays.asList("B","LC","LD","B1","LC1","LD1","all","active","mutual","oneside"))
//			.setAddtion((p,gen)->{
//				double onlynoRecip=p.getSumofFriendLinkNum()-p.getSumofRecipFriendLinkNum();
//				return  Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness(),(double)ins.getPathCount()*2,  p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(),onlynoRecip);
//				})
//			.setTrialTime(10);
//			GameExecutor.ExecParam ep= epb.build();
//			GameExecutor.start(ep);
//
//		}

/*
		{
			ResultLogger d=a.subDir("forCyto");
			NetworkPrinter np= new NetworkPrinter();
			np.init(d.getWriter("comp20.csv"));

			NetworkInfo ins= NetworkInfoFactory.initComplete(20);
			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
			epb.setNetworkInfo(ins)
			.setStrategy(ReciprocityStrategy.class)
			.setGenerationTime(1000)
			.setLogger(d)
			.setStartSeed(1005)
			.setCList(Arrays.asList(2))
			.setLDList(Arrays.asList(1.0))
			.setRList(Arrays.asList(9.0))
			.setHeader(Arrays.asList("B","LC","LD","B1","LC1","LD1","all","active","mutual","oneside"))
			.setAddtion((p,gen)->{
				if(gen<1000)np.printAgentForCSV(p.agents,gen);
				double onlynoRecip=p.getSumofFriendLinkNum()-p.getSumofRecipFriendLinkNum();
				return  Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness(),(double)ins.getPathCount()*2,  p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(),onlynoRecip);
				})
			.setTrialTime(1);
			GameExecutor.ExecParam ep= epb.build();
			GameExecutor.start(ep);
			np.printNetworkForCSV(ins);
			np.close();
		}
*/




	}
}

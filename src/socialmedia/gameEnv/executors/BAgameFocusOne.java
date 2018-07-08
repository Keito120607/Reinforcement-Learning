package socialmedia.gameEnv.executors;

import network.Network;
import network.NetworkInfo;
import network.NetworkInfoFactory;
import network.BAmodel;
import network.NetworkPrinter;
import org.example.Example03;
import socialmedia.evolutions.EvolutionToriumi;

import socialmedia.game.GameExecutor;
import socialmedia.game.GameExecutor.ExecParamBuilder;
import socialmedia.gameStrategy.*;
import utility.MyRandom;
import utility.ResultLogger;

import java.io.IOException;
import java.util.Arrays;


public class BAgameFocusOne {



	public void start(){


/*
*  title = "BA",
*         "log" or "linear"
*         Number of agents,
*         m,
*         mo,
*         generation,
*         trial
*
* */
        String title = "(BA,"+
                "log,"+
                "1000,"+
                "m,"+
                "m0,"+
                "5000,"+
                "100)";

        System.out.println(title);

        ResultLogger a=new ResultLogger(title);


/*
		{
			NetworkInfo ins=NetworkInfoFactory.initWS(1000, 20, 0.01, new MyRandom(999));
			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
			epb.setNetworkInfo(ins)
			.setStrategy(ReciprocityNotExistsStrategy.class)
			.setGenerationTime(2000)
			.setLogger(b)
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

*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//        {
//
//            ResultLogger d=a.subDir("ba-1000-10-10-toriumievo-log");
//            Network ins = new BAmodel(1000, 10, 10);
//            GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//            epb.setNetworkInfo(ins)
//                    .setStrategy(LogNoReciprocityStrategy.class)
//                    .setGenerationTime(1000)
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
//                    .setTrialTime(1);    //試行回数
//            GameExecutor.ExecParam ep = epb.build();
//            GameExecutor.start(ep);
//        }

//        ResultLogger d = a.subDir("ba-" + i + "-" + m + "-" + m0 + "-toriumievo-");
        ResultLogger d = a.subDir("ba-i-m-m0-toriumievo-");
        {
//            for(int i=1000;i<=10000;i+=1000)
            int i = 1000;
            {
                for (int m = 10; m <= 100; m += 10) {
                    int m0 = m;
//                int m = 30;
//                int m0= 30;{



                    Network ins = new BAmodel(i, m, m0);
                    GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
                    epb.setNetworkInfo(ins)
                            .setStrategy(LogNoReciprocityMetaRewardStrategy.class)
                            .setGenerationTime(10)
                            .setLogger(d)
                            .setStartSeed(1005)
                            .setCList(Arrays.asList(2))
                            .setLDList(Arrays.asList(1.0))
                            .setRList(Arrays.asList(9.0))
                            .setBList(Arrays.asList(1.0))
                            .setDynnet(false)
                            .setAnydata("m"+m+"m0"+m0)
                            .setEvolution(EvolutionToriumi.class)
                            .setHeader(Arrays.asList("B", "LC", "LD", "Bhead[0]", "LDhead[0]", "Bmid["+i/2.0+"]", "LDmid["+i/2.0+"]", "Btail["+(i-1)+"]", "LDtail["+(i-1)+"]", "all", "active", "mutual", "oneside","max","min","average"))
                            .setAddtion((p, gen) -> {
                                //if(gen<100)np.printAgentForCSV(p.agents,gen);
                                double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
                                return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getRewardness(), p.getAgentList().get(p.getAgentList().size() / 2).getPosting(), p.getAgentList().get(p.getAgentList().size() / 2).getRewardness(), p.getAgentList().get(p.getAgentList().size() - 1).getPosting(), p.getAgentList().get(p.getAgentList().size() - 1).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
                            })
                            .setTrialTime(5);    //試行回数
                    GameExecutor.ExecParam ep = epb.build();
                    GameExecutor.start(ep);

                    try { Example03.slack_notification("("+m+","+m0+")"+title);
                    } catch (IOException e) { }
                }
            }
        }
        try { Example03.slack_notification(title);
        } catch (IOException e) { }



//        {
//
//            ResultLogger d=a.subDir("ba-1000-10-10-toriumievo-log");
//            Network ins = new BAmodel(1000, 10, 10);
//            GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//            epb.setNetworkInfo(ins)
//                    .setStrategy(LogNoReciprocitySNSnormStrategy.class)
//                    .setGenerationTime(1000)
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
//                    .setTrialTime(1);    //試行回数
//            GameExecutor.ExecParam ep = epb.build();
//            GameExecutor.start(ep);
//        }

//        {
//
//            ResultLogger d=a.subDir("ba-1000-10-10-toriumievo-log");
//            Network ins = new BAmodel(1000, 10, 10);
//            GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//            epb.setNetworkInfo(ins)
//                    .setStrategy(LogReciprocityStrategy.class)
//                    .setGenerationTime(1000)
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
//                    .setTrialTime(1);    //試行回数
//            GameExecutor.ExecParam ep = epb.build();
//            GameExecutor.start(ep);
//        }

        //////////////////////////////////////        //////////////////////////////////////        //////////////////////////////////////        //////////////////////////////////////        //////////////////////////////////////        //////////////////////////////////////        //////////////////////////////////////        //////////////////////////////////////        //////////////////////////////////////


//        {
//
//            ResultLogger d=a.subDir("ba-1000-10-10-toriumievo");
//            Network ins = new BAmodel(1000, 10, 10);
//            GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//            epb.setNetworkInfo(ins)
//                    .setStrategy(ReciprocityNotExistsStrategy.class)
//                    .setGenerationTime(1000)
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
//                    .setTrialTime(1);    //試行回数
//            GameExecutor.ExecParam ep = epb.build();
//            GameExecutor.start(ep);
//        }
//
//        {
//            for (int i = 1000; i <= 10000; i += 1000) {
//                ResultLogger d = a.subDir("ba-" + i + "-" + i / 100 + "-" + i / 100 + "toriumievo");
//
//
//                Network ins = new BAmodel(i, i / 100, i / 100);
//                GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//                epb.setNetworkInfo(ins)
//                        .setStrategy(NoReciprocityMetaRewardStrategy.class)
//                        .setGenerationTime(3000)
//                        .setLogger(d)
//                        .setStartSeed(1005)
//                        .setCList(Arrays.asList(2))
//                        .setLDList(Arrays.asList(1.0))
//                        .setRList(Arrays.asList(9.0))
//                        .setBList(Arrays.asList(1.0))
//                        .setEvolution(EvolutionToriumi.class)
//                        .setHeader(Arrays.asList("B", "LC", "LD", "B1", "LC1", "LD1", "all", "active", "mutual", "oneside"))
//                        .setAddtion((p, gen) -> {
//                            //if(gen<100)np.printAgentForCSV(p.agents,gen);
//                            double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
//                            return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getVengefulness(), p.getAgentList().get(0).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
//                        })
//                        .setTrialTime(1);    //試行回数
//                GameExecutor.ExecParam ep = epb.build();
//                GameExecutor.start(ep);
//            }
//        }
//
//        {
//
//            ResultLogger d=a.subDir("ba-1000-10-10-toriumievo");
//            Network ins = new BAmodel(1000, 10, 10);
//            GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//            epb.setNetworkInfo(ins)
//                    .setStrategy(NoReciprocitySNSnormStrategy.class)
//                    .setGenerationTime(1000)
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
//                    .setTrialTime(1);    //試行回数
//            GameExecutor.ExecParam ep = epb.build();
//            GameExecutor.start(ep);
//        }
//
//        {
//
//            ResultLogger d=a.subDir("ba-1000-10-10-toriumievo");
//            Network ins = new BAmodel(1000, 10, 10);
//            GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//            epb.setNetworkInfo(ins)
//                    .setStrategy(ReciprocityStrategy.class)
//                    .setGenerationTime(1000)
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
//                    .setTrialTime(1);    //試行回数
//            GameExecutor.ExecParam ep = epb.build();
//            GameExecutor.start(ep);
//        }


        //////////////////////////////////////        //////////////////////////////////////        //////////////////////////////////////        //////////////////////////////////////        //////////////////////////////////////


//		{
//			ResultLogger d=a.subDir("ba-1000-10-10-friendnum-toriumievo");
//
//
//			NetworkInfo ins=NetworkInfoFactory.initBA(1000, 10, 10, new MyRandom(999));
//			NetworkPrinter.printNetworkForCyto2(ins,d.getWriter("ba-1000-10-10-999.tsv"));
//			ExecParamBuilder epb=new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//			.setStrategy(NoReciprocityMetaRewardStrategy.class)		//BAモデル報酬ゲーム
//			.setGenerationTime(10000)
//			.setLogger(d)
//			.setStartSeed(1005)
//			.setCList(Arrays.asList(2))
//			.setLDList(Arrays.asList(1.0))
//			.setRList(Arrays.asList(9.0))
//					.setEvolution(EvolutionToriumi.class)
//			.setHeader(Arrays.asList("B","LC","LD","B1","LC1","LD1","all","active","mutual","oneside"))
//			.setAddtion((p,gen)->{
//				//if(gen<100)np.printAgentForCSV(p.agents,gen);
//				double onlynoRecip=p.getSumofFriendLinkNum()-p.getSumofRecipFriendLinkNum();
//				return  Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness(),(double)ins.getPathCount()*2,  p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(),onlynoRecip);
//				})
//			.setTrialTime(100);	//試行回数
//			GameExecutor.ExecParam ep= epb.build();
//			GameExecutor.start(ep);
//
//		}

//		{
//			ResultLogger d=a.subDir("ba-1000-10-10-friendnum-logtoriumievo");
//
//
//			NetworkInfo ins=NetworkInfoFactory.initBA(1000, 10, 10, new MyRandom(999));
//			ExecParamBuilder epb=new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(NoReciprocityMetaRewardStrategy.class)			//BAモデルメタ報酬ゲーム
//					.setGenerationTime(10000)
//					.setLogger(d)
//					.setStartSeed(1005)
//					.setCList(Arrays.asList(2))
//					.setLDList(Arrays.asList(1.0))
//					.setRList(Arrays.asList(9.0))
//					.setEvolution(LogEvolutionToriumi.class)
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

//		{
//			ResultLogger d=a.subDir("ba-1000-10-10-friendnum");
//
//
//			NetworkInfo ins=NetworkInfoFactory.initBA(1000, 10, 10, new MyRandom(999));
//			ExecParamBuilder epb=new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(ReciprocityStrategy.class)			//BAモデル互恵報酬ゲーム
//					.setGenerationTime(10000)
//					.setLogger(d)
//					.setStartSeed(1005)
//					.setCList(Arrays.asList(2))
//					.setLDList(Arrays.asList(1.0))
//					.setRList(Arrays.asList(9.0))
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
//		{
//			ResultLogger d=a.subDir("ba-1000-10-10-friendnum");
//
//
//			NetworkInfo ins=NetworkInfoFactory.initBA(1000, 10, 10, new MyRandom(999));
//			ExecParamBuilder epb=new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(ReciprocityMetaRewardStrategy.class)			//BAモデル互恵メタ報酬ゲーム
//					.setGenerationTime(10000)
//					.setLogger(d)
//					.setStartSeed(1005)
//					.setCList(Arrays.asList(2))
//					.setLDList(Arrays.asList(1.0))
//					.setRList(Arrays.asList(9.0))
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

package socialmedia.gameEnv.executors;

import java.util.Arrays;

import network.Network;
import network.NetworkInfo;
import network.NetworkInfoFactory;
import network.SNAP;
import socialmedia.evolutions.EvolutionToriumi;
import socialmedia.game.GameExecutor;
import socialmedia.game.GameExecutor.ExecParamBuilder;
import socialmedia.gameStrategy.*;

import utility.ResultLogger;


public class GameInSNAP {



	public void start(){

		ResultLogger a=new ResultLogger("SNAPgame-networks-LogSNS崩壊調査");


//		{
//			ResultLogger d=a;
//
//            Network ins = new SNAP("statics/facebook_combined.txt");
//
//			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(ReciprocityNotExistsStrategy.class)
//					.setGenerationTime(10000)
//					.setLogger(d)
//					.setStartSeed(1005)
//					.setCList(Arrays.asList(2))
//					.setLDList(Arrays.asList(1.0))
//					.setRList(Arrays.asList(9.0))
//                    .setEvolution(EvolutionToriumi.class)
//					.setHeader(Arrays.asList("B","LC","LD","B1","LC1","LD1","all","active","mutual","oneside"))
//					.setAddtion((p,gen)->{
//						double onlynoRecip=p.getSumofFriendLinkNum()-p.getSumofRecipFriendLinkNum();
//						return  Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness(),(double)ins.getPathCount()*2,  p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(),onlynoRecip);
//					})
//					.setTrialTime(100);
//			GameExecutor.ExecParam ep= epb.build();
//			GameExecutor.start(ep);
//
//		}
//        {
//            ResultLogger d=a;
//
//            Network ins = new SNAP("statics/facebook_combined.txt");
//
//            GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//            epb.setNetworkInfo(ins)
//                    .setStrategy(NoReciprocityMetaRewardStrategy.class)
//                    .setGenerationTime(10000)
//                    .setLogger(d)
//                    .setStartSeed(1005)
//                    .setCList(Arrays.asList(2))
//                    .setLDList(Arrays.asList(1.0))
//                    .setRList(Arrays.asList(9.0))
//                    .setEvolution(EvolutionToriumi.class)
//                    .setHeader(Arrays.asList("B","LC","LD","B1","LC1","LD1","all","active","mutual","oneside"))
//                    .setAddtion((p,gen)->{
//                        double onlynoRecip=p.getSumofFriendLinkNum()-p.getSumofRecipFriendLinkNum();
//                        return  Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness(),(double)ins.getPathCount()*2,  p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(),onlynoRecip);
//                    })
//                    .setTrialTime(100);
//            GameExecutor.ExecParam ep= epb.build();
//            GameExecutor.start(ep);
//
//        }
//        {
//            ResultLogger d=a;
//
//            Network ins = new SNAP("statics/facebook_combined.txt");
//
//            GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//            epb.setNetworkInfo(ins)
//                    .setStrategy(NoReciprocitySNSnormStrategy.class)
//                    .setGenerationTime(10000)
//                    .setLogger(d)
//                    .setStartSeed(1005)
//                    .setCList(Arrays.asList(2))
//                    .setLDList(Arrays.asList(1.0))
//                    .setRList(Arrays.asList(9.0))
//                    .setEvolution(EvolutionToriumi.class)
//                    .setHeader(Arrays.asList("B","LC","LD","B1","LC1","LD1","all","active","mutual","oneside"))
//                    .setAddtion((p,gen)->{
//                        double onlynoRecip=p.getSumofFriendLinkNum()-p.getSumofRecipFriendLinkNum();
//                        return  Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness(),(double)ins.getPathCount()*2,  p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(),onlynoRecip);
//                    })
//                    .setTrialTime(100);
//            GameExecutor.ExecParam ep= epb.build();
//            GameExecutor.start(ep);
//
//        }
//        {
//            ResultLogger d=a;
//
//            Network ins = new SNAP("statics/facebook_combined.txt");
//
//            GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//            epb.setNetworkInfo(ins)
//                    .setStrategy(ReciprocityStrategy.class)
//                    .setGenerationTime(10000)
//                    .setLogger(d)
//                    .setStartSeed(1005)
//                    .setCList(Arrays.asList(2))
//                    .setLDList(Arrays.asList(1.0))
//                    .setRList(Arrays.asList(9.0))
//                    .setEvolution(EvolutionToriumi.class)
//                    .setHeader(Arrays.asList("B","LC","LD","B1","LC1","LD1","all","active","mutual","oneside"))
//                    .setAddtion((p,gen)->{
//                        double onlynoRecip=p.getSumofFriendLinkNum()-p.getSumofRecipFriendLinkNum();
//                        return  Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness(),(double)ins.getPathCount()*2,  p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(),onlynoRecip);
//                    })
//                    .setTrialTime(100);
//            GameExecutor.ExecParam ep= epb.build();
//            GameExecutor.start(ep);
//
//        }
//        {
//            ResultLogger d=a;
//
//            Network ins = new SNAP("statics/facebook_combined.txt");
//
//            GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//            epb.setNetworkInfo(ins)
//                    .setStrategy(LogNoReciprocitySNSnormStrategy.class)
//                    .setGenerationTime(10000)
//                    .setLogger(d)
//                    .setStartSeed(1005)
//                    .setCList(Arrays.asList(2))
//                    .setLDList(Arrays.asList(1.0))
//                    .setRList(Arrays.asList(9.0))
//                    .setEvolution(EvolutionToriumi.class)
//                    .setHeader(Arrays.asList("B","LC","LD","B1","LC1","LD1","all","active","mutual","oneside"))
//                    .setAddtion((p,gen)->{
//                        double onlynoRecip=p.getSumofFriendLinkNum()-p.getSumofRecipFriendLinkNum();
//                        return  Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness(),(double)ins.getPathCount()*2,  p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(),onlynoRecip);
//                    })
//                    .setTrialTime(100);
//            GameExecutor.ExecParam ep= epb.build();
//            GameExecutor.start(ep);
//
//        }
        {
            ResultLogger d=a;

            Network ins = new SNAP("statics/facebook_combined.txt");

            GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
            epb.setNetworkInfo(ins)
                    .setStrategy(LogNoReciprocityMetaRewardStrategy.class)
                    .setGenerationTime(10000)
                    .setLogger(d)
                    .setStartSeed(1005)
                    .setCList(Arrays.asList(2))
                    .setLDList(Arrays.asList(1.0))
                    .setBList(Arrays.asList(0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9,1.0))
                    .setRList(Arrays.asList(9.0))
                    .setEvolution(EvolutionToriumi.class)
                    .setHeader(Arrays.asList("B","LC","LD","B1","LC1","LD1","all","active","mutual","oneside","maxscore","minscore","scoreave"))
                    .setAddtion((p,gen)->{
                        double onlynoRecip=p.getSumofFriendLinkNum()-p.getSumofRecipFriendLinkNum();
                        return  Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness(),(double)ins.getPathCount()*2,  p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(),onlynoRecip);
                    })
                    .setTrialTime(100);
            GameExecutor.ExecParam ep= epb.build();
            GameExecutor.start(ep);

        }
//        {
//            ResultLogger d=a;
//
//            Network ins = new SNAP("statics/facebook_combined.txt");
//
//            GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//            epb.setNetworkInfo(ins)
//                    .setStrategy(LogNoReciprocitySNSnormStrategy.class)
//                    .setGenerationTime(10000)
//                    .setLogger(d)
//                    .setStartSeed(1005)
//                    .setCList(Arrays.asList(2))
//                    .setLDList(Arrays.asList(1.0))
//                    .setRList(Arrays.asList(9.0))
//                    .setEvolution(EvolutionToriumi.class)
//                    .setHeader(Arrays.asList("B","LC","LD","B1","LC1","LD1","all","active","mutual","oneside"))
//                    .setAddtion((p,gen)->{
//                        double onlynoRecip=p.getSumofFriendLinkNum()-p.getSumofRecipFriendLinkNum();
//                        return  Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness(),(double)ins.getPathCount()*2,  p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(),onlynoRecip);
//                    })
//                    .setTrialTime(100);
//            GameExecutor.ExecParam ep= epb.build();
//            GameExecutor.start(ep);
//
//        }
//        {
//            ResultLogger d=a;
//
//            Network ins = new SNAP("statics/facebook_combined.txt");
//
//            GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//            epb.setNetworkInfo(ins)
//                    .setStrategy(LogReciprocityStrategy.class)
//                    .setGenerationTime(10000)
//                    .setLogger(d)
//                    .setStartSeed(1005)
//                    .setCList(Arrays.asList(2))
//                    .setLDList(Arrays.asList(1.0))
//                    .setRList(Arrays.asList(9.0))
//                    .setEvolution(EvolutionToriumi.class)
//                    .setHeader(Arrays.asList("B","LC","LD","B1","LC1","LD1","all","active","mutual","oneside"))
//                    .setAddtion((p,gen)->{
//                        double onlynoRecip=p.getSumofFriendLinkNum()-p.getSumofRecipFriendLinkNum();
//                        return  Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness(),(double)ins.getPathCount()*2,  p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(),onlynoRecip);
//                    })
//                    .setTrialTime(100);
//            GameExecutor.ExecParam ep= epb.build();
//            GameExecutor.start(ep);
//
//        }

//		{
//			ResultLogger d=a;
//
//
//			NetworkInfo ins= NetworkInfoFactory.importFile( "statics/facebook_combined.txt");
//			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(NoReciprocityMetaRewardStrategy.class)
//					.setGenerationTime(10000)
//					.setLogger(d)
//					.setStartSeed(1005)
//					.setCList(Arrays.asList(2))
//					.setLDList(Arrays.asList(1.0))
//					.setRList(Arrays.asList(9.0))
//					.setHeader(Arrays.asList("B","LC","LD","B1","LC1","LD1","all","active","mutual","oneside"))
//					.setAddtion((p,gen)->{
//						double onlynoRecip=p.getSumofFriendLinkNum()-p.getSumofRecipFriendLinkNum();
//						return  Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness(),(double)ins.getPathCount()*2,  p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(),onlynoRecip);
//					})
//					.setTrialTime(10);
//			GameExecutor.ExecParam ep= epb.build();
//			GameExecutor.start(ep);
//
//		}
/*

		{
			ResultLogger d=a.subDir("forCytoNolimit");
			NetworkPrinter np= new NetworkPrinter();
			np.init(d.getWriter("snap.csv"));

			NetworkInfo ins= NetworkInfoFactory.importFile("statics/facebook_combined.txt");
			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
			epb.setNetworkInfo(ins)
			.setStrategy(ReciprocityStrategy.class)
			.setGenerationTime(150)
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

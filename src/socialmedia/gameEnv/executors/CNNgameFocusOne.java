package socialmedia.gameEnv.executors;

import java.lang.reflect.Array;
import java.util.Arrays;

import network.*;
import socialmedia.evolutions.EvolutionToriumi;

import socialmedia.game.GameExecutor;
import socialmedia.game.GameExecutor.ExecParamBuilder;
import socialmedia.gameStrategy.*;
//import sun.rmi.runtime.Log;
import utility.MyRandom;
import utility.ResultLogger;


public class CNNgameFocusOne {



	public void start(){

		ResultLogger a=new ResultLogger("CNNgameU-0.9-1trial");


//		{

		int i = 1000;
		double u = 0.9;
		{

			ResultLogger d=a.subDir("cnn-1000-0.9");
			Network ins = new CNNmodel(i, u);
			GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
			epb.setNetworkInfo(ins)
					.setStrategy(NoReciprocityMetaRewardStrategy.class)
					.setGenerationTime(10000)
					.setLogger(d)
					.setStartSeed(1005)
					.setBList(Arrays.asList(1.0))
					.setCList(Arrays.asList(2))
					.setLDList(Arrays.asList(1.0))
					.setDynnet(true)
					.setRList(Arrays.asList(9.0))
					.setAnydata(i+"-"+u)
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


//
//			ResultLogger d=a.subDir("cnn-1000-0.9-log");
//			Network ins = new CNNmodel(1000, 0.9);
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
//			ResultLogger d=a.subDir("cnn-1000-0.9-log");
//			Network ins = new CNNmodel(1000, 0.9);
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
//			ResultLogger d=a.subDir("cnn-1000-0.9-log");
//			Network ins = new CNNmodel(1000, 0.9);
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
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//		{
//
//			ResultLogger d=a.subDir("cnn-1000-0.9");
//			Network ins = new CNNmodel(1000, 0.9);
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
//		{
//
//			ResultLogger d=a.subDir("cnn-1000-0.9");
//			Network ins = new CNNmodel(1000, 0.9);
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
//			ResultLogger d=a.subDir("cnn-1000-0.9");
//			Network ins = new CNNmodel(1000, 0.9);
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
//			ResultLogger d=a.subDir("cnn-1000-0.9");
//			Network ins = new CNNmodel(1000, 0.9);
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



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





//for(double i=0.491;i<=0.500;i=i+0.001) {
//	{
//		ResultLogger d = a.subDir("cnn-1000-"+i);
//		//NetworkPrinter np= new NetworkPrinter();
//		//np.init(d.getWriter("ws-1000-20-0.01.csv"));
//
//		NetworkInfo ins = NetworkInfoFactory.initCNN(i, 1000, new MyRandom(9991));
//		NetworkPrinter.printNetworkForCyto2(ins, d.getWriter("cnn"+i+".tsv"));
//
//		GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//		epb.setNetworkInfo(ins)
//				.setStrategy(NoReciprocitySNSnormStrategy.class)
//				.setGenerationTime(5000)
//				.setLogger(d)
//				.setStartSeed(1005)
//				.setCList(Arrays.asList(2))
//				.setLDList(Arrays.asList(1.0))
//				.setRList(Arrays.asList(9.0))
//				.setHeader(Arrays.asList("B", "LC", "LD", "B1", "LC1", "LD1", "all", "active", "mutual", "oneside"))
//				.setAddtion((p, gen) -> {
//					//if(gen<100)np.printAgentForCSV(p.agents,gen);
//					double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
//					return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getVengefulness(), p.getAgentList().get(0).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
//				})
//				.setTrialTime(50);
//		GameExecutor.ExecParam ep = epb.build();
//		GameExecutor.start(ep);
//
//		//np.printNetworkForCSV(ins);
//		//np.close();
//
//	}
//}
//}
//		{
//			ResultLogger d=a.subDir("cnn-1000-0.5");
//			//NetworkPrinter np= new NetworkPrinter();
//			//np.init(d.getWriter("ws-1000-20-0.01.csv"));
//
//			for(int i=0;i<50;i++) {
//				ResultLogger s = d.subDir("seed=" + (999 + 130 * i));
//				NetworkInfo ins = NetworkInfoFactory.initCNN(0.5, 1000, new MyRandom(999 + 130 * i));
//				NetworkPrinter.printNetworkForCyto2(ins, s.getWriter("cnn0499-" + (999 + 13 * i) + ".tsv"));
//
//				GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//				epb.setNetworkInfo(ins)
//						.setStrategy(NoReciprocitySNSnormStrategy.class)
//						.setGenerationTime(5000)
//						.setLogger(s)
//						.setStartSeed(1005)
//						.setCList(Arrays.asList(2))
//						.setLDList(Arrays.asList(1.0))
//						.setRList(Arrays.asList(9.0))
//						.setEvolution(LogEvolutionToriumi.class)
//						.setHeader(Arrays.asList("B", "LC", "LD", "B1", "LC1", "LD1", "all", "active", "mutual", "oneside"))
//						.setAddtion((p, gen) -> {
//							//if(gen<100)np.printAgentForCSV(p.agents,gen);
//							double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
//							return Arrays.asList(p.getAgentList().get(0).getPosting(), p.getAgentList().get(0).getVengefulness(), p.getAgentList().get(0).getRewardness(), (double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(), onlynoRecip);
//						})
//						.setTrialTime(100);
//				GameExecutor.ExecParam ep = epb.build();
//				GameExecutor.start(ep);
//
//				//np.printNetworkForCSV(ins);
//				//np.close();
//			}
//		}
//		{
//			ResultLogger d=a.subDir("cnn-1000-0.9");
//			//NetworkPrinter np= new NetworkPrinter();
//			//np.init(d.getWriter("ws-1000-20-0.01.csv"));
//
//			NetworkInfo ins=NetworkInfoFactory.initCNN(0.9, 1000, new MyRandom(999));
//			NetworkPrinter.printNetworkForCyto2(ins,d.getWriter("cnn09.tsv"));
//
//			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(NoReciprocitySNSnormStrategy.class)
//					.setGenerationTime(5000)
//					.setLogger(d)
//					.setStartSeed(1005)
//					.setCList(Arrays.asList(2))
//					.setLDList(Arrays.asList(1.0))
//					.setRList(Arrays.asList(9.0))
//                    .setEvolution(EvolutionToriumi.class)
//					.setHeader(Arrays.asList("B","LC","LD","B1","LC1","LD1","all","active","mutual","oneside"))
//					.setAddtion((p,gen)->{
//						//if(gen<100)np.printAgentForCSV(p.agents,gen);
//						double onlynoRecip=p.getSumofFriendLinkNum()-p.getSumofRecipFriendLinkNum();
//						return  Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness(),(double)ins.getPathCount()*2,  p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(),onlynoRecip);
//					})
//					.setTrialTime(50);
//			GameExecutor.ExecParam ep= epb.build();
//			GameExecutor.start(ep);
//
//			//np.printNetworkForCSV(ins);
//			//np.close();
//
//		}
//
//		{
//			ResultLogger d=a.subDir("cnn-1000-0.5-log");
//			//NetworkPrinter np= new NetworkPrinter();
//			//np.init(d.getWriter("ws-1000-20-0.01.csv"));
//
//			NetworkInfo ins=NetworkInfoFactory.initCNN(0.9, 1000, new MyRandom(999));
//			NetworkPrinter.printNetworkForCyto2(ins,d.getWriter("cnn09.tsv"));
//
//			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(NoReciprocitySNSnormStrategy.class)
//					.setGenerationTime(5000)
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
//					.setTrialTime(50);
//			GameExecutor.ExecParam ep= epb.build();
//			GameExecutor.start(ep);
//
//			//np.printNetworkForCSV(ins);
//			//np.close();
//
//		}
//		{
//			ResultLogger d=a.subDir("cnn-1000-0.4");
//			//NetworkPrinter np= new NetworkPrinter();
//			//np.init(d.getWriter("ws-1000-20-0.01.csv"));
//
//			NetworkInfo ins=NetworkInfoFactory.initCNN(0.4, 1000, new MyRandom(999));
//			NetworkPrinter.printNetworkForCyto2(ins,d.getWriter("cnn04.tsv"));
//
//			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(NoReciprocitySNSnormStrategy.class)
//					.setGenerationTime(5000)
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
//					.setTrialTime(50);
//			GameExecutor.ExecParam ep= epb.build();
//			GameExecutor.start(ep);
//
//			//np.printNetworkForCSV(ins);
//			//np.close();
//
//		}
//		{
//			ResultLogger d=a.subDir("cnn-1000-0.5");
//			//NetworkPrinter np= new NetworkPrinter();
//			//np.init(d.getWriter("ws-1000-20-0.01.csv"));
//
//			NetworkInfo ins=NetworkInfoFactory.initCNN(0.5, 1000, new MyRandom(999));
//			NetworkPrinter.printNetworkForCyto2(ins,d.getWriter("cnn05.tsv"));
//
//			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(NoReciprocitySNSnormStrategy.class)
//					.setGenerationTime(5000)
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
//					.setTrialTime(50);
//			GameExecutor.ExecParam ep= epb.build();
//			GameExecutor.start(ep);
//
//			//np.printNetworkForCSV(ins);
//			//np.close();
//
//		}
//		{
//			ResultLogger d=a.subDir("cnn-1000-0.6");
//			//NetworkPrinter np= new NetworkPrinter();
//			//np.init(d.getWriter("ws-1000-20-0.01.csv"));
//
//			NetworkInfo ins=NetworkInfoFactory.initCNN(0.6, 1000, new MyRandom(999));
//			NetworkPrinter.printNetworkForCyto2(ins,d.getWriter("cnn06.tsv"));
//
//			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(NoReciprocitySNSnormStrategy.class)
//					.setGenerationTime(5000)
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
//					.setTrialTime(50);
//			GameExecutor.ExecParam ep= epb.build();
//			GameExecutor.start(ep);
//
//			//np.printNetworkForCSV(ins);
//			//np.close();
//
//		}
//		{
//			ResultLogger d=a.subDir("cnn-1000-0.7");
//			//NetworkPrinter np= new NetworkPrinter();
//			//np.init(d.getWriter("ws-1000-20-0.01.csv"));
//
//			NetworkInfo ins=NetworkInfoFactory.initCNN(0.7, 1000, new MyRandom(999));
//			NetworkPrinter.printNetworkForCyto2(ins,d.getWriter("cnn07.tsv"));
//
//			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(NoReciprocitySNSnormStrategy.class)
//					.setGenerationTime(5000)
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
//					.setTrialTime(50);
//			GameExecutor.ExecParam ep= epb.build();
//			GameExecutor.start(ep);
//
//			//np.printNetworkForCSV(ins);
//			//np.close();
//
//		}
//		{
//			ResultLogger d=a.subDir("cnn-1000-0.8");
//			//NetworkPrinter np= new NetworkPrinter();
//			//np.init(d.getWriter("ws-1000-20-0.01.csv"));
//
//			NetworkInfo ins=NetworkInfoFactory.initCNN(0.8, 1000, new MyRandom(999));
//			NetworkPrinter.printNetworkForCyto2(ins,d.getWriter("cnn08.tsv"));
//
//			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(NoReciprocitySNSnormStrategy.class)
//					.setGenerationTime(5000)
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
//					.setTrialTime(50);
//			GameExecutor.ExecParam ep= epb.build();
//			GameExecutor.start(ep);
//
//			//np.printNetworkForCSV(ins);
//			//np.close();
//
//		}
//		{
//			ResultLogger d=a.subDir("cnn-1000-0.9");
//			//NetworkPrinter np= new NetworkPrinter();
//			//np.init(d.getWriter("ws-1000-20-0.01.csv"));
//
//			NetworkInfo ins=NetworkInfoFactory.initCNN(0.9, 1000, new MyRandom(999));
//			NetworkPrinter.printNetworkForCyto2(ins,d.getWriter("cnn09.tsv"));
//
//			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(NoReciprocitySNSnormStrategy.class)
//					.setGenerationTime(5000)
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
//					.setTrialTime(50);
//			GameExecutor.ExecParam ep= epb.build();
//			GameExecutor.start(ep);
//
//			//np.printNetworkForCSV(ins);
//			//np.close();
//
//		}
//
////		{
////			ResultLogger d=a.subDir("cnn-1000-0.9");
////			//NetworkPrinter np= new NetworkPrinter();
////			//np.init(d.getWriter("ws-1000-20-0.01.csv"));
////
////			NetworkInfo ins=NetworkInfoFactory.initCNN(0.9, 1000, new MyRandom(999));
////			NetworkPrinter.printNetworkForCyto2(ins,d.getWriter("cnn09.tsv"));
////
////			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
////			epb.setNetworkInfo(ins)
////					.setStrategy(NoReciprocitySNSnormStrategy.class)		//CNNグラフSNS規範ゲーム
////					.setGenerationTime(10000)
////					.setLogger(d)
////					.setStartSeed(1005)
////					.setCList(Arrays.asList(2))
////					.setLDList(Arrays.asList(1.0))
////					.setRList(Arrays.asList(9.0))
////					.setHeader(Arrays.asList("B","LC","LD","B1","LC1","LD1","all","active","mutual","oneside"))
////					.setAddtion((p,gen)->{
////						//if(gen<100)np.printAgentForCSV(p.agents,gen);
////						double onlynoRecip=p.getSumofFriendLinkNum()-p.getSumofRecipFriendLinkNum();
////						return  Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness(),(double)ins.getPathCount()*2,  p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(),onlynoRecip);
////					})
////					.setTrialTime(100);
////			GameExecutor.ExecParam ep= epb.build();
////			GameExecutor.start(ep);
////
////			//np.printNetworkForCSV(ins);
////			//np.close();
////
////		}
//
//
//
//
//
///*
//		{
//			ResultLogger d=a.subDir("cnn-1000-0.9-forCyto");
//			NetworkPrinter np= new NetworkPrinter();
//			np.init(d.getWriter("cnn-1000-0.9--S08LD01.csv"));
//
//			NetworkInfo ins=NetworkInfoFactory.initCNN(0.9, 1000, new MyRandom(999));
//			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//			.setStrategy(ReciprocityStrategy.class)
//			.setGenerationTime(105)
//			.setLogger(d)
//			.setStartSeed(1005)
//			.setCList(Arrays.asList(2))
//			.setLDList(Arrays.asList(1.0))
//			.setRList(Arrays.asList(9.0))
//			.setHeader(Arrays.asList("B","LC","LD","B1","LC1","LD1","all","active","mutual","oneside"))
//			.setAddtion((p,gen)->{
//				if(gen<100)np.printAgentForCSV(p.agents,gen);
//				double onlynoRecip=p.getSumofFriendLinkNum()-p.getSumofRecipFriendLinkNum();
//				return  Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness(),(double)ins.getPathCount()*2,  p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(),onlynoRecip);
//				})
//			.setTrialTime(1);
//			GameExecutor.ExecParam ep= epb.build();
//			GameExecutor.start(ep);
//
//			np.printNetworkForCSV(ins);
//			np.close();
//		}
//*/


	}
}

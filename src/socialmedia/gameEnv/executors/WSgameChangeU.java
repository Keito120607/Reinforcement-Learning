//package socialmedia.gameEnv.executors;
//
//import java.util.Arrays;
//
//import network.NetworkInfo;
//import network.NetworkInfoFactory;
//import network.NetworkPrinter;
//import socialmedia.game.GameExecutor;
//import socialmedia.game.GameExecutor.ExecParamBuilder;
//import socialmedia.gameStrategy.NewRecipMetaStrategy;
//import utility.MyRandom;
//import utility.ResultLogger;
//
//
//public class WSgameChangeU {
//
//
//
//	public void start(){
//
//		ResultLogger a=new ResultLogger("WSgame-networks");
//
////for(double u=0;u<=1.0;u+=0.1)
//	double u=0.05;
//		{
//			ResultLogger d=a.subDir("ws-1000-20-"+String.format("%.2f", u));
//
//
//					NetworkInfo ins=NetworkInfoFactory.initWS(1000, 20, u, new MyRandom(999));
//					//NetworkInfo ins= NetworkInfoFactory.importFile( "statics/facebook_combined.txt");
//			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//			.setStrategy(NewRecipMetaStrategy.class)		//NewRecipMetaStrategyとはなんぞや
//			.setGenerationTime(2000)
//			.setLogger(d)
//			.setStartSeed(1005)
//			.setCList(Arrays.asList(2))
//			.setLDList(Arrays.asList(1.0))
//			.setRList(Arrays.asList(9.0))
//			//.setS0Stream(Arrays.asList(1.0))
//			.setHeader(Arrays.asList("B","LC","LD","B1","LC1","LD1","all","active","mutual","oneside"))
//			.setAddtion((p,gen)->{
//				//if(gen<100)np.printAgentForCSV(p.agents,gen);
//				double onlynoRecip=p.getSumofFriendLinkNum()-p.getSumofRecipFriendLinkNum();
//				return  Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness(),(double)ins.getPathCount()*2,  p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(),onlynoRecip);
//				})
//			.setTrialTime(20);
//			GameExecutor.ExecParam ep= epb.build();
//			GameExecutor.start(ep);
//			NetworkPrinter.printNetworkForCyto2(ins, d.getWriter("wsnet"+String.format("%.2f", u)));
//		}
//
//
//
//
//
//
//	}
//}

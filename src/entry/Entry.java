package entry;


import socialmedia.gameEnv.executors.*;
//import socialmedia.gameEnv.networkCgtR.NetworkGameExp;

import java.io.IOException;




public class Entry {

//	public static void jikkenA(){
//		//(new NewMetaReciprocityRewardGameEnv()).start();
//		//(new RewardGameEnv()).start();
//		//(new SNSNoReciprocityRewardGameEnv()).start();
//		(new ReciprocityRewardGameEnvOnSLDN()).start();
//		//(new SNSNoReciprocityRewardGameEnv()).start();
//		//(new SNSReciprocityRewardGameEnv()).start();
//
//
//	}
//
//	public static void paramtest(){
////		(new GRewardGame()).start();
////		(new GMetaRewardGameEnv()).start();
////		(new GMetaPunishmentGameEnv()).start();
//		(new AxelrodMetaNormGameEnv()).start();
////		(new MetaNoReciprocityRewardGameEnv()).start();
//	}
//
////	public static void complexs() {
////		(new NetworkGameExp()).main();
////	}

	public static void main(String[] args) {

//		paramtest();

//		(new WSgameChangeU()).start();
//		(new WSgameFocusOne()).start();	
//		(new CNNgameFocusOne()).start();	
//		(new BAgameFocusOne()).start();		
//		(new GameIn20Comp()).start();       
//		(new GameInSNAP()).start();			
		(new GameInRandom()).start();		
//		(new ReciprocityEnv()).start();
//			NetworkInfo ins= NetworkInfoFactory.importFile( "statics/facebook_combined.txt");
//		ResultLogger a=new ResultLogger("SNAP-networks");
//
//		NetworkPrinter.printNetworkForCyto2(ins, a.getWriter("snap-net"));


	}

}
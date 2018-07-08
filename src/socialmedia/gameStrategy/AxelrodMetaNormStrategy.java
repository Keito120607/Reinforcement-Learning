package socialmedia.gameStrategy;

import network.Agent;
import socialmedia.game.GameParam;
import socialmedia.game.GameStrategy;
import socialmedia.gameStrategy.AxelrodMetaNormStrategy.AxelrodParam;


public  class AxelrodMetaNormStrategy extends GameStrategy<AxelrodParam> {

	public static class AxelrodParam extends GameParam{
		protected int T;	//裏切りのコスト
		protected int H;//裏切りよる被害
		protected int P;//懲罰された時の損失
		protected int E;//懲罰のコスト

		public AxelrodParam() {
			T=3;
			H=-1;
			P=-9;
			E=-2;
			fileName="MetaNormGame-"+System.currentTimeMillis();
		}
	}

	@Override
	protected void runGame(int round) {
		for (Agent turnAgent : getAgentList()) {
			//Sよりも大胆だと裏切る

			double S=param.random.nextDouble();
			if(turnAgent.getBoldness()>S){
				turnAgent.addScore(param.T);
				//裏切られた時の他の人の処理
				for (Agent otherAgent : param.getNeighbor(turnAgent)) {
					if(turnAgent==otherAgent)continue;
					//被害を受ける
					otherAgent.addScore(param.H);
					//気づいたら
					if(S>param.random.nextDouble()){
						//懲罰する気になるか
						if(otherAgent.getVengefulness()>param.random.nextDouble()){
							turnAgent.addScore(param.P);
							otherAgent.addScore(param.E);
						}else{
							//懲罰しなかったら
							for (Agent anotherAgent : param.getNeighbor(otherAgent)) {
								if(turnAgent==anotherAgent||otherAgent==anotherAgent)continue;
								if(S>param.random.nextDouble()){
									//懲罰する気になるか
									if(anotherAgent.getVengefulness()>param.random.nextDouble()){
										otherAgent.addScore(param.P);
										anotherAgent.addScore(param.E);
									}
								}
							}
						}
					}


				}

			}

		}

	}

}


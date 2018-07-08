package socialmedia.gameStrategy;

import network.Agent;
import socialmedia.game.GameStrategy;
import socialmedia.gameStrategy.AxelrodMetaNormStrategy.AxelrodParam;

public class AxelrodNormStrategy  extends GameStrategy<AxelrodParam>{
	@Override
	protected void runGame(int round) {
		for (Agent turnAgent : getAgentList()) {
			//Sよりも大胆だと裏切る
			//Sは機会ごとに設定される
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
						}
					}


				}

			}

		}

	}

}

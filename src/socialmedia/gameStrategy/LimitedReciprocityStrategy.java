package socialmedia.gameStrategy;

import network.Agent;

public class LimitedReciprocityStrategy extends ReciprocityStrategy {
	public int limitedTime=10;


	@Override
	public void initGame(RecipParam p) {
		for (Agent turnAgent : getAgentList()) {
			turnAgent.limtedRemainTime=limitedTime;
		}
	};

	@Override
	protected void runGame(int round) {
		for (Agent turnAgent : getAgentList()) {
			//Sよりも大胆だと裏切る
			double S=param.random.nextDouble();
			if(turnAgent.getBoldness()>S){
				defect(turnAgent, S);
			}else{
				cooperat(turnAgent, S);
			}
		}
	}


	public boolean  beNoticedArticle(double S,int agent) {
		if(param.getAgentList().get(agent).limtedRemainTime<1){
			return false;
		}else{
			param.getAgentList().get(agent).limtedRemainTime-=1;
			return true;
		}

	}

}

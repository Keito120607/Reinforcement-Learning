package socialmedia.gameStrategy;

import java.util.ArrayList;

import network.Agent;

public class NewRecipMetaStrategy extends ReciprocitySNSnormStrategy {

	@Override
	public void coopCommentScore(Agent turnAgent,Agent otherAgent) {
		turnAgent.addScore(param.R);
		otherAgent.addScore(param.C);
		//writeCoop(turnAgent.id, otherAgent.id);
		addDebugCommentLog(round,turnAgent.id,otherAgent.id);
	}

	@Override
	public void coopNotCommentScore(Agent turnAgent,Agent otherAgent) {
		//deleteCoop(turnAgent.id, otherAgent.id);
	}

	@Override
	public void notCoopCommentScore(Agent turnAgent,Agent otherAgent) {
		turnAgent.addScore(param.R);
		otherAgent.addScore(param.C);
		//writeCoop(turnAgent.id, otherAgent.id);
		addDebugCommentLog(round,turnAgent.id,otherAgent.id);
	}
	@Override
	public void notCoopNotCommentScore(Agent turnAgent,Agent otherAgent) {
		//deleteCoop(turnAgent.id, otherAgent.id);
	}

	@Override
	protected void throughCoop(int agent,int target){
		//deleteCoop(agent, target);
	}

	@Override
	public void init(RecipParam _param) {
		super.init(_param);
		for (Agent a:_param.agents.getAgentList()) {
			for (Agent b : param.getNeighbor(a)) {
				writeCoop(a.id, b.id);
			}
		}
	};

	@Override
	public void metaComment(Agent turnAgent, Agent otherAgent, double S){

		ArrayList<Agent> group=getAgentsplayMeta(otherAgent, turnAgent );
		for (Agent anotherAgent :group) {

			//気づいたら
			if(beNoticedArticle(S, anotherAgent.id)){


			//褒賞する気になるか
			if(doesCoopComment(anotherAgent)){
				coopCommentScore(otherAgent,anotherAgent);
				writeCoop(otherAgent.id, anotherAgent.id);
			}else{
				coopNotCommentScore(otherAgent,anotherAgent);
				deleteCoop(otherAgent.id, anotherAgent.id);
			}

			}else {throughCoop(otherAgent.id, anotherAgent.id);deleteCoop(otherAgent.id, anotherAgent.id);}
		}

	}

}

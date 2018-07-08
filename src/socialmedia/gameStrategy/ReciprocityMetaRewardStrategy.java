package socialmedia.gameStrategy;

import java.util.ArrayList;
import java.util.List;

import network.Agent;

public class ReciprocityMetaRewardStrategy extends ReciprocityStrategy {

	@Override
	public void cooperat(Agent turnAgent,double S){
		submissionScore(turnAgent);
		//強調されて周りは利得を得る
		for (Agent otherAgent : param.getNeighbor(turnAgent)) {
			if(turnAgent==otherAgent)continue;
			subscriptionScore(otherAgent);

			//気づいたら
			if(beNoticedArticle(S,otherAgent.id)){

				//otherからみてturnはいいやつか
				if(existCoop(otherAgent.id,turnAgent.id)){
					//褒賞する気になるか
					if(doesCoopComment(otherAgent)){
						coopCommentScore(turnAgent,otherAgent);
						metaComment(turnAgent, otherAgent, S);
					}else{
						coopNotCommentScore(turnAgent,otherAgent);
						otherAgent.addfreeride_kizi();
					}

				}else{
					//褒賞する気になるか
					if(doesNotCoopComment(otherAgent)){
						notCoopCommentScore(turnAgent,otherAgent);
						metaComment(turnAgent, otherAgent, S);
					}else{
						notCoopNotCommentScore(turnAgent,otherAgent);
						otherAgent.addfreeride_kizi();
					}
				}

			}else throughCoop(turnAgent.id, otherAgent.id);


		}
	}

	public List<Agent> getAgentsplayMeta(Agent commenter,Agent turnAgent){
		List<Agent> ret=new ArrayList<Agent>(param.getNeighbor(commenter)) ;
//		ret.remove(turnAgent);//**重要らしい**
		return ret;
	}

	public void metaComment(Agent turnAgent, Agent otherAgent, double S){

		List<Agent> group=getAgentsplayMeta(otherAgent, turnAgent );
		for (Agent anotherAgent :group) {
		//Agent_LogScore anotherAgent=turnAgent;

			//気づいたら
			if(beNoticedArticle(S, anotherAgent.id)) {

				//otherからみてturnはいいやつか
				if (existCoop(anotherAgent.id, otherAgent.id)) {
					//褒賞する気になるか
					if (doesCoopComment(anotherAgent)) {
						coopCommentScore(otherAgent, anotherAgent);
					} else {
						coopNotCommentScore(otherAgent, anotherAgent);
						if(turnAgent==anotherAgent) {
							anotherAgent.addfreeride_comment();
						}
					}

				} else {
					//褒賞する気になるか
					if (doesNotCoopComment(anotherAgent)) {
						notCoopCommentScore(otherAgent, anotherAgent);
					} else {
						notCoopNotCommentScore(otherAgent, anotherAgent);
						if(turnAgent==anotherAgent) {
							anotherAgent.addfreeride_comment();
						}
					}
				}
			}


			//}else throughCoop(otherAgent.id, anotherAgent.id);
		}

	}




}

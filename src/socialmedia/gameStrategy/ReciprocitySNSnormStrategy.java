package socialmedia.gameStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import network.Agent;

public class ReciprocitySNSnormStrategy extends ReciprocityMetaRewardStrategy {

	@Override
	public ArrayList<Agent> getAgentsplayMeta(Agent commenter,Agent turnAgent){
		ArrayList<Agent> ret=new ArrayList<Agent>(Arrays.asList(turnAgent));
		return ret;
	}

	@Override
	public void metaComment(Agent turnAgent, Agent otherAgent, double S) {

		List<Agent> group = getAgentsplayMeta(otherAgent, turnAgent);
		for (Agent anotherAgent : group) {
			//Agent_LogScore anotherAgent=turnAgent;

			//気づいたら
//			if (beNoticedArticle(S, anotherAgent.id)) {                //先行研究との比較のため削除 2016/11/08

				//otherからみてturnはいいやつか
				if (existCoop(anotherAgent.id, otherAgent.id)) {
					//褒賞する気になるか
					if (doesCoopComment(anotherAgent)) {
						coopCommentScore(otherAgent, anotherAgent);
					} else {
						coopNotCommentScore(otherAgent, anotherAgent);
					}

				} else {
					//褒賞する気になるか
					if (doesNotCoopComment(anotherAgent)) {
						notCoopCommentScore(otherAgent, anotherAgent);
					} else {
						notCoopNotCommentScore(otherAgent, anotherAgent);
					}
				}
			}
//		}
	}

	
}

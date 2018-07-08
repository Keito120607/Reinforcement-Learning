package socialmedia.gameStrategy;

import network.Agent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogReciprocitySNSnormStrategy extends LogReciprocityMetaRewardStrategy {

	@Override
	public ArrayList<Agent> getAgentsplayMeta(Agent commenter,Agent turnAgent){
		ArrayList<Agent> ret=new ArrayList<Agent>(Arrays.asList(turnAgent));
		return ret;
	}

	@Override
	public void metaComment(Agent turnAgent, Agent otherAgent, double S){

		List<Agent> group=getAgentsplayMeta(otherAgent, turnAgent );
		for (Agent anotherAgent :group) {
			//Agent_LogScore anotherAgent=turnAgent;

			//気づいたら
//			if(beNoticedArticle(S, anotherAgent.id)) {			//先行研究との比較のため削除 2016/11/08　自分へのコメントの見逃しを無しにするならここを削る

				//otherからみてturnはいいやつか
				if (existCoop(anotherAgent.id, otherAgent.id)) {
					//褒賞する気になるか
					if (doesCoopComment(anotherAgent)) {
						coopMetaCommentScore(otherAgent, anotherAgent);
					} else {
						coopNotCommentScore(otherAgent, anotherAgent);
					}

				} else {
					//褒賞する気になるか
					if (doesNotCoopComment(anotherAgent)) {
						notCoopMetaCommentScore(otherAgent, anotherAgent);
					} else {
						notCoopNotCommentScore(otherAgent, anotherAgent);
					}
				}
//			}


			//}else throughCoop(otherAgent.id, anotherAgent.id);
		}

		otherAgent.addmetaRscoretoScore(param.R);

	}

	
}

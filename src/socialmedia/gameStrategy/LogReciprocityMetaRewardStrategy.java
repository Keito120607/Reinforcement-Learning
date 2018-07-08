package socialmedia.gameStrategy;

import network.Agent;

import java.util.ArrayList;
import java.util.List;

public class LogReciprocityMetaRewardStrategy extends LogReciprocityStrategy {

	//エージェント同士が友達であるかどうかを調べる関数
	public void friends(Agent turnAgent, Agent anotherAgent){
		for(Agent agent : param.getNeighbor(turnAgent)){
			if(agent.id != anotherAgent.id){
				System.out.println("not a friend");
			}
		}

	}

	//互恵関係にあるときに行うコメント行動
    public void coopMetaCommentScore(Agent turnAgent,Agent otherAgent) {
//        turnAgent.addScore(param.R);
        turnAgent.addmetaR(param.R);
        otherAgent.addScore(param.C);
        otherAgent.addcomment(turnAgent.id);
        writeCoop(turnAgent.id, otherAgent.id);
        addDebugCommentLog(round,turnAgent.id,otherAgent.id);
    }

    //互恵関係にないときに行うコメント行動
    public void notCoopMetaCommentScore(Agent turnAgent,Agent otherAgent) {
//        turnAgent.addScore(param.R);
        turnAgent.addmetaR(param.R);
        otherAgent.addScore(param.C);


        	otherAgent.addcomment(turnAgent.id);
        writeCoop(turnAgent.id, otherAgent.id);
        addDebugCommentLog(round,turnAgent.id,otherAgent.id);
    }
	
	@Override
	public void cooperat(Agent turnAgent,double S){
		submissionScore(turnAgent);
		//強調されて周りは利得を得る
		for (Agent otherAgent : param.getNeighbor(turnAgent)) {
			if(turnAgent==otherAgent)continue;
			subscriptionScore(otherAgent);


			//気づいたら
			if(beNoticedArticle(S,otherAgent.id)){	//S´

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

		turnAgent.addRsocretoScore(param.R);
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
						coopMetaCommentScore(otherAgent, anotherAgent);
					} else {
						coopNotCommentScore(otherAgent, anotherAgent);
						if(turnAgent==anotherAgent) {
							anotherAgent.addfreeride_comment();
						}
					}

				} else {
					//褒賞する気になるか
					if (doesNotCoopComment(anotherAgent)) {
						notCoopMetaCommentScore(otherAgent, anotherAgent);
//						friends(turnAgent,anotherAgent);	//友達かどうか調べる関数
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
		
		otherAgent.addmetaRscoretoScore(param.R);

	}




}

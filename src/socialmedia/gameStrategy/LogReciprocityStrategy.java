package socialmedia.gameStrategy;

import network.Agent;

/**
 * Created by y.miura on 2016/11/08.
 */
public class LogReciprocityStrategy extends ReciprocityStrategy {







        //互恵関係にあるときに行うコメント行動
    public void coopCommentScore(Agent turnAgent,Agent otherAgent) {
//        turnAgent.addScore(param.R);
        turnAgent.addR(param.R);
        otherAgent.addScore(param.C);
        	otherAgent.addcomment(turnAgent.id);
        writeCoop(turnAgent.id, otherAgent.id);
        addDebugCommentLog(round,turnAgent.id,otherAgent.id);
    }

    //互恵関係にないときに行うコメント行動
    public void notCoopCommentScore(Agent turnAgent,Agent otherAgent) {
//        turnAgent.addScore(param.R);
        turnAgent.addR(param.R);
        otherAgent.addScore(param.C);

        otherAgent.addcomment(turnAgent.id);
        writeCoop(turnAgent.id, otherAgent.id);
        addDebugCommentLog(round,turnAgent.id,otherAgent.id);
    }

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
                    }else{
                        coopNotCommentScore(turnAgent,otherAgent);
                        otherAgent.addfreeride_kizi();
                    }

                }else{
                    //褒賞する気になるか
                    if(doesNotCoopComment(otherAgent)){
                        notCoopCommentScore(turnAgent,otherAgent);
                    }else{
                        notCoopNotCommentScore(turnAgent,otherAgent);
                        otherAgent.addfreeride_kizi();
                    }
                }

            }else throughCoop(turnAgent.id, otherAgent.id);


        }

        turnAgent.addRsocretoScore(param.R);
    }

}
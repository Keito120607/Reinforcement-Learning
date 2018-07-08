package socialmedia.gameStrategy;

import network.Agent;

/**
 * Created by y.miura on 2016/10/16.
 */
public class GeneralPunishmentStrategy extends ReciprocityNotExistsStrategy{

    @Override
    public void cooperat(Agent turnAgent,double S) {
    }


    @Override
    public void defect(Agent turnAgent, double S){
        DsubmissionScore(turnAgent);
        //裏切りし、周りは損害をうける
        for (Agent otherAgent : param.getNeighbor(turnAgent)) {
            if(turnAgent==otherAgent)continue;
            DsubscriptionScore(otherAgent);


            //気づいたら
            if(beNoticedArticle(S,otherAgent.id)){


                if(existCoop(otherAgent.id,turnAgent.id)){

                    if(doesCoopComment(otherAgent)){
                        coopCommentScore(turnAgent,otherAgent);
                    }else{
                        coopNotCommentScore(turnAgent,otherAgent);
                    }

                }else{
                    //罰するかどうか
                    if(doesCoopComment(otherAgent)){
                        DnotCoopCommentScore(turnAgent,otherAgent);
                    }else{
                        notCoopNotCommentScore(turnAgent,otherAgent);
                    }
                }

            }else throughCoop(turnAgent.id, otherAgent.id);


        }
    }

    public void DsubmissionScore(Agent turnAgent) {
        //裏切り利得　T = -F
        turnAgent.addScore(-param.F);
    }

    public void DsubscriptionScore(Agent otherAgent) {
        //裏切りによる痛手 H = -M
        otherAgent.addScore(-param.M);
    }

    public void DnotCoopCommentScore(Agent turnAgent,Agent otherAgent) {
        //懲罰コスト E = C
        //懲罰による痛手 P = -R
        turnAgent.addScore(-param.R);
        otherAgent.addScore(param.C);
        writeCoop(turnAgent.id, otherAgent.id);
        addDebugCommentLog(round,turnAgent.id,otherAgent.id);
    }

}

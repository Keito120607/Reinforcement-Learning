package socialmedia.gameStrategy;

import network.Agent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by y.miura on 2016/10/16.
 */
public class GeneralMetaPunishmentStrategy extends NoReciprocityMetaRewardStrategy{


    @Override
    public void cooperat(Agent turnAgent,double S) {
    }


    @Override
    public void defect(Agent turnAgent, double S){
        DsubmissionScore(turnAgent);
        //裏切りをし、まわりは損害を受ける
        for (Agent otherAgent : param.getNeighbor(turnAgent)) {
            if(turnAgent==otherAgent)continue;
            DsubscriptionScore(otherAgent);

            //気づいたら
            if(beNoticedArticle(S,otherAgent.id)){


                if(existCoop(otherAgent.id,turnAgent.id)){
                    if(doesCoopComment(otherAgent)){
                        coopCommentScore(turnAgent,otherAgent);
                        metaComment(turnAgent, otherAgent, S);
                    }else{
                        coopNotCommentScore(turnAgent,otherAgent);
                    }

                }else{
                    //罰するかどうか
                    if(doesCoopComment(otherAgent)){
                       DnotCoopCommentScore(turnAgent,otherAgent);
                    }else{
                        notCoopNotCommentScore(turnAgent,otherAgent);
                        metaPunishment(turnAgent, otherAgent, S);
                    }
                }

            }else throughCoop(turnAgent.id, otherAgent.id);


        }
    }



    public List<Agent> getAgentsplayMeta(Agent commenter, Agent turnAgent){
        List<Agent> ret=new ArrayList<Agent>(param.getNeighbor(commenter)) ;
        ret.remove(turnAgent);//**重要らしい**
        return ret;
    }

    private void metaPunishment(Agent turnAgent, Agent otherAgent, double S) {
        List<Agent> group=getAgentsplayMeta(otherAgent, turnAgent );
        for (Agent anotherAgent :group) {
            //Agent_LogScore anotherAgent=turnAgent;

            //気づいたら
            if(beNoticedArticle(S, anotherAgent.id)){


                if(existCoop(anotherAgent.id,otherAgent.id)){

                    if(doesCoopComment(anotherAgent)){
                        coopCommentScore(otherAgent,anotherAgent);
                    }else{
                        coopNotCommentScore(otherAgent,anotherAgent);
                    }

                }else{
                    //罰するかどうか
                    if(doesCoopComment(anotherAgent)){
                        DnotCoopCommentScore(otherAgent,anotherAgent);
                    }else{
                        notCoopNotCommentScore(otherAgent,anotherAgent);
                    }
                }

            }else throughCoop(otherAgent.id, anotherAgent.id);
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

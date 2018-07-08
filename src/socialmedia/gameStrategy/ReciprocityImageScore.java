package socialmedia.gameStrategy;

import network.Agent;


public class ReciprocityImageScore extends ReciprocityStrategy{
	@Override
	protected boolean existCoop(int agent,int target){
		return isGood(target);
	}

	public boolean isGood(int target){
		int score=0;
		for (Agent viewer:getAgentList()) {
			if(viewer.id==target)continue;
			if(super.existCoop(viewer.id, target))score++;
		}
		if(score>=getAgentList().size()/2)return true;

		return false;
	}
}
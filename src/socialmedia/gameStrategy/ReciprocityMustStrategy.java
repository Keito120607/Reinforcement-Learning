package socialmedia.gameStrategy;

import network.Agent;


public class ReciprocityMustStrategy extends ReciprocityStrategy{
	public double persent=1.0;
	@Override
	public boolean doesCoopComment(Agent otherAgent){
		//return otherAgent.getVengefulness()>random;
		return persent>param.random.nextDouble();
	}

	@Override
	protected void throughCoop(int agent,int target){

	}
}
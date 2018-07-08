package socialmedia.gameStrategy;

import network.Agent;

public class ReciprocityErrorStrategy extends ReciprocityStrategy{
	public double ErrP=0.01;
	protected double ErrB=0;
	@Override
	protected boolean existCoop(int agent,int target){
		boolean tmp=super.existCoop(agent, target);
		if(ErrP>param.random.nextDouble())tmp=!tmp;
		return tmp;
	}
	@Override
	public boolean doesCoopComment(Agent otherAgent){
		boolean tmp2=super.doesCoopComment(otherAgent);
		if(ErrB>param.random.nextDouble())tmp2=!tmp2;
		return tmp2;
	}
	@Override
	public boolean doesNotCoopComment(Agent otherAgent){
		boolean tmp2=super.doesNotCoopComment(otherAgent);
		if(ErrB>param.random.nextDouble())tmp2=!tmp2;
		return tmp2;
	}
}

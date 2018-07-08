package socialmedia.gameStrategy;

import java.util.HashMap;

public class ReciprocityForgivingLongSpan extends ReciprocityLongSpanStrategy{

	HashMap<Integer, HashMap<Integer, Integer>> deleteCount;
	int forgivVal=4;

	@Override
	protected void writeCoop(int agent,int target){
		super.writeCoop(agent, target);
		deleteCount.get(agent).put(target, 0);
	}
	@Override
	protected void deleteCoop(int agent,int target){
		int tmp=deleteCount.get(agent).get( target);
		deleteCount.get(agent).put( target,tmp+1);
	}

	/**
	 *  忘却機能付き
	 */
	@Override
	protected boolean existCoop(int agent,int target){
		boolean tmp=super.existCoop(agent, target);
		if(deleteCount.get(agent).get( target)>=forgivVal){
			deleteCount.get(agent).put( target,0);
			return true;
		}

		return tmp;
	}
	public void setForgivVal(int v){forgivVal=v;}

	@Override
	public void init(RecipParam p){
		super.init(p);
		deleteCount=new HashMap<Integer, HashMap<Integer,Integer>>();
		for (int i = 0; i < p.getAgentList().size(); i++) {
			deleteCount.put(i,new HashMap<Integer,Integer>());
			for (int j = 0; j < p.getAgentList().size(); j++) {
				deleteCount.get(i).put(j,0);

			}
		}

	}


}

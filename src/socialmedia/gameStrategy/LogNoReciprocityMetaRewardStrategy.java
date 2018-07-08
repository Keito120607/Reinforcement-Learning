package socialmedia.gameStrategy;


public class LogNoReciprocityMetaRewardStrategy extends LogReciprocityMetaRewardStrategy {
	@Override
	protected void writeCoop(int agent,int target){

	}
	@Override
	protected void deleteCoop(int agent,int target){

	}

	@Override
	protected boolean existCoop(int agent,int target){
		return false;
	}




}

package socialmedia.gameStrategy;

public class LogNoReciprocitySNSnormStrategy extends LogReciprocitySNSnormStrategy {
	@Override
	protected boolean existCoop(int agent,int target){
		return false;
	}
}

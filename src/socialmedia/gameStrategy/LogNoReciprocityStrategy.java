package socialmedia.gameStrategy;

public class LogNoReciprocityStrategy extends LogReciprocityStrategy {
	@Override
	protected boolean existCoop(int agent,int target){
		return false;
	}
}

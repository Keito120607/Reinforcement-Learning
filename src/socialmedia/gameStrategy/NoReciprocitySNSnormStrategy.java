package socialmedia.gameStrategy;

public class NoReciprocitySNSnormStrategy extends ReciprocitySNSnormStrategy {
	@Override
	protected boolean existCoop(int agent,int target){
		return false;
	}
}

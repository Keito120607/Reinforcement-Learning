package socialmedia.gameStrategy;

public class ReciprocityNotExistsStrategy extends ReciprocityStrategy {
	@Override
	protected boolean existCoop(int agent,int target){
		return false;
	}
}

package socialmedia.gameStrategy;

import network.Agent;

public class ReciprocityStandingStrategy extends ReciprocityImageScore {
	@Override
	public void notCoopNotCommentScore(Agent turnAgent,Agent otherAgent) {
		writeCoop(turnAgent.id, otherAgent.id);
	}
}

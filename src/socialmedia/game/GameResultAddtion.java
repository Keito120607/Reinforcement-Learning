package socialmedia.game;

import java.util.Collection;


public interface GameResultAddtion<P extends GameParam> {
	abstract public Collection<? extends Double> getPrint(P param, int generation);
}

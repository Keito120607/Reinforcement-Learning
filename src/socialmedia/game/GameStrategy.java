package socialmedia.game;

import java.util.ArrayList;

import network.Agent;

public abstract class GameStrategy <P extends GameParam>{
	protected P param;
	final public void execGame(P _param, int i){
		runGame(i);
	}

	 protected abstract void runGame(int i);
	 public void init(P _param){
		 param=_param;
	 }

	 final public ArrayList<Agent> getAgentList(){
		 return param.getAgentList();
	 }

	 public void initGeneration(P p) {	}
	 public void initGame(P p) {	}
}

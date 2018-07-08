package socialmedia.game;

import java.util.ArrayList;
import java.util.List;

import network.Agent;
import network.AgentGroup;
import utility.MyRandom;

abstract public class GameParam {
	public String fileName;
	public AgentGroup agents;
	public MyRandom random;
	public int GenerationTime;
	public List<String> headlist;
	public void init(){}
	public ArrayList<Agent> getAgentList() {
		return agents.getAgentList();
	}
	public List<Agent>  getNeighbor(Agent agent) {
		return agents.getNeighbor(agent);
	}

}

package network;

import java.util.ArrayList;

import utility.MyRandom;

public interface AgentGroup {
	public ArrayList<Agent> getAgentList();
	public ArrayList<Agent> getNeighbor(Agent agent);
	public void setAgents(ArrayList<Agent> agents);
	public void resetAgents(MyRandom mr);
}

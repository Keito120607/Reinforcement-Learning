package network;

import java.util.ArrayList;

import utility.MyRandom;

public class AgentCompleteGraphList implements AgentGroup {

	public ArrayList<Agent> agentList;
	private int agentNum;

	public  AgentCompleteGraphList(int num,MyRandom  _r) {
		agentNum=num;
		resetAgents(_r);
	}

	public AgentCompleteGraphList(int num) {
		agentNum=num;
	}

	@Override
	public void resetAgents(MyRandom mr){
		agentList=new ArrayList<Agent>();
		for (int i = 0; i < agentNum; i++) {
			addAgent(mr);
		}
	}

	public Agent addAgent(MyRandom random) {
		Agent agent=new Agent(agentList.size(),random);
		agentList.add(agent);
		return agent;
	}

	@Override
	public ArrayList<Agent> getAgentList() {
		return agentList;
	}

	@Override
	public ArrayList<Agent> getNeighbor(Agent agent) {
		@SuppressWarnings("unchecked")
		ArrayList<Agent> copy = (ArrayList<Agent>) agentList.clone();
		copy.remove(agent);
		return copy;
	}
	@Override
	public void setAgents(ArrayList<Agent> agents) {
		agentList=agents;

	}





}

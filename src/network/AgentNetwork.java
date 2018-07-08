package network;

import java.util.ArrayList;
import java.util.HashMap;

import utility.MyRandom;

public class AgentNetwork implements AgentGroup{
	private ArrayList<Agent> agentList;

	public NetworkInfo netInfo;


	HashMap<Integer,ArrayList<Agent>> cache;





	static private HashMap<Integer, ArrayList<Agent>> makeCache(NetworkInfo info,ArrayList<Agent> list){
		HashMap<Integer,ArrayList<Agent>> ret=new HashMap<Integer, ArrayList<Agent>>();

		for (Agent a : list) {
			ret.put(a.id, new ArrayList<Agent>(info.getNeighbors(a.id).size()));
			for (int target : info.getNeighbors(a.id)) {
				ret.get(a.id).add(list.get(target));
			}
		}
		return ret;

	}




	public AgentNetwork(NetworkInfo info, MyRandom m){
		netInfo=info;
		resetAgents(m);
		//cache=new HashMap<Integer, ArrayList<Agent_LogScore>>();

	}

	public AgentNetwork(NetworkInfo info){
		netInfo=info;
	}

	@Override
	public void setAgents(ArrayList<Agent> agents) {
		agentList=agents;
		cache=makeCache(this.netInfo, agentList);
	}

	@Override
	public void resetAgents(MyRandom m){
		int n=netInfo.maxAgentNum;
		ArrayList<Agent> ret=new ArrayList<Agent>(n);
		for (int i = 0; i < n; i++) {
			ret.add(new Agent(i,m));
		}
		setAgents(ret);
	}



	public ArrayList<Agent> getAgents() {
		return agentList;
	}


	public Agent getAgent(int index){
		/*if(index<0)index+=agentList.size();
		if(index>=agentList.size())index-=agentList.size();*/
		return agentList.get(index);
	}

	//TODO: かくにん
	private  ArrayList<Agent> getNeighbor(int id){

		if(cache.containsKey(id)){
			//if(!retAgents.equals(cache.get(id))) throw new RuntimeException("adsfdas");
			return cache.get(id);
		}

		ArrayList<Agent> retAgents=new ArrayList<Agent>( netInfo.getNeighbors(id).size());
		for (int otherid : netInfo.getNeighbors(id)) {
			retAgents.add(getAgent(otherid));
		}



		cache.put(id,retAgents);






		//return netInfo.getNeighbors(id);


		return retAgents;

		//return cache.get(id);
	}




	@Override
	public ArrayList<Agent> getAgentList() {
		return getAgents();
	}

	@Override
	public ArrayList<Agent> getNeighbor(Agent agent) {
		return getNeighbor(agent.id);
	}


}

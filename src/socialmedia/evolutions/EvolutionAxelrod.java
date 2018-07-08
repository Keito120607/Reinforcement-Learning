//package socialmedia.evolutions;
//
//import java.util.ArrayList;
//import java.util.Collections;
//
//import network.Agent;
//import socialmedia.game.GameParam;
//import utility.MyRandom;
//
//public class EvolutionAxelrod extends EvolutionStrategy {
//
//
//
//	public ArrayList<Agent> evolution(ArrayList<Agent> agentList,MyRandom random) {
//
//		double ave=getAverage(agentList);
//		double sd=getSD(agentList,ave);
//		ArrayList<Agent> newGeneration=new ArrayList<Agent>();
//
//		ArrayList<Agent> high=new ArrayList<Agent>();
//		ArrayList<Agent> mid=new ArrayList<Agent>();
//
//		//h,m,lの3グループに分け、h!=lのときmをすくないほうのぐるーぷにいどうさせる
//		for (Agent agent : agentList) {
//			if(agent.score<ave-sd){
//				continue;
//			}
//			else if(agent.score>ave+sd){
//				high.add(agent.clone());
//				high.add(agent.clone());
//			}else{
//				mid.add(agent.clone());
//			}
//		}
//		while(high.size()+mid.size()<20){
//			int id=random.nextInt(mid.size());
//			high.add(mid.get(id).clone());
//		}
//		while(high.size()+mid.size()>20){
//			int id=random.nextInt(mid.size());
//			mid.remove(id);
//		}
//
//		newGeneration.addAll(high);
//		newGeneration.addAll(mid);
//
//		//endでソート
//		Collections.sort(newGeneration, new Agent.AgentComparator());
//		agentList=new ArrayList<Agent>();
//		for (int i = 0; i < 20; i++) {
//			agentList.add(newGeneration.get(i));
//		}
//
//		for (Agent agent : agentList) {
//			agent.mutation();
//			agent.score=0;
//		}
//		return agentList;
//	}
//	public double getAverage(ArrayList<Agent> agentList){
//		int sum=0;
//		for (Agent agent : agentList) {
//			sum+=agent.score;
//		}
//		double ave=sum/(double)agentList.size();
//		return ave;
//	}
//	public double getSD(ArrayList<Agent> agentList,double ave){
//		double sdc=0;
//		for (Agent agent : agentList) {
//			sdc+=Math.pow(agent.score-ave,2);
//		}
//		double sd=Math.sqrt(sdc/agentList.size());
//		return sd;
//	}
//	@Override
//	public void evolution(GameParam p) {
//		// TODO 自動生成されたメソッド・スタブ
//		ArrayList<Agent> hoge=evolution(p.getAgentList(), p.random);
//		p.agents.setAgents(hoge);
//
//	}
//}

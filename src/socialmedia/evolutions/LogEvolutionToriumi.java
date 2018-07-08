//package socialmedia.evolutions;
//
//import network.Agent;
//import socialmedia.game.GameParam;
//import utility.MyRandom;
//
//import java.util.ArrayList;
//
//public class LogEvolutionToriumi extends EvolutionStrategy{
//
//	public void evolution(GameParam param) {
//
//		ArrayList<Agent> agentList=param.getAgentList();
//
////		System.out.println("LogEVOLUTION");
//			//ログスケール
//		for(Agent agent:agentList){
////			System.out.println("[before]"+agent.score);
//			agent.score = Math.log(agent.score);
////			System.out.println("[after]"+agent.score);
//
//		}
//
//
//		ArrayList<Agent> newG=new ArrayList<Agent>();
//		for(Agent agent:agentList){
//
//			ArrayList<Agent> perentList=new ArrayList<Agent>();
//			perentList.addAll((param.getNeighbor(agent)));
//			perentList.add(agent);
//			Agent father=rouletteChoice(perentList,param.random);
//			perentList.remove(father);
//			Agent mother=rouletteChoice(perentList,param.random);
//			perentList.remove(mother);
//
//			Agent n =agent.clone();
//			n.evolution(father,mother);
//			newG.add(n);
//		}
//
//		for (Agent agent : newG) {
//			agent.mutation();
//			agent.score=0;
//		}
//		for (int i = 0; i < newG.size(); i++) {
//			if(newG.get(i).id!=agentList.get(i).id)System.out.println("えらあ");
//
//		}
//
//
//		param.agents.setAgents(newG);
//	}
//
//	public Agent rouletteChoice(ArrayList<Agent> agentList,MyRandom random){
//		int NaNcnt=0;
//		double max = 0.0;
//
//		double Vmin=Integer.MAX_VALUE;
//
//		for (Agent agent : agentList) {
//			if(Vmin>agent.score)Vmin=agent.score;		//最も適応度の低い値を持つエージェントを探す
//		}
//
//		/*
//		ArrayList<Agent_LogScore> cloneList=new ArrayList<Agent_LogScore>(agentList);
//		//ソート
//		Collections.sort(cloneList, new Agent_LogScore.AgentComparator());
//		double Vmin=cloneList.get(cloneList.size()-1).score;
//*/		double bunbo=0;
//
//		for(Agent agent:agentList){
//			bunbo+=(Math.pow(agent.score-Vmin,2));		//Σj(vj -vmin)^2　←分母になる
//		}
//
//		ArrayList<Double> rotate= new ArrayList<Double>();
//		for(Agent agent:agentList){
//			rotate.add(Math.pow(agent.score-Vmin,2)/(double)bunbo);		//各エージェントの適応度を、選択確立に変換したものをArrayList rotateにぶっこむ
//		}
//
//		for(double r:rotate){
//			max += r;
//			if(Double.isNaN(r))NaNcnt+=1;
//		}
//
//		if(NaNcnt==rotate.size())return agentList.get(random.nextInt(rotate.size()));
//
//		double temp = random.nextDouble()*max;
//		for(int i = 0;i < rotate.size() ;i++ ){
//			temp -= rotate.get(i);
//			if(temp < 0)
//				return agentList.get(i);
//		}
//
//		System.out.println(agentList);
//
//		System.out.println("予期しないエラー＠ルーレット選択");
//		return null;
//	}
//
//
//}

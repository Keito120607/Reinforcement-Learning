package socialmedia.evolutions;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Set;

import network.Agent;
import network.NetworkInfo;
import socialmedia.game.GameParam;
import utility.MyRandom;

public class EvolutionToriumi extends EvolutionStrategy{

	public ArrayList evolution(GameParam param) {



		ArrayList<Agent> agentList=param.getAgentList();
		ArrayList<Double> list = new ArrayList<Double>();

//		System.out.println("EVOLUTION");
			//ログスケール

		for(Agent agent:agentList){
			agent.parents_count=0;
		}



		ArrayList<Agent> newG=new ArrayList<Agent>();
		for(Agent agent:agentList){


			ArrayList<Agent> perentList=new ArrayList<Agent>();
			perentList.addAll((param.getNeighbor(agent)));
			perentList.add(agent);
			Agent father=rouletteChoice(perentList,param.random);
			perentList.remove(father);
//			father.parents_count++;

			Agent mother=rouletteChoice(perentList,param.random);
			perentList.remove(mother);
//			mother.parents_count++;

			Agent n =agent.clone();
			n.evolution(father,mother);
			for (int i = 0; i < 10000; i++) {
				n.parents.set(i,0.0);
			}
				n.parents.set(father.id,n.parents.get(father.id)+1.0);
				n.parents.set(mother.id,n.parents.get(mother.id)+1.0);
			newG.add(n);
		}

		for (Agent agent : newG) {
			agent.mutation_count=0;
			agent.mutation();
			agent.score=0;

		}
		for (int i = 0; i < newG.size(); i++) {
			if(newG.get(i).id!=agentList.get(i).id)System.out.println("えらあ");

		}

		//エージェントが1世代で親に選ばれた回数
		for(Agent agent:agentList){
			list.add(agent.parents_count);
		}


		param.agents.setAgents(newG);

		return list;
	}







	public Agent rouletteChoice(ArrayList<Agent> agentList,MyRandom random){

		int NaNcnt=0;
		double max = 0.0;

		double Vmin=Integer.MAX_VALUE;

		for (Agent agent : agentList) {
			if(Vmin>agent.score)Vmin=agent.score;		//最も適応度の低い値を持つエージェントを探す
		}

		/*
		ArrayList<Agent_LogScore> cloneList=new ArrayList<Agent_LogScore>(agentList);
		//ソート
		Collections.sort(cloneList, new Agent_LogScore.AgentComparator());
		double Vmin=cloneList.get(cloneList.size()-1).score;
*/		double bunbo=0;

		for(Agent agent:agentList){
			bunbo+=(Math.pow(agent.score-Vmin,2));		//Σj(vj -vmin)^2　←分母になる
		}

		ArrayList<Double> rotate= new ArrayList<Double>();
		for(Agent agent:agentList){
			rotate.add(Math.pow(agent.score-Vmin,2)/(double)bunbo);		//各エージェントの適応度を、選択確立に変換したものをArrayList rotateにぶっこむ
		}

		for(double r:rotate){
			max += r;
			if(Double.isNaN(r))NaNcnt+=1;
		}

		if(NaNcnt==rotate.size()) {
			int i = random.nextInt(rotate.size());
			agentList.get(i).parents_count++;
			return agentList.get(i);
		}

		double temp = random.nextDouble()*max;
		for(int i = 0;i < rotate.size() ;i++ ){
			temp -= rotate.get(i);
			if(temp < 0) {
				agentList.get(i).parents_count++;
				return agentList.get(i);
			}
		}

		System.out.println(agentList);

		System.out.println("予期しないエラー＠ルーレット選択");
		return null;
	}







}

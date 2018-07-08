package network;

import java.awt.Color;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import socialmedia.game.GameParam;
import socialmedia.gameStrategy.ReciprocityStrategy;

public class NetworkPrinter {
	PrintWriter agentPrinter;


	DecimalFormat df1 = new DecimalFormat("0000");


	public void init(PrintWriter p,int size){
		agentPrinter=p;//new ResultLogger(param.fileName,"csv")
//		df1.setMaximumIntegerDigits(4);
		printAgentForCSVHeader();
	}

	 public void fin(GameParam param){
		printNetworkForCSV(param.agents);
		agentPrinter.close();
	}
	public void print(GameParam param,int time){
		printAgentForCSV(param.agents, time);
	}
	public void printNetworkForCSV(AgentGroup a){
		String[] row={"FromId","ToId","StartTime","EndTime","filltext"};
		agentPrinter.println(String.join(",",row));
		int agents=a.getAgentList().size();

		for (int agent_id=0;agent_id<agents;agent_id++) {
			for (Agent target : a.getNeighbor(a.getAgentList().get(agent_id))) {
				if(target.id<agent_id)continue;
				agentPrinter.println(df1.format(agent_id)+","+df1.format((target.id))+","+"0"+","+"1000,#000000");
			}
		}
		agentPrinter.close();
	}



	public void printBoldNetworkForCSV(ArrayList<Map<Integer, Map<Integer, Set<Integer>>>> logList){
		String[] row={"FromId","ToId","filltext","StartTime","EndTime"};
		agentPrinter.println(String.join(",",row));


		for (Map<Integer, Map<Integer, Set<Integer>>> map : logList) {
			int gen=logList.indexOf(map);
			Map<Integer, Set<Integer>> already=new HashMap<Integer,Set<Integer>>();

			for(Entry<Integer, Map<Integer, Set<Integer>>> data : map.entrySet()){
				for(Entry<Integer, Set<Integer>> tmp:data.getValue().entrySet()){
					int turnP=tmp.getKey();
					for(int otherP:tmp.getValue()){
						if(!already.containsKey(turnP))already.put(turnP,new HashSet<Integer>());
						if(already.get(turnP).contains(otherP))continue;
						agentPrinter.println(otherP+","+(turnP)+","+toHtmlColor(new Color(255,0,0))+","+gen+","+(gen+1));
						already.get(turnP).add(otherP);
					}
				}
			}
		}
	}

	//存在するリンク（片方向）を返す
	public void printReciprocityDirectedNetworkForCSV(Map<Integer,Set<Integer>> map){
		String[] row={"FromId","ToId","StartTime","EndTime"};
		agentPrinter.println(String.join(",",row));

		for (Entry<Integer, Set<Integer>> agent : map.entrySet()) {
			int from=agent.getKey();
			for (Integer to : agent.getValue()) {
				agentPrinter.println(from+","+(to)+","+"0"+","+"10000");
			}
		}
	}

	//双方向あるリンクだけ返す
	public void printReciprocityIndirectedNetworkForCSV(Map<Integer,Set<Integer>> map){
		String[] row={"FromId","ToId","StartTime","EndTime"};
		agentPrinter.println(String.join(",",row));

		for (Entry<Integer, Set<Integer>> agent : map.entrySet()) {
			int from=agent.getKey();
			for (Integer to : agent.getValue()) {
				if(!map.get(to).contains(from))continue;
				agentPrinter.println(from+","+(to)+","+"0"+","+"10000");
			}
		}
	}

	public void printAgentForCSVHeader(){
		String[] row={"nodeid","label","StartTime","EndTime","B","LC","LD","filltext","score","mutation"};
//		String[] row={"FromId","ToId","StartTime","EndTime","filltext"};
		agentPrinter.println(String.join(",",row));
	}
	public void printAgentForCSV(AgentGroup a,int time){


		for (Agent agent:a.getAgentList()) {
//			 Color hoge= new Color((int)(255*agent.getPosting()),(int)(255*agent.getVengefulness()),(int)(255*agent.getRewardness()));
			Color hoge= new Color((int)(255*agent.getPosting()),0,(int)(255*agent.getRewardness()));
			 String[] row={""+df1.format(agent.id),"agent_"+df1.format(agent.id),time+"",(time+1)+"",agent.getPosting()+"",agent.getVengefulness()+"",agent.getRewardness()+"",toHtmlColor(hoge),agent.score+"",agent.mutation_count+""};
			agentPrinter.println(String.join(",",row));
				}


	}

	public String toHtmlColor(double i){
		if(i==1.0) return "#FF0000";
		else return "#000000";
	}



	public String toHtmlColor(Color col) {
		// nullなら白を返す。
		if (col == null) {
		return "#FFFFFF";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("#");
		// 16進数で赤成分を取得、セット
		String colCode = Integer.toHexString(col.getRed());
		sb.append(colCode.length() == 1? "0" + colCode: colCode);
		// 16進数で緑成分を取得、セット
		colCode = Integer.toHexString(col.getGreen());
		sb.append(colCode.length() == 1? "0" + colCode: colCode);
		// 16進数で青成分を取得、セット
		colCode = Integer.toHexString(col.getBlue());
		sb.append(colCode.length() == 1? "0" + colCode: colCode);
		return sb.toString();
	}

	public void close(){agentPrinter.close();}



	static public void printNetworkJson(AgentNetwork a, PrintWriter pw){

		pw.println("{\"nodes\":");
		pw.println(a.getAgents());
		pw.println(",\"links\":[");
		for (Entry<Integer, Set<Integer>> pathNode : a.netInfo.pathMap.entrySet()) {
			for (int end : pathNode.getValue()) {
				pw.println("{\"source\":"+pathNode.getKey()+",\"target\":"+end+",\"value\":1},");
			}
		}

		pw.println("]}");

		pw.close();

	}


	 static public void printNetworkForCyto2(NetworkInfo a, PrintWriter pw){
		//ResultLogger rp=new ResultLogger(fileName);

		for (Entry<Integer, Set<Integer>> pathNode : a.pathMap.entrySet()) {
			for (int end : pathNode.getValue()) {
				pw.println((pathNode.getKey())+" "+(end));
			}
		}
		pw.close();


	}

	static String convetIntToAA(int num){
		char ret[]= new char[4];
		ret[0]=(char) ('A'+num/26/26/26%26);
		ret[1]=(char) ('A'+num/26/26%26);
		ret[2]=(char) ('A'+num/26%26);
		ret[3]=(char) ('A'+num%26);
		return String.valueOf(ret);
	}


}

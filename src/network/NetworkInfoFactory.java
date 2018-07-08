package network;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import network.NetworkInfo.NetworkInfoBuilder;
import utility.MyRandom;

public class NetworkInfoFactory {


	static public NetworkInfo initComplete(int num){

		NetworkInfoBuilder builder=new NetworkInfoBuilder();

		for (int i = 0; i < num; i++) {
			for (int j = 0; j <num; j++) {
				if(i==j)continue;
				builder.addPath(i, j);
			}

		}
		builder.networkInfo.maxAgentNum=num;
		return builder.networkInfo;

	}

	static public NetworkInfo initRandom(double p, int num, MyRandom random){

		NetworkInfoBuilder builder=new NetworkInfoBuilder();

		for (int i = 0; i < num; i++) {
			for (int j = 0; j <num; j++) {
				if(i==j)continue;
				if(p>random.nextDouble()) builder.addPath(i, j);
			}

		}
		builder.networkInfo.maxAgentNum=num;
		return builder.networkInfo;

	}


		//u:確率　num:エージェント数
	static public NetworkInfo initCNN(double u,int num,MyRandom random){
		//AgentNetwork network=new AgentNetwork();



		//ArrayList<Agent_LogScore> agents=new ArrayList<Agent_LogScore>();
		NetworkInfoBuilder potentialNetwork=new NetworkInfoBuilder();
		NetworkInfoBuilder builder=new NetworkInfoBuilder();

		int agentSum=0;

		while (agentSum<num) {
			if(1-u>random.nextDouble()){
				//network.addAgent(random);
				agentSum+=1;//new Agent_LogScore(i,random);
				//if(network.getAgents().size()==1)continue;
				if(agentSum==1)continue;

//int newid=network.agentList.size()-1;
				int latest_id=agentSum-1;
				int otherindex=random.nextInt(latest_id);	//ノードの集合Aの中からランダムに一つのエージェントを選択
				builder.addPath(latest_id,otherindex);

				for (int anothers : builder.networkInfo.getNeighbors(otherindex)) {
					if(anothers==latest_id)continue;
					potentialNetwork.addPath(latest_id, anothers);	//otherindex周りのノードと追加したノードにポテンシャルエッジをはる
				}
			}else{	//確率uでランダムに選ばれたポテンシャルエッジ一つが実エッジになる
				if(potentialNetwork.networkInfo.getPathCount()==0)continue;
				int tmp=random.nextInt(potentialNetwork.networkInfo.getPathCount());
				int fromid=0;
				int toid=0;
				for(Entry<Integer, Set<Integer>> entry:potentialNetwork.pathMap.entrySet()){
					fromid=entry.getKey();
					for (Integer entry2 : entry.getValue()) {
						toid=entry2;
						tmp-=1;
						if(tmp<0)break;
					}
					if(tmp<0)break;
				}
				potentialNetwork.removePath(fromid, toid);
				builder.addPath(fromid, toid);
			}

		}
		builder.networkInfo.maxAgentNum=agentSum;
		//エージェントの数を一緒に返す必要がある
		return builder.networkInfo;
	}

		//num:エージェント数　k:平均次数（偶数） p:張り替える確率
	static public NetworkInfo initWS(int num,int k,double p,MyRandom random){
		/*int num=1000;
		int k=20;
		double p=0.01;*/

		//AgentNetwork network=new AgentNetwork();
		NetworkInfoBuilder builder=new NetworkInfoBuilder();
		//ArrayList<Agent_LogScore> agents=new ArrayList<Agent_LogScore>();
		int agentSum=0;

		//エージェントを追加しそれぞれの前後にパスを張る
		for(int i=0;i<num;i++){
			//agents.add(new Agent_LogScore(i,random));
			agentSum++;//new Agent_LogScore(i,random);
		}

		//for(int i=0;i<network.getAgents().size();i++){

		//各頂点を輪の右隣k/2個までと左隣k/2個までの頂点と隣接させる
		for(int i=0;i<num;i++){
				for(int j=-k/2;j<=k/2;j++){
				if(j==0)continue;
				int tmp=i+j;
				if(tmp<0)tmp+=num;
				else if(tmp>=num)tmp-=num;
				builder.addPath(i,tmp);
			}
		}

		//パスの数から何本つなぎなおすか計算
		int restructNum=(int) Math.round(builder.networkInfo.getPathCount()*p);
		//つなぎなおすパスの決定
		Set<PathNode> restructPathSet=new HashSet<PathNode>();
		while(restructPathSet.size()<restructNum){
			int tmp=random.nextInt(builder.networkInfo.getPathCount());
			int aid=0;
			for(int i=0;i<num;i++){
				aid=i;
				tmp-=builder.networkInfo.pathMap.get(i).size();
				if(tmp<0){break;}
			}
			int pathid=builder.pathMap.get(aid).size()+tmp;
			int startId=aid;
			int endId=builder.pathMap.get(startId).toArray(new Integer[0])[((pathid))];


			restructPathSet.add(new PathNode(startId, endId));
		}

		for (PathNode pathNode : restructPathSet) {
			builder.removePath(pathNode.start, pathNode.end);
			int start=(random.nextBoolean())?pathNode.start:pathNode.end;
			int end=start;
			while(end==start||builder.networkInfo.existPath(start, end)){
				end=random.nextInt(num);
			}
			builder.addPath(start, end);
		}
		//return new AgentNetwork(agents, builder.networkInfo);
		builder.networkInfo.maxAgentNum=agentSum;
		return builder.networkInfo;

	}





		//num:最終的なノードの数 m:追加するノードが持つリンクの数(m<m0)、m0:初期の完全グラフを作るノードの数
	static public NetworkInfo initBA(int num,int m,int m0,MyRandom random){
		/*int num=1000;
		int m=10;
		int m0=10;*/

		//AgentNetwork network=new AgentNetwork();
		//ArrayList<Agent_LogScore> agents=new ArrayList<Agent_LogScore>();
		int agentNum=0;
		NetworkInfoBuilder builder=new NetworkInfoBuilder();

		//完全グラフの用意
		for(int i=0;i<m0;i++){
			//network.addAgent(random);
			//agents.add(new Agent_LogScore(i,random));
			agentNum++;//new Agent_LogScore(i,random);
		}
		for (int i = 0; i < m0; i++) {
			for (int j = 0; j < m0; j++) {
				if(i==j)continue;
				builder.addPath(i, j);

			}
		}
/*
		for(Agent_LogScore a:network.getAgents()){
			for(Agent_LogScore b:network.getAgents()){
				if(a==b)continue;
				network.addPath(a.id,b.id);
			}
		}
*/


		//エージェントを追加しm個のエッジをはる
		//while(agents.size()<num){
		while(agentNum<num){
			int latest_id=agentNum;//agents.size();
			//Agent_LogScore na=new Agent_LogScore(latest_id,random);
			//network.addAgent(random);
			//agents.add(na);
			agentNum++;//new Agent_LogScore(latest_id,random);
			for(int i=0;i<m/2;i++){
				int target=getRoutte(latest_id, builder.networkInfo,random);
				builder.addPath(latest_id,target);
			}

		}
		//return new AgentNetwork(agents, builder.networkInfo);
		builder.networkInfo.maxAgentNum=agentNum;
		return builder.networkInfo;
	}


	static public NetworkInfo importFile(String name){
		return importFile(name, false);
	}

	static public NetworkInfo importFile(String name, boolean useTreeSet){


		NetworkInfoBuilder builder=new NetworkInfoBuilder(useTreeSet);

		File source = new File(name);
		BufferedReader r;
		try {
			r = new BufferedReader( new FileReader(source));
			while(r.ready()){
				String[] pair=r.readLine().split(" ");
				int src=Integer.parseInt(pair[0]);
				int dest=Integer.parseInt(pair[1]);
				builder.addPath(src, dest);

			}
			r.close();
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		//return new AgentNetwork(agents, builder.networkInfo);
		builder.networkInfo.maxAgentNum=builder.networkInfo.count.size();
		return builder.networkInfo;
	}

	static private int getRoutte(int latestid,NetworkInfo network,MyRandom rand){


		//System.exit(2);

		//ki/Σkiの確立でリンクを張る

		//Σkiを計算
		int max=0;
		for(int target=0;target<latestid;target++){
			if(network.existPath(latestid,target))continue;
			max+=network.getAgentPathCount(target);
		}

		int tmp=rand.nextInt(max);

		for(int target=0;target<latestid;target++){
			if(network.existPath(latestid,target))continue;
			tmp-=network.getAgentPathCount(target);

			if(tmp<=0){
				return target;
			}
		}

			return getRoutte(latestid, network,rand);


	}

/*
	static public AgentNetwork  Clone(AgentNetwork src){
		AgentNetwork dest=new AgentNetwork();
		for(Entry<Integer, Set<Integer>> a:src.pathMap.entrySet()){
			int id=a.getKey();
			Set<Integer> set=new HashSet<Integer>(a.getValue());
			dest.pathMap.put(id, set);
		}
		return dest;
	}
*/

	static class PathNode{
		public int start;
		public int end;
		public PathNode(int sid,int eid){
			start=sid;end=eid;
		}
	    public boolean equals(Object o){
	        if((this.start==((PathNode)o).start && this.end==((PathNode)o).end)||(this.start==((PathNode)o).end && this.end==((PathNode)o).start)){
	            return true ;
	        }
	        else{
	            return false ;
	        }
	    }
	    public int hashCode(){
	        return this.start+this.end ;
	    }
		public String toString(){
			return "{\"source\":"+start+",\"target\":"+end+",\"value\":1}";
		}
	}
}

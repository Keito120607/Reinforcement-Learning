package network;

import network.NetworkInfo.NetworkInfoBuilder;
import utility.MyRandom;

public class BAmodel implements Network {
	int num;
	int m;
	int m0;



	public BAmodel(int num, int m, int m0){
		this.num=num;
		this.m=m;
		this.m0=m0;
	}

	@Override
	public NetworkInfo BuildNetwork(MyRandom random) {



		/*int num=1000;
		int m=10;
		int m0=10;*/

			//AgentNetwork network=new AgentNetwork();
			//ArrayList<Agent_LogScore> agents=new ArrayList<Agent_LogScore>();
			int agentNum=0;	//ネットワークに追加されるエージェントの番号
//		System.out.println(agentNum);
			NetworkInfoBuilder builder=new NetworkInfoBuilder();

			//完全グラフの用意
			for(int i=0;i<m0;i++){
				//network.addAgent(random);
				//agents.add(new Agent_LogScore(i,random));
				agentNum++;//new Agent_LogScore(i,random);
//				 System.out.println(agentNum);

			}
			for (int i = 0; i < m0; i++) {
				for (int j = 0; j < m0; j++) {
					if(i==j)continue;
					builder.addPath(i, j);

				}
			}

//		System.out.println("完全グラフ完成");
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
				int latest_id=agentNum;//agents.size();	//次に追加するエージェントのid
				//Agent_LogScore na=new Agent_LogScore(latest_id,random);
				//network.addAgent(random);
				//agents.add(na);
				agentNum++;//new Agent_LogScore(latest_id,random);
				for(int i=0;i<m;i++){
					int target=getRoutte(latest_id, builder.networkInfo,random);
					builder.addPath(latest_id,target);
				}

			}
			//return new AgentNetwork(agents, builder.networkInfo);
			builder.networkInfo.maxAgentNum=agentNum;
			return builder.networkInfo;
		}

	private void getRoutte2(int latestid, NetworkInfo network, MyRandom rand)	{


	}

	 private int getRoutte(int latestid,NetworkInfo network,MyRandom rand){


		//System.exit(2);

		//ki/Σkiの確立でリンクを張る

		//Σkiを計算
		int max=0;
		for(int target=0;target<latestid;target++){
			if(network.existPath(latestid,target))continue;
			max+=network.getAgentPathCount(target);
//			System.out.println("Σki:"+max);
		}

		int tmp=rand.nextInt(max);
//		 System.out.println("[tmp]:"+tmp);

		for(int target=0;target<latestid;target++){
			if(network.existPath(latestid,target))continue;
			tmp-=network.getAgentPathCount(target);
//			System.out.println("tmp:"+tmp);

			if(tmp<=0){
//				System.out.println("target:"+target);
				return target;
			}
		}

		return getRoutte(latestid, network,rand);


	}

		
	

	@Override
	public String NetworkName(int seed) {
		// TODO 自動生成されたメソッド・スタブ
		
		return "ba-"+num+"-"+m+"-"+m0+"-"+seed;
	}

	
	//2016/12/08 未実装　
	@Override
	public double getPathCount() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

}

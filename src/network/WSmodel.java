package network;

import java.util.HashSet;
import java.util.Set;

import network.NetworkInfo.NetworkInfoBuilder;
import network.NetworkInfoFactory.PathNode;
import utility.MyRandom;

public class WSmodel implements Network {
	int num;
	int k;
	double p;

	
	
	public WSmodel(int num,int k,double p){
		this.num=num;
		this.k=k;
		this.p=p;
	}

	@Override
	public NetworkInfo BuildNetwork(MyRandom random) {
		
		
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
		
	

	@Override
	public String NetworkName(int seed) {
		// TODO 自動生成されたメソッド・スタブ
		
		return "ws-"+num+"-"+k+"-"+p+"-"+seed;
	}

	
	//2016/12/08 未実装　
	@Override
	public double getPathCount() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

}

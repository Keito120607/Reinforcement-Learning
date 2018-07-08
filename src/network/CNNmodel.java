package network;

import network.NetworkInfo.NetworkInfoBuilder;
import network.NetworkInfoFactory.PathNode;
import utility.MyRandom;

import java.util.HashSet;
import java.util.Set;
import java.util.Map.Entry;

public class CNNmodel implements Network {
	double u;
	int num;




	public CNNmodel(int num, double u){
		this.num=num;
		this.u=u;
	}

	@Override
	public NetworkInfo BuildNetwork(MyRandom random) {

        //u:確率　num:エージェント数




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
	

	@Override
	public String NetworkName(int seed) {
		// TODO 自動生成されたメソッド・スタブ
		
		return "cnn-"+num+"-"+u+"-"+seed;
	}

	
	//2016/12/08 未実装　
	@Override
	public double getPathCount() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

}

package network;

import network.NetworkInfo.NetworkInfoBuilder;
import network.NetworkInfoFactory.PathNode;
import utility.MyRandom;

import java.util.HashSet;
import java.util.Set;

public class CompleteGraph implements Network {
	int num;




	public CompleteGraph(int num){
		this.num=num;

	}

	@Override
	public NetworkInfo BuildNetwork(MyRandom random) {


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
		
	

	@Override
	public String NetworkName(int seed) {
		// TODO 自動生成されたメソッド・スタブ
		
		return "completegraph-"+num;
	}

	
	//2016/12/08 未実装　
	@Override
	public double getPathCount() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

}

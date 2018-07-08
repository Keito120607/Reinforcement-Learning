package network;

import network.NetworkInfo.NetworkInfoBuilder;
import network.NetworkInfoFactory.PathNode;
import utility.MyRandom;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class SNAP implements Network {

	String name;
	boolean useTreeSet;



	public SNAP(String name){
		this.name = name;
	}



	@Override
	public NetworkInfo BuildNetwork(MyRandom random) {
		this.useTreeSet = false;
		return  importFile();
	}

	public NetworkInfo importFile(){




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

		
	

	@Override
	public String NetworkName(int seed) {
		// TODO 自動生成されたメソッド・スタブ
		
		return "facebook";
	}

	
	//2016/12/08 未実装　
	@Override
	public double getPathCount() {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

}

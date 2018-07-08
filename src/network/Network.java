package network;

import utility.MyRandom;

public interface Network {
	
	public NetworkInfo BuildNetwork(MyRandom random);
	public String NetworkName(int seed);
	public double getPathCount();

}

package network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import utility.SortedPair;

public class NetworkInfo{

	public static  class NetworkInfoBuilder{
		//ソートして片方向にしたものの情報を持つ
		//ネットワーク作る時に全パスのうちから一つ選ぶときに使う
		public Map<Integer,Set<Integer>> pathMap;
		//両方向の情報をもつ
		protected Map<Integer,Set<Integer>> allMap;
		//パスの数をキャッシュする
		Map<Integer, Integer> count;
		//networkinfoの読み取り専用
		NetworkInfo networkInfo;

		boolean useTreeSet=false;

		public NetworkInfoBuilder() {
			pathMap=new HashMap<Integer, Set<Integer>>();
			allMap=new HashMap<Integer, Set<Integer>>();
			count =new HashMap<Integer, Integer>();
			networkInfo=new NetworkInfo(this);
		}

		public NetworkInfoBuilder(boolean _useTreeSet){
			this();
			useTreeSet=_useTreeSet;
		}

		private Set<Integer> newSet() {
			return  useTreeSet?new TreeSet<Integer>():new HashSet<Integer>();
		}

		public boolean addPath(int s,int e){
			SortedPair<Integer> p=new SortedPair<Integer>(s,e);
			int l=p.first;
			int h=p.second;
			if(!pathMap.containsKey(l)){
				pathMap.put(l,newSet());
			}
			boolean ret=pathMap.get(l).add(h);
			if(ret){
				incCnt(l, 1);
				incCnt(h, 1);
				addAllPath(l, h);
			}
			return ret;
		}

		public boolean removePath(Integer s,Integer e) {
			SortedPair<Integer> p=new SortedPair<Integer>(s,e);
			int l=p.first;
			int h=p.second;
			if(!pathMap.containsKey(l))return false;
			boolean ret= pathMap.get(l).remove(h);
			if(ret){
				incCnt(l, -1);
				incCnt(h, -1);
				removeAllPath(l, h);
			}
			return ret;
		}



		private void addAllPath(int s,int e){
			if(!allMap.containsKey(s)){
				allMap.put(s,newSet());
			}
			if(!allMap.containsKey(e)){
				allMap.put(e,newSet());
			}
			allMap.get(s).add(e);
			allMap.get(e).add(s);
		}


		private void removeAllPath(Integer s,Integer e) {
			allMap.get(s).remove(e);
			allMap.get(e).remove(s);
		}

		private void incCnt(int id,int num){
			if(!count.containsKey(id)){
				count.put(id,0);
			}
			int tmp=count.get(id)+num;
			count.put(id, tmp);
		}
	}

	//片方向の情報を持つ
	public Map<Integer,Set<Integer>> pathMap;
	//両方向の情報をもつ
	protected Map<Integer,Set<Integer>> allMap;
	//パスの数をキャッシュする
	Map<Integer, Integer> count;
	public int maxAgentNum=0;

	public NetworkInfo(NetworkInfoBuilder b){
		pathMap=b.pathMap;
		allMap=b.allMap;
		count =b.count;
	}

	public boolean existPath(Integer s,Integer e){
		if(allMap.containsKey(s)){
			return(allMap.get(s).contains(e));
		}
		return false;
	}

	public int getPathCount() {
		return allCount();
	}
	public int getAgentPathCount(int id){
		return getCount(id);
	}

	public List<Integer> getNeighbors(int id){
		ArrayList<Integer> ret=new ArrayList<Integer>(0);
		if(!allMap.containsKey(id))return ret;
		//for (Integer to : allMap.get(id)) {
		//	ret.add(to);
		//}
		ArrayList<Integer> ret2= new ArrayList<Integer>(allMap.get(id));

		//if(!ret.equals(ret2))throw new RuntimeException("getneibors Error!!1");
		return ret2;
	}


	private int getCount(int id) {
		if(!count.containsKey(id))return 0;

		return count.get(id);
	}
	private int allCount(){
		int ret=0;
		for (Entry<Integer, Integer> a : count.entrySet()) {
			ret+=a.getValue();
		}
		return ret/2;
	}

	public  String toString() {
		return allMap.toString();
	}

}


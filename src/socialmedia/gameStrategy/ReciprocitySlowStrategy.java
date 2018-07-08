package socialmedia.gameStrategy;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import socialmedia.gameStrategy.ReciprocityStrategy.cmap;


public class ReciprocitySlowStrategy extends ReciprocityStrategy {

	public static class CoopMap2 implements cmap  {
		Map<Integer, Set<Integer>> coopMap;

		public CoopMap2(int networkSize){
			coopMap=new TreeMap<Integer, Set<Integer>>();
		}


		public void writeCoop(int agent,int target){
			if(!coopMap.containsKey(agent)){
				coopMap.put(agent, new HashSet<Integer>());
			}
			coopMap.get(agent).add(target);
		}
		public void deleteCoop(int agent,int target){
			if(!coopMap.containsKey(agent)){
				return;
			}
			coopMap.get(agent).remove(target);
		}

		public boolean existCoop(int agent,int target){

			return (coopMap.containsKey(agent))? coopMap.get(agent).contains(target):false;

		}

		public int[] getCount() {
			int[] ret=new int[2000];
			for (Entry<Integer, Set<Integer>> tmp :coopMap.entrySet()) {
				ret[tmp.getKey()]=tmp.getValue().size();
			}

			return ret;
		}

	}


	@Override
	public void init(RecipParam p) {
		super.init(p);

		param.coopMap=new CoopMap2(getAgentList().size());
	}


}

package socialmedia.gameStrategy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import network.Agent;

public class ReciprocityLongSpanStrategy extends ReciprocityStrategy {

	protected Map<Integer,LinkedList<Set<Integer>>> coopMapList;
	public int StorageLength=8;

	@Override
	/**
	 *  協調を記入
	 *  記憶長が0のときは何もしない，そうでなくリストに何も入ってないときはエラー
	 */
	protected void writeCoop(int agent,int target){
		if(coopMapList.get(agent).isEmpty()){
			if(StorageLength!=0){System.out.println("エラー");}
			return;
		}
		coopMapList.get(agent).getFirst().add(target);
	}

	@Override
	/*
	 * 何もしなければこのターン裏切ったことになる
	 *
	 * */
	protected void deleteCoop(int agent,int target){

	}

	@Override
	/*
	 * 保持しているセットの中に一回でも強調した記録があれば協調者と判断
	 */
	protected boolean existCoop(int agent,int target){
		LinkedList<Set<Integer>> autoCloseable=coopMapList.get(agent);
		for (Set<Integer> _coopMap :autoCloseable ) {
			if(_coopMap.contains(target)){
				return true;
			}
		}
		return false;
	}
/*
 *  新しい協調リストを追加してstrageLengthの数のリストになるように調整
 */
	protected void newstepCoop(int agent){
		coopMapList.get(agent).addFirst(new HashSet<Integer>());
		while(coopMapList.get(agent).size()>StorageLength)coopMapList.get(agent).removeLast();

	}

	/**
	 *  このステップの協調リストを追加してスタート
	 */
	@Override
	public void cooperat(Agent turnAgent,double S){
		newstepCoop(turnAgent.id);
		super.cooperat(turnAgent, S);
	}

	 /**
	  * Setのリストをエージェント数保持
	  */
	@Override
	public void init(RecipParam p){
		super.init(p);
			coopMapList=new HashMap<Integer, LinkedList<Set<Integer>>>();
		for (int i = 0; i < p.getAgentList().size(); i++) {
			coopMapList.put(i,new LinkedList<Set<Integer>>());
		}
	}

}

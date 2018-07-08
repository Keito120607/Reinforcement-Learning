package socialmedia.gameStrategy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import network.Agent;
import socialmedia.game.GameParam;
import socialmedia.game.GameStrategy;
import socialmedia.gameStrategy.ReciprocityStrategy.RecipParam;

public class ReciprocityStrategy extends GameStrategy<RecipParam> {

	static interface cmap{

		public void writeCoop(int agent,int target);
		public void deleteCoop(int agent,int target);

		public boolean existCoop(int agent,int target);

		public int[] getCount() ;
	}

	public static class CoopMap implements cmap {
		protected boolean[][] coopMap2;
		int count[];
		Map<Integer,Set<Integer>> coopmaC;

		public CoopMap(int networkSize){
			coopMap2=new boolean[networkSize][networkSize];
			count=new int[networkSize];
		}

		public void writeCoop(int agent,int target){
			if(coopMap2[agent][target])return;

			coopMap2[agent][target]=true;
			count[agent]++;

		}
		public void deleteCoop(int agent,int target){
			if(!coopMap2[agent][target])return;

			coopMap2[agent][target]=false;
			count[agent]--;
		}

		public boolean existCoop(int agent,int target){
			return coopMap2[agent][target];

		}

		public int[] getCount() {
			return count;
		}

	}



	public static class RecipParam extends GameParam{

		protected int F;//協調のコスト
		protected int M;//協調による被害
		protected double R;//褒賞された時の利得
		protected int C;//褒賞のコスト
		private double S0=0;
		private boolean useS0=false;
		public Map<Integer, Map<Integer, Set<Integer>>> commentedLog;
		public boolean use_commentedLog=false;

		protected cmap coopMap;




		public RecipParam() {
			F=-3;
			M=1;
			R=9;
			C=-2;

			fileName="ReciprocityRewardGame-"+System.currentTimeMillis();
		}

		public void setRC(double _R, int _C) {
			R=_R;
			C=_C;
		}

		public void setS0(double s0) {
			S0 = s0;
			useS0=true;
		}

		public double getAverageFriend() {
			double friend=0;
			int i=0;
			for (int num : coopMap.getCount()) {
				i++;
				friend+=num;
			}
			if(i!=0){
			friend/=(double)i;
			}
			return (friend);
		}

		public double  getSumofFriendLinkNum() {
			int friend=0;
			for (int num : coopMap.getCount()) {
				friend+=num;
			}
			return (friend);
		}
		public double getSumofRecipFriendLinkNum() {
			int friend=0;
			int netsize=coopMap.getCount().length;
			for (int i = 0; i <netsize ; i++) {
				for (int j = i; j < netsize; j++) {
					if(coopMap.existCoop(i, j)&&coopMap.existCoop(j,i))friend+=2;

				}

			}
			return (friend);
		}

		public Map<Integer, Set<Integer>> getCoopMap() {
			// TODO 自動生成されたメソッド・スタブ
			return null;
		}


	}

	int round=0;

	@Override
	public void initGeneration(RecipParam p) {
		super.initGeneration(p);
		param.commentedLog=new HashMap<Integer, Map<Integer, Set<Integer>>>();
		for (int i = 0; i <4; i++) {
			Map<Integer, Set<Integer>> tmp= new HashMap<Integer, Set<Integer>>();
			param.commentedLog.put(i,tmp);
		}
	}


	@Override
	public void init(RecipParam p) {
		super.init(p);

		param.coopMap=new CoopMap(getAgentList().size());
	}

	void addDebugCommentLog(int round,int turnPlayerId, int commentedPlayerId){
		if(!param.use_commentedLog){return;}
		if(!param.commentedLog.get(round).containsKey(turnPlayerId)){
			param.commentedLog.get(round).put(turnPlayerId, new HashSet<Integer>());
		}
		param.commentedLog.get(round).get(turnPlayerId).add(commentedPlayerId);
	}

	@Override
	protected void runGame(int _round) {
		round=_round;
		for (Agent turnAgent : getAgentList()) {
			//Sよりも大胆だと裏切る
			double S=param.random.nextDouble();
			if(turnAgent.getBoldness()>S){
				defect(turnAgent, S);
			}else{
				cooperat(turnAgent, S);

			}
		}
	}

	public void submissionScore(Agent turnAgent) {
		//強調コスト
		turnAgent.addScore(param.F);
//		if(turnAgent.id==18) System.out.println(turnAgent.score+" Post! ");
		turnAgent.kizi++;

	}

	public void subscriptionScore(Agent otherAgent) {
		//利得を受ける
		otherAgent.addScore(param.M);
//		if(otherAgent.id==18) System.out.println(otherAgent.score+" Read! ");
		otherAgent.add_read_kizi();
	}
	@Deprecated
	public boolean  beNoticedArticle(double S) {
		try {
			throw(new Exception("beNoticeArticleが呼ばれた"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return  false;//beNoticedArticle(S);
	}
	public boolean  beNoticedArticle(double S,int aid) {
		//return  true;
		///*
		if(param.useS0){
			return param.S0>param.random.nextDouble();
		}else{
			return S>param.random.nextDouble();
		}
		//return 0>random.nextDouble();

		 //*/
	}

	public void coopCommentScore(Agent turnAgent,Agent otherAgent) {
		turnAgent.addScore(param.R);
		otherAgent.addScore(param.C);
		otherAgent.addcomment(turnAgent.id);
		writeCoop(turnAgent.id, otherAgent.id);
		addDebugCommentLog(round,turnAgent.id,otherAgent.id);
	}

	public void coopNotCommentScore(Agent turnAgent,Agent otherAgent) {
		deleteCoop(turnAgent.id, otherAgent.id);

	}

	public void notCoopCommentScore(Agent turnAgent,Agent otherAgent) {
		turnAgent.addScore(param.R);
		otherAgent.addScore(param.C);
		otherAgent.addcomment(turnAgent.id);
		writeCoop(turnAgent.id, otherAgent.id);
		addDebugCommentLog(round,turnAgent.id,otherAgent.id);
	}
	public void notCoopNotCommentScore(Agent turnAgent,Agent otherAgent) {
		deleteCoop(turnAgent.id, otherAgent.id);

	}

	public boolean doesCoopComment(Agent otherAgent){
		return otherAgent.getVengefulness()>param.random.nextDouble();

	}

	public boolean doesNotCoopComment(Agent otherAgent){
		return otherAgent.getRewardness()>param.random.nextDouble();
	}



	public void cooperat(Agent turnAgent,double S){
		submissionScore(turnAgent);
		//強調されて周りは利得を得る
		for (Agent otherAgent : param.getNeighbor(turnAgent)) {
			if(turnAgent==otherAgent)continue;
			subscriptionScore(otherAgent);


			//気づいたら
			if(beNoticedArticle(S,otherAgent.id)){	//S´

				//otherからみてturnはいいやつか
				if(existCoop(otherAgent.id,turnAgent.id)){
					//褒賞する気になるか
					if(doesCoopComment(otherAgent)){
						coopCommentScore(turnAgent,otherAgent);
					}else{
						coopNotCommentScore(turnAgent,otherAgent);
						otherAgent.addfreeride_kizi();
					}

				}else{
					//褒賞する気になるか
					if(doesNotCoopComment(otherAgent)){
						notCoopCommentScore(turnAgent,otherAgent);
					}else{
						notCoopNotCommentScore(turnAgent,otherAgent);
						otherAgent.addfreeride_kizi();
					}
				}

			}else throughCoop(turnAgent.id, otherAgent.id);


		}


	}
	public void defect(Agent turnAgent,double S){

	}


	protected void writeCoop(int agent,int target){


		param.coopMap.writeCoop(agent, target);

	}
	protected void deleteCoop(int agent,int target){

		param.coopMap.deleteCoop(agent, target);

	}

	protected boolean existCoop(int agent,int target){

		return param.coopMap.existCoop(agent, target);

	}

	protected void throughCoop(int agent,int target){
		deleteCoop(agent, target);
	}



}

package socialmedia.gameStrategy;

import network.Agent;
import socialmedia.game.GameParam;
import socialmedia.game.GameStrategy;
import socialmedia.gameStrategy.GeneralMetaNormStratgy.GeneralMetaNormParam;







	public  class GeneralMetaNormStratgy extends GameStrategy<GeneralMetaNormParam> {

		public static class GeneralMetaNormParam extends GameParam{
			protected int T;	//裏切りのコスト
			protected int H;//裏切りよる被害
			protected int P;//懲罰された時の損失
			protected int E;//懲罰のコスト

			protected int F;//協調のコスト
			protected int M;//協調による被害
			protected int R;//褒賞された時の利得
			protected int C;//褒賞のコスト

			protected int Pd;//メタ懲罰された時の損失
			protected int Ed;//メタ懲罰のコスト
			protected int Rd;//メタ協調された時の利得
			protected int Cd;//メタ協調のコスト

			protected int Pc;//メタ懲罰された時の損失
			protected int Ec;//メタ懲罰のコスト
			protected int Rc;//メタ協調された時の利得
			protected int Cc;//メタ協調のコスト
		}


		public void defect(Agent turnAgent,double S){
			//利得を得る
			turnAgent.addScore(param.T);
			//裏切られた時の他の人の処理
			for (Agent otherAgent : param.getNeighbor(turnAgent)) {
				if(turnAgent==otherAgent)continue;
				//被害を受ける
				otherAgent.addScore(param.H);
				//気づいたら
				if(S>param.random.nextDouble()){
					//懲罰する気になるか
					if(otherAgent.getVengefulness()>param.random.nextDouble()){
						turnAgent.addScore(param.P);
						otherAgent.addScore(param.E);
						//懲罰したときに報酬するかどうか
						for (Agent anotherAgent : param.getNeighbor(otherAgent)) {
							if(turnAgent==anotherAgent||otherAgent==anotherAgent)continue;
							if(S>param.random.nextDouble()){
								//報酬する気になるか
								if(anotherAgent.getRewardness()>param.random.nextDouble()){
									otherAgent.addScore(param.Rd);
									anotherAgent.addScore(param.Cd);
								}
							}
						}
					}else{
						//懲罰しなかったら
						for (Agent anotherAgent : param.getNeighbor(otherAgent)) {
							if(turnAgent==anotherAgent||otherAgent==anotherAgent)continue;
							if(S>param.random.nextDouble()){
								//懲罰する気になるか
								if(anotherAgent.getVengefulness()>param.random.nextDouble()){
									otherAgent.addScore(param.Pd);
									anotherAgent.addScore(param.Ed);
								}
							}
						}
					}
				}


			}
		}
		public void cooperat(Agent turnAgent,double S){
			//強調コスト
			turnAgent.addScore(param.F);
			//強調されて周りは利得を得る
			for (Agent otherAgent : param.getNeighbor(turnAgent)) {
				if(turnAgent==otherAgent)continue;
				//利得を受ける
				otherAgent.addScore(param.M);
				//気づいたら
				if(S>param.random.nextDouble()){
					//褒賞する気になるか
					if(otherAgent.getRewardness()>param.random.nextDouble()){
						turnAgent.addScore(param.R);
						otherAgent.addScore(param.C);
						//報酬したときに報酬するかどうか
						for (Agent anotherAgent : param.getNeighbor(otherAgent)) {
							if(turnAgent==anotherAgent||otherAgent==anotherAgent)continue;
							if(S>param.random.nextDouble()){
								//報酬する気になるか
								if(anotherAgent.getRewardness()>param.random.nextDouble()){
									otherAgent.addScore(param.Rc);
									anotherAgent.addScore(param.Cc);
								}
							}
						}
					}else{
						//報酬しなかったら
						for (Agent anotherAgent : param.getNeighbor(otherAgent)) {
							if(turnAgent==anotherAgent||otherAgent==anotherAgent)continue;
							if(S>param.random.nextDouble()){
								//懲罰する気になるか
								if(anotherAgent.getVengefulness()>param.random.nextDouble()){
									otherAgent.addScore(param.Pc);
									anotherAgent.addScore(param.Ec);
								}
							}
						}
					}
				}


			}
		}
		@Override
		protected void runGame(int round) {
			for (Agent turnAgent : param.getAgentList()) {
				//Sよりも大胆だと裏切る
				double S=param.random.nextDouble();
				if(turnAgent.getBoldness()>S){
					defect(turnAgent, S);
				}else{
					cooperat(turnAgent, S);
				}
			}

		}

	}


package socialmedia.gameEnv.executors;

import java.util.Arrays;

import network.ERmodel;
import network.Network;
import socialmedia.evolutions.EvolutionToriumi;
import socialmedia.game.GameExecutor;
import socialmedia.game.GameExecutor.ExecParamBuilder;
import socialmedia.gameStrategy.LogNoReciprocityMetaRewardStrategy;
import utility.ResultLogger;

public class GameInRandom {

	public void start() {

		// ResultLogger a = new
		// ResultLogger("ERgame-networks-toriumiEvo-100蝗槫ｹｳ蝮㎏eed荳榊ｮ�");

		/*
		 * title = "ER", "log" or "linear" Number of agents, degree generation,
		 * trial
		 *
		 */
		String title = "ER+" + "Metareward" + "log+" + "1000+" + "50+" + "2000+" + "1";
		System.out.println(title);

		ResultLogger a = new ResultLogger(title);

		// for (int i = 100; i <= 1000; i += 100) {
		int i = 1000;
		{
			// for (double k = 20.0; k <= 100.0; k += 10.0) {
			double k = 50.0;
			{
				double edge = i * k / 2.0;
				double edgecnt = i * (i - 1) / 2.0;
				double pp = edge / edgecnt;
				// double pp = 0.1; //谺｡謨ｰ蟷ｳ蝮� k = pp * (N-1)
				ResultLogger d = a.subDir("er");

				// ResultLogger d = a.subDir("er-" + i + "-" + k + "-log");
				{

					// ResultLogger d = a.subDir("er-" + i + "-" + k + "-log");
					Network ins = new ERmodel(i, pp);
					GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
					epb.setNetworkInfo(ins).setStrategy(LogNoReciprocityMetaRewardStrategy.class).setGenerationTime(100)
							.setLogger(d).setStartSeed(1005).setBList(Arrays.asList(1.0)).setCList(Arrays.asList(2))
							.setLDList(Arrays.asList(1.0)).setRList(Arrays.asList(9.0)).setDynnet(false)
							.setEvolution(EvolutionToriumi.class).setAnydata("i" + i + "k" + k) // "i"+i+"k"+k
							.setHeader(Arrays.asList("B", "LC", "LD", "Bhead", "LDhead", "Bmid", "LDmid", "Btail",
									"LDtail", "all", "active", "mutual", "oneside", "maxscore", "minscore",
									"soceraverage"))
							.setAddtion((p, gen) -> {
								// if(gen<100)np.printAgentForCSV(p.agents,gen);
								double onlynoRecip = p.getSumofFriendLinkNum() - p.getSumofRecipFriendLinkNum();
								return Arrays.asList(p.getAgentList().get(0).getPosting(),
										p.getAgentList().get(0).getRewardness(),
										p.getAgentList().get(p.getAgentList().size() / 2).getPosting(),
										p.getAgentList().get(p.getAgentList().size() / 2).getRewardness(),
										p.getAgentList().get(p.getAgentList().size() - 1).getPosting(),
										p.getAgentList().get(p.getAgentList().size() - 1).getRewardness(),
										(double) ins.getPathCount() * 2, p.getSumofFriendLinkNum(),
										p.getSumofRecipFriendLinkNum(), onlynoRecip);
							}).setTrialTime(1); // 隧ｦ陦悟屓謨ｰ
					GameExecutor.ExecParam ep = epb.build();
					GameExecutor.start(ep);
				}
			}
		}

	}

}

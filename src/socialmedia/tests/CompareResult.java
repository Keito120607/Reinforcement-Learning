//package socialmedia.tests;
//
//import static org.junit.Assert.*;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import network.AgentCompleteGraphList;
//import network.NetworkInfo;
//import network.NetworkInfoFactory;
//import network.NetworkPrinter;
//
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import socialmedia.evolutions.EvolutionToriumi;
//import socialmedia.game.GameExecutor;
//import socialmedia.game.GameExecutor.ExecParamBuilder;
//import socialmedia.game.GameOrigin;
//import socialmedia.gameStrategy.LimitedReciprocityStrategy;
//import socialmedia.gameStrategy.NewRecipMetaStrategy;
//import socialmedia.gameStrategy.NoReciprocityMetaRewardStrategy;
//import socialmedia.gameStrategy.NoReciprocitySNSnormStrategy;
//import socialmedia.gameStrategy.ReciprocityErrorStrategy;
//import socialmedia.gameStrategy.ReciprocityForgivingLongSpan;
//import socialmedia.gameStrategy.ReciprocityImageScore;
//import socialmedia.gameStrategy.ReciprocityLongSpanStrategy;
//import socialmedia.gameStrategy.ReciprocityMetaRewardStrategy;
//import socialmedia.gameStrategy.ReciprocityMustStrategy;
//import socialmedia.gameStrategy.ReciprocityNotExistsStrategy;
//import socialmedia.gameStrategy.ReciprocitySNSnormStrategy;
//import socialmedia.gameStrategy.ReciprocitySlowStrategy;
//import socialmedia.gameStrategy.ReciprocityStandingStrategy;
//import socialmedia.gameStrategy.ReciprocityStrategy;
//import socialmedia.gameStrategy.ReciprocityStrategy.RecipParam;
//import utility.EachOutputer;
//import utility.MyRandom;
//import utility.ResultComparator;
//import utility.ResultLogger;
//import utility.TestReader;
//
//public class CompareResult {
//
//	static ResultLogger a;
//
//	@BeforeClass
//	static public void init() {
//		a = new ResultLogger("test-WSgameFocusOne",true);
//	}
//
//	@Test
//	public void test1() {
//		TestReader first = new TestReader(
//				"test-WSgameFocusOne/ws-1000-20-0.01");
//		ResultLogger b = a.subDir("ws-1000-20-0.01");
//		{
//			NetworkInfo ins = NetworkInfoFactory.initWS(1000, 20, 0.01,
//					new MyRandom(999));
//			GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(ReciprocityStrategy.class)
//					.setGenerationTime(50)
//					.setLogger(b)
//					.setStartSeed(1005)
//					.setCList(Arrays.asList(2))
//					.setLDList(Arrays.asList(1.0))
//					.setRList(Arrays.asList(9.0))
//					.setAddtion(
//							(p, gen) -> {
//								return Arrays.asList(p.getAgentList().get(0)
//										.getPosting(), p.getAgentList().get(0)
//										.getVengefulness(), p.getAgentList()
//										.get(0).getRewardness());
//							}).setTrialTime(1);
//			GameExecutor.ExecParam ep = epb.build();
//			GameExecutor.start(ep);
//		}
//		assertTrue("結果が違います", ResultComparator.compare(first, b));
//	}
//
//	@Test
//	public void testofImportnetwork(){
//		ResultLogger r= a.subDir("test-import-network");
//		NetworkInfo i= NetworkInfoFactory.importFile("statics/facebook_combined.txt",true);
//		NetworkPrinter.printNetworkForCyto2(i, r.getWriter("facebook_combined.txt"));
//		boolean hoge=ResultComparator.compare(r, new TestReader("statics",""));
//		assertTrue("結果が違います", hoge);
//	}
//
//	@Test
//	public void test2() {
//		TestReader first2 = new TestReader(
//				"test-WSgameFocusOne/ba-1000-10-10");
//		ResultLogger c = a.subDir("ba-1000-10-10");
//
//		{
//			NetworkInfo ins = NetworkInfoFactory.initBA(1000, 20, 10,
//					new MyRandom(999));
//			GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(ReciprocityNotExistsStrategy.class)
//					.setGenerationTime(50)
//					.setLogger(c)
//					.setStartSeed(1005)
//					.setCList(Arrays.asList(2))
//					.setLDList(Arrays.asList(1.0))
//					.setRList(Arrays.asList(9.0))
//					.setAddtion(
//							(p, gen) -> {
//								return Arrays.asList(p.getAgentList().get(0)
//										.getPosting(), p.getAgentList().get(0)
//										.getVengefulness(), p.getAgentList()
//										.get(0).getRewardness());
//							}).setTrialTime(1);
//			GameExecutor.ExecParam ep = epb.build();
//			GameExecutor.start(ep);
//		}
//
//		assertTrue("結果が違います", ResultComparator.compare(first2, c));
//
//	}
//
//	@Test
//	public void run() {
//
//		TestReader first3 = new TestReader("test-WSgameFocusOne/ws-1000-20-0.01-forCyto");
//
//		ResultLogger d = a.subDir("ws-1000-20-0.01-forCyto");
//
//		{
//
//			NetworkPrinter np = new NetworkPrinter();
//			np.init(d.getWriter("ws-1000-20-0.01.csv"));
//
//			NetworkInfo ins = NetworkInfoFactory.initWS(1000, 20, 0.01,new MyRandom(999));
//			GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(ReciprocityStrategy.class)
//					.setGenerationTime(50)
//					.setLogger(d)
//					.setStartSeed(1005)
//					.setCList(Arrays.asList(2))
//					.setLDList(Arrays.asList(1.0))
//					.setRList(Arrays.asList(9.0))
//					.setAddtion(
//							(p, gen) -> {
//								if (gen < 100)
//									np.printAgentForCSV(p.agents, gen);
//								return Arrays.asList(p.getAgentList().get(0)
//										.getPosting(), p.getAgentList().get(0)
//										.getVengefulness(), p.getAgentList()
//										.get(0).getRewardness());
//							}).setTrialTime(1);
//			GameExecutor.ExecParam ep = epb.build();
//			GameExecutor.start(ep);
//
//			np.printNetworkForCSV(ins);
//			np.close();
//		}
//
//		assertTrue("結果が違います", ResultComparator.compare(first3, d));
//
//	}
//
//	@Test
//	public void name() {
//		boolean ret = true;
//		assertTrue(ret);
//	}
//
//	@Test
//	public void name2() {
//		boolean ret = true;
//		ret &= false;
//		assertTrue(!ret);
//	}
//
//	@Test
//	public void compareNetwork(){
//		TestReader first3 = new TestReader("test-WSgameFocusOne/networks");
//
//		ResultLogger rl=a.subDir("networks");
//		NetworkInfo ab=NetworkInfoFactory.initBA(1000, 10, 10, new MyRandom(999));
//		{
//			NetworkPrinter np=new NetworkPrinter();
//			np.init(rl.getWriter("BA.csv"));
//			np.printNetworkForCSV(ab);
//		}
//		NetworkInfo b=NetworkInfoFactory.initWS(5000, 10, 0.01, new MyRandom(999));
//		{
//			NetworkPrinter np=new NetworkPrinter();
//			np.init(rl.getWriter("WS.csv"));
//			np.printNetworkForCSV(b);
//		}
//		NetworkInfo c=NetworkInfoFactory.initCNN(0.9, 1000, new MyRandom(999));
//		{
//			NetworkPrinter np=new NetworkPrinter();
//			np.init(rl.getWriter("CNN.csv"));
//			np.printNetworkForCSV(c);
//		}
//		assertTrue("結果が違います", ResultComparator.compare(first3, rl));
//	}
//
//
//	@Test
//	public void slowandCount(){
//		ResultLogger d = a.subDir("normal-coop");
//
//		{
//			NetworkInfo ins = NetworkInfoFactory.initWS(600, 33, 0.01,new MyRandom(999));
//			GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(ReciprocityStrategy.class)
//					.setGenerationTime(50)
//					.setLogger(d)
//					.setStartSeed(1005)
//					.setCList(Arrays.asList(2))
//					.setLDList(Arrays.asList(1.0))
//					.setRList(Arrays.asList(9.0))
//					.setAddtion(
//							(p, gen) -> {
//								return  Arrays.asList(p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum());
//							}).setTrialTime(1);
//			GameExecutor.ExecParam ep = epb.build();
//			GameExecutor.start(ep);
//		}
//
//
//
//
//		ResultLogger df = a.subDir("slow-coop");
//
//		{
//			NetworkInfo ins = NetworkInfoFactory.initWS(600, 33, 0.01,new MyRandom(999));
//			GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(ReciprocitySlowStrategy.class)
//					.setGenerationTime(50)
//					.setLogger(df)
//					.setStartSeed(1005)
//					.setCList(Arrays.asList(2))
//					.setLDList(Arrays.asList(1.0))
//					.setRList(Arrays.asList(9.0))
//					.setAddtion(
//							(p, gen) -> {
//								return  Arrays.asList(p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum());
//							}).setTrialTime(1);
//			GameExecutor.ExecParam ep = epb.build();
//			GameExecutor.start(ep);
//		}
//
//		String first=d.getFolderPath()+"/"+(new File(d.getFolderPath()).list()[0]);
//		String second=df.getFolderPath()+"/"+ (new File(df.getFolderPath()).list()[0]);
//
//		System.out.println(first+" "+second);
//
//
//		assertTrue("結果が違います", ResultComparator.fileCompare(first, second));
//
//
//
//
//
//
//
//
//	}
//
//	@Test
//	public void testStrategies(){
//		ResultLogger d = a.subDir("strategy-tests");
//		TestReader first3 = new TestReader("test-WSgameFocusOne/strategy-tests");
//
//
//			NetworkInfo ins = NetworkInfoFactory.initWS(600, 33, 0.01,new MyRandom(999));
//			GameExecutor.ExecParamBuilder epb = new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//					.setStrategy(ReciprocityStrategy.class)
//					.setGenerationTime(50)
//					.setLogger(d)
//					.setStartSeed(1005)
//					.setCList(Arrays.asList(2))
//					.setLDList(Arrays.asList(1.0))
//					.setRList(Arrays.asList(9.0))
//					.setAddtion(
//							(p, gen) -> {
//								return  Arrays.asList(p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum());
//							}).setTrialTime(1);
//			epb.build();
//			GameExecutor.start(epb.setStrategy(ReciprocityStrategy.class).build());
//
//			GameExecutor.start(epb.setStrategy(ReciprocityStandingStrategy.class).build());
//
//			GameExecutor.start(epb.setStrategy(ReciprocitySNSnormStrategy.class).build());
//
//			GameExecutor.start(epb.setStrategy(ReciprocitySlowStrategy.class).build());
//
//			GameExecutor.start(epb.setStrategy(ReciprocityNotExistsStrategy.class).build());
//
//			GameExecutor.start(epb.setStrategy(ReciprocityMustStrategy.class).build());
//
//			GameExecutor.start(epb.setStrategy(ReciprocityMetaRewardStrategy.class).build());
//
//			GameExecutor.start(epb.setStrategy(ReciprocityLongSpanStrategy.class).build());
//
//			GameExecutor.start(epb.setStrategy(ReciprocityImageScore.class).build());
//
//			GameExecutor.start(epb.setStrategy(ReciprocityForgivingLongSpan.class).build());
//			GameExecutor.start(epb.setStrategy(ReciprocityErrorStrategy.class).build());
//			GameExecutor.start(epb.setStrategy(NoReciprocitySNSnormStrategy.class).build());
//			GameExecutor.start(epb.setStrategy(NoReciprocityMetaRewardStrategy.class).build());
//			GameExecutor.start(epb.setStrategy(NewRecipMetaStrategy.class).build());
//
//			GameExecutor.start(epb.setStrategy(LimitedReciprocityStrategy.class).build());
//
//			assertTrue("結果が違います", ResultComparator.compare(first3, d));
//	}
//
//	@Test
//	public void compareComp20Net(){
//		ResultLogger an=a.subDir("comp20byAN");
//		ResultLogger ni=a.subDir("comp20byNI");
//		{
//			ResultLogger d=ni;
//
//			RecipParam param=new RecipParam();
//			param.init();
//			param.fileName="ReciprocityGame-";
//			MyRandom m2=new MyRandom(1005);
//
//			param.GenerationTime=100;
//			param.agents=new AgentCompleteGraphList(20,m2);
//			param.random=m2;
//
//			GameOrigin<RecipParam> game=new GameOrigin<RecipParam>();
//			game.SetParam(param);
//			game.setStrategy(new ReciprocityStrategy());
//			game.setEvolutionStrategy(new EvolutionToriumi());
//			game.setOutputer(new EachOutputer(d));
//			game.run();
//
//		}
//
//		{
//			ResultLogger d=an;
//
//			RecipParam param=new RecipParam();
//			param.init();
//			param.fileName="ReciprocityGame-";
//			MyRandom m2=new MyRandom(1005);
//
//			param.GenerationTime=100;
//			param.agents=new AgentCompleteGraphList(20,m2);
//			param.random=m2;
//
//			GameOrigin<RecipParam> game=new GameOrigin<RecipParam>();
//			game.SetParam(param);
//			game.setStrategy(new ReciprocityStrategy());
//			game.setEvolutionStrategy(new EvolutionToriumi());
//			game.setOutputer(new EachOutputer(d));
//			game.run();
//
//		}
//		assertTrue("結果が違います", ResultComparator.compare(an, ni));
//
//	}
//
//	@Test
//	public void testOfMultiThread(){
//		ArrayList<ResultLogger> list=new ArrayList<ResultLogger>();
//		for(int i =0;i<5;i++)
//		{
//			ResultLogger d=a.subDir("comp20_time_"+i);
//
//
//
//			NetworkInfo ins= NetworkInfoFactory.initComplete(20);
//			GameExecutor.ExecParamBuilder epb=new ExecParamBuilder();
//			epb.setNetworkInfo(ins)
//			.setStrategy(ReciprocityStrategy.class)
//			.setGenerationTime(50)
//			.setLogger(d)
//			.setStartSeed(1005)
//			.setCList(Arrays.asList(2))
//			.setLDList(Arrays.asList(1.0))
//			.setRList(Arrays.asList(9.0, 5.0))
//			.setHeader(Arrays.asList("B","LC","LD","B1","LC1","LD1","all","active","mutual","oneside"))
//			.setAddtion((p,gen)->{
//				double onlynoRecip=p.getSumofFriendLinkNum()-p.getSumofRecipFriendLinkNum();
//				return  Arrays.asList(p.getAgentList().get(0).getPosting(),p.getAgentList().get(0).getVengefulness(),p.getAgentList().get(0).getRewardness(),(double)ins.getPathCount()*2,  p.getSumofFriendLinkNum(), p.getSumofRecipFriendLinkNum(),onlynoRecip);
//				})
//			.setTrialTime(5);
//			GameExecutor.ExecParam ep= epb.build();
//			GameExecutor.start(ep);
//
//			list.add(d);
//		}
//
//		for (ResultLogger rl1 : list) {
//			for (ResultLogger rl2 : list) {
//					if(rl1==rl2)continue;
//						assertTrue("結果が変わってしまっている",ResultComparator.compare(rl1,rl2));
//			}
//		}
//
//	}
//
//
//
//
//	@AfterClass
//	public static void deletefiles(){
//		a.remove();
//	}
//
//
//}

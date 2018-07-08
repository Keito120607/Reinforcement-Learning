package network;

import utility.MyRandom;

        import network.NetworkInfo.NetworkInfoBuilder;
        import network.NetworkInfoFactory.PathNode;
        import utility.MyRandom;

        import java.util.HashSet;
        import java.util.Set;

public class ERmodel implements Network {
    int num;
    double p;




    public ERmodel(int num, double p){
        this.num=num;
        this.p = p;

    }

    @Override
    public NetworkInfo BuildNetwork(MyRandom random) {


        NetworkInfo.NetworkInfoBuilder builder=new NetworkInfo.NetworkInfoBuilder();
        int begin=0;
        for (int i = 0; i < num; i++) {
            for (int j = begin; j <num; j++) {
                if(i==j)continue;
                double d = random.nextDouble();
//                System.out.println(d);
                if(d<=p) {
//                    System.out.println("edge");
                    builder.addPath(i, j);
                }
            }
            begin++;
        }
        builder.networkInfo.maxAgentNum=num;
        return builder.networkInfo;

    }



    @Override
    public String NetworkName(int seed) {
        // TODO 自動生成されたメソッド・スタブ

        return "ermodel-"+num+"-"+p+"-"+seed;
    }


    //2016/12/08 未実装　
    @Override
    public double getPathCount() {
        // TODO 自動生成されたメソッド・スタブ
        return 0;
    }

}


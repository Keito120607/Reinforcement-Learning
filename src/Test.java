import javax.lang.model.type.ArrayType;
import java.util.ArrayList;

/**
 * Created by y.miura on 2017/01/07.
 */
public class Test {


    public static void main(String[] args) {
        ArrayList<TestObject> ArrayList = new ArrayList<TestObject>();

        TestObject a = new TestObject(0,100);
        TestObject b = new TestObject(1,200);
        TestObject c = new TestObject(2,300);
        TestObject d = new TestObject(3,400);
        TestObject e = new TestObject(4,500);

        ArrayList.add(a);
        ArrayList.add(b);
        ArrayList.add(c);
        ArrayList.add(d);
        ArrayList.add(e);

        ArrayList<TestObject> TestList = new ArrayList<TestObject>();
        TestList = ArrayList;
//        TestList.remove(2);



        System.out.println(TestList.get(2).id);
        System.out.println(ArrayList.get(2).id);






    }

    /**
     * Created by y.miura on 2017/01/07.
     */
    public static class TestObject {


        public int id;
        public int score;


        public TestObject(int id, int score){
            this.id=id;
            this.score=score;

        }



    }
}

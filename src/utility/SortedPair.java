package utility;

public class SortedPair<T extends Comparable<T>>{
    public T first;
    public T second;
    public SortedPair(T a, T b){
            first=a;
            second=b;
            this.sort();
    }
    public void sort(){
            if(first.compareTo(second)>0){
                    T hoge=second;
                    second=first;
                    first=hoge;
            }
    }
}
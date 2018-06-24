package utility;



///* Bitクラス */
public class BitArray implements Cloneable{
  ///* Private */


  private boolean[] data;

  //ビット数
  private final int BITLEN = Character.SIZE; //charのサイズ(bit)

  ///* Public */
  private int length; //合計bit数

  private double max_value;

  private double cache=-1;


  static int[] pow={1,2,4,8,16};
  static double[] calcvalue={1/7.0,2/7.0,4/7.0};

  ///* コンストラクタ */
  public BitArray(int _length) {
	  if(_length!=3)throw new RuntimeException("bitarray ha 3 nomi");
      //配列初期化 ビット数分配列を確保（BITLENの倍数だと１byte多い）
      data=new boolean[_length];
      cache=-1;
      length = _length;
      max_value=(Math.pow(2, length)-1);
  }
  /*
  @Override
  public String toString(){
	  String ret="";
	  for (int i = 0; i <length; i++) {
		if(Value(i))ret+="1";
		else ret+="0";

	  }
	  return ret;
  }*/

  ///* セット */
  public void Set(int index, boolean value) {
      data[index]=value;
      cache=-1;
  }

  ///* 取得 */
  public boolean Value(int index) {
	  //boolean tmp=(bitNote[index / BITLEN] >> index % BITLEN & 1) == 1;
	 // if(data[index]!=tmp)throw new RuntimeException("value くいちがい");

	  return data[index];
      //return (bitNote[index / BITLEN] >> index % BITLEN & 1) == 1;
  }

  ///* クリア */
  public void Clear() {
      data=new boolean[length];
      cache=-1;
  }

  public void SetRamdom(MyRandom random){
	  for(int i=0;i<length;i++){
		  Set(i, random.nextBoolean());
	  }
  }
  public double getDouble(){


	  if(cache>=0){
		  //if(cache!=tmp2)throw new RuntimeException("error get doubel");
		  return cache;
	  }

	  double tmp2=0;
	  for (int i = 0; i <length; i++) {
		 tmp2+=Value(i)?calcvalue[i]:0;
	  }



	  cache=tmp2;
	  return tmp2;

  }
  public BitArray clone(){
	  BitArray r;
		try {
			r = (BitArray)super.clone();
		} catch (CloneNotSupportedException ce)
		{ throw new RuntimeException();/* 適切なエラー処理 */}
		r.data=this.data.clone();

		return r;
	}
}

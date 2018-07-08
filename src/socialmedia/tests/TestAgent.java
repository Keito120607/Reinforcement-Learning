package socialmedia.tests;

import static org.junit.Assert.*;
import network.Agent;

import org.junit.Test;

import utility.BitArray;
import utility.MyRandom;

public class TestAgent {

	@Test
	public void isEqualsCloneAndNew() {
		String s1,s2;
		{
			MyRandom m2=new MyRandom(1005);
			Agent father =new Agent(10,m2);
			Agent mother =new Agent(5,m2);
			System.out.println(father.toString()+mother.toString());
			Agent origin=new Agent(3,m2);

			//new
			Agent n=new Agent(3,m2);
			n.evolution(father, mother);
			n.LDrate=origin.LDrate;

			s1=n.toString();
		}
		{
			MyRandom m2=new MyRandom(1005);
			Agent father =new Agent(10,m2);
			Agent mother =new Agent(5,m2);
System.out.println(father.toString()+mother.toString());
			Agent origin=new Agent(3,m2);

			//clone
			Agent n =origin.clone();
			//ランダムのシード値を動かすだけの意味ないコード
			(new BitArray(3)).SetRamdom(m2);
			(new BitArray(3)).SetRamdom(m2);
			(new BitArray(3)).SetRamdom(m2);

			n.evolution(father,mother);


			s2=n.toString();
		}



		assertEquals("同じ値になっていることを保障",s1,s2);
	}

	@Test
	public void isFixedAgent(){
		MyRandom m2=new MyRandom(1005);
		Agent father =new Agent(10,m2);
		Agent mother =new Agent(5,m2);
		Agent origin=new Agent(3,m2);
		origin.fixmode=true;
		Agent n =origin.clone();
		n.evolution(father,mother);
		assertEquals("固定されているか？",n.toString(),origin.toString());
		n.evolution(father,mother);
		assertEquals("固定されているか？",n.toString(),origin.toString());
		n.fixmode=false;
		n.evolution(father,mother);
		assertNotEquals("へんかするか？",n.toString(),origin.toString());
		Agent n2=n.clone();
		n.fixmode=true;
		n.evolution(father,mother);
		assertEquals("変化しないはず",n2.toString(),n.toString());

	}

}

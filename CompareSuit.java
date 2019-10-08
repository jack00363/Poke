package tw.com.lccnet.chap7.poke.third;

import java.util.Comparator;

public class CompareSuit implements Comparator<PokeBuilder>{

	@Override
	public int compare(PokeBuilder o1, PokeBuilder o2) {
		
		return o2.getId()/13-o1.getId()/13;
	}

}

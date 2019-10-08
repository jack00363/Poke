package tw.com.lccnet.chap7.poke.third;

import java.util.Comparator;

public class ComparePoke implements Comparator<PokeBuilder>{

	@Override
	public int compare(PokeBuilder o1, PokeBuilder o2) {
		//如果點數相同比花色
		if(o1.getPoint()==o2.getPoint()) {
			return o2.getId()-o1.getId();	
		}
		//比點數大小
		return o2.getPoint()-o1.getPoint();
	}
}

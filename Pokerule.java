package tw.com.lccnet.chap7.poke.third;

import java.util.Arrays;
import org.junit.Test;

public class Pokerule {
	@Test
	public void getTest() {
		showHand(13);
	}
	
	public void showHand(int number) {
		PokeBuilder[] card = PokeBuilder.poke().deal(number);
		for(PokeBuilder a:card) {
			System.out.print(a.getCardAndPoint()+" ");
		}
		System.out.println();
		System.out.println(Card5Rule(card));
			
	}
	private String Card5Rule(PokeBuilder[] poke) {
		String bigOne = poke[0].getCardAndPoint();
		
		//記每張牌有幾張,14為A,13為K....2為2,0為鬼牌
		int[] countcardnumber = new int[15];
		for(int i =0;i<poke.length;i++) {
			countcardnumber[poke[i].getPoint()]++;
		}
		
		//判斷鐵支跟葫蘆
		for(int i=14;i>1;i--) {
			if(countcardnumber[i]==4) {
				return PokeBuilder.poke().getCard(i)+"鐵支";
			}
			if(countcardnumber[i]==3) {
				for(int j=14;j>1;j--) {
					if(j==i) {
						continue;
					}
					if(countcardnumber[j]==2) {
						return PokeBuilder.poke().getCard(i)+"葫蘆";
					} 
				}
			}
		}
		//判斷同花
		Arrays.sort(poke, new CompareSuit());
		for(int i=0;i<poke.length-4;i++) {
			if(poke[i].getId()/13==poke[i+4].getId()/13) {
				return poke[i].getSuit()+"同花 最大"+PokeBuilder.poke().getCard(poke[i].getPoint());
			}
		}
		
		for(int i=14;i>5;i--) {
			if(countcardnumber[i]>0&&
				countcardnumber[i-1]>0&&
				countcardnumber[i-2]>0&&
				countcardnumber[i-3]>0&&
				countcardnumber[i-4]>0) {
				return "順子結尾"+PokeBuilder.poke().getCard(i);
			}
			if(countcardnumber[14]>0&&
				countcardnumber[2]>0&&
				countcardnumber[3]>0&&
				countcardnumber[4]>0&&
				countcardnumber[5]>0) {
				return "順子結尾"+5;
			}
		}
		
		for(int i =14;i>1;i--) {
			if(countcardnumber[i]==3) {
				return PokeBuilder.poke().getCard(i)+" 三條";
			}
			if(countcardnumber[i]==2) {
				for(int j =14;j>1;j--) {
					if(i==j) {
						continue;
					}
					if(countcardnumber[j]==2) {
						return PokeBuilder.poke().getCard(i)+" "+PokeBuilder.poke().getCard(j)+" 兩對";
					}
				}
				return PokeBuilder.poke().getCard(i) +" 對子";
			}
		}
		return bigOne;
	}
}
	


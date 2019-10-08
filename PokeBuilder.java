package tw.com.lccnet.chap7.poke.third;

import java.util.Arrays;
import java.util.Random;

public class PokeBuilder {
	private int id;// 牌序號,使牌不會重複;
	// 判斷牌組是否放入鬼牌
	static boolean WantJoker = false;
	//三元運算式true的1代表發幾張鬼牌
	private int jokerControl = (WantJoker ? 1 : 0);

	// 私有無參數建構子,三層式寫法用
	private PokeBuilder() {
	}

	// 建立牌使用建構子
	private PokeBuilder(int a) {
		id = a;
	}

	// 三層式寫法用方法,靜態產生PokeStatement類別才能調用內部方法
	public static PokeBuilder poke() {
		return new PokeBuilder();
	}

	// 取得id
	public int getId() {
		return id;
	}

	// 建立點數判斷 鬼牌0 A14 K13 Q12....
	public int getPoint() {
		int Point = id % 13;

		switch (id % 13) {
		case 0:
			Point = 13;
			break;
		case 1:
			Point = 14;
			break;
		}
		if (id / 13 == 4) {
			Point = 0;
		}
		return Point;
	}
	//取得牌的花色
	public String getSuit() {
		String suit = "";

		switch (id / 13) {
		case 0:
			suit = "梅花";
			break;
		case 1:
			suit = "方塊";
			break;
		case 2:
			suit = "愛心";
			break;
		case 3:
			suit = "黑桃";
			break;
		case 4:
			suit = "鬼牌";
			break;
		}
		return suit;
	}
	//取得牌面數字
	public String getCard(int point) {
		String Card = "" + point % 13;
		switch (point % 13) {
		case 0:
			Card = "K";
			break;
		case 1:
			Card = "A";
			break;
		case 12:
			Card = "Q";
			break;
		case 11:
			Card = "J";
			break;
		}
		if (id / 13 == 4) {
			Card = "";
		}
		return Card;
	}
	//取得整張牌資料
	public String getCardAndPoint() {
		return getSuit() + getCard(getPoint());
	}
	//做出一副牌,若WantJoker=true則會含鬼牌
	public PokeBuilder[] fullCard() {
		PokeBuilder[] fullcard = new PokeBuilder[52 + jokerControl];

		for (int i = 0; i < fullcard.length; i++) {
			fullcard[i] = new PokeBuilder(i);
		}
		return fullcard;
	}
	//洗牌
	public PokeBuilder[] randomCard() {
		Random random = new Random();
		PokeBuilder[] fc = fullCard();
		PokeBuilder[] randomcard = new PokeBuilder[52 + jokerControl];
		for (int i = 0; i < randomcard.length; i++) {
			int j = random.nextInt(52 + jokerControl - i);
			randomcard[i] = fc[j];
			for (int k = j; k < randomcard.length - 1; k++) {
				fc[k] = fc[k + 1];
			}
		}
		return randomcard;
	}
	//發n張牌
	public PokeBuilder[] deal(int number) {
		PokeBuilder[] deal = Arrays.copyOf(randomCard(), number);
		Arrays.sort(deal, new ComparePoke());
		return deal;
	}
	//發number張牌給players位玩家
	//玩家0的牌組就是PokeBuilder[0][]矩陣,玩家1的牌組就是PokeBuilder[1][]矩陣
	public PokeBuilder[][] deal(int number, int players) {
		PokeBuilder[] rc =randomCard();
		PokeBuilder[][] deals = new PokeBuilder[players][number];
		for (int i = 0; i < deals.length; i++) {
			for (int j = 0; j < deals[i].length; j++) {
				deals[i][j] = rc[ number* i + j];
			}
			Arrays.sort(deals[i], new ComparePoke());
		}
		return deals;
	}
}

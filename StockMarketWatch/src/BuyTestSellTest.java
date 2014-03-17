import java.util.Random;

public class BuyTestSellTest implements StrategyType {
	@Override
	public void buySellHold(Stock stock) {
		Random rand = new Random();
		int randomNum = rand.nextInt(3);
		
		if (randomNum == 0) {
			stock.setBsh(BSH.BUY);
		} else if (randomNum == 1) {
			stock.setBsh(BSH.SELL);
		} else {
			stock.setBsh(BSH.HOLD);
		}
	}
}
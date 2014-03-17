public class BuyCharSellTime implements StrategyType {
	@Override
	public void buySellHold(Stock stock) {
		String lowerBounds = "01:00";
		String upperBounds = "03:22";
		String checkTime = stock.getTime();
		
		int lowerResult = lowerBounds.compareTo(checkTime);
		int upperResult = upperBounds.compareTo(checkTime);
		
		if (stock.getSymbol().length() == 4) {
			stock.setBsh(BSH.BUY);
		} else if (lowerResult < 0 && upperResult > 0) {
			stock.setBsh(BSH.SELL);
		} else {
			stock.setBsh(BSH.HOLD);
		}
	}
}
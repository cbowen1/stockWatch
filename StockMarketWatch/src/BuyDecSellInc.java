public class BuyDecSellInc implements StrategyType {
	@Override
	public void buySellHold(Stock stock) {
		
		if ((stock.getLast() - stock.getClose()) >= (stock.getClose() * .05)) {
			stock.setBsh(BSH.SELL);
		} else if ((stock.getClose() - stock.getLast()) >= (stock.getClose() * .05)) {
			stock.setBsh(BSH.BUY);
		} else {
			stock.setBsh(BSH.HOLD);
		}
		
	}
}

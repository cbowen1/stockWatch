public class BuyIncSellDec implements StrategyType {
	/*
	 * Write function that ifBuy, send the stock to buy
	 * ifSell, send the stock to sell
	 * else, send the stock to hold
	 */
	@Override
	public void buySellHold(Stock stock) {
		if ((stock.getLast() - stock.getClose()) >= (stock.getClose() * .05)) {
			stock.setBsh(BSH.BUY);
		} else if ((stock.getClose() - stock.getLast()) >= (stock.getClose() * .05)) {
			stock.setBsh(BSH.SELL);
		} else {
			stock.setBsh(BSH.HOLD);
		}
		
	}
}

public class BuyPrimeSellEven implements StrategyType {
	@Override
	public void buySellHold(Stock stock) {
		
		if (isPrime(stock.getVolume())) {
			stock.setBsh(BSH.BUY);
		} else if (stock.getVolume() % 2 == 0) {
			stock.setBsh(BSH.SELL);
		} else {
			stock.setBsh(BSH.HOLD);
		}
		
	}
	
	boolean isPrime(double n) {
	    if (n % 2 == 0) 
	    	return false;
	    
	    for(double i = 3; i * i <= n; i += 2) {
	        if(n % i == 0)
	            return false;
	    }
	    
	    return true;
	}
}

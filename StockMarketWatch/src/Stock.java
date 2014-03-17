public class Stock {
	private String symbol;
	private String name;
	private double last;
	private String date;
	private String time;
	private double open;
	private double high;
	private double low;
	private double close;
	private double volume;
	private BSH bsh;

	public void whatToDo(StrategyType strategy){
		strategy.buySellHold(this);
	}

	public Stock(Object symbol, Object name, Object last, Object open, Object close, Object time, Object date,Object high, Object low, Object volume) {
		this.symbol = (String) symbol;
		this.name = (String) name;
		this.last = Double.parseDouble((String)last);
		this.date = (String) date;
		this.time = (String) time;
		this.open = Double.parseDouble((String)open);
		this.high = Double.parseDouble((String)high);
		this.low = Double.parseDouble((String)low);
		this.close = Double.parseDouble((String)close);
		this.volume = Double.parseDouble((String)volume);
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public double getLast() {
		return last;
	}

	public void setLast(double last) {
		this.last = last;
	}	
	
	public BSH getBsh() {
		return bsh;
	}

	public void setBsh(BSH bsh) {
		this.bsh = bsh;
	}
}

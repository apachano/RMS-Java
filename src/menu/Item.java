package menu;

public class Item {
	String name;
	boolean active;
	int price;
	int ingredients[];
	
	Item(){
		name = "Does Not Exist";
		active = false;
	}
	Item(String input){
		ingredients = new int[100];
		int a,b,i = 0;
		a = input.indexOf("|");
		b = input.indexOf("|", a+1);
		name = input.substring(a+1,b);
		a = b;
		b = input.indexOf("|", a+1);
		if(Integer.parseInt(input.substring(a+1, b)) == 1)active=true;
		else active=false;
		a = b;
		b = input.indexOf("|", a+1);
		price = Integer.parseInt(input.substring(a+1, b));
		a = b;
		b = input.indexOf("|", a+1);
		i = 0;
		while(b != -1){//Scan in ingredients
			i++;
			ingredients[i] = Integer.parseInt(input.substring(a+1, b));
			a = b;
			b = input.indexOf("|", a+1);
		}
	}
	public String toString(){
		return name;
	}
	public String getName(){
		return name;
	}
	public String out(){
		return "|" + name + "|" + String.valueOf(active) + "|" + price + "|";
	}
	public boolean active(){
		return active;
	}
	public int getPrice() {
		return price;
	}
}

package menu;

public class Ingredient {
	String name;
	Boolean active;
	int charge;
	/**This constructor takes a pre formatted line from a database file and stores the information on
	 * the ingredient. 
	 * @param input Pre formatted line from database file
	 */
	public Ingredient(String input){
		int a,b = 0;
		a = input.indexOf("|");
		b = input.indexOf("|", a+1);
		name = input.substring(a+1,b);
		a = b;
		b = input.indexOf("|", a+1);
		if(Integer.parseInt(input.substring(a+1, b)) == 1)active=true;
		else active=false;
		a = b;
		b = input.indexOf("|", a+1);
		charge = Integer.parseInt(input.substring(a+1, b));
	}
	public Ingredient(String name, Boolean active, int charge){
		this.name = name;
		this.active = active;
		this.charge = charge;
	}
	public String toString(){
		return name;
	}
	public String out(){
		int a;
		if(active)a=1;
		else a=0;
		return "|" + name + "|" + String.valueOf(a) + "|" + charge + "|";
		
	}
}

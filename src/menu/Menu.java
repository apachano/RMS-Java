package menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Menu {
	public Ingredient ingredient[];
	public Item item[];
	static int i;
	static Scanner file;
	static String buffer;
	
	public Menu(File fingredients, File fitems) throws FileNotFoundException{
		//Scan in the ingredients
		System.out.println("Loading Ingredients");
		file = new Scanner(new FileReader(fingredients));
		ingredient = new Ingredient[1000000];
		buffer = file.nextLine();
		while(! buffer.equals("terminate")){
			i = Integer.parseInt(buffer.substring(0,6));
			ingredient[i] =  new Ingredient(buffer);
			buffer = file.nextLine();
		}
		//Scan in the Menu Items
		System.out.println("Loading menu items");
		file = new Scanner(new FileReader(fitems));
		item = new Item[1000000];
		for(i=0;i<1000000;i++){
			item[i] = new Item();
		}
		buffer = file.nextLine();
		i=0;
		while(! buffer.equals("terminate")){
			i = Integer.parseInt(buffer.substring(0,6));
			item[i] = new Item(buffer);
			buffer = file.nextLine();
			check(i);
		}
		System.out.println("Menu Loaded");
	}
	public Menu() {
		ingredient = new Ingredient[1000000];
		item = new Item[1000000];
	}
	public boolean check(int id){
		//TODO add code to check items active flag against ingredients.
		return true;
	}
	public Item getItem(int i){
		return item[i];
	}
	static FileWriter bw;
	public void writeOut(File fingredients, File fitems) throws Exception{
		System.out.println(fingredients);
		fingredients.getParentFile().mkdirs();
		fingredients.createNewFile();
		bw = new  FileWriter(fingredients);
		for(i=0; i<1000000; i++){
			if(ingredient[i] != null){
				bw.write(getID(i) + ingredient[i].out() + "\n");
			}
		}
		bw.write("terminate");
		bw.close();
		//Save items to file
		System.out.println(fitems);
		fitems.getParentFile().mkdirs();
		fitems.createNewFile();
		bw = new  FileWriter(fitems);
		for(i=0; i<1000000; i++){
			if(item[i] != null){
				bw.write( getID(i) + item[i].out() + "\n");
			}
		}
		bw.write("terminate");
		bw.close();
	}
	/**
	 * Converts an integer id into a 6 digit string id
	 * @param id The id to convert
	 * @return The resulting 6 digit id
	 */
	
	public static String getID(int id){
		if(id < 10)return("00000" + id);
		if(id < 100)return("0000" + id);
		if(id < 1000)return("000" + id);
		if(id < 10000)return("00" + id);
		if(id < 100000)return("0" + id);
		return String.valueOf(id);
	}
}

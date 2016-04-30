package cs477.ontap;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Alex on 4/17/2016.
 */
public class drinkObject {
    public String drinkName;
    public String drinkAlcohol;
    public String drinkType;
    public int drinkPrice;
    public int drinkQuantity;

    public drinkObject(String thisDrinksName, String thisDrinksAlcohol, String thisDrinksType, int thisDrinksPrice){

        drinkName = thisDrinksName;
        drinkAlcohol = thisDrinksAlcohol;
        drinkType = thisDrinksType;
        drinkPrice = thisDrinksPrice;
        drinkQuantity = 1;

    }

    public String getDrinkName(){
        return drinkName;
    }

    public String getDrinkAlcohol(){
        return drinkAlcohol;
    }

    public String getDrinkType(){
        return drinkType;
    }

    public int getDrinkPrice(){
        return drinkPrice;
    }

    public void setDrinkQuantity(int newQuantity){
        drinkQuantity = newQuantity;
    }


    public int getDrinkQuantity(){
        return drinkQuantity;
    }

}

package com.JohnConnolly.Battleships;

public class Ship
{
    private String type;
    private int length ;
    private int damage ;

    public Ship(String type, int length)
    {
        this.type = type ;
        this.length = length ;
        this.damage = 0 ;
    }

    public int getDamage()
    {
        return damage ;
    }
    
    public void incDamage()
    {
        damage++ ;
    }
    
    public double damageStatus()
    {
        return (double)damage / (double)length ;
    }
    
    public boolean isDestroyed()
    {
        return damage == length ;
    }
    
    // ONLY sets provided for the following instance variables
    // because you cannot alter them after construction
    public String getType()
    {
        return type ;
    }

    public int getLength()
    {
        return length ;
    }    
}

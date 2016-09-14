package com.JohnConnolly.Battleships;

import java.util.Random ;

public class ExploreClasses
{
    public static void main(String[] args)
    {
        
        Fleet myFleet = new Fleet() ;
        Fleet yourFleet = new Fleet() ;

        System.out.println("Initial state of my fleet...") ;
        displayFleet(myFleet) ;
        System.out.println() ;
        System.out.println("Initial state of your fleet...") ;
        displayFleet(yourFleet) ;

        System.out.println() ;
        System.out.println() ;

        System.out.println("Destroy my fleet...") ;
        destroyFleet(myFleet) ;
        System.out.println() ;
        System.out.println("Destory your fleet...") ;
        destroyFleet(yourFleet) ;
        
        // Examples of cellRef operations
        Random rand = new Random() ;
        int row, col ;
        
        System.out.println("\n\nUsing cell references of the type F4 or A1 or C7...") ;
        System.out.println("A pair of co-ordinates can be converted to a cell ref using the cellRef method. For example...") ;
        for(int i = 0 ; i < 10 ; i++) {
            row = rand.nextInt(10) ;
            col = rand.nextInt(10) ;
            System.out.print("The co-ordinates [" + row + "][" + col + "] are equivalent to cell reference ") ;
            System.out.println(cellRef(row,col)) ;
        }
        System.out.println("\n\nThe row and column components of a cell ref can be determined using cellRefRow and cellRefCol. For example...") ;
        System.out.println("CellRef A6 is row " + cellRefRow("A6") + " and column " + cellRefCol("A6")) ;
        System.out.println("CellRef B9 is row " + cellRefRow("B9") + " and column " + cellRefCol("B9")) ;
        System.out.println("CellRef F2 is row " + cellRefRow("F2") + " and column " + cellRefCol("F2")) ;
        System.out.println("CellRef H7 is row " + cellRefRow("H7") + " and column " + cellRefCol("H7")) ;
        System.out.println("CellRef D8 is row " + cellRefRow("D8") + " and column " + cellRefCol("D8")) ;
        System.out.println("CellRef G9 is row " + cellRefRow("G9") + " and column " + cellRefCol("G9")) ;        
    }

    private static void displayFleet(Fleet flt)
    {
        Ship s ;
        int i ;
        for(i = 1 ; i < flt.size() ; i++) {
            s = flt.shipAt(i) ;
            System.out.print(s.getType() + "\t") ;
            System.out.print(s.getLength() + "\t") ;
            System.out.print(s.getDamage() + "\t") ;
            System.out.print(s.damageStatus() + "\t") ;
            System.out.println() ;
        }
    }
    
    private static void destroyFleet(Fleet flt)
    {
        Ship s ;
        Random rand = new Random() ;
        int k ;
        int shipsDestroyed = 0 ;
        while(shipsDestroyed < flt.size()-1) { 
            // randomly pick a ship
            k = rand.nextInt(flt.size()) ;
            if(k != 0) { // look at Fleet class - we didn't use position zero
                s = flt.shipAt(k) ;
                if(s.isDestroyed() == false) { // or if(!s.isDestroyed()) if you prefer
                    s.incDamage() ;
                    System.out.print("Ship " + k + " : " + s.getType()) ;
                    System.out.print(" is " + s.getDamage() + "/" ) ;
                    System.out.print(s.getLength() + " or ") ;
                    System.out.println( (int)(s.damageStatus()*100) + "% destroyed.") ;
                    if(s.isDestroyed() == true) { // or if(s.isDestroyed()) if you prefer
                        shipsDestroyed++ ; 
                    }
                }
            }
        }
    }
    
    
    public static String cellRef(int row, int col)
    {
        return "" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(col) + row ;
    }
    
    public static int cellRefRow(String cellRef)
    {
        return Integer.valueOf(cellRef.substring(1,cellRef.length())).intValue() ;
    }
    
    public static int cellRefCol(String cellRef)
    {
        return "ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(cellRef.substring(0,1)) ;
    }
}
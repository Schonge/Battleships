package com.JohnConnolly.Battleships;


public class Fleet
{
    private Ship[] fleet ;

    public Fleet()
    {
        fleet = new Ship[9+1] ;// We are including an extra elementso that we can number the ships 1 to 9
        fleet[0] = null ; // We are not using element 0 so that we can number the ships 1 to 9 
        fleet[1] = new Ship("Battleship",4) ;
        fleet[2] = new Ship("Crusier",3) ;
        fleet[3] = new Ship("Crusier",3) ;
        fleet[4] = new Ship("Destroyer",2) ;
        fleet[5] = new Ship("Destroyer",2) ;
        fleet[6] = new Ship("Destroyer",2) ;
        fleet[7] = new Ship("Submarine",1) ;
        fleet[8] = new Ship("Submarine",1) ;
        fleet[9] = new Ship("Submarine",1) ;
    }

    public int size()
    {
        return fleet.length ;
    }
    
    public Ship shipAt(int pos)
    {
        if(pos >= 1 && pos < fleet.length) {
            return fleet[pos];
        } else {
            return null ;
        }
    }
    
    public int shipPos(Ship ref) 
    {
        int i ;
        for(i=1 ; i < fleet.length && fleet[i] != ref ; i++) ;
        if(i < fleet.length) {
            return i ;
        } else {
            return -1 ;
        }
    }
}
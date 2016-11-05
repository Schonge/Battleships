package com.johnconnolly.battleships;

import java.util.Random ;

public class Player
{
    private int[][] grid ;
    public Fleet fleet = new Fleet() ;
    public Ship ship ;
    
    public Player()
    {
        grid = new int[10][10] ;
        placeShips() ;
    }
    
    public Player(int gridSize)
    {
        if(gridSize > 26 || gridSize < 10)
        {
            if(gridSize > 26)
            {
                grid = new int[12][12] ;
                placeShips() ;
            } else {
                grid = new int[10][10] ;
                placeShips() ;
            }
        } else {
            grid = new int[gridSize][gridSize] ;
            placeShips() ;
        }
    }
    
    public void placeShips()
    {
        int row, col, i, j ;
        boolean placed = false ;
        Random rand = new Random() ;
        for(i = 1 ; i < fleet.size() ; i++)
        {
            int direction = rand.nextInt(2) ;
            while(placed == false)
            {
                ship = fleet.shipAt(i) ;
                row = rand.nextInt(grid[0].length) ;
                col = rand.nextInt(grid[0].length) ;
                if(grid[row][col] == 0)
                {
                    if(direction == 0)
                    {
                        if(row + ship.getLength() < grid[0].length)
                        {
                            placed = true; // We say at this stage that we CAN place the ship, but we still need to check if a ship is in the way
                            for(j = 1; j < ship.getLength() && placed == true; j++)
                            {
                                if(grid[row + j][col] == 0)
                                {
                                    placed = true;
                                }
                                else
                                {
                                    placed = false;
                                }
                            }
                        }
                        if(placed == true)
                        {
                            for(j = 1 ; j <= ship.getLength() ; j++)
                            {
                                grid[row][col] = i ;
                                row++ ;
                            }
                        }
                        
                    } else if(direction == 1) {
                        if(col + ship.getLength() < grid[0].length)
                        {
                            placed = true ;
                            for(j = 1 ; j < ship.getLength() && placed == true ; j++)
                            {
                                if(grid[row][col + j] == 0)
                                {
                                    placed = true ;
                                } else {
                                    placed = false ;
                                }
                            }
                        }
                        if(placed == true)
                        {
                            for(j = 1 ; j <= ship.getLength() ; j++)
                            {
                                grid[row][col] = i ;
                                col++ ;
                            }
                        }
                    }
                }
            }
        }
    }
    
    public int shipsRemaining()
    {
        int amountShips = fleet.size() ;
        for(int i = 1 ; i < fleet.size() ; i++)
        {
            ship = fleet.shipAt(i) ;
            if(ship.isDestroyed() == true)
            {
                amountShips-- ;
            }
        }
        return amountShips ;
    }
    
    public void display()
    {
        String columnLabel = " ABCDEFGHIJKLMNOPQRSTUVWXYZ" ;
        String[][] displayGrid = new String[grid.length][grid[0].length] ;
        int rowNums = 0 ;
        for(int i = 0 ; i < grid.length + 1 ; i++)
        {
            System.out.print(columnLabel.substring(i, i+1) + "\t") ;
        }
        System.out.println() ;
        
        System.out.println() ;
        
        for(int i = 0 ; i < grid.length ; i++)
        {
            System.out.print(rowNums + "\t") ;
            rowNums++ ;
            for(int j = 0 ; j < grid[0].length ; j++)
            {
                if(grid[i][j] == 0)
                {
                    displayGrid[i][j] = "-" ;
                } else if(grid[i][j] == -1) {
                    displayGrid[i][j] = "O" ;
                } else if(grid[i][j] == -2) {
                    displayGrid[i][j] = "X" ;
                } else if(grid[i][j] >=1 && grid[i][j] <= fleet.size()) {
                    for(int k = 1 ; k < fleet.size() ; k++)
                    {
                        if(grid[i][j] == k)
                        {
                            displayGrid[i][j] = fleet.shipAt(k).getType().substring(0, 1) ;
                        }
                    }
                }
                System.out.print(displayGrid[i][j] + "\t") ;
            }
            System.out.println() ;
        }
    }
    
    public String nextTarget()
    {
        String colLabel = " ABCDEFGHIJKLMNOPQRSTUVWXYZ" ;
        int rowNums = 0 ;
        Random rand = new Random() ;
        int row = rand.nextInt(grid.length) ;
        rowNums = row ;
        int col = rand.nextInt(grid.length) ;
        colLabel = colLabel.substring(col, col + 1) ;
        return rowNums + colLabel ;
    }
    
    public String incoming(String targetRef)
    {
        String result = "" ;
        int row = ExploreClasses.cellRefRow(targetRef) ;
        int col = ExploreClasses.cellRefCol(targetRef) ;
        if(grid[row][col] >= 1 && grid[row][col] <= fleet.size())
        {
            ship = fleet.shipAt(grid[row][col]) ;
            ship.incDamage() ;
            System.out.println("Damage is " + ship.getDamage() + " " + ship.getType()) ;
            if(ship.getDamage() == ship.getLength())
            {
                System.out.println("YOU SUNK MY BATTLESHIP!") ;
            }
            result = "Hit" ;
            grid[row][col] = -2 ;
            System.out.println() ;
            System.out.println("Fired at Location: " + targetRef + " Result: " + result) ;
            return result ;
        } else if(grid[row][col] == -1 || grid[row][col] == -2) {
            if(grid[row][col] == 0) 
            {
                grid[row][col] = -1 ;
                result = "Miss" ;
                System.out.println() ;
                System.out.println("Fired at Location: " + targetRef + " Result: " + result) ;
                return result ;
            }
        } else {
        }
        return incoming(nextTarget()) ;
    }                    
}
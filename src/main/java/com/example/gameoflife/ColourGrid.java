package com.example.gameoflife;

public class ColourGrid
{
    public Tile[][] grid;
    public int rows;
    public int cols;
    public int cwidth;
    public int cheight;
    public int count;

    public ColourGrid(int r, int c)
    {
        cwidth = 10;
        cheight = 10;
        count = 0;
        rows = r;
        cols = c;
        grid = new Tile[r][c];

        for(int ro = 0; ro<rows; ro++)
        {
            for(int co = 0; co<cols; co++)
            {
                grid[ro][co] = new Tile();

            }
        }

    }
    public void display()
    {
        int y = 0;
        for(int ro = 0; ro<rows; ro++)
        {
            int x = 0;
            for(int co = 0; co<cols; co++)
            {
                //we need to draw the tile at grid[ro][co]
                NOOPDraw.setColor(grid[ro][co].r,grid[ro][co].g,grid[ro][co].b);
                NOOPDraw.fillRectangle(x,y,cwidth,cheight);
                x = x + cwidth;

            }
            y = y + cheight;
        }
    }
    public String tileWas(int ro, int co)
    {
        return grid[ro][co].name;
    }
    public boolean isLegal(int ro, int co)
    {
        if(ro < 0 || ro >=rows || co<0 || co >=cols)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean leftNeighbourHas(String name, int ro, int co)
    {
        if(isLegal(ro,co-1) == true && grid[ro][co-1].name.equals(name))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean rightNeighbourHas(String name, int ro, int co)
    {
        if(isLegal(ro,co+1) == true && grid[ro][co+1].name.equals(name))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean upNeighbourHas(String name, int ro, int co)
    {
        if(isLegal(ro-1,co) == true && grid[ro-1][co].name.equals(name))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean downNeighbourHas(String name, int ro, int co)
    {
        if(isLegal(ro+1,co) == true && grid[ro+1][co].name.equals(name))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void Update()
    {
        count++;
        System.out.println(count);
        for(int ro = 0; ro<rows; ro++)
        {
            for(int co = 0; co<cols; co++)
            {
                grid[ro][co].age++;
            }
        }
        for(int ro = 0; ro<rows; ro++)
        {
            for(int co = 0; co<cols; co++)
            {

                if(grid[ro][co].name.equals("dirt") && grid[ro][co].age == 5)
                {
                    if(Math.random() <=0.45)
                    {
                        grid[ro][co].setGrass();
                    }

                }
                if(grid[ro][co].name.equals("grass") && grid[ro][co].age == 12)
                {
                    //2% chance of grass turning into dirt after 12 turns
                    if(Math.random() <=0.02)
                    {
                        grid[ro][co].setDirt();
                    }
                    //0.008% chance for a fire to start
                    else if(Math.random() <= 0.0008)
                    {
                        grid[ro][co].setFire();
                    }
                }
                if(count <= 600)
                {
                    //Cactus has a 35% chance to turn into water after 10 turns
                    if(grid[ro][co].name.equals("Cactus") && grid[ro][co].age > 10)
                    {
                        if(Math.random() < 0.35)
                        {
                            grid[ro][co].setWater();
                        }
                    }
                    else if(grid[ro][co].name.equals("Sand") && grid[ro][co].age == 10)
                    {
                        if(Math.random() <= 0.0005)
                        {
                            grid[ro][co].setWater();
                        }
                    }
                    else if(grid[ro][co].name.equals("grass") || grid[ro][co].name.equals("dirt") || grid[ro][co].name.equals("fire"))
                    {
                        //10% chance for rain to appear on grass, dirt or fire
                        if(Math.random() <= 0.1)
                        {
                            grid[ro][co].setWater();
                        }
                    }
                    else if(grid[ro][co].name.equals("water") && grid[ro][co].age == 3)
                    {
                        //Water has 50% chance to change to grass after 3 turns
                        if(Math.random() <=0.50)
                        {
                            grid[ro][co].setGrass();
                        }

                    }
                    //Water has 2% chance to spread
                    if(downNeighbourHas("water", ro, co) == true && Math.random()<=0.02 && grid[ro][co].name != ("Stone"))
                    {
                        grid[ro][co].setWater();
                    }
                    if(rightNeighbourHas("water", ro, co) == true && Math.random()<=0.02 && grid[ro][co].name != ("Stone"))
                    {
                        grid[ro][co].setWater();
                    }
                    if(upNeighbourHas("water", ro, co) == true && Math.random()<=0.02 && grid[ro][co].name != ("Stone"))
                    {
                        grid[ro][co].setWater();
                    }
                    if(leftNeighbourHas("water", ro, co) == true && Math.random()<=0.02 && grid[ro][co].name != ("Stone"))
                    {
                        grid[ro][co].setWater();
                    }
                    if(grid[ro][co].name.equals("water") && Math.random() <= 0.5)
                    {
                        grid[ro][co].setGrass();
                    }
                    //10% chance for water to turn into grass
                    if(grid[ro][co].name.equals("water") && Math.random() <= 0.1)
                    {
                        grid[ro][co].setGrass();
                    }
                }
                if(count > 600)
                {
                    if(count <= 1000)
                    {
                        if(grid[ro][co].name.equals("grass") || grid[ro][co].name.equals("dirt") || grid[ro][co].name.equals("fire"))
                        {
                            if(Math.random() <= 0.15)
                            {
                                grid[ro][co].setWater();
                            }
                        }
                        if(grid[ro][co].name.equals("water") && grid[ro][co].age == 3)
                        {
                            if(Math.random() <=0.75)
                            {
                                grid[ro][co].setGrass();
                            }

                        }
                        if(downNeighbourHas("water", ro, co) == true && Math.random()<=0.08 && grid[ro][co].name != ("Stone"))
                        {
                            grid[ro][co].setWater();
                        }
                        if(rightNeighbourHas("water", ro, co) == true && Math.random()<=0.08 && grid[ro][co].name != ("Stone"))
                        {
                            grid[ro][co].setWater();
                        }
                        if(upNeighbourHas("water", ro, co) == true && Math.random()<=0.08 && grid[ro][co].name != ("Stone"))
                        {
                            grid[ro][co].setWater();
                        }
                        if(leftNeighbourHas("water", ro, co) == true && Math.random()<=0.08 && grid[ro][co].name != ("Stone"))
                        {
                            grid[ro][co].setWater();
                        }
                        if(grid[ro][co].name.equals("water") && Math.random() <= 0.4)
                        {
                            grid[ro][co].setGrass();
                        }
                    }
                }
                if(count >= 1000)
                {
                    if(count < 1300)
                    {
                        if(grid[ro][co].name.equals("grass") || grid[ro][co].name.equals("dirt") || grid[ro][co].name.equals("fire"))
                        {
                            if(Math.random() <= 0.25)
                            {
                                grid[ro][co].setWater();
                            }
                        }
                        if(grid[ro][co].name.equals("water") && grid[ro][co].age == 3)
                        {
                            if(Math.random() <=0.75)
                            {
                                grid[ro][co].setGrass();
                            }

                        }
                        if(downNeighbourHas("water", ro, co) == true && Math.random()<=0.08 && grid[ro][co].name != ("Stone"))
                        {
                            grid[ro][co].setWater();
                        }
                        else if(rightNeighbourHas("water", ro, co) == true && Math.random()<=0.08 && grid[ro][co].name != ("Stone"))
                        {
                            grid[ro][co].setWater();
                        }
                        else if(upNeighbourHas("water", ro, co) == true && Math.random()<=0.08 && grid[ro][co].name != ("Stone"))
                        {
                            grid[ro][co].setWater();
                        }
                        else if(leftNeighbourHas("water", ro, co) == true && Math.random()<=0.08 && grid[ro][co].name != ("Stone"))
                        {
                            grid[ro][co].setWater();
                        }
                        else if(grid[ro][co].name.equals("water") && Math.random() <= 0.7)
                        {
                            grid[ro][co].setGrass();
                        }
                    }
                }
                if(count >= 1300)
                {
                    if(count < 1500)
                    {
                        if(grid[ro][co].name.equals("grass") || grid[ro][co].name.equals("dirt") || grid[ro][co].name.equals("fire"))
                        {
                            if(Math.random() <= 0.02)
                            {
                                grid[ro][co].setWater();
                            }
                        }
                        if(grid[ro][co].name.equals("water") && grid[ro][co].age == 3)
                        {
                            if(Math.random() <=0.75)
                            {
                                grid[ro][co].setGrass();
                            }
                        }
                        if(downNeighbourHas("water", ro, co) == true && Math.random()<=0.02 && grid[ro][co].name != ("Stone"))
                        {
                            grid[ro][co].setWater();
                        }
                        else if(rightNeighbourHas("water", ro, co) == true && Math.random()<=0.02 && grid[ro][co].name != ("Stone"))
                        {
                            grid[ro][co].setWater();
                        }
                        else if(upNeighbourHas("water", ro, co) == true && Math.random()<=0.02 && grid[ro][co].name != ("Stone"))
                        {
                            grid[ro][co].setWater();
                        }
                        else if(leftNeighbourHas("water", ro, co) == true && Math.random()<=0.02 && grid[ro][co].name != ("Stone"))
                        {
                            grid[ro][co].setWater();
                        }
                        if(grid[ro][co].name.equals("water") && Math.random() <= 0.1)
                        {
                            grid[ro][co].setGrass();
                        }
                    }
                }
                if(count >= 1500)
                {
                    if(count < 1600)
                    {
                        //Fire has 1% chance to spread
                        if(leftNeighbourHas("fire", ro, co) == true && grid[ro][co].name != ("water") && grid[ro][co].name != ("Stone") && grid[ro][co].name != ("Sand"))
                        {
                            if(Math.random() <= 0.01)
                            {
                                grid[ro][co].setFire();
                            }
                        }
                        if(rightNeighbourHas("fire", ro, co) == true && grid[ro][co].name != ("water") && grid[ro][co].name != ("Stone") && grid[ro][co].name != ("Sand"))
                        {
                            if(Math.random() <= 0.01)
                            {
                                grid[ro][co].setFire();
                            }
                        }
                        if(upNeighbourHas("fire", ro, co) == true && grid[ro][co].name != ("water") && grid[ro][co].name != ("Stone") && grid[ro][co].name != ("Sand"))
                        {
                            if(Math.random() <= 0.01)
                            {
                                grid[ro][co].setFire();
                            }
                        }
                        if(downNeighbourHas("fire", ro, co) == true && grid[ro][co].name != ("water") && grid[ro][co].name != ("Stone") && grid[ro][co].name != ("Sand"))
                        {
                            if(Math.random() <= 0.01)
                            {
                                grid[ro][co].setFire();
                            }
                        }
                        //Fire turns into dirt after 20 turns
                        if(grid[ro][co].name.equals("fire") && grid[ro][co].age == 20)
                        {
                            grid[ro][co].setDirt();
                        }
                    }
                    //0.001% chance for a sheep to appear on grass
                    if(Math.random() <= 0.0001 && grid[ro][co].name.equals("grass"))
                    {
                        grid[ro][co].setSheep();
                    }
                    //If grass is beside a sheep and the sheep has an age of 2 it goes on the tile an eats it
                    if(grid[ro][co].name.equals("Sheep") && downNeighbourHas("grass", ro, co) && grid[ro][co].age == 2)
                    {
                        grid[ro+1][co].setSheep();
                        grid[ro][co].setDirt();
                    }
                    if(grid[ro][co].name.equals("Sheep") && upNeighbourHas("grass", ro, co) && grid[ro][co].age == 2)
                    {
                        grid[ro-1][co].setSheep();
                        grid[ro][co].setDirt();
                    }
                    if(grid[ro][co].name.equals("Sheep") && rightNeighbourHas("grass", ro, co) && grid[ro][co].age == 2)
                    {
                        grid[ro][co+1].setSheep();
                        grid[ro][co].setDirt();
                    }
                    if(grid[ro][co].name.equals("Sheep") && leftNeighbourHas("grass", ro, co) && grid[ro][co].age == 2)
                    {
                        grid[ro][co-1].setSheep();
                        grid[ro][co].setDirt();
                    }
                    //Sheep die after age 15
                    if(grid[ro][co].name.equals("Sheep") && grid[ro][co].age == 15)
                    {
                        grid[ro][co].setDirt();
                    }
                    //if(Math.random() <= 0.00008 && grid[ro][co].name.equals("grass"))
                    //{

                    //	grid[ro][co].setWolf();
                    //}
                    //if(Math.random() < 0.25)
                    //{
                    //	if(grid[ro][co].name.equals("Wolf") && isLegal(ro+1, co))
                    //	{
                    //		String nextTile = tileWas(ro+1, co);
                    //		grid[ro+1][co].setWolf();
                    //		grid[ro][co].setDirt();
                    //	}
                    //}
                    //if(grid[ro][co].name.equals("Wolf"))
                    //{
                    //	grid[ro-1][co].setWolf();
                    //	grid[ro][co].setDirt();
                    //}
                    //if(grid[ro][co].name.equals("Wolf"))
                    //{
                    //	grid[ro][co+1].setWolf();
                    //	grid[ro][co].setDirt();
                    //}
                    //if(grid[ro][co].name.equals("Wolf"))
                    //{
                    //	grid[ro][co-1].setWolf();
                    //	grid[ro][co].setDirt();
                    //}
                    //Wolfs die after age 15
                    //if(grid[ro][co].name.equals("Wolf") && grid[ro][co].age == 15)
                    //{
                    //	grid[ro][co].setDirt();
                    //}
                    //If fire is beside sheep the sheep dies
                    if(grid[ro][co].name.equals("Sheep") && leftNeighbourHas("fire", ro, co))
                    {
                        grid[ro][co].setDirt();
                    }
                    if(grid[ro][co].name.equals("Sheep") && rightNeighbourHas("fire", ro, co))
                    {
                        grid[ro][co].setDirt();
                    }
                    if(grid[ro][co].name.equals("Sheep") && upNeighbourHas("fire", ro, co))
                    {
                        grid[ro][co].setDirt();
                    }
                    if(grid[ro][co].name.equals("Sheep") && downNeighbourHas("fire", ro, co))
                    {
                        grid[ro][co].setDirt();
                    }
                    if(downNeighbourHas("water", ro, co) == true && Math.random()<=0.01 && grid[ro][co].name != ("Stone"))
                    {
                        grid[ro][co].setWater();
                    }
                    if(rightNeighbourHas("water", ro, co) == true && Math.random()<=0.01 && grid[ro][co].name != ("Stone"))
                    {
                        grid[ro][co].setWater();
                    }
                    if(upNeighbourHas("water", ro, co) == true && Math.random()<=0.01 && grid[ro][co].name != ("Stone"))
                    {
                        grid[ro][co].setWater();
                    }
                    if(leftNeighbourHas("water", ro, co) == true && Math.random()<=0.01 && grid[ro][co].name != ("Stone"))
                    {
                        grid[ro][co].setWater();
                    }
                    if(grid[ro][co].name.equals("water") && Math.random() <= 0.02)
                    {
                        grid[ro][co].setGrass();
                    }
                }
                if(count >= 1600)
                {
                    if(count < 1800)
                    {
                        if(grid[ro][co].name.equals("water") && Math.random() <= 0.1)
                        {
                            grid[ro][co].setGrass();
                        }
                        if(leftNeighbourHas("fire", ro, co) == true && grid[ro][co].name != ("water") && grid[ro][co].name != ("Stone") && grid[ro][co].name != ("Sand"))
                        {
                            if(Math.random() <= 0.05)
                            {

                                grid[ro][co].setFire();
                            }
                        }
                        if(rightNeighbourHas("fire", ro, co) == true && grid[ro][co].name != ("water") && grid[ro][co].name != ("Stone") && grid[ro][co].name != ("Sand"))
                        {
                            if(Math.random() <= 0.05)
                            {
                                grid[ro][co].setFire();
                            }
                        }
                        if(upNeighbourHas("fire", ro, co) == true && grid[ro][co].name != ("water") && grid[ro][co].name != ("Stone") && grid[ro][co].name != ("Sand"))
                        {
                            if(Math.random() <= 0.05)
                            {
                                grid[ro][co].setFire();
                            }
                        }
                        if(downNeighbourHas("fire", ro, co) == true && grid[ro][co].name != ("water") && grid[ro][co].name != ("Stone") && grid[ro][co].name != ("Sand"))
                        {
                            if(Math.random() <= 0.05)
                            {
                                grid[ro][co].setFire();
                            }
                        }
                        if(grid[ro][co].name.equals("fire") && grid[ro][co].age == 20)
                        {
                            grid[ro][co].setDirt();
                        }
                        if(grid[ro][co].name.equals("grass") || grid[ro][co].name.equals("dirt") || grid[ro][co].name.equals("fire"))
                        {
                            if(Math.random() <= 0.005)
                            {
                                grid[ro][co].setWater();
                            }
                        }
                    }
                }
                if(count >= 1800)
                {
                    if(count < 2100)
                    {
                        //When water is beside the dirt it turns into Stone
                        if(grid[ro][co].name.equals("dirt") && leftNeighbourHas("water", ro ,co))
                        {
                            grid[ro][co].setStone();
                        }
                        if(grid[ro][co].name.equals("dirt") && rightNeighbourHas("water", ro ,co))
                        {
                            grid[ro][co].setStone();
                        }
                        if(grid[ro][co].name.equals("dirt") && downNeighbourHas("water", ro ,co))
                        {
                            grid[ro][co].setStone();
                        }
                        if(grid[ro][co].name.equals("dirt") && upNeighbourHas("water", ro ,co))
                        {
                            grid[ro][co].setStone();
                        }
                        if(grid[ro][co].name.equals("dirt") && upNeighbourHas("fire", ro ,co))
                        {
                            grid[ro][co].setStone();
                        }
                        //When fire is beside stone there is an 8 percent chance it turns into ash
                        if(grid[ro][co].name.equals("Stone") && upNeighbourHas("fire", ro ,co) && Math.random() < 0.08)
                        {
                            grid[ro][co].setAsh();
                        }
                        if(grid[ro][co].name.equals("Stone") && downNeighbourHas("fire", ro ,co) && Math.random() < 0.08)
                        {
                            grid[ro][co].setAsh();
                        }
                        if(grid[ro][co].name.equals("Stone") && rightNeighbourHas("fire", ro ,co) && Math.random() < 0.08)
                        {
                            grid[ro][co].setAsh();
                        }
                        if(grid[ro][co].name.equals("Stone") && leftNeighbourHas("fire", ro ,co) && Math.random() < 0.08)
                        {
                            grid[ro][co].setAsh();
                        }
                        if(grid[ro][co].name.equals("Stone") && Math.random() < 0.08)
                        {
                            grid[ro][co].setDirt();
                        }
                        if(leftNeighbourHas("fire", ro, co) == true && grid[ro][co].name != ("water") && grid[ro][co].name != ("Stone") && grid[ro][co].name != ("Sand"))
                        {
                            if(Math.random() <= 0.025)
                            {
                                grid[ro][co].setFire();
                            }
                        }
                        if(rightNeighbourHas("fire", ro, co) == true && grid[ro][co].name != ("water") && grid[ro][co].name != ("Stone") && grid[ro][co].name != ("Sand"))
                        {
                            if(Math.random() <= 0.025)
                            {
                                grid[ro][co].setFire();
                            }
                        }
                        if(upNeighbourHas("fire", ro, co) == true && grid[ro][co].name != ("water") && grid[ro][co].name != ("Stone") && grid[ro][co].name != ("Sand"))
                        {
                            if(Math.random() <= 0.025)
                            {
                                grid[ro][co].setFire();
                            }
                        }
                        if(downNeighbourHas("fire", ro, co) == true && grid[ro][co].name != ("water") && grid[ro][co].name != ("Stone") && grid[ro][co].name != ("Sand"))
                        {
                            if(Math.random() <= 0.025)
                            {
                                grid[ro][co].setFire();
                            }
                        }
                        if(grid[ro][co].name.equals("fire") && grid[ro][co].age == 20)
                        {
                            grid[ro][co].setDirt();
                        }
                        if(grid[ro][co].name.equals("grass") || grid[ro][co].name.equals("dirt") || grid[ro][co].name.equals("fire"))
                        {
                            if(Math.random() <= 0.0001)
                            {
                                grid[ro][co].setWater();
                            }
                        }
                    }
                }

                //When water is beside fire the fire goes out
                if(grid[ro][co].name.equals("water"))
                {
                    if(leftNeighbourHas("fire", ro, co))
                    {
                        grid[ro][co-1].setDirt();
                    }
                    else if(rightNeighbourHas("fire", ro, co))
                    {
                        grid[ro][co+1].setDirt();
                    }
                    else if(upNeighbourHas("fire", ro, co))
                    {
                        grid[ro-1][co].setDirt();
                    }
                    else if(downNeighbourHas("fire", ro, co))
                    {
                        grid[ro+1][co].setDirt();
                    }
                }

                else if(Math.random()<0.30 && grid[ro][co].name != ("Sand") && grid[ro][co].name != ("Cactus"))
                {
                    if(grid[ro][co].age == 5)
                    {
                        grid[ro][co].setDirt();
                    }
                }
                //If water is two tiles away from fire the tile in the middle is turned into stone(Minecraft cobblestone generator)
                if(isLegal(ro, co+2))
                {
                    if(grid[ro][co].name.equals("water") && grid[ro][co+2].name.equals("fire"))
                    {
                        grid[ro][co+1].setStone();
                    }
                }
                if(isLegal(ro, co-2))
                {
                    if(grid[ro][co].name.equals("water") && grid[ro][co-2].name.equals("fire"))
                    {
                        grid[ro][co-1].setStone();
                    }
                }
                if(isLegal(ro+2, co))
                {
                    if(grid[ro][co].name.equals("water") && grid[ro+2][co].name.equals("fire"))
                    {
                        grid[ro+1][co].setStone();
                    }
                }
                if(isLegal(ro-2, co))
                {
                    if(grid[ro][co].name.equals("water") && grid[ro-2][co].name.equals("fire"))
                    {
                        grid[ro-1][co].setStone();
                    }
                }
                if(grid[ro][co].name.equals("dirt") && Math.random() < 0.00005)
                {
                    grid[ro][co].setStone();
                }
            }
        }
    }
}

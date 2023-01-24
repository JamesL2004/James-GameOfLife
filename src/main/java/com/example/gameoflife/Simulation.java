package com.example.gameoflife;

public class Simulation
{
    public static void main(String[] args) throws InterruptedException
    {
        NOOPDraw.createWindow(1500, 800, "My Game of Life");
        ColourGrid game = new ColourGrid(76,148);

        game.display();

        while(true)
        {
            game.display();
            Thread.sleep(75);
            game.Update();
        }
    }
}

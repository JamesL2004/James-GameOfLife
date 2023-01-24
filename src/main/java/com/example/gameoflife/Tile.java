package com.example.gameoflife;

public class Tile
{
        public String name;
        public String colour;
        public int r;
        public int g;
        public int b;
        public int age;

        public Tile()
        {
            if(Math.random() < 0.95)
            {
                setSand();
            }
            else if(Math.random() < 0.1)
            {
                setDirt();
            }
            else if(Math.random() < 0.03)
            {
                setWater();
            }
            else
            {
                setCactus();
            }
        }
        public void setGrass()
        {
            name = "grass";
            age = 0;
            colour = "green";
            r=0;
            g=195 + (int)(Math.random() * 60);
            b=0;
        }
        public void setDirt()
        {
            name = "dirt";
            age = 0;
            colour = "brown";
            r=74;
            g=47;
            b=6;

        }
        public void setFire()
        {
            name = "fire";
            age = 0;
            colour = "red";
            r = 155 + (int)(Math.random() * 100);
            g=0;
            b=0;
        }
        public void setWater()
        {
            name = "water";
            age = 0;
            colour = "blue";
            r = 0;
            b = 155 + (int)(Math.random() * 100);
            g = 0;
        }
        public void setStone()
        {
            name = "Stone";
            age = 0;
            colour = "grey";
            r = 128;
            g = 128;
            b = 128;
        }
        public void setSheep()
        {
            name = "Sheep";
            age = 0;
            colour = "white";
            r = 255;
            b = 255;
            g = 255;
        }
        public void setSand()
        {
            name = "Sand";
            age = 0;
            colour = "yellow";
            r = 242;
            g = 209;
            b = 107;
        }
        public void setCactus()
        {
            name = "Cactus";
            age = 0;
            colour = "green";
            r = 0;
            g = 155;
            b = 0;
        }
        public void setWolf()
        {
            name = "Wolf";
            age = 0;
            colour = "brown";
            r = 94;
            g = 73;
            b = 14;
        }
        public void setAsh()
        {
            name = "Ash";
            age = 0;
            colour = "black";
            r = 0;
            g = 0;
            b = 0;
        }
    }


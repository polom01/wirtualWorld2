package Organisms.Animals;
import DataStructure.Point;
import Organisms.Organism;
import  World.World;
import World.WorldField;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Michal on 10.05.2017.
 */
public class Turtle extends Animal {
    public Turtle(World WorldToLive)
    {
        super(2, 1, WorldToLive);
        Type = WorldField.TURTLE;
    }
    public Turtle(World WorldToLive, Point P)
    {
        super(2,1,WorldToLive, P);
        Type = WorldField.TURTLE;
    }
    public Turtle(World WorldToLive, Scanner in)
    {
        super(1, WorldToLive, in);
        Type = WorldField.TURTLE;
    }

    public boolean DeflectedAttack(Organism Enemy)
    {
        if(Enemy.GetStrength() < 5)
        {
            WorldToLive.AddLog(this.getClass().getSimpleName() + " odbija atak " + Enemy.getClass().getSimpleName());
            return true;
        }
        return false;
    }

    public int Act()
    {
        Random MyRandom = new Random();
        int i = (MyRandom.nextInt()%100)+1;
        if(i>75)
            super.Act();
        return 0;
    }

    public int Collide(Organism AnotherOrganism)
    {
        if(AnotherOrganism instanceof Turtle)
            Reproduce();
        else
            super.Collide(AnotherOrganism);
        return 0;
    }
    public void Reproduce()
    {
        Point ChildPostion = GetChildPosition();
        if(!ChildPostion.equals(Position))
        {
            new Turtle(WorldToLive, ChildPostion);
            super.Reproduce();
        }
    }
}

package Organisms.Animals;

import DataStructure.Point;
import Organisms.Organism;
import World.World;
import World.WorldField;

import java.util.Scanner;

/**
 * Created by Michal on 10.05.2017.
 */
public class Wolf extends Animal {
    public Wolf(World WorldToLive)
    {
        super(9, 5, WorldToLive);
        Type = WorldField.WOLF;
    }
    public Wolf(World WorldToLive, Point P)
    {
        super(9,5,WorldToLive,P);
        Type = WorldField.WOLF;
    }
    public Wolf(World WorldToLive, Scanner in)
    {
        super(5, WorldToLive, in);
        Type = WorldField.WOLF;
    }

    public int Collide(Organism AnotherOrganism)
    {
        if(AnotherOrganism instanceof Wolf)
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
            new Wolf(WorldToLive, ChildPostion);
            super.Reproduce();
        }
    }
}

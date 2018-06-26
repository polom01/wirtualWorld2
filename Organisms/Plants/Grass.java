package Organisms.Plants;

import DataStructure.Point;
import World.World;
import World.WorldField;

import java.util.Scanner;

/**
 * Created by Michal on 10.05.2017.
 */
public class Grass extends Plant {
    public Grass(World WorldToLive)
    {
        super(0, WorldToLive);
        Type = WorldField.GRASS;
    }
    public Grass(World WorldToLive, Point P)
    {
        super(0, WorldToLive, P);
        Type = WorldField.GRASS;
    }
    public Grass(World WorldToLive, Scanner in)
    {
        super(WorldToLive, in);
        Type = WorldField.GRASS;
    }

    public int Act()
    {
        if(Position.equals(GetChildPosition()))
            return 0;
        for(int i = 0; i < 3; i++)
            super.Act();
        return 0;
    }

    public void Reproduce()
    {
        Point ChildPosition = GetChildPosition();
        if(!ChildPosition.equals(Position))
        {
            new Grass(WorldToLive, ChildPosition);
            super.Reproduce();
        }
    }
}

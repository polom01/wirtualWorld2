package Organisms.Plants;

import DataStructure.Point;
import World.World;
import World.WorldField;

import java.util.Scanner;

/**
 * Created by Michal on 10.05.2017.
 */
public class SowThistle extends Plant {
    public SowThistle(World WorldToLive)
    {
        super(0, WorldToLive);
        Type = WorldField.SOW_THISTLE;
    }
    public SowThistle(World WorldToLive, Point P)
    {
        super(0, WorldToLive);
        Type = WorldField.SOW_THISTLE;
    }
    public SowThistle(World WorldToLive, Scanner in)
    {
        super(WorldToLive, in);
        Type = WorldField.SOW_THISTLE;
    }

    public void Reproduce()
    {
        Point ChildPosition = GetChildPosition();
        if(!ChildPosition.equals(Position))
        {
            new SowThistle(WorldToLive, ChildPosition);
            super.Reproduce();
        }
    }
}

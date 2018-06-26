package Organisms.Plants;

import DataStructure.Point;
import Organisms.Organism;
import World.World;
import World.WorldField;

import java.util.Scanner;

/**
 * Created by Michal on 10.05.2017.
 */
public class Belladona extends Plant {
    public Belladona(World WorldToLive)
    {
        super(99,WorldToLive);
        ReproduceProbability = 15;
        Type = WorldField.BELLADONA;
    }
    public Belladona(World WorldToLive, Point P)
    {
        super(9, WorldToLive, P);
        ReproduceProbability = 15;
        Type = WorldField.BELLADONA;
    }
    public Belladona(World WorldToLive, Scanner in)
    {
        super(WorldToLive, in);
        ReproduceProbability = 15;
        Type = WorldField.BELLADONA;
    }

    public void Reproduce()
    {
        Point ChildPostion = GetChildPosition();
        if(!ChildPostion.equals(Position))
        {
            new Belladona(WorldToLive, ChildPostion);
            super.Reproduce();
        }
    }

    public int Collide(Organism AnotherOrganism)
    {
        Kill(AnotherOrganism);
        return 0;
    }
}

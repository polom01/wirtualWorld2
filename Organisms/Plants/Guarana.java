package Organisms.Plants;

import DataStructure.Point;
import Organisms.Organism;
import World.World;
import World.WorldField;

import java.util.Scanner;

/**
 * Created by Michal on 10.05.2017.
 */
public class Guarana extends Plant {
    public Guarana(World WorldToLive)
    {
        super(0, WorldToLive);
        Type = WorldField.GUARANA;
    }
    public Guarana(World WorldToLive, Point P)
    {
        super(0, WorldToLive);
        Type = WorldField.GUARANA;
    }
    public Guarana(World WorldToLive, Scanner in)
    {
        super(WorldToLive, in);
        Type = WorldField.GUARANA;
    }

    public void Reproduce()
    {
        Point ChildPosition = GetChildPosition();
        if(!ChildPosition.equals(Position))
        {
            new Guarana(WorldToLive, ChildPosition);
            super.Reproduce();
        }
    }

    public int Collide(Organism AnotherOrganism)
    {
        AnotherOrganism.Buff(3);
        return 0;
    }
}

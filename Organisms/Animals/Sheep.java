package Organisms.Animals;

import DataStructure.Point;
import Organisms.Organism;
import World.World;
import World.WorldField;

import java.util.Scanner;

/**
 * Created by Michal on 10.05.2017.
 */
public class Sheep extends Animal {
    public Sheep(World WorldToLive)
    {
        super(4, 4, WorldToLive);
        Type = WorldField.SHEEP;
    }
    public Sheep(World WorldToLive, Point P)
    {
        super(4,4,WorldToLive,P);
        Type = WorldField.SHEEP;
    }
    public Sheep(World WorldToLive, Scanner in)
    {
        super(4, WorldToLive, in);
        Type = WorldField.SHEEP;
    }

    public int Collide(Organism AnotherOrganism)
    {
        if(AnotherOrganism instanceof Sheep)
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
            new Sheep(WorldToLive, ChildPostion);
            super.Reproduce();
        }
    }
}

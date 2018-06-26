package Organisms.Plants;

import DataStructure.Point;
import Organisms.Animals.Animal;
import Organisms.Organism;
import World.World;
import World.WorldField;

import java.util.Scanner;

/**
 * Created by Michal on 10.05.2017.
 */
public class SosnowskyHogweed extends Plant {
    public SosnowskyHogweed(World WorldToLive)
    {
        super(10, WorldToLive);
        ReproduceProbability = 5;
        Type = WorldField.SOSNOWSKY_HOGWEED;
    }
    public SosnowskyHogweed(World WorldToLive, Point P)
    {
        super(10, WorldToLive, P);
        ReproduceProbability = 5;
        Type = WorldField.SOSNOWSKY_HOGWEED;
    }
    public SosnowskyHogweed(World WorldToLive, Scanner in)
    {
        super(WorldToLive, in);
        ReproduceProbability = 5;
        Type = WorldField.SOSNOWSKY_HOGWEED;
    }

    public int Act()
    {
        Point P1 = new Point(Position.GetX() + 1, Position.GetY());
        Point P2 = new Point(Position.GetX() - 1, Position.GetY());
        Point P3 = new Point(Position.GetX(), Position.GetY() + 1);
        Point P4 = new Point(Position.GetX(), Position.GetY() - 1);

        Organism O1 = WorldToLive.GetOrganimsQueue().Find(P1);
        Organism O2 = WorldToLive.GetOrganimsQueue().Find(P2);
        Organism O3 = WorldToLive.GetOrganimsQueue().Find(P3);
        Organism O4 = WorldToLive.GetOrganimsQueue().Find(P4);
        if(O1 != null && O1 instanceof Animal)
            Kill(O1);
        if(O2 != null && O2 instanceof Animal)
            Kill(O2);
        if(O3 != null && O3 instanceof Animal)
            Kill(O3);
        if(O4 != null && O4 instanceof Animal)
            Kill(O4);

        super.Act();
        return 0;
    }

    public void Reproduce()
    {
        Point ChildPosition = GetChildPosition();
        if(!ChildPosition.equals(Position))
        {
            new SosnowskyHogweed(WorldToLive, ChildPosition);
            super.Reproduce();
        }
    }

    public int Collide(Organism AnotherOrganism)
    {
        Kill(AnotherOrganism);
        return 0;
    }
}

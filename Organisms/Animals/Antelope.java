package Organisms.Animals;
import DataStructure.Point;
import Organisms.Organism;
import Organisms.Plants.Plant;
import World.World;
import World.WorldField;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Michal on 10.05.2017.
 */
public class Antelope extends Animal {
    public Antelope(World WorldToLive)
    {
        super(4, 4,WorldToLive);
        MoveDistance = 2;
        Type = WorldField.ANTELOPE;
    }
    public Antelope(World WorldToLive, Point P)
    {
        super(4, 4, WorldToLive, P);
        MoveDistance = 2;
        Type = WorldField.ANTELOPE;
    }
    public Antelope(World WorldToLive, Scanner in)
    {
        super(4, WorldToLive, in);
        MoveDistance = 2;
        Type = WorldField.ANTELOPE;
    }

    public int Collide(Organism AnotherOrganism)
    {
        if(AnotherOrganism instanceof Antelope)
            Reproduce();
        else if(AnotherOrganism instanceof Plant || !RunAway(AnotherOrganism))
            super.Collide(AnotherOrganism);
        return 0;
    }

    public void Reproduce()
    {
        Point ChildPosition = GetChildPosition();
        if(!ChildPosition.equals(Position))
        {
            new Antelope(WorldToLive, ChildPosition);
            super.Reproduce();
        }
    }

    public boolean RunAway(Organism Enemy)
    {
        Random MyRandom = new Random();
        int i = (MyRandom.nextInt() % 100) +1;
        if(i <= 50 && WorldToLive.IsEmptyNear(Position))
        {
            Position = GetChildPosition();
            WorldToLive.AddLog(this.getClass().getSimpleName() + " ucieka przed" + Enemy.getClass().getSimpleName());
            return true;
        }
        else
            return false;
    }
}

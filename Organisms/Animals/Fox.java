package Organisms.Animals;
import DataStructure.Point;
import Organisms.Organism;
import World.World;
import World.WorldField;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Michal on 10.05.2017.
 */
public class Fox extends Animal{
    public Fox(World WorldToLive)
    {
        super(3, 7,WorldToLive);
        Type = WorldField.FOX;
    }
    public Fox(World WorldToLive, Point P)
    {
        super(3, 7,WorldToLive, P);
        Type = WorldField.FOX;
    }
    public Fox(World WorldToLive, Scanner in)
    {
        super(3,WorldToLive,in);
        Type = WorldField.FOX;
    }

    public int Act()
    {
        Random MyRandom = new Random();
        Point FuturePosition = Position;
        int Dir;
        Organism AnotherOrganism;
        boolean isSet = false;

        Point P1 = new Point(Position.GetX() + MoveDistance, Position.GetY());
        Point P2 = new Point(Position.GetX() - MoveDistance, Position.GetY());
        Point P3 = new Point(Position.GetX(), Position.GetY() + MoveDistance);
        Point P4 = new Point(Position.GetX(), Position.GetY() - MoveDistance);

        Organism O1 = WorldToLive.GetOrganimsQueue().Find(P1);
        Organism O2 = WorldToLive.GetOrganimsQueue().Find(P2);
        Organism O3 = WorldToLive.GetOrganimsQueue().Find(P3);
        Organism O4 = WorldToLive.GetOrganimsQueue().Find(P4);

        if(!GoodNose())
        {
            WorldToLive.AddLog(this.getClass().getSimpleName() + " nie zweszyl dobrego miejsca, tu mu dobrze");
            return 0;
        }
        WorldToLive.AddLog(this.getClass().getSimpleName() + " zweszyl dobre miejsce");
        while(!isSet)
        {
            Dir = MyRandom.nextInt(4);
            switch(Dir) {
                case 0:
                    if (P2.GetX() >= 0) {
                        FuturePosition = P2;
                        if (O2 == null || O2.GetStrength() <= Strength)
                            isSet = true;
                    }
                    break;
                case 1:
                    if (P1.GetX() < WorldToLive.GetWidth()) {
                        FuturePosition = P1;
                        if (O1 == null || O1.GetStrength() <= Strength)
                            isSet = true;
                    }
                    break;
                case 2:
                    if (P4.GetY() >= 0) {
                        FuturePosition = P4;
                        if (O4 == null || O4.GetStrength() <= Strength)
                            isSet = true;
                    }
                    break;
                case 3:
                    if (P3.GetY() < WorldToLive.GetHeight())
                    {    FuturePosition = P3;
                        if (O3 == null || O3.GetStrength() <= Strength)
                            isSet = true;
                    }
                    break;
            }
            if(!Position.equals(FuturePosition))
                isSet = true;
        }
        AnotherOrganism = WorldToLive.GetOrganimsQueue().Find(FuturePosition);
        if(AnotherOrganism != null)
        {
            Collide(AnotherOrganism);
            if(AnotherOrganism.IsDead())
                Position = FuturePosition;
        }
        else
            Position = FuturePosition;
        return 0;
    }
    private boolean GoodNose()
    {
        Point P1 = new Point(Position.GetX() + MoveDistance, Position.GetY());
        Point P2 = new Point(Position.GetX() - MoveDistance, Position.GetY());
        Point P3 = new Point(Position.GetX(), Position.GetY() + MoveDistance);
        Point P4 = new Point(Position.GetX(), Position.GetY() - MoveDistance);

        Organism O1 = WorldToLive.GetOrganimsQueue().Find(P1);
        Organism O2 = WorldToLive.GetOrganimsQueue().Find(P2);
        Organism O3 = WorldToLive.GetOrganimsQueue().Find(P3);
        Organism O4 = WorldToLive.GetOrganimsQueue().Find(P4);

        if(P1.GetX() < WorldToLive.GetWidth() && (O1 == null || O1.GetStrength() <= Strength))
            return true;
        else if(P2.GetX() >= 0 && (O2 == null || O2.GetStrength() <= Strength))
            return true;
        else if(P3.GetY() < WorldToLive.GetHeight() && (O3 == null || O3.GetStrength() <= Strength))
            return true;
        else if(P4.GetY() >= 0 && (O4 == null || O4.GetStrength() <= Strength))
            return true;
        return false;
    }

    public int Collide(Organism AnotherOrganism)
    {
        if(AnotherOrganism instanceof Fox)
            Reproduce();
        else
            super.Collide(AnotherOrganism);
        return 0;
    }

    public void Reproduce()
    {
        Point ChildPosition = GetChildPosition();
        if(!ChildPosition.equals(Position))
        {
            new Fox(WorldToLive, ChildPosition);
            super.Reproduce();
        }
    }
}

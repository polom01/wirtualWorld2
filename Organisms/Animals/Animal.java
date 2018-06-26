package Organisms.Animals;

import DataStructure.Point;
import Organisms.Organism;
import Organisms.Plants.Plant;
import World.World;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Michal on 26.04.2017.
 */
public abstract class Animal extends Organism {
    protected int MoveDistance;
    public Animal(int Strength, int Initiative, World WorldToLive)
    {
        super(Strength, Initiative, WorldToLive);
        MoveDistance = 1;
    }
    public Animal(int Strength, int Initiative, World WorldToLive, Point P)
    {
        super(Strength, Initiative, WorldToLive, P);
        MoveDistance = 1;
    }
    public Animal(int Initiative, World WorldToLive, Scanner in)
    {
        super(Initiative, WorldToLive, in);
        MoveDistance = 1;
    }

    public int Act()
    {
        Random random = new Random();
        Point FuturePosition = Position;
        int Dir;
        Organism AnotherOrganism;
        boolean isSet = false;

        Point P1 = new Point(Position.GetX() + MoveDistance, Position.GetY());
        Point P2 = new Point(Position.GetX() - MoveDistance, Position.GetY());
        Point P3 = new Point(Position.GetX(), Position.GetY()+MoveDistance);
        Point P4 = new Point(Position.GetX(), Position.GetY()-MoveDistance);

        while(!isSet)
        {
            Dir = random.nextInt(4);
            switch(Dir)
            {
                case 0:
                    if(P2.GetX() >= 0)
                        FuturePosition = P2;
                    break;
                case 1:
                    if(P1.GetX() < WorldToLive.GetWidth())
                        FuturePosition = P1;
                    break;
                case 2:
                    if(P4.GetY() >= 0)
                        FuturePosition = P4;
                    break;
                case 3:
                    if(P3.GetY() < WorldToLive.GetHeight())
                        FuturePosition = P3;
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

    public int Collide(Organism AnotherOrganism)
    {
        if(AnotherOrganism instanceof Plant)
            Eat(AnotherOrganism);
        else if(!AnotherOrganism.DeflectedAttack(this) && !AnotherOrganism.RunAway(this))
            Fight(AnotherOrganism);
        return 0;
    }
    public void Reproduce()
    {
        WorldToLive.AddLog("Milosc rosnie wokol nas! Urodzilo sie nowe zwierzatko (" + this.getClass().getSimpleName() + ")");
    }
}

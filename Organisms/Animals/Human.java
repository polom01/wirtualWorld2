package Organisms.Animals;
import DataStructure.Point;
import Organisms.Organism;
import World.World;
import World.WorldField;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Michal on 04.05.2017.
 */
public class Human extends Animal{
    private int SuperPowerCoolDown;
    private boolean SuperPowerActive;
    private int SuperPowerTurnsLeft;
    private Point FuturePosition;
    public Human(World WorldToLive)
    {
        super(5, 4, WorldToLive);
        SuperPowerActive = false;
        SuperPowerCoolDown = 0;
        SuperPowerTurnsLeft = 0;
        Type = WorldField.HUMAN;
        FuturePosition = new Point(Position.GetX(), Position.GetY());
    }
    public Human(World WorldToLive, Scanner in)
    {
        super(4, WorldToLive, in);
        SuperPowerActive = in.nextBoolean();
        SuperPowerCoolDown = in.nextInt();
        SuperPowerTurnsLeft = in.nextInt();
        FuturePosition = new Point(Position.GetX(), Position.GetY());
        Type = WorldField.HUMAN;
    }
    public int Act()
    {
        Organism AnotherOrganism = WorldToLive.GetOrganimsQueue().Find(FuturePosition);
        if(AnotherOrganism != null && AnotherOrganism != this)
        {
            Collide(AnotherOrganism);
            if(AnotherOrganism.IsDead())
                Position = FuturePosition;
        }
        else
            Position = FuturePosition;
        if(!SuperPowerActive && SuperPowerCoolDown > 0)
            SuperPowerCoolDown--;
        else if(SuperPowerActive)
        {
            SuperPowerTurnsLeft--;
            Strength--;
        }
        if(SuperPowerActive && SuperPowerTurnsLeft == 0)
        {
            WorldToLive.AddLog("Moc specjalna (" + this.getClass().getSimpleName() + ") " + "przestala dzialac");
            SuperPowerActive = false;
            SuperPowerCoolDown = 5;
        }
        return 0;
    }
    public void Reproduce()
    {
        return;
    }
    public boolean Control(int Dir)
    {
        Point P1 = new Point(Position.GetX() + 1, Position.GetY());
        Point P2 = new Point(Position.GetX() - 1, Position.GetY());
        Point P3 = new Point(Position.GetX(), Position.GetY()+1);
        Point P4 = new Point(Position.GetX(), Position.GetY()-1);
        FuturePosition = new Point(Position.GetX(), Position.GetY());

        switch (Dir)
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
            default:
                return false;
        }
        if(Position.equals(FuturePosition))
            return false;
        else
            return true;
    }
    public boolean SuperPower()
    {
        if(SuperPowerActive || (!SuperPowerActive && SuperPowerCoolDown > 0))
            return false;
        else
        {
            WorldToLive.AddLog(this.getClass().getSimpleName() + " uzywa mocy specjalnej");
            FuturePosition = Position;
            SuperPowerActive = true;
            Strength += 5;
            SuperPowerTurnsLeft = 5;
            return true;
        }
    }
    public int GetSuperPowerCoolDown()
    {
        return SuperPowerCoolDown;
    }
    public int GetSuperPowerTurnsLeft()
    {
        return SuperPowerTurnsLeft;
    }
    public boolean IsSuperPowerActive()
    {
        return SuperPowerActive;
    }
    public void Save(PrintWriter save)
    {
        super.Save(save);
        save.println(SuperPowerActive);
        save.println(SuperPowerCoolDown);
        save.println(SuperPowerTurnsLeft);
    }
}


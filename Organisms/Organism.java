package Organisms;

import DataStructure.Point;
import World.World;
import World.WorldField;
import World.Direction;
import World.WorldFieldConverter;

import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.SortedMap;

/**
 * Created by Michal on 26.04.2017.
 */
public abstract class Organism {
    protected int Strength, Initative, Age;
    protected Boolean isDead;
    protected Boolean isTurnAllowed;
    protected Point Position;
    protected World WorldToLive;
    protected WorldField Type;
    public Organism(int Strength, int Initative, World WorldTolive)
    {
        this.Strength = Strength;
        this.Initative = Initative;
        this.WorldToLive = WorldTolive;
        Age = 0;
        isDead = false;
        isTurnAllowed = false;
        Position = new Point(0, 0);
        boolean ok = false;
        Random MyRandom = new Random();
        int x, y;
        while(!ok)
        {
            x = MyRandom.nextInt(WorldTolive.GetWidth());
            y = MyRandom.nextInt(WorldTolive.GetHeight());
            Position.SetY(y);
            Position.SetX(x);
            if(WorldTolive.GetOrganimsQueue().Find(Position) == null)
                ok = true;
        }
        this.WorldToLive.AddOrganismToWorld(this);
    }
    public Organism(int Strength, int Initative, World WorldToLive, Point P)
    {
        this.Strength = Strength;
        this.Initative = Initative;
        this.WorldToLive = WorldToLive;
        this.Position = P;
        Age = 0;
        isDead = false;
        isTurnAllowed = false;
        this.WorldToLive.AddOrganismToWorld(this);
    }
    public Organism(int Initiative, World WorldToLive, Scanner in)
    {
        this.Initative = Initiative;
        Strength = in.nextInt();
        int x = in.nextInt(), y = in.nextInt();
        Age = in.nextInt();
        Position = new Point(x, y);
        isDead = false;
        isTurnAllowed = false;
        this.WorldToLive = WorldToLive;
        this.WorldToLive.AddOrganismToWorld(this);
    }

    public void Draw()
    {
        WorldField[][] Map = WorldToLive.GetMap();
        Map[Position.GetX()][Position.GetY()] = Type;
    }
    public int GetStrength()
    {
        return Strength;
    }
    public int GetInitative()
    {
        return Initative;
    }
    public int GetAge()
    {
        return Age;
    }
    public Point GetPosition()
    {
        return Position;
    }
    public void GetOlder()
    {
        Age++;
    }
    public void Fight(Organism Enemy)
    {
        WorldToLive.AddLog(this.getClass().getSimpleName() + " walczy z " + Enemy.getClass().getSimpleName());
        if(Strength >= Enemy.GetStrength())
            Kill(Enemy);
        else
            Enemy.Kill(this);
    }
    public boolean DeflectedAttack(Organism Enemy)
    {
        return false;
    }
    public boolean RunAway(Organism Enemy)
    {
        return false;
    }
    public void Eat(Organism SomePlant)
    {
        WorldToLive.AddLog(this.getClass().getSimpleName() + " zjada " + SomePlant.getClass().getSimpleName());
        SomePlant.Collide(this);
        SomePlant.Die();
    }
    public void Buff(int BuffValue)
    {
        Strength += BuffValue;
        WorldToLive.AddLog(this.getClass().getSimpleName() + " zyskuje wiecej sily, akutalna sila: " + Strength);
    }
    //Save - dodac
    public Point GetChildPosition()
    {
        Random random = new Random();
        int Dir;
        Point ChildPosition;
        boolean isSet = false;
        Point P1 = new Point(Position.GetX() + 1, Position.GetY());
        Point P2 = new Point(Position.GetX() - 1, Position.GetY());
        Point P3 = new Point(Position.GetX(), Position.GetY()+1);
        Point P4 = new Point(Position.GetX(), Position.GetY()-1);

        Organism O1 = WorldToLive.GetOrganimsQueue().Find(P1);
        Organism O2 = WorldToLive.GetOrganimsQueue().Find(P2);
        Organism O3 = WorldToLive.GetOrganimsQueue().Find(P3);
        Organism O4 = WorldToLive.GetOrganimsQueue().Find(P4);

        if(!WorldToLive.IsEmptyNear(Position))
            return Position;
        while(!isSet)
        {
            Dir = random.nextInt(4);
            switch(Dir)
            {
                case 0:
                    if(P2.GetX() >= 0)
                    {
                        ChildPosition = P2;
                        if(O2 == null)
                            return P2;
                    }
                    break;
                case 1:
                    if(P1.GetX() < WorldToLive.GetWidth())
                    {
                        ChildPosition = P1;
                        if(O1 == null)
                            return P1;
                    }
                    break;
                case 2:
                    if(P4.GetY() >= 0)
                    {
                        ChildPosition = P4;
                        if(O4 == null)
                            return P4;
                    }
                    break;
                case 3:
                    if(P3.GetY() < WorldToLive.GetHeight())
                    {
                        ChildPosition = P3;
                        if(O3 == null)
                            return P3;
                    }
                    break;
            }
        }
        return Position;
    }
    public boolean IsDead()
    {
        return isDead;
    }
    protected void Die()
    {
        isDead = true;
    }
    protected void Kill(Organism AnotherOrganism)
    {
        WorldToLive.AddLog(this.getClass().getSimpleName() + " zabija " + AnotherOrganism.getClass().getSimpleName());
        AnotherOrganism.Die();
    }
    public void AllowMakingTurn()
    {
        isTurnAllowed = true;
    }
    public boolean IsTurnAllowed()
    {
        return isTurnAllowed;
    }
    public void Save(PrintWriter save)
    {
        save.println(WorldFieldConverter.Convert(Type));
        save.println(Strength);
        save.println(Position.GetX());
        save.println(Position.GetY());
        save.println(Age);
    }
    public abstract int Act();
    public abstract int Collide(Organism AnotherOrganism);
    public abstract void Reproduce();

}

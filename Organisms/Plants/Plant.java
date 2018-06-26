package Organisms.Plants;

import DataStructure.Point;
import Organisms.Organism;
import World.World;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Michal on 26.04.2017.
 */
public abstract class Plant extends Organism {
    protected int ReproduceProbability;
    public Plant(int Strength, World WorldToLive)
    {
        super(Strength, 0, WorldToLive);
        ReproduceProbability = 35;
    }
    public Plant(int Strength, World WorldToLive, Point P)
    {
        super(Strength, 0, WorldToLive, P);
        ReproduceProbability = 35;
    }
    public Plant(World WorldToLive, Scanner in)
    {
        super(0, WorldToLive, in);
        ReproduceProbability = 35;
    }
    public int Act()
    {
        Random random = new Random();
        int i = (random.nextInt() % 100) + 1;
        if(i <= ReproduceProbability)
            Reproduce();
        return 0;
    }
    public void Reproduce()
    {
        /*WorldToLive.AddLog(this.getClass().getSimpleName() + " rozsiewa sie");*/
    }
    public int Collide(Organism AnotherOrganism)
    {
        return 0;
    }
}

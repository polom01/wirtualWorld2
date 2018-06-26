package DataStructure;

import Organisms.Animals.Human;
import Organisms.Organism;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Michal on 26.04.2017.
 */
public class OrganismQueue {
    private int Size;
    private int Count;
    private ArrayList<Organism> Organisms;

    public OrganismQueue(int m, int n)
    {
        Size = m*n;
        Organisms = new ArrayList<>(Size);
       /* for(int i = 0; i < Size; i++)
            Organisms.get(i) = null;*/
        Count = 0;
    }
    public void Add(Organism LivingOrganism)
    {
        if(FindDead() != -1)
        {
           Organisms.remove(FindDead());
            Organisms.add(LivingOrganism);
        }
        else if(Count < Size)
        {
           Organisms.add(LivingOrganism);
           Count++;
        }
    }
    public Organism Get(int i)
    {
        if(i >= Count)
            return null;
        else
            return Organisms.get(i);
    }
    public void Sort()
    {
        if(Count == 0 || Count >= Size)
            return;
        for(int i = 0; i < Count; i++)
            for(int q = 0; q < Count -1; q++)
            {
                if(Organisms.get(q).GetInitative() < Organisms.get(q+1).GetInitative() ||
                        (Organisms.get(q).GetInitative() == Organisms.get(q+1).GetInitative() &&
                        Organisms.get(q).GetAge() < Organisms.get(q+1).GetAge()))
                {
                    Collections.swap(Organisms, i, q);
                }
            }
    }
    public int GetDeadCount()
    {
        int counter = 0;
        for(int i = 0; i < Count; i++)
            if(Organisms.get(i) != null && Organisms.get(i).IsDead())
                counter++;
        return counter;
    }
    public Organism Find(Point P)
    {
        for(int i = 0; i < Organisms.size(); i++)
            if(!Organisms.get(i).IsDead() && Organisms.get(i).GetPosition().equals(P))
                return Organisms.get(i);
        return null;
    }
    public int GetCount()
    {
        return Count;
    }

    private int FindDead()
    {
        for(int i = 0; i < Organisms.size(); i++)
            if(Organisms.get(i).IsDead() &&!(Organisms.get(i) instanceof Human))
                return i;
        return -1;
    }
}

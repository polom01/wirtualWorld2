package World;

import DataStructure.OrganismQueue;
import DataStructure.Point;
import Organisms.Animals.*;
import Organisms.Organism;
import Organisms.Plants.*;
import Window.MousePanel;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Michal on 26.04.2017.
 */
public class World {

    private WorldField[][] Map;
    private OrganismQueue Organisms;
    private int Width, Height, Turn;
    private LinkedList<String> Logs;

    public World(int Width, int Height)
    {
        this.Width = Width;
        this.Height = Height;
        Logs = new LinkedList<>();
        Map = new WorldField[Width][Height];
        Organisms = new OrganismQueue(Width, Height);
        Turn = 0;
    }

    public void MakeTurn()
    {
        Organisms.Sort();
        for(int i = 0; i < Organisms.GetCount(); i++)
            Organisms.Get(i).AllowMakingTurn();

        for(int i =0; i < Organisms.GetCount(); i++)
            if(!Organisms.Get(i).IsDead() && Organisms.Get(i).IsTurnAllowed())
            {
                Organisms.Get(i).Act();
                Organisms.Get(i).GetOlder();
            }
        Turn++;
    }
    public void Draw(MousePanel m, Graphics2D g)
    {
        for(int x = 0; x < Width; x++)
            for(int y = 0; y < Height; y++)
                Map[x][y] = WorldField.EMPTY;
        for(int i = 0; i < Organisms.GetCount(); i++)
            if(!Organisms.Get(i).IsDead())
                Organisms.Get(i).Draw();
        for(int x = 0; x < Width; x++)
            for(int y = 0; y < Height; y++)
                m.DrawRectangle(g, x, y, this.ParseWorldField(Map[x][y]));

    }
    private Color ParseWorldField(WorldField field)
    {
        switch (field)
        {
            case SOW_THISTLE: return Color.YELLOW;
            case SOSNOWSKY_HOGWEED: return Color.RED;
            case BELLADONA: return Color.PINK;
            case ANTELOPE: return Color.CYAN;
            case TURTLE: return Color.BLUE;
            case GUARANA: return Color.MAGENTA;
            case SHEEP: return Color.GRAY;
            case HUMAN: return Color.BLACK;
            case GRASS: return Color.GREEN;
            case WOLF: return Color.DARK_GRAY;
            case FOX: return Color.ORANGE;
            default: return Color.WHITE;
        }
    }
    public void AddOrganismToWorld(Organism LivingOrganism)
    {
        Organisms.Add(LivingOrganism);
    }
    public int GetWidth()
    {
        return Width;
    }

    public int GetHeight()
    {
        return Height;
    }
    public WorldField[][] GetMap()
    {
        return Map;
    }
    public OrganismQueue GetOrganimsQueue()
    {
        return Organisms;
    }
    public boolean IsEmptyNear(Point P)
    {
        Point P1 = new Point(P.GetX() + 1, P.GetY());
        Point P2 = new Point(P.GetX() - 1, P.GetY());
        Point P3 = new Point(P.GetX(), P.GetY()+1);
        Point P4 = new Point(P.GetX(), P.GetY()-1);
        if(P1.GetX() < Width && Organisms.Find(P1) == null)
            return true;
        else if(P2.GetX() >= 0 && Organisms.Find(P2) == null)
            return true;
        else if(P3.GetY() < Height && Organisms.Find(P3) == null)
            return true;
        else if(P4.GetY() >= 0 && Organisms.Find(P4) == null)
            return true;
        return false;

    }

    public void AddLog(String Log)
    {
       // Logs.add(Log);
        System.out.println(Log);
    }

    public void Save() throws FileNotFoundException
    {
        PrintWriter save = new PrintWriter("zapis.txt");
        save.println(Width);
        save.println(Height);
        save.println(Turn);
        save.println(Organisms.GetCount()/* - Organisms.GetDeadCount()*/);
        for(int i = 0; i < Organisms.GetCount(); i++)
            if(Organisms.Get(i) != null && !Organisms.Get(i).IsDead())
                Organisms.Get(i).Save(save);
        save.close();
    }
    public Organism Load() throws FileNotFoundException
    {
        int species, count;
        Organism czlowiek = null;
        Scanner in = new Scanner(new File("zapis.txt"));
        Width = in.nextInt();
        Height = in.nextInt();
        Turn = in.nextInt();
        count = in.nextInt();
        Map = new WorldField[Width][Height];
        Organisms = new OrganismQueue(Width, Height);
        for(int i =0; i < count; i++)
        {
            species = in.nextInt();
            switch (WorldFieldConverter.Convert(species))
            {
                case ANTELOPE:
                    new Antelope(this, in); break;
                case BELLADONA:
                    new Belladona(this, in); break;
                case FOX:
                    new Fox(this, in); break;
                case GRASS:
                    new Grass(this, in); break;
                case GUARANA:
                   new Guarana(this, in); break;
                case HUMAN:
                    czlowiek = new Human(this, in); break;
                case SHEEP:
                    new Sheep(this, in); break;
                case SOSNOWSKY_HOGWEED:
                   new SosnowskyHogweed(this, in); break;
                case SOW_THISTLE:
                    new SowThistle(this, in); break;
                case TURTLE:
                    new Turtle(this, in); break;
                case WOLF:
                    new Wolf(this, in); break;
            }
        }
        in.close();
        return czlowiek;
    }
}

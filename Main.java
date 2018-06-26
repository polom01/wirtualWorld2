import java.awt.*;
import java.util.concurrent.TimeUnit;

import Organisms.Animals.*;
import Organisms.Plants.*;
import Window.Frame;
import World.World;
import javax.swing.*;

public class Main {

    private static Frame f = null;
    private static World WorldToLive = null;
    private static JLabel l = null;
    private static Human c;
    private static void init()
    {
        JFrame parent = new JFrame();
        parent.pack();
        parent.setVisible(true);
        String w = JOptionPane.showInputDialog(parent,"Szerokosc planszy", null);
        String h = JOptionPane.showInputDialog(parent,"Wysokosc planszy", null);
        parent.setVisible(false);
        parent.dispose();
        l = new JLabel();
        WorldToLive = new World(Integer.parseInt(w), Integer.parseInt(h));
        c = new Human(WorldToLive);
        f = new Frame(Integer.parseInt(w), Integer.parseInt(h), WorldToLive, l, c);
    }
    public static void main(String[] args)
    {

        init();

        new Antelope(WorldToLive);
        new Antelope(WorldToLive);
        new Antelope(WorldToLive);
        new Antelope(WorldToLive);
        new Sheep(WorldToLive);
        new Sheep(WorldToLive);
        new Sheep(WorldToLive);
        new Sheep(WorldToLive);
        new Turtle(WorldToLive);
        new Turtle(WorldToLive);
        new Turtle(WorldToLive);
        new Turtle(WorldToLive);
        new Fox(WorldToLive);
        new Fox(WorldToLive);
        new Fox(WorldToLive);
        new Fox(WorldToLive);
        new Wolf(WorldToLive);
        new Wolf(WorldToLive);
        new Wolf(WorldToLive);
        new Grass(WorldToLive);
        new Grass(WorldToLive);
        new SowThistle(WorldToLive);
        new SowThistle(WorldToLive);
        new SowThistle(WorldToLive);
        new SosnowskyHogweed(WorldToLive);
        new SosnowskyHogweed(WorldToLive);
        new SosnowskyHogweed(WorldToLive);
        new Belladona(WorldToLive);
        new Belladona(WorldToLive);
        new Belladona(WorldToLive);
        new Guarana(WorldToLive);
        new Guarana(WorldToLive);
        new Guarana(WorldToLive);
        while(true)
        {
            l.setText("Sila: " + c.GetStrength() + " Odnowienie " + c.GetSuperPowerCoolDown() + " Aktywna przez " + c.GetSuperPowerTurnsLeft());
            c = f.GetHumanAfterLoad();
            while(!f.ok());
            f.setOkToFalse();
            WorldToLive.MakeTurn();
            try {
                TimeUnit.SECONDS.sleep(2);
            }
            catch (InterruptedException ex)
            {

            }

        }
    }
}
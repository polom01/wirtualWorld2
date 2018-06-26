package Window;

import Organisms.Animals.*;
import Organisms.Plants.*;
import DataStructure.Point;
import Organisms.Animals.Sheep;
import World.World;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Michal on 13.05.2017.
 */
public class MousePanel extends JPanel implements MouseListener, KeyListener {
    private final int Width, Height;
    public MousePanel(int Width, int Height, World WorldToDraw, Human h)
    {
        this.h = h;
        this.Height = Height;
        this.Width = Width;
        this.WorldToDraw = WorldToDraw;
        addKeyListener(this);
        addMouseListener(this);
        setPreferredSize(new Dimension(Width, Height));
    }

    public void mouseClicked(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
    }

    public void mousePressed(MouseEvent e)
    {
        Object[] possibilities = {"Antylopa", "Lis", "Owca", "Wilk", "Zolw",
        "Wilcza Jagoda", "Trawa", "Guarana", "Barszcz", "Mlecz"};
        JFrame parent = new JFrame();
        ImageIcon icon = new ImageIcon();
        Point p = new Point((int)(e.getX()/10), (int)(e.getY()/10));
        String s = (String)JOptionPane.showInputDialog(parent, "Typ organizmu do utworzenia: \n",
                "Customized Dialog", JOptionPane.PLAIN_MESSAGE, icon, possibilities, "Antylopa");
        switch(s)
        {
            case "Antylopa":
                new Antelope(WorldToDraw, p); break;
            case "Lis":
                new Fox(WorldToDraw, p); break;
            case "Owca":
                new Sheep(WorldToDraw, p); break;
            case "Wilk":
                new Wolf(WorldToDraw, p); break;
            case "Zolw":
                new Turtle(WorldToDraw, p); break;
            case "Wilcza Jagoda":
                new Belladona(WorldToDraw, p); break;
            case "Trawa":
                new Grass(WorldToDraw, p); break;
            case "Guarana":
                new Guarana(WorldToDraw, p); break;
            case "Barszcz":
                new SosnowskyHogweed(WorldToDraw, p); break;
            case "Mlecz":
                new SowThistle(WorldToDraw, p); break;
        }
    }

    public void mouseReleased(MouseEvent e)
    {
    }
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, Width, Height);
        WorldToDraw.Draw(this, g2d);
        validate();
        repaint();
    }
    public void DrawRectangle(Graphics2D g ,int x, int y, Color c)
    {
        g.setColor(c);
        g.fillRect(x*10, y*10, 10, 10);
    }
    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                Ok = true;
                h.Control(2);
                return;
            case KeyEvent.VK_DOWN:
                Ok = true;
                h.Control(3);
                return;
            case KeyEvent.VK_LEFT:
                Ok = true;
                h.Control(0);
                return;
            case KeyEvent.VK_RIGHT:
                Ok = true;
                h.Control(1);
                return;
                default: Ok = true;
        }
    }
    public void keyTyped(KeyEvent e)
    {

    }
    private boolean Ok = false;
    public void keyPressed(KeyEvent e) {

    }
    public void SetHumanAfterLoading(Human hum)
    {
        h = hum;
    }
    public boolean ok()
    {
        return Ok;
    }
    public void setOkToFalse()
    {
        Ok = false;
    }
    private Human h;
    private final World WorldToDraw;
}

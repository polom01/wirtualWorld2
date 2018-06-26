package Window;

import Organisms.Animals.Human;
import Organisms.Organism;
import World.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

/**
 * Created by Michal on 12.05.2017.
 */
public class Frame extends JFrame{
    public Frame(int Width, int Height, World w, JLabel l, Human h)
    {
        super("Michal Krakowiak 165596");
        hum = h;
        setLayout(new BorderLayout());
        getRootPane().setSize(900, 900);
        JPanel menu = new JPanel();
        JButton a = new JButton("Zapisz");
        JButton b = new JButton("Wczytaj");
        JButton c = new JButton("SuperMoc");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.add(l, BorderLayout.PAGE_START);
        menu.add(l, BorderLayout.PAGE_START);
        menu.add(a,  BorderLayout.LINE_START);
        menu.add(b, BorderLayout.CENTER);
        menu.add(c,BorderLayout.LINE_END);
        a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {w.Save();}
                catch (FileNotFoundException q) {}
            }
        });
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    hum = w.Load();
                    m.SetHumanAfterLoading((Human)hum);
                }
                catch (FileNotFoundException q) {}
            }
        });
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                h.SuperPower();
            }
        });
        m = new MousePanel(Width*10, Height*10, w, h);
        add(m, BorderLayout.CENTER);
        add(menu, BorderLayout.NORTH);
        pack();
        setVisible(true);
    }
    public void setOkToFalse()
    {
        m.setOkToFalse();
    }
    public boolean ok()
    {
        m.setFocusable(true);
        m.requestFocusInWindow();
        return m.ok();
    }
    public Human GetHumanAfterLoad()
    {
        return (Human) hum;
    }
    private MousePanel m;
    private Organism hum;
}

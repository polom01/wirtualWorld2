package DataStructure;

/**
 * Created by Michal on 26.04.2017.
 */
public class Point {
    private int x, y;
    public Point()
    {
        x = 0;
        y = 0;
    }
    public Point(final Point P)
    {
        x = P.GetX();
        y = P.GetY();
    }
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public int GetX()
    {
        return x;
    }
    public boolean equals(Point P)
    {
        if(P.GetX() == x && P.GetY() == y)
            return true;
        else
            return false;
    }
    public int GetY()
    {
        return y;
    }
    public void SetX(int x)
    {
        this.x = x;
    }
    public void SetY(int y)
    {
        this.y = y;
    }
}

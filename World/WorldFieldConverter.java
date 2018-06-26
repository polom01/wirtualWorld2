package World;

/**
 * Created by Michal on 12.05.2017.
 */
public class WorldFieldConverter {
    public static int Convert(WorldField Field)
    {
        switch(Field)
        {
            case FOX: return 0;
            case WOLF: return 1;
            case EMPTY: return 2;
            case GRASS: return 3;
            case HUMAN: return 4;
            case SHEEP: return 5;
            case TURTLE: return 6;
            case GUARANA: return 7;
            case ANTELOPE: return 8;
            case BELLADONA: return 9;
            case CYBER_SHEEP: return 10;
            case SOW_THISTLE: return 11;
            case SOSNOWSKY_HOGWEED: return 12;
        }
        return 2;
    }
    public static WorldField Convert(int Field)
    {
        switch(Field)
        {
            case 0: return WorldField.FOX;
            case 1: return WorldField.WOLF;
            case 2: return WorldField.EMPTY;
            case 3: return WorldField.GRASS;
            case 4: return WorldField.HUMAN;
            case 5: return WorldField.SHEEP;
            case 6: return WorldField.TURTLE;
            case 7: return WorldField.GUARANA;
            case 8: return WorldField.ANTELOPE;
            case 9: return WorldField.BELLADONA;
            case 10: return WorldField.CYBER_SHEEP;
            case 11: return WorldField.SOW_THISTLE;
            case 12: return WorldField.SOSNOWSKY_HOGWEED;
        }
        return WorldField.EMPTY;
    }
}

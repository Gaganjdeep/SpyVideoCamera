package ggn.ameba.spycam.utills;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gagandeep on 31 May 2016.
 */
public enum Background
{
    IMAGE(1),
    VIDEO(2),
    AUDIO(3);

    int value;

    Background(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

    private static Map<Integer, Background> map = new HashMap<Integer, Background>();

    static
    {
        for (Background gallery : Background.values())
        {
            map.put(gallery.value, gallery);
        }
    }


    public static Background valueOf(int legNo)
    {
        return map.get(legNo);
    }


}
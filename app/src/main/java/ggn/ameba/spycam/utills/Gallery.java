package ggn.ameba.spycam.utills;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gagandeep on 26 May 2016.
 */
public enum Gallery
{
    IMAGE_CLICK(11),
    VIDEO_CLICK(21),

    IMAGE_VIDEO(12),
    VIDEO_VIDEO(22);

    int value;

    Gallery(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }


    private static Map<Integer, Gallery> map = new HashMap<Integer, Gallery>();

    static
    {
        for (Gallery gallery : Gallery.values())
        {
            map.put(gallery.value, gallery);
        }
    }


    public static Gallery valueOf(int legNo)
    {
        return map.get(legNo);
    }


}
import java.io.File;
import java.lang.reflect.Field;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;

public class Start
{
    public static void main(String[] args)
    {
        try
        {
            Field f = Minecraft.class.getDeclaredField("minecraftDir");
            Field.setAccessible(new Field[] { f }, true);
            f.set(null, new File("."));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }

        Minecraft.main(args);
    }
    static {
    	ImageIO.setUseCache(false);
    	try {
    		System.loadLibrary("OpenAL64");
    	} catch(Throwable t) {
    		try {
        		System.loadLibrary("OpenAL32");
        	} catch(Throwable t2) {
        		
        	}
    	}
    }
}

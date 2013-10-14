package examples.Box2D;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Starter {

    /**
     * Launcher for desktop
     * @param args
     */

    public static void main(String[] args){
        LwjglApplicationConfiguration appConfig = new LwjglApplicationConfiguration();
        // Platform configuration - this one is for lwjgl (desktop)
        appConfig.useGL20 = true;   //Use OpenGL 2.0 - need it atm.

        new LwjglApplication(new Box2dTest(), appConfig);   //finally create the lwjgl application.
    }
}

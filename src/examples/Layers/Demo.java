package examples.Layers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.jemge.core.JApp;
import com.jemge.core.JConfig;
import com.jemge.core.JGame;
import com.jemge.core.Jemge;
import com.jemge.j2d.JSprite;
import com.jemge.j2d.Layer;

public class Demo extends JGame {
    @Override
    public void create() {
        final Texture background = new Texture(Gdx.files.internal("texture.png"));
        final Texture player = new Texture(Gdx.files.internal("player.png"));
        final Texture hud = new Texture(Gdx.files.internal("hud.png"));


        Layer backgroundLayer = Jemge.renderer2D.addLayer(new Layer(), 0);
        backgroundLayer.addObject(new JSprite(background));

        Layer playerLayer = Jemge.renderer2D.addLayer(new Layer(), 1);
        playerLayer.addObject(new JSprite(player));

        Layer hudLayer = Jemge.renderer2D.addLayer(new Layer(), 2);
        hudLayer.addObject(new JSprite(hud));
    }

    public static void main(String[] args) {
        JConfig config = new JConfig();

        config.setTitle("Hello World");
        config.setGL(JConfig.Version.GL_20);
        config.setFullscreen();

        new JApp(new Demo(), config);
    }
}

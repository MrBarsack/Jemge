/*
 * Copyright [2013] @author file
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.jemge.j2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Disposable;

import java.util.HashMap;

/**
 * The renderer class. The rendering is done automatically, you just have to add the objects.
 *
 * @author MrBarsack
 * @see RendererObject
 */

public class Renderer2D implements Disposable {

    //Private

    private static Renderer2D renderer2D;

    private final HashMap<Integer, Layer> renderTargets;
    private final SpriteBatch spriteBatch;
    private final OrthographicCamera camera;

    private RenderMode renderMode;

    //Protected

    protected final Rectangle cameraView;

    public enum RenderMode {
        INACTIVE, ENABLED, DISABLED
    }


    public Renderer2D() {
        renderer2D = this;

        renderTargets = new HashMap<Integer, Layer>();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        cameraView = new Rectangle(0, 0, camera.viewportWidth, camera.viewportHeight);
        spriteBatch = new SpriteBatch();

        renderTargets.put(0, new Layer());
    }

    //Public

    public Layer addLayer(Layer layer, int num){
        renderTargets.put(num, layer);

        return layer;
    }

    public void deleteLayer(int num){
        renderTargets.remove(num);
    }

    /**
     * Adds a new object to the renderer.
     */

    public RendererObject add(int layer, RendererObject rendererObject) {
        renderTargets.get(layer).addObject(rendererObject);

        return rendererObject;
    }

    /**
     * Deletes an object from the renderer.
     */


    public void remove(int layer, RendererObject rendererObject) {
        renderTargets.get(layer).deleteObject(rendererObject);

    }

    /**
     * Adds a new object to the renderer.
     */

    public RendererObject add(RendererObject rendererObject) {
        renderTargets.get(0).addObject(rendererObject);

        return rendererObject;
    }

    /**
     * Deletes an object from the renderer.
     */


    public void remove(RendererObject rendererObject) {
        renderTargets.get(0).deleteObject(rendererObject);
    }

    /**
     * Draws all objects from the render list.
     */

    public void render() {

        Gdx.gl20.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
      /*  cameraView.setCenter(camera.position.x,
                camera.position.y);
*/
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();

        renderMode = RenderMode.INACTIVE;

        for (Layer layer : renderTargets.values()) {

            for (RendererObject rend : layer.getRendererObjects()) {
                if (!rend.needRender()) continue;

                if (rend.hasTransparent() && !(renderMode == RenderMode.ENABLED)) {    //with blending
                    spriteBatch.enableBlending();

                    renderMode = RenderMode.ENABLED;
                } else if (!rend.hasTransparent() && !(renderMode == RenderMode.DISABLED)) {  //without blending
                    spriteBatch.disableBlending();

                    renderMode = RenderMode.DISABLED;
                }
                rend.render(spriteBatch);

            }
        }
        spriteBatch.end();
    }

    public void resize(int width, int height) {
      //  camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

    }

    /**
     * Must be called before exiting.
     */

    @Override
    public void dispose() {
        spriteBatch.dispose();

        for (Layer layer : renderTargets.values()) {
            for (RendererObject rend : layer.getRendererObjects()) {
                rend.dispose();
            }
        }
    }

    /**
     * @return Returns an instance of the renderer.
     */

    public static Renderer2D getRenderer2D() {
        if (renderer2D == null) {
            new Renderer2D();
        }

        return renderer2D;
    }

    /**
     * @return Returns an instance of the camera.
     */

    public Camera getCamera() {
        return camera;
    }


}

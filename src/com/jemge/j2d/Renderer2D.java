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
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;
import java.util.List;

public class Renderer2D implements Disposable {
    private static Renderer2D renderer2D;

    private List<RendererObject> renderTargets;
    private SpriteBatch spriteBatch;
    private OrthographicCamera camera;

    private RenderMode renderMode;


    public enum RenderMode {

        INACTIVE, ENABLED, DISABLED
    }


    public Renderer2D() {
        renderer2D = this;
        renderTargets = new ArrayList<RendererObject>();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        spriteBatch = new SpriteBatch();
    }

    //Public

    /**
     * Adds a new object to the renderer.
     */

    public RendererObject add(RendererObject rendererObject) {
        renderTargets.add(rendererObject);

        return rendererObject;
    }

    /**
     * Deletes an object from the renderer.
     */


    public void remove(RendererObject rendererObject) {
        renderTargets.remove(rendererObject);
    }

    /**
     * Draws all objects from the render list.
     */

    public void render() {
        Gdx.gl20.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        spriteBatch.setProjectionMatrix(camera.combined);

        spriteBatch.begin();

        renderMode = RenderMode.INACTIVE;

        for (RendererObject rend : renderTargets) {

            if(!needRender(rend.getRectangle())) continue;

            if (rend.hasTransparent() && !(renderMode == RenderMode.ENABLED)) {
                spriteBatch.enableBlending();

                renderMode = RenderMode.ENABLED;
            }

            if (!rend.hasTransparent() && !(renderMode == RenderMode.DISABLED)) {
                spriteBatch.disableBlending();

                renderMode = RenderMode.DISABLED;
            }
            rend.render(spriteBatch);

        }

        spriteBatch.end();
    }

    /**
     * Must be called before exiting.
     */

    @Override
    public void dispose() {
        spriteBatch.dispose();

        for (RendererObject rend : renderTargets) {
            rend.dispose();
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

    //Private:

    private boolean needRender(Rectangle rectangle)
    {
        if(rectangle.height == rectangle.width)
        {
            return camera.frustum.sphereInFrustum(new Vector3(rectangle.x,rectangle.y, 0), rectangle.width);
        }

        Vector3 point;
        //Bottom left

        point = new Vector3(rectangle.x - rectangle.width / 2, rectangle.y - rectangle.height / 2, 0);
        if(camera.frustum.pointInFrustum(point)) return true;

        //Top left
        point.set(rectangle.x - rectangle.width / 2, rectangle.y + rectangle.height / 2, 0);
        if(camera.frustum.pointInFrustum(point)) return true;

        //Bottom Right

        point.set(rectangle.x + rectangle.width / 2, rectangle.y - rectangle.height / 2, 0);
        if(camera.frustum.pointInFrustum(point)) return true;

        //Top Right

        point.set(rectangle.x + rectangle.width / 2, rectangle.y + rectangle.height / 2, 0);
        if(camera.frustum.pointInFrustum(point)) return true;

        //Not inside the camera view == don't have to render it.
        return false;
    }

}

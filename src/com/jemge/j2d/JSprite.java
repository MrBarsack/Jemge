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


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jemge.core.Jemge;

/**
 * Default object for drawing textures.
 *
 * @see RendererObject
 */

public class JSprite extends Sprite implements RendererObject {

    //Private
    private boolean transparent;

    public JSprite() {
    }

    public JSprite(Texture texture) {
        super(texture);
    }

    public JSprite(Texture texture, float width, float height) {
        super(texture);
        setSize(width, height);
    }

    public JSprite(Texture texture, float x, float y, float width, float height) {
        super(texture);
        setBounds(x, y, width, height);

    }

    public JSprite(TextureRegion region) {
        super(region);
    }

    public JSprite(JSprite sprite) {
        super(sprite);
    }

    /**
     * @return Is this jsprite transparent?
     */

    @Override
    public boolean hasTransparent() {
        return transparent;
    }


    /**
     * Render this jsprite
     */

    @Override
    public void render(SpriteBatch spriteBatch) {
        if (needRender()) {
            super.draw(spriteBatch);
        }

    }

    /**
     * Dispose the native
     */
    @Override
    public void dispose() {
        if (getTexture() == null) return;

        getTexture().dispose();
    }

    private boolean needRender() {
        //Inside the camera view?
        return Jemge.renderer2D.cameraView.overlaps(getBoundingRectangle());
    }

}

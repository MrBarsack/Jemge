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


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Default object for drawing textures.
 *
 * @see RendererObject
 */

public class JSprite extends Sprite implements RendererObject {
    private boolean transparent;

    /**
     * @return Is this bsprite transparent?
     */

    @Override
    public boolean hasTransparent() {
        return transparent;
    }


    /**
     * Render this bsprite
     */

    @Override
    public void render(SpriteBatch spriteBatch) {

        super.draw(spriteBatch);
    }

    /**
     * Dispose the native
     */

    public void dispose() {
        getTexture().dispose();
    }


}

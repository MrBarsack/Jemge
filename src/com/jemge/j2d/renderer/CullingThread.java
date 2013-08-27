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

package com.jemge.j2d.renderer;


import com.jemge.j2d.RendererObject;

import java.util.ArrayList;
import java.util.List;

public class CullingThread extends Thread implements Runnable {

    private List<RendererObject> renderList;

    protected List<RendererObject> finalRenderList;


    public CullingThread(List<RendererObject> rendererObjects) {
        super("Culling Thread");
        renderList = rendererObjects;
        finalRenderList = new ArrayList<RendererObject>();

        setDaemon(true);
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            for (RendererObject rend : renderList) {
                if (rend.needRender()) {
                    finalRenderList.add(rend);
                }
            }


            synchronized (this) {
                notifyAll();

                try {
                    wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finalRenderList.clear();
            }

        }
    }

    public synchronized List<RendererObject> getFinalRenderList() {
        return finalRenderList;
    }

}

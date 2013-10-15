package com.jemge.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.files.FileHandle;

public class JConfig extends LwjglApplicationConfiguration {
    public enum Version {
        GL_20
    }

    ;

    public JConfig() {
        this.title = "JEMGEngine";
    }

    public void setGL(Version gl_version) {
        switch (gl_version) {
            case GL_20:
                this.useGL20 = true;
                break;
        }
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setFullscreen() {
        this.fullscreen = true;
    }

    public void loadSettings(String file) {
        try {
            FileHandle handle = Gdx.files.absolute(file);
            System.out.println(handle);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //this.useCPUSynch = true;
        //this.vSyncEnabled = true;
    }
}

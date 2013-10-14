package com.jemge.box2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class PolygonObject {
    private final Body body;
    private final Fixture fixture;

    private float height;
    private float width;
    private Vector2 position;


    public PolygonObject(float x, float y, float w, float h, BodyDef.BodyType type){
        this.height = h;
        this.width = w;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = type;
        bodyDef.position.set(x, y).add(width / 2, height / 2);

        body = Physics2D.getMainWorld().createBody(bodyDef);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(w / 2, h / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 1.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.1f;

        fixture = body.createFixture(fixtureDef);

        polygonShape.dispose();
    }

    public PolygonObject setPhysicData(float density, float friction, float restitution){
        fixture.setDensity(density);
        fixture.setFriction(friction);
        fixture.setRestitution(restitution);

        return this;
    }

    public Vector2 getPosition(){
        position = body.getPosition();
        position.sub(width / 2, height / 2);

        return position;
    }

    public float getHeight(){
        return height;
    }

    public float getWidth(){
        return width;
    }

}

package com.jemge.box2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class PolygonObject extends Physic2DObject {

    public PolygonObject(float x, float y, float w, float h, BodyDef.BodyType type) {
        super(x, y, w, h, type);

        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(w / 2, h / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 1.5f;
        fixtureDef.friction = 1.4f;
        fixtureDef.restitution = 0f;

        fixture = body.createFixture(fixtureDef);

        polygonShape.dispose();
    }
}

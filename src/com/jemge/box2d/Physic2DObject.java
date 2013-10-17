package com.jemge.box2d;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;

class Physic2DObject {
    protected final Body body;
    protected Fixture fixture;

    private float height;
    private float width;
    private Vector2 position;


    public Physic2DObject(float x, float y, float w, float h, BodyDef.BodyType type) {
        this.height = h;
        this.width = w;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = type;
        bodyDef.position.set(x, y).add(width / 2, height / 2);

        body = Physics2D.getMainWorld().createBody(bodyDef);

    }

    public Physic2DObject setPhysicData(float density, float friction, float restitution) {
        fixture.setDensity(density);
        fixture.setFriction(friction);
        fixture.setRestitution(restitution);

        return this;
    }

    public Body getBody(){
        return body;
    }

    public void setBodyType(BodyDef.BodyType type) {
        body.setType(type);
    }

    public void applyForce(Vector2 force, boolean wake) {
        body.applyForceToCenter(force, wake);
    }

    public void setVelocity(Vector2 velocity) {
        body.setLinearVelocity(velocity);
    }

    public Vector2 getPosition() {
        position = fixture.getBody().getPosition();
        position.sub(width / 2, height / 2);

        return position;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Physic2DObject)) return false;

        Physic2DObject that = (Physic2DObject) o;

        return Float.compare(that.height, height) == 0 &&
                Float.compare(that.width, width) == 0 &&
                (!position.equals(that.position)) &&
                (!body.equals(that.body));
    }

    @Override
    public int hashCode() {
        int result = (height != +0.0f ? Float.floatToIntBits(height) : 0);
        result = 31 * result + (width != +0.0f ? Float.floatToIntBits(width) : 0);
        result = 31 * result + position.hashCode();
        result = 31 * result + body.hashCode();
        return result;
    }
}

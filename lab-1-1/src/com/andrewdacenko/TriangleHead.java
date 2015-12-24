package com.andrewdacenko;

import org.json.JSONArray;
import org.json.JSONException;

public class TriangleHead {
    Double x;
    Double y;

    public TriangleHead(JSONArray head) throws JSONException {
        this.x = head.getDouble(0);
        this.y = head.getDouble(1);
    }

    @Override public String toString() {
        return "TriangleHead: x=" + x + ",y=" + y;
    }
}

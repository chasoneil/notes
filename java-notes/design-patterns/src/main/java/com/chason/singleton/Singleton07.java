package com.chason.singleton;

import java.io.Serializable;

/**
 * got singleton by enum
 *
 * enum was also class type
 *
 * enum can solve reflect destroy singleton and serialize destroy singleton
 *
 */
public enum Singleton07 implements Serializable {

    INSTANCE;

    // we can add some fields for enum class
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Singleton07 getInstance() {
        return INSTANCE;
    }

}

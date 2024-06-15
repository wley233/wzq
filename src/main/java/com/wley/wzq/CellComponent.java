package com.wley.wzq;

import com.almasb.fxgl.entity.component.Component;

public class CellComponent extends Component {
    private int x;
    private int y;

    public CellComponent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void onUpdate(double tpf) {

    }
}

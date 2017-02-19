package kr.sunrin.stellapoly.game.singleplay.material;

import kr.sunrin.stellapoly.framework.Graphics;
import kr.sunrin.stellapoly.game.etc.Def;

/**
 * Created by 건후 on 2015-08-24.
 */
public class Material {
    public int type;
    public int w, h;  //화면 크기
    public int cx,cy;

    Material() {
        w = Def.w;
        h = Def.h;
    }

    public void paint(Graphics g) {
    }

    public void update() {
    }
}

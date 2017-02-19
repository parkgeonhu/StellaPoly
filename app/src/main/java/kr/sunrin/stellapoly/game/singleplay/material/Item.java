package kr.sunrin.stellapoly.game.singleplay.material;

import java.util.Random;

import kr.sunrin.stellapoly.framework.Graphics;
import kr.sunrin.stellapoly.framework.Image;
import kr.sunrin.stellapoly.game.etc.Assets;

/**
 * Created by 건후 on 2015-08-26.
 */
public class Item extends Material {
    Random random;
    public int itemType;
    int movetype;

    int sx, sy; //적 초기 위치 좌표

    Image item;
    MaterialMove path;

    Item() {
        super.type = 0;
        random = new Random();
        itemType = random.nextInt(3);

        setPos(); //캐릭터 좌표 랜덤 설정

        //스프라이트 설정
        if (itemType == 0) {
            item = Assets.item1;
            System.out.println("아이템 1 생성");
        } else if (itemType == 1) {
            item = Assets.item2;
            System.out.println("아이템 2 생성");
        } else {
            item = Assets.item3;
            System.out.println("아이템 3 생성");
        }
    }

    public void setPos() {
        movetype = random.nextInt(4); //랜덤으로 시계방향 위치에서 나타나게 하기 0-위쪽, 1-오른쪽, 2-아래쪽, 3-왼쪽
        if (movetype == 0) {
            sx = random.nextInt(w);
            sy = 0;
            System.out.println("0 : " + sx + " " + sy);
        } else if (movetype == 1) {
            sx = w;
            sy = random.nextInt(h);
            System.out.println("1 : " + sx + " " + sy);
        } else if (movetype == 2) {
            sx = random.nextInt(w);
            sy = h - 40;
            System.out.println("2 : " + sx + " " + sy);
        } else {
            sx = 0;
            sy = random.nextInt(h);
            System.out.println("3 : " + sx + " " + sy);
        }
        path = new MaterialMove(item, sx, sy, w / 2, h / 2);
    }

    public void update() {
        path.update();
        cx = (int) (path.getX());
        cy = (int) (path.getY());
    }

    public void paint(Graphics g){
        g.drawImage(item,cx,cy);
    }
}

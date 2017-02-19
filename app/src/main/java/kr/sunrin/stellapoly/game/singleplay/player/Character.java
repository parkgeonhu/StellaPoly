package kr.sunrin.stellapoly.game.singleplay.player;

import kr.sunrin.stellapoly.framework.Graphics;
import kr.sunrin.stellapoly.game.etc.Assets;
import kr.sunrin.stellapoly.game.etc.Def;
import kr.sunrin.stellapoly.game.etc.Status;

/**
 * Created by 건후 on 2015-08-25.
 */
public class Character {

    int w, h;
    public int x, y;  //캐릭터 왼쪽 위 좌표

    public static double angle = 0.0D;
    private int anchorX;
    private int anchorY;
    private double angleVelocity = 5D / 10;
    public double ballX = 0.0D;
    public double ballY = 0.0D;
    public double dt = 0.15D;
    public double radius = Def.h * 0.25; //초기 값

    public int life;
    public boolean shield = false;

    public Character() {
        w = Def.w;
        h = Def.h;
        life = 3;
    }

    public void paint(Graphics g) {
        g.drawImage(Assets.character, x, y);
    }

    public void update(Status s) {
        anchorX = (w / 2);
        anchorY = (h / 2);
        switch (s) {
            case SETPOS:
                angle += angleVelocity * dt;
                break;
            case PRESS:
                radius += h * 0.0020;
                dt += 0.00018;  //0.036
                angle += angleVelocity * dt;
//                if (dt > 0.15 + (0.00036 * 1000)) {
//                    dt = 0.15 + (0.00036 * 1000);
//                }
                break;
            case RELEASE:
                radius -= h * 0.0020; //0.0009
                dt -= 0.00018;
                angle += angleVelocity * dt;
//                if (dt < 0.15 - (0.00036 * 1000)) {
//                    dt = 0.15 - (0.00036 * 1000);
//                }
                break;
            default:
                break;
        }
        ballX = (anchorX + radius * Math.cos(angle)) - 25;
        ballY = (anchorY + radius * Math.sin(angle)) - 25;
        x = (int) ballX;
        y = (int) ballY;

    }

    public void collision() {
        int characterCX = x + 25;
        int characterCY = y + 25;
        int blackCX = 640;
        int blackCY = 360;
        if (Math.pow((characterCX - blackCX),2) + Math.pow((characterCY - blackCY),2) <= 44 * 44) {
            life = 0;
        }
    }
}

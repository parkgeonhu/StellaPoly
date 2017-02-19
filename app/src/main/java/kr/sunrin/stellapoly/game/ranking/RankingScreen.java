package kr.sunrin.stellapoly.game.ranking;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import kr.sunrin.stellapoly.framework.Game;
import kr.sunrin.stellapoly.framework.Graphics;
import kr.sunrin.stellapoly.framework.Input.*;
import kr.sunrin.stellapoly.framework.Screen;
import kr.sunrin.stellapoly.framework.etc.DBManager;
import kr.sunrin.stellapoly.framework.implementation.AndroidGame;
import kr.sunrin.stellapoly.game.etc.Assets;
import kr.sunrin.stellapoly.game.etc.Def;
import kr.sunrin.stellapoly.game.etc.MainMenuScreen;

/**
 * Created by 건후 on 2015-08-25.
 */
public class RankingScreen extends Screen {

    int[] res;
    Paint paint;
    int w, h;
    int x, y;

    public RankingScreen(Game game) {
        super(game);
        w = Def.w;
        h = Def.h;
        DBManager.getInstance().initHelper(AndroidGame.context);
        res = DBManager.getInstance().selectTop();
        DBManager.getInstance().insert(0);
        DBManager.getInstance().insert(0);
        DBManager.getInstance().insert(0);
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setTextSize(80);

        for (int i = 0; i < res.length; i++) {
            System.out.println("db : " + res[i]);
        }
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_DOWN) {
                //터치 영역 지정해주고 스크린 변경
                if (inBounds(event, 90, 583, 151, 76)) {
                    game.setScreen(new MainMenuScreen(game)); //singleplay
                }
            }
        }
    }

    private boolean inBounds(TouchEvent event, int x, int y, int width,
                             int height) {
        if (event.x > x && event.x < x + width - 1 && event.y > y
                && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.ranking, 0, 0);
        x = (int)(w*0.6);
        y = (int)(h*0.2);
        for (int i = 0; i < res.length; i++) {
            g.drawString(String.valueOf(res[i]), x, y,paint);
            y += h * 0.1;
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {

    }
}

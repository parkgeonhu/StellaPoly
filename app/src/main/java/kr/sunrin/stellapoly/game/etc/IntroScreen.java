package kr.sunrin.stellapoly.game.etc;

import java.util.List;

import kr.sunrin.stellapoly.framework.Game;
import kr.sunrin.stellapoly.framework.Graphics;

import kr.sunrin.stellapoly.framework.Input.*;
import kr.sunrin.stellapoly.framework.Screen;

/**
 * Created by 건후 on 2015-08-06.
 */
public class IntroScreen extends Screen {

    public IntroScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_DOWN) {
                game.setScreen(new MainMenuScreen(game));
            }
        }
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawImage(Assets.intro, 0, 0);
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

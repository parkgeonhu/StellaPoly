package kr.sunrin.stellapoly.game.etc;

import java.util.List;

import kr.sunrin.stellapoly.framework.Game;
import kr.sunrin.stellapoly.framework.Graphics;
import kr.sunrin.stellapoly.framework.Input;
import kr.sunrin.stellapoly.framework.Screen;
import kr.sunrin.stellapoly.game.ranking.RankingScreen;
import kr.sunrin.stellapoly.game.setting.SettingScreen;

/**
 * Created by 건후 on 2015-08-07.
 */
public class MainMenuScreen extends Screen {
    public MainMenuScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_DOWN) {
                //터치 영역 지정해주고 스크린 변경
                if (inBounds(event, 541, 358, 198, 64)) {
                    game.setScreen(new GameReadyScreen(game)); //singleplay
                } else if (inBounds(event, 541, 423, 198, 64)) {
                    game.setScreen(new SettingScreen(game)); //theme
                } else if (inBounds(event, 541, 488, 198, 64)) {
                    game.setScreen(new RankingScreen(game)); //ranking
                } else if (inBounds(event, 541, 554, 198, 64)) {
                    game.setScreen(new IntroScreen(game)); //setting
                }
            }
        }

    }

    private boolean inBounds(Input.TouchEvent event, int x, int y, int width,
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
        g.drawImage(Assets.menu, 0, 0);
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

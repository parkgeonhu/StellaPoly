package kr.sunrin.stellapoly.game.etc;

import kr.sunrin.stellapoly.framework.Game;
import kr.sunrin.stellapoly.framework.Graphics;
import kr.sunrin.stellapoly.framework.Screen;

/**
 * Created by 건후 on 2015-08-27.
 */
public class InitScreen extends Screen{
    public InitScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g=game.getGraphics();
        Assets.intro = g.newImage("first_screen.png", Graphics.ImageFormat.ARGB8888);
        Assets.touch_to_start = g.newImage("touch_to_start.png", Graphics.ImageFormat.ARGB8888);
        Assets.menu = g.newImage("main_screen.png", Graphics.ImageFormat.ARGB8888);
        Assets.ranking = g.newImage("ranking/ranking_screen.png", Graphics.ImageFormat.ARGB8888);
        Assets.theme1=g.newImage("setting/setting_1.png", Graphics.ImageFormat.ARGB8888);
        Assets.theme2=g.newImage("setting/setting_2.png", Graphics.ImageFormat.ARGB8888);
        Assets.theme3=g.newImage("setting/setting_3.png", Graphics.ImageFormat.ARGB8888);
        Assets.theme4=g.newImage("setting/setting_4.png", Graphics.ImageFormat.ARGB8888);
        game.setScreen(new IntroScreen(game));
    }

    @Override
    public void paint(float deltaTime) {

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

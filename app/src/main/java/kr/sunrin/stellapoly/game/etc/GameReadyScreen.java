package kr.sunrin.stellapoly.game.etc;

import kr.sunrin.stellapoly.framework.Game;
import kr.sunrin.stellapoly.framework.Graphics;
import kr.sunrin.stellapoly.framework.Graphics.*;
import kr.sunrin.stellapoly.framework.Screen;
import kr.sunrin.stellapoly.framework.etc.DataManager;
import kr.sunrin.stellapoly.game.singleplay.screen.SinglePlayScreen;

/**
 * Created by 건후 on 2015-08-07.
 */
public class GameReadyScreen extends Screen {

    String theme;
    int select;

    public GameReadyScreen(Game game) {
        super(game);
        theme = DataManager.getInstance(null).getPreferences("THEME");
        if (theme.equals("")) {
            DataManager.getInstance(null).savePreferences("THEME", String.valueOf(0));
            theme=DataManager.getInstance(null).getPreferences("THEME");
        }
        select = Integer.parseInt(theme);
    }

    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.item1 = g.newImage("game/holo/item_energy.png", ImageFormat.ARGB8888);
        Assets.item2 = g.newImage("game/holo/item_life.png", ImageFormat.ARGB8888);
        Assets.item3 = g.newImage("game/holo/item_shield.png", ImageFormat.ARGB8888);
        if (select == 0) {
            Assets.backgroundh0 = g.newImage("game/holo/game_screen_0.png", ImageFormat.ARGB8888);
            Assets.backgroundh1 = g.newImage("game/holo/game_screen_1.png", ImageFormat.ARGB8888);
            Assets.backgroundh2 = g.newImage("game/holo/game_screen_2.png", ImageFormat.ARGB8888);
            Assets.backgroundh3 = g.newImage("game/holo/game_screen_3.png", ImageFormat.ARGB8888);
            Assets.blackhole=g.newImage("game/holo/blackhole.png", ImageFormat.ARGB8888);
            Assets.character = g.newImage("game/holo/character.png", ImageFormat.ARGB8888);
            Assets.enemy1 = g.newImage("game/holo/enemy_1.png", ImageFormat.ARGB8888);
            Assets.enemy2 = g.newImage("game/holo/enemy_2.png", ImageFormat.ARGB8888);
            Assets.enemy3 = g.newImage("game/holo/enemy_3.png", ImageFormat.ARGB8888);

        } //holo
        if (select == 1) {
            Assets.backgroundh0 = g.newImage("game/flat/game_screen_0.png", ImageFormat.ARGB8888);
            Assets.backgroundh1 = g.newImage("game/flat/game_screen_1.png", ImageFormat.ARGB8888);
            Assets.backgroundh2 = g.newImage("game/flat/game_screen_2.png", ImageFormat.ARGB8888);
            Assets.backgroundh3 = g.newImage("game/flat/game_screen_3.png", ImageFormat.ARGB8888);
            Assets.blackhole=g.newImage("game/flat/blackhole.png", ImageFormat.ARGB8888);
            Assets.character = g.newImage("game/flat/character.png", ImageFormat.ARGB8888);
            Assets.enemy1 = g.newImage("game/flat/enemy_1.png", ImageFormat.ARGB8888);
            Assets.enemy2 = g.newImage("game/flat/enemy_2.png", ImageFormat.ARGB8888);
            Assets.enemy3 = g.newImage("game/flat/enemy_3.png", ImageFormat.ARGB8888);
        } //flat
        if (select == 2) {
            Assets.backgroundh0 = g.newImage("game/paper/game_screen_0.png", ImageFormat.ARGB8888);
            Assets.backgroundh1 = g.newImage("game/paper/game_screen_1.png", ImageFormat.ARGB8888);
            Assets.backgroundh2 = g.newImage("game/paper/game_screen_2.png", ImageFormat.ARGB8888);
            Assets.backgroundh3 = g.newImage("game/paper/game_screen_3.png", ImageFormat.ARGB8888);
            Assets.blackhole=g.newImage("game/paper/blackhole.png", ImageFormat.ARGB8888);
            Assets.character = g.newImage("game/paper/character.png", ImageFormat.ARGB8888);
            Assets.enemy1 = g.newImage("game/paper/enemy_1.png", ImageFormat.ARGB8888);
            Assets.enemy2 = g.newImage("game/paper/enemy_2.png", ImageFormat.ARGB8888);
            Assets.enemy3 = g.newImage("game/paper/enemy_3.png", ImageFormat.ARGB8888);
        } //paper
        if (select == 3) {
            Assets.backgroundh0 = g.newImage("game/twotone/game_screen_0.png", ImageFormat.ARGB8888);
            Assets.backgroundh1 = g.newImage("game/twotone/game_screen_1.png", ImageFormat.ARGB8888);
            Assets.backgroundh2 = g.newImage("game/twotone/game_screen_2.png", ImageFormat.ARGB8888);
            Assets.backgroundh3 = g.newImage("game/twotone/game_screen_3.png", ImageFormat.ARGB8888);
            Assets.blackhole=g.newImage("game/twotone/blackhole.png", ImageFormat.ARGB8888);
            Assets.character = g.newImage("game/twotone/character.png", ImageFormat.ARGB8888);
            Assets.enemy1 = g.newImage("game/twotone/enemy_1.png", ImageFormat.ARGB8888);
            Assets.enemy2 = g.newImage("game/twotone/enemy_2.png", ImageFormat.ARGB8888);
            Assets.enemy3 = g.newImage("game/twotone/enemy_3.png", ImageFormat.ARGB8888);
        } //twotone

        game.setScreen(new SinglePlayScreen(game));
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

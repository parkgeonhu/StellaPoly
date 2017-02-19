package kr.sunrin.stellapoly.game.setting;

import java.util.List;

import kr.sunrin.stellapoly.framework.Game;
import kr.sunrin.stellapoly.framework.Graphics;
import kr.sunrin.stellapoly.framework.Input;
import kr.sunrin.stellapoly.framework.Screen;
import kr.sunrin.stellapoly.framework.etc.DataManager;
import kr.sunrin.stellapoly.game.etc.Assets;
import kr.sunrin.stellapoly.game.etc.MainMenuScreen;

/**
 * Created by 건후 on 2015-08-25.
 */
public class SettingScreen extends Screen{

    String theme;
    int select;

    public SettingScreen(Game game) {
        super(game);
        theme = DataManager.getInstance(null).getPreferences("THEME");
        select = Integer.parseInt(theme);
    }

    @Override
    public void update(float deltaTime) {
        theme = DataManager.getInstance(null).getPreferences("THEME");
        select = Integer.parseInt(theme);
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_DOWN) {
                //터치 영역 지정해주고 스크린 변경
                if (inBounds(event, 90, 583, 151, 76)) {
                    game.setScreen(new MainMenuScreen(game));
                }
                else if(inBounds(event, 741, 58, 205, 205)){
                    DataManager.getInstance(null).savePreferences("THEME", String.valueOf(0));
                }
                else if(inBounds(event, 1011, 58, 205, 205)){
                    DataManager.getInstance(null).savePreferences("THEME", String.valueOf(1));
                }
                else if(inBounds(event, 741, 328, 205, 205)){
                    DataManager.getInstance(null).savePreferences("THEME", String.valueOf(2));
                }
                else if(inBounds(event, 1011, 328, 205, 205)){
                    DataManager.getInstance(null).savePreferences("THEME", String.valueOf(3));
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
        Graphics g=game.getGraphics();
        if (select == 0) {
            g.drawImage(Assets.theme1,0,0);
        }else if(select==1){
            g.drawImage(Assets.theme2,0,0);
        }else if(select==2){
            g.drawImage(Assets.theme3,0,0);
        }else if(select==3){
            g.drawImage(Assets.theme4,0,0);
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
        game.setScreen(new MainMenuScreen(game));
    }
}

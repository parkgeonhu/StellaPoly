package kr.sunrin.stellapoly.game;


import kr.sunrin.stellapoly.framework.Screen;
import kr.sunrin.stellapoly.framework.implementation.AndroidGame;
import kr.sunrin.stellapoly.game.etc.Assets;
import kr.sunrin.stellapoly.game.etc.InitScreen;

public class GameActivity extends AndroidGame {

    boolean firstTimeCreate = true;

    @Override
    public Screen getInitScreen() {

        if (firstTimeCreate) {
            Assets.load(this);
            firstTimeCreate = false;
        }

//        return new SplashLoadingScreen(this);
        //다음 스크린 진행
        return new InitScreen(this);
    }

    @Override
    public void onBackPressed() {
        getCurrentScreen().backButton();
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }
}

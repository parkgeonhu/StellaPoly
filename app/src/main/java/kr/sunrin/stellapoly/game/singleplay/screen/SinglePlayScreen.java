package kr.sunrin.stellapoly.game.singleplay.screen;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import kr.sunrin.stellapoly.framework.Game;
import kr.sunrin.stellapoly.framework.Graphics;
import kr.sunrin.stellapoly.framework.Input.*;
import kr.sunrin.stellapoly.framework.Screen;
import kr.sunrin.stellapoly.game.etc.Assets;
import kr.sunrin.stellapoly.game.etc.Def;
import kr.sunrin.stellapoly.game.etc.MainMenuScreen;
import kr.sunrin.stellapoly.game.etc.Status;
import kr.sunrin.stellapoly.game.singleplay.material.EnemyManager;
import kr.sunrin.stellapoly.game.singleplay.material.ItemManager;
import kr.sunrin.stellapoly.game.singleplay.player.Character;

/**
 * Created by 건후 on 2015-08-24.
 */
public class SinglePlayScreen extends Screen {
    enum GameState {
        Ready, Running, Paused, GameOver
    }

    GameState state = GameState.Ready;

    int w,h;

    boolean ispop=true;
    boolean isCalculate=false;

    String theme, sound;

    Character character;
    ItemManager itemManager;
    EnemyManager enemyManager;

    long currentTime;
    long delta;
    long startTime;
    long gameTime;
    long tempTime;

    Paint paint, paint2;


    public SinglePlayScreen(Game game) {
        super(game);

        w= Def.w;
        h = Def.h;

        character=new Character();
        itemManager=new ItemManager(character);
        enemyManager=new EnemyManager(character);

        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

        paint2 = new Paint();
        paint2.setTextSize(100);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setAntiAlias(true);
        paint2.setColor(Color.WHITE);


    }

    @Override
    public void update(float deltaTime) {
        System.out.println("time : "+gameTime);
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

        if (state == GameState.Ready)
            updateReady(touchEvents);
        if (state == GameState.Running){
            isCalculate=false;
            currentTime = System.nanoTime();
            delta = (currentTime - startTime) / 1000000000;
            gameTime=delta+tempTime;
            updateRunning(touchEvents);
        }
        if (state == GameState.Paused){
            if(isCalculate==false){
                tempTime+=(currentTime-startTime)/ 1000000000;
                isCalculate=true;
            }
            updatePaused(touchEvents);
        }
        if (state == GameState.GameOver)
            updateGameOver(touchEvents);
    }

    private void updateReady(List<TouchEvent> touchEvents){
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                startTime=System.nanoTime();
                state= GameState.Running;
            }
        }
    }

    private void updateRunning(List<TouchEvent> touchEvents){
        character.collision();
        character.update(Status.SETPOS);
        itemManager.update(delta);
        enemyManager.update(delta);
        int len=touchEvents.size();
        for(int i=0;i<len;i++){
            TouchEvent event= touchEvents.get(i);
            if(event.type==TouchEvent.TOUCH_DOWN){
                ispop=false;
            }
            else if(event.type==TouchEvent.TOUCH_UP){
                if(inBounds(event,1180,29,70,70)){
                    state=GameState.Paused;
                }
                ispop=true;
            }
        }
        proCessTouchEvent();
    }

    private void updatePaused(List<TouchEvent> touchEvents){
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 0, 0, 1280, 360)) {

                    if (!inBounds(event,1180,29,70,70)) {
                        startTime=System.nanoTime();
                        resume();
                    }
                }

                if (inBounds(event, 0, 360, 1280, 720)) {
                    //메인 메뉴로 돌아가기
                    game.setScreen(new MainMenuScreen(game));
                }
            }
        }
    }

    private void updateGameOver(List<TouchEvent> touchEvents){

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
        Graphics g=game.getGraphics();
        if(character.life==0){
            g.drawImage(Assets.backgroundh0,-2,-2);
            game.setScreen(new MainMenuScreen(game));
        }
        else if(character.life==1){
            g.drawImage(Assets.backgroundh1,-2,-2);
        }
        else if(character.life==2){
            g.drawImage(Assets.backgroundh2,-2,-2);
        }
        else if(character.life==3){
            g.drawImage(Assets.backgroundh3,-2,-2);
        }
        g.drawImage(Assets.blackhole,540,260);
        g.drawImage(Assets.character,character.x,character.y);
        itemManager.paint(g);
        enemyManager.paint(g);
        if (state == GameState.Ready)
            drawReadyUI();
        if (state == GameState.Running)
            drawRunningUI();
        if (state == GameState.Paused)
            drawPausedUI();
        if (state == GameState.GameOver)
            drawGameOverUI();
    }


    private void drawReadyUI() {
        Graphics g = game.getGraphics();

        g.drawARGB(155, 0, 0, 0);
        g.drawString("Tap to Start.", 400, 240, paint);

    }

    private void drawRunningUI() {


    }

    private void drawPausedUI() {
        Graphics g = game.getGraphics();
        // Darken the entire screen so you can display the Paused screen.
        g.drawARGB(155, 0, 0, 0);
        g.drawString("Resume", 400, 165, paint2);
        g.drawString("Menu", 400, 360, paint2);

    }

    private void drawGameOverUI() {
        Graphics g = game.getGraphics();
        g.drawRect(0, 0, 1281, 801, Color.BLACK);
        g.drawString("GAME OVER.", 400, 240, paint2);
        g.drawString("Tap to return.", 400, 290, paint);

    }

    void proCessTouchEvent() {
        if (ispop == true) {
            character.update(Status.RELEASE);
        } //터치 되지 않을 때
        else {
            if (character.radius < 720 * 0.4)
                character.update(Status.PRESS);
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        if (state == GameState.Paused)
            state = GameState.Running;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void backButton() {
        state=GameState.Paused;
    }
}

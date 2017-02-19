package kr.sunrin.stellapoly.game.singleplay.material;

import java.util.ArrayList;

import kr.sunrin.stellapoly.framework.Graphics;
import kr.sunrin.stellapoly.game.singleplay.player.Character;

/**
 * Created by 건후 on 2015-08-26.
 */
public class EnemyManager {
    ArrayList<Enemy> enemyList;
    Character character;

    long delta;
    float lasttimeEnemy;

    boolean isEnemy=true;

    Enemy enemy;

    public EnemyManager(Character character) {
        enemyList = new ArrayList<Enemy>();
        this.character = character;
    }

    public void update(long delta) {
        this.delta=delta;
        makeEnemy();
        for (int i = 0; i < enemyList.size(); i++) {
            checkCollision(i);
            if(isEnemy==true){
                enemyList.get(i).update();
            }
            isEnemy=true;
        }
    }

    public void paint(Graphics g) {
        for (int i = 0; i < enemyList.size(); i++) {
            enemyList.get(i).paint(g);
        }
    }

    public void makeEnemy() {
        if (delta - lasttimeEnemy > 0.3) {
            enemy = new Enemy();
            enemyList.add(enemy);
            enemy = null;
            lasttimeEnemy = delta;
        }
    }

    public void checkCollision(int index) {
        int characterCX = character.x + 25;
        int characterCY = character.y + 25;
        int blackCX = 640;
        int blackCY = 360;
        int enemyCX = enemyList.get(index).cx + 25;
        int enemyCY = enemyList.get(index).cy + 25;

        boolean isCollision=false;
        if (Math.pow((enemyCX - characterCX),2) + Math.pow((enemyCY - characterCY),2) <= 25 * 25) {
            isCollision=true;
            character.life--;
        } //캐릭터 아이템 충돌시
        else if(Math.pow((enemyCX - blackCX),2) + Math.pow((enemyCY - blackCY), 2) <= 44 * 44){
            isCollision=true;
        } //캐릭터 적 블랙홀 충돌 시
        if(isCollision==true){
            isEnemy=false;
            enemyList.remove(index);
        }
    }
}

package kr.sunrin.stellapoly.game.singleplay.material;

import java.util.ArrayList;

import kr.sunrin.stellapoly.framework.Graphics;
import kr.sunrin.stellapoly.game.singleplay.player.Character;

/**
 * Created by 건후 on 2015-08-26.
 */
public class ItemManager {

    ArrayList<Item> itemList;
    Character character;

    long delta;
    float lasttimeItem;

    boolean isItem=true;

    Item item;

    public ItemManager(Character character) {
        itemList = new ArrayList<Item>();
        this.character = character;
    }

    public void update(long delta) {
        this.delta=delta;
        makeItem();
        for (int i = 0; i < itemList.size(); i++) {
            checkCollision(i);
            if(isItem==true){
                itemList.get(i).update();
            }
            isItem=true;
        }
    }

    public void paint(Graphics g) {
        for (int i = 0; i < itemList.size(); i++) {
            itemList.get(i).paint(g);
        }
    }

    public void makeItem() {
        if (delta - lasttimeItem > 10) {
            item = new Item();
            itemList.add(item);
            item = null;
            lasttimeItem = delta;
        }
    }

    public void checkCollision(int index) {
        int characterCX = character.x + 25;
        int characterCY = character.y + 25;
        int blackCX = 640;
        int blackCY = 360;
        int itemCX = itemList.get(index).cx + 25;
        int itemCY = itemList.get(index).cy + 25;

        boolean isCollision=false;

        if (Math.pow((itemCX - characterCX), 2) + Math.pow((itemCY - characterCY),2) <= 25 * 25) {
            isCollision=true;
            if (itemList.get(index).itemType == 0) {
            } //위성

            else if (itemList.get(index).itemType == 1) {
                if (character.life >= 3) {
                } else {
                    System.out.println(character.life + " " + ", 생명 먹음");
                    character.life++;
                }
            }//life

            else if (itemList.get(index).itemType == 2) {
                character.shield = true;
                //캐릭터 이미지 바꾸기
            } //쉴드

        } //캐릭터 아이템 충돌 시
        else if(Math.pow((itemCX - blackCX),2) + Math.pow((itemCY - blackCY), 2) <= 44 * 44){
            isCollision=true;
        }
        if(isCollision==true){
            isItem=false;
            itemList.remove(index);
        } // 블랙홀 아이템 충돌 시
    }
}

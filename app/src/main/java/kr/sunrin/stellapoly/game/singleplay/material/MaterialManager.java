//package kr.sunrin.stellapoly.game.singleplay;
//
//import java.util.ArrayList;
//
//import kr.sunrin.stellapoly.framework.Graphics;
//
///**
// * Created by 건후 on 2015-08-25.
// */
//public class MaterialManager {
//    ArrayList<Material> materialList;
//    Character character;
//
//    long delta;
//    float lasttimeItem;
//    long lasttimeEnemy;
//
//    Enemy enemy;
//    Item item;
//
//    boolean flag = false; //충돌 판정 flag
//
//    MaterialManager(Character character) {
//        materialList = new ArrayList<Material>();
//        this.character = character;
//    }
//
//    public void update(long delta) {
//        this.delta = delta;
//        makeMaterial();
//        if (materialList.size() >= 1) {
//            for (int i = 0; i < materialList.size(); i++) {
//                for (int j = 0; j < materialList.size(); j++) {
//                    checkCollison(materialList.get(j),j);
//                }
//                if(flag==false){
////                    if (materialList.get(i).type == 0) {
////                        ((Item) materialList.get(i)).update();
////                    }// 캐릭터-아이템 충돌 구현부
////                    else if (materialList.get(i).type == 1) {
////                        ((Enemy) materialList.get(i)).update();
////                    } // 캐릭터-적과 충돌 구현부
////                    else {
////                        ((Satellite) materialList.get(i)).update();
////                    }
//                    materialList.get(i).update();
//                }
//            }
//        }
//    }
//
//    //int i = materialList.size() - 1; i >= 0; i--
//
//    public void paint(Graphics g) {
//        if (materialList.size() - 1 >= 1) {
//            for (int i = 0; i < materialList.size(); i++) {
//                for (int j = 0; j < materialList.size(); j++) {
//                    checkCollison(materialList.get(j),j);
//                }
//                if(flag==false){
////                    if (materialList.get(i).type == 0) {
////                        ((Item) materialList.get(i)).paint(g);
////                    }// 아이템 렌더링
////                    else if (materialList.get(i).type == 1) {
////                        ((Enemy) materialList.get(i)).paint(g);
////                    } // 적 렌더링
////                    else if (materialList.get(i).type == 2) {
////                        ((Satellite) materialList.get(i)).paint(g);
////                    } //위성 렌더링
//                    materialList.get(i).paint(g);
//                }
//                flag=false;
//            }
//        }
//    }
//
//    public void makeMaterial() {
//        if (delta - lasttimeEnemy > 1) {
//            enemy = new Enemy();
//            materialList.add(enemy);
//            enemy = null;
//            lasttimeEnemy = delta;
//        }
//        if (delta - lasttimeItem > 10) {
//            item = new Item();
//            materialList.add(item);
//            item = null;
//            lasttimeItem = delta;
//        }
//    }
//
//    public void checkCollison(Material material, int index) {
//        int type = material.type;
//        int blackCX=640;
//        int blackCY=360;
//        if (type == 0) {
//            if (Math.abs((character.x + 25) - material.cx+25) < 50 && Math.abs((character.y + 25) - material.cy+25) < 50) {
//                if (((Item) material).itemType == 0) {
//
//                } //위성
//
//                else if (((Item) material).itemType == 1) {
//                    if (character.life >= 3) {
//                    } else {
//                        System.out.println(character.life + " " + ", 생명 먹음");
//                        character.life++;
//                    }
//                }//life
//
//                else if (((Item) material).itemType == 2) {
//                    character.shield = true;
//                    //캐릭터 이미지 바꾸기
//                } //쉴드
//                flag=true;
//                materialList.remove(index);
//            } //캐릭터 아이템 충돌시
//            else if (Math.abs(blackCX - material.cx+25) < 100 && Math.abs(blackCY - material.cy+25) < 100) {
//                flag=true;
//                materialList.remove(index);
//            } //아이템 블랙홀 충돌시
//        } //아이템
//        else if (type == 1) {
//            if (Math.abs((character.x + 25) - material.cx+25) < 50 && Math.abs((character.y + 25) - material.cy+25) < 50) {
//                flag=true;
//                materialList.remove(index);
//            } //캐릭터 아이템 충돌시
//            else if (Math.abs(blackCX - material.cx+25) < 100 && Math.abs(blackCY - material.cy+25) < 100) {
//                flag=true;
//                materialList.remove(index);
//            } //아이템 블랙홀 충돌시
//        } //적
//        else {
//
//        }
//    }
//}

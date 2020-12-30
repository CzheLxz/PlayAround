package com.czhe.sysmanage.controller;

import com.czhe.sysmanage.entity.Player;
import com.czhe.sysmanage.retrunHandle.ResultBody;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author czhe
 * @version 1.0
 * @create 2020/12/30 16:36
 * @description
 **/

@Api(tags = "斗地主接口")
@RestController
@RequestMapping("/ddz")
public class DouDIZhuController {

    private static List<String> aPairCards;

    private static int master;

    private static List<Player> allPlayer;

    //初始化一副扑克牌
    private static void initializeCards() {
        String[] colors = {"♠", "♥", "♦", "♣"};
        String[] numbers = {"2", "A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3"};
        aPairCards = new ArrayList<>();
        aPairCards.add("大王");
        aPairCards.add("小王");
        for (String color : colors) {
            for (String number : numbers) {
                aPairCards.add(number + color);
            }
        }
        Collections.shuffle(aPairCards);
        master = (int) (Math.random() * 52 + 3);
    }

    //发牌
    private static void licenSing() {
        //初始化三位玩家
        Player playerA = new Player("玩家A", 0, new ArrayList<>(), 1000);
        Player playerB = new Player("玩家B", 0, new ArrayList<>(), 1000);
        Player playerC = new Player("玩家C", 0, new ArrayList<>(), 1000);
        List<String> masterCard = new ArrayList<>();//地主牌
        for (int i = 0; i < aPairCards.size(); i++) {
            if (i < 3) {
                masterCard.add(aPairCards.get(i));
                continue;
            }
            if (i % 3 == 0) {
                playerA.getCards().add(aPairCards.get(i));
                if (i == master) {
                    playerA.setType(1);
                    playerA.getCards().addAll(masterCard);
                }
            } else if (i % 3 == 1) {
                playerB.getCards().add(aPairCards.get(i));
                if (i == master) {
                    playerB.setType(1);
                    playerB.getCards().addAll(masterCard);
                }
            } else if (i % 3 == 2) {
                playerC.getCards().add(aPairCards.get(i));
                if (i == master) {
                    playerC.setType(1);
                    playerC.getCards().addAll(masterCard);
                }
            }
        }
        allPlayer = new ArrayList<>();
        allPlayer.add(playerA);
        allPlayer.add(playerB);
        allPlayer.add(playerC);
    }


    @PostMapping("/StartGame")
    public ResultBody StartGame() {
        initializeCards();
        licenSing();
        return ResultBody.success(allPlayer);
    }
}
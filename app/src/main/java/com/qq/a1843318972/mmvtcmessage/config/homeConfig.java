package com.qq.a1843318972.mmvtcmessage.config;

import com.qq.a1843318972.mmvtcmessage.R;
import com.qq.a1843318972.mmvtcmessage.entity.homeItem;

import java.util.ArrayList;

public class homeConfig {



    public static ArrayList<homeItem> getHomeItems() {
        ArrayList<homeItem> homeItems = new ArrayList<>();
        homeItems.add(new homeItem(R.drawable.mzy_m, "", 0));
        homeItems.add(new homeItem(R.drawable.mzy_z, "", 0));
        homeItems.add(new homeItem(R.drawable.mzy_y, "", 0));
        homeItems.add(new homeItem(R.drawable.xueyuanxinwen, "学院新闻Campus News", 915));
        homeItems.add(new homeItem(R.drawable.tz1, "通知公告Notice", 914));
        homeItems.add(new homeItem(R.drawable.dt2, "高职高专动态", 1962));
        homeItems.add(new homeItem(R.drawable.ss_z, "", 1));
        homeItems.add(new homeItem(R.drawable.dt1, "学院系部动态", 1961));
        homeItems.add(new homeItem(R.drawable.ss_y, "", 1));
        homeItems.add(new homeItem(R.drawable.jsj, "", 0));
        homeItems.add(new homeItem(R.drawable.jsjgcx, "", 0));
        homeItems.add(new homeItem(R.drawable.gcx, "", 0));
        homeItems.add(new homeItem(R.drawable.tz3, "计算机工程系系部新闻", 1212));
        return homeItems;
    }

    public static String getRandmSay() {
        String[] array = {"落叶归根，我归你。", "苦海无涯，回头是我。", "何以解忧，唯有抱抱你。", "众生皆苦，你是草莓味。", "无事献殷勤，非常喜欢你。", "我每天都不思进取，思你。", "我有一个超能力，超喜欢你。", "你是书吗？怎么让人越看越想睡。", "你是三，我的就，除了你，还是你。", "你是最好的，如果真有更好的，我装着看不见。"};
        int random = (int) (Math.random() * 100 % 10);
        int i = array.length - 1;
        while (random > array.length - 1) {
            if (random > i) {
                random = (int) (Math.random() * 100 % 10);
            }
        }
        return array[random];
    }

}

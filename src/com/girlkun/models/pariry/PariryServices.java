package com.girlkun.models.pariry;

import com.girlkun.models.item.Item;
import com.girlkun.models.player.Player;
import com.girlkun.services.InventoryServiceNew;
import com.girlkun.services.ItemService;
import com.girlkun.services.Service;

public class PariryServices {
  private static PariryServices instance;

  public static PariryServices gI() {
    if (instance == null) {
      instance = new PariryServices();
    }
    return instance;
  }

  public void addPlayerEven(Player player) {
    if (pariryManager.gI().currentPariry != null) {
      pariryManager.gI().addPlayerEven(player);
    }
  }

  public void addPlayerOdd(Player player) {
    if (pariryManager.gI().currentPariry != null) {
      pariryManager.gI().addPlayerOdd(player);
    }
  }

  public void rewardRuby(Player player) {
    if (player != null) {
      int Reward = (int) Math.round(player.rubyWin * pariryManager.tiLe);
      player.rubyWin = 0;
      Service.gI().sendThongBao(player, "Bạn đã nhận được " + Reward + " thỏi vàng");
      // player.inventory.ruby += Reward;
      Item thoivang = ItemService.gI().createNewItem((short) 457, Reward);
      InventoryServiceNew.gI().addItemBag(player, thoivang);
      InventoryServiceNew.gI().sendItemBags(player);
      Service.gI().sendMoney(player);
      // InventoryServiceNew.gI().sendItemBags(player);
      pariryManager.gI().setAfterPlayerReward(player);
    }
  }

  public boolean checkHavePariry() {
    return pariryManager.gI().currentPariry != null;
  }

  public String getHistoryPlayer(Player player) {
    return pariryManager.gI().getHistoryPlayer(player);
  }

  public String getHistory() {
    return pariryManager.gI().getHistoryGame();
  }
}

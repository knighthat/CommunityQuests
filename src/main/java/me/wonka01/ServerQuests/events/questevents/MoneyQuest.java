package me.wonka01.ServerQuests.events.questevents;

import me.wonka01.ServerQuests.enums.ObjectiveType;
import me.wonka01.ServerQuests.questcomponents.ActiveQuests;
import me.wonka01.ServerQuests.questcomponents.QuestController;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;

import java.util.List;

public class MoneyQuest extends QuestListener {
    private Economy economy;
    public MoneyQuest(ActiveQuests activeQuests, Economy economy) {
        super(activeQuests);
        this.economy = economy;
    }

    public boolean tryAddItemsToQuest(double money, Player player) {
        List<QuestController> controllers = tryGetControllersOfEventType(ObjectiveType.GIVE_MONEY);
        boolean hasMoneyQuest = false;
        double amountToAdd = money;

        for (QuestController controller : controllers) {
            int goal = controller.getQuestData().getQuestGoal();
            double completed = controller.getQuestData().getAmountCompleted();
            if (goal > 0 && completed + (int)money > goal) {
                amountToAdd = money - completed + goal;
                double difference = completed + money - goal;
                economy.depositPlayer(player, difference);
            }

            updateQuest(controller, player, amountToAdd);
            hasMoneyQuest = true;
        }
        if(hasMoneyQuest) {
            economy.withdrawPlayer(player, amountToAdd);
        }
        return hasMoneyQuest;
    }
}

package me.knighthat.apis.events.instances.entity;

import lombok.NonNull;
import me.knighthat.apis.events.EventBase;
import me.knighthat.apis.utils.Utils;
import me.wonka01.ServerQuests.ServerQuests;
import me.wonka01.ServerQuests.enums.ObjectiveType;
import me.wonka01.ServerQuests.questcomponents.QuestController;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Range;

import java.util.List;

public abstract class EntityEvent<T extends Event> extends EventBase<T> {

    protected EntityEvent(ServerQuests plugin, @NonNull ObjectiveType objectiveType) {
        super(plugin, objectiveType);
    }

    protected <V> boolean attemptToUpdate(@NonNull HumanEntity player, @NonNull V value) {
        return attemptToUpdate(player, value, 1);
    }

    protected <V> boolean attemptToUpdate(@NonNull HumanEntity player, @NonNull V value,
                                          @Range(from = 0, to = Long.MAX_VALUE) int amount) {

        for (QuestController ctrl : super.getControllers()) {

            List<String> materials = ctrl.getEventConstraints().getMobNames();
            if (materials.isEmpty() || Utils.contains(EntityType.values(), value)) {

                super.update(ctrl, (Player) player, amount);
                return true;
            }
        }

        return false;
    }
}

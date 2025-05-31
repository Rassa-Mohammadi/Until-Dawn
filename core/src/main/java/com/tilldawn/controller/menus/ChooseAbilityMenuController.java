package com.tilldawn.controller.menus;

import com.tilldawn.Main;
import com.tilldawn.model.Player;
import com.tilldawn.model.enums.Ability;
import com.tilldawn.view.GameView;
import com.tilldawn.view.menus.ChooseAbilityMenuView;

public class ChooseAbilityMenuController {
    private ChooseAbilityMenuView view;

    public void setView(ChooseAbilityMenuView view) {
        this.view = view;
    }

    public void back(GameView pausedGame, String abilityName) {
        Ability ability = Ability.getAbility(abilityName);
        Player player = pausedGame.getController().getPlayer();
        if (ability == Ability.VITALITY) {
            player.addMaxHp(1);
        }
        else if (ability == Ability.DAMAGER) {
            player.setWeaponDamageTimer();
        }
        else if (ability == Ability.PROCREASE) {
            player.addProjectile(1);
        }
        else if (ability == Ability.AMOCREASE) {
            player.addMaxAmmo(5);
        }
        else if (ability == Ability.SPEEDY) {
            player.setSpeedTimer();
        }
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(pausedGame);
    }
}

package nightkosh.withered_lands.entity;

import net.minecraft.sounds.SoundEvent;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public interface IJumpingCube {

    int getJumpDelay();

    float getSoundVolume();

    SoundEvent getJumpSound();

}

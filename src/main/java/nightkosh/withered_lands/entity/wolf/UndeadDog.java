package nightkosh.withered_lands.entity.wolf;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import nightkosh.withered_lands.entity.UndeadPet;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class UndeadDog extends UndeadPet {

    public UndeadDog(EntityType<? extends UndeadDog> entityType, Level level) {
        super(entityType, level);
    }

}

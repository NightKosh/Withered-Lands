package nightkosh.withered_lands.entity.wolf;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import nightkosh.withered_lands.entity.AUndeadPet;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public abstract class AUndeadDog extends AUndeadPet {

    public AUndeadDog(EntityType<? extends AUndeadDog> entityType, Level level) {
        super(entityType, level);
    }

}

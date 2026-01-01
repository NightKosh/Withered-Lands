package nightkosh.withered_lands.entity.water;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.entity.ai.TridentAttackGoal;
import org.jspecify.annotations.Nullable;

import javax.annotation.Nonnull;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PhantomDiver extends DrownedSailor implements RangedAttackMob {

    public PhantomDiver(EntityType<? extends PhantomDiver> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(
            @Nonnull ServerLevelAccessor level, @Nonnull DifficultyInstance difficulty,
            @Nonnull EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {
        groupData = super.finalizeSpawn(level, difficulty, spawnReason, groupData);
        this.populateDefaultEquipmentSlots(level.getRandom(), difficulty);
        return groupData;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(2, new TridentAttackGoal(this, 1, 40, 10));
    }

    @Override
    protected void populateDefaultEquipmentSlots(@Nonnull RandomSource randomSource, @Nonnull DifficultyInstance difficulty) {
        if (randomSource.nextBoolean()) {
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.TRIDENT));
        }
    }

    @Override
    public void performRangedAttack(LivingEntity target, float velocity) {
        var mainStack = this.getMainHandItem();
        var itemStack = mainStack.is(Items.TRIDENT) ?
                mainStack :
                new ItemStack(Items.TRIDENT);
        var throwntrident = new ThrownTrident(this.level(), this, itemStack);
        double x = target.getX() - this.getX();
        double y = target.getY(0.3333333333333333) - throwntrident.getY();
        double z = target.getZ() - this.getZ();
        double d = Math.sqrt(x * x + z * z);

        if (this.level() instanceof ServerLevel serverlevel) {
            Projectile.spawnProjectileUsingShoot(
                    throwntrident, serverlevel, itemStack,
                    x, y + d * 0.2F, z,
                    1.6F, 14 - this.level().getDifficulty().getId() * 4);
        }

        this.playSound(SoundEvents.DROWNED_SHOOT, 1, 1 / (this.getRandom().nextFloat() * 0.4F + 0.8F));

    }

    public static boolean checkSpawnRules(
            EntityType<? extends AWaterWalkingMob> entityType, ServerLevelAccessor levelAccessor,
            EntitySpawnReason spawnReason, BlockPos blockPos, RandomSource random) {
        return WLConfigs.PHANTOM_DIVER.get() &&
                checkCommonSpawnRules(levelAccessor, spawnReason, blockPos, random);
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 3)
                .add(Attributes.FOLLOW_RANGE, 35)
                .add(Attributes.STEP_HEIGHT, 1)
                .add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.ARMOR, 4)
                .build();
    }

}

package nightkosh.withered_lands.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.equipment.trim.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import nightkosh.withered_lands.core.WLConfigs;
import nightkosh.withered_lands.core.WLEnchantments;
import nightkosh.withered_lands.core.WLSounds;
import nightkosh.withered_lands.helper.WLEnchantmentHelper;
import org.jspecify.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class PossessedArmor extends AMonster {

    private int ticks = 0;

    private static final Map<EquipmentSlot, Item> CHAINMAIL_SET = Map.of(
            EquipmentSlot.MAINHAND, Items.IRON_SWORD,
            EquipmentSlot.HEAD, Items.CHAINMAIL_HELMET,
            EquipmentSlot.CHEST, Items.CHAINMAIL_CHESTPLATE,
            EquipmentSlot.LEGS, Items.CHAINMAIL_LEGGINGS,
            EquipmentSlot.FEET, Items.CHAINMAIL_BOOTS);

    private static final Map<EquipmentSlot, Item> GOLDEN_SET = Map.of(
            EquipmentSlot.MAINHAND, Items.GOLDEN_SWORD,
            EquipmentSlot.HEAD, Items.GOLDEN_HELMET,
            EquipmentSlot.CHEST, Items.GOLDEN_CHESTPLATE,
            EquipmentSlot.LEGS, Items.GOLDEN_LEGGINGS,
            EquipmentSlot.FEET, Items.GOLDEN_BOOTS);

    private static final Map<EquipmentSlot, Item> DIAMOND_SET = Map.of(
            EquipmentSlot.MAINHAND, Items.DIAMOND_SWORD,
            EquipmentSlot.HEAD, Items.DIAMOND_HELMET,
            EquipmentSlot.CHEST, Items.DIAMOND_CHESTPLATE,
            EquipmentSlot.LEGS, Items.DIAMOND_LEGGINGS,
            EquipmentSlot.FEET, Items.DIAMOND_BOOTS);

    private static final Map<EquipmentSlot, Item> NETHERITE_SET = Map.of(
            EquipmentSlot.MAINHAND, Items.NETHERITE_SWORD,
            EquipmentSlot.HEAD, Items.NETHERITE_HELMET,
            EquipmentSlot.CHEST, Items.NETHERITE_CHESTPLATE,
            EquipmentSlot.LEGS, Items.NETHERITE_LEGGINGS,
            EquipmentSlot.FEET, Items.NETHERITE_BOOTS);

    private static final List<ResourceKey<TrimPattern>> TRIM_PATTERNS = List.of(
            TrimPatterns.SILENCE,
            TrimPatterns.SENTRY,
            TrimPatterns.RAISER,
            TrimPatterns.FLOW,
            TrimPatterns.DUNE,
            TrimPatterns.EYE,
            TrimPatterns.VEX,
            TrimPatterns.RIB);

    public PossessedArmor(EntityType<? extends PossessedArmor> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1, false));
        this.goalSelector.addGoal(5, new MoveTowardsRestrictionGoal(this, 1));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));


        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
    }

    @Override
    public @Nullable SpawnGroupData finalizeSpawn(
            @Nonnull ServerLevelAccessor level, @Nonnull DifficultyInstance difficulty,
            @Nonnull EntitySpawnReason spawnReason, @Nullable SpawnGroupData groupData) {
        groupData = super.finalizeSpawn(level, difficulty, spawnReason, groupData);

        var pattern = level.registryAccess()
                .lookupOrThrow(Registries.TRIM_PATTERN)
                .getOrThrow(TRIM_PATTERNS.get(this.random.nextInt(TRIM_PATTERNS.size())));

        ArmorTrim trim;
        Map<EquipmentSlot, Item> armorSet;
        if (this.level().dimension() == Level.NETHER) {
            if (random.nextInt(5) == 0) {
                armorSet = NETHERITE_SET;
                trim = new ArmorTrim(getMaterial(this.level(), TrimMaterials.EMERALD), pattern);
            } else {
                armorSet = GOLDEN_SET;
                trim = new ArmorTrim(getMaterial(this.level(), TrimMaterials.REDSTONE), pattern);
            }
        } else {
            if (random.nextInt(5) == 0) {
                armorSet = DIAMOND_SET;
                trim = new ArmorTrim(getMaterial(this.level(), TrimMaterials.NETHERITE), pattern);
            } else {
                armorSet = CHAINMAIL_SET;
                trim = new ArmorTrim(getMaterial(this.level(), TrimMaterials.LAPIS), pattern);
            }
        }

        this.setItemSlot(EquipmentSlot.MAINHAND, getHandItem(armorSet.get(EquipmentSlot.MAINHAND)));
        this.setItemSlot(EquipmentSlot.HEAD, getArmorItem(armorSet.get(EquipmentSlot.HEAD), trim));
        this.setItemSlot(EquipmentSlot.CHEST, getArmorItem(armorSet.get(EquipmentSlot.CHEST), trim));
        this.setItemSlot(EquipmentSlot.LEGS, getArmorItem(armorSet.get(EquipmentSlot.LEGS), trim));
        this.setItemSlot(EquipmentSlot.FEET, getArmorItem(armorSet.get(EquipmentSlot.FEET), trim));

        return groupData;
    }

    private static Holder<TrimMaterial> getMaterial(Level level, ResourceKey<TrimMaterial> material) {
        return level.registryAccess()
                .lookupOrThrow(Registries.TRIM_MATERIAL)
                .getOrThrow(material);
    }

    private ItemStack getArmorItem(Item item, ArmorTrim trim) {
        var stack = getItem(item);
        stack.set(DataComponents.TRIM, trim);
        return stack;
    }

    private ItemStack getHandItem(Item item) {
        var stack = getItem(item);
        stack.setDamageValue(stack.getMaxDamage() - 30);
        return stack;
    }

    private ItemStack getItem(Item item) {
        var stack = new ItemStack(item);
        stack.enchant(WLEnchantmentHelper.getEnchantmentHolder(this.level(), Enchantments.BINDING_CURSE), 1);
        stack.enchant(WLEnchantmentHelper.getEnchantmentHolder(this.level(), WLEnchantments.CURSE_OF_STARVATION), 1);
        return stack;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level() instanceof ServerLevel server && ticks > 20) {
            double degree = Math.toRadians(-this.getYHeadRot());
            double sin = Math.sin(degree);
            double cos = Math.cos(degree);

            double sin1 = 0.35 * sin;
            double sin2 = 0.15 * sin;
            double cos1 = 0.35 * cos;
            double cos2 = 0.15 * cos;

            server.sendParticles(ParticleTypes.SOUL_FIRE_FLAME,
                    this.getX() + sin1 + cos2,
                    this.getY() + getEyeHeight(),
                    this.getZ() + cos1 - sin2,
                    1,
                    0, 0, 0,
                    0);

            server.sendParticles(ParticleTypes.SOUL_FIRE_FLAME,
                    this.getX() + sin1 - cos2,
                    this.getY() + getEyeHeight(),
                    this.getZ() + cos1 + sin2,
                    1,
                    0, 0, 0,
                    0);

            ticks = 0;
        } else {
            ticks++;
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ARMOR_EQUIP_CHAIN.value();
    }

    @Nonnull
    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSource) {
        return WLSounds.POSSESSED_ARMOR_HURT.get();
    }

    @Nonnull
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SHIELD_BREAK.value();
    }

    @Override
    protected void playStepSound(@Nonnull BlockPos pos, @Nonnull BlockState block) {
        this.playSound(WLSounds.POSSESSED_ARMOR_STEP.get(), 0.15F, 1);
    }

    public static AttributeSupplier createAttributeSupplier() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.FOLLOW_RANGE, 35)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 4)
                .add(Attributes.ARMOR, 5)
                .build();
    }

    public static boolean checkSpawnRules(
            EntityType<? extends PossessedArmor> entityType, ServerLevelAccessor levelAccessor,
            EntitySpawnReason spawnReason, BlockPos blockPos, RandomSource random) {
        return WLConfigs.POSSESSED_ARMOR_SPAWN.get() &&
                checkCommonSpawnRules(levelAccessor, blockPos, random);
    }

}

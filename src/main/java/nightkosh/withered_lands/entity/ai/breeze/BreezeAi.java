package nightkosh.withered_lands.entity.ai.breeze;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.schedule.Activity;
import nightkosh.withered_lands.core.WLSensorTypes;
import nightkosh.withered_lands.entity.breeze.ABreeze;

import java.util.List;
import java.util.Set;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class BreezeAi {

    private static final int TICKS_TO_REMEMBER_SEEN_TARGET = 100;
    public static final float SPEED_MULTIPLIER_WHEN_SLIDING = 0.6F;

    public static final List<SensorType<? extends Sensor<? super ABreeze>>> SENSOR_TYPES = ImmutableList.of(
            SensorType.NEAREST_LIVING_ENTITIES,
            SensorType.HURT_BY,
            SensorType.NEAREST_PLAYERS,
            WLSensorTypes.BREEZE_TARGETS.get()
    );

    public static final List<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES,
            MemoryModuleType.NEAREST_ATTACKABLE,
            MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
            MemoryModuleType.ATTACK_TARGET,
            MemoryModuleType.WALK_TARGET,
            MemoryModuleType.BREEZE_JUMP_COOLDOWN,
            MemoryModuleType.BREEZE_JUMP_INHALING,
            MemoryModuleType.BREEZE_SHOOT,
            MemoryModuleType.BREEZE_SHOOT_CHARGING,
            MemoryModuleType.BREEZE_SHOOT_RECOVERING,
            MemoryModuleType.BREEZE_SHOOT_COOLDOWN,
            MemoryModuleType.BREEZE_JUMP_TARGET,
            MemoryModuleType.BREEZE_LEAVING_WATER,
            MemoryModuleType.HURT_BY,
            MemoryModuleType.HURT_BY_ENTITY,
            MemoryModuleType.PATH
    );

    public static Brain<?> makeBrain(ABreeze breeze, Brain<ABreeze> brain, boolean isThunder) {
        initCoreActivity(brain);
        initIdleActivity(brain);
        initFightActivity(breeze, brain, isThunder);
        brain.setCoreActivities(Set.of(Activity.CORE));
        brain.setDefaultActivity(Activity.FIGHT);
        brain.useDefaultActivity();
        return brain;
    }

    private static void initCoreActivity(Brain<ABreeze> brain) {
        brain.addActivity(Activity.CORE, 0,
                ImmutableList.of(
                        new Swim<>(0.8F),
                        new LookAtTargetSink(45, 90)));
    }

    private static void initIdleActivity(Brain<ABreeze> brain) {
        brain.addActivity(
                Activity.IDLE,
                ImmutableList.of(
                        Pair.of(0, StartAttacking.create((level, breeze) ->
                                breeze.getBrain().getMemory(MemoryModuleType.NEAREST_ATTACKABLE))),
                        Pair.of(1, StartAttacking.create((level, breeze) -> breeze.getHurtBy())),
                        Pair.of(2, new SlideToTargetSink(20, 40)),
                        Pair.of(3, new RunOne<>(ImmutableList.of(
                                Pair.of(new DoNothing(20, TICKS_TO_REMEMBER_SEEN_TARGET), 1),
                                Pair.of(RandomStroll.stroll(SPEED_MULTIPLIER_WHEN_SLIDING), 2)))))
        );
    }

    private static void initFightActivity(ABreeze breeze, Brain<ABreeze> brain, boolean isThunder) {
        brain.addActivityWithConditions(
                Activity.FIGHT,
                ImmutableList.of(
                        Pair.of(0, StopAttackingIfTargetInvalid.create(
                                Sensor.wasEntityAttackableLastNTicks(breeze, TICKS_TO_REMEMBER_SEEN_TARGET).negate()::test)),
                        Pair.of(1, isThunder ? new ThunderStrike() : new BreezeShoot()),
                        Pair.of(2, isThunder ? new ThunderTeleportation() : new LongJump()),
                        Pair.of(3, new ShootWhenStuck()),
                        Pair.of(4, new Slide())),
                ImmutableSet.of(
                        Pair.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT),
                        Pair.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT))
        );
    }

    public static void updateActivity(ABreeze breeze) {
        breeze.getBrain().setActiveActivityToFirstValid(ImmutableList.of(Activity.FIGHT, Activity.IDLE));
    }

}

package nightkosh.withered_lands.renderer.model.animation;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

/**
 * Withered Lands
 *
 * @author NightKosh
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class WhiteJellyfishAnimations {

    public static final AnimationDefinition INFLATE = AnimationDefinition.Builder
            .withLength(4)
            .looping()
            .addAnimation("body",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0, KeyframeAnimations.scaleVec(1, 1, 1),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.scaleVec(1.2, 1.2, 1.2),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.scaleVec(1, 1, 1),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.scaleVec(0.9f, 0.9f, 0.9f),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.scaleVec(1, 1, 1),  AnimationChannel.Interpolations.LINEAR)
                    ))
            .build();

    public static final AnimationDefinition MOVE_LEGS = AnimationDefinition.Builder
            .withLength(4)
            .looping()
            .addAnimation("body_2",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.posVec(0, 2, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.posVec(0, -2, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("side_strings_1",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec(-5, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec(5, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("side_strings_1",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.posVec(0, 1, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.posVec(0, -1, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("side_strings_2",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec(-5, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec(5, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("side_strings_2",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.posVec(0, 1, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.posVec(0, -1, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("side_strings_3",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec(0, 0, -5), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("side_strings_3",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.posVec(0, 1, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.posVec(0, -1, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("side_strings_4",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec(0, 0, 5), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec(0, 0, -5), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("side_strings_4",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.posVec(0, 1, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.posVec(0, -1, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("tentacle_1",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec(-5, 0, -5), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec(-10, 0, -10), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec(-5, 0, -5), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("tentacle_2",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec(-5, 0, 5), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec(-10, 0, 10), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec(-5, 0, 5), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("tentacle_3",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec(5, 0, -5), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec(10, 0, -10), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec(5, 0, -5), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("tentacle_4",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec(5, 0, 5), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec(10, 0, 10), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec(5, 0, 5), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("tentacle_1_1",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec(-3.5F, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec(-7, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec(-3.5F, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("tentacle_1_2",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec(15, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec(35, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec(-20, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("tentacle_2_1",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec(-3.5F, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec(-7, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec(-3.5F, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("tentacle_2_2",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec(15, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec(35, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec(-20, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("tentacle_3_1",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec(3.5F, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec(7, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec(3.5F, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("tentacle_3_2",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec(-15, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec(-35, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec(20, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("tentacle_4_1",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec(3.5F, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec(7, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec(3.5F, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("tentacle_4_2",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec(-15, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec(-35, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec(20, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .build();
    
}

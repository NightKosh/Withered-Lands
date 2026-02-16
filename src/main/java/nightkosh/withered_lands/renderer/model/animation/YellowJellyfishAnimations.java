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
public class YellowJellyfishAnimations {

    public static final AnimationDefinition INFLATE = AnimationDefinition.Builder
            .withLength(4)
            .looping()
            .addAnimation("body",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0, KeyframeAnimations.scaleVec(1, 1, 1), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.scaleVec(1.2f, 1.2f, 1.2f), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.scaleVec(1, 1, 1), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.scaleVec(0.9f, 0.9f, 0.9f), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.scaleVec(1, 1, 1), AnimationChannel.Interpolations.LINEAR)
                    ))
            .build();

    public static final AnimationDefinition MOVE_LEGS = AnimationDefinition.Builder
            .withLength(4)
            .looping()
            .addAnimation("head",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.posVec(0, -2, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.posVec(0, 2, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("head",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec(0, 90, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec(0, 180, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec(0, 270, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec(0, 360, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("tentacles_11",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.posVec(-1, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.posVec(1, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("tentacles_12",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.posVec(1, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.posVec(-1, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("tentacles_13",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.posVec(0, 0, 1), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.posVec(0, 0, -1), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("tentacles_14",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.posVec(0, 0, -1), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.posVec(0, 0, 1), AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.posVec(0, 0, 0), AnimationChannel.Interpolations.LINEAR)
                    ))
            .build();

}

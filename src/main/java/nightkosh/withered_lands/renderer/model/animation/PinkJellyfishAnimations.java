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
public class PinkJellyfishAnimations {

    public static final AnimationDefinition INFLATE = AnimationDefinition.Builder
            .withLength(4)
            .looping()
            .addAnimation("body",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0, KeyframeAnimations.scaleVec(1, 1, 1),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.scaleVec(1.2f, 1.2f, 1.2f),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.scaleVec(1, 1, 1),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.scaleVec(0.9f, 0.9f, 0.9f),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.scaleVec(1, 1, 1),  AnimationChannel.Interpolations.LINEAR)
                    ))
            .build();

    public static final AnimationDefinition MOVE_LEGS = AnimationDefinition.Builder
            .withLength(4)
            .looping()
            .addAnimation("tentacle_front_1",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec( 0, 0,  0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec(-10,0,-10),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec( 0, 0,  0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec( 5, 0,  5),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec( 0, 0,  0),  AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("tentacle_front_2",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec( 0, 0, 0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec( 5, 0, 0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec( 0, 0, 0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec(-5, 0, 0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec( 0, 0, 0),  AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("tentacle_front_3",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec( 0, 0,  0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec(-10,0, 10),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec( 0, 0,  0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec( 5, 0,  5),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec( 0, 0,  0),  AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("tentacle_back_1",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec( 0, 0,  0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec(10, 0,-10),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec( 0, 0,  0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec(-5, 0,  5),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec( 0, 0,  0),  AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("tentacle_back_2",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec( 0, 0, 0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec(-5, 0, 0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec( 0, 0, 0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec( 5, 0, 0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec( 0, 0, 0),  AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("tentacle_back_3",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec( 0, 0,  0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec(10, 0, 10),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec( 0, 0,  0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec(-5, 0, -5),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec( 0, 0,  0),  AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("tentacle_right",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec(0, 0,  0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec(0, 0,  5),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec(0, 0,  0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec(0, 0, -5),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec(0, 0,  0),  AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("tentacle_left",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0, KeyframeAnimations.degreeVec(0, 0,  0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.degreeVec(0, 0, -5),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.degreeVec(0, 0,  0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.degreeVec(0, 0,  5),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.degreeVec(0, 0,  0),  AnimationChannel.Interpolations.LINEAR)
                    ))
            .build();
    
}

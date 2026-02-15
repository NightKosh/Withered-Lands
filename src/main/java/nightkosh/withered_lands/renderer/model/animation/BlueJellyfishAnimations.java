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
public class BlueJellyfishAnimations {

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
            .addAnimation("tentacles_head",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0, KeyframeAnimations.posVec(0, 0,  0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.posVec(0,2,0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.posVec( 0, 0,  0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.posVec( 0, -1,  0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.posVec( 0, 0,  0),  AnimationChannel.Interpolations.LINEAR)
                    ))
            .addAnimation("tentacles_body",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0, KeyframeAnimations.posVec(0, 0,  0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(1, KeyframeAnimations.posVec(0,1,0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2, KeyframeAnimations.posVec( 0, 0,  0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(3, KeyframeAnimations.posVec( 0, -1,  0),  AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(4, KeyframeAnimations.posVec( 0, 0,  0),  AnimationChannel.Interpolations.LINEAR)
                    ))
            .build();
    
}

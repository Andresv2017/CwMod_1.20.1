package net.andres.cassowarymod.entity.custom;


import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.animation.AnimationController;



public class CassowaryEntity extends PathfinderMob implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public static AttributeSupplier setAttributes(){
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 16D)
                .add(Attributes.ATTACK_DAMAGE, 3.0f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.4f)
                .build();
    }
    public CassowaryEntity(EntityType<? extends PathfinderMob> type, Level world) {
        super(type, world);

        // El Cassowary atacará al jugador al verlo.
        this.goalSelector.addGoal(1, new CustomMeleeAttackGoal(this, 1.0, true)); // Ataque cuerpo a cuerpo
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0)); // Se mueve por el mundo
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true)); // Atacar jugadores
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Mob.class, true)); // Atacar otros mobs
    }

    // Definimos un campo para el cooldown de ataque y el contador de ataques


    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 10, event -> {
            // Si la entidad está moviéndose, reproducir la animación de caminar
            if (event.isMoving()) {
                event.getController().setAnimation(RawAnimation.begin().thenPlay("walk"));
                return PlayState.CONTINUE;
            }

            // Si la entidad es agresiva (está atacando)
            if (this.isAggressive()) {
                // Ejecuta la animación de ataque con el pico (beak_attack)
                event.getController().setAnimation(RawAnimation.begin().thenPlay("beak_attack"));
                return PlayState.CONTINUE;
            }

            // Si no está atacando ni moviéndose, reproducir la animación de idle
            event.getController().setAnimation(RawAnimation.begin().thenPlay("idle"));
            return PlayState.CONTINUE;
        }));
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    private static class CustomMeleeAttackGoal extends MeleeAttackGoal {
        private final CassowaryEntity cassowary;

        public CustomMeleeAttackGoal(CassowaryEntity cassowary, double speedModifier, boolean followingTargetEvenIfNotSeen) {
            super(cassowary, speedModifier, followingTargetEvenIfNotSeen);
            this.cassowary = cassowary;
        }
    }



}


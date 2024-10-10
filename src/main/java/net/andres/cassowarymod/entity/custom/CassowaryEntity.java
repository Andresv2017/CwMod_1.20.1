package net.andres.cassowarymod.entity.custom;


import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;



public class CassowaryEntity extends Monster implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public static AttributeSupplier setAttributes(){
        return PathfinderMob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 16D)
                .add(Attributes.ATTACK_DAMAGE, 3.0f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .add(Attributes.MOVEMENT_SPEED, 0.4f)
                .build();
    }
    public CassowaryEntity(EntityType<? extends Monster> type, Level world) {
        super(type, world);

        // El Cassowary atacará al jugador al verlo.
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0, true)); // Ataque cuerpo a cuerpo
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0)); // Se mueve por el mundo
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true)); // Atacar jugadores
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Mob.class, true)); // Atacar otros mobs
    }

    private static final RawAnimation CS_SPRINT = RawAnimation.begin().thenLoop("walk");

    private static final RawAnimation CS_WALK = RawAnimation.begin().thenLoop("walk");

    private static final RawAnimation CS_SWIM = RawAnimation.begin().thenLoop("walk");

    private static final RawAnimation CS_IDLE = RawAnimation.begin().thenLoop("idle");



    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[] { new AnimationController((GeoAnimatable)this, "Normal", 5, this::movementPredicate) });
        //controllerRegistrar.add(new AnimationController[] { new AnimationController((GeoAnimatable)this, "attacking", 4, this::attackingPredicate) });
        //controllerRegistrar.add(new AnimationController[] { new AnimationController((GeoAnimatable)this, "procedure", 4, this::procedurePredicate) });
    }


    protected <E extends CassowaryEntity> PlayState movementPredicate(AnimationState<E> event) {
        if (this.getDeltaMovement().horizontalDistance() > 1.0E-6D && !this.isInWater()) {
            if (isSprinting()) {
                System.out.println("Ejecutando animación: SPRINT");
                event.setAndContinue(CS_SPRINT);
                event.getController().setAnimationSpeed(2.0D);
                return PlayState.CONTINUE;
            }
            if (event.isMoving()) {
                System.out.println("Ejecutando animación: WALK");
                event.setAndContinue(CS_WALK);
                event.getController().setAnimationSpeed(1.0D);
                return PlayState.CONTINUE;
            }
        }
        if (isInWater()) {
            System.out.println("Ejecutando animación: SWIM");
            event.setAndContinue(CS_SWIM);
            event.getController().setAnimationSpeed(1.0D);
            return PlayState.CONTINUE;
        }
        if (!isInWater()) {
            System.out.println("Ejecutando animación: IDLE");
            event.setAndContinue(CS_IDLE);
            event.getController().setAnimationSpeed(1.0D);
        }
        return PlayState.CONTINUE;
    }
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

}


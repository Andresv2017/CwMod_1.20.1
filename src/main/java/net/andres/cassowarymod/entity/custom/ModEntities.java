package net.andres.cassowarymod.entity.custom;

import net.andres.cassowarymod.CassowaryMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;

import java.util.logging.Level;

import static net.minecraftforge.registries.ForgeRegistries.Keys.ENTITY_TYPES;


public class ModEntities {


    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CassowaryMod.MODID);

    public static final RegistryObject<EntityType<CassowaryEntity>> CASSUWARY = ENTITY_TYPES.register("casuario",
            () -> EntityType.Builder.of(CassowaryEntity::new, MobCategory.CREATURE)
                    .sized(1.5f, 1.75f)
                    .build(String.valueOf(new ResourceLocation(CassowaryMod.MODID, "casuario"))));

        public static void register(IEventBus eventBus){
            ENTITY_TYPES.register(eventBus);
        }

}

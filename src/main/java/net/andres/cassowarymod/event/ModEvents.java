package net.andres.cassowarymod.event;

import net.andres.cassowarymod.CassowaryMod;
import net.andres.cassowarymod.entity.custom.CassowaryEntity;
import net.andres.cassowarymod.entity.custom.ModEntities;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = CassowaryMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event){
        event.put(ModEntities.CASSUWARY.get(), CassowaryEntity.setAttributes());
    }
}

package net.andres.cassowarymod.entity.client;

import net.andres.cassowarymod.CassowaryMod;
import net.andres.cassowarymod.entity.custom.CassowaryEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import javax.swing.text.html.parser.Entity;

public class CassowaryModel extends GeoModel<CassowaryEntity> {
    @Override
    public ResourceLocation getModelResource(CassowaryEntity cassowaryEntity) {
        return new ResourceLocation("cassowarymod", "geo/nm_cassowary.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CassowaryEntity object) {
        return new ResourceLocation("cassowarymod", "textures/entity/nm_cassowary_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CassowaryEntity cassowaryEntity) {
        return new ResourceLocation("cassowarymod", "animations/nm_cassowary.animation.json");
    }

    //@Override
    public void setCustomAnimations(CassowaryEntity animatable, long instanceId, AnimationState<CassowaryEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("h_head");
        if(head != null){
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}

package net.andres.cassowarymod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.andres.cassowarymod.CassowaryMod;
import net.andres.cassowarymod.entity.custom.CassowaryEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import javax.swing.text.html.parser.Entity;

public class CassowaryRenderer extends GeoEntityRenderer<CassowaryEntity> {
    public CassowaryRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new CassowaryModel());
    }

    @Override
    public ResourceLocation getTextureLocation(CassowaryEntity animatable) {
        return new ResourceLocation(CassowaryMod.MODID, "textures/entity/nm_cassowary_texture.png");
    }

    @Override
    public void render(CassowaryEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight){
        if(entity.isBaby()){
            poseStack.scale(0.4f, 0.4f,0.4f);
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

}


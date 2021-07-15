package net.silentchaos512.gear.client.model;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.math.vector.TransformationMatrix;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.BakedItemModel;
import net.minecraftforge.client.model.PerspectiveMapWrapper;

public class BakedPerspectiveModel extends BakedItemModel {
    private final ItemCameraTransforms cameraTransforms;

    @SuppressWarnings("ConstructorWithTooManyParameters")
    public BakedPerspectiveModel(ImmutableList<BakedQuad> quads,
                                 TextureAtlasSprite particle,
                                 ImmutableMap<ItemCameraTransforms.TransformType, TransformationMatrix> transforms,
                                 ItemOverrideList overrides,
                                 boolean untransformed,
                                 boolean isSideLit,
                                 ItemCameraTransforms cameraTransforms) {
        super(quads, particle, transforms, overrides, untransformed, isSideLit);
        this.cameraTransforms = cameraTransforms;
    }

    @Override
    public IBakedModel handlePerspective(ItemCameraTransforms.TransformType type, MatrixStack mat) {
        if (cameraTransforms != null) {
            return ForgeHooksClient.handlePerspective(this, type, mat);
        }
        return PerspectiveMapWrapper.handlePerspective(this, transforms, type, mat);
    }

    @Override
    public boolean doesHandlePerspectives() {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public ItemCameraTransforms getTransforms() {
        return cameraTransforms;
    }
}

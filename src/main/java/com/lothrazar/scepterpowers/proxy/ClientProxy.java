package com.lothrazar.scepterpowers.proxy;

import com.lothrazar.scepterpowers.ItemRegistry;
import com.lothrazar.scepterpowers.projectile.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.init.Items;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy 
{   

    @SuppressWarnings("rawtypes")
	@Override
    public void register() 
    {  
       	RenderManager rm = Minecraft.getMinecraft().getRenderManager();
    	RenderItem ri = Minecraft.getMinecraft().getRenderItem();
    	
    	//works similar to vanilla which is like
    	//Minecraft.getMinecraft().getRenderManager().entityRenderMap.put(EntitySoulstoneBolt.class, new RenderSnowball(Minecraft.getMinecraft().getRenderManager(), ItemRegistry.soulstone, Minecraft.getMinecraft().getRenderItem()));
/*
    	RenderingRegistry.registerEntityRenderingHandler(EntityLightningballBolt.class, new RenderSnowball(rm, ItemRegistry.ender_lightning, ri));
    	RenderingRegistry.registerEntityRenderingHandler(EntityHarvestBolt.class, new RenderSnowball(rm, ItemRegistry.ender_harvest, ri));
    	RenderingRegistry.registerEntityRenderingHandler(EntityWaterBolt.class, new RenderSnowball(rm, ItemRegistry.ender_water, ri));
    	RenderingRegistry.registerEntityRenderingHandler(EntitySnowballBolt.class, new RenderSnowball(rm, ItemRegistry.ender_snow, ri));
    	RenderingRegistry.registerEntityRenderingHandler(EntityTorchBolt.class, new RenderSnowball(rm, ItemRegistry.ender_torch, ri));
    	*/
    	RenderingRegistry.registerEntityRenderingHandler(EntityFishingBolt.class, new RenderSnowball(rm, Items.stick, ri));
    	
    	/*
    	RenderingRegistry.registerEntityRenderingHandler(EntityShearingBolt.class, new RenderSnowball(rm, ItemRegistry.ender_wool, ri));
    	//RenderingRegistry.registerEntityRenderingHandler(EntityHomeBolt.class, new RenderSnowball(rm, ItemRegistry.ender_bed, ri));
    	//RenderingRegistry.registerEntityRenderingHandler(EntityDungeonEye.class, new RenderSnowball(rm, ItemRegistry.ender_dungeon, ri));
    	RenderingRegistry.registerEntityRenderingHandler(EntityDynamite.class, new RenderSnowball(rm, ItemRegistry.ender_tnt_1, ri));
    	RenderingRegistry.registerEntityRenderingHandler(EntityBlazeBolt.class, new RenderSnowball(rm, ItemRegistry.ender_blaze, ri));
*/
    }
  

}

package com.lothrazar.scepterpowers;

import com.lothrazar.scepterpowers.potion.MessagePotion;
import com.lothrazar.scepterpowers.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger; 

@Mod(modid = ModScepterPowers.MODID, useMetadata=true)
public class ModScepterPowers
{
    public static final String MODID = "scepterpowers";	
    @Instance(value = MODID)
	public static ModScepterPowers instance;
	@SidedProxy(clientSide="com.lothrazar.scepterpowers.proxy.ClientProxy", serverSide="com.lothrazar.scepterpowers.proxy.CommonProxy")
	public static CommonProxy proxy;     
	public static Logger logger; 
	public static ModConfig cfg;
	public static SimpleNetworkWrapper network; 
	public static CreativeTabs tabSamsContent = new CreativeTabs("tabScepter") 
	{ 
		@Override
		public Item getTabIconItem() 
		{ 
			return Items.stick;//placeholder
		}
	};   
	
	@EventHandler
    public void preInit(FMLPreInitializationEvent  event)
    {
		logger = event.getModLog();  
		cfg = new ModConfig(new Configuration(event.getSuggestedConfigurationFile()));
		

    	network = NetworkRegistry.INSTANCE.newSimpleChannel( MODID );     
    	network.registerMessage(MessagePotion.class, MessagePotion.class, MessagePotion.ID, Side.CLIENT);
    }
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		ItemRegistry.register();
		BlockRegistry.register();
		ProjectileRegistry.register();
		
		proxy.register();
    }
}

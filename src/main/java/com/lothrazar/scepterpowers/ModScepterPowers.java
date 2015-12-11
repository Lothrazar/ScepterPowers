package com.lothrazar.scepterpowers;

import com.lothrazar.scepterpowers.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
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
		//cfg = new ConfigFile(new Configuration(event.getSuggestedConfigurationFile()));
    }
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		ItemRegistry.register();
		BlockRegistry.register();
    }
}

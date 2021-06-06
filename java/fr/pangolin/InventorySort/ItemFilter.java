package fr.pangolin.InventorySort;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MinecraftGame;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;


@Mod(ItemFilter.MODID)

public class ItemFilter {

    public static final String MODID = "itemfilter";
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS,ItemFilter.MODID);
    public int i = 0;
    public static boolean IsSorting;
    public static boolean BeenSorted;
    public List<Item> filterItems;
    public ItemFilter(){

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.addListener(this::filter);
        MinecraftForge.EVENT_BUS.addListener(this::pressSorting);
        filterItems = Lists.newArrayList();
    }

    private void setup(FMLCommonSetupEvent event){

    }

    private void clientSetup(FMLClientSetupEvent event){
        IsSorting = false;
        BeenSorted = true;
        filterItems.add(Items.STONE);
        filterItems.add(Items.GRAVEL);
        filterItems.add(Items.GRANITE);
    }

    private void filter(EntityItemPickupEvent event){
        if(filterItems.contains(event.getItem().getItem().getItem())){
            event.setCanceled(true);
            event.getItem().remove();
        }
    }
    @OnlyIn(Dist.CLIENT)
    public void pressSorting(InputEvent.KeyInputEvent event){

        if(event.getKey() == 80 && !IsSorting ){
            System.out.println("Register !");
            SortEvent.IsSorting = true;
            MinecraftForge.EVENT_BUS.register(SortEvent.class);
        }
    }

    public static void unregisterFilter(){
        System.out.println("Unregister !");
        MinecraftForge.EVENT_BUS.unregister(SortEvent.class);
    }
}

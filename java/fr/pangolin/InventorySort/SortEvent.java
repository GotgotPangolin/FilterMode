package fr.pangolin.InventorySort;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
public class SortEvent {
    static int i = 0;
    static boolean IsSorting = false;
    boolean sorted;
    PlayerInventory inventory;
    public SortEvent(PlayerInventory inventory){
        this.inventory = inventory;
    }

    @SubscribeEvent
    public void filter(TickEvent.PlayerTickEvent event){
        sorted = true;
        if(IsSorting && i < inventory.items.size()-1){
            if(Item.getId(inventory.items.get(i+9).getItem()) > Item.getId(inventory.items.get(i+10).getItem())){

            }
        }
        else {
            i = 0;
            IsSorting = false;
            ItemFilter.unregisterFilter();
        }
        i++;
    }
}

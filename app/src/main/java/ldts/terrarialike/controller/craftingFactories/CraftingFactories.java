package ldts.terrarialike.controller.craftingFactories;

import java.util.List;

public class CraftingFactories {


    public static final List<CraftingFactoryInteface> craftingFactoryInterfaces = List.of(
            new PickaxeFactory(), new WoodenItemsFactory(), new SwordFactory(), new OreIngotFactory()
    );

}

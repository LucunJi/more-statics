package github.io.lucunji.morestatics;

import net.fabricmc.api.ModInitializer;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class MoreStatics implements ModInitializer {
    public static final String MOD_ID = "morestatics";

    public static Identifier BREAK_BEDROCK;

    @Override
    public void onInitialize() {
        Identifier identifierBreakBedrock = new Identifier( "break_bedrock");
        Registry.register(Registry.CUSTOM_STAT, "break_bedrock", identifierBreakBedrock);
        Stats.CUSTOM.getOrCreateStat(identifierBreakBedrock, StatFormatter.DEFAULT);
        BREAK_BEDROCK = identifierBreakBedrock;
    }
}

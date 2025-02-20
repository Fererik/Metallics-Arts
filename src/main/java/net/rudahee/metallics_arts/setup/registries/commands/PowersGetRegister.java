package net.rudahee.metallics_arts.setup.registries.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.rudahee.metallics_arts.data.enums.implementations.MetalTagEnum;
import net.rudahee.metallics_arts.utils.CapabilityUtils;

import java.util.Collection;
import java.util.List;

public class PowersGetRegister {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("ma-powers").requires(commandSource -> commandSource.hasPermission(2))
                .then(Commands.literal("get")
                        .then(Commands.literal("all")
                                .executes(context -> getAllPower(context, "all", List.of(context.getSource().getPlayerOrException())))
                                .then(Commands.argument("target", EntityArgument.players())
                                        .executes(context -> getAllPower(context,"all", EntityArgument.getPlayers(context, "target")))))

                        .then(Commands.literal("allomantic")
                                .then(Commands.literal("all")
                                        .executes(context -> getAllPower(context, "allomantic", List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getAllPower(context,"allomantic", EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("steel")
                                        .executes(context -> getAllPower(context, "steel", List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.STEEL, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("iron")
                                        .executes(context -> getAllPower(context, "iron", List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.IRON, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("tin")
                                        .executes(context -> getAllPower(context, "tin", List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.TIN, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("pewter")
                                        .executes(context -> getAllPower(context, "pewter", List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.PEWTER, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("copper")
                                        .executes(context -> getAllPower(context, "copper", List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.COPPER, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("bronze")
                                        .executes(context -> getAllPower(context, "bronze", List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.BRONZE, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("zinc")
                                        .executes(context -> getAllPower(context, "zinc", List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.ZINC, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("brass")
                                        .executes(context -> getAllPower(context, "brass", List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.BRASS, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("chromium")
                                        .executes(context -> getAllPower(context, "chromium", List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.CHROMIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("nicrosil")
                                        .executes(context -> getAllPower(context, "nicrosil", List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.NICROSIL, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("aluminum")
                                        .executes(context -> getAllPower(context, "aluminum", List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.ALUMINUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("duralumin")
                                        .executes(context -> getAllPower(context, "duralumin", List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.DURALUMIN, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("cadmium")
                                        .executes(context -> getAllPower(context, "cadmium", List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.CADMIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("bendalloy")
                                        .executes(context -> getAllPower(context, "bendalloy", List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.BENDALLOY, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("electrum")
                                        .executes(context -> getAllPower(context, "electrum", List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.ELECTRUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("gold")
                                        .executes(context -> getAllPower(context, "gold", List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.GOLD, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("atium")
                                        .executes(context -> getAllPower(context, "atium", List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.ATIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("malatium")
                                        .executes(context -> getAllPower(context, "malatium", List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.MALATIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("lerasium")
                                        .executes(context -> getAllPower(context, "lerasium", List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.LERASIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("ettmetal")
                                        .executes(context -> getAllPower(context, "ettmetal", List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getAllomanticPower(context, MetalTagEnum.ETTMETAL, EntityArgument.getPlayers(context, "target"))))
                                )
                        )
                        .then(Commands.literal("feruchemic")
                                .then(Commands.literal("all")
                                        .executes(context -> getAllPower(context, "all", List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getAllPower(context,"feruchemic", EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("steel")
                                        .executes(context -> getFeruchemicPower(context, MetalTagEnum.STEEL, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.STEEL, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("iron")
                                        .executes(context -> getFeruchemicPower(context, MetalTagEnum.IRON, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.IRON, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("tin")
                                        .executes(context -> getFeruchemicPower(context, MetalTagEnum.TIN, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.TIN, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("pewter")
                                        .executes(context -> getFeruchemicPower(context, MetalTagEnum.PEWTER, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.PEWTER, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("copper")
                                        .executes(context -> getFeruchemicPower(context, MetalTagEnum.COPPER, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.COPPER, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("bronze")
                                        .executes(context -> getFeruchemicPower(context, MetalTagEnum.BRONZE, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.BRONZE, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("zinc")
                                        .executes(context -> getFeruchemicPower(context, MetalTagEnum.ZINC, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.ZINC, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("brass")
                                        .executes(context -> getFeruchemicPower(context, MetalTagEnum.BRASS, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.BRASS, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("chromium")
                                        .executes(context -> getFeruchemicPower(context, MetalTagEnum.CHROMIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.CHROMIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("nicrosil")
                                        .executes(context -> getFeruchemicPower(context, MetalTagEnum.NICROSIL, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.NICROSIL, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("aluminum")
                                        .executes(context -> getFeruchemicPower(context, MetalTagEnum.ALUMINUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.ALUMINUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("duralumin")
                                        .executes(context -> getFeruchemicPower(context, MetalTagEnum.DURALUMIN, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.DURALUMIN, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("cadmium")
                                        .executes(context -> getFeruchemicPower(context, MetalTagEnum.CADMIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.CADMIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("bendalloy")
                                        .executes(context -> getFeruchemicPower(context, MetalTagEnum.BENDALLOY, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.BENDALLOY, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("electrum")
                                        .executes(context -> getFeruchemicPower(context, MetalTagEnum.ELECTRUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.ELECTRUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("gold")
                                        .executes(context -> getFeruchemicPower(context, MetalTagEnum.GOLD, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.GOLD, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("atium")
                                        .executes(context -> getFeruchemicPower(context, MetalTagEnum.ATIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.ATIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("malatium")
                                        .executes(context -> getFeruchemicPower(context, MetalTagEnum.MALATIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.MALATIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("lerasium")
                                        .executes(context -> getFeruchemicPower(context, MetalTagEnum.LERASIUM, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.LERASIUM, EntityArgument.getPlayers(context, "target"))))
                                )
                                .then(Commands.literal("ettmetal")
                                        .executes(context -> getFeruchemicPower(context, MetalTagEnum.ETTMETAL, List.of(context.getSource().getPlayerOrException())))
                                        .then(Commands.argument("target",EntityArgument.players())
                                                .executes(context -> getFeruchemicPower(context, MetalTagEnum.ETTMETAL, EntityArgument.getPlayers(context, "target"))))
                                )
                        )
                ));

    }

    private static int getAllomanticPower (CommandContext<CommandSourceStack> context, MetalTagEnum metalTagEnum, Collection<ServerPlayer> players) {

        for (ServerPlayer player: players) {
            if (CapabilityUtils.getCapability(player).hasAllomanticPower(metalTagEnum)) {
                context.getSource().sendSystemMessage(Component.translatable(player.getScoreboardName() + " has " + metalTagEnum.getNameLower()));
            } else {
                context.getSource().sendSystemMessage(Component.translatable(player.getScoreboardName() + " doesn't have " + metalTagEnum.getNameLower()));
            }
        }

        return 1;
    }
    private static int getFeruchemicPower (CommandContext<CommandSourceStack> context, MetalTagEnum metalTagEnum, Collection<ServerPlayer> players) {

        for (ServerPlayer player: players) {
            if (CapabilityUtils.getCapability(player).hasFeruchemicPower(metalTagEnum)) {
                context.getSource().sendSystemMessage(Component.translatable(player.getScoreboardName() + " has " + metalTagEnum.getNameLower()));
            } else {
                context.getSource().sendSystemMessage(Component.translatable(player.getScoreboardName() + " doesn't have " + metalTagEnum.getNameLower()));
            }
        }

        return 1;
    }
    private static int getAllPower (CommandContext<CommandSourceStack> context, String type, Collection<ServerPlayer> players) {
        StringBuilder allomanticPowersStr;
        StringBuilder feruchemicPowersStr;

        for (ServerPlayer player: players) {

            if (type.equals("allomantic") || type.equals("all")) {
                allomanticPowersStr = new StringBuilder();
                boolean firstLoopAllomantic = true;

                List<MetalTagEnum> allomanticPowers = CapabilityUtils.getCapability(player).getAllomanticPowers();
                for (MetalTagEnum power: allomanticPowers) {
                    if (firstLoopAllomantic) {
                        allomanticPowersStr.append(" ").append(power.getNameLower());
                        firstLoopAllomantic = false;
                    } else {
                        allomanticPowersStr.append(", ").append(power.getNameLower());
                    }
                }

                context.getSource().sendSystemMessage(Component.translatable(player.getScoreboardName() + " has  allomantics " + allomanticPowersStr));
            }

            if (type.equals("feruchemic") || type.equals("all")) {
                feruchemicPowersStr = new StringBuilder();
                boolean firstLoopFeruchemic = true;

                List<MetalTagEnum> feruchemicPowers = CapabilityUtils.getCapability(player).getFeruchemicPowers();
                for (MetalTagEnum power: feruchemicPowers) {
                    if (firstLoopFeruchemic) {
                        feruchemicPowersStr.append(" ").append(power.getNameLower());
                        firstLoopFeruchemic = false;
                    } else {
                        feruchemicPowersStr.append(", ").append(power.getNameLower());
                    }
                }

                context.getSource().sendSystemMessage(Component.translatable(player.getScoreboardName() + " has  feruchemics " + feruchemicPowersStr));
            }
        }

        return 1;
    }

}

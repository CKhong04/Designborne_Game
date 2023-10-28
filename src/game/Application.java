package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.actors.enemies.bosses.Abxervyer;
import game.actors.traders.Blacksmith;
import game.actors.traders.Traveller;
import game.actors.traders.conversations.Monologue;
import game.actors.traders.conversations.conditions.ActorIsConsciousCondition;
import game.actors.traders.conversations.conditions.ActorIsHoldingWeaponItemCondition;
import game.actors.traders.conversations.conditions.ActorIsUnconsciousCondition;
import game.enums.Status;
import game.grounds.*;
import game.grounds.spawninggrounds.*;
import game.items.BloodBerry;
import game.utilities.FancyMessage;
import game.utilities.Map;
import game.weapons.Broadsword;
import game.weapons.GiantHammer;
import game.weathers.SunnyWeather;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Laura Zhakupova, Carissa Khong, Ishita Gupta
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new BottomlessPit());

        // Set up abandoned village map
        GameMap gameMap = new GameMap(groundFactory, Map.ABANDONED_VILLAGE_MAP);
        world.addGameMap(gameMap);

        gameMap.at(27, 5).addItem(new Broadsword());

        gameMap.at(55,2).setGround(new WanderingUndeadGraveyard());
        gameMap.at(34,10).setGround(new WanderingUndeadGraveyard());
        gameMap.at(28,0).setGround(new Floor());

        // Set up burial ground map
        GameMap burialGroundGameMap = new GameMap(groundFactory, Map.BURIAL_GROUND_MAP);
        world.addGameMap(burialGroundGameMap);

        burialGroundGameMap.at(23,2).setGround(new HollowSoldierGraveyard());
        burialGroundGameMap.at(13,11).setGround(new HollowSoldierGraveyard());
        burialGroundGameMap.at(2,14).setGround(new HollowSoldierGraveyard());
        burialGroundGameMap.at(37,14).setGround(new Floor());
        burialGroundGameMap.at(5, 6).setGround(new Floor());

        // Set up gates
        Gate villageToBurialGroundGate = new Gate();
        gameMap.at(27,0).setGround(villageToBurialGroundGate);
        villageToBurialGroundGate.addMoveAction(new MoveActorAction(burialGroundGameMap.at(37, 14), "to the Burial Ground."));

        Gate groundToVillageGate = new Gate();
        burialGroundGameMap.at(38,14).setGround(groundToVillageGate);
        groundToVillageGate.addMoveAction(new MoveActorAction(gameMap.at(28, 0), "to the Abandoned Village."));

        // Set up the ancient woods map
        GameMap ancientWoodsGameMap = new GameMap(groundFactory, Map.ANCIENT_WOODS_MAP);
        world.addGameMap(ancientWoodsGameMap);

        SunnyWeather sunnyWeather = new SunnyWeather();

        // Add the bushes and huts
        ancientWoodsGameMap.at(11, 3).setGround(new RedWolfBush(sunnyWeather));

        ancientWoodsGameMap.at(46,9).setGround(new ForestKeeperHut(sunnyWeather));
        ancientWoodsGameMap.at(8, 7).setGround(new ForestKeeperHut(sunnyWeather));
        ancientWoodsGameMap.at(26,6).setGround(new Floor());
        ancientWoodsGameMap.at(44,4).setGround(new Floor());

        ancientWoodsGameMap.at(47,1).addItem(new BloodBerry());

        // Set up gates
        Gate burialGroundToWoodsGate = new Gate();
        burialGroundToWoodsGate.addMoveAction(new MoveActorAction(ancientWoodsGameMap.at(26,6),"to the Ancient Woods."));
        burialGroundGameMap.at(4,6).setGround(burialGroundToWoodsGate);

        Gate woodsToBurialGroundGate = new Gate();
        woodsToBurialGroundGate.addMoveAction(new MoveActorAction(burialGroundGameMap.at(5,6),"to the Burial Ground."));
        ancientWoodsGameMap.at(27,6).setGround(woodsToBurialGroundGate);

        // Set up the room map in ancient woods
        GameMap roomGameMap = new GameMap(groundFactory, Map.ROOM_MAP);
        world.addGameMap(roomGameMap);

        // Adding the bushes and huts
        roomGameMap.at(30, 2).setGround(new RedWolfBush(sunnyWeather));

        roomGameMap.at(19,10).setGround(new ForestKeeperHut(sunnyWeather));
        roomGameMap.at(2, 11).setGround(new ForestKeeperHut(sunnyWeather));
        roomGameMap.at(17, 13).setGround(new Floor());

        Item giantHammer = new GiantHammer();
        roomGameMap.at(27, 6).addItem(giantHammer);

        // Adding gates for access to the room
        Gate woodsToRoomGate = new Gate();
        woodsToRoomGate.addMoveAction(new MoveActorAction(roomGameMap.at(17,13),"to the room deep in the Woods."));
        ancientWoodsGameMap.at(44,3).setGround(woodsToRoomGate);

        // Set up overgrown sanctuary map
        GameMap overgrownSanctuaryGameMap = new GameMap(groundFactory, Map.OVERGROWN_SANCTUARY_MAP);
        world.addGameMap(overgrownSanctuaryGameMap);

        //Add the relevant spawning grounds
        overgrownSanctuaryGameMap.at(8, 12).setGround(new HollowSoldierGraveyard());
        overgrownSanctuaryGameMap.at(42, 5).setGround(new GuardianHut());
        overgrownSanctuaryGameMap.at(22, 9).setGround(new LivingBranchBush());
        overgrownSanctuaryGameMap.at(31, 2).setGround(new Floor());

        // Create the gate which Abxervyer will set once dead
        Gate roomToOtherDestinationsGate = new Gate();
        roomToOtherDestinationsGate.addMoveAction(new MoveActorAction(ancientWoodsGameMap.at(44,4), "to the Ancient Woods."));
        roomToOtherDestinationsGate.addMoveAction(new MoveActorAction(overgrownSanctuaryGameMap.at(31, 2), "to the Overgrown Sanctuary."));

        // Add the boss to the room
        Abxervyer abxervyer = new Abxervyer(roomToOtherDestinationsGate, sunnyWeather);
        roomGameMap.at(35,1).addActor(abxervyer);

        // Add a gate from the sanctuary back to the room
        Gate sanctuaryToRoomGate = new Gate();
        sanctuaryToRoomGate.addMoveAction(new MoveActorAction(roomGameMap.at(17,13), "to the room deep in the Woods."));
        overgrownSanctuaryGameMap.at(10, 4).setGround(sanctuaryToRoomGate);

        // Add player
        Player player = new Player("The Abstracted One", '@', 150, 200, gameMap);
        world.addPlayer(player, gameMap.at(29, 5));

        // Add the monologues the blacksmith can use.
        List<Monologue> blacksmithMonologues = Arrays.asList(
                new Monologue("I used to be an adventurer like you, but then .... Nevermind, let’s get back to smithing."),
                new Monologue("It’s dangerous to go alone. Take my creation with you on your adventure!"),
                new Monologue("Ah, it’s you. Let’s get back to make your weapons stronger."),
                new Monologue("Beyond the burial ground, you’ll come across the ancient woods ruled by Abxervyer. Use my creation to slay them and proceed further!", List.of(new ActorIsConsciousCondition(abxervyer))),
                new Monologue("Somebody once told me that a sacred tree rules the land beyond the ancient woods until this day.", List.of(new ActorIsUnconsciousCondition(abxervyer))),
                new Monologue("Hey now, that’s a weapon from a foreign land that I have not seen for so long. I can upgrade it for you if you wish.", List.of(new ActorIsHoldingWeaponItemCondition(player, Status.HOLDING_GREAT_KNIFE)))
        );

        Blacksmith blacksmith = new Blacksmith(blacksmithMonologues);
        gameMap.at(27, 6).addActor(blacksmith);

        //Add Traveller
        List<Monologue> travellerMonologues = Arrays.asList(
                new Monologue("Of course, I will never give you up, valuable customer!"),
                new Monologue("I promise I will never let you down with the quality of the items that I sell."),
                new Monologue("You can always find me here. I'm never gonna run around and desert you, dear customer!"),
                new Monologue("I'm never gonna make you cry with unfair prices."),
                new Monologue("Trust is essential in this business. I promise I’m never gonna say goodbye to a valuable customer like you."),
                new Monologue("Don't worry, I’m never gonna tell a lie and hurt you."),
                new Monologue("Ooh, that’s a fascinating weapon you got there. I will pay a good price for it. You wouldn't get this price from any other guy.", List.of(new ActorIsConsciousCondition(abxervyer),new ActorIsHoldingWeaponItemCondition(player, Status.HOLDING_GIANT_HAMMER))),
                new Monologue("You know the rules of this world, and so do I. Each area is ruled by a lord. Defeat the lord of this area, Abxervyer, and you may proceed to the next area.", List.of(new ActorIsConsciousCondition(abxervyer))),
                new Monologue("Congratulations on defeating the lord of this area. I noticed you still hold on to that hammer. Why don’t you sell it to me? We've known each other for so long. I can tell you probably don’t need that weapon any longer.", List.of(new ActorIsUnconsciousCondition(abxervyer),new ActorIsHoldingWeaponItemCondition(player, Status.HOLDING_GIANT_HAMMER)))
        );

        Traveller traveller = new Traveller(travellerMonologues);
        ancientWoodsGameMap.at(6, 3).addActor(traveller);

        // Print starting message
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        world.run();
    }
}

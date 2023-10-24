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
import game.grounds.spawners.*;
import game.items.BloodBerry;
import game.utilities.FancyMessage;
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

        // Set up starting map
        List<String> map = Arrays.asList(
                "...........................................................",
                "...#######...................................++++..........",
                "...#__.......................................+++++++.......",
                "...#..___#.....................................+++.........",
                "...###.###................#######................+++.......",
                "..........................#_____#.................+........",
                "........~~................#_____#................++........",
                ".........~~~..............###_###..........................",
                "...~~~~~~~~....+++.........................................",
                "....~~~~~........+++++++..................###..##...++++...",
                "~~~~~~~..............+++..................#___..#...++.....",
                "~~~~~~.................++.................#..___#....+++...",
                "~~~~~~~~~.................................#######.......++.");

        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);

        Item broadsword = new Broadsword();
        gameMap.at(27, 5).addItem(broadsword);

        gameMap.at(55,2).setGround(new WanderingUndeadGraveyard());
        gameMap.at(34,10).setGround(new WanderingUndeadGraveyard());

        // Set up second map
        List<String> burialGroundMap = Arrays.asList(
                "...........+++++++........~~~~~~++....~~",
                "...........++++++.........~~~~~~+.....~~",
                "............++++...........~~~~~......++",
                "............+.+.............~~~.......++",
                "..........++~~~.......................++",
                ".........+++~~~....#######...........+++",
                ".........++++~.....#_____#.........+++++",
                "..........+++......#_____#........++++++",
                "..........+++......###_###.......~~+++++",
                "..........~~.....................~~...++",
                "..........~~~..................++.......",
                "...........~~....~~~~~.........++.......",
                "......~~....++..~~~~~~~~~~~......~......",
                "....+~~~~..++++++++~~~~~~~~~....~~~.....",
                "....+~~~~..++++++++~~~..~~~~~..~~~~~....");

        GameMap burialGroundGameMap = new GameMap(groundFactory,burialGroundMap);
        world.addGameMap(burialGroundGameMap);

        burialGroundGameMap.at(23,2).setGround(new HollowSoldierGraveyard());
        burialGroundGameMap.at(13,11).setGround(new HollowSoldierGraveyard());
        burialGroundGameMap.at(2,14).setGround(new HollowSoldierGraveyard());

        // Set up gates
        Gate villageToBurialGroundGate = new Gate();
        gameMap.at(27,0).setGround(villageToBurialGroundGate);
        villageToBurialGroundGate.addMoveAction(new MoveActorAction(burialGroundGameMap.at(39, 14), "to the Burial Ground."));

        Gate groundToVillageGate = new Gate();
        burialGroundGameMap.at(38,14).setGround(groundToVillageGate);
        groundToVillageGate.addMoveAction(new MoveActorAction(gameMap.at(27, 0), "to the Abandoned Village."));

        //Create the Ancient Woods Map
        List<String> ancientWoodsMap = Arrays.asList(
                "...............++++++..............++++++..................",
                "...#######.......+++++...~~~~~...++++......................",
                "...#__...#........++++.....~~~..+++++++....###.............",
                "...#..___#.....+++........~~~~~.....++++...#.#......++++...",
                "...###_###......+...........~~~....++++...........+++.+....",
                ".....................................+++.............++.+..",
                "++......~~..........................+++.........+++++++....",
                "++++.....~~~..............###_###....+.............++++....",
                ".+++++~~~~~.....+++++.....#.___.#.....................++...",
                "....+++++.....++++........#....._.......................+..",
                "~~~~~+++........+++.......#######...................+.++...",
                "~~~~~~..........++.................................+++.....");
        GameMap ancientWoodsGameMap = new GameMap(groundFactory, ancientWoodsMap);
        world.addGameMap(ancientWoodsGameMap);

        SunnyWeather sunnyWeather = new SunnyWeather();

        //Add the bushes and huts to the Ancient Woods map
        ancientWoodsGameMap.at(11, 3).setGround(new RedWolfBush(sunnyWeather));

        ancientWoodsGameMap.at(46,9).setGround(new ForestKeeperHut(sunnyWeather));
        ancientWoodsGameMap.at(8, 7).setGround(new ForestKeeperHut(sunnyWeather));

        ancientWoodsGameMap.at(47,1).addItem(new BloodBerry());

        //Add the gates to and from this place
        Gate burialGroundToWoodsGate = new Gate();
        burialGroundToWoodsGate.addMoveAction(new MoveActorAction(ancientWoodsGameMap.at(26,6),"to the Ancient Woods."));
        burialGroundGameMap.at(4,6).setGround(burialGroundToWoodsGate);

        Gate woodsToBurialGroundGate = new Gate();
        woodsToBurialGroundGate.addMoveAction(new MoveActorAction(burialGroundGameMap.at(5,6),"to the Burial Ground."));
        ancientWoodsGameMap.at(27,6).setGround(woodsToBurialGroundGate);

        //Add Traveller
        Traveller traveller = new Traveller();
        ancientWoodsGameMap.at(6, 3).addActor(traveller);

        //Creating the room in ancient woods
        List<String> roomMap = Arrays.asList(
                "########################################",
                "#~~~.......+++......~+++++.............#",
                "#~~~.......+++.......+++++.............#",
                "#~~++......+++........++++.............#",
                "#~~++......++...........+..............#",
                "#~~~~~...........+.......~~~++........+#",
                "#~~~~~..........++++....~~~~++++....+++#",
                "#~~~~~...........+++++++~~~~.++++....++#",
                "#~~~~..............++++++~~...+++.....+#",
                "#......._______.......+++......++.....+#",
                "#.........___._........+~~............+#",
                "#..........___.........~~~~...........+#",
                "#.......................~~++...........#",
                "#....++++...............+++++..........#",
                "#....++++~..............+++++........++#",
                "#.....+++~~.............++++...........#",
                "#......++..++++.....................~~~#",
                "#..........+++++.....................~~#",
                "#..........++++++..................~~~~#",
                "#.........~~+++++....................~~#",
                "########################################"
        );
        GameMap roomGameMap = new GameMap(groundFactory, roomMap);
        world.addGameMap(roomGameMap);

        // Adding the bushes and huts to the Room
        roomGameMap.at(30, 2).setGround(new RedWolfBush(sunnyWeather));

        roomGameMap.at(19,10).setGround(new ForestKeeperHut(sunnyWeather));
        roomGameMap.at(2, 11).setGround(new ForestKeeperHut(sunnyWeather));

        Item giantHammer = new GiantHammer();
        roomGameMap.at(27, 6).addItem(giantHammer);

        //Adding gates for access to the room
        Gate woodsToRoomGate = new Gate();
        woodsToRoomGate.addMoveAction(new MoveActorAction(roomGameMap.at(17,13),"to the room deep in the Woods."));
        ancientWoodsGameMap.at(44,3).setGround(woodsToRoomGate);

        //Add the new Overgrown Sanctuary game map
        List<String> overgrownSanctuaryMap = Arrays.asList(
        "++++.....++++........++++~~~~~.......~~~..........",
        "++++......++.........++++~~~~.........~...........",
        "+++............~~~...+++++~~.......+++............",
        "..............~~~...++++++......++++++............",
        "................~~.++++........++++++~~...........",
        "..............~~~..+++.........+++..~~~...........",
        "..................+++..........++...~~~...........",
        "~~~...........................~~~..~~~~.....~~~~..",
        "~~~~............+++..........~~~~~~~~~~.......~~..",
        "~~~~............+++.........~~~~~~~~~~~~..........",
        "++~..............+++.......+~~........~~..........",
        "+++..............+++......+++..........~~.........",
        "+++..............+++......+++..........~~.........",
        "~~~..............+++......+++..........~~~........",
        "~~~~.............+++......+++..........~~~........"
        );
        GameMap overgrownSanctuaryGameMap = new GameMap(groundFactory, overgrownSanctuaryMap);
        world.addGameMap(overgrownSanctuaryGameMap);

        //Add the relevant spawning grounds
        overgrownSanctuaryGameMap.at(8, 12).setGround(new HollowSoldierGraveyard());
        overgrownSanctuaryGameMap.at(42, 5).setGround(new GuardianHut());
        overgrownSanctuaryGameMap.at(22, 9).setGround(new LivingBranchBush());

        //Create the gate which Abxervyer will set once dead
        Gate roomToOtherDestinationsGate = new Gate();
        roomToOtherDestinationsGate.addMoveAction(new MoveActorAction(ancientWoodsGameMap.at(45,3), "to the Ancient Woods."));
        roomToOtherDestinationsGate.addMoveAction(new MoveActorAction(overgrownSanctuaryGameMap.at(31, 2), "to the Overgrown Sanctuary."));

        // Add the boss to the room
        Abxervyer abxervyer = new Abxervyer(roomToOtherDestinationsGate, sunnyWeather);
        roomGameMap.at(35,1).addActor(abxervyer);

        //Add a gate from the sanctuary back to the room
        Gate sanctuaryToRoomGate = new Gate();
        sanctuaryToRoomGate.addMoveAction(new MoveActorAction(roomGameMap.at(17,13), "to the room deep in the Woods."));
        overgrownSanctuaryGameMap.at(10, 4).setGround(sanctuaryToRoomGate);

        // Print starting message
        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        // Add player
        Player player = new Player("The Abstracted One", '@', 150, 200, gameMap);
        world.addPlayer(player, gameMap.at(29, 5));

        // Add the monologues the blacksmith can use.
        List<Monologue> monologues = Arrays.asList(
                new Monologue("I used to be an adventurer like you, but then .... Nevermind, let’s get back to smithing."),
                new Monologue("It’s dangerous to go alone. Take my creation with you on your adventure!"),
                new Monologue("Ah, it’s you. Let’s get back to make your weapons stronger."),
                new Monologue("Beyond the burial ground, you’ll come across the ancient woods ruled by Abxervyer. Use my creation to slay them and proceed further!", List.of(new ActorIsConsciousCondition(abxervyer))),
                new Monologue("Somebody once told me that a sacred tree rules the land beyond the ancient woods until this day.", List.of(new ActorIsUnconsciousCondition(abxervyer))),
                new Monologue("Hey now, that’s a weapon from a foreign land that I have not seen for so long. I can upgrade it for you if you wish.", List.of(new ActorIsHoldingWeaponItemCondition(player, Status.HOLDING_GREAT_KNIFE)))
        );

        // Add the blacksmith
        Blacksmith blacksmith = new Blacksmith(monologues);
        gameMap.at(31, 6).addActor(blacksmith);

        world.run();
    }
}

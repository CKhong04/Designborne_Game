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
import game.actors.enemies.Abxervyer;
import game.actors.traders.Blacksmith;
import game.actors.traders.Traveller;
import game.actors.traders.conversations.Monologue;
import game.actors.traders.conversations.conditions.ActorIsConsiousCondition;
import game.actors.traders.conversations.conditions.ActorIsHoldingWeaponItemCondition;
import game.actors.traders.conversations.conditions.ActorIsUnconsiousCondition;
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

        // Set up starting map
        List<String> map = Arrays.asList(
                "...........................................................",
                "...#######...................................++++..........",
                "...#__.......................................+++++++.......",
                "...#..___#.....................................+++.........",
                "...###.###................#######...........n....+++.......",
                ".................n........#_____#.................+........",
                "........~~................#_____#................++........",
                ".........~~~..............###_###..........................",
                "...~~~~~~~~....+++.........................................",
                "....~~~~~........+++++++..................###..##...++++...",
                "~~~~~~~..............+++..................#___..#...++.....",
                "~~~~~~.................++.................#..___#....+++...",
                "~~~~~~~~~.................................#######.......++.");

        FancyGroundFactory abandonedVillageGroundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new BottomlessPit(), new WanderingUndeadGraveyard());

        GameMap abandonedVillageGameMap = new GameMap(abandonedVillageGroundFactory, map);
        world.addGameMap(abandonedVillageGameMap);

        Item broadsword = new Broadsword();
        abandonedVillageGameMap.at(27, 5).addItem(broadsword);

        // Set up second map
        List<String> burialGroundMap = Arrays.asList(
                "...........+++++++........~~~~~~++....~~",
                "...........++++++.........~~~~~~+.....~~",
                "............++++...n.......~~~~~......++",
                "............+.+.............~~~.......++",
                "..........++~~~.......................++",
                ".........+++~~~....#######...........+++",
                ".........++++~.....#_____#.........+++++",
                "..........+++......#_____#........++++++",
                "....n.....+++......###_###.......~~+++++",
                "..........~~.....................~~...++",
                "..........~~~...........n......++.......",
                "...........~~....~~~~~.........++.......",
                "......~~....++..~~~~~~~~~~~......~......",
                "....+~~~~..++++++++~~~~~~~~~....~~~.....",
                "....+~~~~..++++++++~~~..~~~~~..~~~~~....");

        Gate villageToBurialGroundGate = new Gate();

        FancyGroundFactory burialGroundGroundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new BottomlessPit(), new HollowSoldierGraveyard(), villageToBurialGroundGate);

        GameMap burialGroundGameMap = new GameMap(burialGroundGroundFactory, burialGroundMap);
        world.addGameMap(burialGroundGameMap);

        // Set up gates
        villageToBurialGroundGate.addMoveAction(new MoveActorAction(burialGroundGameMap.at(39, 14), "to the Burial Ground."));

        Gate groundToVillageGate = new Gate();
        burialGroundGameMap.at(38,14).setGround(groundToVillageGate);
        groundToVillageGate.addMoveAction(new MoveActorAction(abandonedVillageGameMap.at(27, 0), "to the Abandoned Village."));

        //Create the Ancient Woods Map
        List<String> ancientWoodsMap = Arrays.asList(
                "...............++++++..............++++++..................",
                "...#######.......+++++...~~~~~...++++......................",
                "...#__...#........++++.....~~~..+++++++....###.............",
                "...#..___#.....+++........~~~~~.....++++...#=#......++++...",
                "...###_###......+...........~~~....++++...........+++.+....",
                "..........................=..........+++.............++.+..",
                "++......~~..........................+++.........+++++++....",
                "++++.....~~~..............###_###....+.............++++....",
                ".+++++~~~~~.....+++++.....#.___.#.....................++...",
                "....+++++.....++++........#....._.......................+..",
                "~~~~~+++........+++.......#######...................+.++...",
                "~~~~~~..........++.................................+++.....");

        Gate burialGroundToWoodsGate = new Gate();
        Gate woodsToBurialGroundGate = new Gate();

        FancyGroundFactory ancientWoodsGroundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new BottomlessPit(), burialGroundToWoodsGate, woodsToBurialGroundGate);

        GameMap ancientWoodsGameMap = new GameMap(ancientWoodsGroundFactory, ancientWoodsMap);
        world.addGameMap(ancientWoodsGameMap);

        SunnyWeather sunnyWeather = new SunnyWeather();

        //Add the bushes and huts to the Ancient Woods map
        ancientWoodsGameMap.at(11, 3).setGround(new RedWolfBush(sunnyWeather));

        ancientWoodsGameMap.at(46,9).setGround(new ForestKeeperHut(sunnyWeather));
        ancientWoodsGameMap.at(8, 7).setGround(new ForestKeeperHut(sunnyWeather));

        ancientWoodsGameMap.at(47,1).addItem(new BloodBerry());

        //Add the gates to and from this place
        burialGroundToWoodsGate.addMoveAction(new MoveActorAction(ancientWoodsGameMap.at(26,6),"to the Ancient Woods."));
        woodsToBurialGroundGate.addMoveAction(new MoveActorAction(burialGroundGameMap.at(5,6),"to the Burial Ground."));

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
                "#......++..++++.................=...~~~#",
                "#..........+++++.....................~~#",
                "#..........++++++..................~~~~#",
                "#.........~~+++++....................~~#",
                "########################################"
        );

        Gate woodsToRoomGate = new Gate();

        FancyGroundFactory roomGroundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new BottomlessPit(), woodsToRoomGate);

        GameMap roomGameMap = new GameMap(roomGroundFactory, roomMap);
        world.addGameMap(roomGameMap);

        Item giantHammer = new GiantHammer();
        roomGameMap.at(27, 6).addItem(giantHammer);

        // Adding the bushes and huts to the Room
        roomGameMap.at(30, 2).setGround(new RedWolfBush(sunnyWeather));

        roomGameMap.at(19,10).setGround(new ForestKeeperHut(sunnyWeather));
        roomGameMap.at(2, 11).setGround(new ForestKeeperHut(sunnyWeather));

        //Adding gates for access to the room
        woodsToRoomGate.addMoveAction(new MoveActorAction(roomGameMap.at(17,13),"to the room deep in the Woods."));

        //Add the new Overgrown Sanctuary game map
        List<String> overgrownSanctuaryMap = Arrays.asList(
        "++++.....++++........++++~~~~~.......~~~..........",
        "++++......++.........++++~~~~.........~.....=.....",
        "+++............~~~...+++++~~.......+++............",
        "..............~~~...++++++......++++++............",
        "................~~.++++........++++++~~....m......",
        "..............~~~..+++.........+++..~~~...........",
        "..................+++..........++...~~~...........",
        "~~~...................h.......~~~..~~~~.....~~~~..",
        "~~~~.....n......+++..........~~~~~~~~~~.......~~..",
        "~~~~............+++.........~~~~~~~~~~~~..........",
        "++~..............+++.......+~~........~~..........",
        "+++..............+++......+++..........~~.........",
        "+++..............+++......+++..........~~.........",
        "~~~....=.........+++......+++..........~~~........",
        "~~~~.............+++......+++..........~~~........"
        );

        Gate roomToOtherDestinationsGate = new Gate();
        Gate sanctuaryToRoomGate = new Gate();

        FancyGroundFactory overgrownSanctuaryGroundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new BottomlessPit(), new HollowSoldierGraveyard(), new GuardianHut(), new LivingBranchBush(), roomToOtherDestinationsGate, sanctuaryToRoomGate);

        GameMap overgrownSanctuaryGameMap = new GameMap(overgrownSanctuaryGroundFactory, overgrownSanctuaryMap);
        world.addGameMap(overgrownSanctuaryGameMap);

        //Create the gate which Abxervyer will set once dead
        roomToOtherDestinationsGate.addMoveAction(new MoveActorAction(ancientWoodsGameMap.at(45,3), "to the Ancient Woods."));
        roomToOtherDestinationsGate.addMoveAction(new MoveActorAction(overgrownSanctuaryGameMap.at(31, 2), "to the Overgrown Sanctuary."));

        // Add the boss to the room
        Abxervyer abxervyer = new Abxervyer(roomToOtherDestinationsGate, sunnyWeather);
        roomGameMap.at(35,1).addActor(abxervyer);

        //Add a gate from the sanctuary back to the room
        sanctuaryToRoomGate.addMoveAction(new MoveActorAction(roomGameMap.at(17,13), "to the room deep in the Woods."));

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
        Player player = new Player("The Abstracted One", '@', 150, 200);
        world.addPlayer(player, abandonedVillageGameMap.at(27, 5));

        // Add the monologues
        List<Monologue> monologues = Arrays.asList(
                new Monologue("I used to be an adventurer like you, but then …. Nevermind, let’s get back to smithing."),
                new Monologue("It’s dangerous to go alone. Take my creation with you on your adventure!"),
                new Monologue("Ah, it’s you. Let’s get back to make your weapons stronger."),
                new Monologue("Beyond the burial ground, you’ll come across the ancient woods ruled by Abxervyer. Use my creation to slay them and proceed further!", List.of(new ActorIsConsiousCondition(abxervyer))),
                new Monologue("Somebody once told me that a sacred tree rules the land beyond the ancient woods until this day.", List.of(new ActorIsUnconsiousCondition(abxervyer))),
                new Monologue("Hey now, that’s a weapon from a foreign land that I have not seen for so long. I can upgrade it for you if you wish.", List.of(new ActorIsHoldingWeaponItemCondition(player, Status.HOLDING_GREAT_KNIFE)))
        );

        // Add the blacksmith
        Blacksmith blacksmith = new Blacksmith(monologues);
        ancientWoodsGameMap.at(27, 6).addActor(blacksmith);

        world.run();
    }
}

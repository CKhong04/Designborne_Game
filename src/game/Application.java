package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Player;
import game.actors.enemies.Abxervyer;
import game.actors.traders.Traveller;
import game.grounds.*;
import game.grounds.spawners.Bush;
import game.grounds.spawners.HollowSoldierGraveyard;
import game.grounds.spawners.Hut;
import game.grounds.spawners.WanderingUndeadGraveyard;
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

        //An empty list of all the maps that are active in the game.
        List<GameMap> mapList = new ArrayList<>();

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
                "....~~~~~........+++++++.....+++++++++....###..##...++++...",
                "~~~~~~~..............+++...+++++....++++..#___..#...++.....",
                "~~~~~~.................++.................#..___#....+++...",
                "~~~~~~~~~.....................++++++......#######.......++.");

        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);
        mapList.add(gameMap);

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
        mapList.add(burialGroundGameMap);

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
        mapList.add(ancientWoodsGameMap);

        SunnyWeather sunnyWeather = new SunnyWeather();

        //Add the bushes and huts to the Ancient Woods map
        ancientWoodsGameMap.at(11, 3).setGround(new Bush(sunnyWeather));

        ancientWoodsGameMap.at(46,9).setGround(new Hut(sunnyWeather));
        ancientWoodsGameMap.at(8, 7).setGround(new Hut(sunnyWeather));

        ancientWoodsGameMap.at(47,1).addItem(new BloodBerry());

        //Add the gates to and from this place
        Gate burialGroundToWoodsGate = new Gate();
        burialGroundToWoodsGate.addMoveAction(new MoveActorAction(ancientWoodsGameMap.at(26,6),"to the Ancient Woods."));
        burialGroundGameMap.at(4,6).setGround(burialGroundToWoodsGate);

        Gate woodsToBurialGroundGate = new Gate();
        woodsToBurialGroundGate.addMoveAction(new MoveActorAction(burialGroundGameMap.at(5,6),"to the Burial Ground."));
        ancientWoodsGameMap.at(27,6).setGround(woodsToBurialGroundGate);

        //Add Traveller
        Actor Traveller = new Traveller();
        world.addPlayer(Traveller, ancientWoodsGameMap.at(6, 3));

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
        mapList.add(roomGameMap);

        // Adding the bushes and huts to the Room
        roomGameMap.at(30, 2).setGround(new Bush(sunnyWeather));

        roomGameMap.at(19,10).setGround(new Hut(sunnyWeather));
        roomGameMap.at(2, 11).setGround(new Hut(sunnyWeather));

        Item giantHammer = new GiantHammer();
        roomGameMap.at(27, 6).addItem(giantHammer);

        //Adding gates for access to the room
        Gate woodsToRoomGate = new Gate();
        woodsToRoomGate.addMoveAction(new MoveActorAction(roomGameMap.at(17,13),"to the room containing Abxervyer, the Forest Watcher."));
        ancientWoodsGameMap.at(44,3).setGround(woodsToRoomGate);

        // Add the boss to the room
        roomGameMap.at(35,1).addActor(new Abxervyer(mapList, sunnyWeather));

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
        world.addPlayer(player, gameMap.at(29, 5));
        world.run();
    }
}

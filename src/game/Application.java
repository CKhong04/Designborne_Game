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
import game.grounds.*;
import game.grounds.spawners.HollowSoldierGraveyard;
import game.grounds.spawners.WanderingUndeadGraveyard;
import game.items.consumableitems.HealingVile;
import game.utilities.FancyMessage;
import game.weapons.Broadsword;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Laura Zhakupova
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

        Item broadsword = new Broadsword(110,80,5,10,90);
        gameMap.at(27, 6).addItem(broadsword);

        gameMap.at(55,2).setGround(new WanderingUndeadGraveyard(25));
        gameMap.at(34,10).setGround(new WanderingUndeadGraveyard(25));

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

        burialGroundGameMap.at(23,2).setGround(new HollowSoldierGraveyard(10));
        burialGroundGameMap.at(13,11).setGround(new HollowSoldierGraveyard(10));
        burialGroundGameMap.at(2,14).setGround(new HollowSoldierGraveyard(10));

        // Set up gates
        Gate villageGate = new Gate();
        gameMap.at(27,0).setGround(villageGate);
        villageGate.addMoveAction(new MoveActorAction(burialGroundGameMap.at(39, 14), "to The Burial Ground"));

        Gate groundGate = new Gate();
        burialGroundGameMap.at(38,14).setGround(groundGate);
        groundGate.addMoveAction(new MoveActorAction(gameMap.at(27, 0), "to The Abandoned Village"));

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

        //Item n = new HealingVile();
        //gameMap.at(29,8).addItem(n);
        world.run();
    }
}

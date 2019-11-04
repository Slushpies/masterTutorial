package masterpeer;

import masterpeer.combat.*;
import masterpeer.fishing.BankFish;
import masterpeer.fishing.Fish;
import masterpeer.fishing.WalkToFish;
import masterpeer.mining.Mine;
import masterpeer.mining.RetrieveGear;
import masterpeer.mining.WalkToMine;
import masterpeer.quests.CloseQuestCompleteInterface;
import masterpeer.quests.cooksassistant.CollectItems;
import masterpeer.quests.cooksassistant.CollectMilk;
import masterpeer.quests.cooksassistant.MakeFlour;
import masterpeer.quests.cooksassistant.TalkToChef;
import masterpeer.quests.restlessghost.*;
import masterpeer.quests.romeoandjuliet.*;
import masterpeer.quests.runemysteries.TalkToAubury;
import masterpeer.quests.runemysteries.TalkToSedridor;
import masterpeer.quests.sheepshearer.*;
import masterpeer.quests.xmarksthespot.*;
import masterpeer.tutorial.ContinueChat;
import masterpeer.tutorial.banking.*;
import masterpeer.tutorial.beginAndFish.*;
import masterpeer.tutorial.combatCave.*;
import masterpeer.tutorial.end.Logout;
import masterpeer.tutorial.kitchen.CookDough;
import masterpeer.tutorial.kitchen.ExitKitchen;
import masterpeer.tutorial.kitchen.MixDough;
import masterpeer.tutorial.kitchen.TalkToKitchenGuide;
import masterpeer.tutorial.magic.*;
import masterpeer.tutorial.mining.*;
import masterpeer.tutorial.prayer.ExitChapel;
import masterpeer.tutorial.prayer.NavigateFriends;
import masterpeer.tutorial.prayer.NavigatePrayer;
import masterpeer.tutorial.prayer.TalkToPrayerGuide;
import masterpeer.tutorial.questGuy.EnterCave;
import masterpeer.tutorial.questGuy.NavigateQuestJournal;
import masterpeer.tutorial.questGuy.TalkToQuestGuide;
import masterpeer.tutorial.questGuy.ToggleRun;
import masterpeer.tutorial.woodcuttingAndFiremaking.CookShrimp;
import masterpeer.tutorial.woodcuttingAndFiremaking.CutTree;
import masterpeer.tutorial.woodcuttingAndFiremaking.ExitSurvivalTraining;
import masterpeer.tutorial.woodcuttingAndFiremaking.LightLogs;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.movement.position.Position;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static masterpeer.mining.MiningUtil.bestMiningSpot;

public class Gui extends JFrame {

    private boolean running = false;
    private JButton startButton;
    private JPanel gayPanel;
    private JButton miningButton;
    private JTextField monsterTextField;
    private JButton combatButton;
    private JPanel breakPanel;
    private JButton generateTotalRuntimeButton;
    private JTextField breakEveryMinTextField;
    private JTextField breakEveryMaxTextField;
    private JTextField breakForMinTextField;
    private JTextField breakForMaxTextField;
    private JTextField totalRuntimeMinTextField;
    private JTextField totalRuntimeMaxTextField;
    private JTextField totalRuntimeMinutesTextField;
    private JPanel breakTablePanel;
    private JButton tutorialButton;
    private JRadioButton miningBankRadioButton;
    private JButton questsButton;
    private JComboBox taskOptionsComboBox1;
    private JButton fishingButton;
    private JComboBox taskComboBox;
    private JComboBox taskOptionsComboBox2;
    private JLabel taskOption2Label;
    private JLabel taskOption1Label;
    private JLabel taskOption3Label;
    private JLabel taskLabel;
    private JList breakList;
    private JScrollPane breakScrollPane;
    private String targetMonster;
    private ArrayList<Break> breaks = new ArrayList<>();
    private String task;
    private String taskOption1;
    private String taskOption2;
    String[] combatLoot = {"Bones"};

    Gui(MasterPeer masterPeer) {
        setContentPane(gayPanel);
        pack();
        setVisible(true);

        //Add action listeners
        startButton.addActionListener(e -> {
            masterPeer.setPaused(false);
            this.setVisible(false);
            masterPeer.submit(new CloseQuestCompleteInterface());
            switch (task) {
                case "Fishing":
                    masterPeer.submit(new ClickLevelUpContinue(), new Fish(taskOptionsComboBox2.getSelectedItem().toString()), new BankFish(), new WalkToFish(taskOptionsComboBox1.getSelectedItem().toString(), taskOptionsComboBox2.getSelectedItem().toString()));
                    break;
                case "Quests":
                    String quest = taskOptionsComboBox1.getSelectedItem().toString();
                    switch (quest) {
                        case "The Restless Ghost":
                            masterPeer.submit(new CloseQuestCompleteInterface(), new masterpeer.quests.restlessghost.HandleDialog(), new EquipGhostspeakAmulet(), new GetGhostSkull(), new ReturnSkullToCoffin(), new SearchCoffin(),
                                    new TalkToFatherAereck(), new TalkToFatherUrhney(), new TalkToGhost());
                            break;
                        case "Romeo And Juliet":
                            masterPeer.submit(new CloseQuestCompleteInterface(), new masterpeer.quests.romeoandjuliet.HandleDialog(), new GetCadavaBerries(),
                                    new TalkToApothecary(), new TalkToFatherLawrence(), new TalkToJuliet(), new TalkToRomeo());
                            break;
                        case "Cook's Assistant":
                            masterPeer.submit(new CloseQuestCompleteInterface(), new masterpeer.quests.cooksassistant.HandleDialog(), new CollectItems(), new MakeFlour(), new CollectMilk(), new TalkToChef());
                            break;
                        case "X Marks the Spot":
                            masterPeer.submit(new CloseQuestCompleteInterface(), new masterpeer.quests.xmarksthespot.HandleDialog(), new TalkToVeos(), new BuySpade(), new OpenShop(), new SellJunk(), new FirstClue(), new SecondClue(), new ThirdClue(),
                                    new FourthClue(), new TurnInCasket());
                            break;
                        case "Rune Mysteries":
                            masterPeer.submit(new CloseQuestCompleteInterface(), new masterpeer.quests.runemysteries.HandleDialog(), new masterpeer.quests.runemysteries.Begin(), new TalkToSedridor(), new TalkToAubury());
                            break;
                        case "Sheep Shearer":
                            masterPeer.submit(new CloseQuestCompleteInterface(), new masterpeer.quests.sheepshearer.HandleDialog(), new DepositItemsToFreeSpace(), new ShearSheep(), new SpinWool(), new TakeShears(), new TalkToFred(), new UseSpinningWheel());
                            break;
                    }
                    break;
                case "Mining":
                    if (miningBankRadioButton.isSelected()) {
                        masterPeer.submit(new masterpeer.mining.DepositLoot(combatLoot));
                    }
                    Position miningSpot = bestMiningSpot();
                    masterPeer.submit(new Mine(miningSpot), new RetrieveGear(), new WalkToMine(miningSpot));
                    break;
                case "Combat":
                    this.targetMonster = monsterTextField.getText();
                    masterPeer.submit(new Fight(targetMonster, combatLoot), new ClickLevelUpContinue(), new WalkToMonsters(targetMonster, combatLoot), new PickupLoot(combatLoot, 20, 0, 10), new DepositLoot(combatLoot));
                    break;
                case "tutorial":
                    masterPeer.submit( //Beginning
                            new LookUpName(), new EnterName(), new SetName(), new CharacterDesign(), new ContinueChat(), new ContinueChatOld(), new TalkToGielinorGuide(), new NavigateOptions(), new ExperienceWithOSRS(),
                            //Survival training
                            new GoToFish(), new CatchFish(), new TalkToSurvivalGuide(), new CutTree(), new LightLogs(),
                            new CookShrimp(), new ExitSurvivalTraining(), new NavigateExperience(),
                            //Kitchen
                            new TalkToKitchenGuide(), new CookDough(), new ExitKitchen(), new MixDough(),
                            //Quest guy
                            new EnterCave(), new NavigateQuestJournal(), new TalkToQuestGuide(), new ToggleRun(),
                            //Mining cave
                            new ExitMiningArea(), new MineCopper(), new MineTin(), new Smelt(), new TalkToMiningGuide(), new SmithDagger(), new UseAnvil(), new AttackRat(), new CheckEquipped(),
                            //Combat cave
                            new EquipDagger(), new EquipSwordAndShield(), new EnterRatPen(), new ExitCave(), new NavigateCombat(), new NavigateEquipment(), new RangeRat(), new TalkToCombatGuide(),
                            //Bank
                            new CloseBank(), new ExitBank(), new ExitPoll(), new OpenAccountGuide(), new OpenBank(), new OpenPoll(), new TalkToGuide(),
                            //Prayer
                            new ExitChapel(), new NavigateFriends(), new NavigatePrayer(), new TalkToPrayerGuide(),
                            //Magic
                            new AttackChicken(), new GoToMainland(), new NavigateMagic(), new NotIronman(), new TalkToWizard(),
                            //End
                            new Logout());

                    break;
                default:
                    task = "tutorial";
            }
        });
        taskComboBox.addActionListener( e -> {
            taskOptionsComboBox1.removeAllItems();
            taskOptionsComboBox2.removeAllItems();

            this.task = taskComboBox.getSelectedItem().toString();
            /** Populate task option combo boxes for selected task **/
            switch(taskComboBox.getSelectedItem().toString()){
                case "Quests":
                    taskOptionsComboBox1.addItem("Cook's Assistant");
                    taskOptionsComboBox1.addItem("The Restless Ghost");
                    taskOptionsComboBox1.addItem("Romeo And Juliet");
                    taskOptionsComboBox1.addItem("Rune Mysteries");
                    taskOptionsComboBox1.addItem("Sheep Shearer");
                    taskOptionsComboBox1.addItem("X Marks the Spot");
                    break;
                case "Fishing":
                        taskOption1Label.setText("Location");
                        taskOptionsComboBox1.addItem("Draynor Village");
                        taskOption2Label.setText("Fishing method");
                        taskOptionsComboBox2.addItem("Small Net");
                        break;
            }
        });
        taskOptionsComboBox1.addActionListener(e -> {
            this.taskOption1 = taskOptionsComboBox1.getSelectedItem().toString();
        });
        taskOptionsComboBox2.addActionListener(e -> {
            this.taskOption2 = taskOptionsComboBox2.getSelectedItem().toString();
        });
        generateTotalRuntimeButton.addActionListener(e -> {
            generateBreaks();
        });
    }

    public String getTask() {
        return task;
    }

    public boolean isRunning() {
        return running;
    }

    private void clearButtonColors() {
        miningButton.setBackground(Color.gray);
        combatButton.setBackground(Color.gray);
        tutorialButton.setBackground(Color.gray);
    }

    private void generateBreaks() {
        int minTotalRuntime = Integer.parseInt(totalRuntimeMinTextField.getText());
        int maxTotalRuntime = Integer.parseInt(totalRuntimeMaxTextField.getText());
        int totalRuntime = Random.nextInt(minTotalRuntime, maxTotalRuntime);
        int minBreakEvery = Integer.parseInt(breakEveryMinTextField.getText());
        int maxBreakEvery = Integer.parseInt(breakEveryMaxTextField.getText());
        int minBreakLength = Integer.parseInt(breakForMinTextField.getText());
        int maxBreakLength = Integer.parseInt(breakForMaxTextField.getText());
        totalRuntimeMinutesTextField.setText(Integer.toString(totalRuntime));
        //Generate a breaking plan to meet total runtime requirement
        int runtimeGenerated = 0; //Total cumulative runtime generated so far in our breaking plan
        while (runtimeGenerated < totalRuntime) {
            int intervalLength = Random.nextInt(minBreakEvery, maxBreakEvery);
            int breakLength = Random.nextInt(minBreakLength, maxBreakLength);
            Break peerBreak = new Break(intervalLength, breakLength);
            breaks.add(peerBreak);
            runtimeGenerated += intervalLength;
        }
        //Create JTable from list of breaks
        Integer data[][] = new Integer[breaks.size()][2];
        String[] columnNames = {"Interval length", "Break length"};
        for (int i = 0; i < breaks.size(); i++) {
            data[i][0] = breaks.get(i).getIntervalLength();
            data[i][1] = breaks.get(i).getBreakLength();
        }
        JTable breakTable = new JTable(data, columnNames);
        breakTablePanel.add(breakTable);
        //breakTablePanel.setVisible(true);
        //breakTablePanel.repaint();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

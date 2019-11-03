package masterpeer;

import masterpeer.combat.*;
import masterpeer.mining.Mine;
import masterpeer.mining.RetrieveGear;
import masterpeer.mining.WalkToMine;
import masterpeer.quests.CloseQuestCompleteInterface;
import masterpeer.quests.cooksassistant.CollectItems;
import masterpeer.quests.cooksassistant.CollectMilk;
import masterpeer.quests.cooksassistant.MakeFlour;
import masterpeer.quests.cooksassistant.TalkToChef;
import masterpeer.quests.runemysteries.TalkToAubury;
import masterpeer.quests.runemysteries.TalkToSedridor;
import masterpeer.quests.sheepshearer.*;
import masterpeer.quests.xmarksthespot.*;
import masterpeer.quests.xmarksthespot.HandleDialog;
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
    private JRadioButton miningDropRadioButton;
    private JRadioButton miningBankRadioButton;
    private JButton questsButton;
    private JComboBox questComboBox;
    private JList breakList;
    private JScrollPane breakScrollPane;
    private String targetMonster;
    private ArrayList<Break> breaks = new ArrayList<>();
    private String task;
    String[] loot = {"Bones"};

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
                case "quests":
                    String quest = questComboBox.getSelectedItem().toString();
                    switch(quest){
                        case "Cook's Assistant":
                            masterPeer.submit(new masterpeer.quests.cooksassistant.HandleDialog(), new CollectItems(), new MakeFlour(), new CollectMilk(), new TalkToChef());
                            break;
                        case "X Marks The Spot":
                            masterPeer.submit(new masterpeer.quests.xmarksthespot.HandleDialog(), new TalkToVeos(), new BuySpade(), new OpenShop(), new SellJunk(), new FirstClue(), new SecondClue(), new ThirdClue(),
                                    new FourthClue(), new TurnInCasket());
                            break;
                        case "Rune Mysteries":
                            masterPeer.submit(new masterpeer.quests.runemysteries.HandleDialog(), new masterpeer.quests.runemysteries.Begin(), new TalkToSedridor(), new TalkToAubury());
                            break;
                        case "Sheep Shearer":
                            masterPeer.submit(new masterpeer.quests.sheepshearer.HandleDialog(), new ShearSheep(), new SpinWool(), new TakeShears(), new TalkToFred(), new UseSpinningWheel());
                            break;
                    }
                    break;
                case "mining":
                    if(miningBankRadioButton.isSelected()){
                        masterPeer.submit(new masterpeer.mining.DepositLoot(loot));
                    }
                    Position miningSpot = bestMiningSpot();
                    masterPeer.submit(new Mine(miningSpot), new RetrieveGear(), new WalkToMine(miningSpot));
                    break;
                case "combat":
                    this.targetMonster = monsterTextField.getText();
                    masterPeer.submit(new Fight(targetMonster, loot), new ClickLevelUpContinue(), new WalkToMonsters(targetMonster, loot), new PickupLoot(loot), new DepositLoot(loot));
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
        questComboBox.addActionListener(e -> {
            this.task = "quests";
            clearButtonColors();
            questsButton.setBackground(Color.green);
        });
        questsButton.addActionListener(e -> {
            this.task = "quests";
            clearButtonColors();
            questsButton.setBackground(Color.green);
        });
        miningButton.addActionListener(e -> {
            this.task = "mining";
            clearButtonColors();
            miningButton.setBackground(Color.green);
        });
        combatButton.addActionListener(e -> {
            this.task = "combat";
            clearButtonColors();
            combatButton.setBackground(Color.green);
        });
        tutorialButton.addActionListener(e -> {
            this.task = "tutorial";
            clearButtonColors();
            tutorialButton.setBackground(Color.green);
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

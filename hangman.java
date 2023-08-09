package AI_LAB;

import java.util.*;
import java.io.*;
import java.util.concurrent.*;

public class HangMan {
    // ASCII art representing hangman graphics
    private static final String[] HANGMAN_PICS = {
            "   +---+\n" +
                    "   |   |\n" +
                    "       |\n" +
                    "       |\n" +
                    "       |\n" +
                    "       |\n",

            "   +---+\n" +
                    "   |   |\n" +
                    "   O   |\n" +
                    "       |\n" +
                    "       |\n" +
                    "       |\n",

            "   +---+\n" +
                    "   |   |\n" +
                    "   O   |\n" +
                    "   |   |\n" +
                    "       |\n" +
                    "       |\n",

            "   +---+\n" +
                    "   |   |\n" +
                    "   O   |\n" +
                    "  /|   |\n" +
                    "       |\n" +
                    "       |\n",

            "   +---+\n" +
                    "   |   |\n" +
                    "   O   |\n" +
                    "  /|\\  |\n" +
                    "       |\n" +
                    "       |\n",

            "   +---+\n" +
                    "   |   |\n" +
                    "   O   |\n" +
                    "  /|\\  |\n" +
                    "  /    |\n" +
                    "       |\n",

            "   +---+\n" +
                    "   |   |\n" +
                    "   O   |\n" +
                    "  /|\\  |\n" +
                    "  / \\  |\n" +
                    "       |\n"
    };
    public static void main(String[] args) {
        menu(); // Entry point of the Hangman game
    }
    // Main menu of the game
    private static void menu(){
        Scanner sc=new Scanner(System.in);
        System.out.println("\n" +
                "█▀▀ █▀▀ █── █▀▀ █▀▀ ▀▀█▀▀ 　 █▀▀█ █── █▀▀█ █──█ █▀▀ █▀▀█ 　 ▀▀█▀▀ █──█ █▀▀█ █▀▀ \n" +
                "▀▀█ █▀▀ █── █▀▀ █── ──█── 　 █──█ █── █▄▄█ █▄▄█ █▀▀ █▄▄▀ 　 ──█── █▄▄█ █──█ █▀▀ \n" +
                "▀▀▀ ▀▀▀ ▀▀▀ ▀▀▀ ▀▀▀ ──▀── 　 █▀▀▀ ▀▀▀ ▀──▀ ▄▄▄█ ▀▀▀ ▀─▀▀ 　 ──▀── ▄▄▄█ █▀▀▀ ▀▀▀");
        System.out.println("1. Player vs AI \n2. Dual Player");
        byte select=sc.nextByte();
        if(select==1){
            playerVsAi();
        }
        else{
            dualPlayer();
        }
    }
    // Game mode: Player vs AI
    private static void playerVsAi(){
        // Read words from a file
        File file=new File("E:\\DSA\\src\\AI_LAB\\words.txt");
        try (Scanner sc = new Scanner(file)) {
            List<String> words=new ArrayList<>();
            //add words to arraylist
            while(sc.hasNextLine()){
               words.add(sc.nextLine());
            }
            Random r=new Random();
            //selecting using random
            System.out.println("\n" +
                    "▄▀█ █   █ █▀   █▀ █▀▀ █░░ █▀▀ ▀█▀ █ █▄░█ █▀▀   ▄▀█   █░█░█ █▀█ █▀█ █▀▄ ░ ░ ░ ░\n" +
                    "█▀█ █   █ ▄█   ▄█ ██▄ █▄▄ █▄▄ ░█░ █ █░▀█ █▄█   █▀█   ▀▄▀▄▀ █▄█ █▀▄ █▄▀ ▄ ▄ ▄ ▄");
            TimeUnit.SECONDS.sleep(1);
            String selection=words.get(r.nextInt(words.size()));
            play(selection); // Start the game with the selected word
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private static void dualPlayer(){
        Scanner sc=new Scanner(System.in);
        System.out.println("\n" +
                "█▀█ █░░ ▄▀█ █▄█ █▀▀ █▀█   ▀█   █ █▀   █▀ █▀▀ █░░ █▀▀ █▀▀ ▀█▀ █ █▄░█ █▀▀   ▄▀█   █░█░█ █▀█ █▀█ █▀▄ ░ ░ ░\n" +
                "█▀▀ █▄▄ █▀█ ░█░ ██▄ █▀▄   █▄   █ ▄█   ▄█ ██▄ █▄▄ ██▄ █▄▄ ░█░ █ █░▀█ █▄█   █▀█   ▀▄▀▄▀ █▄█ █▀▄ █▄▀ ▄ ▄ ▄");
        String selection=sc.next();
        play(selection); // Start the game with the provided word
    }
    // Play the Hangman game with a given word
    private static void play(String word){
        boolean gameover=false;
        StringBuffer guess=new StringBuffer();
        for(int i=0;i<word.length();i++) guess.append("-");
        int correct=0,wrong=0;
        System.out.println(HANGMAN_PICS[wrong]);
        System.out.println(word);
        while(!gameover){
            System.out.println(guess);
            System.out.println("Guess a character");
            Scanner sc=new Scanner(System.in);
            char g=sc.next().charAt(0);

            String temp=word;
            //correct guess
            boolean cg=false;
            int r=0;
            // Check if the guessed character is correct
            while(temp.contains(String.valueOf(g))){
                cg=true;
                correct++;
                int idx=temp.indexOf(g);
                guess.setCharAt(r+idx,(char)(g));
                temp=temp.substring(idx+1);
                r+=idx+1;
            }

            // Update game state based on the guess
            if(cg){
                System.out.println(HANGMAN_PICS[wrong]);
                System.out.println(guess+" "+"\n Wrong Count: "+" "+wrong);
            }
            else{
                wrong++;
                System.out.println(HANGMAN_PICS[wrong]);
                System.out.println(guess+" "+"\n Wrong Count: "+" "+wrong);
            }

            // Check game ending conditions
            if(correct==word.length()){
                gameover=true;
                System.out.println("\n" +
                            "█▄█ █▀█ █░█   █░█░█ █ █▄░█\n" +
                            "░█░ █▄█ █▄█   ▀▄▀▄▀ █ █░▀█");
                if(tryagain()) menu();
            }
            if(wrong==5){
                gameover=true;
                System.out.println("\n" +
                            "█▀▀▀ █▀▀█ █▀▄▀█ █▀▀ 　 █▀▀█ ▀█░█▀ █▀▀ █▀▀█ \n" +
                            "█░▀█ █▄▄█ █░▀░█ █▀▀ 　 █░░█ ░█▄█░ █▀▀ █▄▄▀ \n" +
                            "▀▀▀▀ ▀░░▀ ▀░░░▀ ▀▀▀ 　 ▀▀▀▀ ░░▀░░ ▀▀▀ ▀░▀▀");
                if(tryagain()) menu();
            }
        }
    }
    // Prompt the player to play again or exit
    private static boolean tryagain(){
        Scanner sc=new Scanner(System.in);
        System.out.println("\n" +
                "█░█░█ █▀█ █░█ █░░ █▀▄   █▄█ █▀█ █░█   █░░ █ █▄▀ █▀▀   ▀█▀ █▀█   ▀█▀ █▀█ █▄█   ▄▀█ █▀▀ ▄▀█ █ █▄░█ ▀█\n" +
                "▀▄▀▄▀ █▄█ █▄█ █▄▄ █▄▀   ░█░ █▄█ █▄█   █▄▄ █ █░█ ██▄   ░█░ █▄█   ░█░ █▀▄ ░█░   █▀█ █▄█ █▀█ █ █░▀█ ░▄");
        System.out.println("1.YES\n2.NO");
        byte selection=sc.nextByte();
        if(selection==1){
            return true;
        }
        else return false;
    }

}

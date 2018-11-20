package server.controllers;


public class QuizController {
    public int userScore = 0;
    public String AnswerCheck(){
        String userAnswer = "a";
        String answerCorrect = "A";

        if (userAnswer.toUpperCase() == answerCorrect){
            userScore = userScore + 1;
        }
        return null;
    }
    public void GenerateUserScorePecentage(){
        int userScorePercentage = ( userScore * 10);

    }
}
